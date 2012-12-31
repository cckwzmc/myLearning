package com.toney.web.commons.validator;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toney.core.model.UserAuthInfo;
import com.toney.web.commons.constants.AuthLevel;
import com.toney.web.commons.constants.Constants;
import com.toney.web.commons.utils.CookieStoreUtil;
import com.toney.web.commons.utils.IPUtil;

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

	private static final XLogger LOGGER=XLoggerFactory.getXLogger(CookieValidator.class);

	@Override
    public boolean validate(HttpServletRequest request, AuthLevel level) {
        if (AuthLevel.NONE == level) {
            return true;
        }

        // 1. 构造UserAuthInfo对象，以便后续的业务使用
        UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
        if (user != null) {
            user.setClientIpAddress(IPUtil.getRequestIP(request));
        }

        // 2. 检查是否有tokenId
        if ((user == null) || (StringUtils.isBlank(user.getSsoTokenId()))||(user.getSsoUserId()==null)) {
            return false;
        }

        // 3. 检查Cookies上记录数据的安全性
        if (!CookieStoreUtil.checkDataIntegrityOfUser(request)) {
            return false;
        }

        // 4. 到SSO服务器检测tokenId是否有效
        if ((AuthLevel.STRICT == level) && !checkSsoStatus(user)) {
            return false;
        }

        // 5. 构造UserAuthInfo对象，以便后续的业务使用
        request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);
        return true;
    }

    /**
      * 检查用户是否已经登录。 
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
    private boolean checkSsoStatus(UserAuthInfo user) {
    	try{
	    	 
    	}catch(Exception e){
    		LOGGER.error("用户认证失败,tokenId={},ip={},userId={},exception={}", new Object[]{user.getSsoTokenId(),user.getClientIpAddress(),user.getSsoUserId(),e});
    		LOGGER.error("用户认证失败", e);
    	}
    	return false;
    }
}
