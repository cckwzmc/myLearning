package com.toney.mvc.service;

import com.toney.mvc.tpl.SiteParameter;
import com.toney.mvc.web.exception.PublishException;

/**
 * @author toney.li
 * 内容出版服务。
 */
public interface PublishPageService {

	public void publishHomeIndex( SiteParameter site) throws PublishException;
	void publishPublic(SiteParameter site) throws PublishException;

}
