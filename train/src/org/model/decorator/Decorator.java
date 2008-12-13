package org.model.decorator;

public class Decorator implements Phone{

	Phone phone;
	public void setDecorator(Phone phone){
		this.phone=phone;
	}
	@Override
	public void callNumber() {
		this.phone.callNumber();
	}

	@Override
	public void sendMessage() {
		this.phone.sendMessage();
	}

}
