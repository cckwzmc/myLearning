/**
 * 
 */
package com.toney.istyle.core.product.impl;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductReadServcie.java
 * @DESCRIPTION :商品服务服务表
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
interface ProductRepository {

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	ProductBO getProductById(Long id) throws ServiceException;

	/**
	 * 删除缓存中商品ID 为@id
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void deleteCache(Long id) throws ServiceException;

}
