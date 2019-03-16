package cn.edu.xmut.web.portal.revenue;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.revenue.RevenueController;

@Controller("pRevenueWeb")
public class RevenueWebController {
	@Resource(name = "revenueController")
	private RevenueController revenueController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(revenueController.list());
	}

    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = revenueController.page(pageable);
        return EasyTools.jsonToModelMap(result); 
    }


}
