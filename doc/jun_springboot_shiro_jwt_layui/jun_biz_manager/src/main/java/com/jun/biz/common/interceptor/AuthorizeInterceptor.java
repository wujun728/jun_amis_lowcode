package com.jun.biz.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.jun.biz.common.base.enums.VoCodeEnum;
import com.jun.biz.common.base.vo.ResultVO;
import com.jun.biz.common.runtime.AdminSessionHolder;
import com.jun.biz.common.utils.HttpUtil;
import com.jun.biz.manager.vo.auth.AuthVO;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


/**
 * 
 */
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }
        MDC.put("ip", HttpUtil.getRemoteAddr(request));
        MDC.put("requestUri", request.getRequestURI());
        MDC.put("requestId", System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(3));
        AuthVO admin = AdminSessionHolder.getCurrentAdmin();
        if (admin == null) {
            boolean isAjax = HttpUtil.isAjax(request);
            if (isAjax) {
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(JSONObject.toJSONString(ResultVO.buildResult(VoCodeEnum.NOT_LOGGED_IN)));
                return false;
            }
            String redirectUrl = request.getRequestURL().toString();
            if (request.getQueryString() != null) {
                redirectUrl += ("?" + request.getQueryString());
            }
            response.sendRedirect(request.getContextPath() + "/page/login.html?redirectUrl=" + URLEncoder.encode(redirectUrl, "utf-8"));
            return false;
        }
        MDC.put("user", admin.getUsername());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        MDC.clear();
    }
}
