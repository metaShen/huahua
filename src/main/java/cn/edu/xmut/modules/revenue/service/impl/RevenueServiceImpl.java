package  cn.edu.xmut.modules.revenue.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.revenue.bean.Revenue;
import cn.edu.xmut.modules.revenue.service.RevenueService;
import cn.edu.xmut.modules.revenue.dao.RevenueDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("revenueServiceImpl")
@Transactional
public class RevenueServiceImpl implements RevenueService{

	@Autowired
	private RevenueDao revenueDao;
	
	@Override
	public int countAll() {
		return (int) revenueDao.count();
	}
	
	@Override
	public Revenue save(Revenue revenue) {
		return revenueDao.save(revenue);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			revenueDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		revenueDao.delete(id);
	}
	
	@Override
	public Revenue findById(String id) {
		return revenueDao.findOne(id);
	}
	
	@Override
	public Page<Revenue> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_REVENUE order by "+orderBy;
		return revenueDao.findBySql(pageable,sql,Revenue.class);
	}
	
	@Override
	public Page<Revenue> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_REVENUE where "+fieldName+" = ? order by "+orderBy;
		return revenueDao.findBySql(pageable,sql,Revenue.class,fieldValue);
	}
	
	@Override
	public Page<Revenue> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_REVENUE where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return revenueDao.findBySql(pageable,sql,Revenue.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Revenue> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_REVENUE where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return revenueDao.findBySql(pageable,sql,Revenue.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Revenue getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_REVENUE where "+fieldName+" = ?";
		return (Revenue) revenueDao.getBySql(sql,Revenue.class,fieldValue);
	}
	
	@Override
	public Revenue getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_REVENUE where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Revenue) revenueDao.getBySql(sql,Revenue.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Revenue> findAllOrderBy(String orderBy){
		String sql = "select * from TB_REVENUE order by "+orderBy;
		return revenueDao.findBySql(sql,Revenue.class);
	}
	
	@Override
	public List<Revenue> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_REVENUE where "+fieldName+" = ? order by "+orderBy;
		return revenueDao.findBySql(sql,Revenue.class,fieldValue);
	}
	
	@Override
	public List<Revenue> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_REVENUE where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return revenueDao.findBySql(sql,Revenue.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_REVENUE where "+fieldName+" = ?";
		return (int) revenueDao.countBySql(sql,Revenue.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_REVENUE where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) revenueDao.countBySql(sql,Revenue.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}