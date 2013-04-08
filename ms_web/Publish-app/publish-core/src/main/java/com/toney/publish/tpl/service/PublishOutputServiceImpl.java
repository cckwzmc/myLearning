package com.toney.publish.tpl.service;

import static com.toney.commons.constants.CommonsConstants.DEFAULT_CHAR_ENCODE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishOutputService
 * @DESCRIPTION :文件输出服务类.
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 6, 2013
 *       </p>
 **************************************************************** 
 */

@Transactional
public class PublishOutputServiceImpl implements PublishOutputService {
	private static final XLogger logger=XLoggerFactory.getXLogger(PublishOutputServiceImpl.class);
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private MessageSource tplMessageSource;
	private String tplRootPath;
	private String tplIndexPath;
	private String publishRootPath;
	
	public void setTplMessageSource(MessageSource tplMessageSource) {
		this.tplMessageSource = tplMessageSource;
	}

	public void setTplRootPath(String tplRootPath) {
		this.tplRootPath = tplRootPath;
	}

	public void setTplIndexPath(String tplIndexPath) {
		this.tplIndexPath = tplIndexPath;
	}

	public void setPublishRootPath(String publishRootPath) {
		this.publishRootPath = publishRootPath;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	/**
	 * @param outFile 输出文件路径
	 * @param tpl 模板路径
	 * @param data 模板需求的数据
	 * @throws IOException
	 * @throws TemplateException
	 */
	@Override
	@Transactional(readOnly = true)
	public void publishFile(String outFile, String tpl, Map<String, Object> data) throws IOException, TemplateException {
		logger.debug("出版时间"+System.currentTimeMillis());
		File f = new File(publishRootPath+"/"+outFile);
		File parent = f.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(f), DEFAULT_CHAR_ENCODE);
			Template template = this.freeMarkerConfigurer.getConfiguration().getTemplate(tpl);
			template.process(data, out);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
