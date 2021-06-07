package com.zhonghe.active4j.core.beetl.tag;

import java.io.IOException;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 页面脚本文件
 * @author teli_
 *
 */
@Getter
@Setter
public class JsBaseTag extends CommonParamsTag {

	//页面脚本文件
	private String jsFile;
	
	@Override
	public void render() {
		if(args.length > 1) {
			this.jsFile = this.getParameterString("jsFile");
		}
		
		StringBuffer sb = new StringBuffer();
		
		if(StringUtils.isNotEmpty(jsFile)) {
			sb = sb.append("<script src=\"").append(this.getJsFile()).append("\"></script>");
		}
		
		try {
			this.ctx.byteWriter.writeString(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
