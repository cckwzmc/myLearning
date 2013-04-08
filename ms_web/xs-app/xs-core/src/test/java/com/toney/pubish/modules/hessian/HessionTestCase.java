package com.toney.pubish.modules.hessian;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.junit.Test;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.toney.commons.constants.CommonsConstants;
import com.toney.core.biz.BaseManagerTestCase;
import com.toney.publish.context.PublishContext;
import com.toney.publish.hessian.PublishHessian;
import com.toney.publish.model.PublishTplModel;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :HessionTestCase
 * @DESCRIPTION :功能描述
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 9, 2013
 *       </p>
 **************************************************************** 
 */
public class HessionTestCase extends BaseManagerTestCase {
	private static final XLogger logger = XLoggerFactory.getXLogger(HessionTestCase.class);

	// @Autowired
	// PublishHessian publishHessianClient;

	@Test
	public void testPublishHessian() throws MalformedURLException {

		// 创建HessianProxyFactory实例
		HessianProxyFactory factory = new HessianProxyFactory();

		String hessianUrl = "http://127.0.0.1:8080/publish-modules/remoting/publishHessian";
		// String hessianUrl = req.getParameter("hessianURL");
		// 获得Hessian服务的远程引用
		PublishHessian publishHessianClient = (PublishHessian) factory.create(PublishHessian.class, hessianUrl);
		PublishContext context = new PublishContext();
		PublishTplModel publishTplModel = new PublishTplModel();
		publishTplModel.setTplTypeCode(CommonsConstants.TPL_HOME_PAGE_INDEX);
		context.setPublishTplModel(publishTplModel);
		publishHessianClient.publish(context);
		logger.info("current test number :::{}{}", "xs-web", "--------------");

	}

	public static void main(String[] args) throws MalformedURLException {
		// 创建HessianProxyFactory实例
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setOverloadEnabled(true);
		String hessianUrl = "http://127.0.0.1:8080/publish-modules/remoting/publishHessian";
		// String hessianUrl = req.getParameter("hessianURL");
		// 获得Hessian服务的远程引用
		PublishHessian publishHessianClient = (PublishHessian) factory.create(PublishHessian.class, hessianUrl);
		// publishHessianClient.directPublish("/public/public_head_head.html","/default/public/public_head_head.html",
		// new HashMap<String, Object>());
		// publishHessianClient.directPublish("/public/public_head_nav.html","/default/public/public_head_nav.html",
		// new HashMap<String, Object>());
		PublishContext context = new PublishContext();
		PublishTplModel publishTplModel = new PublishTplModel();
		publishTplModel.setTplTypeCode(CommonsConstants.TPL_HOME_PAGE_INDEX);
		context.setPublishTplModel(publishTplModel);
		publishHessianClient.publish(context);
		logger.info("current test number :::{}{}", "xs-web", "--------------");

	}
}
