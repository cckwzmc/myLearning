package com.lyxmq.blog.publisher.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MasterMain {
	public static void main(String[] args) {
		ApplicationContext context = 
			new ClassPathXmlApplicationContext(new String[] { "classpath:/spring/ApplicationContext-task.xml" });
		context.getBean("sinaBlogJob");
	}
}
