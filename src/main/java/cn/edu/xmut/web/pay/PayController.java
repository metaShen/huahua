package cn.edu.xmut.web.pay;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.pay.bean.Pay;
import cn.edu.xmut.modules.pay.service.impl.PayServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("payController")
@RequestMapping("/pay")
public class PayController extends BaseController{
	@Resource(name="payServiceImpl")
	private PayServiceImpl payService;
	
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Pay pay){
		if(!beanValidator(pay)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			Pay iepay = payService.getByTwoFields(Pay.FieldOfPay.ID.name(), pay.getId(),Pay.FieldOfPay.NUMBER.name(), pay.getNumber());
			if(iepay != null){
				return JsonTool.genErrorMsg("添加失败！");
			}else{
				payService.save(pay);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
	@RequestMapping("/edit")
	public @ResponseBody JSONObject edit(String id){
			Pay pay = payService.getByOneField(Pay.FieldOfPay.ID.name(), id);
			if(pay == null){
				return JsonTool.genErrorMsg("修改失败！");
			}else{
				payService.save(pay);
				return JsonTool.genSuccessMsg("修改成功！");
			}
	}

		public JSONObject list(){
			List<Pay> pays = payService.findAllOrderBy(Pay.FieldOfPay.ID.name()+" ASC");
			return JsonTool.genSuccessMsg(pays);
		}

		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			Pay iepay = payService.getByOneField(Pay.FieldOfPay.ID.name(), id);
			if(iepay == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				payService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		 
}
