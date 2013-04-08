package com.toney.publish.tpl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.toney.commons.constants.CommonsConstants;
import com.toney.dal.model.TemplateMappingModel;
import com.toney.dal.service.DalMybatisManagerFactory;
import com.toney.publish.exception.PublishException;
import com.toney.publish.tpl.PublishContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author toney.li 页面出版实现类。
 */
@Service("publishPageService")
@Transactional
public class PublishPageServiceImpl implements PublishPageService {

	private static final XLogger logger = XLoggerFactory.getXLogger(PublishPageServiceImpl.class);
	
	@Autowired
	DalMybatisManagerFactory dalMybatisManagerFactory; 
	
	@Override
	public void publishHomeIndex(PublishContext site) throws PublishException {
		List<TemplateMappingModel	> mappingList = dalMybatisManagerFactory.getTemplateManagerFactory().getTemplateMappingDao().getAll();
		// PublishUtils.getHomeIndexTemplatePath(tplRootPath,site,tplIndexPath,TPL_SITE_INDEX,TPL_SUFFIX);
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String outFile = this.publishRootPath + "/index.html";
			publish(outFile,"", data);
		} catch (IOException e) {
			logger.error("IO EXCEPTION", e);
			throw new PublishException("IO EXCEPTION", e);
		} catch (TemplateException e) {
			logger.error("TEMPLATE EXCEPTION", e);
			throw new PublishException("TEMPLATE EXCEPTION", e);
		}
	}

	@Transactional(readOnly = true)
	public void publish(String outFile, String tpl, Map<String, Object> data) throws IOException, TemplateException {
		File f = new File(outFile);
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = null;
		try {
			// FileWriter不能指定编码确实是个问题，只能用这个代替了。
			out = new OutputStreamWriter(new FileOutputStream(f), CommonsConstants.DEFAULT_CHAR_ENCODE);
			Template template = this.freeMarkerConfigurer.getTemplate(tpl);
			template.process(data, out);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	private Configuration freeMarkerConfigurer;
	private MessageSource tplMessageSource;
	private String tplRootPath;
	private String tplIndexPath;
	private String publishRootPath;

	// @Autowired
	// private ServletContextResolver servletContextResolver;

	public void setPublishRootPath(String publishRootPath) {
		this.publishRootPath = publishRootPath;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer.getConfiguration();
	}

	public void setTplMessageSource(MessageSource tplMessageSource) {
		this.tplMessageSource = tplMessageSource;
	}

	public void setTplRootPath(String tplRootPath) {
		this.tplRootPath = tplRootPath;
	}

	public void setTplIndexPath(String tplIndexPath) {
		this.tplIndexPath = tplIndexPath;
	}

	@Override
	public void publishPublic(PublishContext site) throws PublishException {
		String tpl = "";// PublishUtils.getTemplatePath(tplRootPath,site,TPL_SUFFIX);
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String outFile = "";// PublishUtils.getPublishPath(this.publishRootPath,site,TPL_SUFFIX);
			publish(outFile, tpl, data);
		} catch (IOException e) {
			logger.error("IO EXCEPTION", e);
			throw new PublishException("IO EXCEPTION", e);
		} catch (TemplateException e) {
			logger.error("TEMPLATE EXCEPTION", e);
			throw new PublishException("TEMPLATE EXCEPTION", e);
		}
	}
}
