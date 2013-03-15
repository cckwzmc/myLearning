package com.toney.istyle.biz.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Service;

import com.toney.istyle.biz.ProductManager;
import com.toney.istyle.commons.Page;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductManagerImpl
 * @DESCRIPTION :商品管理
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Service("productManager")
public class ProductManagerImpl implements ProductManager {

	private static final XLogger logger=XLoggerFactory.getXLogger(ProductManagerImpl.class);

	public Page<ProductForm> get
}
