package org.webservice.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.http.NameValuePair;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.xerces.parsers.AbstractSAXParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.apache.xpath.XPathAPI;
import org.cyberneko.html.HTMLConfiguration;
import org.cyberneko.html.HTMLTagBalancingListener;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			login(new WebClient());
			// testIgnoredTags();
//			 testNoke() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void testNoke() {
		DOMParser parser = new DOMParser();
		try {
			// 设置网页的默认编码
			parser.setProperty("http://cyberneko.org/html/properties/default-encoding", "gb2312");
			/*
			 * The Xerces HTML DOM implementation does not support namespaces and cannot represent XHTML documents with namespace information. Therefore, in order to use the default HTML DOM implementation with NekoHTML's DOMParser to parse XHTML documents, you must turn off
			 * namespace processing.
			 */
			parser.setFeature("http://xml.org/sax/features/namespaces", false);

			String strURL = "http://product.dangdang.com/product.aspx?product_id=9317290";
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(strURL).openStream()));
			parser.parse(new InputSource(in));
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Document doc = parser.getDocument();
		// tags should be in upper case
		String productsXpath = "/HTML/BODY/DIV[2]/DIV[4]/DIV[2]/DIV/DIV[3]/UL[@class]/LI[9]";
		NodeList products;
		try {
			products = XPathAPI.selectNodeList(doc, productsXpath);
			System.out.println("found: " + products.getLength());
			Node node = null;
			for (int i = 0; i < products.getLength(); i++) {
				node = products.item(i);
				System.out.println(i + ":\n" + node.getTextContent());
			}
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	protected static void login(WebClient client) throws Exception {
		long time = System.currentTimeMillis();
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = "http://my.blog.sina.com.cn/login.php?url=%2F";
		NameValuePair data[] = { new BasicNameValuePair("loginname", "artmm@sina.com"), new BasicNameValuePair("passwd", "yb654321")};
		String content = readInputStream(client.doPost(loginPageUrl, data, ""));
		logger.info("content ::: :" + content);
		loginPageUrl = "http://www.txt.cn/dede/index.php";
		content = readInputStream(client.doGet(loginPageUrl, ""));
		logger.info("get ::: :" + content);
		String makeHomepage = "http://www.txt.cn/dede/makehtml_homepage.php";
		//主页发布
		NameValuePair homeData[] = { new BasicNameValuePair("position", "../index.html"), new BasicNameValuePair("templet", "default/index.htm"), new BasicNameValuePair("dopost", "make") };
		content = readInputStream(client.doPost(makeHomepage, homeData, ""));
		System.out.println(content);
//		String updateCachePage="http://www.txt.cn/dede/sys_cache_up.php";
//		NameValuePair cacheData[] = { new BasicNameValuePair("dopost", "ok")};
//		content = readInputStream(client.doPost(updateCachePage, cacheData, ""));
//		System.out.println(content);
		if(content.indexOf("成功更新主页")>-1){
			String makeListPage = "http://www.txt.cn/dede/makehtml_list_action.php";
			NameValuePair listData[] = { new BasicNameValuePair("typeid", "0"), new BasicNameValuePair("upnext", "1"), new BasicNameValuePair("maxpagesize", "1") };
			content = readInputStream(client.doPost(makeListPage, listData, ""));
		}
		System.out.println(content);
//		content = readInputStream(client.doGet(makeHomepage, ""));
//		logger.info("makehomepage " + content);
//		DOMParser parser = new DOMParser();
//		// 设置网页的默认编码
//		parser.setProperty("http://cyberneko.org/html/properties/default-encoding", "gb2312");
//		/*
//		 * The Xerces HTML DOM implementation does not support namespaces and cannot represent XHTML documents with namespace information. Therefore, in order to use the default HTML DOM implementation with NekoHTML's DOMParser to parse XHTML documents, you must turn off
//		 * namespace processing.
//		 */
//		parser.setFeature("http://xml.org/sax/features/namespaces", false);
//		parser.parse(new InputSource(new StringReader(content)));
//		Document doc = parser.getDocument();
//		Node myNode = doc.getElementById("position");
//		logger.info(myNode + " aa "+myNode.getNodeValue());
//		myNode=doc.getElementById("stafrm");
//		logger.info(myNode + " aa "+myNode.getNodeValue());
//		DOMFragmentParser fragParser = new DOMFragmentParser();
//		DocumentFragment node = new HTMLDocumentImpl().createDocumentFragment();
//		InputStream is = new ByteArrayInputStream(content.getBytes());
//		try {
//			fragParser.parse(new InputSource(is), node);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SAXException se) {
//			se.printStackTrace();
//		}

	}

	public static void testIgnoredTags() throws Exception {
		String string = "<html><head><title>foo</title></head>" + "<body>" + "<body onload='alert(123)'>" + "<div>" + "<form action='foo'>" + "  <input name='text1' id='text1'/>" + "</div>" + "</form>" + "</body></html>";

		final TestParser parser = new TestParser();
		final StringReader sr = new StringReader(string);
		final XMLInputSource in = new XMLInputSource(null, "foo", null, sr, null);
		parser.parse(in);
		logger.info(parser.messages.toString());
	}

	/**
	 * Reads an inputstream and converts it to a string. Note that this is rather memory intensive, if you do not need random access in the inputstream you should iterate sequentially over the lines using readLine()
	 * 
	 * @param is
	 *            the inputstream to convert
	 * @return the content of the input stream
	 * @throws IOException
	 *             if reading the inputstream fails
	 */
	protected static String readInputStream(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuffer buffer = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		is.close();
		return buffer.toString();
	}
}

class TestParser extends AbstractSAXParser implements HTMLTagBalancingListener {
	final List messages = new ArrayList();

	TestParser() throws Exception {
		super(new HTMLConfiguration());
		setFeature("http://cyberneko.org/html/features/balance-tags/ignore-outside-content", true);
	}

	public void startElement(QName element, XMLAttributes attributes, Augmentations augs) throws XNIException {

		messages.add("start " + element.rawname);
		super.startElement(element, attributes, augs);
	}

	public void ignoredEndElement(QName element, Augmentations augs) {
		messages.add("ignored end " + element.rawname);
	}

	public void ignoredStartElement(QName element, XMLAttributes attrs, Augmentations augs) {
		messages.add("ignored start " + element.rawname);
	}

	public void endElement(QName element, Augmentations augs) throws XNIException {
		messages.add("end " + element.rawname);
		super.endElement(element, augs);
	}
}
