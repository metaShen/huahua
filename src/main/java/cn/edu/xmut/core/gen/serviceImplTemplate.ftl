package  ${defaultTemplate.packagePath};

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import ${defaultTemplate.modelPath};
import ${defaultTemplate.servicePath};
import ${defaultTemplate.daoPath};

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("${defaultTemplate.entityViable}ServiceImpl")
@Transactional
public class ${defaultTemplate.serviceImplName} implements ${defaultTemplate.serviceName}{

	@Autowired
	private ${defaultTemplate.daoName} ${defaultTemplate.daoEntity};
	
	@Override
	public int countAll() {
		return (int) ${defaultTemplate.daoEntity}.count();
	}
	
	@Override
	public ${defaultTemplate.entityName} save(${defaultTemplate.entityName} ${defaultTemplate.entityViable}) {
		return ${defaultTemplate.daoEntity}.save(${defaultTemplate.entityViable});
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			${defaultTemplate.daoEntity}.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		${defaultTemplate.daoEntity}.delete(id);
	}
	
	@Override
	public ${defaultTemplate.entityName} findById(String id) {
		return ${defaultTemplate.daoEntity}.findOne(id);
	}
	
	@Override
	public Page<${defaultTemplate.entityName}> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from ${defaultTemplate.tableName} order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(pageable,sql,${defaultTemplate.entityName}.class);
	}
	
	@Override
	public Page<${defaultTemplate.entityName}> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName+" = ? order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(pageable,sql,${defaultTemplate.entityName}.class,fieldValue);
	}
	
	@Override
	public Page<${defaultTemplate.entityName}> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(pageable,sql,${defaultTemplate.entityName}.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<${defaultTemplate.entityName}> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(pageable,sql,${defaultTemplate.entityName}.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public ${defaultTemplate.entityName} getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName+" = ?";
		return (${defaultTemplate.entityName}) ${defaultTemplate.daoEntity}.getBySql(sql,${defaultTemplate.entityName}.class,fieldValue);
	}
	
	@Override
	public ${defaultTemplate.entityName} getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (${defaultTemplate.entityName}) ${defaultTemplate.daoEntity}.getBySql(sql,${defaultTemplate.entityName}.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<${defaultTemplate.entityName}> findAllOrderBy(String orderBy){
		String sql = "select * from ${defaultTemplate.tableName} order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(sql,${defaultTemplate.entityName}.class);
	}
	
	@Override
	public List<${defaultTemplate.entityName}> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName+" = ? order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(sql,${defaultTemplate.entityName}.class,fieldValue);
	}
	
	@Override
	public List<${defaultTemplate.entityName}> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from ${defaultTemplate.tableName} where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return ${defaultTemplate.daoEntity}.findBySql(sql,${defaultTemplate.entityName}.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from ${defaultTemplate.tableName} where "+fieldName+" = ?";
		return (int) ${defaultTemplate.daoEntity}.countBySql(sql,${defaultTemplate.entityName}.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from ${defaultTemplate.tableName} where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) ${defaultTemplate.daoEntity}.countBySql(sql,${defaultTemplate.entityName}.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}