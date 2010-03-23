package com.lyxmq.lottery.ssq;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 媒体推荐的处理
 * @author Administrator
 *
 */
public class LotterySsqConifgService {
	public static String xmlUrl = "";
	public static int quOne = -1;
	public static int quTwo = -1;
	public static int quThree = 3;
	public static int quOneNum = 11;
	public static int quTwoNum = 22;
	// 包含其中一个数字
	public static String[] includeRed = new String[] {};
	// 不包含
	public static String[] excludeRed = new String[] {};
	// 必须有的
	public static String[] musthavered = new String[] {};
	// 是否从 文件中读取排除号码
	public static int ishaveexclude = 0;
	// 第一个数字要求大于等于该数字
	public static int firstMinCode = 1;
	// 第一个数字要求小于等于该数字
	public static int firstMaxCode = 33;
	// 最大数字要求大于等于该数字
	public static int lastMinCode = 29;
	// 最大数字要求小于等于该数字
	public static int lastMaxCode = 34;
	// 有几个两连号
	public static int haveTwoSeries = 1;
	// 有几个三连号
	public static int haveThreeSeries = 0;
	// 有几个差值为1的，，如1，3，5 算两个
	public static int haveOnediffer = 1;
	// 在这些号码中选择6个
	public static String[] selectCode = new String[] {};
	// 不能同时出现的号码
	public static String[] cannotSelectedTogethor = new String[] {};
	// 是否有边号
	public static int haveSideCode = 1;
	// 包含includeRed中的几个数字
	public static int includeRedNum = 1;
	// 上一期号码
	public static String[] preRedCode = new String[] {};
	// 计算出上一期的所有的边号
	public static String[] preSideCode = new String[] {};
	public static int secondMinCode = 2;
	public static int secondMaxCode = 20;
	public static int thirdMinCode = 3;
	public static int thirdMaxCode = 29;
	public static int fourthMinCode = 4;
	public static int fourthMaxCode = 33;
	static {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("lottery/ssq/lottery.properties");
			xmlUrl = StringUtils.isNotBlank(pro.getProperty("xmlUrl")) ? pro.getProperty("xmlUrl") : xmlUrl;
			quOne = StringUtils.isNotBlank(pro.getProperty("quOne")) ? NumberUtils.toInt(pro.getProperty("quOne")) : quOne;
			quTwo = StringUtils.isNotBlank(pro.getProperty("quTwo")) ? NumberUtils.toInt(pro.getProperty("quTwo")) : quTwo;
			quThree = StringUtils.isNotBlank(pro.getProperty("quThree")) ? NumberUtils.toInt(pro.getProperty("quThree")) : quThree;
			ishaveexclude = StringUtils.isNotBlank(pro.getProperty("ishaveexclude")) ? NumberUtils.toInt(pro.getProperty("ishaveexclude")) : ishaveexclude;
			includeRed = StringUtils.isNotBlank("includeRed") ? StringUtils.split(pro.getProperty("includeRed"), ",") : null;
			excludeRed = StringUtils.isNotBlank("excludeRed") ? StringUtils.split(pro.getProperty("excludeRed"), ",") : null;
			musthavered = StringUtils.isNotBlank("musthavered") ? StringUtils.split(pro.getProperty("musthavered"), ",") : null;
			cannotSelectedTogethor = StringUtils.isNotBlank("cannotSelectedTogethor") ? StringUtils.split(pro.getProperty("cannotSelectedTogethor"), "|") : null;
			selectCode = StringUtils.isNotBlank("selectCode") ? StringUtils.split(pro.getProperty("selectCode"), ",") : null;
			preRedCode = StringUtils.isNotBlank("preRedCode") ? StringUtils.split(pro.getProperty("preRedCode"), ",") : null;
			firstMinCode = StringUtils.isNotBlank(pro.getProperty("firstMinCode")) ? NumberUtils.toInt(pro.getProperty("firstMinCode")) : firstMinCode;
			firstMaxCode = StringUtils.isNotBlank(pro.getProperty("firstMaxCode")) ? NumberUtils.toInt(pro.getProperty("firstMaxCode")) : firstMaxCode;
			secondMinCode = StringUtils.isNotBlank(pro.getProperty("secondMinCode")) ? NumberUtils.toInt(pro.getProperty("secondMinCode")) : secondMinCode;
			secondMaxCode = StringUtils.isNotBlank(pro.getProperty("secondMaxCode")) ? NumberUtils.toInt(pro.getProperty("secondMaxCode")) : secondMaxCode;
			thirdMinCode = StringUtils.isNotBlank(pro.getProperty("thirdMinCode")) ? NumberUtils.toInt(pro.getProperty("thirdMinCode")) : thirdMinCode;
			thirdMaxCode = StringUtils.isNotBlank(pro.getProperty("thirdMaxCode")) ? NumberUtils.toInt(pro.getProperty("thirdMaxCode")) : thirdMaxCode;
			fourthMinCode = StringUtils.isNotBlank(pro.getProperty("fourthMinCode")) ? NumberUtils.toInt(pro.getProperty("fourthMinCode")) : fourthMinCode;
			fourthMaxCode = StringUtils.isNotBlank(pro.getProperty("fourthMaxCode")) ? NumberUtils.toInt(pro.getProperty("fourthMaxCode")) : fourthMaxCode;
			lastMinCode = StringUtils.isNotBlank(pro.getProperty("lastMinCode")) ? NumberUtils.toInt(pro.getProperty("lastMinCode")) : lastMinCode;
			lastMaxCode = StringUtils.isNotBlank(pro.getProperty("lastMaxCode")) ? NumberUtils.toInt(pro.getProperty("lastMaxCode")) : lastMaxCode;
			haveTwoSeries = StringUtils.isNotBlank(pro.getProperty("haveTwoSeries")) ? NumberUtils.toInt(pro.getProperty("haveTwoSeries")) : haveTwoSeries;
			haveThreeSeries = StringUtils.isNotBlank(pro.getProperty("haveThreeSeries")) ? NumberUtils.toInt(pro.getProperty("haveThreeSeries")) : haveThreeSeries;
			haveOnediffer = StringUtils.isNotBlank(pro.getProperty("haveOnediffer")) ? NumberUtils.toInt(pro.getProperty("haveOnediffer")) : haveOnediffer;
			haveSideCode = StringUtils.isNotBlank(pro.getProperty("haveSideCode")) ? NumberUtils.toInt(pro.getProperty("haveSideCode")) : haveSideCode;
			includeRedNum = StringUtils.isNotBlank(pro.getProperty("includeRedNum")) ? NumberUtils.toInt(pro.getProperty("includeRedNum")) : includeRedNum;
			if (haveSideCode == 1) {
				String temp = "";
				for (int i = 0; i < preRedCode.length; i++) {
					int tmpInt = NumberUtils.toInt(preRedCode[i]);
					if ("".equals(temp)) {
						temp = tmpInt == 1 ? "2" : ((tmpInt - 1) + "," + (tmpInt + 1));
					} else {
						temp += "," + (tmpInt == 1 ? "2" : ((tmpInt - 1) + "," + (tmpInt + 1)));

					}
				}
				preSideCode = StringUtils.split(temp, ",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public String getXmlUrl() {
		return xmlUrl;
	}
	public void setXmlUrl(String xmlUrl) {
		LotterySsqConifgService.xmlUrl = xmlUrl;
	}
	public int getQuOne() {
		return quOne;
	}
	public void setQuOne(int quOne) {
		LotterySsqConifgService.quOne = quOne;
	}
	public int getQuTwo() {
		return quTwo;
	}
	public void setQuTwo(int quTwo) {
		LotterySsqConifgService.quTwo = quTwo;
	}
	public int getQuThree() {
		return quThree;
	}
	public void setQuThree(int quThree) {
		LotterySsqConifgService.quThree = quThree;
	}
	public int getQuOneNum() {
		return quOneNum;
	}
	public void setQuOneNum(int quOneNum) {
		LotterySsqConifgService.quOneNum = quOneNum;
	}
	public int getQuTwoNum() {
		return quTwoNum;
	}
	public void setQuTwoNum(int quTwoNum) {
		LotterySsqConifgService.quTwoNum = quTwoNum;
	}
	public String[] getIncludeRed() {
		return includeRed;
	}
	public void setIncludeRed(String[] includeRed) {
		LotterySsqConifgService.includeRed = includeRed;
	}
	public String[] getExcludeRed() {
		return excludeRed;
	}
	public void setExcludeRed(String[] excludeRed) {
		LotterySsqConifgService.excludeRed = excludeRed;
	}
	public String[] getMusthavered() {
		return musthavered;
	}
	public void setMusthavered(String[] musthavered) {
		LotterySsqConifgService.musthavered = musthavered;
	}
	public int getIshaveexclude() {
		return ishaveexclude;
	}
	public void setIshaveexclude(int ishaveexclude) {
		LotterySsqConifgService.ishaveexclude = ishaveexclude;
	}
	public int getFirstMinCode() {
		return firstMinCode;
	}
	public void setFirstMinCode(int firstMinCode) {
		LotterySsqConifgService.firstMinCode = firstMinCode;
	}
	public int getFirstMaxCode() {
		return firstMaxCode;
	}
	public void setFirstMaxCode(int firstMaxCode) {
		LotterySsqConifgService.firstMaxCode = firstMaxCode;
	}
	public int getLastMinCode() {
		return lastMinCode;
	}
	public void setLastMinCode(int lastMinCode) {
		LotterySsqConifgService.lastMinCode = lastMinCode;
	}
	public int getLastMaxCode() {
		return lastMaxCode;
	}
	public void setLastMaxCode(int lastMaxCode) {
		LotterySsqConifgService.lastMaxCode = lastMaxCode;
	}
	public int getHaveTwoSeries() {
		return haveTwoSeries;
	}
	public void setHaveTwoSeries(int haveTwoSeries) {
		LotterySsqConifgService.haveTwoSeries = haveTwoSeries;
	}
	public int getHaveThreeSeries() {
		return haveThreeSeries;
	}
	public void setHaveThreeSeries(int haveThreeSeries) {
		LotterySsqConifgService.haveThreeSeries = haveThreeSeries;
	}
	public int getHaveOnediffer() {
		return haveOnediffer;
	}
	public void setHaveOnediffer(int haveOnediffer) {
		LotterySsqConifgService.haveOnediffer = haveOnediffer;
	}
	public String[] getSelectCode() {
		return selectCode;
	}
	public void setSelectCode(String[] selectCode) {
		LotterySsqConifgService.selectCode = selectCode;
	}
	public String[] getCannotSelectedTogethor() {
		return cannotSelectedTogethor;
	}
	public void setCannotSelectedTogethor(String[] cannotSelectedTogethor) {
		LotterySsqConifgService.cannotSelectedTogethor = cannotSelectedTogethor;
	}
	public int getHaveSideCode() {
		return haveSideCode;
	}
	public void setHaveSideCode(int haveSideCode) {
		LotterySsqConifgService.haveSideCode = haveSideCode;
	}
	public int getIncludeRedNum() {
		return includeRedNum;
	}
	public void setIncludeRedNum(int includeRedNum) {
		LotterySsqConifgService.includeRedNum = includeRedNum;
	}
	public String[] getPreRedCode() {
		return preRedCode;
	}
	public void setPreRedCode(String[] preRedCode) {
		LotterySsqConifgService.preRedCode = preRedCode;
	}
	public String[] getPreSideCode() {
		return preSideCode;
	}
	public void setPreSideCode(String[] preSideCode) {
		LotterySsqConifgService.preSideCode = preSideCode;
	}
	public int getSecondMinCode() {
		return secondMinCode;
	}
	public void setSecondMinCode(int secondMinCode) {
		LotterySsqConifgService.secondMinCode = secondMinCode;
	}
	public int getSecondMaxCode() {
		return secondMaxCode;
	}
	public void setSecondMaxCode(int secondMaxCode) {
		LotterySsqConifgService.secondMaxCode = secondMaxCode;
	}
	public int getThirdMinCode() {
		return thirdMinCode;
	}
	public void setThirdMinCode(int thirdMinCode) {
		LotterySsqConifgService.thirdMinCode = thirdMinCode;
	}
	public int getThirdMaxCode() {
		return thirdMaxCode;
	}
	public void setThirdMaxCode(int thirdMaxCode) {
		LotterySsqConifgService.thirdMaxCode = thirdMaxCode;
	}
	public int getFourthMinCode() {
		return fourthMinCode;
	}
	public void setFourthMinCode(int fourthMinCode) {
		LotterySsqConifgService.fourthMinCode = fourthMinCode;
	}
	public int getFourthMaxCode() {
		return fourthMaxCode;
	}
	public void setFourthMaxCode(int fourthMaxCode) {
		LotterySsqConifgService.fourthMaxCode = fourthMaxCode;
	}
	
}
