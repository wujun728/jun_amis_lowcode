package com.jun.plugin.system.common;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.util.UUID;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-08 16:36
 **/

public class MD5Utils {

	/**
	 * 生成uuid
	 *
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 加密
	 *
	 * @param source
	 * @param slat
	 * @param hashIterations
	 * @return
	 */
	public static String md5(String source, String slat, Integer hashIterations) {
		Md5Hash hash = new Md5Hash(source, slat, hashIterations);
		return hash.toString();
	}

	public static void main(String[] args) {

		String uuid = MD5Utils.createUUID();
		String s = md5("admin123", uuid, 2);
		System.out.println(s);
		System.out.println(uuid);
		// 04A93C74C8294AA09A8B974FD1F4ECBB

		// 532ac00e86893901af5f0be6b704dbc7

	}

}
