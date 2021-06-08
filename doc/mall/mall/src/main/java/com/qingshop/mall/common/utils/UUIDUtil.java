package com.qingshop.mall.common.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

	private static final int SHORT_LENGTH = 8;

	public static String uuid() {
		String str = UUID.randomUUID().toString();
		String temp = str.replace("-", "");
		return temp;
	}

	public static String getUniqueIdByUUId() {
		// 最大支持1-9个集群机器部署
		int machineId = 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return machineId + String.format("%015d", hashCodeV);
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < SHORT_LENGTH; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();
	}

	public static String generateIntCode(int length) {
		Random random = new Random();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < length; i++) {
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		String a = "http://2620342963290368.iyongweb-cs.kenfor.com";
		String b = "https://xcx-cs.iyong.com/2620342963290368";
		String c = "http://m.2620342963290368.iyongweb-cs.kenfor.com";
		System.out.println(a.split("://")[1]);
		System.out.println(b.split("://")[1]);
		System.out.println(c.split("://")[1]);
	}
}
