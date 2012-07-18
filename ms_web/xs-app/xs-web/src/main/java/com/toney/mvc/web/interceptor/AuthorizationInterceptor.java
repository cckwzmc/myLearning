package com.toney.mvc.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toney.core.model.UserAuthInfo;
import com.toney.mvc.web.annotation.AuthRequired;
import com.toney.mvc.web.contants.Constants;
import com.toney.mvc.web.contants.UrlConstants;
import com.toney.mvc.web.interceptor.validator.AuthorizationValidator;
import com.toney.mvc.web.utils.UserAuthInfoHolder;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : xiu@xiu.com
 * @DATE :2012-4-3 上午9:58:04
 *       </p>
 **************************************************************** 
 */
public class AuthorizationInterceptor extends AnnotationBasedIgnoreableInterceptor {
    protected XLogger logger = XLoggerFactory.getXLogger(getClass());

    private List<AuthorizationValidator> validators;

    public List<AuthorizationValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<AuthorizationValidator> validators) {
        this.validators = validators;
    }

    @Override
    protected boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //用于区分mvc:resources, 正常的Controller请求
        if (handler==null || !handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        
        HandlerMethod method = (HandlerMethod) handler;
        AuthRequired ar = method.getBeanType().getAnnotation(AuthRequired.class);
        if (ar == null) {
            ar = method.getMethodAnnotation(AuthRequired.class);
        }
        
        if (ar == null) {
            return true;
        }

        for (AuthorizationValidator validator : validators) {
            if (validator.validate(request)) {
                setUserAuthInfo(request);
                return true;
            }
        }

        if (StringUtils.equals("json", request.getParameter("_format"))
                || StringUtils.equals("jsonp", request.getParameter("_format"))) {
            // TODO: 对于4xx/5xx错误是否需要使用统一的JsonPackageWrapper格式？
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.sendRedirect(composeLoginURL(request));
        }

        response.flushBuffer();

        return false;
    }

    @Override
    protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    protected void afterCompletionInternal(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        clearUserAuthInfo(request);
    }

    private void setUserAuthInfo(HttpServletRequest request) {
        UserAuthInfo user = (UserAuthInfo) request.getAttribute(Constants.USER_AUTH_INFO_ATTR);
        if (user != null) {
            UserAuthInfoHolder.setUserAuthInfo(user);
        }
    }

    private void clearUserAuthInfo(HttpServletRequest request) {
        // TODO: Make sure it must be call
        UserAuthInfoHolder.clear();
    }

    private String composeLoginURL(HttpServletRequest request) {
        String backURL = request.getRequestURL().toString();
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(UrlConstants.LOGIN_ROOT_URL);
        try {
            String encodeURL = URLEncoder.encode(backURL, "UTF-8");
            sbuf.append("?backURL=");
            sbuf.append(encodeURL);
        } catch (UnsupportedEncodingException e) {
            // FIXME: throw exception while logging
            logger.warn("Get exception while encode url: {}", backURL, e);
        }

        return sbuf.toString();
    }

}
