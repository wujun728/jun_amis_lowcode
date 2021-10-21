package com.jun.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jun.plugin.modules.sys.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseTest {

	@Autowired
	private RedisService redisService;

	@Test
	public void testRedis() {
		List<Map<String, Object>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> tempMap = new HashMap<>();
			tempMap.put("a" + i, "aaaaaaaaaa" + i);
			list.add(tempMap);
		}
		redisService.setList("testRedisList", list);
	}

}
