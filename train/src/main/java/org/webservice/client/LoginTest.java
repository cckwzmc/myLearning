package org.webservice.client;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
import org.apache.xerces.xni.parser.XMLParserConfiguration;
import org.cyberneko.html.HTMLConfiguration;
import org.cyberneko.html.HTMLTagBalancingListener;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;
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
//			testIgnoredTags();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static void login(WebClient client) throws Exception {
		long time = System.currentTimeMillis();
		BasicClientCookie cookie = new BasicClientCookie("", "");
		client.getHttpClient().getCookieStore().addCookie(cookie);
		String loginPageUrl = "http://www.txt.cn/dede/login_publisher.php";
		logger.info("Requesting login page");
		NameValuePair data[] = { new BasicNameValuePair("userid", "admin"), new BasicNameValuePair("pwd", "admin"), new BasicNameValuePair("adminstyle", "newdedecms"), new BasicNameValuePair("dopost", "login") };
		String content = readInputStream(client.doPost(loginPageUrl, data, ""));
		logger.info("content ::: :" + content);
		loginPageUrl = "http://www.txt.cn/dede/index.php";
		content = readInputStream(client.doGet(loginPageUrl, ""));
		logger.info("get ::: :" + content);
		String makeHomepage = "http://www.txt.cn/dede/makehtml_homepage.php";
		content = readInputStream(client.doGet(makeHomepage, ""));
		logger.info("makehomepage " + content);
		DOMParser parser = new DOMParser();
		// 设置网页的默认编码
		parser.setProperty("http://cyberneko.org/html/properties/default-encoding", "gb2312");
		/*
		 * The Xerces HTML DOM implementation does not support namespaces and cannot represent XHTML documents with namespace information. Therefore, in order to use the default HTML DOM implementation with NekoHTML's DOMParser to parse XHTML documents, you must turn off
		 * namespace processing.
		 */
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		parser.parse(new InputSource(new StringReader(content)));
		Document doc = parser.getDocument();
		Node myNode = doc.getElementById("position");
		logger.info(myNode + "");
		DOMFragmentParser fragParser = new DOMFragmentParser();
		DocumentFragment node = new HTMLDocumentImpl().createDocumentFragment();
		InputStream is = new ByteArrayInputStream(content.getBytes());
		try {
			fragParser.parse(new InputSource(is), node);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}

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
