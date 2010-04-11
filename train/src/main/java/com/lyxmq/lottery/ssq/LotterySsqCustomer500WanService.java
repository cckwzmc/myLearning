package com.lyxmq.lottery.ssq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.utils.LotteryUtils;
import com.myfetch.service.http.util.HttpHtmlService;

/**
 * 500wan用户购买/收集的数据处理/
 * 查看有两个链接，一个是层方式的一个是下载方式的
 * project_ssqshow.php?pid='+pid+'&'+(+new Date()) 层方式
 * 查看带HTTp链接的是下载方式的
 * 
 * 
 * @author LIYI
 * 
 */
public class LotterySsqCustomer500WanService {
	private static final Logger logger = LoggerFactory.getLogger(LotterySsqCustomer500WanService.class);
	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	/**
	 * 获取用户的各种方案
	 * 
	 * @return
	 */
	public List<Map<String, String>> get500WanProject() {
		new LotterySsqConifgService();
		String url = LotterySsqConifgService.getWww500wanUrl();
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k=0;
		for (int i = 1; i < 300; i++) {
			if(k>4){
				break;
			}
			String jsonData = HttpHtmlService.getHtmlContent(StringUtils.replace(url,"@page@",i+""), "GB2312");
			logger.info(StringUtils.replace(url,"@page@",i+""));
			JSONArray array=JSONArray.fromObject("["+jsonData+"]");
			if(array.isEmpty()){
				k++;
				continue;
			}
			JSONObject obj=JSONObject.fromObject(array.get(0));
			array=JSONArray.fromObject(obj.getString("data"));
			for (int j=0;j<array.size();j++) {
				obj=JSONObject.fromObject(array.get(j));
				String fangan=obj.getString("fangan");
				if(fangan.indexOf("查看")==-1){
					continue;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("fangan",obj.getString("fangan"));
				retList.add(map);
			}
		}
		return retList;
	}
	/**
	 * 下载用户方案
	 * @param id
	 * @param playtype
	 * @return
	 */
	public List<String> download500WanProject(String url){
		String content = HttpHtmlService.getHtmlContent(url, "GB2312");
		if(content.indexOf("red")!=-1){
			return this.parserdownloadProjectHtml(content);
		}
		List<String> list=new ArrayList<String>();
		content=StringUtils.replace(content, " + ", "+");
		content=StringUtils.replace(content, "<br><br>", "\n");
		content=StringUtils.replace(content, " | ", "+");
		content=StringUtils.replace(content, " \n", "\n");
		content=StringUtils.replace(content, "\t", ",");
		content=StringUtils.replace(content, "、", ",");
		content=StringUtils.replace(content, "，", ",");
		String[] contents=StringUtils.split(content,"\n");
		for (int i = 0; i < contents.length; i++) {
			String code=contents[i];
			String[] codes=StringUtils.split(code, " ");
			if(codes.length==7){
				code=codes[0]+","+codes[1]+","+codes[2]+","+codes[3]+","+codes[4]+","+codes[5]+"+"+codes[6];
			}else{
				code=StringUtils.replace(code," ", ",");
			}
			list.add(code);
		}
		return list;
	}
	/**
	 * 解析html
	 * @param content
	 * @return
	 */
	private List<String> parserdownloadProjectHtml(String content) {
		List<String> list=new ArrayList<String>();
		Source source=new Source(content);
		Element projectDetailList=source.getElementById("projectDetailList");
		List<Element> detail=projectDetailList.getAllElementsByClass("num");
		for(Element e:detail){
			String code=e.getContent().getTextExtractor().toString();
			if(code.indexOf("红球")!=-1){
				code=StringUtils.replace(code, " ", "");
				code=StringUtils.replace(code, "蓝球:", "+");
				code=StringUtils.replace(code, "红球:", "");
			}else if(code.indexOf("胆")!=-1||code.indexOf("拖")!=-1){
				continue;
			}else{
				code=StringUtils.replace(code, " ", "");
				code=StringUtils.replace(code, "蓝球:", "+");
				code=StringUtils.replace(code, "胆:", "");
				code=StringUtils.replace(code, "拖:", ",");
				code=sortCode(code);
			}
			list.add(code);
		}
		return list;
	}

	/**
	 * 给红球排序
	 * @param code
	 * @return
	 */
	private String sortCode(String code) {
		String[] codes=StringUtils.split(code, "+");
		String[] redCode=StringUtils.split(codes[0],",");
		Arrays.sort(redCode);
		code=StringUtils.join(redCode, ",")+"+"+codes[1];
		return code;
	}

	public void save500WanProjectRedCode(){
		List<Map<String,String>> list=this.get500WanProject();
		List<String> resultList=new ArrayList<String>();
		for(Map<String,String> map : list){
			for(int i=0;i<10000;i++){
				
			}
			String downloadUrl=this.parserUrl(map.get("fangan"));
			downloadUrl=StringUtils.replace(downloadUrl, "@nowdate@", new Date().getTime()+"");
			List<String> pList=this.download500WanProject(downloadUrl);
			logger.info(downloadUrl);
			if(CollectionUtils.isEmpty(pList)){
				continue;
			}
			for(String ssq:pList){
				String redCode=StringUtils.split(ssq, "+")[0];
				String[] redCodes =StringUtils.split(redCode,",");
				if(redCodes.length<6||redCodes.length>20){
					logger.error("方案解析失败=="+ssq);
					continue;
				}
				LotteryUtils.select(6, redCodes, resultList);
			}
			if (resultList.size() > 2000) {
				this.dao.saveSsqLotteryCollectRedCod(resultList);
				resultList.clear();
			}
		}
		if(CollectionUtils.isNotEmpty(resultList)){
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
	}

	private String parserUrl(String fangan) {
		String download=LotterySsqConifgService.getWww500wanDowload();
		if(fangan.toLowerCase().indexOf("onclick")!=-1){
			download=StringUtils.replace(download, "@pid@",StringUtils.substringBetween(fangan, "list.showProject(", ")"));
		}else{
			download="http://"+StringUtils.substringBetween(fangan, "http://", "'");
		}
		return download;
	}
}
