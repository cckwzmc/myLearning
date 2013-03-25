package com.toney.istyle.core.platform;

import java.util.List;

import com.toney.istyle.bo.PlatFormBO;
import com.toney.istyle.core.exception.RespositoryException;

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

	List<PlatFormBO> getPlatFormAll() throws RespositoryException;

	PlatFormBO getPlatFormById(Long id) throws RespositoryException;

}
