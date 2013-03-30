package com.toney.istyle.core.platform;

import java.util.List;

import com.toney.istyle.core.exception.RespositoryException;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :PlatFormRespository.java
 * @DESCRIPTION : PlatForm Respository
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
public interface PlatFormRespository {

	List<PlatformModule> getPlatFormAll() throws RespositoryException;

	PlatformModule getPlatFormById(Long id) throws RespositoryException;

}
