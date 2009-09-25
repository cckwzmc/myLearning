package com.lyxmq.novel.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NovelConfig {
	private static final Log log = LogFactory.getLog(NovelConfig.class);
	private static String default_config = "/com/lyxmq/novel/configuration/novel_config.properties";
	private static Properties config;

	// no, you may not instantiate this class :p
	private NovelConfig() {
	}

	/*
	 * Static block run once at class loading
	 * 
	 * We load the default properties and any custom properties we find
	 */
	static {
		config = new Properties();
		try {
			// we'll need this to get at our properties files in the classpath
			Class config_class = Class.forName("com.lyxmq.novel.configuration.NovelConfig");
			// load system properties
			InputStream is = config_class.getResourceAsStream(default_config);
			if (is != null) {
				config.load(is);
				log.info("Successfully loaded load config file ");
			} else {
				log.info("No any properties load . ");
			}

		} catch (ClassNotFoundException e) {
			log.error("com.lyxmq.novel.configuration.NovelConfig not found.");
		} catch (IOException e) {
			log.error("IO Exception " + default_config);
		}

	}

	/**
	 * Retrieve a property key
	 * 
	 * @param key
	 * @return String Value of property requested, null if not found
	 */
	public static String getProperty(String key) {
		log.debug("Fetching property [" + key + "=" + config.getProperty(key));
		String value = config.getProperty(key);
		return value == null ? value : value.trim();
	}

	/**
	 * Retrieve a property key,
	 * 
	 * @param key
	 * @param defaultValue
	 *            Default value of property if not found
	 * @return String Value of property requested, default_value if not found
	 */
	public static String getProperty(String key, String default_value) {
		log.debug("Fetching property [" + key + "=" + config.getProperty(key) + ",defaultValue=" + default_value + "]");
		String value = config.getProperty(key);
		if (value == null) {
			value = default_value;
		}
		return value;
	}

	/**
	 * Retrieve a property key,
	 * 
	 * @param key
	 * @return Boolean Value of property requested, false if not found
	 */
	public static Boolean getBooleanProperty(String key) {
		return getBooleanProperty(key, false);
	}

	/**
	 * Retrieve a property key,
	 * 
	 * @param key
	 * @param defaultValue
	 *            Default value of property if not found
	 * @return Boolean Value of property requested, default_value if not found
	 */
	public static Boolean getBooleanProperty(String key, boolean defaultValue) {
		log.debug("Fetching property [" + key + "=" + config.getProperty(key) + ",defaultValue=" + defaultValue + "]");
		String value = NovelConfig.getProperty(key);
		if (value == null) {
			return defaultValue;
		} else {
			return new Boolean(value);
		}
	}

    /**
     * Set the "uploads.dir" property at runtime.
     * <p />
     * Properties are meant to be read-only, but we make this exception because  
     * we know that some people are still writing their uploads to the webapp  
     * context and we can only get that path at runtime (and for unit testing).
     * <p />
     * This property is *not* persisted in any way.
     */
    public static void setUploadsDir(String path) {
        // only do this if the user wants to use the webapp context
        if("${webapp.context}".equals(config.getProperty("uploads.dir")))
            config.setProperty("uploads.dir", path);
    }
    
    /**
     * Set the "themes.dir" property at runtime.
     * <p />
     * Properties are meant to be read-only, but we make this exception because  
     * we know that some people are still using their themes in the webapp  
     * context and we can only get that path at runtime (and for unit testing).
     * <p />
     * This property is *not* persisted in any way.
     */
    public static void setThemesDir(String path) {
        // only do this if the user wants to use the webapp context
        if("${webapp.context}".equals(config.getProperty("themes.dir")))
            config.setProperty("themes.dir", path);
    }
    
}
