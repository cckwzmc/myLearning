package com.myfetch.util;

public class Encoding {
	private String name;
	private String encoding;

	public Encoding(String name, String encoding) {
		this.name = name;
		this.encoding = encoding;
	}

	public String getName() {
		return name;
	}

	public String getEncoding() {
		return encoding;
	}
}