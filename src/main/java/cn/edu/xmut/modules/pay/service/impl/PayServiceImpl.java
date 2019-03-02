package  cn.edu.xmut.modules.pay.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.pay.bean.Pay;
import cn.edu.xmut.modules.pay.service.PayService;
import cn.edu.xmut.modules.pay.dao.PayDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("payServiceImpl")
@Transactional
public class PayServiceImpl implements PayService{

	@Autowired
	private PayDao payDao;
	
	@Override
	public int countAll() {
		return (int) payDao.count();
	}
	
	@Override
	public Pay save(Pay pay) {
		return payDao.save(pay);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			payDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		payDao.delete(id);
	}
	
	@Override
	public Pay findById(String id) {
		return payDao.findOne(id);
	}
	
	@Override
	public Page<Pay> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_PAY order by "+orderBy;
		return payDao.findBySql(pageable,sql,Pay.class);
	}
	
	@Override
	public Page<Pay> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PAY where "+fieldName+" = ? order by "+orderBy;
		return payDao.findBySql(pageable,sql,Pay.class,fieldValue);
	}
	
	@Override
	public Page<Pay> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PAY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return payDao.findBySql(pageable,sql,Pay.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Pay> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_PAY where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return payDao.findBySql(pageable,sql,Pay.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Pay getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_PAY where "+fieldName+" = ?";
		return (Pay) payDao.getBySql(sql,Pay.class,fieldValue);
	}
	
	@Override
	public Pay getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_PAY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Pay) payDao.getBySql(sql,Pay.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Pay> findAllOrderBy(String orderBy){
		String sql = "select * from TB_PAY order by "+orderBy;
		return payDao.findBySql(sql,Pay.class);
	}
	
	@Override
	public List<Pay> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_PAY where "+fieldName+" = ? order by "+orderBy;
		return payDao.findBySql(sql,Pay.class,fieldValue);
	}
	
	@Override
	public List<Pay> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_PAY where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return payDao.findBySql(sql,Pay.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_PAY where "+fieldName+" = ?";
		return (int) payDao.countBySql(sql,Pay.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_PAY where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) payDao.countBySql(sql,Pay.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}