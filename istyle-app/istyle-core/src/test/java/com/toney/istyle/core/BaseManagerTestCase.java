package com.toney.istyle.core;

import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import net.sf.ehcache.Cache;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.toney.istyle.util.ConvertUtil;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :BaseManagerTestCase.java
 * @DESCRIPTION : 测试基类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
@ContextConfiguration(locations = { "classpath:/applicationContext-core.xml", "classpath:/applicationContext-test.xml" })
public abstract class BaseManagerTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected static final XLogger LOGGER = XLoggerFactory.getXLogger(BaseManagerTestCase.class);
	protected ResourceBundle rb;
	@Autowired
	EhCacheCacheManager cacheManager;

	public BaseManagerTestCase() {
		String className = this.getClass().getName();
		try {
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException e) {
			LOGGER.error("Not Found resource bandle :{}", className, e);
		}
	}

	/**
	 * Utility method to populate an object with values from a properties file
	 * 
	 * @param obj
	 *            the model object to populate
	 * @return Object populated object
	 * @throws Exception
	 *             if BeanUtils fails to copy properly
	 */
	protected Object populate(Object obj) throws Exception {
		// loop through all the beans methods and set its properties from
		// its .properties file
		Map<String, String> map = ConvertUtil.convertBundleToMap(rb);

		BeanUtils.copyProperties(obj, map);

		return obj;
	}

	protected Map<String, String> populate() throws Exception {
		return ConvertUtil.convertBundleToMap(rb);
	}

	protected void cacheManagerInfo() {
		Cache cache = this.cacheManager.getCacheManager().getCache("productCache");
		// 得到缓存中的对象数
		LOGGER.info("~~~~~~cache.getSize~~~" + cache.getSize());
		// 得到缓存对象占用内存的大小
		LOGGER.info("~~~~~~cache.getMemoryStoreSize()~~~" + cache.getMemoryStoreSize());
		// 得到缓存读取的命中次数
		LOGGER.info("~~~~~~cache.getStatistics().getCacheHits()~~~" + cache.getStatistics().getCacheHits());
		// 得到缓存读取的错失次数
		LOGGER.info("~~~~~~cache.getStatistics().getCacheMisses()~~~" + cache.getStatistics().getCacheMisses());
		LOGGER.info("~~~~~~cache.getStatistics()~~~" + cache.getStatistics());
	}
}
