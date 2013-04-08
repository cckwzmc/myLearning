package com.toney.publish.tpl.service;

import com.toney.publish.exception.PublishException;
import com.toney.publish.tpl.PublishContext;

/**
 * @author toney.li
 * 内容出版服务。
 */
public interface PublishPageService {

	public void publishHomeIndex( PublishContext site) throws PublishException;
	void publishPublic(PublishContext site) throws PublishException;

}
