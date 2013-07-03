package com.toney.dal.cms.dao;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.dal.cms.model.AreaModel;

/**
 * @author toney.li
 * 
 */
public class AreaDaoTest extends BaseDaoTestCase {
	@Autowired
	private AreaDao areaDao;

	// 1353513162 1353513162
	// {com.toney.dal.dao.TemplateManagerDao=[ name =
	// com.toney.dal.dao.TemplateManagerDao status = STATUS_ALIVE eternal = true
	// overflowToDisk = true maxEntriesLocalHeap = 500 maxEntriesLocalDisk = 0
	// memoryStoreEvictionPolicy = LRU timeToLiveSeconds = 0 timeToIdleSeconds =
	// 0 persistence = none diskExpiryThreadIntervalSeconds = 120
	// cacheEventListeners: net.sf.ehcache.statistics.LiveCacheStatisticsWrapper
	// hitCount = 0 memoryStoreHitCount = 0 diskStoreHitCount = 0
	// missCountNotFound = 0 missCountExpired = 0 maxBytesLocalHeap = 0
	// overflowToOffHeap = false maxBytesLocalOffHeap = 0 maxBytesLocalDisk = 0
	// pinned = false ], dalSiteContext=[ name = dalSiteContext status = STATUS_ALIVE eternal = true overflowToDisk = true maxEntriesLocalHeap = 150
	// maxEntriesLocalDisk = 0 memoryStoreEvictionPolicy = LRU timeToLiveSeconds = 0 timeToIdleSeconds = 0 persistence = none diskExpiryThreadIntervalSeconds =
	// 120 cacheEventListeners: net.sf.ehcache.statistics.LiveCacheStatisticsWrapper hitCount = 0 memoryStoreHitCount = 0 diskStoreHitCount = 0
	// missCountNotFound = 0 missCountExpired = 0 maxBytesLocalHeap = 0 overflowToOffHeap = false maxBytesLocalOffHeap = 0 maxBytesLocalDisk = 0 pinned = false
	// ], com.toney.dal.dao.AreaDao=[ name = com.toney.dal.dao.AreaDao status = STATUS_ALIVE eternal = true overflowToDisk = true maxEntriesLocalHeap = 500
	// maxEntriesLocalDisk = 0 memoryStoreEvictionPolicy = LRU timeToLiveSeconds = 0 timeToIdleSeconds = 0 persistence = none diskExpiryThreadIntervalSeconds =
	// 120 cacheEventListeners: net.sf.ehcache.statistics.LiveCacheStatisticsWrapper hitCount = 0 memoryStoreHitCount = 0 diskStoreHitCount = 0
	// missCountNotFound = 0 missCountExpired = 0 maxBytesLocalHeap = 0 overflowToOffHeap = false maxBytesLocalOffHeap = 0 maxBytesLocalDisk = 0 pinned = false
	// ]}
	// 1353513162:1193540497:com.toney.dal.dao.AreaDao.getAllArea:0:2147483647:select id,name,reid,disorder from dede_area t order by id ,reid,disorder
	@Test
	public void testGetAllArea() throws InterruptedException {
		List<AreaModel> list = areaDao.getAllArea();
		Assert.assertNotNull(list);
		Thread.sleep(4000);
	}

	@Test
	public void testGetAllArea2() throws InterruptedException {
		List<AreaModel> list = areaDao.getAllArea();
		Assert.assertNotNull(list);
		Thread.sleep(4000);
	}

}
