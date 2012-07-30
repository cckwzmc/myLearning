package com.toney.mvc.web.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 根据模板，生产内容
 * @AUTHOR : Liang Yongxu@xiu.com 
 * @DATE :2012-5-15 下午7:16:41             
 * </p>
 **************************************************************** 
 */
public class TemplateUtil {
	
	public static String genEmailContent(FreeMarkerConfig freemarkerConfig, int type, Map<String, Object> map) throws IOException, TemplateException{
		
		//模板路径
		String temlatePath = EmailUtil.genTemplatePath(type);
		Template tpl= freemarkerConfig.getConfiguration().getTemplate(temlatePath);   
		
		//解析模板并替换动态数据
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);
		return content;
		
	}

}
