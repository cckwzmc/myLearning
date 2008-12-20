package org.model.factory;

public class Test {
	public static void main(String[] args) {
		Factory factory=new OperationFactoryA();
		factory.getOperation().said();
		factory.getOperation().saidMessage();
	}
}
