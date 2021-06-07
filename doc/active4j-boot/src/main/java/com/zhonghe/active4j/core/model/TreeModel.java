package com.zhonghe.active4j.core.model;

import java.util.List;

/**
 * 树形结构
 * @author teli_
 *
 */
public class TreeModel {

	
	private String id;
	
	private String label;
	
	private List<TreeModel> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<TreeModel> getChildren() {
		return children;
	}

	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}
	
}
