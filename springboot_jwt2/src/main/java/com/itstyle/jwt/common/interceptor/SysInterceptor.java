package com.itstyle.jwt.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itstyle.jwt.common.annotation.Logical;
import com.itstyle.jwt.common.annotation.RequiresRoles;
import com.itstyle.jwt.common.model.CheckResult;
import com.itstyle.jwt.common.model.Result;
import com.itstyle.jwt.common.util.JwtUtils;
import com.itstyle.jwt.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 认证拦截器
 */
public class SysInterceptor  implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SysInterceptor.class);

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler){
        if (handler instanceof HandlerMethod){
            String authHeader = request.getHeader("token");
            if (StringUtils.isEmpty(authHeader)) {
                logger.info("验证失败");
                print(response, Result.error(JwtUtils.JWT_ERROR_CODE_NULL,"签名验证不存在，请重新登录"));
                return false;
            }else{
                CheckResult checkResult = JwtUtils.validateJWT(authHeader);
                if (checkResult.isSuccess()) {
                    /**
                     * 权限验证
                     */
                    String userId = checkResult.getClaims().getId();
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Annotation roleAnnotation= handlerMethod.getMethod().getAnnotation(RequiresRoles.class);
                    if(roleAnnotation!=null){
                        String[] role = handlerMethod.getMethod().getAnnotation(RequiresRoles.class).value();
                        Logical logical = handlerMethod.getMethod().getAnnotation(RequiresRoles.class).logical();
                        List<String> list = sysUserService.getRoleSignByUserId(Integer.parseInt(userId));
                        int count = 0;
                        for(int i=0;i<role.length;i++){
                            if(list.contains(role[i])){
                                count++;
                                if(logical==Logical.OR){
                                    continue;
                                }
                            }
                        }
                        if(logical==Logical.OR){
                            if(count==0){
                                print(response,Result.error("无权限操作"));
                                return false;
                            }
                        }else{
                            if(count!=role.length){
                                print(response,Result.error("无权限操作"));
                                return false;
                            }
                        }
                    }
                    return true;
                } else {
                    switch (checkResult.getErrCode()) {
                        case JwtUtils.JWT_ERROR_CODE_FAIL:
                            logger.info("签名验证不通过");
                            print(response,Result.error(checkResult.getErrCode(),"签名验证不通过，请重新登录"));
                            break;
                        case JwtUtils.JWT_ERROR_CODE_EXPIRE:
                            logger.info("签名过期");
                            print(response,Result.error(checkResult.getErrCode(),"签名过期，请重新登录"));
                            break;
                        default:
                            break;
                    }
                    return false;
                }
            }
        }else{
            return true;
        }
    }
    /**
     * 打印输出
     * @param response
     * @param message  void
     */
    public void print(HttpServletResponse response,Object message){
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            response.setHeader("bpmValidate", "fail");
            response.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {

    }
}
