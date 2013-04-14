package com.toney.istyle.core.system;

import com.toney.istyle.core.exception.RespositoryException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :KeyTableRespository.java
 * @DESCRIPTION : key table respository
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 14, 2013
 *       </p>
 **************************************************************** 
 */
public interface KeyTableRespository {
	public Long getValue(String keyName) throws RespositoryException;
}
