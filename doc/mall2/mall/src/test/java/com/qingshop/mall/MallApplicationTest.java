package com.qingshop.mall;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qingshop.mall.modules.system.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MallApplicationTest {

	@Autowired
	private MailService mailService;

	@Test
	public void test() {
		String[] users = { "1124431973@qq.com" };
		String title = "测试模板发送邮件";
		String templateName = "system/mail/mail-templte.html";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "景甜");
		params.put("desc", "女演员");
		mailService.sendTemplateMail(users, title, templateName, params);
	}

}
