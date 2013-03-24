package com.toney.istyle.login;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :LoginController.java
 * @DESCRIPTION : 登录
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
public class LoginController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(LoginController.class);
	
	
	public String login(String userName,String password,String serviceUrl,Model model) {
		return "";
	}

}
