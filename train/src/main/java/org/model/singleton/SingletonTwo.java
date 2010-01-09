package org.model.singleton;

public class SingletonTwo
{
	private static SingletonTwo instance=null;
	public static synchronized SingletonTwo getInstance(){
		if(instance==null){
			return new SingletonTwo();
		}
		return instance;
	}
}
