package com.toney.istyle.mvc.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.toney.istyle.core.biz.AppConfigManager;
import com.toney.istyle.form.UserAuthInfo;
import com.toney.istyle.mvc.annotation.AuthLevel;
import com.toney.istyle.mvc.annotation.AuthRequired;
import com.toney.istyle.mvc.constants.Constants;
import com.toney.istyle.mvc.interceptor.validator.AuthorizationValidator;
import com.toney.istyle.mvc.util.RequestUtil;
import com.toney.istyle.mvc.util.UserAuthInfoHolder;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AuthorizationInterceptor.java
 * @DESCRIPTION : 用户权限认证的实现
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013
 *       </p>
 **************************************************************** 
 */
public class AuthorizationInterceptor extends AnnotationBasedIgnoreableInterceptor {
	protected static final XLogger LOGGER = XLoggerFactory.getXLogger(AuthorizationInterceptor.class);

	@Autowired
	private AppConfigManager appConfigManager;
	private List<AuthorizationValidator> validators;

	public List<AuthorizationValidator> getValidators() {
		return validators;
	}

	public void setValidators(List<AuthorizationValidator> validators) {
		this.validators = validators;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.xiu.portal.web.interceptor.AnnotationBasedIgnoreableInterceptor#preHandleInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 *      {@inheritDoc}
	 */
	@Override
	protected boolean preHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 用于区分mvc:resources, 正常的Controller请求
		if (handler == null || !HandlerMethod.class.isAssignableFrom(handler.getClass())) {
			return true;
		}

		MDC.put(Constants.MDC_USERID_KEY, "-");

		HandlerMethod method = (HandlerMethod) handler;
		AuthLevel level = getAuthLevelFromHandler(method);

		if (AuthLevel.NONE == level) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Authorize {}.{} AuthLevel={} [SKIPPED]", new Object[] { method.getBeanType().getSimpleName(), method.getMethod().getName(), level });
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
			LOGGER.debug("Authorize {}.{} AuthLevel={} [{}]", new Object[] { method.getBeanType().getSimpleName(), method.getMethod().getName(), level,
					isAllowed ? "PASSED" : "DENIED" });
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
	protected void postHandleInternal(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	protected void afterCompletionInternal(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		clearUserAuthInfo(request);
	}

	private void setUserAuthInfo(HttpServletRequest request) {
		UserAuthInfo user = (UserAuthInfo) request.getAttribute(Constants.USER_AUTH_INFO_ATTR);
		if (user != null) {
			MDC.put(Constants.MDC_USERID_KEY, String.valueOf(user.getUserId()));
			UserAuthInfoHolder.setUserAuthInfo(user);
		}
	}

	private void clearUserAuthInfo(HttpServletRequest request) {
		// 经测试确认，Controller出异常时会调用afterCompletionInternal
		UserAuthInfoHolder.clear();
		MDC.remove(Constants.MDC_USERID_KEY);
	}

	private String composeLoginURL(HttpServletRequest request) throws Exception{
		String backURL = request.getRequestURL().toString();
		String urlParamStr = request.getQueryString();
		if (backURL.contains("?")) {
			backURL = backURL + "&" + urlParamStr;
		} else {
			backURL = backURL + "?" + urlParamStr;
		}
		StringBuffer sbuf = new StringBuffer();
		sbuf.append(appConfigManager.getAppConfigs().get("USER_LOGIN_URL").getCfgValues());
		try {
			String encodeURL = URLEncoder.encode(backURL, "UTF-8");
			sbuf.append("?service_url=");
			sbuf.append(encodeURL);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Get exception while encode url: {}", backURL, e);
		}

		return sbuf.toString();
	}

	private AuthLevel getAuthLevelFromHandler(HandlerMethod method) {
		AuthRequired ar = method.getMethodAnnotation(AuthRequired.class);
		if (ar == null) {
			ar = method.getBeanType().getAnnotation(AuthRequired.class);
		}
		return (ar == null) ? AuthLevel.NONE : ar.value();
	}

}
