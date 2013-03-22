/**
 * 
 */
package com.toney.istyle.core.product;

import java.util.List;

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
public interface ProductQueryServcie {

	/**
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	ProductBO getProductById(Long id) throws ServiceException;

	/**
	 * 按查询条件查询结果.
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<ProductBO> getProductList() throws ServiceException;

	/**
	 * 按类目分页查询.
	 * 
	 * @param page
	 * @param pageSize
	 * @param catCode
	 * @return
	 * @throws ServiceException
	 */
	List<ProductBO> getProductPage(Integer page, Integer pageSize, String catCode) throws ServiceException;

	/**
	 * 多个Id查询
	 * 
	 * @return
	 * @throws ServiceException
	 */
	List<ProductBO> getProductIdList(List<Long> ids) throws ServiceException;

}
