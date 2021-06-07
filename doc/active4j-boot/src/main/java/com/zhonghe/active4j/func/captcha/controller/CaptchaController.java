package com.zhonghe.active4j.func.captcha.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.zhonghe.active4j.common.controller.BaseController;
import com.zhonghe.active4j.core.model.AjaxJson;
import com.zhonghe.active4j.core.util.ShiroUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @title CaptchaController.java
 * @description 
		验证码管理
 * @time  2019年12月19日 下午3:30:47
 * @author guyp
 * @version 1.0
 */
@Controller
@RequestMapping("/func/captcha")
@Slf4j
public class CaptchaController extends BaseController {
	
	@Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

	public static final String prefix_page = "func/captcha/";
	
	/**
	 * 
	 * @description
	 *  	跳转验证码页面
	 * @params
	 * @return String
	 * @author guyp
	 * @time 2019年12月19日 下午3:30:28
	 */
	@RequestMapping("/captcha")
	public String captcha(Model model) {
		return prefix_page + "captcha.html";
		
	}
	
	/**
	 * 
	 * @description
	 *  	图形验证码
	 * @params
	 * @return void
	 * @author guyp
	 * @time 2019年12月19日 下午4:49:57
	 */
	@RequestMapping("/captchaImage")
	public void verCode(String type, HttpServletRequest request, HttpServletResponse response) {
		ServletOutputStream out = null;
        try {
            HttpSession session = request.getSession();
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");

            String capStr = null;
            String code = null;
            BufferedImage bi = null;
            //算数验证码
            if(StringUtils.equals("math", type)) {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                bi = captchaProducerMath.createImage(capStr);
            }
            //文字验证码
            else if(StringUtils.equals("char", type)) {
                capStr = code = captchaProducer.createText();
                bi = captchaProducer.createImage(capStr);
            }
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, code);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
        	log.error("获取验证码报错，错误信息：{}", e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException e) {
            	log.error("获取验证码报错，错误信息：{}", e.getMessage());
                e.printStackTrace();
            }
        }
	}
	
	/**
	 * 
	 * @description
	 *  	校验验证码
	 * @params
	 * @return AjaxJson
	 * @author guyp
	 * @time 2019年12月19日 下午5:21:09
	 */
	@RequestMapping("/verify")
	@ResponseBody
	public AjaxJson verify(String vercode, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try{
			//获取缓存的验证码
			Object obj = ShiroUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			String code = String.valueOf(obj != null ? obj : "");
			//校验输入的验证码
	    	if (StringUtils.isEmpty(vercode) || !StringUtils.equalsIgnoreCase(code, vercode)) {
	           j.setSuccess(false);
	        }
		} catch(Exception e) {
			j.setSuccess(false);
			log.error("校验验证码报错，错误信息：{}", e.getMessage());
			e.printStackTrace();
		}
		
		return j;
	}
}
