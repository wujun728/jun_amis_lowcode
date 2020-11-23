package cn.com.ttblog.jfinal_bootstrap_table.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 时间打印
 * @author Wujun
 */
public class TimeInterceptor implements Interceptor {
	private static Logger timeInterceptor= LoggerFactory
			.getLogger(TimeInterceptor.class);
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ssSSS");
	private SimpleDateFormat format2 = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	
	@Override
	public void intercept(Invocation inv) {
		timeInterceptor.info("time begin:" + format.format(new Date()));
//		timeInterceptor.info("time inceptor-controller-getpara():"+inv.getController().getPara());
		inv.invoke();
		timeInterceptor.info("time end:" + format.format(new Date()));
	}

}
