/**
 * 
 */
package com.toney.istyle.core.service;

import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatEventService.java
 * @DESCRIPTION :商品统计数据事件操作
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-20
 *       </p>
 **************************************************************** 
 */
public interface ProductStatEventService {

	/**
	 * @param productClickBo
	 * @throws ServiceException
	 */
	void create(ProductClickBO productClickBo) throws ServiceException;

	/**
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Long id) throws ServiceException;

	/**
	 * @param productClickBo
	 * @throws ServiceException
	 */
	void update(ProductClickBO productClickBo) throws ServiceException;

}
