package com.toney.mvc.web.interceptor.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;

import com.toney.core.model.UserAuthInfo;
import com.toney.mvc.web.contants.Constants;
import com.toney.mvc.web.utils.CookieStoreUtil;
import com.toney.mvc.web.utils.IPUtil;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-8 上午7:00:24
 *       </p>
 **************************************************************** 
 */
@Component("dummyValidator")
public class DummyValidator implements AuthorizationValidator {
    protected final static XLogger LOGGER = XLoggerFactory.getXLogger(DummyValidator.class);

    @Override
    public boolean validate(HttpServletRequest request) {
        UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
        user.setClientIpAddress(IPUtil.getRequestIP(request));
        user.setSsoUserId(1000000351l);

        request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);

        LOGGER.warn("Authorizate as user: {}, use for development only!!! URL={}", user.getSsoUserId(),
                request.getRequestURI());

        return true;
    }

}
