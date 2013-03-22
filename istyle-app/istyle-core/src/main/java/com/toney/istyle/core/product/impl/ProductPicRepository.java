package com.toney.istyle.core.product.impl;

import java.util.List;

import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductPicRepository.java
 * @DESCRIPTION : 商品图片Repository
 * @AUTHOR :toney.li
 * @VERSION :v1.0		
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
interface ProductPicRepository {

	List<ProductPicBO> getProductPicById(Long id) throws ServiceException;

	void deleteCache(Long id) throws ServiceException;
}
