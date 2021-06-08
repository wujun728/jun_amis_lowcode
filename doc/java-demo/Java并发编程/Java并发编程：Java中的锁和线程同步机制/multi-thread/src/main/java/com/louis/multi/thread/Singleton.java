package com.louis.multi.thread;

class Singleton {
	
	private static volatile Singleton instance;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) { // 当instance不为null时，仍可能指向一个“被部分初始化的对象”
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}