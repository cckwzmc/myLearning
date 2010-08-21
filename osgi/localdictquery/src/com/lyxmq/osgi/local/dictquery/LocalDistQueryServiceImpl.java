package com.lyxmq.osgi.local.dictquery;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lyxmq.osgi.dictquery.query.QueryService;

public class LocalDistQueryServiceImpl implements QueryService {

	private static final ConcurrentMap<String, String> dict = new ConcurrentHashMap<String, String>();
	static {
		dict.put("test", "中国");
		dict.put("China", "大中国");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lyxmq.osgi.dictquery.query.QueryService#queryWord(java.lang.String)
	 */
	@Override
	public String queryWord(String word) {
		System.out.println(getClass().getName() + " queryWord called.........");
		String result = dict.get(word);
		if (result == null) {
			return "N/A";
		}
		return result;
	}

}
