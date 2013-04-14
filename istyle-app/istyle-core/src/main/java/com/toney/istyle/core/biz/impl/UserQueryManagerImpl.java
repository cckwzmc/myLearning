package com.toney.istyle.core.biz.impl;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.istyle.core.biz.UserQueryManager;
import com.toney.istyle.core.hessian.UucQueryService;
import com.toney.istyle.core.hessian.system.UucAdminQueryService;
import com.toney.sso.core.adminservice.UucAdminService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserQueryManagerImpl.java
 * @DESCRIPTION : User Query Manager Impl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userQueryManager")
public class UserQueryManagerImpl implements UserQueryManager {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserQueryManagerImpl.class);

	@Autowired
	UucQueryService uucQueryService;

	@Autowired
	UucAdminQueryService uucAdminQueryService;

	@Override
	public UserDTO getUserDTOById(Long userId) {
		return this.uucQueryService.getUserDTOById(userId);
	}

	private List<UserDTO> getUserDTOByUserType(Integer userType) {
		return uucAdminQueryService.getUserDTOByUserType(userType);
	}

	@Override
	public List<UserDTO> getUserDTORepertoire() {
		return this.getUserDTOByUserType(UucAdminService.REG_TYPE_0);
	}
}
