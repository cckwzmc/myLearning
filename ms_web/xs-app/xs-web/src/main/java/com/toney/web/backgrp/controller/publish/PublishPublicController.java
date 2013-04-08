package com.toney.web.backgrp.controller.publish;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.toney.commons.lang.Page;
import com.toney.core.biz.PublishPublicManager;
import com.toney.core.exception.BusinessException;
import com.toney.dal.bean.PublicSearchBean;
import com.toney.dal.model.TplPublicMappingModel;
import com.toney.web.commons.annotation.AuthRequired;
import com.toney.web.commons.constants.AuthLevel;
import com.toney.web.commons.constants.Constants;
import com.toney.web.commons.utils.JsonPackageWrapper;

/**
 * @author toney.li 公用部分出版controller.
 */
@Controller
@RequestMapping(value = "/sys/publish/public")
@AuthRequired(AuthLevel.STRICT)
public class PublishPublicController {
	private static final XLogger logger = XLoggerFactory.getXLogger(PublishPublicController.class);

	@Autowired
	private PublishPublicManager publishPublicManager;
	
	/**
	 * @return
	 * 公用页面出版页。
	 * @throws Exception 
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public String list(Model model,PublicSearchBean publicSearch) throws Exception{
		Page<TplPublicMappingModel> page=new Page<TplPublicMappingModel>();
		publicSearch.setIsEnabled(1);
		model.addAttribute("page",this.publishPublicManager.getTplPublicMappingModelAll(page,publicSearch));
		return "sys/publishmgr/publish_public";
	}

	/**
	 * @param position
	 *            页头的那个部分.
	 * @param model
	 */
	@RequestMapping(value = "publish_publicId", method = RequestMethod.POST, produces = "application/json", params = "format=json")
	public String publishPublicHeadById(Long publicId, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			this.publishPublicManager.publishPublic(publicId);
		} catch (BusinessException e) {
			logger.error("发布公共页面失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			logger.error("发布失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return null;
	}
	/**
	 * @param position
	 *            页头是否可用.
	 * @param model
	 */
	@RequestMapping(value = "public_enabled", method = RequestMethod.POST, produces = "application/json", params = "format=json")
	public String publishPublicEnabledById(Long publicId,Integer isEnabled, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			this.publishPublicManager.modifyEnabled(publicId,isEnabled);
		} catch (BusinessException e) {
			logger.error("发布公共页面失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			logger.error("发布失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return null;
	}
	/**
	 * @param position
	 *            批量页面发布.
	 * @param model
	 */
	@RequestMapping(value = "batch_publish", method = RequestMethod.POST, produces = "application/json", params = "format=json")
	public String batchPublishPublic(Long[] publicId, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			this.publishPublicManager.batchPublish(publicId);
		} catch (BusinessException e) {
			logger.error("发布公共页面失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			logger.error("发布失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return null;
	}
	/**
	 * @param position
	 *           批量禁用.
	 * @param model
	 */
	@RequestMapping(value = "batch_disabled", method = RequestMethod.POST, produces = "application/json", params = "format=json")
	public String batchPublicDisabled(Long[] publicId, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			this.publishPublicManager.batchModifyDisabled(publicId);
		} catch (BusinessException e) {
			logger.error("发布公共页面失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			logger.error("发布失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return null;
	}
	/**
	 * @param position
	 *           批量启用.
	 * @param model
	 */
	@RequestMapping(value = "batch_enabled", method = RequestMethod.POST, produces = "application/json", params = "format=json")
	public String batchPublicEnabled(Long[] publicId,Integer isEnabled, Model model) {
		JsonPackageWrapper json = new JsonPackageWrapper();
		try {
			this.publishPublicManager.batchModifyEnabled(publicId);
		} catch (BusinessException e) {
			logger.error("发布公共页面失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		} catch (Exception e) {
			logger.error("发布失败", e);
			json.setSuccess(false);
			json.setSmsg(e.getMessage());
		}
		model.addAttribute(Constants.JSON_MODEL_DATA, json);
		return null;
	}

}
