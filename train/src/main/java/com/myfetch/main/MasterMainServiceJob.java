package com.myfetch.main;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.myfetch.service.MyFetchService;

public class MasterMainServiceJob {
	public void fetchDataService() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/myfetch/ApplicationContext-database.xml", "classpath:/myfetch/applicationContext-dao.xml", "classpath:/myfetch/applicationContext-service.xml" });
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("myfetch/fetchxml/fetchSite.properties");
			MyFetchService service = (MyFetchService) context.getBean("myfetchService");
			MyFetchService.bootPath = pro.getProperty("imgDownloadDir");
//			service.disposeBookMap("aishuzhe.com");
			
//			isrunsite=1
//			isrunbookconver=1
//			isrunchapter=1
//			isrunchaptercontent=1
//			isrunmisc=1
//			isrundede=1
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunsite"))))
			{
				service.disposeBookList((String) pro.get("sitename"));
			}	
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunbookconver"))))
			{
				service.disposeBookConver(pro.getProperty("bookconversite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunchapter"))))
			{
				service.disposeBookChapter((String) pro.getProperty("chaptersite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunchaptercontent"))))
			{
				service.disposeChapterContent(pro.getProperty("chaptercontentsite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunmisc"))))
			{
				service.saveDataToDedecms(pro.getProperty("miscsite"));
			}
//			// 发布完成自动发布
			pro.clear();
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrundede"))))
			{
				pro = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisherUrl.properties");
				service.dedePublisher(pro);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/myfetch/ApplicationContext-database.xml", "classpath:/myfetch/applicationContext-dao.xml", "classpath:/myfetch/applicationContext-service.xml" });
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("myfetch/fetchxml/fetchSite.properties");
			MyFetchService service = (MyFetchService) context.getBean("myfetchService");
			
//			isrunsite=1
//			isrunbookconver=1
//			isrunchapter=1
//			isrunchaptercontent=1
//			isrunmisc=1
//			isrundede=1
			MyFetchService.bootPath = pro.getProperty("imgDownloadDir");
//			service.disposeBookMap("aishuzhe.com");
			//
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunsite"))))
			{
				service.disposeBookList((String) pro.get("sitename"));
			}	
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunbookconver"))))
			{
				service.disposeBookConver(pro.getProperty("bookconversite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunchapter"))))
			{
				service.disposeBookChapter((String) pro.getProperty("chaptersite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunchaptercontent"))))
			{
				service.disposeChapterContent(pro.getProperty("chaptercontentsite"));
			}
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrunmisc"))))
			{
				service.saveDataToDedecms(pro.getProperty("miscsite"));
			}
//			// 发布完成自动发布
			pro.clear();
			if("1".equals(ObjectUtils.toString(pro.getProperty("isrundede"))))
			{
				pro = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisherUrl.properties");
				service.dedePublisher(pro);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
