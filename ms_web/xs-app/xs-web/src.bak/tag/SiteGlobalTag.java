package com.toney.mvc.tpl.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.cache.CacheManagerFactory;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.SysConfigModel;
import com.toney.mvc.tpl.exception.FreemarkerTagException;
import com.toney.mvc.tpl.utils.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;


/**
 * @author toney.li
 * 全站变量，目前使用cache中的数据。
 */
public class SiteGlobalTag implements TemplateDirectiveModel {
	
	private static final XLogger logger=XLoggerFactory.getXLogger(SiteGlobalTag.class);
	
	private static final  String TAG_NAME="global_param";
	
	private static final  String TAG_DATA_NAME="global";
	@Autowired
	CacheManagerFactory  ehcacheManagerFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException,
			IOException {
		try {
			Map<String,SysConfigModel> cfgMap=ehcacheManagerFactory.getAppContextCacheManager().getApplicatonContext();
			Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
					params);
			paramWrap.put(TAG_DATA_NAME, DEFAULT_WRAPPER.wrap(cfgMap));
			Map<String, TemplateModel> origMap = FreemarkerUtils
					.addParamsToVariable(env, paramWrap);
			body.render(env.getOut());
			FreemarkerUtils.removeParamsFromVariable(env, paramWrap, origMap);
		} catch (BusinessException e) {
			logger.error("读取系统全局变量失败",e);
			throw new FreemarkerTagException("读取系统全局变量失败");
		}
	}
}
