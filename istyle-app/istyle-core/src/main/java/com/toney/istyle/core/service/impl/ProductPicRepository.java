package com.toney.istyle.core.service.impl;

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

	ProductPicBO getProductPicById(Long id) throws ServiceException;

	void deleteCache(Long id) throws ServiceException;
}
