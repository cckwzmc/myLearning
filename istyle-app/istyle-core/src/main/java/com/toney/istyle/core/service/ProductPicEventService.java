package com.toney.istyle.core.service;

import java.util.List;

import com.toney.istyle.bo.ProductBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :ProductPicEventService.java
 * @DESCRIPTION : 图片写操作事件
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public interface ProductPicEventService {

	void create(ProductBO productBo) throws ServiceException;

	void delete(Long id) throws ServiceException;

	void delete(List<Long> ids) throws ServiceException;

}
