package com.lottery.ssq.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.LotterySsqFetchConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotteryUtils;
import com.lottery.util.html.HttpHtmlService;

/**
 * 500wan用户购买/收集的数据处理/ 查看有两个链接，一个是层方式的一个是下载方式的 project_ssqshow.php?pid='+pid+'&'+(+new Date()) 层方式 查看带HTTp链接的是下载方式的
 * 
 * @author LIYI
 */
public class LotterySsqCustomer500WanService extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomer500WanService.class);
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}
	LotterySsqThan20Service lotterySsqThan20Service = null;

	public void setLotterySsqThan20Service(LotterySsqThan20Service lotterySsqThan20Service) {
		this.lotterySsqThan20Service = lotterySsqThan20Service;
	}

	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	public List<Map<String, String>> get500WanProject() {
		new LotterySsqConifgService();
		String url = LotterySsqFetchConfig.www500wanUrl;
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k = 0;
		for (int i = 1; i < 300; i++) {
			if (k > 4) {
				break;
			}
			String jsonData = HttpHtmlService.getHtmlContent(StringUtils.replace(url, "@page@", i + ""), "GB2312");
			logger.info(StringUtils.replace(url, "@page@", i + ""));
			JSONArray array = JSONArray.fromObject("[" + jsonData + "]");
			if (array.isEmpty()) {
				k++;
				continue;
			}
			JSONObject obj = JSONObject.fromObject(array.get(0));
			array = JSONArray.fromObject(obj.getString("data"));
			for (int j = 0; j < array.size(); j++) {
				obj = JSONObject.fromObject(array.get(j));
				Map<String, String> map = new HashMap<String, String>();
				map.put("fangan", obj.getString("fangan"));
				String proid = obj.getString("proid");
				if (StringUtils.indexOf(proid, "</a>") != -1) {
					proid = StringUtils.substringBetween(proid, ">", "</a>").trim();
					if (StringUtils.indexOf(proid, "</") != -1) {
						proid = StringUtils.substring(proid, 0, StringUtils.indexOf(proid, "</"));
					}
					if (StringUtils.lastIndexOf(proid, ">") != -1) {
						proid = StringUtils.substring(proid, StringUtils.indexOf(proid, ">") + 1);
					}
				}
				map.put("proid", proid.trim());
				retList.add(map);
			}
			try {
				sleep(3000);
			} catch (InterruptedException e) {
				notify();
			}
		}
		return retList;
	}

	/**
	 * 下载用户方案
	 * 
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> download500WanProject(String url) {
		String content = HttpHtmlService.getHtmlContent(url, "GB2312");
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			notify();
		}
		if (content.indexOf("red") != -1) {
			return this.parserdownloadProjectHtml(content);
		}
		List<String> list = new ArrayList<String>();
		content = StringUtils.replace(content, " + ", "+");
		content = StringUtils.replace(content, "<br><br>", "\n");
		content = StringUtils.replace(content, " | ", "+");
		content = StringUtils.replace(content, "∣", "+");
		content = StringUtils.replace(content, "|", "+");
		content = StringUtils.replace(content, "  ", " ");
		content = StringUtils.replace(content, "=", "+");
		content = StringUtils.replace(content, " \n", "\n");
		content = StringUtils.replace(content, "\t", ",");
		content = StringUtils.replace(content, ".", ",");
		content = StringUtils.replace(content, "-", ",");
		content = StringUtils.replace(content, "、", ",");
		content = StringUtils.replace(content, "-", ",");
		content = StringUtils.replace(content, ".", ",");
		content = StringUtils.replace(content, "，", ",");
		String[] contents = StringUtils.split(content, "\n");
		for (int i = 0; i < contents.length; i++) {
			String code = contents[i];
			String[] codes = StringUtils.split(code, " ");
			if (codes.length == 7) {
				code = codes[0] + "," + codes[1] + "," + codes[2] + "," + codes[3] + "," + codes[4] + "," + codes[5] + "+" + codes[6];
			} else {
				code = StringUtils.replace(code, " ", ",");
			}
			list.add(code);
		}
		return list;
	}

	/**
	 * 解析html
	 * 
	 * @param content
	 * @return
	 */
	private List<String> parserdownloadProjectHtml(String content) {
		List<String> list = new ArrayList<String>();
		Source source = new Source(content);
		Element projectDetailList = source.getElementById("projectDetailList");
		List<Element> detail = projectDetailList.getAllElementsByClass("num");
		boolean isHaveDan=false;
		for (Element e : detail) {
			String code = e.getContent().getTextExtractor().toString();
			if (code.indexOf("红球") != -1) {
				code = StringUtils.replace(code, " ", "");
				code = StringUtils.replace(code, "蓝球:", "+");
				code = StringUtils.replace(code, "红球:", "");
				code = StringUtils.replace(code, "蓝球", "+");
				code = StringUtils.replace(code, "红球", "");
			} else if (code.indexOf("胆") != -1) {
				String[] dans = StringUtils.substringsBetween(code, "胆:", "拖:");
				for (int i = 0; i < dans.length; i++) {
					String dan = dans[i].replace(" ", "");
					if (StringUtils.isNotBlank(dan)) {
						list.add("--1," + dan);
						isHaveDan=true;
					}
				}
			} else if (code.indexOf("拖") != -1) {
				continue;
			} else {
				code = StringUtils.replace(code, " ", "");
				code = StringUtils.replace(code, "蓝球:", "+");
				code = StringUtils.replace(code, "胆:", "");
				code = StringUtils.replace(code, "拖:", ",");
				code = sortCode(code);
			}
			list.add(code);
		}
		if(isHaveDan){
			list.add("--1");
		}
		return list;
	}

	/**
	 * 给红球排序
	 * 
	 * @param code
	 * @return
	 */
	private String sortCode(String code) {
		String[] codes = StringUtils.split(code, "+");
		String[] redCode = StringUtils.split(codes[0], ",");
		Arrays.sort(redCode);
		code = StringUtils.join(redCode, ",") + "+" + codes[1];
		return code;
	}

	@SuppressWarnings("unchecked")
	public void save500WanProjectRedCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		List<String[]> danList = new ArrayList<String[]>();
		int last = 0;
		int page = 200;
		List list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "0");
		Pattern p = Pattern.compile("[^\\x00-\\xff]");
		while (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String code = ObjectUtils.toString(map.get("code"));
				if(StringUtils.indexOf(code, "胆")!=-1){
					String[] codes = StringUtils.split(code, "@@");
					for (String ssq : codes) {
						String redCode = StringUtils.split(ssq, "+")[0];
						if (StringUtils.indexOf(redCode, "胆")!=-1) {
							redCode=StringUtils.replace(redCode,"--1","");
							redCode=StringUtils.replace(redCode,": ","");
							redCode=StringUtils.replace(redCode,":","");
							redCode=StringUtils.replace(redCode,"： ","");
							redCode=StringUtils.replace(redCode,"：","");
							redCode=StringUtils.replace(redCode,"胆","");
							redCode=StringUtils.replace(redCode,"拖","|");
							redCode=StringUtils.replace(redCode,"蓝球","#");
							redCode=StringUtils.replace(redCode," ","");
						}else{
							continue;
						}
						String dan=StringUtils.substring(redCode, 0, StringUtils.indexOf(redCode, "|"));
						String tuo=StringUtils.substring(redCode, StringUtils.indexOf(redCode, "|")+1, StringUtils.indexOf(redCode, "#"));
						String[] dans=dan.split(",");
						LotteryUtils.selectDanArray(6, dan,tuo, danList);
						for(String[] danCode:danList){
							String[] rsDanCode=(StringUtils.join(dans, ",")+","+StringUtils.join(danCode, ",")).split(",");
							if(rsDanCode.length!=6){
								continue;
							}
							Arrays.sort(rsDanCode);
							resultList.add(rsDanCode);
						}
						danList.clear();
					}
				}else
				{
					Matcher m = p.matcher(code);
					if (m.find()) {
						logger.error("内容有非法字符==" + code);
						continue;
					}
					String[] codes = StringUtils.split(code, "@@");
					for (String ssq : codes) {
						String redCode = StringUtils.split(ssq, "+")[0];
						if(redCode.startsWith("--1")){
							logger.error("使用了胆的方案解析==" + ssq);
							continue;
						}
						String[] redCodes = StringUtils.split(redCode, ",");
						if (redCodes.length < 6 || redCodes.length > 20) {
							logger.error("方案解析失败==" + ssq);
							continue;
						}
						LotteryUtils.selectArray(6, redCodes, resultList);
					}
				}
				if (resultList.size() > 2000) {
					this.dao.saveSsqLotteryCollectRedCod(resultList);
					resultList.clear();
				}
			}
			last += page;
			list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "0");
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
	}

	private String parserUrl(String fangan) {
		String download = LotterySsqFetchConfig.www500wanDowload;
		if (fangan.toLowerCase().indexOf("onclick") != -1) {
			download = StringUtils.replace(download, "@pid@", StringUtils.substringBetween(fangan, "list.showProject(", ")"));
		} else {
			download = "http://" + StringUtils.substringBetween(fangan, "http://", "'");
		}
		return download;
	}

	public void run() {
		this.fetch500WanProjectRedCode();
	}

	public void fetch500WanProjectRedCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqFetchConfig.expect, "0");
		List<Map<String, String>> list = this.get500WanProject();
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		List<String> danList=new ArrayList<String>();
		for (Map<String, String> map : list) {
			String fangan = map.get("fangan");
			if (this.dao.getCountSsqLotteryCollectFetchByProid(map.get("proid"), "0") > 0) {
				continue;
			}
			Map<String, String> tmpMap = new HashMap<String, String>();
			if (fangan.indexOf("查看") == -1) {
				tmpMap.put("proid", map.get("proid"));
				tmpMap.put("net", "0");
				tmpMap.put("expect", LotterySsqFetchConfig.expect);
				tmpMap.put("code", "-1");
				tmpMap.put("isfail", "1");
				resultList.add(tmpMap);
				continue;
			}
			String downloadUrl = this.parserUrl(map.get("fangan"));
			downloadUrl = StringUtils.replace(downloadUrl, "@nowdate@", new Date().getTime() + "");
			logger.info(downloadUrl);
			List<String> pList = this.download500WanProject(downloadUrl);
			if(CollectionUtils.isNotEmpty(pList)&&pList.contains("--1")){
				for (int k=0;k<pList.size();k++) {
					String dan = (String)pList.get(k);
					if(dan.startsWith("--1,")){
						danList.add(pList.remove(k).replace("--1,", ""));
						k--;
					}
				}
				String[] codes = pList.toArray(new String[pList.size()]);
				tmpMap.put("proid", map.get("proid"));
				tmpMap.put("net", "0");
				tmpMap.put("expect", LotterySsqFetchConfig.expect);
				tmpMap.put("code", StringUtils.join(codes, "@@"));
				tmpMap.put("isfail", "0");
				resultList.add(tmpMap);
				continue;
			}
			if (CollectionUtils.isEmpty(pList)) {
				tmpMap.put("proid", map.get("proid"));
				tmpMap.put("net", "0");
				tmpMap.put("expect", LotterySsqFetchConfig.expect);
				tmpMap.put("code", "-1");
				tmpMap.put("isfail", "1");
				resultList.add(tmpMap);
				continue;
			}

			String[] codes = pList.toArray(new String[pList.size()]);
			tmpMap.put("proid", map.get("proid"));
			tmpMap.put("net", "0");
			tmpMap.put("expect", LotterySsqFetchConfig.expect);
			tmpMap.put("code", StringUtils.join(codes, "@@"));
			tmpMap.put("isfail", "0");
			resultList.add(tmpMap);
			if (resultList.size() > 200) {
				this.dao.batchSaveSsqLotteryCollectFetch(resultList);
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.batchSaveSsqLotteryCollectFetch(resultList);
			resultList.clear();
		}
		if(CollectionUtils.isNotEmpty(danList)){
			this.dao.batchSqqLotteryDanResult(danList, "1");
		}
		logger.info("========" + "500Wan抓取完成..............................................");
	}
	public static void main(String[] args) {
		LotterySsqCustomer500WanService ss=new LotterySsqCustomer500WanService();
		String tt="<div class=\"alert_content\" id=\"projectDetailList\">" +
				"<div class=\"num\"><span class=\"red\">胆: 14,18,29</span><br><span class=\"red\">拖: 02,05,08,13,15,19,20,24,26</span><br><span class=\"blue\">蓝球: 08,13</span></div>" +
				"<div class=\"num\"><span class=\"red\">胆: 11,18,29</span><br><span class=\"red\">拖: 02,05,08,13,15,19,20,24,26</span><br><span class=\"blue\">蓝球: 08,13</span></div>" +
				"</div>";
		System.out.println(ss.parserdownloadProjectHtml(tt));
	}
}
