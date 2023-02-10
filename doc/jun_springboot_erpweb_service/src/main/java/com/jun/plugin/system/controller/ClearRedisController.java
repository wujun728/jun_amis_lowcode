package com.jun.plugin.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * ClassName: ClearRedis create: 2020-04-24 15:37
 *
 * @author: Wujun @since： JDK1.8
 **/

@RestController
@RequestMapping("api/clearRedis")
public class ClearRedisController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/*
	 * ******************************************清理全部缓存开始***************************
	 * ***********************
	 */

	@RequestMapping("cleanRedis")
	public Map<String, Object> cleanRedis() {
		Map<String, Object> map = new HashMap<>();
		try {
			Set<String> keys = stringRedisTemplate.keys("*");
			assert keys != null;
			Iterator<String> it1 = keys.iterator();
			while (it1.hasNext()) {
				stringRedisTemplate.delete(it1.next());
			}
			map.put("code", 1);
			map.put("msg", "清理全局缓存成功");
			return map;
		} catch (Exception e) {
			map.put("code", -1);
			map.put("msg", "清理全局缓存失败");
			return map;
		}
	}

	/*
	 * ******************************************清理全部缓存结束***************************
	 * ***********************
	 */

}
