package cn.edu.xmut.web.portal.property;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.property.PropertyController;

@Controller("pPropertyWeb")
public class PropertyWebController {
	@Resource(name = "propertyController")
	private PropertyController propertyController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(propertyController.list());
	}
	
    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = propertyController.page(pageable);
        return EasyTools.jsonToModelMap(result);
       
    }

}
