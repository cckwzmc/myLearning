package com.lyxmq.novel.struts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final Log log=LogFactory.getLog(BaseAction.class);
	private static final long serialVersionUID = -8503056199217288180L;
	
	protected static final String CANCEL="cancel";
	protected static final String ACCESS_ENABLED="access_enabled";
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	protected HttpSession getSession(){
		return this.getSession(false);
	}
	
	protected HttpSession getSession(boolean b) {
		return ServletActionContext.getRequest().getSession(b);
	}
	
}
