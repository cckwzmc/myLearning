package com.lyxmq.lottery.ssq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
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
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> get500WanProject() {
		new LotterySsqConifgService();
		String url = LotterySsqConifgService.getWww500wanUrl();
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		int k=0;
		for (int i = 1; i < 100; i++) {
			if(k>3){
				break;
			}
			String dyjXmlData = HttpHtmlService.getXmlContent(StringUtils.replace(url,"@pageno@",i+""), "GB2312");
			logger.info(StringUtils.replace(url,"@pageno@",i+""));
			Document document = null;
			try {
				document = DocumentHelper.parseText(dyjXmlData);
			} catch (DocumentException e) {
				logger.error("parser xml error===" + e.getMessage());
			}
			List list=new ArrayList();
			try{
				list = document.selectNodes("//xml/row");
			}catch(Exception e){
			}
			if(CollectionUtils.isEmpty(list)){
				k++;
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				Element e = (Element) node;
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", e.attributeValue("id"));
				map.put("playtype", e.attributeValue("playtype"));
				map.put("isupload", e.attributeValue("isupload"));
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
	public List<String> download500WanProject(String id,String playtype){
		String url=StringUtils.replace(StringUtils.replace(LotterySsqConifgService.getDyjDowload(),"@id@",id),"@playtype@",playtype);
		String content = HttpHtmlService.getXmlContent(url, "GB2312");
		if(content.indexOf("该方案")!=-1){
			return null;
		}
		List<String> list=new ArrayList<String>();
		content=StringUtils.replace(content, " + ", "+");
		content=StringUtils.replace(content, "<br><br>", "\n");
		content=StringUtils.replace(content, " | ", "+");
		content=StringUtils.replace(content, " \n", "\n");
		content=StringUtils.replace(content, "\t", ",");
		String[] contents=StringUtils.split(content,"\n");
		for (int i = 0; i < contents.length; i++) {
			list.add(StringUtils.replace(contents[i]," ", ","));
		}
		return list;
	}
	public void save500WanProjectRedCode(){
		List<Map<String,String>> list=this.get500WanProject();
		List<String> resultList=new ArrayList<String>();
		for(Map<String,String> map : list){
			if("0".equals(map.get("isupload"))){
				continue;
			}
			List<String> pList=this.download500WanProject(map.get("id"), map.get("playtype"));
			if(CollectionUtils.isEmpty(pList)){
				continue;
			}
			for(String ssq:pList){
				String redCode=StringUtils.split(ssq, "+")[0];
				String[] redCodes =StringUtils.split(redCode,",");
				if(redCodes.length<6){
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
}
