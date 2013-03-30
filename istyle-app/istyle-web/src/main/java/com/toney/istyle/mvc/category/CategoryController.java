package com.toney.istyle.mvc.category;

import java.util.List;

import org.codehaus.jackson.map.util.JSONWrappedObject;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.constants.ErrConstants;
import com.toney.istyle.core.biz.CategoryManager;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.CategoryModule;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.istyle.mvc.constants.Constants;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CategoryController.java
 * @DESCRIPTION : Category Controller
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 29, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@AuthRequired(AuthLevel.NONE)
@RequestMapping("/category")
public class CategoryController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CategoryController.class);
	@Autowired
	CategoryManager categoryManager;

	@RequestMapping(value = "topCategory", method = RequestMethod.GET, produces = "application/json", params = "format=json")
	public String getTopCategory(Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			List<CategoryModule> catList = categoryManager.getTopCatgoryAll();
			json.setData(catList);
		} catch (ServiceException e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setErrorCode(ErrConstants.GENERAL_ERR_CODE);
			json.setSmsg(ErrConstants.GENERAL_COMM_ERR_MSG);
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return "";
	}

	@RequestMapping(value = "childrenCategory", method = RequestMethod.GET, produces = "application/json", params = "format=json")
	public String getChildrenCategory(Integer id, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			List<CategoryModule> catList = categoryManager.getChildrensCatgoryById(id);
			json.setData(catList);
		} catch (ServiceException e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setErrorCode(ErrConstants.GENERAL_ERR_CODE);
			json.setSmsg(ErrConstants.GENERAL_COMM_ERR_MSG);
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return "";
	}
}
