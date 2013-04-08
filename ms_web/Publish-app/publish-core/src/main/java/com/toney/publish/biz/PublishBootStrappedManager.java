package com.toney.publish.biz;

import com.toney.publish.exception.PublishBusinessException;


/**
 * @author toney.li
 * 服务启动时，加载初始化数据.
 */
public interface PublishBootStrappedManager {
	/**
	 * @throws PublishBusinessException
	 * 初始化全站数据。
	 */
	public void initApplicationData() throws PublishBusinessException;
}
