package com.lottery.football.service;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置初始化
 * 
 * @author Administrator
 * 
 */
public class LotteryFootballConifgService {
	private static String footballExpect;
	private static String dyjFootballUrl = "";
	private static String dyjFootballDowload = "";
	private static String www500wanFootballUrl = "";
	private static String www500wanFootballDowload = "";
	private static String www500wanFootballMedia = "";

	static {
		try {
			Properties pro = PropertiesLoaderUtils.loadAllProperties("lottery/football/ft_lottery.properties");
			footballExpect = StringUtils.isNotBlank(pro.getProperty("footballExpect")) ? pro.getProperty("footballExpect") : footballExpect;

			www500wanFootballMedia = StringUtils.isNotBlank(pro.getProperty("www500wanFootballMedia")) ? pro.getProperty("www500wanFootballMedia") : www500wanFootballMedia;
			www500wanFootballMedia = StringUtils.replace(www500wanFootballMedia, "@footballExpect@", footballExpect);

			dyjFootballUrl = StringUtils.isNotBlank(pro.getProperty("dyjFootballUrl")) ? pro.getProperty("dyjFootballUrl") : dyjFootballUrl;
			dyjFootballUrl = StringUtils.replace(dyjFootballUrl, "@footballExpect@", footballExpect);
			dyjFootballDowload = StringUtils.isNotBlank(pro.getProperty("dyjFootballDowload")) ? pro.getProperty("dyjFootballDowload") : dyjFootballDowload;
			dyjFootballDowload = StringUtils.replace(dyjFootballDowload, "@footballExpect@", footballExpect);

			www500wanFootballUrl = StringUtils.isNotBlank(pro.getProperty("www500wanFootballUrl")) ? pro.getProperty("www500wanFootballUrl") : www500wanFootballUrl;
			www500wanFootballUrl = StringUtils.replace(www500wanFootballUrl, "@footballExpect@", footballExpect);
			www500wanFootballDowload = StringUtils.isNotBlank(pro.getProperty("www500wanFootballDowload")) ? pro.getProperty("www500wanFootballDowload") : www500wanFootballDowload;
			www500wanFootballDowload = StringUtils.replace(www500wanFootballDowload, "@footballExpect@", footballExpect);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getWww500wanFootballMedia() {
		return www500wanFootballMedia;
	}

	public static String getFootballExpect() {
		return footballExpect;
	}

	public static String getDyjFootballUrl() {
		return dyjFootballUrl;
	}

	public static String getDyjFootballDowload() {
		return dyjFootballDowload;
	}

	public static String getWww500wanFootballUrl() {
		return www500wanFootballUrl;
	}

	public static String getWww500wanFootballDowload() {
		return www500wanFootballDowload;
	}

}
