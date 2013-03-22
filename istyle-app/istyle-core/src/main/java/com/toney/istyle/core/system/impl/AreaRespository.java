package com.toney.istyle.core.system.impl;

import java.util.List;
import java.util.Map;

import com.toney.istyle.bo.AreaBO;
import com.toney.istyle.core.exception.RespositoryException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :AreaRespository.java
 * @DESCRIPTION : Area Respository
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 22, 2013
 *       </p>
 **************************************************************** 
 */
interface AreaRespository {

	Map<Integer, AreaBO> getAreaMapAll() throws RespositoryException;

	List<AreaBO> getAreaListAll() throws RespositoryException;

	void refresh() throws RespositoryException;

}
