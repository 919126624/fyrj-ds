package com.fyrj.framework.mq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * mq消息处理者
 * @author Administrator
 *
 * @param <T>
 */
public abstract class MqMessageHandler<T> {
	private Logger logger = LoggerFactory.getLogger(MqMessageHandler.class);
	protected void beforeMethod(){
		logger.info("消息接受处理中。。。");
	}	
	
	protected void onMessage(MqMessageDto<T> mData) {
		beforeMethod();
		onProcess(mData);
		afterMethod();
	}
	
	protected void afterMethod(){
		logger.info("消息接受处理完毕。。。");
	}	
	protected abstract void onProcess(MqMessageDto<T> mData);
	
	
}
