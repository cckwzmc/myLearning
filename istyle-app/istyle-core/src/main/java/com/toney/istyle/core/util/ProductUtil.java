package com.toney.istyle.core.util;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.util.Assert;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductUtil.java
 * @DESCRIPTION : 商品工具类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 7, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductUtil {
	
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductUtil.class);
	private static final int PRODUCT_ID_MAXLENTH_10 = 10;

	public static String genProductCode(String platFormCode,Long productId){
		Assert.isNull(platFormCode);
		Assert.isNull(productId);
		String productIdStr=String.valueOf(productId);
		if(productIdStr.length()<PRODUCT_ID_MAXLENTH_10){
			while(PRODUCT_ID_MAXLENTH_10-productIdStr.length()>=0){
				productIdStr="0"+productIdStr;
			}
		}
		return "P"+platFormCode+"C"+productIdStr;
	}
}
