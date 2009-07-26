package com.myfetch.main;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.myfetch.service.MyFetchService;

public class MasterMain {
	public static void main(String[] args) {
		// ParseHtml getcontent=new ParseHtml();
		// getcontent.parseContent("");
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/myfetch/ApplicationContext-database.xml", "classpath:/myfetch/applicationContext-dao.xml", "classpath:/myfetch/applicationContext-service.xml" });
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("myfetch/fetchxml/fetchSite.properties");
			MyFetchService service = (MyFetchService) context.getBean("myfetchService");
//			service.disposeBookMap("aishuzhe");
			
			
//			service.disposeBookList((String) pro.get("sitename"));
//			service.disposeBookConver(pro.getProperty("bookconversite"));
//			 service.disposeBookChapter((String) pro.getProperty("chaptersite"));
//			 service.disposeChapterContent(pro.getProperty("chaptercontentsite"));
			 service.saveDataToDedecms(pro.getProperty("miscsite"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
