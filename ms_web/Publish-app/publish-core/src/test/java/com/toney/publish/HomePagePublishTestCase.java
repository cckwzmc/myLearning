package com.toney.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.toney.commons.constants.CommonsConstants;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;
import com.toney.publish.executor.PublishFactory;
import com.toney.publish.model.PublishTplModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :HomePagePublishTestCase
 * @DESCRIPTION :首页出版测试
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 5, 2013
 *       </p>
 **************************************************************** 
 */
public class HomePagePublishTestCase extends BaseManagerTestCase {
	private static final XLogger logger = XLoggerFactory.getXLogger(HomePagePublishTestCase.class);
	@Autowired
	PublishFactory publishFactory;

	public void testPublishHomePage() throws PublishException {
		PublishContext context = new PublishContext();
		PublishTplModel publishTplModel = new PublishTplModel();
		publishTplModel.setTplTypeCode(CommonsConstants.TPL_HOME_PAGE_INDEX);
		context.setPublishTplModel(publishTplModel);
		publishFactory.publish(context);
	}

	public static void main(String[] args) throws PublishException {
		ApplicationContext context1 = new ClassPathXmlApplicationContext(new String[] { "classpath:/applicationContext-publish.xml" ,"classpath:/applicationContext-dao.xml"});

		logger.info("PublishFactoryTestCase setup ...{}","aaa");
		PublishFactory publishFactory = (PublishFactory) context1.getBean("publishFactory");
		
		PublishContext context = new PublishContext();
		PublishTplModel publishTplModel = new PublishTplModel();
		publishTplModel.setTplTypeCode(CommonsConstants.TPL_HOME_PAGE_INDEX);
		for(long i=0;i<Integer.MAX_VALUE;i++){
			publishTplModel.setDetailMaxId(i);
			context.setPublishTplModel(publishTplModel);
			publishFactory.publish(context);
			logger.info("current test number :::{}{}",i,"--------------");
		}
	}
}
