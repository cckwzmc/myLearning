package com.toney.istyle.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaDao.java
 * @DESCRIPTION : generate  key  id .
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Repository
public interface KeyTableDao {
	public Long getKeyValue(String keyName);
	public void  updateKeyValue(@Param("keyName")String keyName,@Param("keyValue")Long keyValue);
}
