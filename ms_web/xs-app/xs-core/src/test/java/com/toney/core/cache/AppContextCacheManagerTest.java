package com.toney.core.cache;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.biz.BaseManagerTestCase;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.SysConfigModel;

public class AppContextCacheManagerTest extends BaseManagerTestCase {

	@Autowired
	// @Mock
	private CacheManagerFactory ehcacheManagerFactory;

	@Test
	public void testInitApplicatonContext() {
		try {
			Map<String, SysConfigModel> map = this.ehcacheManagerFactory.getAppContextCacheManager().initApplicationContext( );
			Assert.assertEquals(
					this.ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext()
							.get("cfg_basehost").getCfgValue(), "http://dd.toney.com");
			Assert.assertNotNull(map);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testRefreshApplicatonContext() {
		try {
			this.ehcacheManagerFactory.getAppContextCacheManager().refresh(CacheConstants.KEY_APPCONTEXT_CACHE);
			Map<String, SysConfigModel> map = this.ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext(
					);
			Assert.assertEquals(map.get("cfg_basehost").getCfgValue(), "http://dd.toney.com");
			Assert.assertNotNull(map);

		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetApplicatonContext() {
		try {
			Map<String, SysConfigModel> map = this.ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext(
					);
			Assert.assertEquals(map.get("cfg_basehost").getCfgValue(), "http://dd.toney.com");
			Assert.assertNotNull(map);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}
}
