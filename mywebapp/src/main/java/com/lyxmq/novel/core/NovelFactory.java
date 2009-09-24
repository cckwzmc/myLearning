package com.lyxmq.novel.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lyxmq.novel.exception.BootstrapException;
import com.lyxmq.novel.startup.NovelStartup;

public class NovelFactory {
	private static final Log log = LogFactory.getLog(NovelFactory.class);
	// our configured Novel provider
	private static NovelProvider novelProvider = null;

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
	public static final NovelModulCore getNovelModulCore() {
		if (novelProvider == null) {
			throw new IllegalStateException("Novel has not been bootstrapped yet");
		}
		return novelProvider.getNovelModulCore();
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
		String providerClassname = WebloggerConfig.getProperty("weblogger.provider.class");
		if (providerClassname != null) {
			try {
				Class providerClass = Class.forName(providerClassname);
				defaultProvider = (NovelProvider) providerClass.newInstance();
			} catch (Exception ex) {
				throw new BootstrapException("Error instantiating default provider: " + providerClassname, ex);
			}
		} else {
			throw new NullPointerException("No provider specified in config property 'weblogger.provider.class'");
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
		if (novelProvider.getNovelModulCore() == null) {
			throw new BootstrapException("Bootstrapping failed, Novel instance is null");
		}
		log.info("Novel business tier successfully bootstrapped");
	}

}
