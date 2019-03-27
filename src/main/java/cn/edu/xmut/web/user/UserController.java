package cn.edu.xmut.web.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmut.core.persistence.Page;
import cn.edu.xmut.core.persistence.Pageable;
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
				return JsonTool.genSuccessMsg("注册成功!！");
			}
		}
	}
	@RequestMapping("/edit")
	public @ResponseBody JSONObject edit(String username,String password){
			User ieuser = userService.getByOneField(User.FieldOfUser.USERNAME.name(), username);
			if(ieuser == null){
				return JsonTool.genErrorMsg("修改密码失败！");
			}else{
				ieuser.setPassword(password);
				userService.save(ieuser);
				return JsonTool.genSuccessMsg("修改成功！");
			}
	}
	@RequestMapping("/list")
		public  JSONObject list(){
			List<User> users = userService.findAllOrderBy(User.FieldOfUser.ID.name()+" ASC");
 			return JsonTool.genSuccessMsg(users);
		}
		@RequestMapping("/page")
	    public @ResponseBody JSONObject page(Pageable pageable){
			Page<User> users = userService.findPageOrderBy(pageable,User.FieldOfUser.ID.name()+" DESC");
			return JsonTool.genSuccessMsg(users);
		}
		   
	    @RequestMapping("/login")
	    public @ResponseBody JSONObject login(String username,String password){
	    	User user = userService.getByTwoFields(User.FieldOfUser.USERNAME.name(), username, User.FieldOfUser.PASSWORD.name(), password);
	    		if(user != null)
	    		{
	    			UtilCtrl.sessionPut("user",user);
	    			return JsonTool.genSuccessMsg("登录成功");
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
	    
		@RequestMapping("/delete")
		public @ResponseBody JSONObject delete(String id){
			User ieuser = userService.getByOneField(User.FieldOfUser.ID.name(), id);
			if(ieuser == null){
				return JsonTool.genErrorMsg("删除失败！");
			}else{
				userService.deleteById(id);
				return JsonTool.genSuccessMsg("已删除！");
			}
		}
		@RequestMapping("/give")
		public @ResponseBody JSONObject give(String id){
			User ieuser = userService.getByOneField(User.FieldOfUser.ID.name(), id);
			if(ieuser == null){
				return JsonTool.genErrorMsg("赋权失败！");
			}else{
				ieuser.setType(1);
				userService.save(ieuser);
				return JsonTool.genSuccessMsg("已赋权！");
			}
		}
		 
}
