package com.toney.web.commons.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toney.core.model.UserAuthInfo;
import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.constants.AuthLevel;
import com.toney.web.commons.constants.Constants;
import com.toney.web.commons.constants.UrlConstants;
import com.toney.web.commons.utils.RequestUtil;
import com.toney.web.commons.utils.UserAuthInfoHolder;
import com.toney.web.commons.validator.AuthorizationValidator;

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
    protected static final XLogger LOGGER = XLoggerFactory.getXLogger(AuthorizationInterceptor.class);

    private List<AuthorizationValidator> validators;

    public List<AuthorizationValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<AuthorizationValidator> validators) {
        this.validators = validators;
    }

    /**
     *  (non-Javadoc)
     * @see com.xiu.portal.web.interceptor.AnnotationBasedIgnoreableInterceptor#preHandleInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     * {@inheritDoc}
     */
    @Override
    protected boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //用于区分mvc:resources, 正常的Controller请求
        if (handler==null || !HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            return true;
        }

        MDC.put(Constants.MDC_SSOUSERID_KEY, "-");

        HandlerMethod method = (HandlerMethod) handler;
        AuthLevel level = getAuthLevelFromHandler(method);

        if (AuthLevel.NONE == level) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Authorize {}.{} AuthLevel={} [SKIPPED]", 
                        new Object[] { method.getBeanType().getSimpleName(), method.getMethod().getName(),
                        level} );
            }
            return true;
        }

        boolean isAllowed = false;
        for (AuthorizationValidator validator : validators) {
            if (validator.validate(request, level)) {
                setUserAuthInfo(request);
                isAllowed = true;
                break;
            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Authorize {}.{} AuthLevel={} [{}]", 
                    new Object[] { method.getBeanType().getSimpleName(), method.getMethod().getName(),
                        level, isAllowed ? "PASSED" : "DENIED" } );
        }

        if (isAllowed) {
            return true;
        }
        
        if (RequestUtil.isJsonRequest(request)) {
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
            MDC.put(Constants.MDC_SSOUSERID_KEY, String.valueOf(user.getSsoUserId()));
            UserAuthInfoHolder.setUserAuthInfo(user);
        }
    }

    private void clearUserAuthInfo(HttpServletRequest request) {
        // 经测试确认，Controller出异常时会调用afterCompletionInternal
        UserAuthInfoHolder.clear();
        MDC.remove(Constants.MDC_SSOUSERID_KEY);
    }

    private String composeLoginURL(HttpServletRequest request) {
        String backURL = request.getRequestURL().toString();
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(UrlConstants.LOGIN_ROOT_URL);
        try {
            String encodeURL = URLEncoder.encode(backURL, "UTF-8");
            sbuf.append("?rd=");
            sbuf.append(encodeURL);
        } catch (UnsupportedEncodingException e) {
            // FIXME: throw exception while logging
            LOGGER.warn("Get exception while encode url: {}", backURL, e);
        }

        return sbuf.toString();
    }
    
    private AuthLevel getAuthLevelFromHandler(HandlerMethod method) {
        AuthRequired ar = method.getMethodAnnotation(AuthRequired.class);        
        if (ar == null) {
            ar = method.getBeanType().getAnnotation(AuthRequired.class);
        }
        return (ar == null)? AuthLevel.NONE : ar.value();
    }

}
