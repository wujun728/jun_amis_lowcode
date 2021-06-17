package com.jun.plugin.common.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.jun.plugin.modules.sys.model.SysUser;

public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";

	private static int hashIterations = 2;

	public static void encryptPassword(SysUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
	}

	public static String getPassword(SysUser user) {
		String encryptPassword = new SimpleHash(algorithmName, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
		return encryptPassword;
	}

	public static void main(String[] args) {
		SysUser user = new SysUser();
		user.setUsername("admin");
		user.setPassword("123456");
		user.setSalt("wujun");
		encryptPassword(user);
		System.out.println(user.getPassword());
		System.out.println(user.getSalt());
	}
}
