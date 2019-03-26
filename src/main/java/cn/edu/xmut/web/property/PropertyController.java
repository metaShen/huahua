package cn.edu.xmut.web.property;

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
import cn.edu.xmut.modules.property.bean.Property;
import cn.edu.xmut.modules.property.service.impl.PropertyServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("propertyController")
@RequestMapping("/property")
public class PropertyController extends BaseController{
	@Resource(name="propertyServiceImpl")
	private PropertyServiceImpl propertyService;
	@Resource(name="accountServiceImpl")
	private AccountServiceImpl accountService ;
	
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Property property){
		if(!beanValidator(property)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			Property ieproperty = propertyService.getByOneField(Property.FieldOfProperty.ID.name(), property.getId());
			if(ieproperty != null){
				return JsonTool.genErrorMsg("添加提醒失败！");
			}else{
				propertyService.save(property);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
	@RequestMapping("/list")
		public  JSONObject list(){
			List<Property> propertys = propertyService.findAllOrderBy(Property.FieldOfProperty.ID.name()+" ASC");
 			return JsonTool.genSuccessMsg(propertys);
		}
		@RequestMapping("/page")
	    public @ResponseBody JSONObject page(Pageable pageable){
			Page<Property> propertys = propertyService.findPageOrderBy(pageable,Property.FieldOfProperty.ID.name()+" DESC");
			return JsonTool.genSuccessMsg(propertys);
		}
		   
	    
		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			Property ieproperty = propertyService.getByOneField(Property.FieldOfProperty.ID.name(), id);
			if(ieproperty == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				propertyService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		@RequestMapping("/pay")
		public @ResponseBody JSONObject pay(String id,String price,String number){
			Account ieAccount = accountService.getByOneField(Account.FieldOfAccount.BANKNUMBER.name(), number);
			Property ieproperty = propertyService.getByOneField(Property.FieldOfProperty.ID.name(), id);
			Property property =propertyService.getByOneField(Property.FieldOfProperty.PRICE.name(), price);
			if(property == null || ieproperty == null || ieAccount == null){
				return JsonTool.genErrorMsg("缴费失败！");
			}else if (ieAccount.getBalance() < property.getPrice()) {
				return JsonTool.genErrorMsg("缴费失败,余额不足！");
			}{
				ieAccount.setBalance(ieAccount.getBalance()-property.getPrice());
				accountService.save(ieAccount);
				propertyService.deleteById(id);
				return JsonTool.genSuccessMsg("缴费成功！");
			}
		}
		
		@RequestMapping("/clock")
		public @ResponseBody JSONObject clock(String id,String day,String newdate){
			Property ieproperty = propertyService.getByOneField(Property.FieldOfProperty.ID.name(), id);
			if(ieproperty == null){
				return JsonTool.genErrorMsg("添加失败！");
			}else if (day.compareTo(newdate) < 0 ||day.compareTo(ieproperty.getDay()) > 0) {
				return JsonTool.genErrorMsg("提醒日期不能大于截止日期且不能小于当前日期！");
			}{
				ieproperty.setTime(day);
				propertyService.save(ieproperty);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
		@RequestMapping("/send")
		public @ResponseBody JSONObject send(String newdate){
			Property ieproperty = propertyService.getByOneField(Property.FieldOfProperty.TIME.name(),newdate);
			 if (newdate.compareTo(ieproperty.getTime()) == 0)
			 {
				return JsonTool.genSuccessMsg("您有新的缴费信息！");
			}
			 else
			 {
				return JsonTool.genErrorMsg("");
			}
		}
		 
}
