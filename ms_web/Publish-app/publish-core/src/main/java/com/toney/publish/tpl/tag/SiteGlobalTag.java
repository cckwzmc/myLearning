package com.toney.publish.tpl.tag;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.dal.model.SysConfigModel;
import com.toney.dal.service.DalMybatisManagerFactory;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * @author toney.li 全站变量，目前使用cache中的数据。
 */
public class SiteGlobalTag extends BeanModel implements TemplateMethodModelEx {

	public SiteGlobalTag() {
		super(new Object(), new BeansWrapper());
	}

	public SiteGlobalTag(Object object, BeansWrapper wrapper) {
		super(object, wrapper);
	}

	private static final XLogger logger = XLoggerFactory.getXLogger(SiteGlobalTag.class);

	@Autowired
	private DalMybatisManagerFactory dalMybatisManagerFactory;

	// private static final String TAG_DATA_NAME = "global";

	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// @Override
	// public void execute(Environment env, Map params, TemplateModel[]
	// loopVars, TemplateDirectiveBody body) throws TemplateException,
	// IOException {
	// try {
	// Map<String,String> configMap=appCacheManager.getGlobalMapConfiguration();
	// Map<String, TemplateModel> paramWrap = new HashMap<String,
	// TemplateModel>(
	// params);
	// paramWrap.put(TAG_DATA_NAME, DEFAULT_WRAPPER.wrap(configMap));
	// Map<String, TemplateModel> origMap =
	// FreemarkerUtils.addParamsToVariable(env, paramWrap);
	// body.render(env.getOut());
	// FreemarkerUtils.removeParamsFromVariable(env, paramWrap, origMap);
	// } catch (BusinessException e) {
	// logger.error("读取系统全局变量失败",e);
	// throw new FreemarkerTagException("读取系统全局变量失败");
	// }
	// }

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments == null || arguments.size() == 0) {
			throw new TemplateModelException("No properties key was specified");
		}
		Iterator it = arguments.iterator();
		String key = unwrap((TemplateModel) it.next()).toString();
		// 默认第二值为默认值，
		String defValue = null;
		if (it.hasNext()) {
			defValue = unwrap((TemplateModel) it.next()).toString();
		}
		try {
			List<SysConfigModel> list=dalMybatisManagerFactory.getGlobalManagerFactory().getSysConfigDao().getAll();
			Map<String, String> configMap = new HashMap<String, String>();
			if (CollectionUtils.isNotEmpty(list)) {
				for (SysConfigModel model : list) {
					configMap.put(model.getCfgName(), model.getCfgValue());
				}
			}
			String value = configMap.get(key);
			if (value == null) {
				value = defValue;
			}
			return wrap(value);
		} catch (Exception e) {
			throw new TemplateModelException(e);
		}
	}
}
