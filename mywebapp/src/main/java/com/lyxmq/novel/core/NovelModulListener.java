package com.lyxmq.novel.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lyxmq.novel.configuration.NovelConfig;
import com.lyxmq.novel.exception.BootstrapException;
import com.lyxmq.novel.exception.InitializationException;
import com.lyxmq.novel.exception.NovelException;
import com.lyxmq.novel.startup.NovelStartup;

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
		// Now prepare the core services of Novel app so can bootstrap
		try {
			NovelStartup.prepare();
		} catch (BootstrapException e) {
			log.fatal("Novel prepare faile so App start fail!", e);
			return;
		}
		if (!NovelStartup.isPrepared()) {
			StringBuffer buf = new StringBuffer();
			buf.append("\n--------------------------------------------------------------");
			buf.append("\n Novel module startup INCOMPLETE, user interaction required");
			buf.append("\n--------------------------------------------------------------");
			log.info(buf.toString());
		} else {
			// trigger bootstrapping process
			try {
				NovelFactory.servletContext = servletContext;
				NovelFactory.bootstrap();
				NovelFactory.getNovelModulCore().initialize();
			} catch (BootstrapException e) {
				log.fatal("Novel module boot start failed", e);
			} catch (InitializationException e) {
				log.fatal("Novel module initialize failed", e);
			}
		}
		if (NovelFactory.isBootstrapped()) {

		}

		// do a small amount of work to initialize the web tier
		try {
			// Initialize Acegi based on Roller configuration
			initializeSecurityFeatures(servletContext);

			// Setup Velocity template engine
			setupFramemaker();
		} catch (NovelException ex) {
			log.fatal("Error initializing Roller Weblogger web tier", ex);
		}

	}

	private void setupFramemaker() throws NovelException {

	}

	/**
	 * setup spring securityFeatures
	 * 
	 * @param servletContext
	 * @throws NovelException
	 */
	private void initializeSecurityFeatures(ServletContext servletContext) throws NovelException {
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		boolean rememberMeEnabled = NovelConfig.getBooleanProperty("rememberme.enabled", true);
		log.info(" Remember me enabled: " + rememberMeEnabled);
		if (!rememberMeEnabled) {

		}
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		NovelFactory.getNovelModulCore().shutdown();
	}

}
