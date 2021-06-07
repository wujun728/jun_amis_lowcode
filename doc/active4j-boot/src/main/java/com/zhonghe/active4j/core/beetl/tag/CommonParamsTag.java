package com.zhonghe.active4j.core.beetl.tag;

import java.util.Map;

import org.beetl.core.Tag;


/**
 * 抽象了一下，简化参数获取代码
 * @author teli_
 *
 */
public abstract class CommonParamsTag extends Tag{

	
	public String getParameterString(String parameter) {
		Map map = (Map) this.args[1];
		
		if(null != map && map.containsKey(parameter)) {
			return (String)map.get(parameter);
		}
		
		return "";
	}
	
	public Boolean getParameterBoolean(String parameter) {
		Map map = (Map) this.args[1];
		
		if(null != map && map.containsKey(parameter)) {
			return Boolean.parseBoolean((String)map.get(parameter));
		}
		
		return null;
	}
	
	public Integer getParameterInteger(String parameter) {
		Map map = (Map) this.args[1];
		
		if(null != map && map.containsKey(parameter)) {
			return Integer.valueOf((String)map.get(parameter));
		}
		
		return null;
	}
	

	public Object getParamter(String parameter) {
		Map map = (Map) this.args[1];
		
		return map.get(parameter);
	}
}
