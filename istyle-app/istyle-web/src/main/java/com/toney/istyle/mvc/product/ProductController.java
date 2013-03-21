package com.toney.istyle.mvc.product;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.istyle.commons.Page;
import com.toney.istyle.constants.Constants;
import com.toney.istyle.constants.ErrConstants;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.form.ProductForm;
import com.toney.istyle.form.SearchForm;
import com.toney.istyle.form.SortForm;
import com.toney.istyle.util.JsonPackageWrapper;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductController.java
 * @DESCRIPTION : 商品控制层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
@Controller
public class ProductController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductController.class);

	@Autowired
	ProductManager productManager;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Integer pageNo, String catCode, Model model){
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			Page<ProductForm> page;
			page = this.productManager.getProductInfoByCatCode(pageNo, catCode);
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("productList", page.getResult());
			map.put("pageNo", page.getPageNo());
			json.setData(map);
		} catch (Exception e) {
			json.setScode(JsonPackageWrapper.S_ERR);
			json.setSmsg(e.getMessage());
			json.setErrorCode(ErrConstants.GERNERAL_ERR_CODE);
			LOGGER.error("",e);
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return "";
	}

	@RequestMapping(value="search",method=RequestMethod.GET)
	public String search(Integer page, Integer pageSize, SearchForm searchFom, SortForm sortForm) throws Exception {
		return null;
	}
}
