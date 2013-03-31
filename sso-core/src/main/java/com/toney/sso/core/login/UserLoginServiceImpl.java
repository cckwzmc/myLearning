package com.toney.sso.core.login;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.sso.commons.SSOConstants;
import com.toney.sso.core.bo.UserBO;
import com.toney.sso.core.bo.UserLoginedBO;
import com.toney.sso.core.constants.Constants;
import com.toney.sso.core.exception.RespositoryException;
import com.toney.sso.core.exception.ServiceException;
import com.toney.sso.core.util.SSOUtil;
import com.toney.sso.core.util.TokenIdUtil;
import com.toney.sso.dto.LoginDTO;
import com.toney.sso.dto.UserDTO;

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
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.istyle.core.service.UserLoginService#isUserLogined(java.lang
	 * .String)
	 */
	@Override
	public UserLoginedBO checkUserLogined(String userName, String tokenId, String ip, String loginType) throws ServiceException {
		try {
			UserLoginedBO userBo = this.userLoginRespository.userLoginedInfo(userName, loginType);
			if (userBo != null) {
				long loginLiveTime = System.currentTimeMillis() - userBo.getLastSessionDate();
				// 超时
				if (loginLiveTime > Constants.SESSION_LIVE_TIME) {
					return null;
				}
				userBo.setLastSessionDate(System.currentTimeMillis());
				this.userLoginRespository.refreshLogined(userName, loginType);
				return userBo;
			}
			return null;
		} catch (RespositoryException e) {
			LOGGER.error("根据用户名查询用户是否已登录 ， userName:{}", userName, e);
			throw new ServiceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.toney.sso.core.login.UserLoginService#login(com.toney.sso.dto.LoginDTO
	 * )
	 */
	@Override
	public UserDTO login(LoginDTO loginDTO) throws ServiceException {
		String password = SSOUtil.encodePassword(loginDTO.getPassword());
		if (SSOConstants.EMAIL_LOGIN.equals(loginDTO.getLoginType())) {
			try {
				UserBO userBO = this.userLoginRespository.getLoginUser(loginDTO.getUserName(), password);
				userBO.setIp(loginDTO.getIp());
				UserLoginedBO bo=wrapLoginBO(userBO);
				this.userLoginRespository.putLoginedInfoCache(bo, loginDTO.getLoginType());
			} catch (RespositoryException e) {
				LOGGER.error("用户登录异常", e);
				throw new ServiceException(e);
			}
		} else {
			// TODO 暂时没有手机登录.
		}
		return null;
	}

	private UserLoginedBO wrapLoginBO(UserBO userBO) {
		UserLoginedBO bo = new UserLoginedBO();
		String tokenId=TokenIdUtil.generateTokenId(userBO.getUserName(), userBO.getIp(), userBO.getIp(), Constants.LOGIN_RANDOM_ENCODE, bo.getLoginDate(), null);
		return bo;
	}
}
