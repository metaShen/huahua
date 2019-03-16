package cn.edu.xmut.web.card;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.card.bean.Card;
import cn.edu.xmut.modules.card.service.impl.CardServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("cardController")
@RequestMapping("/card")
public class CardController extends BaseController{
	@Resource(name="cardServiceImpl")
	private CardServiceImpl cardService;

	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Card card){
		if(!beanValidator(card)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			Card iecard = cardService.getByTwoFields(Card.FieldOfCard.USERID.name(), card.getUserid(),Card.FieldOfCard.CARDID.name(), card.getCardid());
			if(iecard != null){
				return JsonTool.genErrorMsg("添加失败！");
			}else{
				cardService.save(card);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
		public JSONObject list(){
			List<Card> cards = cardService.findAllOrderBy(Card.FieldOfCard.ID.name()+" ASC");
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
