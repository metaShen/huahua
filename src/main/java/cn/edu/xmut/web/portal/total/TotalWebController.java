package cn.edu.xmut.web.portal.total;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.total.TotalController;

@Controller("pTotalWeb")
public class TotalWebController {
	@Resource(name = "totalController")
	private TotalController totalController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(totalController.list());
	}
	
    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = totalController.page(pageable);
        return EasyTools.jsonToModelMap(result);
       
    }

}
