package  cn.edu.xmut.modules.user.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.UserService;
import cn.edu.xmut.modules.user.dao.UserDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public int countAll() {
		return (int) userDao.count();
	}
	
	@Override
	public User save(User user) {
		return userDao.save(user);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			userDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		userDao.delete(id);
	}
	
	@Override
	public User findById(String id) {
		return userDao.findOne(id);
	}
	
	@Override
	public Page<User> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_USER order by "+orderBy;
		return userDao.findBySql(pageable,sql,User.class);
	}
	
	@Override
	public Page<User> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER where "+fieldName+" = ? order by "+orderBy;
		return userDao.findBySql(pageable,sql,User.class,fieldValue);
	}
	
	@Override
	public Page<User> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userDao.findBySql(pageable,sql,User.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<User> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_USER where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return userDao.findBySql(pageable,sql,User.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public User getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_USER where "+fieldName+" = ?";
		return (User) userDao.getBySql(sql,User.class,fieldValue);
	}
	
	@Override
	public User getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_USER where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (User) userDao.getBySql(sql,User.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<User> findAllOrderBy(String orderBy){
		String sql = "select * from TB_USER order by "+orderBy;
		return userDao.findBySql(sql,User.class);
	}
	
	@Override
	public List<User> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_USER where "+fieldName+" = ? order by "+orderBy;
		return userDao.findBySql(sql,User.class,fieldValue);
	}
	
	@Override
	public List<User> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_USER where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return userDao.findBySql(sql,User.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_USER where "+fieldName+" = ?";
		return (int) userDao.countBySql(sql,User.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_USER where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) userDao.countBySql(sql,User.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}