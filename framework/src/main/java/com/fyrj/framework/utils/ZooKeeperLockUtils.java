package com.fyrj.framework.utils;

import org.I0Itec.zkclient.ZkLock;

public class ZooKeeperLockUtils {
	
	public static void show(){
		ZkLock zkLock = new ZkLock();
		zkLock.lock();
	}
}
