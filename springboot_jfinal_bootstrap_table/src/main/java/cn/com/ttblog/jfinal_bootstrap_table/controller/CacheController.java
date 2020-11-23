package cn.com.ttblog.jfinal_bootstrap_table.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jfinal.core.Controller;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * @author Wujun
 */
public class CacheController extends Controller {
	private static Logger cacheLog = LoggerFactory
			.getLogger(CacheController.class);

	public void index() {
		List data=new ArrayList();
		String[] cnames=CacheKit.getCacheManager().getCacheNames();
		cacheLog.info(Arrays.toString(cnames));
		for(String key:cnames){
			Cache c=CacheKit.getCacheManager().getCache(key);
			for(Object k:c.getKeys()){
				Element e=c.get(k);
				data.add(e);
				cacheLog.info(ToStringBuilder.reflectionToString(e));
			}
			cacheLog.info(ToStringBuilder.reflectionToString(c));
		}
		setAttr("data", data);
//		renderJson(Arrays.toString(cnames));
		renderJsp("cache.jsp");
	}
}
