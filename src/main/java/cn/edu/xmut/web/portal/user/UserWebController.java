package cn.edu.xmut.web.portal.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.user.UserController;

@Controller("pUserWeb")
public class UserWebController {
	@Resource(name = "userController")
	private UserController userController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(userController.list());
	}
	
    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = userController.page(pageable);
        return EasyTools.jsonToModelMap(result);
       
    }

}
