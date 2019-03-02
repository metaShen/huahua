/**
 * 
 */
package cn.edu.xmut.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.ApplicationContext;

//import cn.edu.xmut.modules.admin.user.bean.User;


//import cn.edu.xmut.modules.user.bean.User;

/**
 * @author yangzj
 * @version 2014-8-12 下午2:25:01
 */
public class ValidateUtils<T> {
	
	private static Validator validator;
	
	public static void  setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static  Object beanValidate() {
		ApplicationContext context = null;
//		context.getBean(c);
		setUp();
		//User  user = null ;
		//Set violations = validator.validate(user);
		return null;
	}
}
