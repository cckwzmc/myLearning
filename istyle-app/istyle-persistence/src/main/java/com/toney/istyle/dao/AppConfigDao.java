package com.toney.istyle.dao;

import org.springframework.stereotype.Repository;

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

	public void selectAll();
	
}
