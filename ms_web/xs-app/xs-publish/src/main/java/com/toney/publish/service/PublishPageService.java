package com.toney.publish.service;

import com.toney.publish.exception.PublishException;
import com.toney.publish.tpl.SiteParameter;

/**
 * @author toney.li
 * 内容出版服务。
 */
public interface PublishPageService {

	public void publishHomeIndex( SiteParameter site) throws PublishException;
	void publishPublic(SiteParameter site) throws PublishException;

}
