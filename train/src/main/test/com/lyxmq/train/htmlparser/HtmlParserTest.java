package com.lyxmq.train.htmlparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.myfetch.service.http.HttpHtmlService;

public class HtmlParserTest extends TestCase {
	public void testParserHtml() {
		String html = HttpHtmlService.getHtmlContent("http://about.validator.nu/htmlparser/");
		// input file
		DOMParser parser =new DOMParser();
		BufferedReader in = new BufferedReader(new StringReader(html));
		try {
			parser.parse(new InputSource(in));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = parser.getDocument();
		assertNull(doc);
	}
}
