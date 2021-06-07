package com.zhonghe.active4j.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 公共返回实体
 * @author teli_
 *
 */
@Getter
@Setter
public class ResultJson {

	private int code = 0;
	
	private boolean success = true;
	
	private String msg = "操作成功";
	
	private Object data;
	
}
