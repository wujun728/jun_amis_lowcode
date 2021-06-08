package com.fish.web.admin.filter;

import com.fish.cons.REST;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: Worshiper
 * Date: 2014/6/21
 * Time: 13:40
 */

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getServletPath());

        if (request.getServletPath().startsWith(REST.LOGIN) ||
                request.getServletPath().startsWith(REST.LOGOUT)) {
            return true;
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return true;
        }

        response.sendRedirect("/");
        return false;
    }
}
