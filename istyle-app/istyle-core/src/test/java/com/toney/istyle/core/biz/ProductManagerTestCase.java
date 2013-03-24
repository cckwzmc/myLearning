package com.toney.istyle.core.biz;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.commons.Page;
import com.toney.istyle.core.BaseManagerTestCase;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.form.ProductForm;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :ProductManagerTestCase.java
 * @DESCRIPTION : 商品测试.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 20, 2013
 *       </p>
 **************************************************************** 
 */
public class ProductManagerTestCase extends BaseManagerTestCase {

	private static final String PRODUCT_CACHE = "productCache";
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(ProductManagerTestCase.class);
	@Autowired
	ProductManager productManager;

	@Test
	public void testGetProductById() {
		ProductForm bo = null;
		try {
			long start = System.currentTimeMillis();
			Long a = 1L;
			for (int i = 0; i < 10000; i++) {
				bo = this.productManager.getProductInfoById(a);
			}
			super.cacheManagerInfo(PRODUCT_CACHE);
			System.out.println("~~~~~~~~~~~~" + (System.currentTimeMillis() - start) + "~~~~~~~~~~~~~");
		} catch (ManagerException e) {
			LOGGER.error("", e);
		}
		Assert.assertNotNull(bo);
	}

	@Test
	public void testGetProductInfoByCatCode() {
		Page<ProductForm> page = null;
		try {
			for (int i = 0; i < 10000; i++) {
				page = this.productManager.getProductInfoByCatCode(1, "01");
			}

		} catch (ManagerException e) {
			LOGGER.error("查询失败", e);
		}
		super.cacheManagerInfo(PRODUCT_CACHE);
		Assert.assertNotNull(page);
	}
}
