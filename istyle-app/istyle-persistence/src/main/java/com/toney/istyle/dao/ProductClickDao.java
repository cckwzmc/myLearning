package com.toney.istyle.dao;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.ProductClickModule;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :ProductClickDao
 * @DESCRIPTION :商品相关的一些数字
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("productClickDao")
public interface ProductClickDao {

	void deleteById(Long id);
    void insert(ProductClickModule productClickModule);
    ProductClickModule selectById(Long id);
	/**
	 * @param productClickModule
	 */
	void updateById(ProductClickModule productClickModule);
    
}