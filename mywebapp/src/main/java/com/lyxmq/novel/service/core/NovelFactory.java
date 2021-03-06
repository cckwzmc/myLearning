package com.lyxmq.novel.service.core;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lyxmq.novel.bootstrap.NovelStartup;
import com.lyxmq.novel.exception.BootstrapException;

public class NovelFactory {
	private static final Log log = LogFactory.getLog(NovelFactory.class);
	// our configured Novel provider
	private static NovelProvider novelProvider = null;

	private static ServletContext servletContext;

	public static void setServletContext(ServletContext servletContext) {
		NovelFactory.servletContext = servletContext;
	}

	/**
	 * True if bootstrap process has been completed, False otherwise.
	 */
	public static boolean isBootstrapped() {
		return (novelProvider != null);
	}

	/**
	 * Accessor to the Novel business tier.
	 * 
	 * @return An instance of Novel.
	 * @throws IllegalStateException
	 *             If the app has not been properly bootstrapped yet.
	 */
	public static final NovelServiceCore getNovelServiceCore() {
		if (novelProvider == null) {
			throw new IllegalStateException("Novel has not been bootstrapped yet");
		}
		return novelProvider.getNovelServiceCore();
	}

	/**
	 * Bootstrap the Novel business tier, uses default NovelProvider.
	 * 
	 * Bootstrapping the application effectively instantiates all the necessary pieces of the business tier and wires them together so that the app is ready to run.
	 * 
	 * @throws IllegalStateException
	 *             If the app has not been properly prepared yet.
	 * @throws BootstrapException
	 *             If an error happens during the bootstrap process.
	 */
	public static final void bootstrap() throws BootstrapException {

		// if the app hasn't been properly started so far then bail
		if (!NovelStartup.isPrepared()) {
			throw new IllegalStateException("Cannot bootstrap until application has been properly prepared");
		}

		// lookup our default provider and instantiate it
		NovelProvider defaultProvider;
		try {
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			defaultProvider = (NovelProvider) context.getBean("novelProvider");
		} catch (Exception ex) {
			throw new BootstrapException("Error instantiating default provider: ", ex);
		}

		// now just bootstrap using our default provider
		bootstrap(defaultProvider);
	}

	/**
	 * Bootstrap the Novel business tier, uses specified NovelProvider.
	 * 
	 * Bootstrapping the application effectively instantiates all the necessary pieces of the business tier and wires them together so that the app is ready to run.
	 * 
	 * @param provider
	 *            A NovelProvider to use for bootstrapping.
	 * @throws IllegalStateException
	 *             If the app has not been properly prepared yet.
	 * @throws BootstrapException
	 *             If an error happens during the bootstrap process.
	 */
	public static final void bootstrap(NovelProvider provider) throws BootstrapException {

		// if the app hasn't been properly started so far then bail
		if (!NovelStartup.isPrepared()) {
			throw new IllegalStateException("Cannot bootstrap until application has been properly prepared");
		}

		if (provider == null) {
			throw new NullPointerException("Novel provider is null");
		}

		log.info("Bootstrapping Novel business tier");

		log.info("Novel Provider = " + provider.getClass().getName());

		// save reference to provider
		novelProvider = provider;

		// bootstrap weblogger provider
		novelProvider.bootstrap();

		// make sure we are all set
		if (novelProvider.getNovelServiceCore() == null) {
			throw new BootstrapException("Bootstrapping failed, Novel instance is null");
		}
		log.info("Novel business tier successfully bootstrapped");
	}

}
