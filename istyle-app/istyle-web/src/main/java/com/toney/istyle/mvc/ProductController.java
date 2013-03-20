package com.toney.istyle.mvc;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.form.SearchForm;
import com.toney.istyle.form.SortForm;

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
	
	@RequestMapping("list")
	public String list(Integer page,Integer pageSize,String catCode) throws Exception {
		this.productManager.getProductInfoByCatCode(page, pageSize, catCode);
		return null;
	}
	
	@RequestMapping("search")
	public String search(Integer page,Integer pageSize,SearchForm searchFom,SortForm sortForm) throws Exception{
		return null;
	}
}
