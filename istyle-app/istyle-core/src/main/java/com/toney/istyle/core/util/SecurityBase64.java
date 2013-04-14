package com.toney.istyle.core.util;

import org.apache.commons.codec.binary.Base64;

public class SecurityBase64 {

	public static String encodeAsString(String plaintext) throws Exception {
		return new String(Base64.encodeBase64(plaintext.getBytes()));
	}

	public static String decodeAsString(String b64string) throws Exception {
		return new String(Base64.decodeBase64(b64string.getBytes()));
	}


	public static void main(String args[]) throws Exception {
		System.out.println(encodeAsString("dick.xiao@xiu.com"));
		System.out.println(decodeAsString("MTIzNDU2"));
	}
}
