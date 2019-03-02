/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.edu.xmut.core.persistence;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.filter.impl.CachingWrapperFilter;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.util.Assert;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.edu.xmut.core.persistence.Filter.Operator;
import cn.edu.xmut.core.persistence.Order.Direction;
import cn.edu.xmut.core.utils.Reflections;
import cn.edu.xmut.core.utils.StringUtils;

/**
 * DAO支持类实现
 * @author lilin
 * @version 2013-05-15
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

	/**
	 * 获取实体工厂管理对象
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 实体类类型(由构造方法自动赋值)
	 */
	private Class<T> entityClass;
	/** 别名数 */
	private static volatile long aliasCount = 0;

	/**
	 * 构造方法，根据实例类自动获取实体类类型
	 */
	public BaseDaoImpl() {
		entityClass = Reflections.getClassGenricType(getClass());
	}

	/**
	 * 获取实体工厂管理对象
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 获取 Session
	 */
	public Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}

	/**
	 * 强制与数据库同步
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * 清除缓存数据
	 */
	public void clear() {
		getSession().clear();
	}

	// -------------- QL Query --------------

	/**
	 * QL 分页查询
	 * @param page
	 * @param qlString
	 * @param parameter
	 * @return
	 */
	@Override
	public Page<T> find(Pageable pageable, String qlString, Object... parameter) {
		// get count

		String countQlString = "select count(*) " + removeSelect(removeOrders(qlString));
		Query query = createQuery(countQlString, parameter);
		List<Object> list = query.list();

		long total = -1L;
		if (list.size() > 0) {
			total = Long.valueOf(list.get(0).toString());
		} else {
			total = list.size();
		}
		if (total < 1) {
			return new Page<T>(null, total, pageable);
		}

		Query sqlquery = createQuery(qlString, parameter);

		sqlquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		sqlquery.setMaxResults(pageable.getPageSize());

		return new Page<T>(sqlquery.list(), total, pageable);
	}

	/**
	 * QL 查询
	 * @param qlString
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> find(String qlString, Object... parameter) {
		Query query = createQuery(qlString, parameter);
		return query.list();
	}

	/**
	 * QL 更新
	 * @param qlString
	 * @param parameter
	 * @return
	 */
	public int update(String qlString, Object... parameter) {
		return createQuery(qlString, parameter).executeUpdate();
	}

	/**
	 * 创建 QL 查询对象
	 * @param qlString
	 * @param parameter
	 * @return
	 */
	public Query createQuery(String qlString, Object... parameter) {
		Query query = getSession().createQuery(qlString);
		setParameter(query, parameter);
		return query;
	}

	// -------------- SQL Query --------------

	/**
	 * SQL 分页查询
	 * @param page
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public Page<T> findBySql(Pageable pageable, String sqlString, Object... parameter) {
		return findBySql(pageable, sqlString, null, parameter);
	}

	/**
	 * SQL 分页查询
	 * @param page
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> findBySql(Pageable pageable, String sqlString, Class<?> resultClass, Object... parameter) {
		String sql = sqlString;
		System.out.println(sql);
		String countSqlString = "select count(*) " + removeSelect(removeOrders(sqlString));
		//	        page.setCount(Long.valueOf(createSqlQuery(countSqlString, parameter).uniqueResult().toString()));
		Query query = createSqlQuery(countSqlString, parameter);
		List<Object> list = query.list();
		long total = -1L;
		if (list.size() > 0) {
			total = Long.valueOf(list.get(0).toString());
		} else {
			total = list.size();
		}
		if (total < 1) {
			return new Page<T>(null, total, pageable);
		}

		SQLQuery sqlquery = createSqlQuery(sql, parameter);
		// set page
		sqlquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		sqlquery.setMaxResults(pageable.getPageSize());

		setResultTransformer(sqlquery, resultClass);

		return new Page<T>(sqlquery.list(), total, pageable);
	}

	/**
	 * SQL 查询
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> List<E> findBySql(String sqlString, Object... parameter) {
		return findBySql(sqlString, null, parameter);
	}

	/**
	 * SQL 查询
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> findBySql(String sqlString, Class<?> resultClass, Object... parameter) {
		SQLQuery query = createSqlQuery(sqlString, parameter);
		setResultTransformer(query, resultClass);
		return query.list();
	}
	
	
	/**
	 * SQL 查询
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public <E> E getBySql(String sqlString, Object... parameter) {
		return getBySql(sqlString, null, parameter);
	}
	
	/**
	 * SQL 查询
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> E getBySql(String sqlString, Class<?> resultClass, Object... parameter) {
		SQLQuery query = createSqlQuery(sqlString, parameter);
		setResultTransformer(query, resultClass);
		List<?> list = query.list();
		if(list == null || list.size() == 0) {
			return null;
		} else {
			return (E) query.list().get(0);
		}
	}

	/**
	 * 计数
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	@Override
	public long countBySql(String sqlString, Object... parameter) {
		return countBySql(sqlString, null, parameter);
	}

	/**
	 * 计数
	 * @param sqlString
	 * @param resultClass
	 * @param parameter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long countBySql(String sqlString, Class<?> resultClass,
			Object... parameter) {
		String countSqlString = "select count(*) " + removeSelect(removeOrders(sqlString));
		Query query = createSqlQuery(countSqlString, parameter);
		List<Object> list = query.list();
		long total = -1L;
		if (list.size() > 0) {
			total = Long.valueOf(list.get(0).toString());
		} else {
			total = list.size();
		}
		return total;
	}
	
	
	/**
	 * SQL 更新
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public int updateBySql(String sqlString, Object... parameter) {
		return createSqlQuery(sqlString, parameter).executeUpdate();
	}

	/**
	 * 创建 SQL 查询对象
	 * @param sqlString
	 * @param parameter
	 * @return
	 */
	public SQLQuery createSqlQuery(String sqlString, Object... parameter) {
		SQLQuery query = getSession().createSQLQuery(sqlString);
		setParameter(query, parameter);
		return query;
	}

	// -------------- Query Tools --------------

	/**
	 * 设置查询结果类型
	 * @param query
	 * @param resultClass
	 */
	private void setResultTransformer(SQLQuery query, Class<?> resultClass) {
		if (resultClass != null) {
			if (resultClass == Map.class) {
				query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			} else if (resultClass == List.class) {
				query.setResultTransformer(Transformers.TO_LIST);
			} else {
				query.addEntity(resultClass);
			}
		}
	}

	/**
	 * 设置查询参数
	 * @param query
	 * @param parameter
	 */
	private void setParameter(Query query, Object... parameter) {
		if (parameter != null) {
			for (int i = 0; i < parameter.length; i++) {
				query.setParameter(i, parameter[i]);
			}
		}
	}

	/** 
	 * 去除qlString的select子句。 
	 * @param hql 
	 * @return 
	 */
	private String removeSelect(String qlString) {
		int beginPos = qlString.toLowerCase().indexOf("from");
		return qlString.substring(beginPos);
	}

	/** 
	 * 去除hql的orderBy子句。 
	 * @param hql 
	 * @return 
	 */
	private String removeOrders(String qlString) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(qlString);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	// -------------- Criteria --------------
	@Override
	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));
		return findList(criteriaQuery, first, count, filters, orders);
	}

	@Override
	public CriteriaQuery<T> createCriteriaQuery() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));
		return criteriaQuery;
	}

	@Override
	public Page<T> find(Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		criteriaQuery.select(criteriaQuery.from(entityClass));
		return find(criteriaQuery, pageable);
	}

	@Override
	public Page<T> find(CriteriaQuery<T> criteriaQuery, Pageable pageable) {

		if (pageable == null) {
			pageable = new Pageable();
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		Root<T> root = getRoot(criteriaQuery);
		addRestrictions(criteriaQuery, pageable);
		addOrders(criteriaQuery, pageable);
		//默认排序这里先取消掉
		//		if (criteriaQuery.getOrderList().isEmpty()) {
		//			if (OrderEntity.class.isAssignableFrom(entityClass)) {
		//				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
		//			} else {
		//				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(OrderEntity.CREATE_DATE_PROPERTY_NAME)));
		//			}
		//		}
		long total = count(criteriaQuery, null);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<T>(query.getResultList(), total, pageable);
	}

	@Override
	public List<T> findList(CriteriaQuery<T> criteriaQuery, Integer first, Integer count, List<Filter> filters,
			List<Order> orders) {
		Assert.notNull(criteriaQuery);
		Assert.notNull(criteriaQuery.getSelection());
		Assert.notEmpty(criteriaQuery.getRoots());

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		Root<T> root = getRoot(criteriaQuery);
		addRestrictions(criteriaQuery, filters);
		addOrders(criteriaQuery, orders);
		//		if (criteriaQuery.getOrderList().isEmpty()) {
		//			if (OrderEntity.class.isAssignableFrom(entityClass)) {
		//				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(OrderEntity.ORDER_PROPERTY_NAME)));
		//			} else {
		//				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(OrderEntity.CREATE_DATE_PROPERTY_NAME)));
		//			}
		//		}
		TypedQuery<T> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
		if (first != null) {
			query.setFirstResult(first);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	protected Long count(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
		Assert.notNull(criteriaQuery);
		Assert.notNull(criteriaQuery.getSelection());
		Assert.notEmpty(criteriaQuery.getRoots());

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		addRestrictions(criteriaQuery, filters);

		CriteriaQuery<Long> countCriteriaQuery = criteriaBuilder.createQuery(Long.class);
		for (Root<?> root : criteriaQuery.getRoots()) {
			Root<?> dest = countCriteriaQuery.from(root.getJavaType());
			dest.alias(getAlias(root));
			copyJoins(root, dest);
		}

		Root<?> countRoot = getRoot(countCriteriaQuery, criteriaQuery.getResultType());
		countCriteriaQuery.select(criteriaBuilder.count(countRoot));

		if (criteriaQuery.getGroupList() != null) {
			countCriteriaQuery.groupBy(criteriaQuery.getGroupList());
		}
		if (criteriaQuery.getGroupRestriction() != null) {
			countCriteriaQuery.having(criteriaQuery.getGroupRestriction());
		}
		if (criteriaQuery.getRestriction() != null) {
			countCriteriaQuery.where(criteriaQuery.getRestriction());
		}
		return entityManager.createQuery(countCriteriaQuery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
	}

	/**
	 * 
	 * 如何描述该方法
	 * 这个方法做什么？
	 * @param selection
	 * @return
	 */
	private synchronized String getAlias(Selection<?> selection) {
		if (selection != null) {
			String alias = selection.getAlias();
			if (alias == null) {
				if (aliasCount >= 1000) {
					aliasCount = 0;
				}
				alias = "shopxxGeneratedAlias" + aliasCount++;
				selection.alias(alias);
			}
			return alias;
		}
		return null;
	}

	private void copyJoins(From<?, ?> from, From<?, ?> to) {
		for (Join<?, ?> join : from.getJoins()) {
			Join<?, ?> toJoin = to.join(join.getAttribute().getName(), join.getJoinType());
			toJoin.alias(getAlias(join));
			copyJoins(join, toJoin);
		}
		for (Fetch<?, ?> fetch : from.getFetches()) {
			Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
			copyFetches(fetch, toFetch);
		}
	}

	private void copyFetches(Fetch<?, ?> from, Fetch<?, ?> to) {
		for (Fetch<?, ?> fetch : from.getFetches()) {
			Fetch<?, ?> toFetch = to.fetch(fetch.getAttribute().getName());
			copyFetches(fetch, toFetch);
		}
	}

	private void addRestrictions(CriteriaQuery<T> criteriaQuery, List<Filter> filters) {
		if (criteriaQuery == null || filters == null || filters.isEmpty()) {
			return;
		}
		Root<T> root = getRoot(criteriaQuery);
		if (root == null) {
			return;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction()
				: criteriaBuilder.conjunction();
		for (Filter filter : filters) {
			if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
				continue;
			}
			if (filter.getOperator() == Operator.eq && filter.getValue() != null) {
				if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
					restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(
							criteriaBuilder.lower(root.<String> get(filter.getProperty())),
							((String) filter.getValue()).toLowerCase()));
				} else {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
				}
			} else if (filter.getOperator() == Operator.ne && filter.getValue() != null) {
				if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
					restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(
							criteriaBuilder.lower(root.<String> get(filter.getProperty())),
							((String) filter.getValue()).toLowerCase()));
				} else {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
				}
			} else if (filter.getOperator() == Operator.gt && filter.getValue() != null) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.gt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
			} else if (filter.getOperator() == Operator.lt && filter.getValue() != null) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.lt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
			} else if (filter.getOperator() == Operator.ge && filter.getValue() != null) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.ge(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
			} else if (filter.getOperator() == Operator.le && filter.getValue() != null) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.le(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
			} else if (filter.getOperator() == Operator.like && filter.getValue() != null
					&& filter.getValue() instanceof String) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.like(root.<String> get(filter.getProperty()), (String) filter.getValue()));
			} else if (filter.getOperator() == Operator.in && filter.getValue() != null) {
				restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).in(filter.getValue()));
			} else if (filter.getOperator() == Operator.isNull) {
				restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNull());
			} else if (filter.getOperator() == Operator.isNotNull) {
				restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNotNull());
			}
		}
		criteriaQuery.where(restrictions);
	}

	private void addRestrictions(CriteriaQuery<T> criteriaQuery, Pageable pageable) {
		if (criteriaQuery == null || pageable == null) {
			return;
		}
		Root<T> root = getRoot(criteriaQuery);
		if (root == null) {
			return;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		Predicate restrictions = criteriaQuery.getRestriction() != null ? criteriaQuery.getRestriction()
				: criteriaBuilder.conjunction();
		if (StringUtils.isNotEmpty(pageable.getSearchProperty()) && StringUtils.isNotEmpty(pageable.getSearchValue())) {
			restrictions = criteriaBuilder.and(
					restrictions,
					criteriaBuilder.like(root.<String> get(pageable.getSearchProperty()),
							"%" + pageable.getSearchValue() + "%"));
		}
		if (pageable.getFilters() != null) {
			for (Filter filter : pageable.getFilters()) {
				if (filter == null || StringUtils.isEmpty(filter.getProperty())) {
					continue;
				}
				if (filter.getOperator() == Operator.eq && filter.getValue() != null) {
					if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(
								criteriaBuilder.lower(root.<String> get(filter.getProperty())),
								((String) filter.getValue()).toLowerCase()));
					} else {
						restrictions = criteriaBuilder.and(restrictions,
								criteriaBuilder.equal(root.get(filter.getProperty()), filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.ne && filter.getValue() != null) {
					if (filter.getIgnoreCase() != null && filter.getIgnoreCase() && filter.getValue() instanceof String) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(
								criteriaBuilder.lower(root.<String> get(filter.getProperty())),
								((String) filter.getValue()).toLowerCase()));
					} else {
						restrictions = criteriaBuilder.and(restrictions,
								criteriaBuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
					}
				} else if (filter.getOperator() == Operator.gt && filter.getValue() != null) {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.gt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
				} else if (filter.getOperator() == Operator.lt && filter.getValue() != null) {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.lt(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
				} else if (filter.getOperator() == Operator.ge && filter.getValue() != null) {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.ge(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
				} else if (filter.getOperator() == Operator.le && filter.getValue() != null) {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.le(root.<Number> get(filter.getProperty()), (Number) filter.getValue()));
				} else if (filter.getOperator() == Operator.like && filter.getValue() != null
						&& filter.getValue() instanceof String) {
					restrictions = criteriaBuilder.and(restrictions,
							criteriaBuilder.like(root.<String> get(filter.getProperty()), (String) filter.getValue()));
				} else if (filter.getOperator() == Operator.in && filter.getValue() != null) {
					restrictions = criteriaBuilder.and(restrictions,
							root.get(filter.getProperty()).in(filter.getValue()));
				} else if (filter.getOperator() == Operator.isNull) {
					restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNull());
				} else if (filter.getOperator() == Operator.isNotNull) {
					restrictions = criteriaBuilder.and(restrictions, root.get(filter.getProperty()).isNotNull());
				}
			}
		}
		criteriaQuery.where(restrictions);
	}

	private void addOrders(CriteriaQuery<T> criteriaQuery, List<Order> orders) {
		if (criteriaQuery == null || orders == null || orders.isEmpty()) {
			return;
		}
		Root<T> root = getRoot(criteriaQuery);
		if (root == null) {
			return;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>();
		if (!criteriaQuery.getOrderList().isEmpty()) {
			orderList.addAll(criteriaQuery.getOrderList());
		}
		for (Order order : orders) {
			if (order.getDirection() == Direction.asc) {
				orderList.add(criteriaBuilder.asc(root.get(order.getProperty())));
			} else if (order.getDirection() == Direction.desc) {
				orderList.add(criteriaBuilder.desc(root.get(order.getProperty())));
			}
		}
		criteriaQuery.orderBy(orderList);
	}

	private void addOrders(CriteriaQuery<T> criteriaQuery, Pageable pageable) {
		if (criteriaQuery == null || pageable == null) {
			return;
		}
		Root<T> root = getRoot(criteriaQuery);
		if (root == null) {
			return;
		}
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>();
		if (!criteriaQuery.getOrderList().isEmpty()) {
			orderList.addAll(criteriaQuery.getOrderList());
		}
		if (StringUtils.isNotEmpty(pageable.getOrderProperty()) && pageable.getOrderDirection() != null) {
			if (pageable.getOrderDirection() == Direction.asc) {
				orderList.add(criteriaBuilder.asc(root.get(pageable.getOrderProperty())));
			} else if (pageable.getOrderDirection() == Direction.desc) {
				orderList.add(criteriaBuilder.desc(root.get(pageable.getOrderProperty())));
			}
		}
		if (pageable.getOrders() != null) {
			for (Order order : pageable.getOrders()) {
				if (order.getDirection() == Direction.asc) {
					orderList.add(criteriaBuilder.asc(root.get(order.getProperty())));
				} else if (order.getDirection() == Direction.desc) {
					orderList.add(criteriaBuilder.desc(root.get(order.getProperty())));
				}
			}
		}
		criteriaQuery.orderBy(orderList);
	}

	private Root<T> getRoot(CriteriaQuery<T> criteriaQuery) {
		if (criteriaQuery != null) {
			return getRoot(criteriaQuery, criteriaQuery.getResultType());
		}
		return null;
	}

	private Root<T> getRoot(CriteriaQuery<?> criteriaQuery, Class<T> clazz) {
		if (criteriaQuery != null && criteriaQuery.getRoots() != null && clazz != null) {
			for (Root<?> root : criteriaQuery.getRoots()) {
				if (clazz.equals(root.getJavaType())) {
					return (Root<T>) root.as(clazz);
				}
			}
		}
		return null;
	}

	// -------------- Hibernate search --------------

	/**
	 * 获取全文Session
	 */
	public FullTextSession getFullTextSession() {
		return Search.getFullTextSession(getSession());
	}

	/**
	 * 建立索引
	 */
	public void createIndex() {
		try {
			getFullTextSession().createIndexer(entityClass).startAndWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 全文检索
	 * @param page 分页对象
	 * @param query 关键字查询对象
	 * @param queryFilter 查询过滤对象
	 * @param sort 排序对象
	 * @return 分页对象
	 */
	@SuppressWarnings("unchecked")
	public Page<T> search(Pageable pageable, BooleanQuery query, BooleanQuery queryFilter, Sort sort) {

		// 按关键字查询
		FullTextQuery fullTextQuery = getFullTextSession().createFullTextQuery(query, entityClass);

		// 过滤无效的内容
		fullTextQuery.setFilter(new CachingWrapperFilter(new QueryWrapperFilter(queryFilter)));

		// 按时间排序
		fullTextQuery.setSort(sort);

		// 定义分页

		fullTextQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		fullTextQuery.setMaxResults(pageable.getPageSize());
		// 先从持久化上下文中查找对象，如果没有再从二级缓存中查找
		fullTextQuery.initializeObjectsWith(ObjectLookupMethod.SECOND_LEVEL_CACHE, DatabaseRetrievalMethod.QUERY);

		return new Page<T>(fullTextQuery.list(), fullTextQuery.getResultSize(), pageable);
	}

	/**
	 * 获取全文查询对象
	 */
	public BooleanQuery getFullTextQuery(BooleanClause... booleanClauses) {
		BooleanQuery booleanQuery = new BooleanQuery();
		for (BooleanClause booleanClause : booleanClauses) {
			booleanQuery.add(booleanClause);
		}
		return booleanQuery;
	}

	/**
	 * 获取全文查询对象
	 * @param q 查询关键字
	 * @param fields 查询字段
	 * @return 全文查询对象
	 */
	public BooleanQuery getFullTextQuery(String q, String... fields) {
		Analyzer analyzer = new IKAnalyzer();
		BooleanQuery query = new BooleanQuery();
		try {
			if (StringUtils.isNotBlank(q)) {
				for (String field : fields) {
					QueryParser parser = new QueryParser(Version.LUCENE_36, field, analyzer);
					query.add(parser.parse(q), Occur.SHOULD);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return query;
	}

	/**
	 * 设置关键字高亮
	 * @param query 查询对象
	 * @param list 设置高亮的内容列表
	 * @param fields 字段名
	 */
	public List<T> keywordsHighlight(BooleanQuery query, List<T> list, String... fields) {
		Analyzer analyzer = new IKAnalyzer();
		Formatter formatter = new SimpleHTMLFormatter("<span class=\"highlight\">", "</span>");
		Highlighter highlighter = new Highlighter(formatter, new QueryScorer(query));
		highlighter.setTextFragmenter(new SimpleFragmenter(130));
		for (T entity : list) {
			try {
				for (String field : fields) {
					String text = StringUtils.replaceHtml((String) Reflections.invokeGetter(entity, field));
					String description = highlighter.getBestFragment(analyzer, field, text);
					if (description != null) {
						Reflections.invokeSetter(entity, fields[0], description);
						break;
					}
					Reflections.invokeSetter(entity, fields[0], StringUtils.abbr(text, 130));
				}
				//Reflections.invokeSetter(entity, fields[1], "sdfkjsdlkfjklsdjf");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidTokenOffsetsException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//-------------- 废除方法----------------

	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	@Deprecated
	public Page<T> find(Page<T> page) {
		return find(page, createDetachedCriteria());
	}

	/**
	 * 使用检索标准对象分页查询
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	@Deprecated
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria) {
		return find(page, detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
	}

	/**
	 * 使用检索标准对象分页查询
	 * @param page
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public Page<T> find(Page<T> page, DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {
		// get count
		if (!page.isNotCount()) {
			page.setTotal(count(detachedCriteria));
			if (page.getTotal() < 1) {
				return page;
			}
		}
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		// set page

		criteria.setFirstResult(page.getFirstResult());
		criteria.setMaxResults(page.getMaxResults());

		// order by
		//		if (StringUtils.isNotBlank(page.getOrderBy())) {
		//			for (String order : StringUtils.split(page.getOrderBy(), ",")) {
		//				String[] o = StringUtils.split(order, " ");
		//				if (o.length == 1) {
		//					criteria.addOrder(Order.asc(o[0]));
		//				} else if (o.length == 2) {
		//					if ("DESC".equals(o[1].toUpperCase())) {
		//						criteria.addOrder(Order.desc(o[0]));
		//					} else {
		//						criteria.addOrder(Order.asc(o[0]));
		//					}
		//				}
		//			}
		//		}
		page.setContent(criteria.list());
		return page;
	}

	/**
	 * 使用检索标准对象查询
	 * @param detachedCriteria
	 * @return
	 */
	@Deprecated
	public List<T> find(DetachedCriteria detachedCriteria) {
		return find(detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
	}

	/**
	 * 使用检索标准对象查询
	 * @param detachedCriteria
	 * @param resultTransformer
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<T> find(DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setResultTransformer(resultTransformer);
		return criteria.list();
	}

	/**
	 * 使用检索标准对象查询记录数
	 * @param detachedCriteria
	 * @return
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public long count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		long totalCount = 0;
		try {
			// Get orders
			Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
			field.setAccessible(true);
			List orderEntrys = (List) field.get(criteria);
			// Remove orders
			field.set(criteria, new ArrayList());
			// Get count
			criteria.setProjection(Projections.rowCount());
			totalCount = Long.valueOf(criteria.uniqueResult().toString());
			// Clean count
			criteria.setProjection(null);
			// Restore orders
			field.set(criteria, orderEntrys);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	/**
	 * 创建与会话无关的检索标准对象
	 * @param criterions Restrictions.eq("name", value);
	 * @return 
	 */
	@Deprecated
	public DetachedCriteria createDetachedCriteria(Criterion... criterions) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		for (Criterion c : criterions) {
			dc.add(c);
		}
		return dc;
	}

	/* (non-Javadoc)
	 * @see cn.edu.xmut.core.persistence.BaseDao#count(java.lang.String, java.lang.Class, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long count(String sqlString, Class<?> resultClass, Object... parameter) {
		Query query = createSqlQuery(sqlString, parameter);
		List<Object> list = query.list();
		long total = -1L;
		if (list.size() > 0) {
//			total = Long.valueOf(list.get(0).toString());
			total = 1;
		} else {
			total = list.size();
		}
		return total;
	}

}