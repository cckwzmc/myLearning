package com.toney.istyle.core.hessian;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.Result;
import com.toney.sso.core.service.UucService;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UucQueryServiceImpl.java
 * @DESCRIPTION : Uuc Query Service Impl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
@Service("uucQueryService")
public class UucQueryServiceImpl implements UucQueryService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UucQueryServiceImpl.class);

	@Autowired
	private UucService uucFacadeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.hessian.UucQueryService#getUserDTOById(java.lang .Long)
	 */
	@Override
	public UserDTO getUserDTOById(Long userId) {
		UserDTO dto = new UserDTO();
		dto.setId(userId);
		Result<UserDTO> result = uucFacadeService.queryUserById(dto);
		if (result.getIsSuccess() && CollectionUtils.isNotEmpty(result.getResult())) {
			return result.getResult().get(0);
		}
		return null;
	}

	@Override
	public List<UserDTO> getUserDTOByUserType(Integer userType) {
		return null;
	}
}
