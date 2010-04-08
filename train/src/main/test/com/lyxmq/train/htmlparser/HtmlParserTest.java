package com.lyxmq.train.htmlparser;

import java.util.Map;

import junit.framework.TestCase;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myfetch.service.http.util.HttpHtmlService;

public class HtmlParserTest extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger(HtmlParserTest.class);

	public void testParserHtml() {
		String html = HttpHtmlService
				.getHtmlContent(
						"http://trade.500wan.com/pages/trade/ssq/project_list_sz_in.php?key=allmoney&sort=desc&pages_num=1&page=1&type=&ticheng_term=-1&state_term=2&totalcount_term=0~0&permoney_term=0~0&plan_term=0~0&baodi_term=0&currentsort=desc&currentkey=renqi&lotid=3&playid=1&expect=10039&rnd=91050",
						"GB2312");
		
		String jsonStr=StringUtils.substringBetween(html,"data:[","],pagestr");
		logger.info(jsonStr);
		logger.info("=="+JSONUtils.mayBeJSON(jsonStr));
		if(JSONUtils.mayBeJSON(jsonStr)){
			JSONObject jsonOop=new JSONObject();
//			JSONArray array=jsonOop.getJSONArray(jsonStr);
			//jsonOop=JSONObject.fromObject(jsonStr);
//			JSONArray array=JSONArray.fromObject(jsonStr);
//			jSon
			logger.info(JSONUtils.isArray(html)+"");
			Map map=JSONUtils.getProperties(jsonOop);
		}
		// // input file
		// Source source = new Source(html);
		// Element element=source.getElementById("table1");
		// List<Element> eList=element.getAllElements("tr");
		// for(Element e:eList.subList(1, eList.size())){
		// List<Element> tdList=e.getAllElements("td");
		// Element tdE=tdList.get(3);
		// List<Element> divList=tdE.getAllElements("div");
		// for(Element eDiv:divList){
		// logger.info(eDiv.getContent().toString());
		// }
		// logger.info(tdE.getContent().toString());
		// }
	}
}
