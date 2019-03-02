package cn.edu.xmut.core.template;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//import cn.edu.xmut.modules.admin.user.bean.Permission;
import cn.edu.xmut.utils.UtilCtrl;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class PermissionDirective implements TemplateDirectiveModel {
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String value = params.get("value").toString();
		if(value==null){
			throw new RuntimeException("@permission指令需要value属性");
		}	
		boolean blFlag = false;
		/*List<Permission> lstPermission = UtilCtrl.sessionGet("lstPermission");
		if(lstPermission==null || lstPermission.size()<=0){
			return;
		}
		String[] aryValue = value.split(",");
		for(String p : aryValue){
			if(lstPermission.indexOf(p)>=0){
				blFlag = true;
				break;
			}
		}
		if(blFlag){
			body.render(env.getOut());
		}*/
		
	}
}