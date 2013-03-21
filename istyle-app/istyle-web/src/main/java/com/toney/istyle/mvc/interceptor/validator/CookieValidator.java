package com.toney.istyle.mvc.interceptor.validator;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toney.istyle.bo.UserLoginedBO;
import com.toney.istyle.constants.Constants;
import com.toney.istyle.core.biz.UserLoginManager;
import com.toney.istyle.form.UserAuthInfo;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.util.CookieStoreUtil;

import freemarker.template.utility.DateUtil;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :用户登录 Cookie 校验方式。
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 下午12:28:02
 *       </p>
 **************************************************************** 
 */
@Component("cookieValidator")
public class CookieValidator implements AuthorizationValidator {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(CookieValidator.class);
	@Autowired
	private UserLoginManager userLoginManager;

	@Override
	public boolean validate(HttpServletRequest request, AuthLevel level) {
		if (AuthLevel.NONE == level) {
			return true;
		}
		UserAuthInfo user=null;
		// 1. 构造UserAuthInfo对象，以便后续的业务使用 TODO
//		UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
//		if (user != null) {
//			user.setClientIpAddress(IPUtil.getRequestIP(request));
//		}

		// 2. 检查是否有tokenId TODO
//		if ((user == null) || (StringUtils.isBlank(user.getSsoTokenId())) || (user.getSsoUserId() == null)) {
//			return false;
//		}

		// 3. 检查Cookies上记录数据的安全性 TODO
//		if (!CookieStoreUtil.checkDataIntegrityOfUser(request)) {
//			return false;
//		}

		// 4. 到SSO服务器检测tokenId是否有效
		if ((AuthLevel.STRICT == level) && !checkLoginedStatus(user)) {
			return false;
		}

		// 5. 构造UserAuthInfo对象，以便后续的业务使用
		request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);
		return true;
	}

	/**
	 * @param request
	 * @Title: checkSsoStatus
	 * @Description: SSO权限认证
	 * @param @param ssoTokenId
	 * @param @param ip
	 * @param @param userId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	private boolean checkLoginedStatus(UserAuthInfo user) {
		try {
			UserLoginedBO userLoginedBo = this.userLoginManager.checkLoginedState(user.getTokenId(), user.getUserId(), user.getUserName());
			if (userLoginedBo != null && userLoginedBo.getUserBo().getId() != null && userLoginedBo.getUserBo().getId().longValue() == user.getUserId().longValue()) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("用户认证失败,tokenId={},ip={},userId={},userName={},exception={}", new Object[] { user.getTokenId(), user.getUserId(), user.getUserName(), e });
			LOGGER.error("用户认证失败", e);
		}
		return false;
	}
}
