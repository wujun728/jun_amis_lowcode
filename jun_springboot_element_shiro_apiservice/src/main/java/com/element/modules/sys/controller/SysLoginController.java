package com.element.modules.sys.controller;

import com.element.common.utils.Base64;
import com.element.common.utils.ResultVo;
import com.element.modules.sys.entity.SysMenuEntity;
import com.element.modules.sys.entity.SysUserEntity;
import com.element.modules.sys.entity.vo.RouterVo;
import com.element.modules.sys.form.SysLoginForm;
import com.element.modules.sys.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 登录相关
 */
@RestController
public class SysLoginController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Autowired
    private SysCaptchaService sysCaptchaService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public ResultVo captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", stream);
        try {
            ResultVo ajax = ResultVo.ok();
            ajax.put("img", Base64.encode(stream.toByteArray()));
            return ajax;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.error(e.getMessage());
        } finally {
            stream.close();
        }
    }

    /**
     * 登录
     */
    @PostMapping("/sys/login")
    public ResultVo login(@RequestBody SysLoginForm form) {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return ResultVo.error("验证码不正确");
        }
        //用户信息
        SysUserEntity user = sysUserService.getByUserName(form.getUsername());
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return ResultVo.error("账号或密码不正确");
        }
        //账号锁定
        if (user.getStatus() == 0) {
            return ResultVo.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        ResultVo resultVo = sysUserTokenService.createToken(user.getUserId());
        return resultVo;
    }

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/sys/info")
    public ResultVo info() {
        ResultVo resultVo = ResultVo.ok();
        // 用户信息
        SysUserEntity user = getUser();
        // 角色集合
        Set<String> roles = sysRoleService.listRolePermission(user);
        // 权限集合
        Set<String> permissions = sysMenuService.listMenuPermission(user);
        resultVo.put("user", user);
        resultVo.put("roles", roles);
        resultVo.put("permissions", permissions);
        return resultVo;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/sys/getRouters")
    public ResultVo getRouters() {
        // 用户信息
        SysUserEntity user = getUser();
        // 角色查询菜单
        List<SysMenuEntity> menus = sysMenuService.listMenuTreeByUserId(user.getUserId());
        // 构建路由菜单
        List<RouterVo> routerList = sysMenuService.buildMenus(menus);
        // 返回动态路由
        return ResultVo.ok().put("routerList", routerList);
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public ResultVo logout() {
        Subject currentUser = SecurityUtils.getSubject();
        sysUserTokenService.logout(getUserId());
        currentUser.logout();
        return ResultVo.ok();
    }

}
