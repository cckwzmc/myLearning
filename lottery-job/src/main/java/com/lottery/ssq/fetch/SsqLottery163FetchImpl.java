package com.lottery.ssq.fetch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Segment;
import net.htmlparser.jericho.Source;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.fetch.dao.LotteryFetchDao;
import com.lottery.util.html.HttpHtmlService;

public class SsqLottery163FetchImpl implements ISsqLotteryFetch {

	private static final Logger logger = LoggerFactory.getLogger(SsqLottery163FetchImpl.class);
	final String URL163 = "http://sports.163.com/special/00051O8E/ssqgd.html";
	private LotteryFetchDao lotteryFetchDao = null;

	public void setLotteryFetchDao(LotteryFetchDao lotteryFetchDao) {
		this.lotteryFetchDao = lotteryFetchDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSsqLotteryDetail(String url, String title) {
		List<String[]> ssqList = this.getSsqLotteryIndexList();
		List webList = this.lotteryFetchDao.getSsqLotteryWebFetchList(1);
		List<String> commendCode=new ArrayList<String>();
		for (String[] detail : ssqList) {
			for (Iterator iterator = webList.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String webTitle = ObjectUtils.toString(map.get("title"));
				String[] wt = StringUtils.split(webTitle, "|");
				boolean isMatch = true;
				for (String subTitle : wt) {
					if (detail[1].indexOf(subTitle) == -1) {
						isMatch = false;
						break;
					}
				}
				Set<String> result = new HashSet<String>();
				List<String> blueResult = new ArrayList<String>();
				if (isMatch) {
					String htmlContent = HttpHtmlService.getHtmlContent(detail[0], "GBK");
					String[] pattens = StringUtils.split(ObjectUtils.toString(map.get("patten_des")), "|");
					String[] replaces = StringUtils.split(ObjectUtils.toString(map.get("replace")), "|");
					logger.info(htmlContent);
					Source source = new Source(htmlContent);
					String content = "";
					List<Element> eList = null;
					for (String patten : pattens) {
						boolean isExistBlueCode = false;
						if (StringUtils.indexOf(ObjectUtils.toString(map.get("patten_des")), "blue=") > -1) {
							isExistBlueCode = true;
						}
						if (StringUtils.indexOf(patten, "id=") > -1) {
							Element e = source.getElementById(StringUtils.remove(patten, "id="));
						}
						if (StringUtils.indexOf(patten, "tag_list=") > -1) {
							eList = source.getAllElements(StringUtils.remove(patten, "tag_list="));
						}
						if (CollectionUtils.isNotEmpty(eList)) {
							// 取红球
							if (StringUtils.indexOf(patten, "have=") > -1) {
								boolean isAtNext = false;
								if (StringUtils.indexOf(patten, "next=") > -1) {
									isAtNext = true;
								}
								String hStr = StringUtils.remove(patten, "have=");
								hStr = StringUtils.remove(patten, "next=");
								for (int i = 0; i < eList.size(); i++) {
									String code = "";
									Segment element = eList.get(i);
									if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
										if (!isAtNext) {
											code = element.getTextExtractor().toString();
											if (StringUtils.isNotBlank(code)) {
												this.replaceChar(code,replaces);
												result.add(code);
											}
										} else {
											i++;
											code = eList.get(i).getTextExtractor().toString();

											if (StringUtils.indexOf(eList.get(i + 1).getTextExtractor().toString(), "蓝球") > -1) {
												code += eList.get(i + 1).getTextExtractor().toString();
												i++;
											}
											if (StringUtils.isNotBlank(code)) {
												this.replaceChar(code,replaces);
												result.add(code);
											}
										}
									}
								}

							}
							// 取蓝球 ---begin
							if (isExistBlueCode) {
								boolean isAtNext = false;
								if (StringUtils.indexOf(patten, "next=") > -1) {
									isAtNext = true;
								}
								String hStr = StringUtils.remove(patten, "blue=");
								hStr = StringUtils.remove(patten, "next=");
								for (int i = 0; i < eList.size(); i++) {
									Segment element = eList.get(i);
									String blueCode = "";
									if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
										if (!isAtNext) {
											if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
												blueCode = element.getTextExtractor().toString();
											}
											if (StringUtils.isNotBlank(blueCode)) {
												this.replaceChar(blueCode,replaces);
												blueResult.add(blueCode);
											}
										} else {
											i++;
											blueCode = eList.get(i).getTextExtractor().toString();
											if (StringUtils.isNotBlank(blueCode)) {
												this.replaceChar(blueCode,replaces);
												blueResult.add(blueCode);
											}
										}
									}
								}
							}
							// 蓝球 end
						}
					}
				}
				if(CollectionUtils.isNotEmpty(blueResult)&&CollectionUtils.isNotEmpty(result)){
					String[] codeTmp=result.toArray(new String[]{});
					String[] blueCode=blueResult.toArray(new String[]{});
					String tmpCode="";
					for(String redCode:codeTmp){
						if("".equals(tmpCode)){
							tmpCode=codeTmp+"+"+StringUtils.join(blueCode, ",");
						}else{
							tmpCode+="@@"+codeTmp+"+"+StringUtils.join(blueCode, ",");
						}
					}
					commendCode.add(tmpCode);
				}else{
					if(CollectionUtils.isNotEmpty(result)){
						String[] codeTmp=result.toArray(new String[]{});
						commendCode.add(StringUtils.join(codeTmp, "@@"));
					}
				}
			}
		}
		this.lotteryFetchDao.batchLotterySsqCommendCode(commendCode);
		return null;
	}

	private void replaceChar(String code, String[] replaces) {
		code=StringUtils.remove(code, LotterySsqConfig.expect);
		code=StringUtils.remove(code, LotterySsqConfig.expect.substring(2));
		for(String replace:replaces){
			if(StringUtils.indexOf(replace,"split=")>-1){
				replace=StringUtils.remove(replace, "split=");
				if(StringUtils.indexOf(replace, "first=")>-1)
				{
					replace=StringUtils.remove(replace, "first=");
					code=StringUtils.substring(code,0,StringUtils.indexOf(code, replace));
				}
			}
			code=StringUtils.remove(code, replace);
		}
	}

	@Override
	public List<String[]> getSsqLotteryIndexList() {
		String listContent = HttpHtmlService.getHtmlContent(URL163, "GBK");
		if (StringUtils.isBlank(listContent)) {
			return null;
		}
		List<String[]> ssqList = new ArrayList<String[]>();
		Source source = new Source(listContent);
		List<Element> list = source.getAllElementsByClass("articleList");
		for (Element ul : list) {
			List<Element> liList = ul.getAllElements("li");
			for (Element li : liList) {
				String[] ssq = new String[2];
				List<Element> articleTitle = li.getAllElementsByClass("articleTitle");
				List<Element> href = articleTitle.get(0).getAllElements("a");
				String hrefValue = href.get(0).getAttributeValue("href");
				String hrefTitle = href.get(0).getContent().getTextExtractor().toString();

				if (hrefTitle.indexOf(LotterySsqConfig.expect + "") != -1 || hrefTitle.indexOf(LotterySsqConfig.expect.substring(LotterySsqConfig.expect.length() - 3)) != -1) {
					ssq[0] = hrefValue;
					ssq[1] = hrefTitle;
					ssqList.add(ssq);
				}

			}
		}
		return ssqList;
	}

	public static void main(String[] args) {
		new LotterySsqConfig().expect = "10076";
		SsqLottery163FetchImpl fetch = new SsqLottery163FetchImpl();
		fetch.getSsqLotteryDetail("", "");
	}
}
