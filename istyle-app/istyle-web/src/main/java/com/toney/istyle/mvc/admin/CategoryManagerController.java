package com.toney.istyle.mvc.admin;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.istyle.core.biz.CategoryManager;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryManagerController.java
 * @DESCRIPTION : 类目管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 28, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired(AuthLevel.ADMIN)
@RequestMapping("/admin-manager/category")
public class CategoryManagerController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CategoryManagerController.class);
	
	@Autowired
	CategoryManager categoryManager;
	
}
