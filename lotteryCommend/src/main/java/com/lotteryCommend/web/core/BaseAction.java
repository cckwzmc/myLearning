package com.lotteryCommend.web.core;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	private static final long serialVersionUID = -742935837872038913L;
	
	public static final String LIST="list";
	protected ServletContext getContext() {
		return ServletActionContext.getServletContext();
	}

	protected HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * first HTTP_CLIENT_IP
	 * second HTTP_X_FORWARDED_FOR
	 * last request remoteAddr
	 * @return can't get ip then return null, or return unkown;
	 */
	protected final String getRemoteIp() {
		HttpServletRequest request = this.getHttpServletRequest();
		if (request == null)
			return "";
		String ipaddr = request.getHeader("HTTP_CLIENT_IP");
		if (StringUtils.isBlank(ipaddr)||"unknown".equalsIgnoreCase(ipaddr)) {
			ipaddr = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(ipaddr)||"unknown".equalsIgnoreCase(ipaddr)) {
			ipaddr = request.getRemoteAddr();
		}
		return StringUtils.isBlank(ipaddr)?"unknown":ipaddr;
	}
}
