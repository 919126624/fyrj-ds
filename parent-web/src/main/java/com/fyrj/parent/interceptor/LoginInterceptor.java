package com.fyrj.parent.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fyrj.framework.utils.RedisUtil;

/***
 * sso登录拦截
 * @author Administrator
 *
 */
@Controller
public class LoginInterceptor extends HandlerInterceptorAdapter{
	private RedisUtil redisUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("#######################login####################");
		return super.preHandle(request, response, handler);
	}
	
	
}
