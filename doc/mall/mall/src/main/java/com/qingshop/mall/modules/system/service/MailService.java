package com.qingshop.mall.modules.system.service;

import java.util.Map;

public interface MailService {

	public void sendSimpleMail(String[] users, String title, String contentText);

	public void sendHtmlMail(String[] users, String title, String contentHtml);

	public void sendAttachmentsMail(String[] users, String title, String content, String filePath);

    public void sendTemplateMail(String[] users, String title, String templateName, Map<String, Object> params);
}
