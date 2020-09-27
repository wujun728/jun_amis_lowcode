package cn.com.ttblog.jfinal_bootstrap_table.config;

import cn.com.ttblog.jfinal_bootstrap_table.controller.IndexController;
import cn.com.ttblog.jfinal_bootstrap_table.controller.RegisterController;

import com.jfinal.config.Routes;

/**
 * 前端路由规则
 * @author netbuffer
 */
public class FrontRoutes extends Routes {

	@Override
	public void config() {
		add("/", IndexController.class, "/");
		add("/register", RegisterController.class, "/");
	}

}
