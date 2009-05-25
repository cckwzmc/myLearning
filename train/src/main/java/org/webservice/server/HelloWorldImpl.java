package org.webservice.server;

import javax.jws.WebService;

@WebService
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello(String msg) {
		System.out.println("Hello world is be called!");
		return "Hello "+msg;
	}

}
