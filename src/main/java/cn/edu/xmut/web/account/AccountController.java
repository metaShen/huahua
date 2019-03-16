package cn.edu.xmut.web.account;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.account.bean.Account;
import cn.edu.xmut.modules.account.service.impl.AccountServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("accountController")
@RequestMapping("/account")
public class AccountController extends BaseController{
	@Resource(name="accountServiceImpl")
	private AccountServiceImpl accountService;
	
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Account account){
		if(!beanValidator(account)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			Account ieaccount = accountService.getByTwoFields(Account.FieldOfAccount.ID.name(), account.getId(),Account.FieldOfAccount.BANKNUMBER.name(), account.getBanknumber());
			if(ieaccount != null){
				return JsonTool.genErrorMsg("添加失败！");
			}else{
				accountService.save(account);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
	@RequestMapping("/edit")
	public @ResponseBody JSONObject edit(String id){
			Account account = accountService.getByOneField(Account.FieldOfAccount.ID.name(), id);
			if(account == null){
				return JsonTool.genErrorMsg("修改失败！");
			}else{
				accountService.save(account);
				return JsonTool.genSuccessMsg("修改成功！");
			}
	}

		public JSONObject list(){
			List<Account> accounts = accountService.findAllOrderBy(Account.FieldOfAccount.ID.name()+" ASC");
			return JsonTool.genSuccessMsg(accounts);
		}

		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			Account ieaccount = accountService.getByOneField(Account.FieldOfAccount.ID.name(), id);
			if(ieaccount == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				accountService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		 
}
