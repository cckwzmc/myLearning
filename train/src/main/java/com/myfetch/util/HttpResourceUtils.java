package com.myfetch.util;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.slf4j.LoggerFactory;

import com.myfetch.service.MyFetchService;

public class HttpResourceUtils {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpResourceUtils.class);

	/**
	 * 将HTTP资源另存为文件
	 * 
	 * @param destUrl
	 *            String
	 * @param fileName
	 *            String
	 * @throws Exception
	 */
	public static void saveToFile(String destUrl, String fileName) throws IOException {
		final boolean DEBUG = true;// 调试用
		int BUFFER_SIZE = 8096;// 缓冲区大小
		Vector vDownLoad = new Vector();// URL列表
		Vector vFileList = new Vector();// 下载后的保存文件名列表
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		// 建立链接
		url = new URL(destUrl);
		httpUrl = (HttpURLConnection) url.openConnection();
		// 连接指定的资源
		httpUrl.connect();
		// 获取网络输入流
		bis = new BufferedInputStream(httpUrl.getInputStream());
		// 建立文件
		fos = new FileOutputStream(fileName);
		if (DEBUG)
		{
			logger.info("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件[" + fileName + "]");
		}	
		// 保存文件
		while ((size = bis.read(buf)) != -1)
		{
			fos.write(buf, 0, size);
		}	
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
}
