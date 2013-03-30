package com.toney.istyle.core.platform;

import java.util.List;

import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.module.PlatformModule;

/**
 *************************************************************** 
 * <p>
 * @CLASS :PlatFormQueryService.java
 * @DESCRIPTION : 平台商查询接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 25, 2013
 *       </p>
 **************************************************************** 
 */
public interface PlatFormQueryService {

	List<PlatformModule> getPlatFormAll() throws ServiceException;
	
	PlatformModule getPlatFormById(Long id) throws ServiceException;
}
