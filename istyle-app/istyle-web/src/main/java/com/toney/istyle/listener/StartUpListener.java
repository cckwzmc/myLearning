package com.toney.istyle.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.toney.istyle.core.biz.AppConfigManager;
import com.toney.istyle.core.exception.ManagerException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :StartUpListener.java
 * @DESCRIPTION : web 启动监听
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 19, 2013
 *       </p>
 **************************************************************** 
 */
public class StartUpListener implements ServletContextListener {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(StartUpListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event)  {
		LOGGER.info("~~~~~~~~~~~~~~~开始加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		String[] beans = ctx.getBeanDefinitionNames();
		for (String bean : beans) {
			LOGGER.info(bean);
		}
		LOGGER.info("~~~~~~~~~~~~~~~初始化缓存数据~~~~~~~~~~~~~~~~~~~~");
		// 系统缓存
		AppConfigManager appConfigManager = ctx.getBean("appConfigManager", AppConfigManager.class);
		try {
			appConfigManager.getAppConfigs();
		} catch (ManagerException e) {
			LOGGER.error("加载系统配置失败",e);
			System.exit(0);
		}
		// 业务缓存
		LOGGER.info("~~~~~~~~~~~~~~~初始化缓存数据完成~~~~~~~~~~~~~~~~~~~~");

		LOGGER.info("~~~~~~~~~~~~~~~成功加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("~~~~~~~~~~~~~~~开始关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~成功关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
