package cn.com.ttblog.jfinal_bootstrap_table.config;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import cn.com.ttblog.jfinal_bootstrap_table.interceptor.AuthInterceptor;
import cn.com.ttblog.jfinal_bootstrap_table.model._MappingKit;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.ModelRecordElResolver;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.druid.IDruidStatViewAuth;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

/**
 * jfinal配置文件
 * 
 * @author champ
 *
 */
public class AppConfig extends JFinalConfig {

	/**
	 * 启动完回调
	 */
	public void afterJFinalStart() {
		/**
		 * 添加setResolveBeanAsModel(boolean)
		 * ，设置为true时，用于指定在JSP/jstl中，对待合体后的Bean仍然采用老版本对待Model的方式输出数据，也即使用
		 * Model.get(String attr)而非Bean的getter方法输出数据，有利于在关联查询时输出无 getter
		 * 方法的字段值。建议mysql数据表中的字段采用驼峰命名，表名采用下划线方式命名便于win与linux间移植
		 */
		ModelRecordElResolver.setResolveBeanAsModel(true);
	}

	/**
	 * 停止回调
	 */
	public void beforeJFinalStop() {
		System.out.println("jfinal停止!");
	};

	/**
	 * 常量配置
	 */
	@Override
	public void configConstant(Constants me) {
		Prop p = PropKit.use("config.txt");
		me.setDevMode(p.getBoolean("devMode"));// 开发模式
		me.setEncoding("utf8");
		/**
		 * 该路径参数接受以”/”打头或者以 windows 磁盘盘符打头的绝对路径，
		 * 即可将基础路径指向项目根径之外，方便单机多实例部署。当该路径参数设置为相对路径时， 则是以项目根为基础的相对路径
		 */
		me.setBaseUploadPath("upload");
		me.setBaseDownloadPath("export");
		me.setViewType(ViewType.OTHER);
		me.setError403View("/403.html");
		// 404错误是web应用报出的，只能依靠web.xml里面来配置
		me.setError404View("/404.html");
		me.setError500View("/500.html");
	}

	@Override
	public void configRoute(Routes me) {
		// me.add("/", IndexController.class, "/"); // 第三个参数为该Controller的视图存放路径
		// me.add("/blog", BlogController.class); // 第三个参数省略时默认与第一个参数值相同，在此即为
		// "/blog"
		me.add(new FrontRoutes()); // 前端路由
		me.add(new AdminRoutes()); // 后端路由
	}

	/**
	 * 插件配置
	 */
	@Override
	public void configPlugin(Plugins me) {
		loadPropertyFile("config.txt");
		DruidPlugin druid = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"));
		druid.setDriverClass(getProperty("driver"));
		druid.addFilter(new StatFilter());
		WallFilter wall = new WallFilter();
		wall.setDbType(JdbcConstants.MYSQL);
		wall.setLogViolation(true);
		druid.addFilter(wall);
		me.add(druid);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druid);
		arp.setDevMode(true);
		arp.setShowSql(true);
		// 设置数据库大小写不敏感
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		_MappingKit.mapping(arp);
		me.add(arp);
		// arp.addMapping("user", User.class);
		//EhCachePlugin 
		System.out.println("路径:"+PathKit.getRootClassPath()+File.separator+"ehcache.xml");
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 拦截器 ,当某个 Method 被多个级别的拦截器所拦截，拦截器各级别执行的次序依次为：Global、
	 * Inject、Class、Method，如果同级中有多个拦截器，那么同级中的执行次序是：配置在前面的 先执行。 拦截器从上到下依次分为
	 * Global、Inject、Class、Method 四个层次，Clear 用于清除自身 所处层次以上层的拦截器。
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		// 添加控制层全局拦截器
		me.addGlobalActionInterceptor(new AuthInterceptor());
		// 添加业务层全局拦截器
		// me.addGlobalServiceInterceptor(new TimeInterceptor());
		// 为兼容老版本保留的方法，功能与addGlobalActionInterceptor完全一样
		// me.add(new AuthInterceptor());
		/**
		 * 上例中的 TxByRegex 拦截器可通过传入正则表达式对 action 进行拦截，当 actionKey 被正
		 * 则匹配上将开启事务。TxByActionKeys 可以对指定的 actionKey 进行拦截并开启事务， TxByMethods
		 * 可以对指定的 method 进行拦截并开启事务。 注意：MySql 数据库表必须设置为 InnoDB 引擎时才支持事务，MyISAM
		 * 并不支持事务
		 */
		// me.add(new TxByMethodRegex("(.*save.*|.*update.*)")); me.add(new
		// TxByMethods("save", "update"));
		// me.add(new TxByActionKeyRegex("/trans.*")); me.add(new
		// TxByActionKeys("/tx/save", "/tx/update"));

	}

	/**
	 * ，Handler 可以接管所有 web 请求，并对应用拥有完全的控制权，可以很方便地实现更高层的功能性扩 展
	 */
	@Override
	public void configHandler(Handlers me) {
		// me.add(new ResourceHandler());
		// 添加druid监控
		DruidStatViewHandler dvh = new DruidStatViewHandler("/druid",
				new IDruidStatViewAuth() {
					public boolean isPermitted(HttpServletRequest request) {
						// HttpSession hs = request.getSession(false);
						// return (hs != null && hs.getAttribute("admin") !=
						// null);
						return true;
					}
				});
		me.add(dvh);
	}
}
