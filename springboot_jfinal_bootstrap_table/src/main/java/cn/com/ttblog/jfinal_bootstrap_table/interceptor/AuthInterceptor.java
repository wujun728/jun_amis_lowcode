package cn.com.ttblog.jfinal_bootstrap_table.interceptor;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ttblog.jfinal_bootstrap_table.Constant.ConfigConstant;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 认证拦截器
 * @author Wujun
 */
public class AuthInterceptor implements Interceptor {
	private static Logger authlogger=LoggerFactory.getLogger(AuthInterceptor.class);
	@Override
	public void intercept(Invocation inv) {
		authlogger.info("Before method invoking");
		Object login=inv.getController().getSession().getAttribute(ConfigConstant.ISLOGIN);
		authlogger.info("invoking:"+inv.getControllerKey()+"--"+inv.getMethodName()+"--"+inv.getViewPath()+"--"+ToStringBuilder.reflectionToString(inv.getArgs()));
		//未登录跳转
		if(inv.getMethodName().equals("login")||inv.getMethodName().equals("captcha")||inv.getControllerKey().contains("register")
				||(null!=login&&Boolean.parseBoolean(login.toString()))){
//			//传递本次调用，调用剩下的拦截器与目标方法 
			inv.invoke();
		}else{
			inv.getController().renderJavascript("<script type='text/javascript'>alert('您还未登录,不能执行此操作!');</script>");
		}
		authlogger.info("After method invoking");
	}

}
