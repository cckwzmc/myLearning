package com.toney.istyle.core.platform;

import java.util.List;

import com.toney.istyle.bo.PlatFormBO;
import com.toney.istyle.core.exception.ServiceException;

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

	List<PlatFormBO> getPlatFormAll() throws ServiceException;
	
	PlatFormBO getPlatFormById(Long id) throws ServiceException;
}
