package com.toney.sso.core.login;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.bo.UserLoginedBO;
import com.toney.sso.core.exception.RespositoryException;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.util.SSOUtil;
import com.toney.sso.core.util.TokenIdUtil;
import com.toney.sso.dto.LoginDTO;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :UserLoginServiceImpl.java
 * @DESCRIPTION : EhCache实现Session持久化
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {
	private static final XLogger LOGGER = XLoggerFactory.getXLogger(UserLoginServiceImpl.class);
	@Autowired
	private UserLoginRespository userLoginRespository;

	/*
	 *  (non-Javadoc)
	 * @see com.toney.istyle.core.service.UserLoginService#isUserLogined(java.lang.String)
	 */
	@Override
	public UserLoginedBO checkUserLogined(String userName,String tokenId,String ip) throws ServiceException {
		try {
			UserLoginedBO userBo = this.userLoginRespository.userLoginedInfo(userName);
			if (userBo != null) {
				this.userLoginRespository.refreshLogined(userName);
				return true;
			}
		} catch (RespositoryException e) {
			LOGGER.error("根据用户名查询用户是否已登录 ， userName:{}", userName, e);
			throw new ServiceException(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.toney.sso.core.login.UserLoginService#login(com.toney.sso.dto.LoginDTO)
	 */
	@Override
	public UserBO login(LoginDTO loginDTO) throws ServiceException {
		String password=SSOUtil.encodePassword(loginDTO.getPassword());
		if(SSOConstants.MOBILE_LOGIN.equals(loginDTO.getLoginType())){
			try {
				UserBO userBO=this.userLoginRespository.getLoginUser(loginDTO.getUserName(), password);
				userBO.setIp(loginDTO.getIp());
				UserLoginedBO bo=new UserLoginedBO();
				TokenIdUtil.generateTokenId(userBO.getUserName(),userBO.getIp(),userBO.getIp(),bo.getLoginDate(),SSOConstants)
				this.userLoginRespository.putLoginedInfoCache(bo);
			} catch (RespositoryException e) {
				LOGGER.error("用户登录异常", e);
				throw new ServiceException(e);
			}
		}else{
			
		}
		return null;
	}
}
