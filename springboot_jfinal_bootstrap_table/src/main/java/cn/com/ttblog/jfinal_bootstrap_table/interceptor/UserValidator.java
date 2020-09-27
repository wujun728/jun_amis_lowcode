package cn.com.ttblog.jfinal_bootstrap_table.interceptor;

import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.com.ttblog.jfinal_bootstrap_table.model.User;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserValidator extends Validator {
	private static Logger userVal= LoggerFactory
			.getLogger(UserValidator.class);
	protected void validate(Controller c) {
//		userVal.info("in:"+ToStringBuilder.reflectionToString(c));
		userVal.info("校验user.name...");
		validateRequiredString("user.name", "username", "请输入昵称");
		userVal.info("校验user.age...");
		validateInteger("user.age", 10, 150,  "age", "请输入年龄");
		userVal.info("校验user.phone...");
		validateRequiredString("user.phone", "phone", "请输入手机号");
		Enumeration<String> attrs=c.getAttrNames();
		StringBuilder sb=new StringBuilder();
		while (attrs.hasMoreElements()) {
			String attr = (String)attrs.nextElement();
			sb.append(attr).append(" ");
		}
		userVal.info("校验后..."+sb.toString());
	}

	protected void handleError(Controller c) {
		c.keepModel(User.class);
		userVal.info("actionkey:"+getActionKey());
//		userVal.info("out:"+ToStringBuilder.reflectionToString(c));
		c.redirect("/register.html");
	}
}