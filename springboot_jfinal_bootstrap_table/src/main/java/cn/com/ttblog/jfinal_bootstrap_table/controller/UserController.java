package cn.com.ttblog.jfinal_bootstrap_table.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.ttblog.jfinal_bootstrap_table.interceptor.TimeInterceptor;
import cn.com.ttblog.jfinal_bootstrap_table.model.User;
import cn.com.ttblog.jfinal_bootstrap_table.service.IUserService;
import cn.com.ttblog.jfinal_bootstrap_table.service.impl.UserServiceImpl;

import com.jfinal.aop.Before;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheInterceptor;

/**
 * user控制器
 * @author netbuffer
 *
 */
@Before({ TimeInterceptor.class })
public class UserController extends Controller {
	private static IUserService userservice=Enhancer.enhance(UserServiceImpl.class);
	private static Logger userLog= LoggerFactory
			.getLogger(UserController.class);

	public void index() {
		render("index.html");
	}
	
	/**
	 * 开启缓存
	 */
	@Before({CacheInterceptor.class})
	public void userlist() {
		int limit=getParaToInt("limit");
		int offset=getParaToInt("offset");
		Map<String, Object> data=userservice.getUserList(offset, limit);
		renderJson(data);
	}
	public void delete(){
		User.dao.deleteById(getPara("id"));
		setAttr("status", "success");
		renderJson();
	}
}
