package com.lyxmq.lottery.ssq.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.htmlparser.jericho.Element;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.dao.LotteryDao;
import com.lyxmq.lottery.ssq.utils.LotteryUtils;
import com.myfetch.service.http.util.HtmlParseUtils;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqMediaSinaService {
	private static Logger log = LoggerFactory.getLogger(LotterySsqMediaSinaService.class);
	LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;  
	}
	/**
	 * html格式
	 * @param mediaSina
	 * @return
	 */
	public List<String> parseCurrentMediaRedCode(String mediaSina) {
		List<String> redCode=this.getCurrentMediaRedCode(mediaSina);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String> iterator = redCode.iterator(); iterator.hasNext();) {
			String[] red =iterator.next().split(",");
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}
	public List<String> getCurrentMediaRedCode(String mediaSina) {
		List<String> redList=new ArrayList<String>();
		List<Element> list=HtmlParseUtils.getElementListByTagName(mediaSina, "tr");
		for(Element e:list.subList(1,list.size())){
			List<Element> tdList=HtmlParseUtils.getElementListByTagName(e.toString(), "td");
			Element tdE=tdList.get(3);
			List<Element> divList=tdE.getAllElements("div");
			String redCode="";
			for(Element eDiv:divList){
				if(StringUtils.isBlank(redCode)){
					redCode=eDiv.getContent().toString().length()<2?"0"+eDiv.getContent().toString():eDiv.getContent().toString().substring(0,2);
				}else{
					redCode+=","+(eDiv.getContent().toString().length()<2?"0"+eDiv.getContent().toString():eDiv.getContent().toString().substring(0,2));
				}
			}
			redList.add(redCode);
		}
		return redList;
	}
	public List<String> getCurrentMediaDanRedCode(String mediaSina) {
		List<String> redList=new ArrayList<String>();
		List<Element> list=HtmlParseUtils.getElementListByTagName(mediaSina, "tr");
		for(Element e:list.subList(1,list.size())){
			List<Element> tdList=HtmlParseUtils.getElementListByTagName(e.toString(), "td");
			Element tdE=tdList.get(2);
			List<Element> divList=tdE.getAllElements("div");
			String redCode="";
			for(Element eDiv:divList){
				if(StringUtils.isBlank(redCode)){
					redCode=eDiv.getContent().toString().length()<2?"0"+eDiv.getContent().toString():eDiv.getContent().toString().substring(0,2);
				}else{
					redCode+=","+(eDiv.getContent().toString().length()<2?"0"+eDiv.getContent().toString():eDiv.getContent().toString().substring(0,2));
				}
			}
			redList.add(redCode);
		}
		return redList;
	}
	public void saveCurrrentMediaDanRedCode(List<String> list)
	{	
		this.dao.batchSqqLotteryDanResult(list,"0");
	}
}
