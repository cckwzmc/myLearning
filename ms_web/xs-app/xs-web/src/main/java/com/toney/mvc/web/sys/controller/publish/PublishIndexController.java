package com.toney.mvc.web.sys.controller.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.core.cache.CacheManagerFactory;
import com.toney.core.exception.BusinessException;
import com.toney.mvc.service.PublishPageService;
import com.toney.mvc.tpl.SiteParameter;
import com.toney.mvc.web.annotation.AuthRequired;
import com.toney.mvc.web.contants.AuthLevel;
import com.toney.mvc.web.contants.Constants;
import com.toney.mvc.web.exception.PublishException;
import com.toney.mvc.web.utils.JsonPackageWrapper;

/**
 * @author toney.li
 * 首页出版 Controller；
 */
@Controller
@RequestMapping(value="/sys/publish")
@AuthRequired(AuthLevel.STRICT)
public class PublishIndexController {
	
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishIndexController.class);

	@Autowired
	PublishPageService publishPageService;
	@Autowired
	private CacheManagerFactory ehcacheManagerFactory;
	
	/**
	 * @param model
	 * @return
	 * @throws Exception
	 * 首页出版.
	 */
	@RequestMapping(value="publish_index",method=RequestMethod.POST,produces="application/json",params="format=json")
	public String publishIndex(Model model){
		logger.info("...开始首页出版...");
		try {
			this.publishPageService.publishHomeIndex(setSiteParameter());
			JsonPackageWrapper json=new JsonPackageWrapper();
			model.addAttribute(Constants.JSON_MODEL_DATA, json);
		} catch (PublishException e) {
			JsonPackageWrapper json=new JsonPackageWrapper();
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
			model.addAttribute(Constants.JSON_MODEL_DATA, json);
		} catch (BusinessException e) {
			JsonPackageWrapper json=new JsonPackageWrapper();
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
			model.addAttribute(Constants.JSON_MODEL_DATA, json);
		}
		logger.info("...首页出版完成...");
		
		return null;
	}

	private SiteParameter setSiteParameter() throws BusinessException {
		SiteParameter site=new SiteParameter();
		site.setSysConfig(ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext());
		site.setTemplateManager(ehcacheManagerFactory.getAppContextCacheManager().getTemplageManagerData());
		return site;
	}
}