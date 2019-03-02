package  cn.edu.xmut.modules.total.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.total.bean.Total;
import cn.edu.xmut.modules.total.service.TotalService;
import cn.edu.xmut.modules.total.dao.TotalDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("totalServiceImpl")
@Transactional
public class TotalServiceImpl implements TotalService{

	@Autowired
	private TotalDao totalDao;
	
	@Override
	public int countAll() {
		return (int) totalDao.count();
	}
	
	@Override
	public Total save(Total total) {
		return totalDao.save(total);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			totalDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		totalDao.delete(id);
	}
	
	@Override
	public Total findById(String id) {
		return totalDao.findOne(id);
	}
	
	@Override
	public Page<Total> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_TOTAL order by "+orderBy;
		return totalDao.findBySql(pageable,sql,Total.class);
	}
	
	@Override
	public Page<Total> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_TOTAL where "+fieldName+" = ? order by "+orderBy;
		return totalDao.findBySql(pageable,sql,Total.class,fieldValue);
	}
	
	@Override
	public Page<Total> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_TOTAL where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return totalDao.findBySql(pageable,sql,Total.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Total> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_TOTAL where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return totalDao.findBySql(pageable,sql,Total.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Total getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_TOTAL where "+fieldName+" = ?";
		return (Total) totalDao.getBySql(sql,Total.class,fieldValue);
	}
	
	@Override
	public Total getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_TOTAL where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Total) totalDao.getBySql(sql,Total.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Total> findAllOrderBy(String orderBy){
		String sql = "select * from TB_TOTAL order by "+orderBy;
		return totalDao.findBySql(sql,Total.class);
	}
	
	@Override
	public List<Total> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_TOTAL where "+fieldName+" = ? order by "+orderBy;
		return totalDao.findBySql(sql,Total.class,fieldValue);
	}
	
	@Override
	public List<Total> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_TOTAL where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return totalDao.findBySql(sql,Total.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_TOTAL where "+fieldName+" = ?";
		return (int) totalDao.countBySql(sql,Total.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_TOTAL where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) totalDao.countBySql(sql,Total.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}