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
import java.util.UUID;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

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
	public static void genFetchId(String fromString,Document doc) {
		if(doc==null){
			System.out.println("documnt is null!!");
			return;
		}
		Node node=doc.selectSingleNode("//data/fetchid");
		Element e=(Element)node;
		if("".equals(e.getStringValue())){
			e.setText(fromString);
			SAXWriter writer=new SAXWriter();
			try {
				writer.write(doc);
			} catch (SAXException e1) {
				logger.error(e1.getMessage());
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String> parseBookTypeList(Document doc){
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map<String,String> retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/booktypemap");
		Element e=(Element)node;
		List eList=e.elements();
		for (Iterator iterator = eList.iterator(); iterator.hasNext();) {
			Element el = (Element)iterator.next();
			logger.info(el.getName()+"::"+el.getText());
			retMap.put(el.getName(), el.getText());
		}
		return retMap;
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
	public static Map<String, String> parseConverXml(Document doc) {
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map<String,String> retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/bookinfo");
		Element e=(Element)node;
		List eList=e.elements();
		for (Iterator iterator = eList.iterator(); iterator.hasNext();) {
			Element el = (Element)iterator.next();
			logger.info(el.getName()+"::"+el.getText());
			retMap.put(el.getName(), el.getText());
		}
		return retMap;
	}
	public static Map<String, String> parseChapterXml(Document doc) {
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map<String,String> retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/chapterlistinfo");
		Element e=(Element)node;
		List eList=e.elements();
		for (Iterator iterator = eList.iterator(); iterator.hasNext();) {
			Element el = (Element)iterator.next();
			logger.info(el.getName()+"::"+el.getText());
			retMap.put(el.getName(), el.getText());
		}
		return retMap;
	}
	
	public static Map<String, String> parseContentXml(Document doc) {
		if(doc==null){
			System.out.println("documnt is null!!");
			return null;
		}
		Map<String,String> retMap=new HashMap<String, String>();
		Node node=doc.selectSingleNode("//data/chaptercontent");
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
//		System.out.println(convertXml(type));
//		//书籍url
//		type="<td class=\"oddf\"><a href=\"[args1]\" target=\"_blank\">[*]</a>";
//		System.out.println(convertXml(type));
//		//最新章节
//		type="<td class=\"even\"><a href=\"[*]target=\"_blank\">[args1]</a></td>";
//		System.out.println(convertXml(type));
//		//书籍是否完结
//		type="<td class=\"even\" align=\"center\">[args1]</td>";
//		System.out.println(convertXml(type));
//		/**
//		 * 章节列表地址
//		 */
//		type="<a title=\"[*]%\" href=\"[args1]\" target=\"_blank\"><IMG  src=\"/images/dian.gif\"[*]border=0></a>";
//		System.out.println(convertXml(type));
//		type="<span class=\"hottextx\">内容简介：</span>[args1]</td>";
//		System.out.println(convertXml(type));
//		//缩略图
//		type="<img src=\"[args1]\" alt[*]vspace=\"5\" />";
//		System.out.println(convertXml(type));
//		//章节名称
//		type="<td class=\"ccss\"><div class=\"dccss\"><a href=\"[args1]\" alt=[*]>[args2]</a></div></td>";
//		System.out.println(convertXml(type));
//		type="<div id=\"content\">";
//		System.out.println(convertXml(type));
//		type="<script type=\"text/javascript\" src=\"/ad/3.js\"></script>";
//		System.out.println(convertXml(type));
//		type="<TD noWrap align=middle><A class=ahui12[*]>[args1]</a></TD>####<TD align=left><A class=ahui12 href=\"[args1]\"><font color=\"#006699\">[*]</font></a></TD>####<TD align=left><A class=ahui12[*]><font color=\"#006699\">[args1]</font></a></TD>####<TD align=left><A class=ahuang12[*]href=\"/html/book/[*]\">[args1]</a></TD>####<TD align=middle><FONT color=blue>[args1]</FONT></TD>";
//		type="<TR bgColor=#ffffff height=24>[*]<TD align=left>100</TD>[*]<TD noWrap align=middle><A class=ahui12[*]>[arg1]</a></TD>[*]<TD align=left><A class=ahui12[*]href=\"[arg1]\"><font color=\"#006699\">[arg1]</font></a></TD>[*]<TD align=left><A class=ahuang12[*]>[arg1]</a></TD>[*]<TD align=middle><A class=ahui12[*]<TD align=middle><FONT color=blue>[arg1]</FONT></TD>[*]</TR>";
//		System.out.println(convertXml(type));
//		type="<td height=\"22\" align=\"center\">[[*]玄幻魔法</a>]</td>##<td align=\"left\">[*]目录</a>]&nbsp;<a href=\"[args1]\">[*]</a></td>##<td align=\"left\">[*]目录</a>]&nbsp;<a href=\"[*]\">[args1]</a></td>##<td align=\"center\"><a href=\"/[*].aspx\">[args1]</a></td>##<td align=\"left\"><a href=\"/Html/Book/[*]\" target=\"_blank\">[args1]</a></td>##<td align=\"center\"><font color=blue>[args1]</font></td>";
//		System.out.println(convertXml(type));
		type="<img src=\"/[args1]\" width=\"120\" height=\"150\">";
		System.out.println(convertXml(type));
		type="(www.sdxsw.com)|http://www.sdxsw.com|http://bbs.sdxsw.com|http://wap.sdxsw.com|www.sdxsw.com|www.sdxsw.co|尽在sdxsw.com|手打小说，手打版小说，文字版小说，|s!d!x!s!w!.!c!o!m|欢迎您光临手打小说网，手机WAP站点已经开通，敬请访问：http://wap.sdxsw.com|sDxsw.com|sdxsw.com|手.打小.说.网|手.打.小.说.网|手打小说网|手.打小说网|s D x s w . c o m|最新手打小说，尽在|尽在|中文网|;移动书库|&lt;table width=\"((.|\\n)+?) cellpadding=\"0\"&gt;|&lt;DIV id=booktext((.|\\n)+?)align=\"left\"&gt;|&lt;div id=\"booktext\"((.|\\n)+?)align=\"left\"&gt;|&lt;div id=\"booktext\"((.|\\n)+?)padding-right:12\"&gt;|&lt;img src=\"((.|\\n)+?)border=\"0\" /&gt;|内容请见:[a-zA-z]+://[^\\s]*\\d|&lt;tr&gt;|&lt;/tr&gt;|&lt;td&gt;|&lt;/td&gt;|&lt;/div&gt;|&lt;/DIV&gt;|&lt;/table&gt;|&lt;div align=\"center\"&gt;&lt;IMG SRC=\"((.|\\n)+?)border=0&gt;&lt;/div&gt;|隆重向大家推荐|(w.w.w.s.d.x.s.w.com)|全文字阅读及下载|全文字小说阅读，尽在|WWW.nilongdao.com|翠微居首发：www.cuiweiju.com";
		System.out.println(convertXml(type));
	}

}
