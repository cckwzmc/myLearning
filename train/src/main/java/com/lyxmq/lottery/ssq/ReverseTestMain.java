package com.lyxmq.lottery.test;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 反向处理，把不存在媒体预测的结果选出来，两种方式 1、把媒体的结果都合并处理。 2、把媒体的结果分开处理。
 * 
 * @author Administrator
 * 
 */
public class ReverseTestMain {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ReverseTestMain.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/applicationContext.xml","classpath:/lottory/applicationContext-database.xml",
				"classpath:/lottory/applicationContext-dao.xml", "classpath:/lottory/applicationContext-service.xml" });
		try {
			lottoryService service = (lottoryService) context.getBean("lottoryService");
			service.testCode();
//			service.getCurrentExpertMergeResult();
//			service.getCurrentExpertSingleResult();
//			service.saveAllRedResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
