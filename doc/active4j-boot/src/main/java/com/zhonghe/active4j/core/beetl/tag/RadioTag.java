package com.zhonghe.active4j.core.beetl.tag;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zhonghe.active4j.system.util.SystemUtils;

/**
 * 显示radio的控件  取值数据字典
 * @author teli_
 *
 */
public class RadioTag extends CommonParamsTag {
	
	private String name;
	
	private String defaultValue;
	
	private String dicCode;

	@Override
	public void render() {
		//参数赋值
		if(args.length > 1) {
			this.name = this.getParameterString("name");
			this.defaultValue = this.getParameterString("defaultValue");
			this.dicCode = this.getParameterString("dicCode");
		}
		
		StringBuffer sb = new StringBuffer();
		Map<String, String> map = SystemUtils.getDictionaryMap(dicCode);
		
		if(null != map && map.keySet().size() > 0) {
			for(String key : map.keySet()) {
				if(StringUtils.equals(key, defaultValue)) {
					sb = sb.append("<input type=\"radio\" name=\"").append(this.name).append("\" value=\"").append(key).append("\" title=\"").append(map.get(key)).append("\"  checked>");
				}else {
					sb = sb.append("<input type=\"radio\" name=\"").append(this.name).append("\" value=\"").append(key).append("\" title=\"").append(map.get(key)).append("\">");
				}
			}
		}
		
		
		try {
			this.ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
