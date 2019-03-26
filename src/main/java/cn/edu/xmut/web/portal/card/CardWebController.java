package cn.edu.xmut.web.portal.card;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.card.CardController;

@Controller("pCardWeb")
public class CardWebController {
	@Resource(name = "cardController")
	private CardController cardController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(cardController.list());
	}

    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = cardController.page(pageable);
        return EasyTools.jsonToModelMap(result); 
    }


}
