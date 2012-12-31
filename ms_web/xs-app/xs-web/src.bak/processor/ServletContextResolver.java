package com.toney.mvc.web.processor;

import javax.servlet.ServletContext;

/**
 * @author toney.li
 * 在其他层使用servletContext.
 * 
 */
public interface ServletContextResolver {
	public ServletContext getServletContext();
}

