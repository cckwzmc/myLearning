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
	public static List<String> getXmlFileName(){
		List<String> list=new ArrayList<String>();
		File files=new File(XMLPATH);
//		if(files.isDirectory()){
			File[] filelist=files.listFiles();
			for (int i = 0; i < filelist.length; i++) {
				if(!filelist[i].isDirectory())
				{
					list.add(filelist[i].getAbsolutePath());
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
		parseSiteList(getDocumentXml(getXmlFileName().get(0)));
//		System.out.println(parseSiteList(getDocumentXml(getXmlFileName().get(0))));
//		String type="<tr> <td class=\"even\" align=\"center\">游戏</td>";
//		System.out.println(convertXml(type));
	}
}
