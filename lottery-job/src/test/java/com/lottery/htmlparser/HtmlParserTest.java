package com.lottery.htmlparser;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.myfetch.service.http.util.HttpHtmlService;

public class HtmlParserTest extends TestCase {
	private static final Logger logger = LoggerFactory.getLogger(HtmlParserTest.class);

	public void testParserHtml() {
		String html = HttpHtmlService
				.getHtmlContent(
						"http://trade.500wan.com/pages/trade/ssq/project_list_sz_in.php?key=allmoney&sort=desc&pages_num=1&page=4&type=&ticheng_term=-1&state_term=2&totalcount_term=0~0&permoney_term=0~0&plan_term=0~0&baodi_term=0&currentsort=desc&currentkey=renqi&lotid=3&playid=1&expect=10040&rnd=91050",
						"GB2312");

		String jsonStr = StringUtils.substringBetween(html, "data:[", "],pagestr");
		JSONArray array = JSONArray.fromObject("[" + jsonStr + "]");
		String download="http://trade.500wan.com/pages/trade/ssq/project_ssqshow.php?pid=@pid@&@nowdate@";
		Date nowdate=new Date();
		download=StringUtils.replace(download, "@nowdate@",nowdate.getTime()+"");
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObj=(JSONObject) array.get(i);
			String fangan=jsonObj.getString("fangan");
			if(fangan.indexOf("查看")==-1){
				continue;
			}
			if(fangan.toLowerCase().indexOf("onclick")!=-1){
				download=StringUtils.replace(download, "@pid@",StringUtils.substringBetween(fangan, "list.showProject(", ")"));
			}else{
				download="http://"+StringUtils.substringBetween(fangan, "http://", "'");
			}
			String ds=HttpHtmlService.getHtmlContent(download,"gb2312");
			
			if(ds.indexOf("red")!=-1){
				
				Source source=new Source(ds);
				Element projectDetailList=source.getElementById("projectDetailList");
				List<Element> detail=projectDetailList.getAllElementsByClass("num");
				for(Element e:detail){
					String code=e.getContent().getTextExtractor().toString();
					if(code.indexOf("红球")!=-1){
						code=StringUtils.replace(code, " ", "");
						code=StringUtils.replace(code, "蓝球:", "+");
						code=StringUtils.replace(code, "红球:", "");
					}else{
						code=StringUtils.replace(code, " ", "");
						code=StringUtils.replace(code, "蓝球:", "+");
						code=StringUtils.replace(code, "胆:", "");
						code=StringUtils.replace(code, "拖:", ",");
						String[] codes=StringUtils.split(code, "+");
						String[] redCode=StringUtils.split(codes[0],",");
						Arrays.sort(redCode);
						code=StringUtils.join(redCode, ",")+"+"+codes[1];
					}
					logger.info(code);
				}
				
			}
		}
		// logger.info(jsonStr);
		// logger.info("=="+JSONUtils.mayBeJSON(jsonStr));
		// if(JSONUtils.mayBeJSON(jsonStr)){
		// JSONObject jsonOop=new JSONObject();
		// // JSONArray array=jsonOop.getJSONArray(jsonStr);
		// //jsonOop=JSONObject.fromObject(jsonStr);
		// // JSONArray array=JSONArray.fromObject(jsonStr);
		// // jSon
		// logger.info(JSONUtils.isArray(html)+"");
		// Map map=JSONUtils.getProperties(jsonOop);
		// }
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
