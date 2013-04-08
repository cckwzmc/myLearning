package com.toney.publish.executor;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.toney.publish.tpl.service.PublishOutputService;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PulicPagePublishManagerImpl
 * @DESCRIPTION :公共页面出版。
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 10, 2013
 *       </p>
 **************************************************************** 
 */
@Service("pulicPagePublishManager")
public class PulicPagePublishManagerImpl  implements PulicPagePublishManager {
	private static final XLogger logger = XLoggerFactory.getXLogger(PulicPagePublishManagerImpl.class);

	@Autowired
	PublishOutputService publishOutputService; 
	/* (non-Javadoc)
	 * @see com.toney.publish.executor.PulicPagePublishManager#publish(java.lang.Long)
	 */
	@Override
	public void publish(Long publicId) {
		Assert.notNull(publicId);
		logger.debug("---------公共页面出版-----------");
		
	}


}
