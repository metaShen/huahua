package  cn.edu.xmut.modules.card.service.impl;

import java.util.List;
import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import cn.edu.xmut.modules.card.bean.Card;
import cn.edu.xmut.modules.card.service.CardService;
import cn.edu.xmut.modules.card.dao.CardDao;

/**
 * @author yangzj
 * @version v1.0.0   
 * @since v1.0.0 
 * @date $date
 */
@Service("cardServiceImpl")
@Transactional
public class CardServiceImpl implements CardService{

	@Autowired
	private CardDao cardDao;
	
	@Override
	public int countAll() {
		return (int) cardDao.count();
	}
	
	@Override
	public Card save(Card card) {
		return cardDao.save(card);
	}
		
	@Override
	public void deleteById(String[] ids) {
		for (String id : ids) {
			cardDao.delete(id);
		}
	}
	
	@Override
	public void deleteById(String id) {
		cardDao.delete(id);
	}
	
	@Override
	public Card findById(String id) {
		return cardDao.findOne(id);
	}
	
	@Override
	public Page<Card> findPageOrderBy(Pageable pageable, String orderBy) {
		String sql = "select * from TB_CARD order by "+orderBy;
		return cardDao.findBySql(pageable,sql,Card.class);
	}
	
	@Override
	public Page<Card> findPageByOneFieldOrderBy(Pageable pageable, String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_CARD where "+fieldName+" = ? order by "+orderBy;
		return cardDao.findBySql(pageable,sql,Card.class,fieldValue);
	}
	
	@Override
	public Page<Card> findPageByTwoFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_CARD where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return cardDao.findBySql(pageable,sql,Card.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public Page<Card> findPageByThreeFieldsOrderBy(Pageable pageable, String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String fieldName3, Object fieldValue3, String orderBy) {
		String sql = "select * from TB_CARD where "+fieldName1+" = ? and "+fieldName2+" = ? and "+fieldName3+" = ? order by "+orderBy;
		return cardDao.findBySql(pageable,sql,Card.class,fieldValue1,fieldValue2, fieldValue3);
	}
	
	@Override
	public Card getByOneField(String fieldName, Object fieldValue) {
		String sql = "select * from TB_CARD where "+fieldName+" = ?";
		return (Card) cardDao.getBySql(sql,Card.class,fieldValue);
	}
	
	@Override
	public Card getByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select * from TB_CARD where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (Card) cardDao.getBySql(sql,Card.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public List<Card> findAllOrderBy(String orderBy){
		String sql = "select * from TB_CARD order by "+orderBy;
		return cardDao.findBySql(sql,Card.class);
	}
	
	@Override
	public List<Card> findByOneFieldOrderBy(String fieldName, Object fieldValue, String orderBy){
		String sql = "select * from TB_CARD where "+fieldName+" = ? order by "+orderBy;
		return cardDao.findBySql(sql,Card.class,fieldValue);
	}
	
	@Override
	public List<Card> findByTwoFieldsOrderBy(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2, String orderBy){
		String sql = "select * from TB_CARD where "+fieldName1+" = ? and "+fieldName2+" = ? order by "+orderBy;
		return cardDao.findBySql(sql,Card.class,fieldValue1,fieldValue2);
	}
	
	@Override
	public int countByOneField(String fieldName, Object fieldValue) {
		String sql = "select count(*) as count from TB_CARD where "+fieldName+" = ?";
		return (int) cardDao.countBySql(sql,Card.class,fieldValue);
	}
	
	@Override
	public int countByTwoFields(String fieldName1, Object fieldValue1, String fieldName2, Object fieldValue2){
		String sql = "select count(*) as count from TB_CARD where "+fieldName1+" = ? and "+fieldName2+" = ?";
		return (int) cardDao.countBySql(sql,Card.class,fieldValue1,fieldValue2);
	}
	// 用户自定义开始，请不要修改以上内容
	
	
	// 用户自定义结束，请不要修改以下内容
 	
	
}