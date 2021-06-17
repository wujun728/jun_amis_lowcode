package com.jun.plugin.modules.base;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jun.plugin.common.util.ResultUtil;
import com.jun.plugin.common.vo.ResponseVo;

@Controller
@RequestMapping("/publicservice")
public class PublicServiceController {

	private static final Logger logger = LoggerFactory.getLogger(PublicServiceController.class);

	@GetMapping("/test1")
	@ResponseBody
	public String add() {
		return "abc";
	}

	@PostMapping("/test2")
	@ResponseBody
	public ResponseVo<?> logBatchDelete(String id) throws SchedulerException {
		return ResultUtil.success("删除成功 test2");
	}
  
	//http://192.168.199.101:8100/publicservice/hello
	@GetMapping("/hello")
	@ResponseBody
	public String jobloginfo(Model model, String jobLogId) {
		return "hello123";
	}

}
