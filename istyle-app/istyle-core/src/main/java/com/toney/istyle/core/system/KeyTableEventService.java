package com.toney.istyle.core.system;

import com.toney.istyle.core.exception.ServiceException;

/**
 *************************************************************** 
 * <p>
 * @CLASS :KeyTableEventService.java
 * @DESCRIPTION : KeyTable Event Service
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 15, 2013
 *       </p>
 **************************************************************** 
 */
public interface KeyTableEventService {
	/**
	 * udpate keytable value's value .
	 * @param keyName
	 * @param keyValue
	 * @throws ServiceException
	 */
	public void updateKeyValue(String keyName,Long keyValue) throws ServiceException;
}
