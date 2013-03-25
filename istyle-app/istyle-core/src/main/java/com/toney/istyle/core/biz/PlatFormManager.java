package com.toney.istyle.core.biz;

import java.util.List;

import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.form.PlatFormForm;

/**
 *************************************************************** 
 * <p>
 * @CLASS :PlatFormManager.java
 * @DESCRIPTION : 平台商 business
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
public interface PlatFormManager {

	List<PlatFormForm> getPlatFormAll() throws ManagerException;
	
	PlatFormForm getPlatFormById(Long id) throws ManagerException;
	
}
