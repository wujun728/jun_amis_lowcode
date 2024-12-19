package com.ruoyi.project.system.user.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.utils.CookieUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.menu.domain.Menu;
import com.ruoyi.project.system.menu.service.MenuService;
import com.ruoyi.project.system.param.service.ParamService;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 首页 业务处理
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController {
	@Autowired
	private MenuService menuService;

	@Autowired
	private ParamService paramService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

	// 系统首页
	@GetMapping("/index")
	public String index(ModelMap mmap) {
		// 取身份信息
		User user = getSysUser();
		// 根据用户id取出菜单
		List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", paramService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", paramService.selectConfigByKey("sys.index.skinName"));
        mmap.put("ignoreFooter", paramService.selectConfigByKey("sys.index.ignoreFooter"));
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        mmap.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        mmap.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));

        // 菜单导航显示风格
        String menuStyle = paramService.selectConfigByKey("sys.index.menuStyle");
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (StrUtil.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName())) {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        return webIndex;
	}

    // 锁定屏幕
    @GetMapping("/lockscreen")
    public String lockscreen(ModelMap mmap) {
        mmap.put("user", getSysUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 解锁屏幕
    @PostMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password) {
        User user = getSysUser();
        if (ObjectUtil.isNull(user)) {
            return AjaxResult.error("服务器超时，请重新登陆");
        }
        if (passwordService.matches(user, password)) {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
	public String switchSkin() {
		return "skin";
	}

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response) {
        CookieUtils.setCookie(response, "nav-style", style);
    }

	// 系统介绍
	@GetMapping("/system/main")
	public String main(ModelMap mmap) {
        String sql = "select notice_id, notice_title, notice_type, notice_content, status, create_by, create_time, update_by, update_time, remark " +
                "  from sys_notice a where notice_id = '1'";
		mmap.put("notice", menuService.db.queryForMap(sql, null));
        mmap.put("version", ruoYiConfig.getVersion());
		return "main_v2";
	}

    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate) {
        Integer initPasswordModify = Convert.toInt(paramService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify !=null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate) {
        Integer passwordValidateDays = Convert.toInt(paramService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays !=null && passwordValidateDays > 0) {
            if (ObjectUtil.isNull(pwdUpdateDate)) {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            return DateUtil.betweenDay(pwdUpdateDate, new Date(), false) > passwordValidateDays;
        }
        return false;
    }
}