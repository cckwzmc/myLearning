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
	//��������֤����
	//������֤������֤��ins_id����֤
	@Override
	public void handleMessage(Message msg) throws Fault {
		
	}
	
}
