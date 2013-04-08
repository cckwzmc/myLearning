package com.toney.publish.tpl.service;

import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.toney.publish.BaseManagerTestCase;
import com.toney.publish.tpl.PublishContext;
import com.toney.publish.tpl.manager.PublishFactory;
import com.toney.publish.tpl.model.PublishTplModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.toney.publish.tpl.service.PublishFactory
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 18, 2012
 *       </p>
 **************************************************************** 
 */
public class PublishFactoryTestCase extends BaseManagerTestCase {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishFactoryTestCase.class);
	@Autowired
	private PublishFactory publishFactory;
	@Test
	public void testFactoryInit(){
		logger.info("PublishFactoryTestCase setup ...");
		for(long i=0;i<1;i++){
			PublishContext context=new PublishContext();
			PublishTplModel publishTplModel=new PublishTplModel();
			publishTplModel.setHomePage(true);
			publishTplModel.setChannelActivityListId(i);
			context.setPublishTplModel(publishTplModel);
			try {
				publishFactory.publish(context);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+"~~~~~~~~~");
		}
	}
	public static void main(String[] args) {
		ApplicationContext context1=new ClassPathXmlApplicationContext(new String[]{"classpath:/applicationContext-publish-resources.xml","classpath:/applicationContext-publish.xml"});
		
		logger.info("PublishFactoryTestCase setup ...");
		PublishFactory publishFactory=(PublishFactory) context1.getBean("publishFactory");
		for(long i=0;i<10;i++){
			PublishContext context=new PublishContext();
			PublishTplModel publishTplModel=new PublishTplModel();
			publishTplModel.setHomePage(true);
			publishTplModel.setChannelActivityListId(i);
			context.setPublishTplModel(publishTplModel);
			try {
				publishFactory.publish(context);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(i+"~~~~~~~~~");
		}
	}
}
