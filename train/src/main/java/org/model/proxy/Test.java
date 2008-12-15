package org.model.proxy;

public class Test {
public static void main(String[] args) {
	ProxyPerson proxy=new ProxyPerson();
	proxy.getInstance("org.model.proxy.PersonA");
	proxy.getInstance("org.model.proxy.PersonB");
}
}
