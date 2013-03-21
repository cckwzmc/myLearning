/**
 * 
 */
package com.toney.istyle.core.service.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.bo.ProductClickBO;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.service.ProductStatQueryService;
import com.toney.istyle.dao.ProductClickDao;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductStatQueryServiceImpl.java
 * @DESCRIPTION :TODO
 * @AUTHOR :ly_zy_ljh
 * @VERSION :v1.0
 * @DATE :2013-3-19
 *       </p>
 **************************************************************** 
 */
@Service("productStatQueryService")
public class ProductStatQueryServiceImpl implements ProductStatQueryService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductStatQueryServiceImpl.class);

	@Autowired
	private ProductClickDao productClickDao;

	@Autowired
	private ProductStatRepository productStatRepository;

	@Override
	public ProductClickBO getProductClickById(Long id) throws ServiceException {
		return this.productStatRepository.getProductClickById(id);
	}

}
