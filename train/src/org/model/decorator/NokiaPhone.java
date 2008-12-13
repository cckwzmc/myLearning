package org.model.decorator;
/**
 * ：具体的对象
 * 在装饰UML图中位置：createComponent
 * @author Administrator
 *
 */
public class NokiaPhone  implements Phone{

	@Override
	public void callNumber() {
		System.out.println("=="+getClass().getName()+"==Method==callNumber==");
	}

	@Override
	public void sendMessage() {
		System.out.println("=="+getClass().getName()+"==Method==sendMessage==");
	}

}
