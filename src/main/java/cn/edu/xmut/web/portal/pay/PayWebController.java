package cn.edu.xmut.web.portal.pay;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.pay.PayController;

@Controller("pPayWeb")
public class PayWebController {
	@Resource(name = "payController")
	private PayController payController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(payController.list());
	}

    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = payController.page(pageable);
        return EasyTools.jsonToModelMap(result); 
    }


}
