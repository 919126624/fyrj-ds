package com.fyrj.framework.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.NamedThreadLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/***
 * 动态数据源
 * @author caiying
 * @version 1.0.0
 */
public class RoutingDataSource extends AbstractRoutingDataSource implements BeanPostProcessor{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoutingDataSource.class);
	/**当前持有key上下文*/
	private static final ThreadLocal<Object> currentLookupKey = new NamedThreadLocal<Object>("Current lookup key");
	/**主数据源key*/
	public static final String MASTER_DATASOURCE = "MASTER";
	/**从数据源key*/
	public static final String SLAVE_DATASOURCE = "SLAVE";
	
	@Override
	protected Object determineCurrentLookupKey() {
		return RoutingDataSource.currentLookupKey.get();
	}
	
	/***
	 * 通过spring Bean增强工具来为声明式事务拦截器创建代理对象
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (TransactionInterceptor.class.isInstance(bean)) {
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			Class[] MethodInterceptor = {MethodInterceptor.class};
			proxyFactoryBean.setInterfaces(MethodInterceptor);
			proxyFactoryBean.setTarget(bean);
			proxyFactoryBean.addAdvice(new TransactionInterceptorAdvice());
			proxyFactoryBean.setProxyTargetClass(true);
			return proxyFactoryBean.getObject();
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	/***
	 * 通过代理TransactionInterceptor来实现在事务执行切面invoke方法调用前先确定数据源key
	 * @author ying.cai
	 * @email 919126624@qq.com
	 * @version 1.0
	 */
	public class TransactionInterceptorAdvice implements MethodInterceptor {
		@Override
		
		public Object invoke(MethodInvocation invocation) throws Throwable {
			try {
				if (!TransactionSynchronizationManager.isSynchronizationActive()) {
					RoutingDataSource.currentLookupKey.set(MASTER_DATASOURCE);
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("当前进入主库 '{}'", MASTER_DATASOURCE);
					}
				}
				return invocation.proceed();
			} finally {
				RoutingDataSource.currentLookupKey.remove();
			}
		}
	}
}
