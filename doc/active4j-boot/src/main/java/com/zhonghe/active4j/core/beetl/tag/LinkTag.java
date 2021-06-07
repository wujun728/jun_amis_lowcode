package com.zhonghe.active4j.core.beetl.tag;

import java.io.IOException;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhonghe.active4j.core.util.ShiroUtils;

/**
 * 页面a link标签 主要为了权限控制
 * 
 * @author teli_
 *
 */
public class LinkTag extends CommonParamsTag {

	private String id;

	private String layEvent;

	private String label;

	private String cssClass;

	private String permission;

	@Override
	public void render() {

		// 参数赋值
		if (args.length > 1) {
			this.id = this.getParameterString("id");
			this.layEvent = this.getParameterString("layEvent");
			this.label = this.getParameterString("label");
			this.cssClass = this.getParameterString("cssClass");
			this.permission = this.getParameterString("permission");
		}
		
		StringBuffer sb = new StringBuffer();
		
		//有权限才显示
		if(StringUtils.isEmpty(permission) || ShiroUtils.hasPermission(permission)) {
			sb = sb.append("<a class=\"").append(this.cssClass).append("\"").append(" lay-event=\"").append(this.layEvent)
					.append("\"");
			if(StringUtils.isNotEmpty(this.id)) {
				sb = sb.append(" id=\"").append(id).append("\"");
			}
					
			sb = sb.append(">").append(this.label).append("</a>");
		}
		
		try {
			this.ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
