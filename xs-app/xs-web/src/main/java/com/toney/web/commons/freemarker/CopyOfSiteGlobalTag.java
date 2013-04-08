package com.toney.web.commons.freemarker;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.biz.AppCacheManager;
import com.toney.core.exception.BusinessException;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * @author toney.li
 * 全站变量，目前使用cache中的数据。
 */
public class CopyOfSiteGlobalTag implements TemplateDirectiveModel {
	
	private static final XLogger logger=XLoggerFactory.getXLogger(CopyOfSiteGlobalTag.class);
	
	@Autowired
	private AppCacheManager appCacheManager; 
	
	private static final  String TAG_DATA_NAME="global";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException,
			IOException {
		try {
			Map<String,String> configMap=appCacheManager.getGlobalMapConfiguration();
			Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
					params);
			paramWrap.put(TAG_DATA_NAME, DEFAULT_WRAPPER.wrap(configMap));
			Map<String, TemplateModel> origMap = FreemarkerUtils.addParamsToVariable(env, paramWrap);
			body.render(env.getOut());
			FreemarkerUtils.removeParamsFromVariable(env, paramWrap, origMap);
		} catch (BusinessException e) {
			logger.error("读取系统全局变量失败",e);
			throw new FreemarkerTagException("读取系统全局变量失败");
		}
	}
}
