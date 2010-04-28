package com.lottery.ssq.job;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobMasterMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/lottery/ssq/ApplicationContext-task.xml" });
		context.getBean("lotterySsqJob");
	}
}
