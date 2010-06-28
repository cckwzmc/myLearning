package com.lottery.ssq.config;

public class LotterySsqFilterConfig extends LotterySsqConfig {
	public static int is_reFilter = 0;
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
	// 最多选择其中的一个
	public static String[] zuiduoSelectedOneCode = new String[] {};
	// 至少选其中的一个
	public static String[] leastSelectedOneCode = new String[] {};
	// 必须选择其中的一个
	public static String[] mustSelectedOneCode = new String[] {};
	// 必须选其中的两个
	public static String[] mustSelectedTwoCode = new String[] {};
	// 至少选其中的两个
	public static String[] leastSelectedTwoCode = new String[] {};
	// 至少选中其中的三个
	public static String[] leastSelectedThreeCode = new String[] {};
	// 三个尾数相同的号码
	public static int mantissaThreeSame;
	// 三个两倍数的号码
	public static int haveThree2Multiple;
	public static int haveThree3Multiple;
	// 连续4个奇数或偶数
	public static int continueFourOddOreven;
	// 5个以上的奇数或偶数
	public static int geFiveOddOrEven;

}
