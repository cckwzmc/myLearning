package com.toney.istyle.core.biz;

import java.util.List;
import java.util.Map;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.ManagerException;

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

	List<AreaBO> getAreaList() throws ManagerException;

	Map<Integer, AreaBO> getAreaMap() throws ManagerException;

}
