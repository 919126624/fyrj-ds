package com.fyrj.framework.mq.service;

import java.io.Serializable;

/***
 * mq消息模型
 * @author Administrator
 *
 * @param <T>
 */
public class MqMessageDto<T> implements Serializable{

	private static final long serialVersionUID = -6262199486728287192L;

	private T data;//消息数据
	
	private String label;//消息发送方信息
	
	private String describe; //消息描述

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
}
