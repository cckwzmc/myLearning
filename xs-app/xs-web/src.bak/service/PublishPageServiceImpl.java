package com.toney.mvc.service;

import static com.toney.mvc.service.PublishConstants.TPL_SITE_INDEX;
import static com.toney.mvc.service.PublishConstants.TPL_SUFFIX;
import static com.toney.mvc.service.PublishConstants.UTF8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.toney.core.model.SysConfigModel;
import com.toney.mvc.tpl.SiteParameter;
import com.toney.mvc.tpl.utils.PublishUtils;
import com.toney.mvc.web.exception.PublishException;
import com.toney.mvc.web.processor.ServletContextResolver;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author toney.li
 * 页面出版实现类。
 */
public class PublishPageServiceImpl implements PublishPageService {
	
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishPageServiceImpl.class);
	
	@Override
	public void publishHomeIndex(SiteParameter site) throws PublishException {
		String tpl=PublishUtils.getHomeIndexTemplatePath(tplRootPath,site,tplIndexPath,TPL_SITE_INDEX,TPL_SUFFIX);
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String outFile=this.publishRootPath+"/index.html";
			publish(outFile, tpl, data);
		} catch (IOException e) {
			logger.error("IO EXCEPTION",e);
			throw new PublishException("IO EXCEPTION",e);
		} catch (TemplateException e) {
			logger.error("TEMPLATE EXCEPTION",e);
			throw new PublishException("TEMPLATE EXCEPTION",e);
		}
	}

	@Transactional(readOnly = true)
	public void publish(String outFile,String tpl, Map<String, Object> data)
			throws IOException, TemplateException {
		File f = new File(outFile);
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = null;
		try {
			// FileWriter不能指定编码确实是个问题，只能用这个代替了。
			out = new OutputStreamWriter(new FileOutputStream(f), UTF8);
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

	@Autowired
	private ServletContextResolver servletContextResolver;
	
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
	public void publishPublic(SiteParameter site) throws PublishException {
		String tpl=PublishUtils.getTemplatePath(tplRootPath,site,TPL_SUFFIX);
		Map<String, Object> data=new HashMap<String, Object>();
		try {
			String outFile=PublishUtils.getPublishPath(this.publishRootPath,site,TPL_SUFFIX);
			publish(outFile, tpl, data);
		} catch (IOException e) {
			logger.error("IO EXCEPTION",e);
			throw new PublishException("IO EXCEPTION",e);
		} catch (TemplateException e) {
			logger.error("TEMPLATE EXCEPTION",e);
			throw new PublishException("TEMPLATE EXCEPTION",e);
		}
	}
}
