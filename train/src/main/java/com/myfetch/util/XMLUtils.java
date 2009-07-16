package com.myfetch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;

public class XMLUtils {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(XMLUtils.class);
	private final static String XMLPATH=(Thread.currentThread().getContextClassLoader().getResource("")+"myfetch/fetchxml").replace("file:", "");
	public static Document getDocumentXml(String filename){
		Document doc=null;
		FileInputStream fi = null;
		try {
			fi=new FileInputStream(filename);
			SAXReader  sb = new SAXReader ();
			doc = sb.read(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> parseSiteList(Document doc){
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map<String,String> retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/siteinfo");
		Element e=(Element)node;
		List eList=e.elements();
		for (Iterator iterator = eList.iterator(); iterator.hasNext();) {
			Element el = (Element)iterator.next();
			logger.info(el.getName()+"::"+el.getText());
			retMap.put(el.getName(), el.getText());
		}
		return retMap;
	}
	public static String parseCoverInfo(){
		String retstr="";
		return retstr;
	}
	
	public static String parseTableInfo(){
		String retstr="";
		return retstr;
	}
	public static String parseChapterInfo(){
		String retstr="";
		return retstr;
	}
	public static List<String> getXmlFileName(String sitename){
		List<String> list=new ArrayList<String>();
		File files=new File(XMLPATH);
//		if(files.isDirectory()){
			File[] filelist=files.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				String[] names=StringUtils.split(sitename,",");
				if(!filelist[i].isDirectory())
				{
					boolean flag=false;
					for (int j = 0; j < names.length; j++) {
						if(StringUtils.contains(filelist[i].getName(), "listrul_"+names[j]+".xml")){
							flag=true;
						}
					}
					if(flag){
						list.add(filelist[i].getAbsolutePath());
					}
				}	
			}
//		}else{
//			list.add(XMLPATH);
//		}
		return list;
	} 
	public static String convertHtml(String str){
		String retStr="";
		retStr=StringEscapeUtils.escapeHtml(str);
		return retStr;
	}
	public static String convertXml(String str){
		String retStr="";
		retStr=StringEscapeUtils.escapeXml(str);
		return retStr;
	}
	public static void main(String[] args) {
//		parseSiteList(getDocumentXml(getXmlFileName().get(0)));
//		System.out.println(parseSiteList(getDocumentXml(getXmlFileName().get(0))));
		//书籍类别
		String type="<td class=\"odd\"> [<a href=\"[*]\" class=\"sideA\" target=\"_blank\">[args1]</a>]</td>";
		System.out.println(convertXml(type));
		//书籍url
		type="<td class=\"oddf\"><a href=\"[args1]\" target=\"_blank\">[*]</a>";
		System.out.println(convertXml(type));
		//最新章节
		type="<td class=\"even\"><a href=\"[*]target=\"_blank\">[args1]</a></td>";
		System.out.println(convertXml(type));
		//书籍是否完结
		type="<td class=\"even\" align=\"center\">[args1]</td>";
		System.out.println(convertXml(type));
		/**
		 * 章节列表地址
		 */
		type="<a title=\"[*]%\" href=\"[args1]\" target=\"_blank\"><IMG  src=\"/images/dian.gif\"[*]border=0></a>";
		System.out.println(convertXml(type));
		type="<span class=\"hottextx\">内容简介：</span>[args1]</td>";
		System.out.println(convertXml(type));
		//缩略图
		type="<img src=\"[args1]\" alt[*]vspace=\"5\" />";
		System.out.println(convertXml(type));
	}
}
