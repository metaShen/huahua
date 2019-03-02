/**
 * 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package cn.edu.xmut.core.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.edu.xmut.core.beanvalidator.BeanValidators;
import cn.edu.xmut.core.tempftl.directive.FlashMessageDirective;
import cn.edu.xmut.core.utils.DateUtils;
import cn.edu.xmut.utils.Constants;
import cn.edu.xmut.utils.SpringUtils;

/**
 * 控制器支持类
 * @author lilin
 * @version 2013-3-23
 */
public abstract class BaseController {
//	/** 错误视图 */
//	protected static final String ERROR_VIEW = "/admin/common/error";
//
//	/** 错误消息 */
//	protected static final Message ERROR_MESSAGE = Message.error("admin.message.error");
//
//	/** 成功消息 */
//	protected static final Message SUCCESS_MESSAGE = Message.success("admin.message.success");
//
//	/** "验证结果"参数名称 */
//	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;
	
	private static List<String> list = new ArrayList<String>();

	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[] {}));
			return false;
		}
		return true;
	}

	/**
	 * 实体验证返回信息, 没有传入Model
	 * @param object
	 * @param groups
	 * @return
	 */
	protected boolean beanValidator(Object object, Class<?>... groups) {
		try {
			BeanValidators.validateWithException(validator, object, groups);
		} catch (ConstraintViolationException ex) {
			list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			return false;
		}
		return true;
	}
	
	protected String message(Object object, Class<?>... groups) {
		if(list.size() != 0 && list != null) {
//			int index = list.get(0).indexOf(":") + 1 ;
			return list.get(0);
		} else {
			return "验证成功";
		}
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		model.addAttribute("message", sb.toString());
		//		redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME,
		//				Message.warn(sb.toString()));
	}

	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages) {
			sb.append(message).append(messages.length > 1 ? "<br/>" : "");
		}
		redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME,
				Message.success(sb.toString()));
	}

	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message) {

		redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
	}

	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		BaseController.list = list;
	}
	
	
}
