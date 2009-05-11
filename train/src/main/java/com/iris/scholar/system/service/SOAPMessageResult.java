package com.iris.scholar.system.service;

public class SOAPMessageResult {
	private String messageId;
	private int resultCount = -1;

	public SOAPMessageResult(String messageId2, int retInt) {
		this.messageId=messageId2;
		this.resultCount=retInt;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
