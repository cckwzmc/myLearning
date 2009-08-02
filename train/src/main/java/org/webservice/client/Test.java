package org.webservice.client;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.myfetch.service.http.HttpHtmlService;

public class Test {

	public final static boolean DEBUG = true;// 调试用
	private static int BUFFER_SIZE = 8096;// 缓冲区大小
	private Vector vDownLoad = new Vector();// URL列表
	private Vector vFileList = new Vector();// 下载后的保存文件名列表

	public static void main(String[] args) {
		testRemotePublisher();
	}
	
	public static void testRemotePublisher(){
		String url="http://www.txt.com/dede/login_publisher.php";
		String html=HttpHtmlService.getHtmlContent(url);
		System.out.println(html);
		url="http://www.rentimm.com/include/vdimgck.php";
		html=HttpHtmlService.getHtmlContent(url);
		System.out.println(html);
	}
	
	public static void downRemoteFile(){
		System.out.println("http://ddd/dd/ss/ddd".substring(0,
				"http://ddd/dd/ss/ddd".lastIndexOf("/")));
		Calendar calendar=Calendar.getInstance();
		String year=calendar.get(Calendar.YEAR)+""+(calendar.get(Calendar.MONTH)+1)+""+calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(year);
		System.out.println((new Date()).getTime());
		try {
			saveToFile("http://media.twango.com/m1/original/0111/ce3ba22c5a4443d3ad75ee26c1af45ca.jpg", "D:\\334s.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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

			System.out.println("正在获取链接[" + destUrl + "]的内容...\n将其保存为文件["
					+ fileName + "]");
		// 保存文件
		while ((size = bis.read(buf)) != -1)

			fos.write(buf, 0, size);
		fos.close();
		bis.close();
		httpUrl.disconnect();
	}
}
// 1247071503
// 1247893600354