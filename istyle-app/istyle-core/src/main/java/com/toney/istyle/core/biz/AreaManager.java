package com.toney.istyle.core.biz;

import java.util.List;
import java.util.Map;

import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.module.AreaModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AreaService.java
 * @DESCRIPTION : 地市服务层
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
public interface AreaManager {

	List<AreaModule> getAreaList() throws ManagerException;

	Map<Integer, AreaModule> getAreaMap() throws ManagerException;

	List<AreaModule> getTopArea() throws ManagerException;

	AreaModule getAreaById(Integer cityId) throws ManagerException;

	List<AreaModule> getChildrenArea(Integer id) throws ManagerException;

}
