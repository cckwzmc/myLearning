package com.lyxmq.lottery.ssq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class LotteryUtils {
	private static int count = 0;

	public static void select(int k) {
		String[] result = new String[k];
		subselect(0, 1, result, k);

	}

	private static void subselect(int head, int index, String[] r, int k) {
		for (int i = head; i < LotteryConstant.redBall.length + index - k; i++) {
			if (index < k) {
				r[index - 1] = LotteryConstant.redBall[i];
				subselect(i + 1, index + 1, r, k);
				count++;
			} else if (index == k) {
				r[index - 1] = LotteryConstant.redBall[i];
				// System.out.print(i + "===");
				// System.out.println(StringUtils.join(r, ","));
				LotteryConstant.redResultList.add(StringUtils.join(r, ","));
				subselect(i + 1, index + 1, r, k);
				count++;
			} else {
				return;
			}

		}
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> disposeXmlStatData(String xmlData) {
		try {
			Document document = DocumentHelper.parseText(xmlData);
			List redCode = document.selectNodes("//xml/red/code");
			Map<String, String> redMap = new HashMap<String, String>();
			for (int i = 0; i < redCode.size(); i++) {
				Node redRs = (Node) redCode.get(i);
				String code = redRs.getText();
				String num = ((Element) redRs).attributeValue("num");
				String fen = ((Element) redRs).attributeValue("fen");
				redMap.put(code, num + "|" + fen);
			}
			return redMap;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 去掉3连号 去掉一个区4个号
	 * 
	 * 最多两个连号 最多3个 两个号码的差都等于同一个数 最多2个 两个号码的差都小于等于2 最多3个 两个号码的差都小于等于3
	 * 
	 * @param r
	 * @return
	 */
	public static boolean isValidateData(String[] r) {
		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;
		if (r.length == 6) {
			one = NumberUtils.toInt(r[0]);
			two = NumberUtils.toInt(r[1]);
			three = NumberUtils.toInt(r[2]);
			four = NumberUtils.toInt(r[3]);
			five = NumberUtils.toInt(r[4]);
			six = NumberUtils.toInt(r[5]);
			if (two - one == 1 && three - two == 1) {
				return false;
			}
			if (two - one == 1 && three - two == 1) {
				return false;
			}
			if (three - two == 1 && four - three == 1) {
				return false;
			}
			if (four - three == 1 && five - four == 1) {
				return false;
			}
			if (five - four == 1 && six - five == 1) {
				return false;
			}
			int qCount = 0;
			for (int i = 0; i < r.length; i++) {
				int rValue = NumberUtils.toInt(r[i]);
				if (rValue <= 11) {
					qCount++;
				}
			}
			if (qCount >= 4) {
				return false;
			}
			qCount = 0;
			for (int i = 0; i < r.length; i++) {
				int rValue = NumberUtils.toInt(r[i]);
				if (rValue > 11 && rValue <= 22) {
					qCount++;
				}
			}
			if (qCount >= 4) {
				return false;
			}
			qCount = 0;
			for (int i = 0; i < r.length; i++) {
				int rValue = NumberUtils.toInt(r[i]);
				if (rValue > 22 && rValue <= 33) {
					qCount++;
				}
			}
			if (qCount >= 4) {
				return false;
			}
			qCount = 0;
			for (int i = 0; i < r.length; i++) {
				int rValue = NumberUtils.toInt(r[i]);
				int nextValue = (i + 1) < r.length ? NumberUtils.toInt(r[i + 1]) : -1;
				if (nextValue != -1 && nextValue - rValue == 1) {
					qCount++;
				}
			}
			if (qCount > 2) {
				return false;
			}
			qCount = 0;
			Integer[] cValue = new Integer[5];
			for (int i = 0; i < r.length; i++) {
				int rValue = NumberUtils.toInt(r[i]);
				int nextValue = (i + 1) < r.length ? NumberUtils.toInt(r[i + 1]) : -1;
				if (nextValue != -1) {
					cValue[i] = nextValue - rValue;
				}
			}
			for (int i = 0; i < cValue.length; i++) {
				for (int j = i + 1; j < cValue.length; j++) {
					if (cValue[i] == cValue[j] & cValue[i].intValue() == 2) {
						qCount++;
					}
				}
			}
			if (qCount > 2) {
				return false;
			}
			for (int i = 0; i < cValue.length; i++) {
				for (int j = i + 1; j < cValue.length; j++) {
					if (cValue[i] == cValue[j] & cValue[i].intValue() == 3) {
						qCount++;
					}
				}
			}
			if (qCount > 3) {
				return false;
			}
			for (int i = 0; i < cValue.length; i++) {
				for (int j = i + 1; j < cValue.length; j++) {
					if (cValue[i] == cValue[j] & cValue[i].intValue() > 3) {
						qCount++;
					}
				}
			}
			if (qCount > 4) {
				return false;
			}
			int qOne = 0;
			int qTwo = 0;
			int qThree = 0;
			for (int i = 0; i < r.length; i++) {
				if (NumberUtils.toInt(r[i]) <= 11) {
					qOne++;
				}
				if (NumberUtils.toInt(r[i]) > 11 && NumberUtils.toInt(r[i]) <= 22) {
					qTwo++;
				}
				if (NumberUtils.toInt(r[i]) > 22 && NumberUtils.toInt(r[i]) <= 33) {
					qThree++;
				}
			}
			if (qOne == 0 || qTwo == 0 || qThree == 0) {
				return false;
			}
		}
		return true;
	}
}
