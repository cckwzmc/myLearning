package com.toney.istyle.core.system;

import java.util.List;
import java.util.Map;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AreaQueryService.java
 * @DESCRIPTION : Area query service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
public interface AreaQueryService {


	Map<Integer, AreaBO> getAreaMapAll() throws ServiceException;

	List<AreaBO> getAreaListAll() throws ServiceException;

	AreaBO getAreaById(Integer cityId) throws ServiceException;

}
