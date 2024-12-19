package com.ruoyi.project.monitor.server.domain;

import java.lang.management.ManagementFactory;
import java.util.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;

/**
 * JVM相关信息
 * @author ruoyi
 */
public class Jvm {
	/**
	 * 当前JVM占用的内存总数(M)
	 */
	private double total;

	/**
	 * JVM最大可用内存总数(M)
	 */
	private double max;

	/**
	 * JVM空闲内存(M)
	 */
	private double free;

	/**
	 * JDK版本
	 */
	private String version;

	/**
	 * JDK路径
	 */
	private String home;

	public double getTotal() {
		return NumberUtil.div(total, (1024 * 1024), 2);
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getMax() {
		return NumberUtil.div(max, (1024 * 1024), 2);
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getFree() {
		return NumberUtil.div(free, (1024 * 1024), 2);
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getUsed() {
		return NumberUtil.div(total - free, (1024 * 1024), 2);
	}

	public double getUsage() {
		return NumberUtil.mul(NumberUtil.div(total - free, total, 4), 100);
	}

	/**
	 * 获取JDK名称
	 */
	public String getName() {
		return ManagementFactory.getRuntimeMXBean().getVmName();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	/**
	 * 获取服务器启动时间
	 */
	public static Date getServerStartDate() {
		long time = ManagementFactory.getRuntimeMXBean().getStartTime();
		return new Date(time);
	}

	/**
	 * JDK启动时间
	 */
	public String getStartTime() {
		return DateUtil.format(getServerStartDate(), DatePattern.NORM_DATETIME_PATTERN);
	}

	/**
	 * JDK运行时间
	 */
	public String getRunTime() {
		return DateUtil.formatBetween(getServerStartDate(), new Date());
	}
}