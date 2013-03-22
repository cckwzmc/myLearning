package com.toney.istyle.core.product;

import java.util.List;

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
	

	/**
	 * 读取商品Id的所有图片.
	 * @param pid
	 * @return
	 * @throws ServiceException
	 */
	List<ProductPicBO> getProductPicById(Long pid) throws ServiceException;

	/**
	 * 读取主图.
	 * @param pid
	 * @return
	 * @throws ServiceException
	 */
	ProductPicBO getProductMasterPic(Long pid) throws ServiceException;

}
