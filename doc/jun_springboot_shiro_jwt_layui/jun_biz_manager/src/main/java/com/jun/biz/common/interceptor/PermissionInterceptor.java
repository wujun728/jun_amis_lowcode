package com.jun.biz.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jun.biz.common.annotation.PerCode;
import com.jun.biz.common.base.enums.VoCodeEnum;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.manager.model.Permission;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        PerCode permissionCode = ((HandlerMethod) handler).getMethodAnnotation(PerCode.class);
        if (permissionCode == null) {
            permissionCode = ((HandlerMethod) handler).getBeanType().getAnnotation(PerCode.class);
        }
        if (permissionCode == null) {
            return true;
        }
        List<Permission> permissions = AdminSessionHolder.getPermission();
        Set<String> permissionCodes = permissions.stream().map(Permission::getCode).collect(Collectors.toSet());
        for (String code : permissionCode.value()) {
            if (permissionCodes.contains(code)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSONObject.toJSONString(ResultVO.buildResult(VoCodeEnum.NO_PERMISSION)));
        return false;
    }

}
