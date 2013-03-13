package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.ProductPicModule;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :IstylePrdPictureDao
 * @DESCRIPTION :商品图片表
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("productPicDao")
public interface ProductPicDao {
    
	void deleteById(Long id);
	void deleteByIds(List<Long> idList);
	/**
	 * 根据商品ID删除
	 * @param pid
	 */
	void deleteByPId(Long pid);
	void deleteByPIds(List<Long> pidList);

   void insert(ProductPicModule module);

   ProductPicModule selectById(Long id);
   
    List<ProductPicModule> selectByPid(Long pid);

    List<ProductPicModule> selectByCondition(ProductPicModule module);
}