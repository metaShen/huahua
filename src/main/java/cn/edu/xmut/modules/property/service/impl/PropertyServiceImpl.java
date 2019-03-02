package  cn.edu.xmut.modules.property.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.property.bean.Property;
import cn.edu.xmut.modules.property.service.PropertyService;
import cn.edu.xmut.modules.property.dao.PropertyDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("propertyServiceImpl")
@Transactional
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public int countAll() {
		return (int) propertyDao.count();
	}
	
	@Override
	public Property save(Property property) {
		return propertyDao.save(property);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			propertyDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		propertyDao.delete(id);
	}
	
	@Override
	public Property findById(String id) {
		return propertyDao.findOne(id);
	}
	
	@Override
	public Page<Property> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_PROPERTY order by "+orderBy;
		return propertyDao.findBySql(pageable,sql,Property.class);
	}
	
	@Override
	public Page<Property> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PROPERTY where "+fieldName+" = ? order by "+orderBy;
		return propertyDao.findBySql(pageable,sql,Property.class,fieldValue);
	}
	
	@Override
	public Page<Property> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PROPERTY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return propertyDao.findBySql(pageable,sql,Property.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Property> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_PROPERTY where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return propertyDao.findBySql(pageable,sql,Property.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Property getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_PROPERTY where "+fieldName+" = ?";
		return (Property) propertyDao.getBySql(sql,Property.class,fieldValue);
	}
	
	@Override
	public Property getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_PROPERTY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Property) propertyDao.getBySql(sql,Property.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Property> findAllOrderBy(String orderBy){
		String sql = "select * from TB_PROPERTY order by "+orderBy;
		return propertyDao.findBySql(sql,Property.class);
	}
	
	@Override
	public List<Property> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PROPERTY where "+fieldName+" = ? order by "+orderBy;
		return propertyDao.findBySql(sql,Property.class,fieldValue);
	}
	
	@Override
	public List<Property> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PROPERTY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return propertyDao.findBySql(sql,Property.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_PROPERTY where "+fieldName+" = ?";
		return (int) propertyDao.countBySql(sql,Property.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_PROPERTY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) propertyDao.countBySql(sql,Property.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}