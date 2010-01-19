package com.lyxmq.lottery.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ClassUtils;

import com.myfetch.service.http.HttpHtmlService;

public class lottoryService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(lottoryService.class);
	LottoryDao dao = null;
	private static int count = 0;
	private static String xmlUrl = "http://www.500wan.com/static/info/ssq/mediadetail/10002.xml";
	private static int quOne = -1;
	private static int quTwo = -1;
	private static int quThree = 3;
	private static int quOneNum = 11;
	private static int quTwoNum = 22;
	// 包含其中一个数字
	private static String[] includeRed = new String[] {};
	// 不包含
	private static String[] excludeRed = new String[] {};
	// 必须有的
	private static String[] musthavered = new String[] {};
	// 是否从 文件中读取排除号码
	private static int ishaveexclude = 0;
	// 第一个数字要求大于等于该数字
	private static int firstMinCode = 2;
	// 第一个数字要求小于等于该数字
	private static int firstMaxCode = 9;
	// 最大数字要求大于等于该数字
	private static int lastMinCode = 29;
	// 最大数字要求小于等于该数字
	private static int lastMaxCode = 34;
	// 有几个两连号
	private static int haveTwoSeries = 1;
	// 有几个三连号
	private static int haveThreeSeries = 0;
	// 有几个差值为1的，，如1，3，5 算两个
	private static int haveOnediffer = 1;
	static {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("lottory/lottory.properties");
			xmlUrl = StringUtils.isNotBlank(pro.getProperty("xmlUrl")) ? pro.getProperty("xmlUrl") : xmlUrl;
			quOne = StringUtils.isNotBlank(pro.getProperty("quOne")) ? NumberUtils.toInt(pro.getProperty("quOne")) : quOne;
			quTwo = StringUtils.isNotBlank(pro.getProperty("quTwo")) ? NumberUtils.toInt(pro.getProperty("quTwo")) : quTwo;
			quThree = StringUtils.isNotBlank(pro.getProperty("quThree")) ? NumberUtils.toInt(pro.getProperty("quThree")) : quThree;
			ishaveexclude = StringUtils.isNotBlank(pro.getProperty("ishaveexclude")) ? NumberUtils.toInt(pro.getProperty("ishaveexclude")) : ishaveexclude;
			includeRed = StringUtils.isNotBlank("includeRed") ? StringUtils.split(pro.getProperty("includeRed"), ",") : null;
			excludeRed = StringUtils.isNotBlank("excludeRed") ? StringUtils.split(pro.getProperty("excludeRed"), ",") : null;
			musthavered = StringUtils.isNotBlank("musthavered") ? StringUtils.split(pro.getProperty("musthavered"), ",") : null;
			firstMinCode = StringUtils.isNotBlank(pro.getProperty("firstMinCode")) ? NumberUtils.toInt(pro.getProperty("firstMinCode")) : firstMinCode;
			firstMaxCode = StringUtils.isNotBlank(pro.getProperty("firstMaxCode")) ? NumberUtils.toInt(pro.getProperty("firstMaxCode")) : firstMaxCode;
			lastMinCode = StringUtils.isNotBlank(pro.getProperty("lastMinCode")) ? NumberUtils.toInt(pro.getProperty("lastMinCode")) : lastMinCode;
			lastMaxCode = StringUtils.isNotBlank(pro.getProperty("lastMaxCode")) ? NumberUtils.toInt(pro.getProperty("lastMaxCode")) : lastMaxCode;
			haveTwoSeries = StringUtils.isNotBlank(pro.getProperty("haveTwoSeries")) ? NumberUtils.toInt(pro.getProperty("haveTwoSeries")) : haveTwoSeries;
			haveThreeSeries = StringUtils.isNotBlank(pro.getProperty("haveThreeSeries")) ? NumberUtils.toInt(pro.getProperty("haveThreeSeries"))
					: haveThreeSeries;
			haveOnediffer = StringUtils.isNotBlank(pro.getProperty("haveOnediffer")) ? NumberUtils.toInt(pro.getProperty("haveOnediffer")) : haveOnediffer;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setDao(LottoryDao dao) {
		this.dao = dao;
	}

	public void saveLottoryResult(String redResult) {
		this.dao.saveLottoryResult(redResult);
	}

	public void saveTempLottoryResult(String redResult) {
		this.dao.saveTempLottoryResult(redResult);
	}

	public void getCurrentExpertMergeResult() {
		String xmlData = getXmlData(xmlUrl);

	}

	@SuppressWarnings("unchecked")
	public void getCurrentExpertSingleResult() {
		String xmlData = getXmlData(xmlUrl);
		List<String> redMedia = new ArrayList<String>();
		List<String> redFile = new ArrayList<String>();
		redMedia = disposeXmlData(xmlData);
		if (ishaveexclude > 0) {
			redFile = disposeFileData();
		}
		Map<String, String> map = LottoryUtils.disposeXmlStatData(xmlData);
		List<String> redList = new ArrayList<String>();
		int count = this.dao.getTotalLottoryResult();
		int last = 0;
		int page = 10000;
		int tmpCount = 0;
		while (last < count) {
			List list = this.dao.getLottoryResultLimit(last, page);
			last += page;
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map lMap = (Map) iterator.next();
				String lValue = (String) lMap.get("value");
				String[] lValues = StringUtils.split(lValue, ",");
				int qOne = 0;
				int qTwo = 0;
				int qThree = 0;
				if (ishaveexclude > 0 && redFile.contains(lValue)) {
					continue;
				}
				if(redMedia.contains(lValue)){
					continue;
				}
				if (NumberUtils.toInt(lValues[0]) > firstMaxCode) {
					continue;
				}
				if (NumberUtils.toInt(lValues[0]) < firstMinCode) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) < lastMinCode) {
					continue;
				}
				if (NumberUtils.toInt(lValues[5]) > lastMaxCode) {
					continue;
				}
				for (int i = 0; i < lValues.length; i++) {
					if (quOne != -1 && NumberUtils.toInt(lValues[i]) <= quOneNum) {
						qOne++;
					}
					if (quTwo != -1 && NumberUtils.toInt(lValues[i]) > quOneNum && NumberUtils.toInt(lValues[i]) <= quTwoNum) {
						qTwo++;
					}
					if (quThree != -1 && NumberUtils.toInt(lValues[i]) > quTwoNum) {
						qThree++;
					}
				}
				if (quOne != -1 && quTwo != -1 && quThree != -1 && (quOne + quTwo + quThree) == 6) {
					if (qOne == quOne && qTwo == quTwo && qThree == quThree) {
						redList.add(lValue);
						tmpCount++;
					}
				} else {
					int[] quTemp = { quOne, quTwo, quThree };
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
		for (int i = 0; i < redList.size(); i++) {
			int tmpRed = 0;
			String filerRed = (String) redList.get(i);
			String[] lValues = StringUtils.split(filerRed, ",");
			
			// 必须包含其中的任意一个数字
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < includeRed.length; k++) {
					if (StringUtils.equals(lValues[j], includeRed[k])) {
						tmpRed++;
					}
				}
			}
			if (tmpRed == 0) {
				redList.remove(i);
				i--;
				continue;
			}
			// 不能包含其中的任意一个数字
			tmpRed = 0;
			for (int j = 0; j < lValues.length; j++) {
				for (int k = 0; k < excludeRed.length; k++) {
					if (StringUtils.equals(lValues[j], excludeRed[k])) {
						tmpRed++;
						break;
					}
				}
				if (tmpRed > 0) {
					break;
				}
			}
			if (tmpRed > 0) {
				redList.remove(i);
				i--;
				continue;
			}
			// 至少有一个两连号 或三连号
			if (haveTwoSeries > 0) {
				tmpRed = 0;
				for (int j = 0; j < lValues.length; j++) {
					int rValue = NumberUtils.toInt(lValues[j]);
					int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
					if (nextValue != -1 && nextValue - rValue == 1) {
						tmpRed++;
					}
				}
				if (tmpRed != haveTwoSeries) {
					redList.remove(i);
					i--;
					continue;
				}
			}
			if (haveThreeSeries > 0) {
				tmpRed = 0;
				for (int j = 0; j < lValues.length; j++) {
					int rValue = NumberUtils.toInt(lValues[j]);
					int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
					int nNextValue = (j + 2) < lValues.length ? NumberUtils.toInt(lValues[j + 2]) : -1;
					if (nextValue != -1 && nNextValue != -1 && nextValue - rValue == 1 && nNextValue - nextValue == 1) {
						tmpRed++;
					}
				}
				if (tmpRed != haveThreeSeries) {
					redList.remove(i);
					i--;
					continue;
				}
			}
			if (haveOnediffer > 0) {
				tmpRed = 0;
				for (int j = 0; j < lValues.length; j++) {
					int rValue = NumberUtils.toInt(lValues[j]);
					int nextValue = (j + 1) < lValues.length ? NumberUtils.toInt(lValues[j + 1]) : -1;
					if (nextValue != -1 && nextValue - rValue == 2) {
						tmpRed++;
					}
				}
				if (tmpRed != haveOnediffer) {
					redList.remove(i);
					i--;
					continue;
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
		try {
			File file = new File("d:/myproject/current.xml");
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

	@SuppressWarnings("unchecked")
	private List<String> disposeFileData() {
		List<String> list = new ArrayList<String>();
		String filestr="";
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources("lottory/excluderedfile.txt");
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
					filestr=new String(bs, "GBK");
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
		}
		if(filestr!=null&&!"".equals(filestr)){
			String[] redCodes=StringUtils.split(filestr, '\n');
			for (int i = 0; i < redCodes.length; i++) {
				String[] tmp=StringUtils.split(redCodes[i],",");
				for (int j = 0; j < tmp.length; j++) {
					tmp[j]=tmp[j].startsWith("0")?tmp[j].replace(tmp[j], ""):tmp[j];
				}
				LottoryConstant.redBall =tmp;
				LottoryUtils.select(6);
				for (int j = 0; j < LottoryConstant.redResultList.size(); j++) {
					String redResult = LottoryConstant.redResultList.get(j);
					if (list.contains(redResult)) {
						continue;
					}
					list.add(redResult);
				}
				LottoryConstant.redResultList = new ArrayList<String>();	
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
				LottoryConstant.redBall = redCodeArr;
				LottoryUtils.select(6);
				for (int i = 0; i < LottoryConstant.redResultList.size(); i++) {
					String redResult = LottoryConstant.redResultList.get(i);
					if (list.contains(redResult)) {
						continue;
					}
					list.add(redResult);
				}
				LottoryConstant.redResultList = new ArrayList<String>();
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
		for (int i = head; i < LottoryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LottoryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
				count++;
			} else if (index == k) {
				r[index - 1] = LottoryConstant.redBall[i];
				if (LottoryUtils.isValidateData(r)) {
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

		int count = this.dao.getTotalLottoryResult();
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

	public static void main(String[] args) throws IOException {
		try {
			// ClassLoader classLoader=new ClassLoader();
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources("lottory/excluderedfile.txt");
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
					int ch = 0;
					// java.nio.ByteBuffer bb=java.nio.ByteBuffer.allocate(2048);
					// CharBuffer cb=CharBuffer.allocate(bis.available());
					// while(ch!=-1){
					// // bb.put(bs);
					// cb.append((char)ch);
					// ch=bis.read();
					//						
					// }
					bis.read(bs);
					System.out.println(new String(bs, "GBK"));
					// String redCode=new String(bb.array(),"GBK");
					// String[] redCodes=StringUtils.split(redCode, '\n');
					// System.out.println(redCodes+"\n"+redCode);
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
