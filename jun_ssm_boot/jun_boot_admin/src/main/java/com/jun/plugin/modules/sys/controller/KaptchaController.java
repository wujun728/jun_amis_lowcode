package com.jun.plugin.modules.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.jun.plugin.config.shiro.ShiroUtils;

@Controller
public class KaptchaController {

	private static final Logger logger = LoggerFactory.getLogger(KaptchaController.class);

	@Autowired
	private Producer captchaProducer;

	/**
	 *
	 * 获取验证码图片 Gets captcha code.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the captcha code
	 * @throws IOException
	 *             the io exception
	 */
	@RequestMapping("/verificationCode")
	public ModelAndView getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");
		// 生成验证码文本
		String capText = captchaProducer.createText();
		String capStr = capText.substring(0, capText.lastIndexOf("@"));
		String code = capText.substring(capText.lastIndexOf("@") + 1);
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, code);
		logger.info("生成验证码文本====" + capText);
		logger.info("生成验证码code====" + code);
		// 利用生成的字符串构建图片
		BufferedImage bi = captchaProducer.createImage(capStr);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	
	 /**
    *
    * 获取验证码图片-字符串
    * Gets captcha code.
    *
    * @param request  the request
    * @param response the response
    * @return the captcha code
    * @throws IOException the io exception
    */
   @RequestMapping("/verificationCodeV2")
   public ModelAndView getCaptchaCodeV2(HttpServletRequest request, HttpServletResponse response) throws IOException {
       HttpSession session = request.getSession();
       response.setDateHeader("Expires", 0);
       response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
       response.addHeader("Cache-Control", "post-check=0, pre-check=0");
       response.setHeader("Pragma", "no-cache");
       response.setContentType("image/jpeg");
       //生成验证码文本
       String capText = captchaProducer.createText();
       session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
       logger.info("生成验证码文本====" + capText);
       //利用生成的字符串构建图片
       BufferedImage bi = captchaProducer.createImage(capText);
       ServletOutputStream out = response.getOutputStream();
       ImageIO.write(bi, "jpg", out);
       try {
           out.flush();
       } finally {
           out.close();
       }
       return null;
   }
}
