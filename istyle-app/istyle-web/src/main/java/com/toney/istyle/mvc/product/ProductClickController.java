/**
 * 
 */
package com.toney.istyle.mvc.product;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductClickController.java
 * @DESCRIPTION :商品点击数据
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-22
 *       </p>
 **************************************************************** 
 */
@Controller
public class ProductClickController {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductClickController.class);
	
	@RequestMapping(value="like",method=RequestMethod.POST)
	public String clickLike(Long productId) {
		
		return "";
	}
	
	@RequestMapping(value="browse",method=RequestMethod.POST)
	public String browse(Long productId){
		return null;
	}
	@RequestMapping(value="recommend",method=RequestMethod.POST)
	public String recommend(Long productId){
		return null;
	}
}
