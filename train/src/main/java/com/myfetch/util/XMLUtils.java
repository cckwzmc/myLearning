package com.myfetch.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.helpers.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import org.dom4j.Element;

import ch.qos.logback.core.util.FileUtil;

public class XMLUtils {

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
	
	public static Map parseSiteList(Document doc){
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/siteinfo");
		Element e=(Element)node;
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
				list.add(filelist[i].getAbsolutePath());
			}
//		}else{
//			list.add(XMLPATH);
//		}
		return list;
	} 
	public static void main(String[] args) {
		parseSiteList(getDocumentXml(getXmlFileName().get(0)));
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource("")+"myfetch/fetchxml");
	}
}
