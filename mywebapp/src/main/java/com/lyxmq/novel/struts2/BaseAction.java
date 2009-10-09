package com.lyxmq.novel.struts2;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.lyxmq.novel.core.NovelModulCore;
import com.lyxmq.novel.hibernate.pojo.User;
import com.lyxmq.novel.struts2.utils.UIUtils;
import com.lyxmq.novel.user.NovelUserManager;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ActionPreparable, SecurityEnforced {
	
	private static final Log log = LogFactory.getLog(BaseAction.class);
	
	private static final long serialVersionUID = -8503056199217288180L;

	protected static final String CANCEL = "cancel";
	protected static final String LIST = "list";

	// action name (used by tabbed menu utility)
	protected String actionName = null;

	// the name of the menu this action wants to show, or null for no menu
	protected String desiredMenu = null;

	// page title
	protected String pageTitle = null;
	private User authenticatedUser;
	private NovelModulCore actionNovelModulCore;

	// convenient way to tell if user being dealt with is an admin
	public boolean isUserIsAdmin() {
		return getAuthenticatedUser().hasRole("admin");
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDesiredMenu() {
		return desiredMenu;
	}

	public void setDesiredMenu(String desiredMenu) {
		this.desiredMenu = desiredMenu;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	protected static final String ACCESS_ENABLED = "access_enabled";

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpSession getSession() {
		return this.getSession(false);
	}

	protected HttpSession getSession(boolean b) {
		return ServletActionContext.getRequest().getSession(b);
	}

	public void addError(String errorKey) {
		addActionError(getText(errorKey));
	}

	public void addError(String errorKey, String param) {
		addActionError(getText(errorKey, errorKey, param));
	}

	@SuppressWarnings("unchecked")
	public void addError(String errorKey, List args) {
		addActionError(getText(errorKey, args));
	}

	/**
	 * This simply returns the result of hasActionErrors() but we need it because without it you can't easily check if there were errors since you can't call a hasXXX() method via OGNL.
	 */
	public boolean errorsExist() {
		return hasActionErrors();
	}

	public void addMessage(String msgKey) {
		addActionMessage(getText(msgKey));
	}

	public void addMessage(String msgKey, String param) {
		addActionMessage(getText(msgKey, msgKey, param));
	}

	@SuppressWarnings("unchecked")
	public void addMessage(String msgKey, List args) {
		addActionMessage(getText(msgKey, args));
	}

	/**
	 * This simply returns the result of hasActionMessages() but we need it because without it you can't easily check if there were messages since you can't call a hasXXX() method via OGNL.
	 */
	public boolean messagesExist() {
		return hasActionMessages();
	}

	@SuppressWarnings("unchecked")
	public List getLocalesList() {
		return UIUtils.getLocales();
	}

	@SuppressWarnings("unchecked")
	public List getTimeZonesList() {
		return UIUtils.getTimeZones();
	}

	public List<Integer> getHoursList() {
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < 24; i++) {
			ret.add(i);
		}
		return ret;
	}

	public List<Integer> getMinutesList() {
		List<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i < 60; i++) {
			ret.add(i);
		}
		return ret;
	}

	public List<Integer> getSecondsList() {
		return getMinutesList();
	}

	/**
	 * sub classes can do self prepare
	 */
	@Override
	public void actionPrepare() {

	}

	@Override
	public boolean isRequired() {
		return true;
	}

	@Override
	public boolean isUserRequired() {
		return true;
	}

	// default action permissions, no perms required
	@Override
	public short requiredPermissions() {
		return -1;
	}

	@Override
	public String requiredUserRole() {
		return "";
	}

	public User getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(User authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public NovelModulCore getNovelModulCore() {
		return actionNovelModulCore;
	}

	public NovelModulCore getActionNovelModulCore() {
		return actionNovelModulCore;
	}

	public void setActionNovelModulCore(NovelModulCore actionNovelModulCore) {
		this.actionNovelModulCore = actionNovelModulCore;
	}

	public NovelUserManager getUserNovelAuths() {
		return null;
	}

}
