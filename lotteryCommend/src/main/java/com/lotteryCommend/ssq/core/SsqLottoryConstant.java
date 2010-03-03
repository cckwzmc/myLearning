package com.lotteryCommend.ssq.core;


public class SsqLottoryConstant {
	public static String[] redBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33" };
	public static String[] blueBall = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16" };
	//三区分布<=11 1区
	public static int threeFirstZone = 11;
	//三区分布11> <=22 2区
	public static int threeSecondZone = 22;
	//三区分布22> <=33 2区
	public static int threeThirdZone = 33;
}
