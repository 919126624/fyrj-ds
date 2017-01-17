package com.fyrj.cims.mq.service;

import org.springframework.stereotype.Component;

import com.fyrj.framework.mq.service.MqMessageDto;
import com.fyrj.framework.mq.service.MqMessageHandler;

@Component
public class MqDemoService extends MqMessageHandler<String>{
	
	@Override
	public void onProcess(MqMessageDto<String> mData) {
		System.out.println(mData);
	}

	public void direct_queue1(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>direct_queue1<<<<<<<<<<<<<<<");
	}
	
	public void direct_queue2(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>direct_queue2<<<<<<<<<<<<<<<");
	}
	
	public void topic_queue1(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>topic_queue1<<<<<<<<<<<<<<<");
	}
	
	public void topic_queue2(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>topic_queue2<<<<<<<<<<<<<<<");
	}
	
	public void fanout_queue1(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>fanout_queue1<<<<<<<<<<<<<<<");
	}
	
	public void fanout_queue2(MqMessageDto<String> mData) {
		System.out.println(">>>>>>>>>>>>>>>fanout_queue2<<<<<<<<<<<<<<<");
	}
	
	
}
