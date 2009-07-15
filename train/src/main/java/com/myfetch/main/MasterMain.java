package com.myfetch.main;

import com.myfetch.service.http.HttpHtmlService;
import com.myfetch.service.parse.ParseHtml;

public class MasterMain {
	public static void main(String[] args) {
		ParseHtml getcontent=new ParseHtml();
		getcontent.parseContent("");
	}
}
