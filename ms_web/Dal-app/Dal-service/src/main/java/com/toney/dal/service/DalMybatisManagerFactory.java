package com.toney.dal.service;

import com.toney.dal.service.mybatis.ArticleManagerFactory;
import com.toney.dal.service.mybatis.ChannelManagerFactory;
import com.toney.dal.service.mybatis.GlobalManagerFactory;
import com.toney.dal.service.mybatis.TemplateManagerFactory;
import com.toney.dal.service.mybatis.UserManagerFactory;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :DalMybatisManagerFactory
 * @DESCRIPTION :Mybatis data access layer
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 25, 2012
 *       </p>
 **************************************************************** 
 */
public interface DalMybatisManagerFactory {
	public UserManagerFactory getUserManagerFactory();

	public TemplateManagerFactory getTemplateManagerFactory();

	public GlobalManagerFactory getGlobalManagerFactory();

	public ArticleManagerFactory getArticleManagerFactory();

	public ChannelManagerFactory getChannelManagerFactory();

}
