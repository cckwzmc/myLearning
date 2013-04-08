package com.toney.publish.factory;

import com.toney.publish.exception.PublishException;
import com.toney.publish.factory.manager.PublishIndexManager;

public interface PublishIndexFactory {
	public PublishIndexManager getPublishIndexManager() throws PublishException;
}
