package com.toney.istyle.mvc.admin;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AdminIndexController.java
 * @DESCRIPTION : 管理后台首页
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired(AuthLevel.ADMIN)
@RequestMapping("/admin-manager")
public class AdminIndexController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AdminIndexController.class);

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index() {
		
		return "admin-manager/index";
	}
}
