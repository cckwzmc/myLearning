package com.toney.publish.factory.manager;

import com.toney.publish.exception.PublishException;
import com.toney.publish.factory.PublishContext;

public interface PublishIndexManager {
	public void publishIndex(PublishContext context) throws PublishException;
}	
