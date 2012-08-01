package com.toney.mvc.web.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.mvc.web.utils.CookieStoreUtil;

/**
 * @author toney.li
 * 系统管理首页
 */
@Controller
@RequestMapping("/sys")
public class SysLoginContorller {
	
	@RequestMapping(value="login")
	public String index(){
		CookieStoreUtil.clearUserCookie();
		return "sys/login";
	}
}
