package com.toney.istyle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	
	List<ProductModule> selectByIds(List<Long> ids);

	/**
	 * 根据条件查询商品数量
	 * @param object
	 */
	Integer  countByCondiction(ProductModule module);

	/**
	 * 更新
	 * @param module
	 */
	void updateById(ProductModule module);

	/**
	 * 按类目分页查询
	 * @param startRecord
	 * @param pageSize
	 * @param catCode
	 */
	List<ProductModule> selectPage(@Param("startRecord") int startRecord, @Param("pageSize")Integer pageSize, @Param("catCode")String catCode);

	/**
	 * 按类目分页查询,只查询ID
	 * @param startRecord
	 * @param pageSize
	 * @param catCode
	 * @return
	 */
	List<Long> selectPageId(@Param("startRecord") int startRecord, @Param("pageSize")Integer pageSize, @Param("catCode")String catCode);

}