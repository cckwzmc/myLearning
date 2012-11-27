package com.toney.publish.tpl.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.core.cache.CacheManagerFactory;
import com.toney.core.exception.BusinessException;
import com.toney.core.model.ChannelDetailModel;
import com.toney.publish.tpl.utils.FreemarkerUtils;
import com.toney.publish.tpl.utils.FreemarkerUtils.InvokeType;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author toney.li
 * 
 */
public class ChannelListTag extends AbstractChannelTag {
	private static final XLogger logger = XLoggerFactory.getXLogger(ChannelListTag.class);

	private static final String TAG_DATA_NAME = "tag_list";
	@Autowired
	CacheManagerFactory ehcacheManagerFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException,
			IOException {

		InvokeType type = FreemarkerUtils.getInvokeType(params);
		if (InvokeType.body == type && body != null) {
			List<ChannelDetailModel> list=null;
			try {
				list = this.ehcacheManagerFactory.getAppContextCacheManager().getAppChannel();
			} catch (BusinessException e) {
				logger.error("判断数据查询失败",e);
				throw new TemplateException(env);
			}
			if (CollectionUtils.isNotEmpty(list)) {
				Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(params);
				paramWrap.put(TAG_DATA_NAME, DEFAULT_WRAPPER.wrap(list));
				Map<String, TemplateModel> origMap = FreemarkerUtils.addParamsToVariable(env, paramWrap);
				body.render(env.getOut());
				FreemarkerUtils.removeParamsFromVariable(env, paramWrap, origMap);
			}
		}
	}
}
