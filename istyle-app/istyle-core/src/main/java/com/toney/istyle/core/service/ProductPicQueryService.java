package com.toney.istyle.core.service;

import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductPicQueryService.java
 * @DESCRIPTION : 商品图片
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public interface ProductPicQueryService {


	ProductPicBO getProductPicById(Long id) throws ServiceException;

}
