package com.qingshop.mall.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {

	private static final Logger logger = Logger.getLogger("HttpUtil");

	/**
	 * 允许 JS 跨域设置 <!-- 使用 nginx 注意在 nginx.conf 中配置 --> http { ...... add_header
	 * Access-Control-Allow-Origin *; ...... } 非 ngnix 下，如果该方法设置不管用、可以尝试增加下行代码。
	 * response.setHeader("Access-Control-Allow-Origin", "*");
	 * 
	 * @param response
	 *            响应请求
	 */
	public static void allowJsCrossDomain(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Max-Age", "3600");
	}

	/**
	 * 判断请求是否为 AJAX
	 * 
	 * @param request
	 *            当前请求
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ? true : false;
	}

	/**
	 * AJAX 设置 response 返回状态
	 * 
	 * @param response
	 * @param status
	 *            HTTP 状态码
	 * @param tip
	 */
	public static void ajaxStatus(HttpServletResponse response, int status, String tip) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.setStatus(status);
			PrintWriter out = response.getWriter();
			out.print(tip);
			out.flush();
		} catch (IOException e) {
			logger.severe(e.toString());
		}
	}

	/**
	 * 获取当前 URL 包含查询条件
	 * 
	 * @param request
	 * @param encode
	 *            URLEncoder编码格式
	 * @return
	 * @throws IOException
	 */
	public static String getQueryString(HttpServletRequest request, String encode) throws IOException {
		StringBuffer sb = new StringBuffer(request.getRequestURL());
		String query = request.getQueryString();
		if (query != null && query.length() > 0) {
			sb.append("?").append(query);
		}
		return URLEncoder.encode(sb.toString(), encode);
	}

	/**
	 * getRequestURL是否包含在URL之内
	 * 
	 * @param request
	 * @param url
	 *            参数为以';'分割的URL字符串
	 * @return
	 */
	public static boolean inContainURL(HttpServletRequest request, String url) {
		boolean result = false;
		if (url != null && !"".equals(url.trim())) {
			String[] urlArr = url.split(";");
			StringBuffer reqUrl = new StringBuffer(request.getRequestURL());
			for (int i = 0; i < urlArr.length; i++) {
				if (reqUrl.indexOf(urlArr[i]) > 1) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * URLEncoder 返回地址
	 * 
	 * @param url
	 *            跳转地址
	 * @param retParam
	 *            返回地址参数名
	 * @param retUrl
	 *            返回地址
	 * @return
	 */
	public static String encodeRetURL(String url, String retParam, String retUrl) {
		return encodeRetURL(url, retParam, retUrl, null);
	}

	/**
	 * URLEncoder 返回地址
	 * 
	 * @param url
	 *            跳转地址
	 * @param retParam
	 *            返回地址参数名
	 * @param retUrl
	 *            返回地址
	 * @param Map
	 *            携带参数
	 * @return
	 */
	public static String encodeRetURL(String url, String retParam, String retUrl, Map<String, String> data) {
		if (url == null) {
			return null;
		}

		StringBuffer retStr = new StringBuffer(url);
		retStr.append("?");
		retStr.append(retParam);
		retStr.append("=");
		try {
			retStr.append(URLEncoder.encode(retUrl, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.severe("encodeRetURL error." + url);
			e.printStackTrace();
		}

		if (data != null) {
			for (Map.Entry<String, String> entry : data.entrySet()) {
				retStr.append("&").append(entry.getKey()).append("=").append(entry.getValue());
			}
		}

		return retStr.toString();
	}

	/**
	 * URLDecoder 解码地址
	 * 
	 * @param url
	 *            解码地址
	 * @return
	 */
	public static String decodeURL(String url) {
		if (url == null) {
			return null;
		}
		String retUrl = "";

		try {
			retUrl = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.severe("encodeRetURL error." + url);
			e.printStackTrace();
		}

		return retUrl;
	}

	/**
	 * GET 请求
	 * 
	 * @param request
	 * @return boolean
	 */
	public static boolean isGet(HttpServletRequest request) {
		if ("GET".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		return false;
	}

	/**
	 * POST 请求
	 * 
	 * @param request
	 * @return boolean
	 */
	public static boolean isPost(HttpServletRequest request) {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		return false;
	}

	/**
	 * 请求重定向至地址 location
	 * @param response
	 *            请求响应
	 * @param location
	 *            重定向至地址
	 */
	public static void sendRedirect(HttpServletResponse response, String location) {
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			logger.severe("sendRedirect location:" + location);
			e.printStackTrace();
		}
	}

	/**
	 * 获取Request Playload 内容
	 * @param request
	 * @return Request Playload 内容
	 */
	public static String requestPlayload(HttpServletRequest request) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 获取当前完整请求地址
	 * @param request
	 * @return 请求地址
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer url = new StringBuffer(request.getScheme());
		// 请求协议 http,https
		url.append("://");
		url.append(request.getHeader("host"));// 请求服务器
		url.append(request.getRequestURI());// 工程名
		if (request.getQueryString() != null) {
			// 请求参数
			url.append("?").append(request.getQueryString());
		}
		return url.toString();
	}
}