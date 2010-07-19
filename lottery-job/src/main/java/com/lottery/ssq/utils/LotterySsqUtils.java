package com.lottery.ssq.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.lottery.ssq.LotteryConstant;

public class LotterySsqUtils {
	private static int count = 0;

	public static void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}

	/**
	 * @param k
	 * @param source
	 *            String[]
	 * @param resultList
	 */
	public static void select(int k, String[] source, List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k, source, resultList);
	}

	/**
	 * @param k
	 * @param source
	 * @param resultList
	 *            String[]
	 */
	public static void selectArray(int k, String[] source, List<String[]> resultList) {
		String[] result = new String[k];
		subselectArray(0, 1, result, k, source, resultList);
	}

	/**
	 * @param k
	 * @param source
	 * @param resultList
	 */
	public static void select(int k, String source, List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k, StringUtils.split(source, ","), resultList);
	}

	/**
	 * @param head
	 * @param index
	 * @param r
	 * @param k
	 * @param source
	 * @param resultList
	 *            String[]
	 */
	private static void subselectArray(int head, int index, String[] r, int k, String[] source,
			List<String[]> resultList) {
		for (int i = head; i < source.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = source[i];
				subselectArray(i + 1, index + 1, r, k, source, resultList);
			} else if (index == k) {
				r[index - 1] = source[i];
				String[] tmp = new String[r.length];
				for (int n = 0; n < r.length; n++) {
					tmp[n] = r[n];
				}
				resultList.add(tmp);
				subselectArray(i + 1, index + 1, r, k, source, resultList);
			} else {
				return;
			}
		}
	}

	private static void subselect(int head, int index, String[] r, int k, String[] source, List<String> resultList) {
		for (int i = head; i < source.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = source[i];
				subselect(i + 1, index + 1, r, k, source, resultList);
			} else if (index == k) {
				r[index - 1] = source[i];
				String redCode = StringUtils.join(r, ",");
				if (!resultList.contains(redCode)) {
					resultList.add(redCode);
				}
				subselect(i + 1, index + 1, r, k, source, resultList);
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
		String retContent = "";
		String line = "";
		while (line != null) {
			line = br.readLine();
			if (StringUtils.isNotBlank(line)) {
				retContent += line;
			}
		}
		rd.close();
		return retContent;
	}

	public static void selectDanArray(int k, String dan, String tuo, List<String[]> resultList) {
		String[] dans = dan.split(",");
		int sltLen = k - dans.length;
		selectArray(sltLen, tuo.split(","), resultList);
	}

	/*
	 * ~~~~~~~~~~~~~~~~~~~~~上面是选号处理~~~~~~~~~~~~~~~~~~~~
	 */

	/**
	 * 把号码合并为一组号码
	 * 
	 * @param cRedList
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	// public static String[] mergeRedCode(List cRedList) {
	// Set<String> set = new HashSet<String>();
	// for (Iterator iterator = cRedList.iterator(); iterator.hasNext();) {
	// Map map = (Map) iterator.next();
	// String redCode = ObjectUtils.toString(map.get("redcode"));
	// String[] redCodes = StringUtils.split(redCode, ",");
	// for (int i = 0; i < redCodes.length; i++) {
	// set.add(redCodes[i]);
	// }
	// }
	// String[] ret = set.toArray(new String[] {});
	// Arrays.sort(ret);
	// return ret;
	// }

	/**
	 * . 把号码合并为一组号码
	 * 
	 * @param redCodeList
	 * @return
	 */
	public static String[] mergeRedCode(List<String[]> redCodeList) {
		if (CollectionUtils.isEmpty(redCodeList)) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (String[] redCodes : redCodeList) {
			for (int i = 0; i < redCodes.length; i++) {
				set.add(redCodes[i]);
			}
		}
		String[] ret = set.toArray(new String[] {});
		Arrays.sort(ret);
		return ret;
	}

	/**
	 * . 把号码合并为一组号码
	 * 
	 * @param redCodeList
	 * @return
	 */
	public static String[] mergeRedCode(Set<String[]> redCodeList) {
		if (CollectionUtils.isEmpty(redCodeList)) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (String[] redCodes : redCodeList) {
			for (int i = 0; i < redCodes.length; i++) {
				set.add(redCodes[i]);
			}
		}
		String[] ret = set.toArray(new String[] {});
		Arrays.sort(ret);
		return ret;
	}

	public static void main(String[] args) {
		List<String[]> resultList = new ArrayList<String[]>();
		selectArray(6, new String[] { "01", "02", "04", "08", "11", "12", "33" }, resultList);
		for (String[] red : resultList) {
			System.out.println(StringUtils.join(red, ","));
		}
		System.out.println();
	}

	public static String[] mergeRedCode(List<String[]> tmpRedCodeList, String mergeCode) {
		Set<String> tmpSet = new HashSet<String>();
		if (StringUtils.isNotBlank(mergeCode)) {
			String[] mergeCodes = mergeCode.split(",");
			for (String tmpMergeCode : mergeCodes) {
				tmpSet.add(tmpMergeCode);
			}
		}
		String[] mergeList = LotterySsqUtils.mergeRedCode(tmpRedCodeList);
		for (String tmpMergeCode : mergeList) {
			tmpSet.add(tmpMergeCode);
		}
		String[] ret = tmpSet.toArray(new String[] {});
		Arrays.sort(ret);
		return ret;
	}

	public static String standardReplace(String code) {
		if (StringUtils.isBlank(code)) {
			return "";
		}
		code=code.trim();
		code = StringUtils.replace(code, "　", "");
		code = StringUtils.replace(code, " + ", "+");
		code = StringUtils.replace(code, " +", "+");
		code = StringUtils.replace(code, "+ ", "+");
		code = StringUtils.replace(code, "，", ",");
		code = StringUtils.replace(code, "、", ",");
		code = StringUtils.replace(code, ".", ",");
		code = StringUtils.replace(code, "。", " ");
		code = StringUtils.replace(code, "；", ",");
		code = StringUtils.replace(code, ", ", ",");
		code = StringUtils.replace(code, " ", ",");
		String[] codes = StringUtils.split(code, "+");
		String[] redcodes = StringUtils.split(codes[0], ",");
		for (int i = 0; i < redcodes.length; i++) {
			redcodes[i] = redcodes[i].trim();
			if (StringUtils.isBlank(redcodes[i])) {
				redcodes[i] = "";
			} else if (redcodes[i].length() < 2) {
				redcodes[i] = "0" + redcodes[i];
			} else if (redcodes[i].length() == 3) {
				redcodes[i] = "0" + StringUtils.substring(redcodes[i], 0, 1) + ","
						+ StringUtils.substring(redcodes[i], 1);
			} else if (redcodes[i].length() == 4) {
				redcodes[i] = StringUtils.substring(redcodes[i], 0, 2) + "," + StringUtils.substring(redcodes[i], 2);
			}
		}
		if(codes.length==2){
			codes[0]=StringUtils.join(redcodes,",");
			String[] tmpRedcodes = StringUtils.split(codes[1], ",");
			for (int i = 0; i < tmpRedcodes.length; i++) {
				tmpRedcodes[i] = tmpRedcodes[i].trim();
				if (StringUtils.isBlank(tmpRedcodes[i])) {
					tmpRedcodes[i] = "";
				} else if (tmpRedcodes[i].length() < 2) {
					tmpRedcodes[i] = "0" + tmpRedcodes[i];
				} else if (tmpRedcodes[i].length() == 3) {
					tmpRedcodes[i] = "0" + StringUtils.substring(tmpRedcodes[i], 0, 1) + ","
							+ StringUtils.substring(tmpRedcodes[i], 1);
				} else if (tmpRedcodes[i].length() == 4) {
					tmpRedcodes[i] = StringUtils.substring(tmpRedcodes[i], 0, 2) + "," + StringUtils.substring(tmpRedcodes[i], 2);
				}
			}
			codes[1]=StringUtils.join(tmpRedcodes,",");
		}else if(codes.length==1){
			codes[0]=StringUtils.join(redcodes,",");
		}
		return StringUtils.join(codes,"+");
	}

}
