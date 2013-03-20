package com.toney.istyle.core.biz;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.commons.Page;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.form.ProductForm;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductManager
 * @DESCRIPTION :商品管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
public interface ProductManager {

	/**
	 * 根据分类查询商品
	 * 
	 * @param page
	 *            查询页数
	 * @param pageSize
	 *            分页大小
	 * @param catCode
	 *            分类编码
	 * @return
	 * @throws ManagerException
	 */
	Page<ProductForm> getProductInfoByCatCode(Integer page, Integer pageSize, String catCode) throws ManagerException;

	/**
	 * 根据商品ID查询商品明细.
	 * 
	 * @param id
	 * @return
	 * @throws ManagerException
	 */
	ProductForm getProductInfoById(Long id) throws ManagerException;

}
