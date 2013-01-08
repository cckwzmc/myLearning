package com.toney.core.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.core.biz.AppBootStrappedManager;
import com.toney.core.biz.AppCacheManager;
import com.toney.core.exception.BusinessException;

@Service("appBootStrappedManager")
public class AppBootStrappedManagerImpl implements AppBootStrappedManager {

	@Autowired
	private AppCacheManager  appCacheManager;
	@Override
	public void initApplicationData() throws BusinessException {
		this.appCacheManager.getChannelListData();
		this.appCacheManager.getGlobalMapConfiguration();
		this.appCacheManager.getGlobalModelConfiguration();
		this.appCacheManager.getAppArtType();
	}

}
