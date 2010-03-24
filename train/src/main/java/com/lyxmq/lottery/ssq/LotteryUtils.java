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
	public static void select(int k,String[] source,List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k,source,resultList);
	}
	public static void select(int k,String source,List<String> resultList) {
		String[] result = new String[k];
		subselect(0, 1, result, k,StringUtils.split(source, ","),resultList);
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

}
