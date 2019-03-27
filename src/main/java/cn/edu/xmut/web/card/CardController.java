package cn.edu.xmut.web.card;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.account.bean.Account;
import cn.edu.xmut.modules.account.service.impl.AccountServiceImpl;
import cn.edu.xmut.modules.card.bean.Card;
import cn.edu.xmut.modules.card.service.impl.CardServiceImpl;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.impl.UserServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("cardController")
@RequestMapping("/card")
public class CardController extends BaseController{
	@Resource(name="cardServiceImpl")
	private CardServiceImpl cardService;
	@Resource(name="userServiceImpl")
	private UserServiceImpl userService;
	@Resource(name="accountServiceImpl")
	private AccountServiceImpl accountService;

	@RequestMapping("/bind")
	public @ResponseBody JSONObject bind(String userid,String accountid){
			Card iecard = new Card();
			User ieUser = userService.getByOneField(User.FieldOfUser.NAME.name(), userid);
			Account ieAccount = accountService.getByOneField(Account.FieldOfAccount.BANKNUMBER.name(), accountid);
			if( ieAccount ==null || ieUser == null){
				return JsonTool.genErrorMsg("绑定失败,卡号不正确！");
			}
			else{
				iecard.setAccountid(ieAccount.getBanknumber());
				iecard.setPrice(ieAccount.getBalance());
				iecard.setUserid(ieUser.getName());
				cardService.save(iecard);
				return JsonTool.genSuccessMsg("绑定成功！");
			}
		}
	
		public JSONObject list(){
			List<Card> cards = cardService.findAllOrderBy(Card.FieldOfCard.ID.name()+" ASC");
			return JsonTool.genSuccessMsg(cards);
		}
		@RequestMapping("/page")
	    public @ResponseBody JSONObject page(Pageable pageable){
			Page<Card> cards = cardService.findPageOrderBy(pageable,Card.FieldOfCard.ID.name()+" DESC");
			return JsonTool.genSuccessMsg(cards);
		}
		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			Card iecard = cardService.getByOneField(Card.FieldOfCard.ID.name(), id);
			if(iecard == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				cardService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		 
}
