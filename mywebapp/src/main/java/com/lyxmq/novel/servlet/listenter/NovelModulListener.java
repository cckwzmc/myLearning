package com.lyxmq.novel.servlet.listenter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

/**
 * Application Lifecycle Listener implementation class XiaoshuoModulListener
 * 
 */
public class NovelModulListener extends ContextLoaderListener implements ServletContextListener {
	private static Log log = LogFactory.getLog(NovelModulListener.class);
	private static ServletContext servletContext = null;

	/**
	 * Default constructor.
	 */
	public NovelModulListener() {
		super();
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		// Call Spring's context ContextLoaderListener to initialize all the
		// context files specified in web.xml. This is necessary because
		// listeners don't initialize in the order specified in 2.3 containers
		super.contextInitialized(sce);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
