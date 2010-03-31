package com.lyxmq.lottery.ssq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.LoggerFactory;

import com.lyxmq.lottery.ssq.utils.LotterySsqMediaUtils;
import com.myfetch.service.http.HttpHtmlService;

public class LotteryService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryService.class);
	LotteryDao dao = null;
	private String expect = "";
	private boolean isSaveToDatabase = true;
	LotterySsqConifgService lotterySsqConfigService = null;
	LotterySsqMediaService lotterySsqMediaService = null;
	LotterySsqOtherCommendService lotterySsqOtherCommendService = null;

	public void setLotterySsqMediaService(LotterySsqMediaService lotterySsqMediaService) {
		this.lotterySsqMediaService = lotterySsqMediaService;
	}

	public void setLotterySsqOtherCommendService(LotterySsqOtherCommendService lotterySsqOtherCommendService) {
		this.lotterySsqOtherCommendService = lotterySsqOtherCommendService;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void saveLottoryResult(String redResult) {
		this.dao.saveSsqLottoryResult(redResult);
	}

	public void saveTempLottoryResult(String redResult) {
		this.dao.saveTempLottoryResult(redResult);
	}

	public void getCurrentExpertMergeResult() {
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
	}

	private void initConifg(boolean isInit) {
		if (isInit) {
			lotterySsqConfigService = new LotterySsqConifgService();
		}
	}

	@SuppressWarnings("unchecked")
	public void getCurrentExpertSingleResult() {
		this.initConifg(true);
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		List<String[]> redCodeList = new ArrayList<String[]>();
		if (StringUtils.isNotBlank(xmlData)) {
			try {
				Document document = DocumentHelper.parseText(xmlData);
				redCodeList = LotterySsqMediaUtils.getMediaRedCode(document);
				this.expect = LotterySsqMediaUtils.getMediaExpect(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		// List<String> redMedia = new ArrayList<String>();
		// List<String> redFile = new ArrayList<String>();
		// if (ishaveexclude > 0) {
		// redFile = disposeFileData();
		// }
		// 算合值的时候可以使用
		// if (StringUtils.isNotBlank(xmlData)) {
		// Map<String, String> map = LottoryUtils.disposeXmlStatData(xmlData);
		// }
		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLotteryFilterResult();
		int last = 0;
		int page = 50000;
		List list = new ArrayList();
		while (last < count) {
			list = this.dao.getSsqLottoryFilterResultLimit(last, page);
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lValue = (Map) iterator.next();
				String[] lValues = StringUtils.split(ObjectUtils.toString(lValue.get("value")), ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;
				if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeRequiredCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeAnyOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedNotIncludeTheCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedInTheCodes(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedTogethorCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeThreeEvenIn(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeDifferCode(lValues)) {
					continue;
				}

				if (!LotterySsqAlgorithm.isRedIncludeMediaFourCode(lValues, redCodeList)) {
					continue;
				}

				for (int i = 0; i < lValues.length; i++) {
					if (LotterySsqConifgService.getQuOne() != -1 && NumberUtils.toInt(lValues[i]) <= LotterySsqConifgService.getQuOneNum()) {
						qOne++;
					}
					if (LotterySsqConifgService.getQuTwo() != -1 && NumberUtils.toInt(lValues[i]) > LotterySsqConifgService.getQuOneNum()
							&& NumberUtils.toInt(lValues[i]) <= LotterySsqConifgService.getQuTwoNum()) {
						qTwo++;
					}
					if (LotterySsqConifgService.getQuThree() != -1 && NumberUtils.toInt(lValues[i]) > LotterySsqConifgService.getQuTwoNum()) {
						qThree++;
					}
				}
				if (LotterySsqAlgorithm.isRedCoincidenceZone(lValues, qOne, qTwo, qThree)) {
					redList.add(ObjectUtils.toString(lValue.get("value")));
				}
			}
		}
		writeFile(redList, "d:/myproject/current.xml",false);
	}

	/**
	 * 以追加的方式写入文件
	 * 
	 * @param redList
	 * @param fileName
	 */
	private void writeFile(List<String> redList, String fileName,boolean isAppend) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
			}
			FileWriter writer = new FileWriter(file, isAppend);
			for (int i = 0; i < redList.size(); i++) {
				String filerRed = (String) redList.get(i);
				filerRed = (i == redList.size() - 1) ? filerRed : filerRed + "\n";
				writer.write(filerRed);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> fromFileMediaData(File qsFile) throws IOException {
		FileReader rd = new FileReader(qsFile);
		BufferedReader br = new BufferedReader(rd);
		String line = "";
		List<String> list = new ArrayList<String>();
		while (line != null) {
			line = br.readLine();
			if (line != null) {
				list.add(line);
			}
		}
		rd.close();
		return list;
	}

	/**
	 * 处理媒体号码及自己收集的号码
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void filterCurrentRedCode() {
		this.initConifg(true);
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		// 媒体预测号码
		List<String> redMedia = new ArrayList<String>();
		// 本人添加的过滤号码
		List<String> redFile = new ArrayList<String>();
		Document document = null;
		if (StringUtils.isNotBlank(xmlData)) {
			try {
				document = DocumentHelper.parseText(xmlData);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		if(document!=null)
		{
			if (MapUtils.isEmpty(this.dao.isGenLotteryResult("1", LotterySsqMediaUtils.getMediaExpect(document)))) {
				this.dao.clearSsqLotteryFilterResult();
				this.dao.clearSsqLotteryCollectResult();
			}
		}
		if(document!=null)
		{
			this.expect = LotterySsqMediaUtils.getMediaExpect(document);
			if (!isSaveToDatabase) {
				redMedia = this.lotterySsqMediaService.parseCurrentMediaRedCode(document);
				this.lotterySsqMediaService.saveCurrentMediaRedCode(redMedia, this.expect);
			} else {
				redMedia = this.lotterySsqMediaService.parseCurrentMediaRedCode(document);
				this.lotterySsqMediaService.saveCurrentMediaRedCodeToDb(redMedia, this.expect);
			}
		}
		if (LotterySsqConifgService.getIshaveexclude() > 0) {
			if (!isSaveToDatabase) {
				this.lotterySsqOtherCommendService.parseCurrentFileRedCodeToFile("lottery/ssq/excluderedfile.txt", this.expect);
			} else {
				this.lotterySsqOtherCommendService.parseCurrentFileRedCodeToDb("lottery/ssq/excluderedfile.txt");
			}
		}

		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLotteryResult();
		int last = 0;
		int page = 40000;
		while (last < count) {
			List list = this.dao.getLottoryResultLimit(last, page);
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lMap = (Map) iterator.next();
				String lValue = (String) lMap.get("value");
				String[] lValues = StringUtils.split(lValue, ",");
				if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeAnyOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues) && !LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
					continue;
				}
				if (isSaveToDatabase && this.dao.isExistSsqLotteryCollect(lValue)) {
					continue;
				} else if (CollectionUtils.isNotEmpty(redFile) && redFile.contains(lValue)) {
					continue;
				}
				if (redMedia.contains(lValue)) {
					continue;
				}
				redList.add(lValue);
				if (isSaveToDatabase && redList.size() > 1000) {
					this.dao.addSsqLotteryFilterResult(redList);
					redList.clear();
				} else if (!isSaveToDatabase && redList.size() > 1000) {
					this.writeFile(redList, "d:/myproject/ssq_red_" + this.expect + ".xml",true);
					redList.clear();
				}
			}
		}
		this.dao.saveLotteryGenLog("1", this.expect, "1");
	}

	/**
	 * 
	 * 从文件中读取数据过滤
	 */
	@SuppressWarnings("unchecked")
	public void filterCurrentRedCodeFromFile() {
		this.initConifg(true);
		String xmlData = HttpHtmlService.getXmlContent(LotterySsqConifgService.getXmlUrl());
		if (StringUtils.isBlank(xmlData)) {
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlData);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		this.expect = LotterySsqMediaUtils.getMediaExpect(document);
		// 媒体预测号码
		List<String> redMedia = new ArrayList<String>();
		// 本人添加的过滤号码
		List<String> redFile = new ArrayList<String>();
		File qsFile = new File("d:/myproject/ssq_media_red_" + this.expect + ".xml");
		try {
			redMedia = this.fromFileMediaData(qsFile);
			qsFile = new File("d:/myproject/ssq_file_red_" + this.expect + ".xml");
			redFile = this.fromFileMediaData(qsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> redList = new ArrayList<String>();
		if (MapUtils.isEmpty(this.dao.isGenLotteryResult("1", LotterySsqMediaUtils.getMediaExpect(document)))) {
			this.dao.clearSsqLotteryFilterResult();
			this.dao.clearSsqLotteryCollectResult();
		}
		int count = this.dao.getTotalLotteryResult();
		int last = 0;
		int page = 60000;
		List list = null;
		while (last < count) {
			list = this.dao.getLottoryResultLimit(last, page);
			if (list.size() == count) {
				last = list.size();
			} else {
				last += page;
			}
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lMap = (Map) iterator.next();
				String lValue = (String) lMap.get("value");
				String[] lValues = StringUtils.split(lValue, ",");

				if (!LotterySsqAlgorithm.isRedNumericInRange(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeAnyOneCode(lValues)) {
					continue;
				}
				if (!LotterySsqAlgorithm.isRedIncludeSideCode(lValues) && !LotterySsqAlgorithm.isRedIncludeEvenIn(lValues)) {
					continue;
				}
				if (CollectionUtils.isNotEmpty(redFile) && redFile.contains(lValue)) {
					continue;
				}
				if (redMedia.contains(lValue)) {
					continue;
				}
				this.dao.addSsqLotteryFilterResult(lValue);
			}
		}
		this.writeFile(redList, "d:/myproject/ssq_red_" + this.expect + ".xml",true);
		this.dao.saveLotteryGenLog("1", this.expect, "1");
	}

	public static void main(String[] args) {
		File file = new File("d:/myproject/current.tt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			List<String> list = new ArrayList<String>();
			String line = br.readLine();
			while (line != null) {
				list.add(line);
				line = br.readLine();
			}
			List<String> redList = new ArrayList<String>();
			for (int i = 0; i < list.size(); i++) {
				String[] redCode = list.get(i).split(",");
				for (int j = 0; j < redCode.length; j++) {
					if (!redList.contains(redCode[j])) {
						redList.add(redCode[j]);
					}
				}
			}
			String out = "";
			for (Iterator<String> iterator = redList.iterator(); iterator.hasNext();) {
				String code = iterator.next();
				if ("".equals(out)) {
					out = code;
				} else {
					out += "," + code;
				}
			}
			System.out.println(out + "            " + redList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// // ClassLoader classLoader=new ClassLoader();
		// ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
		// Enumeration urls = null;
		// try {
		// urls = classLoader.getResources("lottery/ssq/excluderedfile.txt");
		// } catch (IOException e) {
		// }
		// while (urls.hasMoreElements()) {
		// URL url = (URL) urls.nextElement();
		// InputStream is = null;
		// try {
		// URLConnection con = url.openConnection();
		// is = con.getInputStream();
		//
		// BufferedInputStream bis = new BufferedInputStream(is);
		// byte[] bs = new byte[bis.available()];
		// int ch = 0;
		// // java.nio.ByteBuffer bb=java.nio.ByteBuffer.allocate(2048);
		// // CharBuffer cb=CharBuffer.allocate(bis.available());
		// // while(ch!=-1){
		// // // bb.put(bs);
		// // cb.append((char)ch);
		// // ch=bis.read();
		// //
		// // }
		// bis.read(bs);
		// System.out.println(new String(bs, "GBK"));
		// // String redCode=new String(bb.array(),"GBK");
		// // String[] redCodes=StringUtils.split(redCode, '\n');
		// // System.out.println(redCodes+"\n"+redCode);
		// } finally {
		// if (is != null) {
		// is.close();
		// }
		// }
		// }
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
	}
}
