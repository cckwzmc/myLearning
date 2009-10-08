package com.lyxmq.novel.struts2.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lyxmq.novel.struts2.ActionPreparable;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * A struts2 interceptor for doing custom prepare logic.
 */
public class ActionPreparableInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5241402312431282914L;
	private static Log log = LogFactory.getLog(ActionPreparableInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("Entering ActionPreparableInterceptor");
		final Object action=invocation.getAction();
        final ActionContext context=invocation.getInvocationContext();
        if(action instanceof ActionPreparable){
        	 
            log.debug("action is ActionPreparable, calling actionPrepare() method");
            ActionPreparable prepare=(ActionPreparable) action;
            prepare.actionPrepare();
        }
		return invocation.invoke();
	}

}
