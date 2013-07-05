package com.toney.istyle.mvc.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.core.biz.CategoryManager;
import com.toney.istyle.module.CategoryModule;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.istyle.mvc.constants.Constants;

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

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(String parentCatCode, Model model) throws Exception {
		List<CategoryModule> pathList=this.categoryManager.getCategoryPathByChildrenId(parentCatCode);
		model.addAttribute("pathList", pathList);
		model.addAttribute("parentCatCode", parentCatCode);
		return "/admin-manager/cat-manager/cat-add";
	}

	@RequestMapping(value = "doCreate", method = RequestMethod.POST)
	public String doCreate(String parentCatCode,String catName, Model model) throws Exception {
		Map<String, String> successMap = new HashMap<String, String>();
		this.categoryManager.addCategory(parentCatCode, catName);
		successMap.put(Constants.SUCCESS_KEY, Constants.SUCCESS_MESSAGE);
		return "/admin-manager/cat-manager/cat-list";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(String catCode, Model model) throws Exception {
		List<CategoryModule> list = this.categoryManager.getCategoryAllByCatCode(catCode);
		model.addAttribute("catList", list);
		return "/admin-manager/cat-manager/cat-list";
	}
}
