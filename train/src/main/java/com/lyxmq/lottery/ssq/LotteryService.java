package com.lyxmq.lottery.ssq;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
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
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.myfetch.service.http.HttpHtmlService;

public class LotteryService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryService.class);
	LotteryDao dao = null;
	private static int count = 0;
	LotterySsqConifgService  lotterySsqConfigService=null;
	LotterySsqMediaService lotterySsqMediaService=null;
	LotterySsqOtherCommendService lotterySsqOtherCommendService=null;
	public void setLotterySsqConfigService(LotterySsqConifgService lotterySsqConfigService) {
		this.lotterySsqConfigService = lotterySsqConfigService;
	}

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
		this.dao.saveLottoryResult(redResult);
	}

	public void saveTempLottoryResult(String redResult) {
		this.dao.saveTempLottoryResult(redResult);
	}

	public void getCurrentExpertMergeResult() {
		String xmlData = getXmlData(lotterySsqConfigService.getXmlUrl());

	}

	@SuppressWarnings("unchecked")
	public void getCurrentExpertSingleResult() {
		String xmlData = "";
		List<String[]> redCodeList=new ArrayList<String[]>();
		if (StringUtils.isNotBlank(lotterySsqConfigService.getXmlUrl())) {
			xmlData = getXmlData(lotterySsqConfigService.getXmlUrl());
			try {
				Document document = DocumentHelper.parseText(xmlData);
				redCodeList=this.lotterySsqMediaService.getMediaRedCode(document);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		String qs = lotterySsqConfigService.getXmlUrl().substring(lotterySsqConfigService.getXmlUrl().lastIndexOf("/") + 1, lotterySsqConfigService.getXmlUrl().lastIndexOf("."));
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
//		int count = this.dao.getTota/lLotteryFilterResult();
		int last = 0;
		int page = 50000;
		int tmpCount = 0;
		boolean whileFlag = true;
		List list = new ArrayList();
		while (whileFlag) {
			if (StringUtils.isNotBlank(qs)) {
				File qsFile = new File("d:/myproject/ssq_" + qs + ".xml");
				if (qsFile.exists()) {
					try {
						list = fromFileMediaData(qsFile);
						whileFlag = false;
					} catch (IOException e) {
						logger.error(e.getMessage());
						whileFlag = false;
					}
				} else {
					list = this.dao.getLottoryFilterResultLimit(last, page);
				}
			}
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String lValue=(String) iterator.next();
				String[] lValues = StringUtils.split(lValue, ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;
				if (NumberUtils.toInt(lValues[0]) > this.lotterySsqConfigService.getFirstMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[0]) < this.lotterySsqConfigService.getFirstMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[1]) > this.lotterySsqConfigService.getSecondMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[1]) < this.lotterySsqConfigService.getSecondMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[2]) > this.lotterySsqConfigService.getThirdMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[2]) < this.lotterySsqConfigService.getThirdMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[3]) > this.lotterySsqConfigService.getFourthMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[3]) < this.lotterySsqConfigService.getFourthMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) < this.lotterySsqConfigService.getLastMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) > this.lotterySsqConfigService.getLastMaxCode()) {
					continue;
				}
				int tempSelect = 0;
				// 胆
				if (this.lotterySsqConfigService.getMusthavered() != null && this.lotterySsqConfigService.getMusthavered().length > 0) {
					for (int i = 0; i < lValues.length; i++) {
						for (int j = 0; j < this.lotterySsqConfigService.getMusthavered().length; j++) {
							if (StringUtils.equals(lValues[i], ObjectUtils.toString(this.lotterySsqConfigService.getMusthavered()[j]).trim())) {
								tempSelect++;
							}
						}
					}
					if (tempSelect != this.lotterySsqConfigService.getMusthavered().length) {
						continue;
					}
				}
				tempSelect = 0;
				// 必须包含其中的任意一个数字
				for (int j = 0; j < lValues.length; j++) {
					for (int k = 0; k < this.lotterySsqConfigService.getIncludeRed().length; k++) {
						if (StringUtils.equals(lValues[j], this.lotterySsqConfigService.getIncludeRed()[k])) {
							tempSelect++;
						}
					}
				}
				if (tempSelect != this.lotterySsqConfigService.getIncludeRedNum()) {
					continue;
				}
				// 必须包含其中的任意一个数字(边号)
				tempSelect = 0;
				if (this.lotterySsqConfigService.getPreSideCode() != null && this.lotterySsqConfigService.getPreSideCode().length > 0) {
					for (int j = 0; j < lValues.length; j++) {
						for (int k = 0; k < this.lotterySsqConfigService.getPreSideCode().length; k++) {
							if (StringUtils.equals(lValues[j], this.lotterySsqConfigService.getPreSideCode()[k])) {
								tempSelect++;
							}
						}
					}
					if (tempSelect == 0) {
						continue;
					}
				}
				// 不能包含其中的任意一个数字
				tempSelect = 0;
				if (this.lotterySsqConfigService.getExcludeRed() != null && this.lotterySsqConfigService.getExcludeRed().length > 0) {
					for (int j = 0; j < lValues.length; j++) {
						for (int k = 0; k < this.lotterySsqConfigService.getExcludeRed().length; k++) {
							if (StringUtils.equals(lValues[j], this.lotterySsqConfigService.getExcludeRed()[k])) {
								tempSelect++;
								break;
							}
						}
						if (tempSelect > 0) {
							break;
						}
					}
					if (tempSelect > 0) {
						continue;
					}
				}
				// 在指定的一系列号码中选取6个
				if (this.lotterySsqConfigService.getSelectCode() != null && this.lotterySsqConfigService.getSelectCode().length > 0) {
					for (int i = 0; i < lValues.length; i++) {
						for (int j = 0; j < this.lotterySsqConfigService.getSelectCode().length; j++) {
							if (StringUtils.equals(lValues[i], ObjectUtils.toString(this.lotterySsqConfigService.getSelectCode()[j]).trim())) {
								tempSelect++;
							}
						}
					}
					if (tempSelect != 6) {
						continue;
					}
				}
				tempSelect = 0;
				// 不能同时存在的号码
				if (this.lotterySsqConfigService.getCannotSelectedTogethor() != null && this.lotterySsqConfigService.getCannotSelectedTogethor().length > 0) {
					boolean breakFlag = false;
					for (int j = 0; j < this.lotterySsqConfigService.getCannotSelectedTogethor().length; j++) {
						String[] tmp = StringUtils.split(this.lotterySsqConfigService.getCannotSelectedTogethor()[j], ",");
						tempSelect = 0;
						for (int k = 0; k < tmp.length; k++) {
							for (int i = 0; i < lValues.length; i++) {
								if (StringUtils.equals(lValues[i], ObjectUtils.toString(tmp[k]).trim())) {
									tempSelect++;
								}
							}
						}
						if (tmp.length == tempSelect) {
							breakFlag = true;
							break;
						}
					}
					if (breakFlag) {
						continue;
					}
				}

				// 至少有一个两连号 或三连号
				tempSelect = 0;
				if (this.lotterySsqConfigService.getHaveTwoSeries() > 0) {
					tempSelect = 0;
					for (int j = 0; j < lValues.length; j++) {
						int rValue = NumberUtils.toInt(lValues[j]);
						int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
						if (nextValue != -1 && nextValue - rValue == 1) {
							tempSelect++;
						}
					}
					if (tempSelect != this.lotterySsqConfigService.getHaveTwoSeries()) {
						continue;
					}
				}
				if (this.lotterySsqConfigService.getHaveThreeSeries() > 0) {
					tempSelect = 0;
					for (int j = 0; j < lValues.length; j++) {
						int rValue = NumberUtils.toInt(lValues[j]);
						int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
						int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
						if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
							tempSelect++;
						}
					}
					if (tempSelect != this.lotterySsqConfigService.getHaveThreeSeries()) {
						continue;
					}
				}
				// 有几个以上差值为1的
				if (this.lotterySsqConfigService.getHaveOnediffer() > 0) {
					tempSelect = 0;
					for (int j = 0; j < lValues.length; j++) {
						int rValue = NumberUtils.toInt(lValues[j]);
						int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
						if (nextValue != -1 && nextValue - rValue == 2) {
							tempSelect++;
						}
					}
					if (tempSelect < this.lotterySsqConfigService.getHaveOnediffer()) {
						continue;
					}
				}
				
				for (Iterator iterator2 = redCodeList.iterator(); iterator2.hasNext();) {
					tempSelect=0;
					String[] mediaRedCode = (String[]) iterator2.next();
					for (int i = 0; i < mediaRedCode.length; i++) {
						for (int j = 0; j < lValues.length; j++) {
							if(StringUtils.equals(mediaRedCode[i].startsWith("0")?mediaRedCode[i].substring(1):mediaRedCode[i], lValues[j])){
								tempSelect++;
							}
						}
					}
					if(tempSelect>4){
						continue;
					}
				}
				
				for (int i = 0; i < lValues.length; i++) {
					if (this.lotterySsqConfigService.getQuOne() != -1 && NumberUtils.toInt(lValues[i]) <= this.lotterySsqConfigService.getQuOneNum()) {
						qOne++;
					}
					if (this.lotterySsqConfigService.getQuTwo() != -1 && NumberUtils.toInt(lValues[i]) > this.lotterySsqConfigService.getQuOneNum() && NumberUtils.toInt(lValues[i]) <= this.lotterySsqConfigService.getQuTwoNum()) {
						qTwo++;
					}
					if (this.lotterySsqConfigService.getQuThree() != -1 && NumberUtils.toInt(lValues[i]) > this.lotterySsqConfigService.getQuTwoNum()) {
						qThree++;
					}
				}
				if (this.lotterySsqConfigService.getQuOne() != -1 && this.lotterySsqConfigService.getQuTwo() != -1 && this.lotterySsqConfigService.getQuThree() != -1 && (this.lotterySsqConfigService.getQuOne() + this.lotterySsqConfigService.getQuTwo() + this.lotterySsqConfigService.getQuThree()) == 6) {
					if (qOne == this.lotterySsqConfigService.getQuOne() && qTwo == this.lotterySsqConfigService.getQuTwo() && qThree == this.lotterySsqConfigService.getQuThree()) {
						redList.add(lValue);
						tmpCount++;
					}
				} else {
					int[] quTemp = { this.lotterySsqConfigService.getQuOne(), this.lotterySsqConfigService.getQuTwo(), this.lotterySsqConfigService.getQuThree() };
					boolean[] flag = { false, false, false };
					for (int i = 0; i < quTemp.length; i++) {
						if (quTemp[i] != -1) {
							if (i == 0 && quTemp[i] == qOne) {
								flag[i] = true;
							}
							if (i == 1 && quTemp[i] == qTwo) {
								flag[i] = true;
							}
							if (i == 2 && quTemp[i] == qThree) {
								flag[i] = true;
							}
						} else {
							flag[i] = true;
						}
					}
					if (flag[0] && flag[1] && flag[2]) {
						redList.add(lValue);
						tmpCount++;
					}
				}
			}
		}
		String filePrint = "";
		for (int i = 0; i < redList.size(); i++) {
			String filerRed = (String) redList.get(i);
			if ("".equals(filePrint)) {
				filePrint = filerRed;
			} else {
				filePrint += "\n" + filerRed;
			}
		}
		writeFile(filePrint, "d:/myproject/current.xml");
	}

	private void writeFile(String filePrint, String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			} else {
			}
			FileWriter writer = new FileWriter(file);
			writer.write(filePrint);
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

	@SuppressWarnings("unchecked")
	private List<String> disposeFileData() {
		List<String> list = new ArrayList<String>();
		String filestr = "";
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources("lottery/ssq/excluderedfile.txt");
			} catch (IOException e) {
			}
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				InputStream is = null;
				try {
					URLConnection con = url.openConnection();
					is = con.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					byte[] bs = new byte[bis.available()];
					bis.read(bs);
					filestr = new String(bs, "GBK");
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
		if (filestr != null && !"".equals(filestr)) {
			String[] redCodes = StringUtils.split(StringUtils.remove(filestr, '\r'), '\n');
			for (int i = 0; i < redCodes.length; i++) {
				String[] tmp = StringUtils.split(redCodes[i], ",");
				for (int j = 0; j < tmp.length; j++) {
					tmp[j] = tmp[j].startsWith("0") ? tmp[j].replace("0", "") : tmp[j];
				}
				LotteryConstant.redBall = tmp;
				LotteryUtils.select(6);
				for (int j = 0; j < LotteryConstant.redResultList.size(); j++) {
					String redResult = LotteryConstant.redResultList.get(j);
					if (list.contains(redResult)) {
						continue;
					}
					list.add(redResult);
				}
				LotteryConstant.redResultList = new ArrayList<String>();
			}

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	private List disposeXmlData(String xmlData) {
		try {
			Document document = DocumentHelper.parseText(xmlData);
			Node kjjg = document.selectSingleNode("//xml/head/kjflag");
			String kjflag = ((Element) kjjg).getText();
			if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {

			}
			List<String> list = new ArrayList<String>();
			List media = document.selectNodes("//xml/media");
			for (Iterator iterator = media.iterator(); iterator.hasNext();) {
				Node node = (Node) iterator.next();
				List redNode = node.selectNodes("tjcode/redcode/code");
				String[] redCodeArr = null;
				if (CollectionUtils.isNotEmpty(redNode)) {
					redCodeArr = new String[redNode.size()];
				}
				for (int i = 0; i < redNode.size(); i++) {
					Node red = (Node) redNode.get(i);
					redCodeArr[i] = red.getText();
				}
				List blueNode = node.selectNodes("tjcode/bluecode/code");
				String[] blueCodeArr = null;
				if (CollectionUtils.isNotEmpty(blueNode)) {
					blueCodeArr = new String[blueNode.size()];
				}
				for (int i = 0; i < blueNode.size(); i++) {
					Node blue = (Node) blueNode.get(i);
					blueCodeArr[i] = blue.getText().startsWith("0") ? blue.getText().substring(1) : blue.getText();
				}
				LotteryConstant.redBall = redCodeArr;
				LotteryUtils.select(6);
				for (int i = 0; i < LotteryConstant.redResultList.size(); i++) {
					String redResult = LotteryConstant.redResultList.get(i);
					if (list.contains(redResult)) {
						continue;
					}
					list.add(redResult);
				}
				LotteryConstant.redResultList = new ArrayList<String>();
			}

			if (CollectionUtils.isNotEmpty(list)) {
				String qs = lotterySsqConfigService.getXmlUrl().substring(lotterySsqConfigService.getXmlUrl().lastIndexOf("/") + 1, lotterySsqConfigService.getXmlUrl().lastIndexOf("."));
				try {
					File file = new File("d:/myproject/ssq_media_" + qs + ".xml");
					if (!file.exists()) {
						file.createNewFile();
					} else {
					}
					FileWriter writer = new FileWriter(file);
					StringBuffer filePrint = new StringBuffer();
					int temp = 0;
					for (Iterator iterator = list.iterator(); iterator.hasNext();) {
						temp++;
						String redCode = (String) iterator.next();
						if (temp < list.size()) {
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
			return list;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getXmlData(String url) {
		return HttpHtmlService.getXmlContent(url);
	}

	public void saveAllRedResult() {
		select(6);
	}

	public void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}

	private void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < LotteryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LotteryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
				count++;
			} else if (index == k) {
				r[index - 1] = LotteryConstant.redBall[i];
				if (LotteryUtils.isValidateData(r)) {
					this.dao.saveLottoryResult(StringUtils.join(r, ","));
				}
				subselect(i + 1, index + 1, r, k);
				count++;
			} else {
				return;
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void testCode() {

		int count = this.dao.getTotalLotteryResult();
		int last = 0;
		int page = 100000;
		int tmpCount = 0;
		while (last < count) {
			List list = this.dao.getLottoryResultLimit(last, page);
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;
				Map map = (Map) iterator.next();
				String codes = (String) map.get("value");
				String[] codeSix = StringUtils.split(codes, ",");
				for (int i = 0; i < codeSix.length; i++) {
					if (NumberUtils.toInt(codeSix[i]) <= 11) {
						qOne++;
					}
					if (NumberUtils.toInt(codeSix[i]) > 11 && NumberUtils.toInt(codeSix[i]) <= 22) {
						qTwo++;
					}
					if (NumberUtils.toInt(codeSix[i]) > 22 && NumberUtils.toInt(codeSix[i]) <= 33) {
						qThree++;
					}
				}
				if (qOne == 0 || qTwo == 0 || qThree == 0) {
					tmpCount++;
				}
			}

		}
		System.out.println(tmpCount);
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

	/**
	 * 过滤掉最基本的号码 1：媒体号码 2：推荐号码 3：基本规则号码(必须包含上一期的一个号码,必须包含一个边好，必须有个连号，必须有个差值为1的)
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void filterCurrentRedCode() {
		String xmlData = "";
		if (StringUtils.isNotBlank(lotterySsqConfigService.getXmlUrl())) {
			xmlData = getXmlData(lotterySsqConfigService.getXmlUrl());
		}
		String qs = lotterySsqConfigService.getXmlUrl().substring(lotterySsqConfigService.getXmlUrl().lastIndexOf("/") + 1, lotterySsqConfigService.getXmlUrl().lastIndexOf("."));
		// 媒体预测号码
		List<String[]> redMedia = new ArrayList<String[]>();
		// 本人添加的过滤号码
		List<String> redFile = new ArrayList<String>();
		if (StringUtils.isNotBlank(xmlData)) {
			Document document=null;
			try {
				document = DocumentHelper.parseText(xmlData);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			redMedia = this.lotterySsqMediaService.getMediaRedCode(document);
			this.lotterySsqMediaService.saveCurrentMediaRedCode(redMedia);
		}
		if (this.lotterySsqConfigService.getIshaveexclude()> 0) {
			redFile = disposeFileData();
		}
		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLotteryResult();
		int last = 0;
		int page = 10000;
		while (last < count) {
			List list = this.dao.getLottoryResultLimit(last, page);
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {

				Map lMap = (Map) iterator.next();
				String lValue = (String) lMap.get("value");
				String[] lValues = StringUtils.split(lValue, ",");
				if (NumberUtils.toInt(lValues[0]) > this.lotterySsqConfigService.getFirstMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[0]) < this.lotterySsqConfigService.getFirstMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[1]) > this.lotterySsqConfigService.getSecondMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[1]) < this.lotterySsqConfigService.getSecondMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[2]) > this.lotterySsqConfigService.getThirdMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[2]) < this.lotterySsqConfigService.getThirdMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[3]) > this.lotterySsqConfigService.getFourthMaxCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[3]) < this.lotterySsqConfigService.getFourthMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) < this.lotterySsqConfigService.getLastMinCode()) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) > this.lotterySsqConfigService.getLastMaxCode()) {
					continue;
				}
				int tempSelect = 0;
				tempSelect = 0;
				// 必须包含其中的任意一个数字
				for (int j = 0; j < lValues.length; j++) {
					for (int k = 0; k < this.lotterySsqConfigService.getIncludeRed().length; k++) {
						if (StringUtils.equals(lValues[j], this.lotterySsqConfigService.getIncludeRed()[k])) {
							tempSelect++;
						}
					}
				}
				if (tempSelect != this.lotterySsqConfigService.getIncludeRedNum()) {
					continue;
				}
				// 必须包含其中的任意一个数字(边号)
				tempSelect = 0;
				if (this.lotterySsqConfigService.getPreSideCode() != null && this.lotterySsqConfigService.getPreSideCode().length > 0) {
					for (int j = 0; j < lValues.length; j++) {
						for (int k = 0; k < this.lotterySsqConfigService.getPreSideCode().length; k++) {
							if (StringUtils.equals(lValues[j], this.lotterySsqConfigService.getPreSideCode()[k])) {
								tempSelect++;
							}
						}
					}
				}

				// 至少有一个两连号
<<<<<<< .mine
				int tempSelect2 = 0;
				for (int j = 0; j < lValues.length; j++) {
					int rValue = NumberUtils.toInt(lValues[j]);
					int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
					if (nextValue != -1 && nextValue - rValue == 1) {
						tempSelect2++;
=======
				int serTempSelect = 0;
				for (int j = 0; j < lValues.length; j++) {
					int rValue = NumberUtils.toInt(lValues[j]);
					int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
					if (nextValue != -1 && nextValue - rValue == 1) {
						serTempSelect++;
>>>>>>> .r203
					}
				}
<<<<<<< .mine
				if (tempSelect <= 0 && tempSelect2 <= 0) {
=======
				if (serTempSelect <=0 && tempSelect<=0) {
>>>>>>> .r203
					continue;
				}
<<<<<<< .mine
				if (this.lotterySsqConfigService.getIshaveexclude() > 0 && redFile.contains(lValue)) {
					continue;
				}
=======
				if (CollectionUtils.isNotEmpty(redFile)&&redFile.contains(lValue)) {
					continue;
				}
>>>>>>> .r203
				if (redMedia.contains(lValue)) {
					continue;
				}
				redList.add(lValue);
			}
		}
<<<<<<< .mine
		if (MapUtils.isEmpty(this.dao.isGenLotteryResult("1", qs))) {
			this.dao.clearSsqLotteryFilterResult();
		}
		StringBuffer sb = new StringBuffer();
=======
		if(MapUtils
				.isEmpty(this.dao.isGenLotteryResult("1", qs))){
			this.dao.deleteSsqLotteryFilterResult();
				
		}
>>>>>>> .r203
		for (int i = 0; i < redList.size(); i++) {
			String redCode = redList.get(i);
			sb.append(redCode + "\n");
			// this.dao.addSsqLotteryFilterResult(redCode);
		}
		this.writeFile(sb.toString(), "d:/myproject/ssq_" + qs + ".xml");
		this.dao.saveLotteryGenLog("1", qs, "1");
	}
}
