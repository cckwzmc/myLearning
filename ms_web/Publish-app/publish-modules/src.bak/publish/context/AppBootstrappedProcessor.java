package com.toney.publish.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import com.toney.dal.dao.biz.ServiceStartInitManager;
import com.toney.dal.dao.exception.BusinessException;
/**
 * @author toney.li
 * spring加载成功后，加载初始化信息。
 */
public class AppBootstrappedProcessor implements BeanFactoryPostProcessor {
	private static final Logger logger=LoggerFactory.getLogger(AppBootstrappedProcessor.class);
	
	private ServiceStartInitManager serviceStartInitManager;
	
	public void setServiceStartInitManager(ServiceStartInitManager serviceStartInitManager) {
		this.serviceStartInitManager = serviceStartInitManager;
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		logger.info("~~~~~~~~~~~~~~~开始加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			serviceStartInitManager.initApplicationData();
		} catch (BusinessException e) {
			logger.info("~~~~~~~~~~~~~~~失败加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
			throw new RuntimeException("失败加载初始化数据");
		}
		logger.info("~~~~~~~~~~~~~~~成功加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
