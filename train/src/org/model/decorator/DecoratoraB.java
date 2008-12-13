package org.model.decorator;

public class DecoratoraB extends Decorator{


	@Override
	public void callNumber() {
		this.selfFunction("callNumber");
		this.phone.callNumber();
	}

	@Override
	public void sendMessage() {
		this.selfFunction("sendMessage");
		this.phone.sendMessage();
	}
	public void selfFunction(String methodName){
		System.out.println("==B == self == function "+methodName);
	}
}
