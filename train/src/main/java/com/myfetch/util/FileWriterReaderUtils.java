package com.myfetch.util;

import java.io.*;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import com.myfetch.service.MyFetchService;

public class FileWriterReaderUtils {
	// 功能:读取f:/aillo.txt文件的内容(一行一行读),并将其内容写入f:/jackie.txt中
	// 知识点:java读文件、写文件---<以字符流方式>
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader("D:/aillo.txt");// 创建FileReader对象，用来读取字符流
			BufferedReader br = new BufferedReader(fr); // 缓冲指定文件的输入
			FileWriter fw = new FileWriter("D:/jackie.txt");// 创建FileWriter对象，用来写入字符流
			BufferedWriter bw = new BufferedWriter(fw); // 将缓冲对文件的输出
			String myreadline; // 定义一个String类型的变量,用来每次读取一行
			while (br.ready()) {
				myreadline = br.readLine();// 读取一行
				bw.write(myreadline); // 写入文件
				bw.newLine();
				System.out.println(myreadline);// 在屏幕上输出
			}
			bw.flush(); // 刷新该流的缓冲
			bw.close();
			br.close();
			fw.close();
			br.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeBodyToTxt(String filePath, String id, String bookid, String body) throws IOException {
		String filename = filePath + "/" + ("".equals(bookid) ? id : bookid) + "/" + id + ".txt";
		File path = new File(filePath + "/" + ("".equals(bookid) ? id : bookid));
		if (!path.exists()) {
			path.mkdirs();
		}
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
			FileWriter writer = new FileWriter(file);
			writer.write(body);
			writer.close();
		} else {
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(body);
			writer.close();
		}
	}
	@SuppressWarnings("unchecked")
	public static String imageDownload(Map map) throws IOException {
		String filename = ObjectUtils.toString(map.get("litpic"));
		if (!"".equals(filename)) {
			String dir = DateUtils.getDate();
			String fileName = StringUtils.substring(filename, filename.lastIndexOf("/") + 1);
			String savePath = MyFetchService.bootPath + "\\" + dir;
			File filepath = new File(savePath);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
			savePath = savePath + "\\" + fileName;
			filepath = new File(savePath);
			if (filepath.exists()) {
				filepath.delete();
			}
			int retDown = HttpResourceUtils.saveToFile(filename, savePath);
			if (retDown == 1) {
				map.put("litpic", "/uploads/" + dir + "/" + fileName);
			} else {
				map.put("litpic", "/uploads/noImage.gif");
			}
		}
		return filename;
	}

	public static String contentImgDownload(String imgUrl, String contentTxtPath, String bookid, String id) throws IOException {
		String filename = ObjectUtils.toString(imgUrl);
		if (!"".equals(filename)) {
//			String dir = DateUtils.getDate();
			String fileName = StringUtils.substring(filename, filename.lastIndexOf("/") + 1);
			String savePath = MyFetchService.contentTxtPath + "/" + bookid+"/"+"id"+"/";
			File filepath = new File(savePath);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
			savePath = savePath + "/" + fileName;
			filepath = new File(savePath);
			if (filepath.exists()) {
				filepath.delete();
			}
			int retDown = HttpResourceUtils.saveToFile(filename, savePath);
			return "/xs_txt/" +"/" + bookid+"/"+"id"+"/" + fileName;
		}
		return filename;
	}
}