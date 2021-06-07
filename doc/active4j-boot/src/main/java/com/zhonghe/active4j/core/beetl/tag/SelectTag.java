package com.zhonghe.active4j.core.beetl.tag;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhonghe.active4j.system.util.SystemUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 下拉选择框标签，数据取自数据字典表
 * @author teli_
 *
 */
@Getter
@Setter
public class SelectTag extends CommonParamsTag {
	
	private String id;
	private String dicCode;
	private String name;
	private Boolean must;
	private String blank;
	private String defaultValue;
	
	@Override
	public void render() {
		//参数赋值
		if(args.length > 1) {
			this.id = this.getParameterString("id");
			this.dicCode = this.getParameterString("dicCode");
			this.name = this.getParameterString("name");
			this.must = this.getParameterBoolean("must");
			this.blank = this.getParameterString("blank");
			this.defaultValue = this.getParameterString("defaultValue");
			//默认值
			if(StringUtils.isEmpty(id)) {
				this.id = name;
			}
			if(null == must) {
				must = false;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = SystemUtils.getDictionaryMap(dicCode);
		
		if(null != map && map.keySet().size() > 0) {
			sb = sb.append("<select class=\"layui-input\" id=\"").append(this.id).append("\" name=\"").append(this.getName()).append("\" ");
			if(must) {
				sb = sb.append("lay-verify=\"required\">");
			}else {
				sb = sb.append(">");
			}
			
			if(StringUtils.isNotEmpty(blank)) {
				sb = sb.append("<option value=\"\">").append(blank).append("</option>");
			}
			
			for(String key : map.keySet()) {
				if(StringUtils.isNotEmpty(defaultValue) && StringUtils.equals(defaultValue, key)) {
					sb = sb.append("<option value=\"").append(key).append("\" selected=\"selected\">").append(map.get(key)).append("</option>");
				}else {
					sb = sb.append("<option value=\"").append(key).append("\">").append(map.get(key)).append("</option>");
				}
				
			}
			sb = sb.append("</select>");
		}
		
		try {
			this.ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
