package com.toney.istyle.core.hessian;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.Result;
import com.toney.sso.core.service.SsoService;
import com.toney.sso.dto.RegisterDTO;
import com.toney.sso.dto.UserDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :SsoClientServiceImpl.java
 * @DESCRIPTION : Sso hessian Client Service Impl
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Apr 3, 2013
 *       </p>
 **************************************************************** 
 */
@Service("ssoClientService")
public class SsoClientServiceImpl implements SsoClientService {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(SsoClientServiceImpl.class);

	@Autowired
	private SsoService ssoFacadeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.toney.istyle.core.hessian.SsoClientService#register(com.toney.sso.dto.RegisterDTO)
	 */
	@Override
	public UserDTO register(RegisterDTO regDTO) {
		Result<UserDTO> result = this.ssoFacadeService.register(regDTO);
		if (result.getIsSuccess() && CollectionUtils.isNotEmpty(result.getResult())) {
			return result.getResult().get(0);
		}
		return null;
	}
}
