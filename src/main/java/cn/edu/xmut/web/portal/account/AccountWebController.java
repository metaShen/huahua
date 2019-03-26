package cn.edu.xmut.web.portal.account;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.utils.PageUtils;
import cn.edu.xmut.utils.bingyi.EasyTools;
import cn.edu.xmut.web.account.AccountController;

@Controller("pAccountWeb")
public class AccountWebController {
	@Resource(name = "accountController")
	private AccountController accountController;

	public ModelMap list(){
		return EasyTools.jsonToModelMap(accountController.list());
	}

    public ModelMap page() {
        Pageable pageable = PageUtils.getPageable();
        JSONObject result = accountController.page(pageable);
        return EasyTools.jsonToModelMap(result); 
    }


}
