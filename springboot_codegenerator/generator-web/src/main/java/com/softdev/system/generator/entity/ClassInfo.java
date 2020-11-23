package com.softdev.system.generator.entity;

import lombok.Data;

import java.util.List;

/**
 * class info
 *
 * @author Wujun
 */
@Data
public class ClassInfo {

    private String tableName;
    private String className;
	private String classComment;
	private List<FieldInfo> fieldList;

}