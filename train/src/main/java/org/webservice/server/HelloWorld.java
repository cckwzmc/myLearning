package org.webservice.server;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface HelloWorld {
	public @WebResult(name="helloResult")String sayHello(@WebParam(name="name")String msg);
}
