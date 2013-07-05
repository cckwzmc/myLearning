package com.toney.istyle.mvc.freemarker.tag;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.toney.istyle.bo.AppConfigBO;
import com.toney.istyle.core.biz.AppConfigManager;
import com.toney.istyle.core.exception.ManagerException;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :AppConfigsTag.java
 * @DESCRIPTION : 全局变量tag
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 23, 2013
 *       </p>
 **************************************************************** 
 */
public class AppConfigsTag extends BeanModel implements TemplateMethodModelEx {

	private static final XLogger LOGGER = XLoggerFactory.getXLogger(AppConfigsTag.class);

	public AppConfigsTag() {
		super(new Object(), new BeansWrapper());
	}

	public AppConfigsTag(Object object, BeansWrapper wrapper) {
		super(object, wrapper);
	}

	@Autowired
	AppConfigManager appConfigManager;

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		if (CollectionUtils.isEmpty(arguments)) {
			throw new TemplateModelException(" Arguments can not empty");
		}
		Iterator it = arguments.iterator();
		String key = unwrap((TemplateModel) it.next()).toString();
		try {
			Map<String, AppConfigBO> map = this.appConfigManager.getAppConfigs();
			return map.get(key) == null ? map.get(key) : wrap(map.get(key));
		} catch (ManagerException e) {
			LOGGER.error("NOT FOUND KEY={} OBJECT", key, e);
			throw new TemplateModelException();
		}
	}

}
