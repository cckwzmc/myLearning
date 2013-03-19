package com.toney.istyle.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.toney.istyle.core.biz.AppConfigManager;

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

	private static final XLogger logger = XLoggerFactory.getXLogger(StartUpListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("~~~~~~~~~~~~~~~开始加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		String[] beans = ctx.getBeanDefinitionNames();
		for (String bean : beans) {
			logger.info(bean);
		}
		logger.info("~~~~~~~~~~~~~~~初始化缓存数据~~~~~~~~~~~~~~~~~~~~");
		// 系统缓存
		AppConfigManager appConfigManager = ctx.getBean("appConfigManager", AppConfigManager.class);
		appConfigManager.initAppConfigForMem();
		// 业务缓存
		logger.info("~~~~~~~~~~~~~~~初始化缓存数据完成~~~~~~~~~~~~~~~~~~~~");

		logger.info("~~~~~~~~~~~~~~~成功加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("~~~~~~~~~~~~~~~开始关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~成功关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
