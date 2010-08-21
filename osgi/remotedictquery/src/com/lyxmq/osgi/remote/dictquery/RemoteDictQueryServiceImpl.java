package com.lyxmq.osgi.remote.dictquery;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lyxmq.osgi.dictquery.query.QueryService;

public class RemoteDictQueryServiceImpl implements QueryService {
	private static final ConcurrentMap<String, String> dict = new ConcurrentHashMap<String, String>();
	static {
		dict.put("computer", "计算机");
		dict.put("osgi", "动态加载模块");
	}

	@Override
	public String queryWord(String word) {
		System.out.println(this.getClass().getName() + " queryWord called.......");
		String result = dict.get(word);
		if (result == null) {
			result = "N/A";
		}
		return result;
	}

}
