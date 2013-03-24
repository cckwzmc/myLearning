package com.toney.istyle.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AreaDao.java
 * @DESCRIPTION : 地市DAO
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
@Repository
public interface AreaDao {
	public List<AreaModule> selectAll();

	public void deleteById(Integer id);

	public void insert(AreaModule areaModule);

}
