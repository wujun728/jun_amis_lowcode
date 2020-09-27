package cn.com.ttblog.jfinal_bootstrap_table.config;

import cn.com.ttblog.jfinal_bootstrap_table.controller.CacheController;
import cn.com.ttblog.jfinal_bootstrap_table.controller.UserController;

import com.jfinal.config.Routes;

/**
 * 后端路由规则
 * @author champ
 */
public class AdminRoutes extends Routes {

	@Override
	public void config() {
		add("/user",UserController.class,"/");
		add("/cache",CacheController.class,"/");
	}

}
