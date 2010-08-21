package com.lyxmq.osgi.dictquery.query;

public interface QueryService {
	/**
	 * 根据输入的word查找单词
	 * 
	 * @param word
	 *            查询单词
	 * @return 返回结果
	 */
	public String queryWord(String word);
}
