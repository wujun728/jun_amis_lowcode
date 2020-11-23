package com.softdev.system.generator.util;



import com.softdev.system.generator.entity.ClassInfo;

import java.io.IOException;

/**
 * code generate tool
 *
 * @author Wujun
 */
public class CodeGeneratorTool {

	/**
	 * process Table Into ClassInfo
	 *
	 * @param tableSql
	 * @return
	 */
	public static ClassInfo processTableIntoClassInfo(String tableSql) throws IOException {
		return TableParseUtil.processTableIntoClassInfo(tableSql);
	}

}