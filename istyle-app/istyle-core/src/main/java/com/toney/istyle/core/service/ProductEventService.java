/**
 * 
 */
package com.toney.istyle.core.service;

import java.util.List;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductWriterService.java
 * @DESCRIPTION :商品信息写业务层
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
public interface ProductEventService {

	/**
	 * 保存商品信息.
	 * 
	 * @param productModule
	 * @throws ServiceException
	 */
	void save(ProductBO productBo) throws ServiceException;

	/**
	 * 删除商品信息.
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	void delete(Long id) throws ServiceException;

	/**
	 * 更新商品信息,这时需要更新缓存.
	 * 
	 * @param productBo
	 * @throws ServiceException
	 */
	void update(ProductBO productBo) throws ServiceException;

	/**
	 * 批量删除商品信息.
	 * 
	 * @param ids
	 * @throws ServiceException
	 */
	void delete(List<Long> ids) throws ServiceException;

}
