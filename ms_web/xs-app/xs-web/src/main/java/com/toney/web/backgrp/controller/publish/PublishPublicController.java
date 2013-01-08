package com.toney.web.backgrp.controller.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.constants.AuthLevel;

/**
 * @author toney.li
 * 公用部分出版controller.
 */
@Controller
@RequestMapping(value="/sys/publish")
@AuthRequired(AuthLevel.STRICT)
public class PublishPublicController {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishPublicController.class);
//	@Autowired
//	private PublishPageService publishPageService;
//	@Autowired
//	private CacheManagerFactory ehcacheManagerFactory;
//	/**
//	 * @param position 页头的那个部分.
//	 * @param model
//	 */
//	@RequestMapping(value="publish_public",method=RequestMethod.POST,produces="application/json",params="format=json")
//	public String publishPublicHead(@RequestParam String templateName,@RequestParam String groupName,Model model){
//		try {
//			publishPageService.publishPublic(setSiteParameter(templateName,groupName));
//			JsonPackageWrapper json=new JsonPackageWrapper();
//			model.addAttribute(Constants.JSON_MODEL_DATA, json);
//		} catch (PublishException e) {
//			JsonPackageWrapper json=new JsonPackageWrapper();
//			json.setSuccess(false);
//			json.setSmsg(e.getMessage());
//			model.addAttribute(Constants.JSON_MODEL_DATA, json);
//		} catch (BusinessException e) {
//			JsonPackageWrapper json=new JsonPackageWrapper();
//			json.setSuccess(false);
//			json.setSmsg(e.getMessage());
//			model.addAttribute(Constants.JSON_MODEL_DATA, json);
//		}
//		return null;
//	}
//	private SiteParameter setSiteParameter(String templateName, String groupName) throws BusinessException {
//		SiteParameter site=new SiteParameter();
//		site.setGroupName(groupName);
//		site.setTemplateName(templateName);
//		site.setSysConfig(ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext());
//		site.setTemplateManager(ehcacheManagerFactory.getAppContextCacheManager().getTemplageManagerData());
//		return site;
//	}
}
