package com.jun.plugin.system.common;

import java.io.Serializable;

public class SetAndGetMethod implements Serializable {

	private static final long serialVersionUID = 941681601828257157L;
	private String setMethod;

	private String getMethod;

	public String getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}

	public SetAndGetMethod(String setMethod, String getMethod) {
		this.setMethod = setMethod;
		this.getMethod = getMethod;
	}
}
