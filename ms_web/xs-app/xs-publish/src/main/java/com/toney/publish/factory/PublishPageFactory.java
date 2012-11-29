package com.toney.publish.factory;

import com.toney.publish.exception.PublishException;

/**
 * 出版页面的总入口,以工厂模式的方式对外提供服务。<br/>
 * 在此工厂下会有多个子工厂，<br>
 * @author toney.li
 *
 */
public interface PublishPageFactory {
	
	/**
	 * 各种类型首页出版(包括1、),如163首页，163新闻首页，163体育首页.
	 * @param context
	 * @throws PublishException
	 */
	public PublishIndexFactory getPublishIndexFactory() throws PublishException;
	/**
	 * 频道页出版
	 * @param context
	 * @throws PublishException
	 */
	public PublishChannelFactory getPublishChannelFactory() throws PublishException;
	
	/**
	 * 专题页，活动页，主题页
	 * @param context
	 * @throws PublishException
	 */
	public PublishTopicFactory getPublishTopicFactory() throws PublishException; 
	/**
	 * 专题页，活动页，主题页
	 * @param context
	 * @throws PublishException
	 */
	public PublishDetailFactory getPublishDetailFactory() throws PublishException; 
	
	/**
	 * 列表页
	 * @param context
	 * @return
	 * @throws PublishException
	 */
	public PublishListFactory getPublishListFactory() throws PublishException;
}
