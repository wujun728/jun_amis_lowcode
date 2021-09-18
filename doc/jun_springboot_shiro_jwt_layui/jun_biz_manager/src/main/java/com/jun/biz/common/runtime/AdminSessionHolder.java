package com.jun.biz.common.runtime;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jun.biz.common.utils.SpringContextUtil;
import com.jun.biz.manager.model.Permission;
import com.jun.biz.manager.service.PermissionService;
import com.jun.biz.manager.vo.auth.AuthVO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created on 2020/10/14 18:04
 * <p>
 * Description: [TODO]
 * <p>
 *
 * 
 */
public class AdminSessionHolder {


    public static AuthVO getCurrentAdmin() {
        return (AuthVO) getSession().getAttribute("currentAdmin");
    }

    public static void setCurrentAdmin(AuthVO authVO) {
        getSession().setAttribute("currentAdmin", authVO);
    }

    @SuppressWarnings("unchecked")
    public static List<Permission> getPermission() {
        List<Permission> permissions = (List<Permission>) getSession().getAttribute("Permissions");
        if (permissions == null) {
            PermissionService bean = SpringContextUtil.getBean(PermissionService.class);
            permissions = bean.currentPermissions();
            if (permissions != null) {
                getSession().setAttribute("Permissions", permissions);
            }
        }
        return permissions;
    }


    private static HttpSession getSession() {
        return ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession (true);
    }


}
