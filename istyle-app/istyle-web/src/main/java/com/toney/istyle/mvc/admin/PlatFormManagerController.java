package com.toney.istyle.mvc.admin;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;

/**
 *************************************************************** 
 * <p>
 * @CLASS :PlatFormManagerController.java
 * @DESCRIPTION : 平台商 管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
@AuthRequired(AuthLevel.ADMIN)
@Controller
@RequestMapping("/admin-manager/platform")
public class PlatFormManagerController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlatFormManagerController.class);
	@Autowired
	PlatFormManager platFormManager;
	
}
