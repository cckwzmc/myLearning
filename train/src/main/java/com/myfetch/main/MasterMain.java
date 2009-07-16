package com.myfetch.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myfetch.service.MyFetchService;

public class MasterMain {
	public static void main(String[] args) {
		// ParseHtml getcontent=new ParseHtml();
		// getcontent.parseContent("");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {
						"classpath:/myfetch/ApplicationContext-database.xml",
						"classpath:/myfetch/applicationContext-dao.xml",
						"classpath:/myfetch/applicationContext-service.xml" });

		MyFetchService service = (MyFetchService) context
				.getBean("myfetchService");
		service.disposeBookList();
	}
}
