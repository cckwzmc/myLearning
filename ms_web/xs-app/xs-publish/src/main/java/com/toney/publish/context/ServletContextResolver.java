package com.toney.publish.context;

import javax.servlet.ServletContext;

/**
 * @author toney.li
 * 在其他层使用servletContext.
 * 
 */
public interface ServletContextResolver {
	public ServletContext getServletContext();
}

