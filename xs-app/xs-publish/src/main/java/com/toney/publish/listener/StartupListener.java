package com.toney.publish.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.toney.core.biz.ServiceStartInitManager;
import com.toney.core.exception.BusinessException;

public class StartupListener implements ServletContextListener {

	private static final Logger logger=LoggerFactory.getLogger(StartupListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("~~~~~~~~~~~~~~~开始加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		String[] beans = ctx.getBeanDefinitionNames();
        for (String bean : beans) {
        	logger.info(bean);
        }
		ServiceStartInitManager initmgr= (ServiceStartInitManager) ctx.getBean("serviceStartInitManager");
		try {
			initmgr.initApplicationData();
		} catch (BusinessException e) {
			logger.info("~~~~~~~~~~~~~~~失败加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
			throw new RuntimeException("失败加载初始化数据");
		}
		logger.info("~~~~~~~~~~~~~~~成功加载初始化数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("~~~~~~~~~~~~~~~开始关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
		logger.info("~~~~~~~~~~~~~~~成功关闭应用释放相关数据~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
