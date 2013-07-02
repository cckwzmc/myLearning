package com.toney.istyle.core.util;

import java.security.MessageDigest;

//字符串转换成MD5编码
public class SecurityMD5 {
	private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7","8", "9", "a", "b", "c", "d", "e", "f"};

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
	    for (int i = 0; i < b.length; i++) {
	      resultSb.append(byteToHexString(b[i]));
	    }
	    return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
	    if (n < 0){
	    	n = 256 + n;
	    }
	    int d1 = n / 16;
	    int d2 = n % 16;
	    return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String password) throws Exception {
		if(null == password){
			password = "";
		}
		MessageDigest md = MessageDigest.getInstance("MD5");
		return byteArrayToHexString(md.digest(password.getBytes()));
	}
}