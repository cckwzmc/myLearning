package com.lyxmq.blog.publisher.job;

import com.lyxmq.blog.publisher.sina.SinaBlog;

public class SinaBlogServiceJob {
	private SinaBlog sinaBlog=null;

	public SinaBlog getSinaBlog() {
		return sinaBlog;
	}

	public void setSinaBlog(SinaBlog sinaBlog) {
		this.sinaBlog = sinaBlog;
	}
	public void sinaBlogDoJob(){
		this.getSinaBlog().sinaPublishService();
	}
}	
