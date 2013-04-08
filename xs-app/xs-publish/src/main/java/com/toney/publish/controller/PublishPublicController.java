package com.toney.publish.controller;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.toney.core.cache.CacheManagerFactory;
import com.toney.core.exception.BusinessException;
import com.toney.publish.annotation.AuthRequired;
import com.toney.publish.contants.AuthLevel;
import com.toney.publish.contants.Constants;
import com.toney.publish.exception.PublishException;
import com.toney.publish.service.PublishPageService;
import com.toney.publish.tpl.SiteParameter;
import com.toney.publish.utils.JsonPackageWrapper;

/**
 * @author toney.li
 * 公用部分出版controller.
 */
@Controller
@RequestMapping(value="/sys/publish")
@AuthRequired(AuthLevel.STRICT)
public class PublishPublicController {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishPublicController.class);
	@Autowired
	private PublishPageService publishPageService;
	@Autowired
	private CacheManagerFactory ehcacheManagerFactory;
	/**
	 * @param position 页头的那个部分.
	 * @param model
	 */
	@RequestMapping(value="publish_public",method=RequestMethod.POST,produces="application/json",params="format=json")
	public String publishPublicHead(@RequestParam String templateName,@RequestParam String groupName,Model model){
		try {
			publishPageService.publishPublic(setSiteParameter(templateName,groupName));
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
		return null;
	}
	private SiteParameter setSiteParameter(String templateName, String groupName) throws BusinessException {
		SiteParameter site=new SiteParameter();
		site.setGroupName(groupName);
		site.setTemplateName(templateName);
		site.setSysConfig(ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext());
		site.setTemplateManager(ehcacheManagerFactory.getAppContextCacheManager().getTemplageManagerData());
		return site;
	}
}
