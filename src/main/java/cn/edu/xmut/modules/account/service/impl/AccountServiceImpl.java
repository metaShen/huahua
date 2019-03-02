package  cn.edu.xmut.modules.account.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.account.bean.Account;
import cn.edu.xmut.modules.account.service.AccountService;
import cn.edu.xmut.modules.account.dao.AccountDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("accountServiceImpl")
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public int countAll() {
		return (int) accountDao.count();
	}
	
	@Override
	public Account save(Account account) {
		return accountDao.save(account);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			accountDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		accountDao.delete(id);
	}
	
	@Override
	public Account findById(String id) {
		return accountDao.findOne(id);
	}
	
	@Override
	public Page<Account> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_ACCOUNT order by "+orderBy;
		return accountDao.findBySql(pageable,sql,Account.class);
	}
	
	@Override
	public Page<Account> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_ACCOUNT where "+fieldName+" = ? order by "+orderBy;
		return accountDao.findBySql(pageable,sql,Account.class,fieldValue);
	}
	
	@Override
	public Page<Account> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_ACCOUNT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return accountDao.findBySql(pageable,sql,Account.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Account> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_ACCOUNT where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return accountDao.findBySql(pageable,sql,Account.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Account getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_ACCOUNT where "+fieldName+" = ?";
		return (Account) accountDao.getBySql(sql,Account.class,fieldValue);
	}
	
	@Override
	public Account getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_ACCOUNT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Account) accountDao.getBySql(sql,Account.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Account> findAllOrderBy(String orderBy){
		String sql = "select * from TB_ACCOUNT order by "+orderBy;
		return accountDao.findBySql(sql,Account.class);
	}
	
	@Override
	public List<Account> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_ACCOUNT where "+fieldName+" = ? order by "+orderBy;
		return accountDao.findBySql(sql,Account.class,fieldValue);
	}
	
	@Override
	public List<Account> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_ACCOUNT where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return accountDao.findBySql(sql,Account.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_ACCOUNT where "+fieldName+" = ?";
		return (int) accountDao.countBySql(sql,Account.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_ACCOUNT where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) accountDao.countBySql(sql,Account.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}