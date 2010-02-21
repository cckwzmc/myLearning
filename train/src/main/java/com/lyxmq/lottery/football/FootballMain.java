package com.lyxmq.lottery.football;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class FootballMain {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FootballMain.class);
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml",
				"classpath:/football/applicationContext-database.xml", "classpath:/football/applicationContext-dao.xml",
				"classpath:/football/applicationContext-service.xml" });
		try {
			FootballLotteryService service = (FootballLotteryService) context.getBean("footballService");
			 service.genAllCode();
			// service.getCurrentExpertMergeResult();
//			service.getCurrentExpertSingleResult();
			// service.saveAllRedResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
