package com.fyrj.parent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * sso登录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sso/*")
public class LoginController {
	
	@RequestMapping("login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("logout")
	public void logout(){
		
	}
}
