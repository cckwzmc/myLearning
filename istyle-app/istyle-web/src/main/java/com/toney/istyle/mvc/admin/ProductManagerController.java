package com.toney.istyle.mvc.admin;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.module.AreaModule;
import com.toney.istyle.module.PlatformModule;
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
@AuthRequired(AuthLevel.ADMIN)
@Controller
@RequestMapping("/admin-manager/product")
public class ProductManagerController {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductManagerController.class);

	@Autowired
	ProductManager productManager;
	@Autowired
	PlatFormManager platFormManager;

	@Autowired
	AreaManager areaManager;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list() {
		return "admin-manager/prd-manager/list";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		List<PlatformModule>  platFormList=platFormManager.getPlatFormAll();
		model.addAttribute("platFormList", platFormList);
		List<AreaModule> areaList=areaManager.getAreaList();		
		model.addAttribute("areaList", areaList);
		return "admin-manager/prd-manager/create";
	}

	@RequestMapping(value = "doCreate", method = RequestMethod.POST)
	public String doCreate() {
		
		return "admin-manager/prd-manager/create";
	}
}
