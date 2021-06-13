package com.qingshop.mall.common.utils.idwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 缓存配置文件读写操作类
 * 
 * @version
 */
public class DistributeConfiguration {
	private static Object lock = new Object();
	private static DistributeConfiguration config = null;
	private static ResourceBundle rb = null;
	private static final String DEFAULT_CONFIG_FILE = "distrubite";// 默认缓存配置文件名称
	private static final String DEFAULT_PROPERTY_FILE = "distrubite.properties";// 默认缓存properties配置文件名，文件放在src下

	private DistributeConfiguration() {
		rb = ResourceBundle.getBundle(DEFAULT_CONFIG_FILE);
	}

	public static DistributeConfiguration getInstance() {
		synchronized (lock) {
			if (config == null) {
				config = new DistributeConfiguration();
			}
		}
		return config;
	}

	public String getValue(String key) {
		return rb.getString(key);
	}

	public void setValue(String key, String value) {
		Properties prop = new Properties();
		try {
			File file = new File(DEFAULT_PROPERTY_FILE);
			if (!file.exists())
				file.createNewFile();
			InputStream fis = new FileInputStream(file);
			prop.load(fis);
			fis.close();
			OutputStream fos = new FileOutputStream(DEFAULT_PROPERTY_FILE);
			prop.setProperty(key, value);
			prop.store(fos, "Update '" + key + "' value");
			fos.close();
		} catch (IOException e) {
			System.err.println("Visit " + DEFAULT_PROPERTY_FILE + " for updating " + value + " value error");
		}
	}
}
