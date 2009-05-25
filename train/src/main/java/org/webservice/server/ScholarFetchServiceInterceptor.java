package org.webservice.server;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;


public class ScholarFetchServiceInterceptor extends AbstractPhaseInterceptor<Message>{

	private String scode=null;
	public ScholarFetchServiceInterceptor() {
		super(Phase.RECEIVE);
	}
	public ScholarFetchServiceInterceptor(String phase) {
		super(phase);
	}

	public ScholarFetchServiceInterceptor(String phase,String scode) {
		super(phase);
		this.scode=scode;
	}
	//拦截器验证请求
	//请求串验证串的验证和ins_id的验证
	@Override
	public void handleMessage(Message msg) throws Fault {
		
	}
	
}
