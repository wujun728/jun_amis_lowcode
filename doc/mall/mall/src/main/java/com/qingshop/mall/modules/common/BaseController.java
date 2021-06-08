package com.qingshop.mall.modules.common;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qingshop.mall.common.utils.DateUtils;
import com.qingshop.mall.framework.shiro.ShiroUtils;
import com.qingshop.mall.modules.system.entity.SysUser;

/**
 * 基础控制器
 */
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

	/**
	 * 获取分页对象
	 */
	protected <T> Page<T> getPage(int pageNumber) {
		return getPage(pageNumber, 15);
	}

	/**
	 * 获取分页对象
	 * 
	 * @param size
	 *            每页显示数量
	 * @return
	 */
	protected <T> Page<T> getPage(int pageNumber, int pageSize) {
		return new Page<T>(pageNumber, pageSize);
	}

	/**
	 * 重定向至地址 url
	 * 
	 * @param url
	 *            请求地址
	 * @return
	 */
	protected String redirectTo(String url) {
		StringBuffer rto = new StringBuffer("redirect:");
		rto.append(url);
		return rto.toString();
	}

	public SysUser getSysUser() {
		return ShiroUtils.getSysUser();
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @return
	 */
	protected String toJson(Object object) {
		return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
	}

	/**
	 * 
	 * 返回 JSON 格式对象
	 * 
	 * @param object
	 *            转换对象
	 * @param features
	 *            序列化特点
	 * @return
	 */
	protected String toJson(Object object, String format) {
		if (format == null) {
			return toJson(object);
		}
		return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
	}

}
