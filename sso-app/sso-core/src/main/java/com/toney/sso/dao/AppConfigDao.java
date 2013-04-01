package com.toney.sso.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.sso.module.AppConfigModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AppConfigDao.java
 * @DESCRIPTION : 系统配置Dao
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 18, 2013
 *       </p>
 **************************************************************** 
 */
@Repository("appConfigDao")
public interface AppConfigDao {

	public List<AppConfigModule> selectAll();

	public void deleteById(Long id);

	public void insert(AppConfigModule module);
	
}
