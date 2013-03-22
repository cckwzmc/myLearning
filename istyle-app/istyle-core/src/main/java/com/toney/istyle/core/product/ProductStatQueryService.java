/**
 * 
 */
package com.toney.istyle.core.product;

import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatQueryService.java
 * @DESCRIPTION :商品的浏览量 等统计数据.
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
public interface ProductStatQueryService {

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	ProductClickBO getProductClickById(Long id) throws ServiceException;

}
