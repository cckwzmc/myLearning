package com.lottery.ssq.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class LotteryServiceUtils {

	/**
	 * 把号码合并为一组号码
	 * @param cRedList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String[] mergeRedCode(List cRedList) {
		Set<String> set=new HashSet<String>();
		for (Iterator iterator = cRedList.iterator(); iterator.hasNext();) {
			Map map = (Map) iterator.next();
			String redCode = ObjectUtils.toString(map.get("redcode"));
			String[] redCodes = StringUtils.split(redCode, ",");
			for (int i = 0; i < redCodes.length; i++) {
				set.add(redCodes[i]);	
			}
		}
		String[] ret=set.toArray(new String[]{});
		Arrays.sort(ret);
		return ret;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List list=new ArrayList();
		for (int i = 0; i < 10; i++) {
			Map map=new HashMap();
			map.put("redcode", "12,12,01,03,05,07");
			list.add(map);
		}
		System.out.println(StringUtils.join(mergeRedCode(list), ","));
	}
}
