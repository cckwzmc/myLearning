package com.toney.publish;

import java.util.HashMap;

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
public class PublicPagePublishTestCase extends BaseManagerTestCase {
	private static final XLogger logger = XLoggerFactory.getXLogger(PublicPagePublishTestCase.class);
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
		
//		publishFactory.directPublish("/public/public_head_head.html","/default/public/public_head_head.html", new HashMap<String, Object>());
//		publishFactory.directPublish("/public/public_head_nav.html","/default/public/public_head_nav.html", new HashMap<String, Object>());
//		publishFactory.directPublish("/public/public_footer_index.html","/default/public/public_footer_index.html", new HashMap<String, Object>());
	}
}
