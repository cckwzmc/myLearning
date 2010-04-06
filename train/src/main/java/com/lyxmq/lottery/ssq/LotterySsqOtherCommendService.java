package com.lyxmq.lottery.ssq;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ClassUtils;

import com.lyxmq.lottery.ssq.utils.LotteryUtils;

/**
 * 通过其他方式收集的数据处理
 * 
 * @author Administrator
 * 
 */
public class LotterySsqOtherCommendService {

	private LotteryDao dao = null;

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	/**
	 * 从文件中读取号码，包括：红球、蓝球 一行一个号码 "lottery/ssq/excluderedfile.txt"
	 * 
	 * fileList中的格式可能包含了蓝球分隔符为"+","|"
	 * 
	 * @param fileName
	 *            String[] String[0]=redCode,String[1]=blueCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getCodeFromFile(String fileName) {
		List<String[]> list = new ArrayList<String[]>();
		String filestr = "";
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources(fileName);
			} catch (IOException e) {
			}
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				InputStream is = null;
				try {
					URLConnection con = url.openConnection();
					is = con.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);
					byte[] bs = new byte[bis.available()];
					bis.read(bs);
					filestr = new String(bs, "GBK");
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
		if (filestr != null && !"".equals(filestr)) {
			String[] redCodes = StringUtils.split(StringUtils.remove(filestr, '\r'), '\n');
			for (int i = 0; i < redCodes.length; i++) {
				String code = redCodes[i];
				String[] codes = new String[2];
				code = StringUtils.replace(StringUtils.replace(code, " ", ""), "|", "+");
				if (StringUtils.indexOf(code, "+") != -1) {
					codes = StringUtils.split(code, "+");
				} else {
					codes[0] = code;
					codes[1] = null;
				}
				list.add(codes);
			}
		}
		return list;
	}

	public void parseCurrentFileRedCodeToDb(String fileName) {
		List<String[]> fileList = this.getCodeFromFile(fileName);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotteryUtils.select(6, code[0], resultList);
			if (resultList.size() > 2000) {
				this.dao.saveSsqLotteryCollectRedCod(resultList);
				resultList.clear();
			}
		}
		if(CollectionUtils.isNotEmpty(resultList)){
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
	}

	/**
	 * 
	 * @param fileList
	 *            String[] String[0]=redCode,String[1]=blueCode
	 * @return
	 */
	public List<String> getRedCodeFromFile(List<String[]> fileList) {
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotteryUtils.select(6, code[0], resultList);
		}
		return resultList;
	}

	public List<String> parseCurrentFileRedCode(String fileName) {
		List<String[]> fileList = this.getCodeFromFile(fileName);
		return this.getRedCodeFromFile(fileList);
	}

	/**
	 * 以追加的方式写入文件
	 * 
	 * @param expect
	 * @param string
	 */
	public void parseCurrentFileRedCodeToFile(String filename, String expect) {
		List<String[]> fileList = this.getCodeFromFile(filename);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotteryUtils.select(6, code[0], resultList);
			if (resultList.size() > 2000) {
				saveCurrentFileRedCode(resultList, expect);
				resultList.clear();
			}
		}
		if(CollectionUtils.isNotEmpty(resultList)){
			saveCurrentFileRedCode(resultList, expect);
			resultList.clear();
		}
	}

	public void saveCurrentFileRedCode(List<String> redFile, String qs) {
		if (CollectionUtils.isNotEmpty(redFile)) {
			try {
				File file = new File("d:/myproject/ssq_file_red_" + qs + ".xml");
				if (!file.exists()) {
					file.createNewFile();
				} else {
				}
				FileWriter writer = new FileWriter(file, true);
				int temp = 0;
				for (Iterator<String> iterator = redFile.iterator(); iterator.hasNext();) {
					temp++;
					String redCode = (String) iterator.next();
					if (temp < redFile.size()) {
						writer.write(redCode + "\n");
					} else {
						writer.write(redCode);
					}
				}
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// public static void main(String[] args) throws IOException {
	// List<String> list=new ArrayList<String>();
	// list.add("1");
	// list.add("2");
	// list.add("3");
	// list.add("4");
	// list.add("5");
	// list.add("6");
	// list.add("7");
	// File file = new File("d:/myproject/ssq_file_red_.xml");
	// if (!file.exists()) {
	// file.createNewFile();
	// } else {
	// }
	// FileWriter writer = new FileWriter(file,true);
	// int temp = 0;
	// for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
	// temp++;
	// String redCode = (String) iterator.next();
	// if (temp < list.size()) {
	// writer.write(redCode + "\n");
	// } else {
	// writer.write(redCode);
	// }
	// }
	// writer.close();
	// }

}
