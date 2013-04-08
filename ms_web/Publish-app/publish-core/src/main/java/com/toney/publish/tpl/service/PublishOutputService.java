package com.toney.publish.tpl.service;

import java.io.IOException;
import java.util.Map;

import freemarker.template.TemplateException;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishOutputService
 * @DESCRIPTION : 出版文件输出类
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 7, 2013
 *       </p>
 **************************************************************** 
 */
public interface PublishOutputService {

	/**
	 * @param outFile 输出文件路径
	 * @param tpl 模板路径
	 * @param data 绑定数据.
	 * @throws IOException
	 * @throws TemplateException
	 */
	void publishFile(String outFile, String tpl, Map<String, Object> data) throws IOException, TemplateException;

}
