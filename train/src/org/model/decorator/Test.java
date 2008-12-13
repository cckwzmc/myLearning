package org.model.decorator;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("dd");
		NokiaPhone n_phone = new NokiaPhone();
		DecoratoraA a = new DecoratoraA();
		DecoratoraB b = new DecoratoraB();
		a.setDecorator(n_phone);
		b.setDecorator(a);
		b.callNumber();
	}

}
