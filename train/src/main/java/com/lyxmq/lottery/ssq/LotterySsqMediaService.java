package com.lyxmq.lottery.ssq;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.utils.LotterySsqMediaUtils;
import com.lyxmq.lottery.ssq.utils.LotteryUtils;
import com.mchange.v1.io.InputStreamUtils;
import com.myfetch.service.http.HtmlParseUtils;
import com.myfetch.service.http.HttpHtmlService;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 */
public class LotterySsqMediaService {
	private static Logger log = LoggerFactory.getLogger(LotterySsqMediaService.class);
	LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	/**
	 * 按单注方式生成红球号码
	 * 
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(Document document) {
		List<String[]> redCode = LotterySsqMediaUtils.getMediaRedCode(document);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = redCode.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}

	/**
	 * 按单注方式生成红球号码
	 * 
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(List<String[]> redList) {
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = redList.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}

	/**
	 * 把媒体红球生产的单注保存到文件 d:/myproject/ssq_media_red_" + qh + ".xml
	 * 
	 * @param redMedia
	 * @param qh
	 */
	public void saveCurrentMediaRedCode(List<String> redMedia, String qh) {
		if (CollectionUtils.isNotEmpty(redMedia)) {
			try {
				File file = new File("d:/myproject/ssq_media_red_" + qh + ".xml");
				if (!file.exists()) {
					file.createNewFile();
				} else {
				}
				FileWriter writer = new FileWriter(file);
				StringBuffer filePrint = new StringBuffer();
				int temp = 0;
				for (Iterator<String> iterator = redMedia.iterator(); iterator.hasNext();) {
					temp++;
					String redCode = (String) iterator.next();
					if (temp < redMedia.size()) {
						filePrint.append(redCode + "\n");
					} else {
						filePrint.append(redCode);
					}
				}
				writer.write(filePrint.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 保存媒体推荐XML 到文件 D:/myproject/myselflearning/lottery/ssq_media/" + expect + ".xml
	 * 
	 * @param content
	 * @param expect
	 * @throws IOException
	 */
	public static void writerHistoryMediaXml(String content, String expect) throws IOException {
		if (StringUtils.isBlank(expect) || StringUtils.isBlank(content)) {
			return;
		}
		File file = new File("D:/myproject/myselflearning/lottery/ssq_media/" + expect + ".xml");
		if (!file.exists()) {
			file.createNewFile();
		} else {
		}
		FileWriter writer = new FileWriter(file);
		writer.write(content);
		writer.close();
	}

	/**
	 * 保存媒体推荐的历史xml
	 * 
	 * @param start
	 * @param end
	 */
	public static void saveHistoryMediaXml(int start, int end) {
		String url = "http://www.500wan.com/static/info/ssq/mediadetail/";// 10001.xml
		for (int i = start; i <= end; i++) {
			String content = "";
			try {
				content = HttpHtmlService.getXmlContent(url + i + ".xml");
				if (StringUtils.isBlank(content)) {
					continue;
				}
				writerHistoryMediaXml(content, i + "");
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * 保存媒体推荐的历史xml
	 * 
	 * @param start
	 * @param end
	 */
	public static void saveHistoryMediaXml(String expect, String xmlData) {
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		try {
			writerHistoryMediaXml(xmlData, expect);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	/**
	 * 保存历史的媒体推荐红球统计
	 * 
	 * @param expect
	 * @param redMap
	 * @param redCode
	 */
	public void saveHistoryMediaRedStat(String expect, Map<String, String> redMap, String redCode) {
		for (Iterator<String> iterator = redMap.keySet().iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			redCode = "," + redCode + ",";
			String value = redMap.get(code);
			String[] stat = StringUtils.split(value, "|");
			if (StringUtils.indexOf(redCode, "," + code + ",") != -1) {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "0", code, stat[0], stat[1], true);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "0", code, stat[0], stat[1], false);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * 保存历史的媒体推荐蓝球统计
	 * 
	 * @param expect
	 * @param blueMap
	 * @param blueCode
	 */
	public void saveHistoryMediaBlueStat(String expect, Map<String, String> blueMap, String blueCode) {
		for (Iterator<String> iterator = blueMap.keySet().iterator(); iterator.hasNext();) {
			String code = (String) iterator.next();
			String value = blueMap.get(code);
			String[] stat = StringUtils.split(value, "|");
			if (StringUtils.equals(blueCode, code)) {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "1", code, stat[0], stat[1], true);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			} else {
				try {
					this.dao.saveSsqLotteryHistoryStat(expect, "1", code, stat[0], stat[1], false);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
	}

	public static void main(String[] args) {
		// WebHtmlUtils client=new WebHtmlUtils();
		// String html="";
		// try {
		// html = InputStreamUtils.readInputStream(client.doGet("http://www.sina.com.cn", ""), "GB2312");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (URISyntaxException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (HttpException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(HtmlParseUtils.getElementById(html, "page", "GB2312"));
	}

	public void saveHistoryMediaStat(String xmlContent, String expect) {
		if (StringUtils.isBlank(xmlContent)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlContent);
		} catch (DocumentException e) {
			log.error("XML parse error");
		}
		this.saveHistoryMediaRedStat(expect, LotterySsqMediaUtils.getMediaRedCodeStat(document), StringUtils.join(LotterySsqMediaUtils
				.getHistoryOpenRedcode(document), ","));
		this.saveHistoryMediaBlueStat(expect, LotterySsqMediaUtils.getMediaBlueCodeStat(document), LotterySsqMediaUtils.getHistoryOpenBlueCode(document));
	}

	public void saveCurrentMediaRedCodeToDb(List<String> redMedia, String expect) {
		this.dao.saveSsqLotteryCollectRedCod(redMedia);
	}
}
