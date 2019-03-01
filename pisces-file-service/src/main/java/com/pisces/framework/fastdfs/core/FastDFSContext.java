/**
 * Copyright (c) 2018 Pisces Technology Co., Ltd.
 */
package com.pisces.framework.fastdfs.core;

public class FastDFSContext {
	private final static ThreadLocal<String> currentGroup = new ThreadLocal<String>();
	
	public static void setCurrentGroup(String groupName) {
		currentGroup.set(groupName);
	}
	
	public static String getCurrentGroup() {
		return currentGroup.get();
	}
	
	public static void removeCurrentGroup() {
		currentGroup.remove();
	}
}
