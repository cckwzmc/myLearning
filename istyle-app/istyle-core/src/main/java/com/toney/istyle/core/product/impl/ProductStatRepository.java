package com.toney.istyle.core.product.impl;

import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatCacheService.java
 * @DESCRIPTION : 商品统计Repository层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
interface ProductStatRepository {

	ProductClickBO getProductClickById(Long id) throws ServiceException;

	void deleteCache(Long id) throws ServiceException;

}
