package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.ProductModule;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :productDao
 * @DESCRIPTION :商品信息表
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 11, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("productDao")
public interface ProductDao {
	void deleteById(Long id);

	void deleteByIds(List<Long> idList);

	int insert(ProductModule module);

	List<ProductModule> selectByCondiction(ProductModule module);

	ProductModule selectById(Long id);
	ProductModule selectByIds(List<Long> id);

	/**
	 * 根据条件查询商品数量
	 * @param object
	 */
	Integer  countByCondiction(ProductModule module);

}