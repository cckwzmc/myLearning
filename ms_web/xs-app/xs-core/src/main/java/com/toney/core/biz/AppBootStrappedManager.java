package com.toney.core.biz;

import com.toney.core.exception.BusinessException;

/**
 * @author toney.li
 * 服务启动时，加载初始化数据.
 */
public interface AppBootStrappedManager {
	/**
	 * @throws BusinessException
	 * 初始化全站数据。
	 */
	public void initApplicationData() throws BusinessException;
}
