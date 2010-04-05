package com.myfetch.service.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;


public class HtmlParseUtils {
	private static Logger logger=LoggerFactory.getLogger(HtmlParseUtils.class);
	public static String getElementById(String html, String id, String charset) {
		String retValue = "";
		DOMParser parser = new DOMParser();
		// 设置网页的默认编码
		try {
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding", charset);
			parser.setProperty("http://cyberneko.org/html/properties/names/elems", "lower");
			// input file
			BufferedReader in = new BufferedReader(new StringReader(html));
			parser.parse(new InputSource(in));
			Document doc = parser.getDocument();
			// 获得body节点，以此为根，计算其文本内容
			Node node = doc.getElementById(id);
			if (node!=null&&node.getNodeType() == Node.TEXT_NODE) {
				retValue=StringUtils.trim(node.getNodeValue());
			}
		} catch (SAXNotRecognizedException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (SAXNotSupportedException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (SAXException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (IOException e) {
			logger.error("解析html失败"+e.getMessage());
		}
		return retValue;
	}
	public static String clickHrefById(String html, String id, String charset) {
		String retValue = "";
		DOMParser parser = new DOMParser();
		// 设置网页的默认编码
		try {
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding", charset);
			

			// input file
			BufferedReader in = new BufferedReader(new StringReader(html));
			parser.parse(new InputSource(in));
			Document doc = parser.getDocument();
			// 获得body节点，以此为根，计算其文本内容
			Node node = doc.getElementById(id);
			if (node.getNodeType() == Node.TEXT_NODE) {
				retValue=node.getNodeValue().trim();
			}
		} catch (SAXNotRecognizedException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (SAXNotSupportedException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (SAXException e) {
			logger.error("解析html失败"+e.getMessage());
		} catch (IOException e) {
			logger.error("解析html失败"+e.getMessage());
		}
		return retValue;
	}
	public static NameValuePair[] getElementsPostData(String publishHtml, String charset) {
		DOMParser parser = new DOMParser();
		// 设置网页的默认编码
		BufferedReader in = new BufferedReader(new StringReader(publishHtml));
		try {
			parser.parse(new InputSource(in));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Document doc = parser.getDocument();
		NodeList input = doc.getElementsByTagName("input");
		NodeList select = doc.getElementsByTagName("select");
		NodeList textarea  = doc.getElementsByTagName("textarea");
		List<Node> list=new ArrayList<Node>();
		for(int i=0;i<input.getLength();i++){
			list.add(input.item(i));
		}
		for(int i=0;i<select.getLength();i++){
			list.add(select.item(i));
		}
		for(int i=0;i<textarea.getLength();i++){
			list.add(textarea.item(i));
		}
		NameValuePair[] data=new BasicNameValuePair[input.getLength()+select.getLength()+textarea.getLength()];
		for(int i=0;i<list.size();i++){
			Node node=list.get(i);
			NamedNodeMap map= node.getAttributes();
			String name="";
			String value="";
			for (int j=0;j<map.getLength();j++) {
				Node aNode=map.item(j);
				if("name".equals(aNode.getNodeName())){
					name=aNode.getNodeValue();
				}
				if("value".equals(aNode.getNodeName())){
					value=aNode.getNodeValue();
				}
			}
			if("blog_body".equals(name)){
				value="kkkkkkkkkkkkkkk";
			}
				data[i]=new BasicNameValuePair(name,value);
//			}
		}
		return data;
	}
	public static void main(String[] args) {
		WebHtmlUtils client=new WebHtmlUtils();
		String html="";
		try {
			html = InputStreamUtils.readInputStream(client.doGet("http://www.sina.com.cn", ""), "GB2312");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(HtmlParseUtils.getElementById(html, "page", "GB2312"));
	}
}
