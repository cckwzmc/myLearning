package com.toney.test;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

	public static void main(String[] args) {
		TestService test = new TestService();
		System.out.println(" a=" + test.a + " a1=" + test.a1 + " a2=" + test.a2);
		test.a.put("1", "1");
		test.a1.put("2", "2");
		test.a2.put("3", "3");
		System.out.println(" a=" + test.a + " a1=" + test.a1 + " a2=" + test.a2);
		TestService test1 = new TestService();
		System.out.println(" a=" + test.a + " a1=" + test.a1 + " a2=" + test.a2);
		test.a.remove("1");
		test.a1.remove("2");
		test.a2.remove("3");
		System.out.println(" a=" + test.a + " a1=" + test.a1 + " a2=" + test.a2);
		Map<String, String> m = new HashMap<String, String>();
		m.put("m", "m");
		Integer a=null;
		System.out.println(a.intValue());
	}

}
