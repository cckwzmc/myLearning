package com.toney.dal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toney.dal.service.mybatis.ArticleManagerFactory;
import com.toney.dal.service.mybatis.ChannelManagerFactory;
import com.toney.dal.service.mybatis.GlobalManagerFactory;
import com.toney.dal.service.mybatis.TemplateManagerFactory;
import com.toney.dal.service.mybatis.UserManagerFactory;

/**
 * 
 *************************************************************** 
 * <p>
 * @CLASS :DalMybatisManagerFactoryImpl
 * @DESCRIPTION :Mybatis data access layer implements
 * @AUTHOR :toney.li
 * @VERSION :v1.0
 * @DATE :Dec 26, 2012
 *       </p>
 **************************************************************** 
 */
@Service("dalMybatisManagerFactory")
public class DalMybatisManagerFactoryImpl implements DalMybatisManagerFactory {
	
	@Autowired
	private  UserManagerFactory userManagerFactory;
	@Autowired
	private TemplateManagerFactory templateManagerFactory;
	@Autowired
	private GlobalManagerFactory globalManagerFactory;
	@Autowired
	private ArticleManagerFactory articleManagerFactory;
	@Autowired
	private ChannelManagerFactory channelManagerFactory;
	
	@Override
	public  UserManagerFactory getUserManagerFactory() {
		return this.userManagerFactory;
	}

	@Override
	public  TemplateManagerFactory getTemplateManagerFactory() {
		return this.templateManagerFactory;
	}

	@Override
	public GlobalManagerFactory getGlobalManagerFactory() {
		return this.globalManagerFactory;
	}

	@Override
	public ArticleManagerFactory getArticleManagerFactory() {
		return this.articleManagerFactory;
	}

	@Override
	public ChannelManagerFactory getChannelManagerFactory() {
		return this.channelManagerFactory;
	}

}
