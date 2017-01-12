package com.fyrj.framework.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/***
 * spring快捷工具类
 * @author caiying
 * @date 2016-5-8 下午11:00:06
 * @version 1.0.0
 */
@Component
@Lazy(false)
public class SpringUtils implements ApplicationContextAware{
	
	private static ApplicationContext ctx = null;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}
}
