package com.louis.multi.thread;

public class MutableInteger {
	
	private volatile int value;

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}
}