package com.lyxmq.train.htmlparser;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfetch.service.http.util.HttpHtmlService;

public class HtmlParserTest extends TestCase {
	private static final Logger logger=LoggerFactory.getLogger(HtmlParserTest.class);
	public void testParserHtml() {
		String html = HttpHtmlService.getHtmlContent("http://sports.sina.com.cn/l/ssqleitai/");
		// input file
		Source source = new Source(html);
		Element element=source.getElementById("table1");
		List<Element> eList=element.getAllElements("tr");
		for(Element e:eList.subList(1, eList.size())){
			List<Element> tdList=e.getAllElements("td");
			Element tdE=tdList.get(3);
			List<Element> divList=tdE.getAllElements("div");
			for(Element eDiv:divList){
				logger.info(eDiv.getContent().toString());
			}
			logger.info(tdE.getContent().toString());
		}
	}
}
