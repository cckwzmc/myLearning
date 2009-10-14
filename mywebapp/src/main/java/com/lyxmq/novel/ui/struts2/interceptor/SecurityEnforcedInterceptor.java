package com.lyxmq.novel.ui.struts2.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lyxmq.novel.hibernate.pojo.User;
import com.lyxmq.novel.ui.struts2.core.BaseAction;
import com.lyxmq.novel.ui.struts2.core.SecurityEnforced;
import com.lyxmq.novel.user.NovelUserManager;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityEnforcedInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1043935201346857877L;
	private static final Log log = LogFactory.getLog(SecurityEnforcedInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("Entering SecurityEnforcedInterceptor");
		final Object action = invocation.getAction();
		if (action instanceof SecurityEnforced && action instanceof BaseAction) {
			log.debug("action is UISecurityEnforced ... enforcing security rules");
			final SecurityEnforced theAction = (SecurityEnforced) action;
			// are we requiring an authenticated user?
			if (theAction.isUserRequired()) {

				User authenticatedUser = ((BaseAction) theAction).getAuthenticatedUser();
				if (authenticatedUser == null) {
					log.debug("DENIED: required user not found");
					return "access-denied";
				}

				// are we also enforcing a specific role?
				if (theAction.requiredUserRole() != null) {
					if (!authenticatedUser.hasRole(theAction.requiredUserRole())) {
						log.debug("DENIED: user does not have role = " + theAction.requiredUserRole());
						return "access-denied";
					}
				}

				// are we requiring a valid action NovelModulCore?
				if (theAction.isRequired()) {

					NovelUserManager actionNovel = ((BaseAction) theAction).getUserNovelAuths();
					if (actionNovel == null) {
						log.debug("DENIED: required action novel not found");
						return "access-denied";
					}

					// are we also enforcing a specific web permission?
					if (theAction.requiredPermissions() > -1) {

						if (!actionNovel.hasUserPermissions(authenticatedUser, theAction.requiredPermissions())) {
							log.debug("DENIED: user does not have required novel permissions = " + theAction.requiredPermissions());
							return "access-denied";
						}
					}
				}
			}
		}
		return null;
	}

}
