package com.jun.plugin.modules.job.task;

import org.springframework.stereotype.Component;

@Component("jobTaskTest")
public class JobTaskTest {
	
	public void jobParams(String params) {
		System.out.println("Bean加载执行有参方法：" + params);
	}

	public void jobNoParams() {
		System.out.println("Bean加载执行无参方法");
	}
}
