package com.toney.publish.hessian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.toney.commons.constants.CommonsConstants;
import com.toney.commons.lang.CommonsResult;
import com.toney.publish.biz.TemplateManager;
import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;
import com.toney.publish.executor.PublishFactory;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :PublishHession
 * @DESCRIPTION :发布hession 接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 8, 2013
 *       </p>
 **************************************************************** 
 */
//@Service("publishHessian")
@SuppressWarnings("rawtypes")
public class PublishHessianImpl implements PublishHessian {
	
	private static final XLogger logger = XLoggerFactory.getXLogger(PublishHessianImpl.class);

	private PublishFactory publishFactory;
	private TemplateManager templateManagerFactory; 
	
	public void setPublishFactory(PublishFactory publishFactory) {
		this.publishFactory = publishFactory;
	}

	/**
	 * 非共用页面的出版
	 * @param context
	 * @return
	 */
	public CommonsResult publish(PublishContext context) {
		try {
			publishFactory.publish(context);
		} catch (PublishException e) {
			logger.error("发布失败 " + ToStringBuilder.reflectionToString(context), e);

			CommonsResult commonsResult = new CommonsResult();
			commonsResult.setResultCode(CommonsConstants.FAIL_RESULT_CODE);
			List<Map<String, String>> ml = new ArrayList<Map<String, String>>();
			Map<String, String> m = new HashMap<String, String>();
			m.put(CommonsConstants.FAIL_RESULT_CODE, e.getMessage());
			ml.add(m);
			return commonsResult;
		}
		return null;
	}

	public CommonsResult publicPublish(Long publicId){
		try {
			publishFactory.publishPublic(publicId);
		} catch (PublishException e) {
			logger.error("发布失败 publicId =" + publicId, e);

			CommonsResult commonsResult = new CommonsResult();
			commonsResult.setResultCode(CommonsConstants.FAIL_RESULT_CODE);
			List<Map<String, String>> ml = new ArrayList<Map<String, String>>();
			Map<String, String> m = new HashMap<String, String>();
			m.put(CommonsConstants.FAIL_RESULT_CODE, e.getMessage());
			ml.add(m);
			return commonsResult;
		}
		return null;
	}

	@Override
	public CommonsResult getTemplate(PublishContext context) {
		
		return null;
	}

	@Override
	public CommonsResult getPublicTemplate(Long publicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonsResult saveTemplate(PublishContext context, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommonsResult savePublicTemplate(Long publicId, String content) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param outFile
	 * @param tpl
	 * @param data
	 * @return
	 *//*
	public CommonsResult directPublish(String outFile, String tpl, Map<String, Object> data) {
		try {
			publishFactory.directPublish(outFile, tpl, data);
		} catch (PublishException e) {
			logger.error("发布失败 outFile =" + outFile + " tpl=" + tpl + "  " + ToStringBuilder.reflectionToString(data), e);

			CommonsResult commonsResult = new CommonsResult();
			commonsResult.setResultCode(CommonsConstants.FAIL_RESULT_CODE);
			List<Map<String, String>> ml = new ArrayList<Map<String, String>>();
			Map<String, String> m = new HashMap<String, String>();
			m.put(CommonsConstants.FAIL_RESULT_CODE, e.getMessage());
			ml.add(m);
			return commonsResult;
		}
		return null;
	}*/
}
