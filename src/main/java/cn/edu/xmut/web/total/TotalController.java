package cn.edu.xmut.web.total;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.total.bean.Total;
import cn.edu.xmut.modules.total.service.impl.TotalServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("totalController")
@RequestMapping("/total")
public class TotalController extends BaseController{
	@Resource(name="totalServiceImpl")
	private TotalServiceImpl totalService;
	
	

	@RequestMapping("/list")
	@ResponseBody
		public  JSONObject list(){
			List<Total> totals = totalService.findAllOrderBy(Total.FieldOfTotal.ID.name()+" ASC");
 			return JsonTool.genSuccessMsg(totals);
		}
		@RequestMapping("/page")
	    public @ResponseBody JSONObject page(Pageable pageable){
			Page<Total> totals = totalService.findPageOrderBy(pageable,Total.FieldOfTotal.ID.name()+" DESC");
			return JsonTool.genSuccessMsg(totals);
		}
		   


	    

		 
}
