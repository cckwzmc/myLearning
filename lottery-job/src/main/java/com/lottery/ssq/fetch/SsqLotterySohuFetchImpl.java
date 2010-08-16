package com.lottery.ssq.fetch;

import java.net.URL;
import java.util.ArrayList;
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
import com.lottery.ssq.utils.LotterySsqUtils;
import com.lottery.util.html.HttpHtmlService;

public class SsqLotterySohuFetchImpl implements ISsqLotteryFetch {

	private static final Logger logger = LoggerFactory.getLogger(SsqLotterySohuFetchImpl.class);
	final String[] URLSOHU = { "http://sports.sohu.com/s2005/1284/s227150850.shtml", "http://sports.sohu.com/s2005/1284/s227150850_192.shtml" };
	private LotteryFetchDao lotteryFetchDao = null;
	// "双色球|各媒体|工作室"; id=117特殊处理
	private boolean isSepTitle = false;

	public void setLotteryFetchDao(LotteryFetchDao lotteryFetchDao) {
		this.lotteryFetchDao = lotteryFetchDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSsqLotteryDetail(String url, String title) {
		List<String[]> ssqList = this.getSsqLotteryIndexList();
		List webList = this.lotteryFetchDao.getSsqLotteryWebFetchList(3);
		String webFetchcode = "";
		Set<String> result = new HashSet<String>();
		List<String> blueResult = new ArrayList<String>();
		for (Iterator iterator = webList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String webTitle = ObjectUtils.toString(map.get("title"));
			String[] wt = StringUtils.split(webTitle, "|");
			for (String[] detail : ssqList) {
				boolean isMatch = true;
				for (String subTitle : wt) {
					if (detail[1].indexOf(subTitle) == -1) {
						isMatch = false;
						//break;
					}
				}
				if ("117".equals(ObjectUtils.toString(map.get("id"))) && isMatch) {
					result = this.sohuSepPage(detail[0]);
				} else if (isMatch) {
					String htmlContent = HttpHtmlService.getHtmlContent(detail[0], "GBK");
					String[] pattens = StringUtils.split(ObjectUtils.toString(map.get("patten_des")), "|");
					String[] replaces = StringUtils.split(ObjectUtils.toString(map.get("replace")), "|");
					Source source = new Source(htmlContent);
					List<Element> eList = null;
					Element e = null;
					for (String patten : pattens) {
						boolean isExistBlueCode = false;
						if (StringUtils.indexOf(ObjectUtils.toString(map.get("patten_des")), "blue=") > -1) {
							isExistBlueCode = true;
						}
						if (StringUtils.indexOf(patten, "id=") > -1) {
							e = source.getElementById(StringUtils.remove(patten, "id="));
						}
						if (StringUtils.indexOf(patten, "tag_list=") > -1 && e != null) {
							eList = e.getAllElements(StringUtils.remove(patten, "tag_list="));
						}
						if (CollectionUtils.isNotEmpty(eList)) {
							// 取红球
							if (StringUtils.indexOf(patten, "have=") > -1) {
								boolean isAtNext = false;
								if (StringUtils.indexOf(patten, "next=") > -1) {
									isAtNext = true;
								}
								String hStr = StringUtils.remove(patten, "have=");
								hStr = StringUtils.remove(hStr, "next=");
								for (int i = 0; i < eList.size(); i++) {
									String code = "";
									Segment element = eList.get(i);
									if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
										if (!isAtNext) {
											code = element.getTextExtractor().toString();
											if (StringUtils.isNotBlank(code)) {
												code = this.replaceChar(code, replaces);
												result.add(code);
											}
										} else {
											i++;
											code = eList.get(i).getTextExtractor().toString();

											// if (StringUtils.indexOf(eList.get(i + 1).getTextExtractor().toString(),
											// "蓝球") > -1) {
											// code += eList.get(i + 1).getTextExtractor().toString();
											// i++;
											// }
											if (StringUtils.isNotBlank(code)) {
												code = this.replaceChar(code, replaces);
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
								hStr = StringUtils.remove(hStr, "next=");
								for (int i = 0; i < eList.size(); i++) {
									Segment element = eList.get(i);
									String blueCode = "";
									if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
										if (!isAtNext) {
											if (StringUtils.indexOf(element.getTextExtractor().toString(), hStr) > -1) {
												blueCode = element.getTextExtractor().toString();
											}
											if (StringUtils.isNotBlank(blueCode)) {
												blueCode = this.replaceChar(blueCode, replaces);
												blueResult.add(blueCode);
											}
										} else {
											i++;
											blueCode = eList.get(i).getTextExtractor().toString();
											if (StringUtils.isNotBlank(blueCode)) {
												blueCode = this.replaceChar(blueCode, replaces);
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
				if (CollectionUtils.isNotEmpty(blueResult) && CollectionUtils.isNotEmpty(result)) {
					String[] codeTmp = result.toArray(new String[] {});
					String[] blueCode = blueResult.toArray(new String[] {});
					String tmpCode = "";
					for (String redCode : codeTmp) {
						if ("".equals(tmpCode)) {
							tmpCode = redCode + "+" + StringUtils.join(blueCode, ",");
						} else {
							tmpCode += "@@" + redCode + "+" + StringUtils.join(blueCode, ",");
						}
					}
					webFetchcode = tmpCode;
					this.lotteryFetchDao.batchLotterySsqCommendCode(webFetchcode, LotterySsqConfig.expect, map.get("id"));
					webFetchcode = "";
					result.clear();
					blueResult.clear();
				} else {
					if (CollectionUtils.isNotEmpty(result)) {
						String[] codeTmp = result.toArray(new String[] {});
						webFetchcode = StringUtils.join(codeTmp, "@@");
						this.lotteryFetchDao.batchLotterySsqCommendCode(webFetchcode, LotterySsqConfig.expect, map.get("id"));
					}
					webFetchcode = "";
					result.clear();
					blueResult.clear();
				}
			}
		}
		return null;
	}

	/**
	 * 双色球2010081期各媒体专家工作室推荐汇总
	 * <p>
	 * 特殊处理
	 * 
	 * @return
	 */
	private Set<String> sohuSepPage(String url) {
		String urlTmp = "http://mirror4.club.sohu.com/readjsnew-ssq-@code@-111111.html";
		Set<String> result = new HashSet<String>();
		String htmlContent = HttpHtmlService.getHtmlContent(url, "GBK");
		Source source = new Source(htmlContent);
		List<Element> content02 = source.getAllElementsByClass("content02");
		List<Element> content01 = source.getAllElementsByClass("content01");
		content02.addAll(content01);
		for (Element el : content02) {
			List<Element> para2 = el.getAllElementsByClass("para2");
			if (CollectionUtils.isEmpty(para2)) {
				para2 = el.getAllElementsByClass("para1");
			}
			if (CollectionUtils.isEmpty(para2)) {
				continue;
			}
			Element para2El = para2.get(0);
			String para2Str = para2El.toString();
			String showItem = StringUtils.substring(para2Str, StringUtils.indexOf(para2Str, "show_item(\"") + 11);
			showItem = StringUtils.substring(showItem, 0, StringUtils.indexOf(showItem, "\""));
			String codeUrl = StringUtils.replace(urlTmp, "@code@", showItem);
			String codeContent = HttpHtmlService.getHtmlContent(codeUrl, "GBK");
			String body = StringUtils.substring(codeContent, StringUtils.indexOf(codeContent, "body_" + showItem) + ("body_" + showItem).length() + 2);
			body = StringUtils.substring(body, 0, StringUtils.indexOf(body, "var"));
			if (StringUtils.indexOf(body, "幸运之门") > -1 || StringUtils.indexOf(body, "盈彩网") > -1 || StringUtils.indexOf(body, "鑫源彩") > -1) {
				String[] xyzm = StringUtils.split(body, "<br>");
				for (String code : xyzm) {
					if (StringUtils.indexOf(code, "红球") > -1) {
						code = StringUtils.replace(code, "红球", "");
						code = StringUtils.replace(code, ":", "");
						code = StringUtils.replace(code, "：", "");
						code = StringUtils.replace(code, "┃,", "");
						code = StringUtils.replace(code, "&nbsp;", "##");
						code = StringUtils.replace(code, "：", "");
						result.add(code);
					} else if (StringUtils.indexOf(code, "蓝球") > -1) {

					} else if (StringUtils.indexOf(code, "红胆") > -1) {
						code = StringUtils.replace(code, "红球", "");
						code = StringUtils.replace(code, "红胆", "");
						code = StringUtils.replace(code, ":", "");
						code = StringUtils.replace(code, "：", "");
						code = StringUtils.replace(code, "┃,", "");
						code = StringUtils.replace(code, "&nbsp;", "##");
						code = StringUtils.replace(code, "：", "");
						code = StringUtils.replace(code, "┃", "");
						code = StringUtils.replace(code, "&n", "");
						code=this.replaceStantardString(code);
						this.lotteryFetchDao.saveWebFetchDanResult(code);
						
					}
				}
			}
			if (StringUtils.indexOf(body, "金海特") > -1) {
				String[] xyzm = StringUtils.split(body, "<br>");
				for (String code : xyzm) {
					if (StringUtils.indexOf(code, "红球15码") > -1 || StringUtils.indexOf(code, "缩水大复推荐") > -1 || StringUtils.indexOf(code, "复式") > -1) {
						code = StringUtils.replace(code, "缩水大复推荐", "");
						code = StringUtils.replace(code, "复式", "");
						code = StringUtils.replace(code, "红球15码", "");
						code = StringUtils.replace(code, "红球", "");
						code = StringUtils.replace(code, ":", "");
						code = StringUtils.replace(code, "：", "");
						result.add(code);
					} else if (StringUtils.indexOf(code, "蓝球") > -1) {

					}
				}
			}
		}
		Set<String> retResult = new HashSet<String>();
		for (String code : result) {
			if (StringUtils.isBlank(code) || code.length() < 16) {
				continue;
			}
			code = StringUtils.replace(code, "┃", "");
			code = StringUtils.replace(code, "&n", "");
			if (StringUtils.endsWith(code, ",")) {
				code = StringUtils.substring(code, 0, code.length() - 1);
			}
			code = this.replaceChar(code, null);
			retResult.add(code);
		}
		return retResult;
	}

	private String replaceChar(String code, String[] replaces) {
		code = replaceStantardString(code);
		code = StringUtils.remove(code, LotterySsqConfig.expect);
		code = StringUtils.remove(code, LotterySsqConfig.expect.substring(2));
		if (replaces != null && replaces.length > 0) {
			for (String replace : replaces) {
				if (StringUtils.indexOf(replace, "split=") > -1) {
					replace = StringUtils.remove(replace, "split=");
					if (StringUtils.indexOf(replace, "second=") > -1) {
						String firstStr = StringUtils.substringBetween(replace, "first=", "second=");
						String secondStr = StringUtils.substring(replace, StringUtils.indexOf(replace, "second=") + 7);
						code = StringUtils.substring(code, StringUtils.indexOf(code, firstStr) + firstStr.length());
						if (StringUtils.indexOf(code, secondStr) > -1) {
							code = StringUtils.substring(code, 0, StringUtils.indexOf(code, secondStr));
						}
					} else if (StringUtils.indexOf(replace, "first=") > -1) {
						replace = StringUtils.remove(replace, "first=");
						code = StringUtils.substring(code, 0, StringUtils.indexOf(code, replace));
					}
				}
			}
			for (String replace : replaces) {
				if (StringUtils.indexOf(replace, "==") > -1) {
					String[] rStr = StringUtils.split(replace, "==");
					code = StringUtils.replace(code, rStr[0], rStr[1]);
				} else {
					code = StringUtils.remove(code, replace);
				}
			}
		}
		code = LotterySsqUtils.standardReplace(code);
		return code;
	}

	private String replaceStantardString(String code) {
		code = StringUtils.replace(code, "　", ",");
		code = StringUtils.replace(code, "    ", " ");
		code = StringUtils.replace(code, "    ", " ");
		code = StringUtils.replace(code, "    ", " ");
		code = StringUtils.replace(code, "    ", " ");
		code = StringUtils.replace(code, "    ", " ");
		code = StringUtils.replace(code, "   ", " ");
		code = StringUtils.replace(code, "   ", " ");
		code = StringUtils.replace(code, "   ", " ");
		code = StringUtils.replace(code, "   ", " ");
		code = StringUtils.replace(code, "   ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "  ", " ");
		code = StringUtils.replace(code, "，", ",");
		code = StringUtils.replace(code, ",,,", ",");
		code = StringUtils.replace(code, ",,,", ",");
		code = StringUtils.replace(code, ",,", ",");
		code = StringUtils.replace(code, ",,", ",");
		code = StringUtils.replace(code, "，", ",");
		if(StringUtils.endsWith(code, ",")){
			code=code.substring(0, code.length()-1);
		}
		if(StringUtils.startsWith(code, ",")){
			code=code.substring(1);
		}
		return code;
	}

	@Override
	public List<String[]> getSsqLotteryIndexList() {
		List<String[]> ssqList = new ArrayList<String[]>();
		for (String sohuUrl : URLSOHU) {
			String listContent = HttpHtmlService.getHtmlContent(sohuUrl, "GBK");
			if (StringUtils.isBlank(listContent)) {
				return null;
			}

			Source source = new Source(listContent);
			List<Element> listContainer = source.getAllElementsByClass("f14list");
			if (CollectionUtils.isEmpty(listContainer)) {
				return null;
			}
			List<Element> list = (listContainer.get(0)).getAllElements("li");
			for (Element li : list) {
				if (li.getTextExtractor().toString().trim().length() < 10) {
					continue;
				}
				String[] ssq = new String[2];
				List<Element> href = li.getAllElements("a");
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
		// new LotterySsqConfig().expect = "10076";
		SsqLotterySohuFetchImpl fetch = new SsqLotterySohuFetchImpl();
		fetch.sohuSepPage("http://club.sports.sohu.com/r-ssq-685259-0-10-900.html");
		// fetch.getSsqLotteryDetail("", "");
	}
}
