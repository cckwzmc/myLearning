package com.toney.istyle.mvc.interceptor.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.toney.istyle.constants.Constants;
import com.toney.istyle.form.UserAuthInfo;
import com.toney.istyle.mvc.annotation.AuthLevel;

/**
 *************************************************************** 
 * <p>
 * @CLASS :DummyValidator.java
 * @DESCRIPTION : 用户认证测试 Dummy
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
@Component("dummyValidator")
public class DummyValidator implements AuthorizationValidator {
    protected final static XLogger LOGGER = XLoggerFactory.getXLogger(DummyValidator.class);

    @Override
    public boolean validate(HttpServletRequest request, AuthLevel level) {
        Assert.notNull(level);
 
        if (AuthLevel.NONE == level) {
            return true;
        }
 
//        UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
//        user.setClientIpAddress(IPUtil.getRequestIP(request));
//        user.setSsoUserId(1000000351l);
//        user.setSsoCusId(100000341l);
//        user.setUserName("dev_tester");

//        request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);

//        LOGGER.warn("Authorizate as user: {}, AuthLevel: {}, use for development only!!! URL={}", new Object[] {user.getSsoUserId(),
//                level, request.getRequestURI()});

        return true;
    }

}
