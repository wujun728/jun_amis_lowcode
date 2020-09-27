package cn.com.ttblog.jfinal_bootstrap_table.controller;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ttblog.jfinal_bootstrap_table.interceptor.UserValidator;
import cn.com.ttblog.jfinal_bootstrap_table.model.User;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * register控制器
 * @author netbuffer
 *
 */
public class RegisterController extends Controller {
	private static Logger regLog = LoggerFactory
			.getLogger(RegisterController.class);

	public void index() {
		redirect("/register.html");
	}
	@Before({UserValidator.class})
	public void save() {
		User u=getModel(User.class);
		u.setAdddate((int)(System.currentTimeMillis()/1000));
		regLog.info("user:"+ToStringBuilder.reflectionToString(u));
		try {
			if(u.save()){
				redirect("/register-success.html");
			}else{
				redirect("/register-error.html");
			}
		} catch (Exception e) {
			regLog.error("保存用户信息发生错误:"+e.getMessage()+",原因:"+e.getCause().getMessage());
			redirect("/register-error.html");
		}
	}
	
	public void captcha() {
		renderCaptcha();
	}
}
