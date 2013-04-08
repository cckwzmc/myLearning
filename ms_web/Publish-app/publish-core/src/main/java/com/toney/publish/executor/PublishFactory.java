package com.toney.publish.executor;

import com.toney.publish.context.PublishContext;
import com.toney.publish.exception.PublishException;

public interface PublishFactory {

	void publish(PublishContext context) throws PublishException;

	/**
	 * 用于公共页面的出版.如果页头页尾.
	 * @param publicId
	 * @throws PublishException
	 */
	void publishPublic(Long publicId) throws PublishException;

}
