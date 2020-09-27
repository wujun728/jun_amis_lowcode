package cn.com.ttblog.jfinal_bootstrap_table.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {
	private static Logger loginVal= LoggerFactory
			.getLogger(LoginValidator.class);
	protected void validate(Controller c) {
		loginVal.info("校验"+c.getParaMap().toString());
		validateRequired("password", "pwd", "请输入密码");
		validateRequired("username", "username", "请输入用户名");
	}

	protected void handleError(Controller c) {
		loginVal.info("输入有误");
		c.keepPara("username");
		c.render("index.html");
	}
}