package com.toney.core.cache;

/**
 * @author toney.li
 * 命名规则，_app全站级别的使用.<BR/>
 * 其他使用SqEL表达式处理。
 */
public class CacheConstants {
	public static final String CACHE_NAME="siteContext";
	
	//----全站上下文-----
	public static final String KEY_APPCONTEXT_CACHE="_app_context";
	//----全站上下文-----
	
	//----全站频道数据-----
	public static final String KEY_APPCHANNEL_CACHE="_app_channel";
	//----全站频道数据-------
	
	//----全站文章类型数据-----
	public static final String KEY_ARTTYPE_CACHE="_app_arttype";
	//----全站文章类型数据-----
	//----全站模版管理-----
	public static final String KEY_TPLMANAGER_CACHE="_app_tplmanager";
	//----全站模版管理-----
}
