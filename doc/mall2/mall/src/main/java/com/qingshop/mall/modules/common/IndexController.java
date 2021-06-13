package com.qingshop.mall.modules.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.system.entity.SysMenu;
import com.qingshop.mall.modules.system.entity.SysUser;
import com.qingshop.mall.modules.system.service.ISysMenuService;

/**
 * 首页控制器
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private ISysMenuService sysMenuService;

	@GetMapping("index")
	public String index(Model model) {
		SysUser user = ShiroUtils.getSysUser();
		List<SysMenu> memuList = sysMenuService.selectMenusByUserId(user);
		model.addAttribute("memuList", memuList);
		model.addAttribute("user", user);
		return "index";
	}

	@GetMapping("main")
	public String main(Model model) {
		return "main";
	}

	@GetMapping("/tocron")
	public String tocron() {
		return "system/job/cron";
	}

	@GetMapping("/toicon")
	public String toicon() {
		return "system/menu/icon";
	}

	@GetMapping("/jobinfo/calcRunTime")
	@ResponseBody
	public Object calcRunTime(String expression) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		StringBuffer sbResult = new StringBuffer();
		sbResult.append("[");
		try {
			CronTriggerImpl trigger = new CronTriggerImpl();
			trigger.setCronExpression(expression);
			Date start = trigger.getStartTime();
			for (int i = 0; i < 5; i++) {
				Date next = trigger.getFireTimeAfter(start);
				sbResult.append(", \"").append(sdf.format(next)).append("\"");
				start = next;
			}
			sbResult.append("]");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sbResult.replace(sbResult.indexOf(","), sbResult.indexOf(",") + 1, "");
	}
}
