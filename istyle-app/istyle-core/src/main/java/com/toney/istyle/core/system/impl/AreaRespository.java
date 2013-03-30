package com.toney.istyle.core.system.impl;

import java.util.List;
import java.util.Map;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.module.AreaModule;

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
public interface AreaRespository {

	Map<Integer, AreaModule> getAreaMapAll() throws RespositoryException;

	List<AreaModule> getAreaListAll() throws RespositoryException;

}
