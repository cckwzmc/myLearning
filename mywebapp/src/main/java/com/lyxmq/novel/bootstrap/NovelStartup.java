package com.lyxmq.novel.bootstrap;

import com.lyxmq.novel.exception.BootstrapException;

/**
 * 数据的导入，初始化等
 * 此类暂时不使用
 * @author LIYI
 *
 */
public class NovelStartup {

	private static boolean prepare=false;
	public static boolean isPrepared() {
		return prepare;
	}
	public static void prepare() throws BootstrapException {
		prepare=true;
	}
}
