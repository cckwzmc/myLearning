package com.toney.crawler.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.toney.crawler.collection.biz.test.TestService;

public class MasterMain {
	public static void main(String[] args) {
		String[] configLocations=new String[]{"classpath:/spring/applicationContext*.xml"};
		ApplicationContext context =new ClassPathXmlApplicationContext(configLocations, true);
		TestService service=(TestService) context.getBean("testService");
		service.test();
	}
}
