package com.toney.istyle.core.service.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.bo.ProductPicBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductPicQueryService;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductPicQueryServiceImpl.java
 * @DESCRIPTION : 商品图片查询
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductPicQueryServiceImpl implements ProductPicQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductPicQueryServiceImpl.class);
	
	@Autowired
	ProductPicRepository productPicRepository;
	
	/**
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public ProductPicBO getProductPicById(Long id) throws ServiceException{
		return this.productPicRepository.getProductPicById(id);
	}

}
