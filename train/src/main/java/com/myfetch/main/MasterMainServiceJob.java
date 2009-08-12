package com.myfetch.main;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.myfetch.service.MyFetchService;

public class MasterMainServiceJob extends QuartzJobBean {
	private MasterMainService fetchService = null;

	public MasterMainService getFetchServiceJ() {
		return fetchService;
	}

	public void setFetchServiceJ(MasterMainService fetchServiceJ) {
		this.fetchService = fetchServiceJ;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		fetchService.fetchDataService();
	}
	public void fetchDataService(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "classpath:/myfetch/ApplicationContext-database.xml", "classpath:/myfetch/applicationContext-dao.xml", "classpath:/myfetch/applicationContext-service.xml" });
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("myfetch/fetchxml/fetchSite.properties");
			MyFetchService service = (MyFetchService) context.getBean("myfetchService");
			MyFetchService.bootPath=pro.getProperty("imgDownloadDir");
//			service.disposeBookMap("aishuzhe");
//
			service.disposeBookList((String) pro.get("sitename"));
			service.disposeBookConver(pro.getProperty("bookconversite"));
			service.disposeBookChapter((String) pro.getProperty("chaptersite"));
			service.disposeChapterContent(pro.getProperty("chaptercontentsite"));
			service.saveDataToDedecms(pro.getProperty("miscsite"));
			// 发布完成自动发布
			pro.clear();
			pro = PropertiesLoaderUtils.loadAllProperties("myfetch/publisher/publisherUrl.properties");
			service.dedePublisher(pro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
