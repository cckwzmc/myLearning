package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.CategoryModule;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :CategoryDao
 * @DESCRIPTION :类目表持久化操作
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("categoryDao")
public interface CategoryDao {
    void deleteById(Integer id);
    /**
     * 根据类目编码删除
     * @param pfCode
     * @return
     */
    void deleteByCatCode(String catCode); 

    void insert(CategoryModule categoryModule);

    List<CategoryModule> selectAll();
    CategoryModule selectById(Integer id);
    CategoryModule selectByCatCode(String catCode);
}