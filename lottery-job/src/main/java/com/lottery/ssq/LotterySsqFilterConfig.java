package com.lottery.ssq;


public class LotterySsqFilterConfig {

	public static int quOne = -1;
	public static int quTwo = -1;
	public static int quThree = -1;
	public static int quOneNum = 11;
	public static int quTwoNum = 22;
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
	public static int haveTwoSeries = -1;
	// 有几个三连号
	public static int haveThreeSeries = -1;
	// 有几个差值为1的，，如1，3，5 算两个
	public static int haveOnediffer = -1;
	// 在这些号码中选择6个
	public static String[] selectCode = new String[] {};
	// 不能同时出现的号码
	public static String[] cannotSelectedTogethor = new String[] {};
	// 是否有边号
	public static int haveSideCode = -1;
	// 包含includeRed中的几个数字
	public static int includePreRedNum = 1;
	public static int secondMinCode = 2;
	public static int secondMaxCode = 20;
	public static int thirdMinCode = 3;
	public static int thirdMaxCode = 29;
	public static int fourthMinCode = 4;
	public static int fourthMaxCode = 33;
	public static String[] selectedOneCode = new String[]{};
	// static {
	// try {
	// Properties pro = PropertiesLoaderUtils.loadAllProperties("lottery/ssq/lottery.properties");
	// expect = StringUtils.isNotBlank(pro.getProperty("expect")) ? pro.getProperty("expect") : expect;
	// media500WanUrl = StringUtils.isNotBlank(pro.getProperty("media500WanUrl")) ? pro.getProperty("media500WanUrl") : media500WanUrl;
	// media500WanUrl = StringUtils.replace(media500WanUrl, "@expect@", expect);
	// mediaSinaUrl = StringUtils.isNotBlank(pro.getProperty("mediaSinaUrl")) ? pro.getProperty("mediaSinaUrl") : mediaSinaUrl;
	// mediaSinaUrl = StringUtils.replace(mediaSinaUrl, "@expect@", expect);
	//
	// dyjUrl = StringUtils.isNotBlank(pro.getProperty("dyjUrl")) ? pro.getProperty("dyjUrl") : dyjUrl;
	// dyjUrl = StringUtils.replace(dyjUrl, "@expect@", expect);
	// dyjDowload = StringUtils.isNotBlank(pro.getProperty("dyjDowload")) ? pro.getProperty("dyjDowload") : dyjDowload;
	// dyjDowload = StringUtils.replace(dyjDowload, "@expect@", expect);
	//			
	// www500wanUrl = StringUtils.isNotBlank(pro.getProperty("www500wanUrl")) ? pro.getProperty("www500wanUrl") : www500wanUrl;
	// www500wanUrl = StringUtils.replace(www500wanUrl, "@expect@", expect);
	// www500wanDowload = StringUtils.isNotBlank(pro.getProperty("www500wanDowload")) ? pro.getProperty("www500wanDowload") : www500wanDowload;
	// www500wanDowload = StringUtils.replace(www500wanDowload, "@expect@", expect);
	//			
	// caipiaoUrl = StringUtils.isNotBlank(pro.getProperty("caipiaoUrl")) ? pro.getProperty("caipiaoUrl") : caipiaoUrl;
	// caipiaoUrl = StringUtils.replace(caipiaoUrl, "@expect@", expect);
	// caipiaoDowload = StringUtils.isNotBlank(pro.getProperty("caipiaoDowload")) ? pro.getProperty("caipiaoDowload") : caipiaoDowload;
	// caipiaoDowload = StringUtils.replace(caipiaoDowload, "@expect@", expect);
	//
	// quOne = StringUtils.isNotBlank(pro.getProperty("quOne")) ? NumberUtils.toInt(pro.getProperty("quOne")) : quOne;
	// quTwo = StringUtils.isNotBlank(pro.getProperty("quTwo")) ? NumberUtils.toInt(pro.getProperty("quTwo")) : quTwo;
	// quThree = StringUtils.isNotBlank(pro.getProperty("quThree")) ? NumberUtils.toInt(pro.getProperty("quThree")) : quThree;
	// ishaveexclude = StringUtils.isNotBlank(pro.getProperty("ishaveexclude")) ? NumberUtils.toInt(pro.getProperty("ishaveexclude")) : ishaveexclude;
	// includeRed = StringUtils.isNotBlank("includeRed") ? StringUtils.split(pro.getProperty("includeRed"), ",") : null;
	// selectedOneCode = StringUtils.isNotBlank("selectedOneCode") ? StringUtils.split(pro.getProperty("selectedOneCode"), "|") : null;
	// excludeRed = StringUtils.isNotBlank("excludeRed") ? StringUtils.split(pro.getProperty("excludeRed"), ",") : null;
	// musthavered = StringUtils.isNotBlank("musthavered") ? StringUtils.split(pro.getProperty("musthavered"), ",") : null;
	// cannotSelectedTogethor = StringUtils.isNotBlank("cannotSelectedTogethor") ? StringUtils.split(pro.getProperty("cannotSelectedTogethor"), "|")
	// : null;
	// selectCode = StringUtils.isNotBlank("selectCode") ? StringUtils.split(pro.getProperty("selectCode"), ",") : null;
	// preRedCode = StringUtils.isNotBlank("preRedCode") ? StringUtils.split(pro.getProperty("preRedCode"), ",") : null;
	// firstMinCode = StringUtils.isNotBlank(pro.getProperty("firstMinCode")) ? NumberUtils.toInt(pro.getProperty("firstMinCode")) : firstMinCode;
	// firstMaxCode = StringUtils.isNotBlank(pro.getProperty("firstMaxCode")) ? NumberUtils.toInt(pro.getProperty("firstMaxCode")) : firstMaxCode;
	// secondMinCode = StringUtils.isNotBlank(pro.getProperty("secondMinCode")) ? NumberUtils.toInt(pro.getProperty("secondMinCode")) : secondMinCode;
	// secondMaxCode = StringUtils.isNotBlank(pro.getProperty("secondMaxCode")) ? NumberUtils.toInt(pro.getProperty("secondMaxCode")) : secondMaxCode;
	// thirdMinCode = StringUtils.isNotBlank(pro.getProperty("thirdMinCode")) ? NumberUtils.toInt(pro.getProperty("thirdMinCode")) : thirdMinCode;
	// thirdMaxCode = StringUtils.isNotBlank(pro.getProperty("thirdMaxCode")) ? NumberUtils.toInt(pro.getProperty("thirdMaxCode")) : thirdMaxCode;
	// fourthMinCode = StringUtils.isNotBlank(pro.getProperty("fourthMinCode")) ? NumberUtils.toInt(pro.getProperty("fourthMinCode")) : fourthMinCode;
	// fourthMaxCode = StringUtils.isNotBlank(pro.getProperty("fourthMaxCode")) ? NumberUtils.toInt(pro.getProperty("fourthMaxCode")) : fourthMaxCode;
	// lastMinCode = StringUtils.isNotBlank(pro.getProperty("lastMinCode")) ? NumberUtils.toInt(pro.getProperty("lastMinCode")) : lastMinCode;
	// lastMaxCode = StringUtils.isNotBlank(pro.getProperty("lastMaxCode")) ? NumberUtils.toInt(pro.getProperty("lastMaxCode")) : lastMaxCode;
	// haveTwoSeries = StringUtils.isNotBlank(pro.getProperty("haveTwoSeries")) ? NumberUtils.toInt(pro.getProperty("haveTwoSeries")) : haveTwoSeries;
	// haveThreeSeries = StringUtils.isNotBlank(pro.getProperty("haveThreeSeries")) ? NumberUtils.toInt(pro.getProperty("haveThreeSeries"))
	// : haveThreeSeries;
	// haveOnediffer = StringUtils.isNotBlank(pro.getProperty("haveOnediffer")) ? NumberUtils.toInt(pro.getProperty("haveOnediffer")) : haveOnediffer;
	// haveSideCode = StringUtils.isNotBlank(pro.getProperty("haveSideCode")) ? NumberUtils.toInt(pro.getProperty("haveSideCode")) : haveSideCode;
	// includeRedNum = StringUtils.isNotBlank(pro.getProperty("includeRedNum")) ? NumberUtils.toInt(pro.getProperty("includeRedNum")) : includeRedNum;
	// if (haveSideCode == 1) {
	// String temp = "";
	// for (int i = 0; i < preRedCode.length; i++) {
	// int tmpInt = NumberUtils.toInt(preRedCode[i]);
	// if ("".equals(temp)) {
	// temp = tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0" + (tmpInt + 1)
	// : (tmpInt + 1)));
	// } else {
	// temp += ","
	// + (tmpInt == 1 ? "02" : (((tmpInt - 1) < 10 ? "0" + (tmpInt - 1) : (tmpInt - 1)) + "," + ((tmpInt + 1) < 10 ? "0"
	// + (tmpInt + 1) : (tmpInt + 1))));
	//
	// }
	// }
	// preSideCode = StringUtils.split(temp, ",");
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }

}
