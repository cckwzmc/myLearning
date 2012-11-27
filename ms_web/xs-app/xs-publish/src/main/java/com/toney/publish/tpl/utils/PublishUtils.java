package com.toney.publish.tpl.utils;

import static com.toney.publish.service.PublishConstants.CUSTOMER_TEMPLATE_PATH_NAME;
import static com.toney.publish.service.PublishConstants.DEFAULT_TEMPLATE_PATH_NAME;
import static com.toney.publish.service.PublishConstants.FILE_SEPARATION;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.toney.core.model.SysConfigModel;
import com.toney.core.model.TemplateManagerModel;
import com.toney.publish.exception.PublishException;
import com.toney.publish.tpl.SiteParameter;

/**
 * @author toney.li 模版实用类。
 */
public class PublishUtils {

	/**
	 * @param tplSuffix
	 * @param string
	 * @return 获得首页模版路径。
	 */
	public static String getHomeIndexTemplatePath(String rootPath,SiteParameter site, String path, String name, String suffix) {
		
		return rootPath +FILE_SEPARATION+getTemplateStyle(site)+ path + FILE_SEPARATION + name + suffix;
	}

	/**
	 * 发布文件目录。
	 * @return
	 */
	public static String getPublishOutPath(){
		return null;
	}

	public static String getPublicTemplatePath(String tplRootPath, String templateStyle,String tplPublicPath, String name, String tplSuffix) {
		return tplRootPath + templateStyle+ tplPublicPath + FILE_SEPARATION + name + tplSuffix;
	}

	public static String getTemplateStyle(SiteParameter site) {
		Map<String ,SysConfigModel> sysConfig=site.getSysConfig();
		if(sysConfig.containsKey(CUSTOMER_TEMPLATE_PATH_NAME)&&sysConfig.get(CUSTOMER_TEMPLATE_PATH_NAME)!=null){
			return sysConfig.get(CUSTOMER_TEMPLATE_PATH_NAME).getCfgValue();
		}
		return sysConfig.get(DEFAULT_TEMPLATE_PATH_NAME).getCfgValue();
	}

	/**
	 * @param tplRootPath
	 * @param site
	 * @param tplSiteIndex
	 * @param tplSuffix
	 * @return
	 * 根据模版管理数据查找指定的模版路径.
	 * @throws PublishException 
	 */
	public static String getTemplatePath(String tplRootPath, SiteParameter site, String tplSuffix) throws PublishException {
		String tpl=tplRootPath+FILE_SEPARATION +getTemplateStyle(site)+ getTemplateValue(site) + tplSuffix;
		return tpl;
	}

	private static String getTemplateValue(SiteParameter site) throws PublishException {
		if(StringUtils.isBlank(site.getTemplateName())||StringUtils.isBlank(site.getGroupName())){
			throw new PublishException("缺少参数会导致找不到模版.");
		}
		List<TemplateManagerModel> list=site.getTemplateManager();
		for(TemplateManagerModel model:list){
			if(StringUtils.equalsIgnoreCase(model.getGroupName(), site.getGroupName())&&StringUtils.equalsIgnoreCase(model.getTemplateName(), site.getTemplateName())){
				return model.getValue();
			}
		}
		return "";
	}

	/**
	 * @param publishRootPath
	 * @param site
	 * @param tplSuffix
	 * @return
	 * @throws PublishException
	 * 发布路程方法.
	 */
	public static String getPublishPath(String publishRootPath, SiteParameter site, String tplSuffix) throws PublishException {
		return publishRootPath + getTemplatePublishPath(site) + tplSuffix;
	}

	private static String getTemplatePublishPath(SiteParameter site) throws PublishException {
		if(StringUtils.isBlank(site.getTemplateName())&&StringUtils.isBlank(site.getGroupName())){
			throw new PublishException("缺少参数会导致找不到模版.");
		}
		List<TemplateManagerModel> list=site.getTemplateManager();
		for(TemplateManagerModel model:list){
			if(StringUtils.equalsIgnoreCase(model.getGroupName(), site.getGroupName())&&StringUtils.equalsIgnoreCase(model.getTemplateName(), site.getTemplateName())){
				return model.getPublishPath();
			}
		}
		return "";
	}
}
