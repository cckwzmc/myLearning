package com.lottery.ssq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.LotteryConstant;

public class LotteryUtils {
	private static int count = 0;

	public static void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}
	/**
	 * @param k
	 * @param source String[]
	 * @param resultList
	 */
	public static void select(int k,String[] source,List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k,source,resultList);
	}
	/**
	 * @param k
	 * @param source
	 * @param resultList String[]
	 */
	public static void selectArray(int k,String[] source,List<String[]> resultList) {
		String[] result = new String[k];
		subselectArray(0, 1, result, k,source,resultList);
	}
	/**
	 * @param k
	 * @param source
	 * @param resultList
	 */
	public static void select(int k,String source,List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k,StringUtils.split(source, ","),resultList);
	}

	/**
	 * @param head
	 * @param index
	 * @param r
	 * @param k
	 * @param source
	 * @param resultList String[]
	 */
	private static void subselectArray(int head, int index, String[] r, int k,String[] source,List<String[]> resultList) {
		for (int i = head; i < source.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = source[i];
				subselectArray(i + 1, index + 1, r, k,source,resultList);
			} else if (index == k) {
				r[index - 1] = source[i];
//				String redCode=StringUtils.join(r, ",");
//				if(!resultList.contains(r))
//				{
					resultList.add(r);
//				}
				subselectArray(i + 1, index + 1, r, k,source,resultList);
			} else {
				return;
			}
			
		}
	}
	private static void subselect(int head, int index, String[] r, int k,String[] source,List<String> resultList) {
		for (int i = head; i < source.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = source[i];
				subselect(i + 1, index + 1, r, k,source,resultList);
			} else if (index == k) {
				r[index - 1] = source[i];
				String redCode=StringUtils.join(r, ",");
				if(!resultList.contains(redCode))
				{
					resultList.add(redCode);
				}
				subselect(i + 1, index + 1, r, k,source,resultList);
			} else {
				return;
			}
			
		}
	}
	private static void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < LotteryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LotteryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
				count++;
			} else if (index == k) {
				r[index - 1] = LotteryConstant.redBall[i];
				LotteryConstant.redResultList.add(StringUtils.join(r, ","));
				subselect(i + 1, index + 1, r, k);
				count++;
			} else {
				return;
			}

		}
	}
	public static String getFileContent(File file) throws IOException {
		FileReader rd = new FileReader(file);
		BufferedReader br = new BufferedReader(rd);
		String retContent="";
		String line = "";
		while (line != null) {
			line = br.readLine();
			if(StringUtils.isNotBlank(line))
			{
				retContent+=line;
			}
		}
		rd.close();
		return retContent;
	}
	public static void selectDanArray(int k, String dan, String tuo, List<String[]> resultList) {
		String[] dans=dan.split(",");
		int sltLen=k-dans.length;
		selectArray(sltLen, tuo.split(","), resultList);
	}
}
