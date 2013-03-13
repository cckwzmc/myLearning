package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.PlatformModule;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :IstylePlatformDao
 * @DESCRIPTION :平台商表
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 10, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("platformDao")
public interface PlatformDao {
    void deleteById(Integer id);
    /**
     * 根据平台商编码删除
     * @param pfCode
     * @return
     */
    void deleteByPfCode(String pfCode); 

    void insert(PlatformModule istylePlatform);

    List<PlatformModule> selectAll();
    PlatformModule selectById(Integer id);
    PlatformModule selectByPfCode(String pfCode);
}