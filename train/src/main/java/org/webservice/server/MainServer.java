package org.webservice.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;

public class MainServer {

	public static void main(String[] args) {
//		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
//		factory.setServiceClass(HelloWorldImpl.class);
//		factory.setAddress("http://localhost:8089/HelloWorld");
//		factory.getInInterceptors().add(new ScholarFetchServiceInterceptor("scode"));
//		factory.getInInterceptors().add(new LoggingInInterceptor());
//		Server server = factory.create();
//		server.start();
		List list=new ArrayList ();
		list.add("dfdf");
		list.add("dfdf");list.add("dfdf");
		System.out.println(ObjectUtils.toString("a"+list+"a"));
	}

}
