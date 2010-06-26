package com.lottery.ssq.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobMasterMain {
	private static final Logger logger = LoggerFactory.getLogger(JobMasterMain.class);

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					new String[] { "classpath:/lottery/ssq/ApplicationContext-task.xml" });
			context.getBean("lotterySsqJob");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
