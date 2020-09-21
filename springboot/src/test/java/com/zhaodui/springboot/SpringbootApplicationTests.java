package com.zhaodui.springboot;

import com.zhaodui.springboot.baseutil.RedisUtil;
import com.zhaodui.springboot.common.mdoel.Page;
import com.zhaodui.springboot.buse.model.FxjFBiData01;
import com.zhaodui.springboot.buse.service.FxjBiData01Service;
import com.zhaodui.springboot.system.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private FxjBiData01Service fxjBiData01Service;
	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void contextLoads() {

//		sysUserService.getUserByName("1");
//
//		SysUser user = new SysUser();
////		user.setUser01("admin");
////		user.setUser02("123456");
////		user.setUser03("1234567");
////		sysUserService.add(user);
////		sysUserService.getAll().forEach(System.out::println);
////		sysUserService.}
//		redisUtil.set("k1", "Hello World");
//        String k1 = redisUtil.get("k1").toString();
//        System.out.println(k1);
//        Page<SysUser> page = sysUserService.getPage("ad", "1", "23", 1, 1);
//		page.getData().forEach(System.out::println);


//		FxjFBiData01 fxjFBiData01 = new FxjFBiData01();
//		StringBuilder sb = new StringBuilder();
//		UUID uuid = UUID.randomUUID();
//		String uniqueId = sb.append(uuid.toString().replace("-", "")).toString();
//		fxjFBiData01.setId(uniqueId);
//		fxjFBiData01.setFxjOut01("1111");
//		fxjBiData01Service.add(fxjFBiData01);
		Page<FxjFBiData01>  page =  fxjBiData01Service.getPage("1","","","","","","","","","",1,11);

		page.getData().forEach(System.out::println);
	}
}
