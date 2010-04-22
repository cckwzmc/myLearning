package com.lyxmq.lottery.ssq.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 * 
 */
public class LotterySsqConifgService {
	private static String expect;
	private static String media500WanUrl = "";
	private static String mediaSinaUrl = "";
	private static String dyjUrl = "";
	private static String dyjDowload = "";
	private static String www500wanUrl = "";
	private static String www500wanDowload = "";
	private static int quOne = -1;
	private static int quTwo = -1;
	private static int quThree = -1;
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
	private static int firstMinCode = 1;
	// 第一个数字要求小于等于该数字
	private static int firstMaxCode = 33;
	// 最大数字要求大于等于该数字
	private static int lastMinCode = 29;
	// 最大数字要求小于等于该数字
	private static int lastMaxCode = 34;
	// 有几个两连号
	private static int haveTwoSeries = -1;
	// 有几个三连号
	private static int haveThreeSeries = -1;
	// 有几个差值为1的，，如1，3，5 算两个
	private static int haveOnediffer = -1;
	// 在这些号码中选择6个
	private static String[] selectCode = new String[] {};
	// 不能同时出现的号码
	private static String[] cannotSelectedTogethor = new String[] {};
	// 是否有边号
	private static int haveSideCode = -1;
	// 包含includeRed中的几个数字
	private static int includeRedNum = 1;
	// 上一期号码
	private static String[] preRedCode = new String[] {};
	// 计算出上一期的所有的边号
	private static String[] preSideCode = new String[] {};
	private static int secondMinCode = 2;
	private static int secondMaxCode = 20;
	private static int thirdMinCode = 3;
	private static int thirdMaxCode = 29;
	private static int fourthMinCode = 4;
	private static int fourthMaxCode = 33;
	private static String[] selectedOneCode = new String[]{};
	static {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("lottery/ssq/lottery.properties");
			expect = StringUtils.isNotBlank(pro.getProperty("expect")) ? pro.getProperty("expect") : expect;
			media500WanUrl = StringUtils.isNotBlank(pro.getProperty("media500WanUrl")) ? pro.getProperty("media500WanUrl") : media500WanUrl;
			media500WanUrl = StringUtils.replace(media500WanUrl, "@expect@", expect);
			mediaSinaUrl = StringUtils.isNotBlank(pro.getProperty("mediaSinaUrl")) ? pro.getProperty("mediaSinaUrl") : mediaSinaUrl;
			mediaSinaUrl = StringUtils.replace(mediaSinaUrl, "@expect@", expect);

			dyjUrl = StringUtils.isNotBlank(pro.getProperty("dyjUrl")) ? pro.getProperty("dyjUrl") : dyjUrl;
			dyjUrl = StringUtils.replace(dyjUrl, "@expect@", expect);
			dyjDowload = StringUtils.isNotBlank(pro.getProperty("dyjDowload")) ? pro.getProperty("dyjDowload") : dyjDowload;
			dyjDowload = StringUtils.replace(dyjDowload, "@expect@", expect);
			
			www500wanUrl = StringUtils.isNotBlank(pro.getProperty("www500wanUrl")) ? pro.getProperty("www500wanUrl") : www500wanUrl;
			www500wanUrl = StringUtils.replace(www500wanUrl, "@expect@", expect);
			www500wanDowload = StringUtils.isNotBlank(pro.getProperty("www500wanDowload")) ? pro.getProperty("www500wanDowload") : www500wanDowload;
			www500wanDowload = StringUtils.replace(www500wanDowload, "@expect@", expect);

			quOne = StringUtils.isNotBlank(pro.getProperty("quOne")) ? NumberUtils.toInt(pro.getProperty("quOne")) : quOne;
			quTwo = StringUtils.isNotBlank(pro.getProperty("quTwo")) ? NumberUtils.toInt(pro.getProperty("quTwo")) : quTwo;
			quThree = StringUtils.isNotBlank(pro.getProperty("quThree")) ? NumberUtils.toInt(pro.getProperty("quThree")) : quThree;
			ishaveexclude = StringUtils.isNotBlank(pro.getProperty("ishaveexclude")) ? NumberUtils.toInt(pro.getProperty("ishaveexclude")) : ishaveexclude;
			includeRed = StringUtils.isNotBlank("includeRed") ? StringUtils.split(pro.getProperty("includeRed"), ",") : null;
			selectedOneCode = StringUtils.isNotBlank("selectedOneCode") ? StringUtils.split(pro.getProperty("selectedOneCode"), "|") : null;
			excludeRed = StringUtils.isNotBlank("excludeRed") ? StringUtils.split(pro.getProperty("excludeRed"), ",") : null;
			musthavered = StringUtils.isNotBlank("musthavered") ? StringUtils.split(pro.getProperty("musthavered"), ",") : null;
			cannotSelectedTogethor = StringUtils.isNotBlank("cannotSelectedTogethor") ? StringUtils.split(pro.getProperty("cannotSelectedTogethor"), "|")
					: null;
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
			haveThreeSeries = StringUtils.isNotBlank(pro.getProperty("haveThreeSeries")) ? NumberUtils.toInt(pro.getProperty("haveThreeSeries"))
					: haveThreeSeries;
			haveOnediffer = StringUtils.isNotBlank(pro.getProperty("haveOnediffer")) ? NumberUtils.toInt(pro.getProperty("haveOnediffer")) : haveOnediffer;
			haveSideCode = StringUtils.isNotBlank(pro.getProperty("haveSideCode")) ? NumberUtils.toInt(pro.getProperty("haveSideCode")) : haveSideCode;
			includeRedNum = StringUtils.isNotBlank(pro.getProperty("includeRedNum")) ? NumberUtils.toInt(pro.getProperty("includeRedNum")) : includeRedNum;
			if (haveSideCode == 1) {
				String temp = "";
				for (int i = 0; i < preRedCode.length; i++) {
					int tmpInt = NumberUtils.toInt(preRedCode[i]);
					if ("".equals(temp)) {
						temp = tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0" + (tmpInt + 1)
								: (tmpInt + 1)));
					} else {
						temp += ","
								+ (tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0"
										+ (tmpInt + 1) : (tmpInt + 1))));

					}
				}
				preSideCode = StringUtils.split(temp, ",");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getExpect() {
		return expect;
	}


	public static String getMedia500WanUrl() {
		return media500WanUrl;
	}


	public static String getMediaSinaUrl() {
		return mediaSinaUrl;
	}


	public static String getDyjUrl() {
		return dyjUrl;
	}

	public static String getWww500wanUrl() {
		return www500wanUrl;
	}

	public static int getQuOne() {
		return quOne;
	}

	public static int getQuTwo() {
		return quTwo;
	}

	public static int getQuThree() {
		return quThree;
	}

	public static int getQuOneNum() {
		return quOneNum;
	}

	public static int getQuTwoNum() {
		return quTwoNum;
	}

	public static String[] getIncludeRed() {
		return includeRed;
	}

	public static String[] getExcludeRed() {
		return excludeRed;
	}

	public static String[] getMusthavered() {
		return musthavered;
	}

	public static int getIshaveexclude() {
		return ishaveexclude;
	}

	public static int getFirstMinCode() {
		return firstMinCode;
	}

	public static int getFirstMaxCode() {
		return firstMaxCode;
	}

	public static int getLastMinCode() {
		return lastMinCode;
	}

	public static int getLastMaxCode() {
		return lastMaxCode;
	}

	public static int getHaveTwoSeries() {
		return haveTwoSeries;
	}

	public static int getHaveThreeSeries() {
		return haveThreeSeries;
	}

	public static int getHaveOnediffer() {
		return haveOnediffer;
	}

	public static String[] getSelectCode() {
		return selectCode;
	}

	public static String[] getCannotSelectedTogethor() {
		return cannotSelectedTogethor;
	}

	public static int getHaveSideCode() {
		return haveSideCode;
	}

	public static int getIncludeRedNum() {
		return includeRedNum;
	}

	public static String[] getPreRedCode() {
		return preRedCode;
	}

	public static String getWww500wanDowload() {
		return www500wanDowload;
	}
	
	public static String[] getPreSideCode() {
		return preSideCode;
	}

	public static int getSecondMinCode() {
		return secondMinCode;
	}

	public static int getSecondMaxCode() {
		return secondMaxCode;
	}

	public static int getThirdMinCode() {
		return thirdMinCode;
	}

	public static int getThirdMaxCode() {
		return thirdMaxCode;
	}

	public static String getDyjDowload() {
		return dyjDowload;
	}


	public static int getFourthMinCode() {
		return fourthMinCode;
	}

	public static int getFourthMaxCode() {
		return fourthMaxCode;
	}


	public static String[] getSelectedOneCode() {
		return selectedOneCode;
	}

}
