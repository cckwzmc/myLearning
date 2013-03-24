package com.toney.istyle.core.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.core.BaseManagerTestCase;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :CacheServiceTestCase.java
 * @DESCRIPTION : cache service testcase
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
public class CacheServiceTestCase extends BaseManagerTestCase {
	@Autowired
	CacheService<String> cacheService;

	@Test
	public void testCache() {
		String t = cacheService.testCacheable("abc");
		for (int i = 0; i < 1000; i++) {
//			super.LOGGER.info("~~~~~~" + cacheService.get("userCache", "test_abc"));
			 cacheService.testCacheable("abc");
		}
		// super.cacheManagerInfo();
		cacheService.cacheManagerInfo("userCache");
	}
}
