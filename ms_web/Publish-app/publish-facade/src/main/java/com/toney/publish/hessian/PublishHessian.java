package com.toney.publish.hessian;

import com.toney.commons.lang.CommonsResult;
import com.toney.publish.context.PublishContext;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :PublishHession
 * @DESCRIPTION :发布hession 接口
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Jan 8, 2013
 *       </p>
 **************************************************************** 
 */
public interface PublishHessian {
		
	
	/**
	 * @param context
	 * @return
	 */
	public CommonsResult publish(PublishContext context) ;

	/**
	 * @param outFile
	 * @param tpl
	 * @param data
	 * @return
	 */
	public CommonsResult publicPublish(Long publicId);
	
	/**
	 * 获取非公用模版信息
	 * @param context
	 * @return
	 */
	public CommonsResult getTemplate(PublishContext context);
	
	/**
	 * 获取公用页面模版信息
	 * @param publicId
	 * @return
	 */
	public CommonsResult getPublicTemplate(Long publicId);
	/**
	 * 保存非公用模版信息
	 * @param context
	 * @return
	 */
	public CommonsResult saveTemplate(PublishContext context,String content);
	
	/**
	 *  保存公用页面模版信息
	 * @param publicId
	 * @return
	 */
	public CommonsResult savePublicTemplate(Long publicId,String content);
}
