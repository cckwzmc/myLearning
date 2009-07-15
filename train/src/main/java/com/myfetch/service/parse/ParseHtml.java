package com.myfetch.service.parse;

import com.myfetch.service.http.HttpHtmlService;

public class ParseHtml {
	public String parseContent(String url){
		HttpHtmlService httpService=new HttpHtmlService();
		String html=httpService.getHtmlContent(url);
		return html;
	}
	/**
	 * 读取xml文件，文件格式为
	 * <sitename></sitename>
	 * <listurl></listurl>
	 * @return
	 */
	public String getFetchUrls(){
		String url="";
		
		return url;
	}
}
