package com.toney.istyle.core.biz.impl;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.commons.Page;
import com.toney.istyle.core.biz.ProductManager;
import com.toney.istyle.dao.ProductDao;
import com.toney.istyle.form.ProductForm;

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
	
	@Autowired
	private ProductDao productDao;

	@Override
	public Page<ProductForm> getProductInfoByCatCode(Integer page,Integer pageSize,String catCode){
		Page<ProductForm> pageForm=new Page<ProductForm>();
		this.productDao.countByCondiction(null);
		//this.productDao.selectAllPage();
		
		return pageForm;
	}
	
	@Override
	public ProductForm getProductInfoById(Long id){
		ProductForm form =new ProductForm();
		return form;
	}
	
}
