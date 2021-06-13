package com.qingshop.mall.modules.system.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.qingshop.mall.modules.system.service.MailService;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class MailServiceImpl implements MailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Value("${spring.mail.from}")
	private String from;

	/**
	 * TODO （发送简单文本邮件）
	 * @see com.qingshop.mall.modules.system.service.MailService#sendSimpleMail(java.lang.String[], java.lang.String, java.lang.String)
	 */
	public void sendSimpleMail(String[] users, String title, String contentText) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(users);
		message.setSubject(title);
		message.setText(contentText);
		mailSender.send(message);
		logger.info("邮件已经发送。");
	}
	
	/**
	 * TODO （发送含html内容邮件）
	 * @see com.qingshop.mall.modules.system.service.MailService#sendHtmlMail(java.lang.String[], java.lang.String, java.lang.String)
	 */
	public void sendHtmlMail(String[] users, String title, String contentHtml) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(from);
			messageHelper.setTo(users);
			message.setSubject(title);
			messageHelper.setText(contentHtml, true);
			mailSender.send(message);
			logger.info("邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送邮件时发生异常！", e);
		}
	}

	/**
	 * TODO （发送带附件内容邮件）
	 * @see com.qingshop.mall.modules.system.service.MailService#sendAttachmentsMail(java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	public void sendAttachmentsMail(String[] users, String title, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(users);
			helper.setSubject(title);
			helper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
			// 日志信息
			logger.info("邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送邮件时发生异常！", e);
		}

	}
	
	/**
	 * TODO （发送模板文件）
	 * @see com.qingshop.mall.modules.system.service.MailService#sendTemplateMail(java.lang.String[], java.lang.String, java.lang.String, java.util.Map)
	 */
	public void sendTemplateMail(String[] users, String title, String templateName, Map<String, Object> params) {
		// 获取MimeMessage对象
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(from);
			messageHelper.setTo(users);
			message.setSubject(title);
			String content = "";
			try {
				content = getMailTextByTemplateName(templateName, params);
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
			messageHelper.setText(content, true);
			mailSender.send(message);
			logger.info("邮件已经发送。");
		} catch (MessagingException e) {
			logger.error("发送邮件时发生异常！", e);
		}
	}

	private String getMailTextByTemplateName(String templateName, Map<String, Object> params) throws IOException, TemplateException {
		try {
			String mailText = "";
			// 通过指定模板名获取FreeMarker模板实例
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			mailText = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
			return mailText;
		} catch (TemplateNotFoundException e) {
			// 若找不到指定模板则使用默认模板
			templateName = "system/mail/mail-templte.html";
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
		}
	}
}
