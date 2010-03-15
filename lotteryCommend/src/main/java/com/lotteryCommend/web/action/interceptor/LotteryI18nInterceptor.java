package com.lotteryCommend.web.action.interceptor;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LotteryI18nInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5609692870317454385L;
	//private static final Logger log = LoggerFactory.getLogger(LotteryI18nInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Locale locale = LocaleContextHolder.getLocale();
		setLocale(invocation, locale);
		return invocation.invoke();
	}

	private void setLocale(ActionInvocation invocation, Locale locale) {
		invocation.getInvocationContext().setLocale(locale);
	}
}
