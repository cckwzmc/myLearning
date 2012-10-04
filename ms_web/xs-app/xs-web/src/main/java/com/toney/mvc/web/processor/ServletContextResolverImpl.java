package com.toney.mvc.web.processor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletConfigAware;

/**
 * @author toney.li
 *
 */
@Component
public class ServletContextResolverImpl implements ServletContextResolver,ServletConfigAware {

	@Override
	public ServletContext getServletContext() {
		return this.servletContext;
	}
	
	private ServletContext servletContext;

	@Override
	public void setServletConfig(ServletConfig arg0) {
		this.servletContext = arg0.getServletContext();
	}

}
