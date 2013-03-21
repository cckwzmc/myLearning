package com.toney.istyle.mvc.freemaker;

import java.io.File;
import java.io.IOException;

import freemarker.cache.TemplateLoader;
import freemarker.cache.FileTemplateLoader;
import org.springframework.core.io.Resource;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 *************************************************************** 
 * <p>
 * 
 * @CLASS :FreeMarkerConfigurerExt.java
 * @DESCRIPTION : 对Spring 3.1。0的实现进行扩展，用于支持FreeMarker symlinks 文件的加载
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Mar 21, 2013 <br/>
 *       @see: FreeMarkerConfigurationFactory#setTemplateLoaderPath
 *       </p>
 **************************************************************** 
 */
public class FreeMarkerConfigurerExt extends FreeMarkerConfigurer {
	protected TemplateLoader getTemplateLoaderForPath(String templateLoaderPath) {
		if (isPreferFileSystemAccess()) {
			// Try to load via the file system, fall back to
			// SpringTemplateLoader
			// (for hot detection of template changes, if possible).
			try {
				Resource path = getResourceLoader().getResource(templateLoaderPath);
				File file = path.getFile(); // will fail if not resolvable in
											// the file system
				if (logger.isDebugEnabled()) {
					logger.debug("Template loader path [" + path + "] resolved to file path [" + file.getAbsolutePath() + "]");
				}
				// It will allow access to template files that are accessible
				// through
				// symlinks that point outside the base directory.
				return new FileTemplateLoader(file, true);
			} catch (IOException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("Cannot resolve template loader path [" + templateLoaderPath + "] to [java.io.File]: using SpringTemplateLoader as fallback", ex);
				}
				return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
			}
		} else {
			// Always load via SpringTemplateLoader (without hot detection of
			// template changes).
			logger.debug("File system access not preferred: using SpringTemplateLoader");
			return new SpringTemplateLoader(getResourceLoader(), templateLoaderPath);
		}
	}

}
