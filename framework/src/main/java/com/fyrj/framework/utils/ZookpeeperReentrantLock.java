package com.fyrj.framework.utils;

import java.util.concurrent.locks.ReentrantLock;

public class ZookpeeperReentrantLock extends ZooKeeperLock{
	
	private ReentrantLock reentrantLock = new ReentrantLock();
	
	/**
     * 创建分布式锁,使用前请确认config配置的zookeeper服务可用
     *
     * @param server   127.0.0.1:2181
     * @param lockName 竞争资源标志,lockName中不能包含单词lock
     */
    public ZookpeeperReentrantLock(String server, String lockName) {
        super(server, lockName);
    }
 
    public void lock() {
        reentrantLock.lock();//多线程竞争时，先拿到第一层锁
        super.lock();
    }
 
    public void unlock() {
        super.unlock();
        reentrantLock.unlock();//多线程竞争时，释放最外层锁
    }
}
