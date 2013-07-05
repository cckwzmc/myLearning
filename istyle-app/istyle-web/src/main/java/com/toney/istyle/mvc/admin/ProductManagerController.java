package com.toney.istyle.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.commons.Page;
import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.form.ProductForm;
import com.toney.istyle.module.ProductModule;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductManagerController.java
 * @DESCRIPTION : 商品管理.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@RequestMapping("/admin-manager/product")
@AuthRequired(AuthLevel.ADMIN)
public class ProductManagerController {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductManagerController.class);

	@Autowired
	ProductManager productManager;
	@Autowired
	PlatFormManager platFormManager;

	@Autowired
	AreaManager areaManager;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("prdPage", null);
		return "admin-manager/prd-manager/list";
	}

	@RequestMapping(value = "searchProduct", method = RequestMethod.GET)
	public String searchProduct(String catCode, String cityId, Integer page, Model model) throws Exception {
		Map<String, String> errorMessageMap = new HashMap<String, String>();
		if (StringUtils.isBlank(catCode)) {
			errorMessageMap.put("CAT_CODE_IS_BLANK", "分类不能为空");
			model.addAttribute("errorMessage", errorMessageMap);
			return "admin-manager/prd-manager/list";
		}
		Page<ProductForm> prdPage = productManager.getProductInfoByCatCode(page, catCode);
		model.addAttribute("prdPage", prdPage);
		return "admin-manager/prd-manager/list";
	}

}
