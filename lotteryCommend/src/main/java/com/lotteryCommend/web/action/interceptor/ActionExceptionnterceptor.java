package com.lotteryCommend.web.action.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ActionExceptionnterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4992304688224511539L;
	private static final Logger log = LoggerFactory.getLogger(ActionExceptionnterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result=null;
		try {
			result=invocation.invoke();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		return result;
	}

}
