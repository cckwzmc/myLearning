package com.toney.istyle.mvc.admin;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.core.biz.AreaManager;
import com.toney.istyle.core.biz.PlatFormManager;
import com.toney.istyle.core.biz.ProductDetailManager;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.core.biz.UserQueryManager;
import com.toney.istyle.form.ProductBaseForm;
import com.toney.istyle.module.AreaModule;
import com.toney.istyle.module.PlatformModule;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductDetailManagerController.java
 * @DESCRIPTION : 商品明细管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 4, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
@RequestMapping("/admin-manager/product")
@AuthRequired(AuthLevel.ADMIN)
public class ProductDetailManagerController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductDetailManagerController.class);
	@Autowired
	private PlatFormManager platFormManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private ProductManager productManager;
	@Autowired
	private UserQueryManager userQueryManager;
	@Autowired
	private ProductDetailManager productDetailManager;

	@RequestMapping(value = "baseInfo", method = RequestMethod.GET)
	public String baseInfo(ProductBaseForm productBaseForm,Model model) throws Exception {
		List<PlatformModule> platFormList = platFormManager.getPlatFormAll();
		model.addAttribute("platFormList", platFormList);
		List<UserDTO> userList=this.userQueryManager.getUserDTORepertoire();
		model.addAttribute("userList", userList);
		return "admin-manager/prd-manager/baseInfo";
	}

	@RequestMapping(value = "doBaseInfo", method = RequestMethod.POST)
	public String doBaseInfo(Model model,@Validated @ModelAttribute("productBaseForm") ProductBaseForm productBaseForm,Errors errors) throws Exception {
		if(errors.hasErrors()){
			return this.baseInfo(productBaseForm, model);
		}
		this.productDetailManager.;
		return "redirect:/admin-manager/product/baseInfo";
	}

	@RequestMapping(value = "picInfo", method = RequestMethod.GET)
	public String picInfo(Model model) throws Exception {
		List<PlatformModule> platFormList = platFormManager.getPlatFormAll();
		model.addAttribute("platFormList", platFormList);
		List<AreaModule> areaList = areaManager.getAreaList();
		model.addAttribute("areaList", areaList);
		return "admin-manager/prd-manager/picInfo";
	}

	@RequestMapping(value = "doPicInfo", method = RequestMethod.POST)
	public String doPicInfo() {

		return "admin-manager/prd-manager/picInfo";
	}

	@RequestMapping(value = "statInfo", method = RequestMethod.GET)
	public String statInfo(Model model) throws Exception {
		List<PlatformModule> platFormList = platFormManager.getPlatFormAll();
		model.addAttribute("platFormList", platFormList);
		List<AreaModule> areaList = areaManager.getAreaList();
		model.addAttribute("areaList", areaList);
		return "admin-manager/prd-manager/statInfo";
	}

	@RequestMapping(value = "doStatInfo", method = RequestMethod.POST)
	public String doStatInfo() {

		return "admin-manager/prd-manager/statInfo";
	}
}
