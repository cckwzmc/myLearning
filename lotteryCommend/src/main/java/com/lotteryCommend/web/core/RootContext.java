package com.lotteryCommend.web.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class RootContext
 * 
 */
public class RootContext implements ServletContextListener {
	private static Logger log = LoggerFactory.getLogger(RootContext.class);

	/**
	 * Default constructor.
	 */
	public RootContext() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		log.debug("RootContext lotteryCommend start init............");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}
