package cn.edu.xmut.web.revenue;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.revenue.bean.Revenue;
import cn.edu.xmut.modules.revenue.service.impl.RevenueServiceImpl;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("revenueController")
@RequestMapping("/revenue")
public class RevenueController extends BaseController{
	@Resource(name="revenueServiceImpl")
	private RevenueServiceImpl revenueService;
	
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(Revenue revenue){
		if(!beanValidator(revenue)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			Revenue ierevenue = revenueService.getByOneField(Revenue.FieldOfRevenue.NUMBER.name(), revenue.getNumber());
			if(ierevenue != null){
				return JsonTool.genErrorMsg("添加失败！");
			}else{
				revenueService.save(revenue);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
		public JSONObject list(){
			List<Revenue> revenues = revenueService.findAllOrderBy(Revenue.FieldOfRevenue.ID.name()+" ASC");
			return JsonTool.genSuccessMsg(revenues);
		}
		
		@RequestMapping("/page")
	    public @ResponseBody JSONObject page(Pageable pageable){
			Page<Revenue> revenues = revenueService.findPageOrderBy(pageable,Revenue.FieldOfRevenue.ID.name()+" DESC");
			return JsonTool.genSuccessMsg(revenues);
		}

		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			Revenue ierevenue = revenueService.getByOneField(Revenue.FieldOfRevenue.ID.name(), id);
			if(ierevenue == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				revenueService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		@RequestMapping("/edit")
		public @ResponseBody JSONObject edit(String id){
			Revenue ierevenue = revenueService.getByOneField(Revenue.FieldOfRevenue.ID.name(), id);
			if(ierevenue == null){
				return JsonTool.genErrorMsg("修改失败！");
			}else{
				revenueService.save(ierevenue);
				return JsonTool.genSuccessMsg("修改成功！");
			}
		} 
}
