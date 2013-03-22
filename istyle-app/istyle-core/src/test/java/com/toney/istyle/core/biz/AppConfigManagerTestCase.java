package com.toney.istyle.core.biz;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.BaseManagerTestCase;
import com.toney.istyle.core.exception.ManagerException;
import com.toney.istyle.core.exception.ServiceException;
import com.toney.istyle.core.system.AppConfigQueryService;

public class AppConfigManagerTestCase extends BaseManagerTestCase {

	@Autowired
	AppConfigManager appConfigManager;
	@Autowired
	AppConfigQueryService appConfigReadService;

	@Test
	public void testGetAppConfig() {
		Map<String, AppConfigBO> map = null;
		try {
			long start = System.currentTimeMillis();
			map = this.appConfigManager.getAppConfigs();
			// 112
			System.out.println("~~~~start ~~~" + (System.currentTimeMillis() - start));
		} catch (ManagerException e) {
			e.printStackTrace();
		}
		org.junit.Assert.assertNotNull(map);
		org.junit.Assert.assertTrue(map.keySet().size() > 0);
	}

	@Test
	public void testRefresh() {
		try {
			this.appConfigReadService.refresh();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		this.testGetAppConfig();
	}
}
