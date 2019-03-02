package cn.edu.xmut.web.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.web.BaseController;
import cn.edu.xmut.modules.user.bean.User;
import cn.edu.xmut.modules.user.service.impl.UserServiceImpl;
import cn.edu.xmut.utils.JsonTool;
import cn.edu.xmut.utils.UtilCtrl;

@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource(name="userServiceImpl")
	private UserServiceImpl userService;
	
	@RequestMapping("/add")
	public @ResponseBody JSONObject add(User user){
		if(!beanValidator(user)){
			return JsonTool.genErrorMsg("添加失败！");
		}else{
			User ieuser = userService.getByTwoFields(User.FieldOfUser.USERNAME.name(), user.getUsername(), User.FieldOfUser.PASSWORD.name(), user.getPassword());
			if(ieuser != null){
				return JsonTool.genErrorMsg("该用户已经存在！");
			}else{
				userService.save(user);
				return JsonTool.genSuccessMsg("添加成功！");
			}
		}
	}
	
	@RequestMapping("/register")
	public @ResponseBody JSONObject register(User user){
		if(!beanValidator(user)){
			return JsonTool.genErrorMsg("注册失败！");
		}else{
			
			User ieuser = userService.getByOneField(User.FieldOfUser.ID.name(), user.getId());
			if(ieuser != null){
				return JsonTool.genErrorMsg("该用户已经存在!");
			}else{
				userService.save(user);
				return JsonTool.genSuccessMsg("恭喜注册成功!！");
			}
		}
	}
	@RequestMapping("/modify")
	public @ResponseBody JSONObject modify(User user){
		if(!beanValidator(user)){
			return JsonTool.genErrorMsg("修改失败！");
		}else{
			User ieuser = userService.getByOneField(User.FieldOfUser.ID.name(), user.getId());
			if(ieuser == null){
				return JsonTool.genErrorMsg("该用户不存在！");
			}else{

				userService.save(user);
				return JsonTool.genSuccessMsg("修改成功！");
			}
		}
	}
	/*   @RequestMapping("/proving") 
	    public @ResponseBody JSONObject proving(User user){
	        User ieuser = userService.getByTwoFields(User.FieldOfUser.PASSWORD.name(),user.getPassword(),User.FieldOfUser.IDM.name(),user.getIdm());
	        if(ieuser == null){
	            return JsonTool.genErrorMsg("该用户不存在或密码错误！");//如果用户密码不存在，报错
	        }else{
	             if(user.getIdm() ==ieuser.getIdm()&user.getPhone()==ieuser.getPhone())
	             {
	            	 return JsonTool.genSuccessMsg("用户验证成功！");//返回验证成功 
	             }
	             return JsonTool.genErrorMsg("用户验证失败！");//返回验证成功
	        }
	    }	*/
		public JSONObject list(){
			//List<User> users = userService.findByOneFieldOrderBy("", "", User.FieldOfUser.NAME.name()+" ASC");
			List<User> users = userService.findAllOrderBy(User.FieldOfUser.ID.name()+" ASC");
			return JsonTool.genSuccessMsg(users);
		}
	    @RequestMapping("/login")
	    public @ResponseBody JSONObject login(String username,String password){
	    	User user = userService.getByTwoFields(User.FieldOfUser.USERNAME.name(), username, User.FieldOfUser.PASSWORD.name(), password);
	    		if(user != null)
	    		{
	    			UtilCtrl.sessionPut("user",user);
	    			return JsonTool.genSuccessMsg("user.jhtml");
	    		}
	    		else {
	    			return JsonTool.genErrorMsg("用户验证失败！");
	    		}
	    			}
	    @RequestMapping("/logout")
	    public @ResponseBody JSONObject logout(){
		  UtilCtrl.sessionRemove("user");
		  return JsonTool.genSuccessMsg("退出成功！");
		 }
		 
}
