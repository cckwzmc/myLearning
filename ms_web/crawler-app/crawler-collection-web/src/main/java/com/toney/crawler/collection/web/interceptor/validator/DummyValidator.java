package com.toney.crawler.collection.web.interceptor.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.toney.crawler.collection.web.annotation.AuthLevel;
import com.toney.crawler.collection.web.contants.Constants;
import com.toney.crawler.collection.web.utils.CookieStoreUtil;
import com.toney.crawler.collection.web.utils.IPUtil;
import com.toney.crawler.common.model.UserAuthInfo;

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
    public boolean validate(HttpServletRequest request, AuthLevel level) {
        Assert.notNull(level);
 
        if (AuthLevel.NONE == level) {
            return true;
        }
 
        UserAuthInfo user = CookieStoreUtil.buildUserAuthInfoFromCookie(request);
        user.setClientIpAddress(IPUtil.getRequestIP(request));
        user.setSsoUserId(1000000351l);
        user.setSsoCusId(100000341l);
        request.setAttribute(Constants.USER_AUTH_INFO_ATTR, user);

        LOGGER.warn("Authorizate as user: {}, AuthLevel: {}, use for development only!!! URL={}", new Object[] {user.getSsoUserId(),
                level, request.getRequestURI()});

        return true;
    }

}
