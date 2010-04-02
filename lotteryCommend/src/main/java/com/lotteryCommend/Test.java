package com.lotteryCommend;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		String s1="09";
		String s2="0912";
		String s3="091212";
		String s4="08";
		String s5="0812";
		int i1=0;
		String[] ss={"09","08","091212","0812","0912"};
		Arrays.sort(ss);
		System.out.println(ss);
	}
}
