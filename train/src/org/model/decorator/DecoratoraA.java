package org.model.decorator;

public class DecoratoraA extends Decorator{

	@Override
	public void callNumber() {
		this.selfFunction("callNumber");
		super.callNumber();
	}
	@Override
	public void sendMessage() {
		this.selfFunction("sendMessage");
		super.sendMessage();
	}
	public void selfFunction(String methodName){
		System.out.println("==A == self == function "+methodName);
	}

}
