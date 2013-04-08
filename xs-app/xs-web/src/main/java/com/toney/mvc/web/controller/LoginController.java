package com.toney.mvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Controller
 * 首页生成
 * @author toney.li
 *
 */
@Controller
@RequestMapping(value="/user")
public class LoginController {
	/**
	 * @return
	 */
	@RequestMapping(value="logined")
	public String login(){
		return "sys/main";
	}
}
