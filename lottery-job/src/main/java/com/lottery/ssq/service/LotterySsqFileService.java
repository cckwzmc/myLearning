package com.lottery.ssq.service;

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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.lottery.ssq.config.LotterySsqConfig;
import com.lottery.ssq.dao.LotteryDao;
import com.lottery.ssq.utils.LotterySsqUtils;

/**
 * 通过其他方式收集的数据处理
 * 
 * @author Administrator
 */
public class LotterySsqFileService extends Thread {
	private static Logger logger = LoggerFactory.getLogger(LotterySsqFileService.class);

	private final String DEFAULTFILENAME = "lottery/ssq/excluderedfile.txt";
	private LotteryDao dao = null;
	LotterySsqThan20Service lotterySsqThan20Service = null;

	public static void setLogger(Logger logger) {
		LotterySsqFileService.logger = logger;
	}

	public void setLotterySsqThan20Service(LotterySsqThan20Service lotterySsqThan20Service) {
		this.lotterySsqThan20Service = lotterySsqThan20Service;
	}

	public void setDao(LotteryDao dao) {
		this.dao = dao;
	}

	public void run() {
		this.collectFileCode();
	}

	/**
	 * 从文件中读取号码，包括：红球、蓝球 一行一个号码 "lottery/ssq/excluderedfile.txt" fileList中的格式可能包含了蓝球分隔符为"+","|"
	 * 
	 * @param fileName
	 *            String[] String[0]=redCode,String[1]=blueCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String[]> getCodeFromFile(String fileName) {
		Set<String[]> list = new LinkedHashSet<String[]>();
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
			logger.error(fileName + "没有找到..............");
		} catch (UnsupportedEncodingException e) {
			logger.error(fileName + "编码有问题..............");
		} catch (IOException e) {
			logger.error(fileName + "+++++" + e.getMessage() + "..............");
		}
		if (filestr != null && !"".equals(filestr)) {
			filestr = fileStrReplace(filestr);
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

	/**
	 * 替换算法
	 * 
	 * @param filestr
	 * @return
	 */
	private String fileStrReplace(String filestr) {
		filestr = StringUtils.replace(filestr, " + ", "+");
		filestr = StringUtils.replace(filestr, " = ", "+");
		filestr = StringUtils.replace(filestr, "<br><br>", "\n");
		filestr = StringUtils.replace(filestr, "<br>", "\n");
		filestr = StringUtils.replace(filestr, " | ", "+");
		filestr = StringUtils.replace(filestr, "|", "+");
		filestr = StringUtils.replace(filestr, "=", "+");
		filestr = StringUtils.replace(filestr, " \n", "\n");
		filestr = StringUtils.replace(filestr, " \r\n", "\n");
		filestr = StringUtils.replace(filestr, "\n\r\n", "\n");
		filestr = StringUtils.replace(filestr, " \n\n", "\n");
		filestr = StringUtils.replace(filestr, "\t", ",");
		filestr = StringUtils.replace(filestr, ".", ",");
		filestr = StringUtils.replace(filestr, "、", ",");
		filestr = StringUtils.replace(filestr, "，", ",");
		filestr = StringUtils.replace(filestr, " ", ",");
		return filestr;
	}

	/**
	 * 使用默认文件名
	 * 
	 * @return
	 */
	public Set<String[]> getCodeFromFile() {
		Set<String[]> list = new HashSet<String[]>(50, 20);
		String filestr = "";
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources(this.DEFAULTFILENAME);
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
					filestr = new String(bs, "GB2312");
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
			filestr = fileStrReplace(filestr);
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

	/**
	 * 使用默认文件名
	 * 
	 * @return
	 */
	public Set<String> getRedCodeFromFile() {
		Set<String[]> list = this.getCodeFromFile();
		Set<String> redSet = new HashSet<String>();
		for (String[] code : list) {
			redSet.add(code[0]);
		}
		return redSet;
	}

	/**
	 * 解析红球并入库
	 * 
	 * @param fileName
	 */
	public void parseCurrentFileRedCodeToDb(String fileName) {
		Set<String[]> fileList = this.getCodeFromFile(fileName);
		List<String[]> resultList = new ArrayList<String[]>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotterySsqUtils.selectArray(6, code[0].split(","), resultList);
			if (resultList.size() > 2000) {
				this.dao.saveSsqLotteryCollectRedCod(resultList);
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
	}

	/**
	 * 不保存文件中的数据至数据库，直接返回分析结果
	 * 
	 * @param fileName
	 */
	public List<String> getCurrentFileRedCode() {
		Set<String[]> fileList = this.getCodeFromFile(this.DEFAULTFILENAME);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotterySsqUtils.select(6, code[0], resultList);
		}
		return resultList;
	}

	/**
	 * @param fileList
	 *            String[] String[0]=redCode,String[1]=blueCode
	 * @return
	 */
	public List<String> getRedCodeFromFile(Set<String[]> fileList) {
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotterySsqUtils.select(6, code[0], resultList);
		}
		return resultList;
	}

	public List<String> parseCurrentFileRedCode(String fileName) {
		Set<String[]> fileList = this.getCodeFromFile(fileName);
		return this.getRedCodeFromFile(fileList);
	}

	/**
	 * 以追加的方式写入文件
	 * 
	 * @param expect
	 * @param string
	 */
	public void parseCurrentFileRedCodeToFile(String filename, String expect) {
		Set<String[]> fileList = this.getCodeFromFile(filename);
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String[]> iterator = fileList.iterator(); iterator.hasNext();) {
			String[] code = (String[]) iterator.next();
			LotterySsqUtils.select(6, code[0], resultList);
			if (resultList.size() > 2000) {
				saveCurrentFileRedCode(resultList, expect);
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			saveCurrentFileRedCode(resultList, expect);
			resultList.clear();
		}
	}

	/**
	 * 保存解析过的红球写入文件
	 * 
	 * @param redFile
	 * @param qs
	 */
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

	/**
	 * 文件收集号码
	 */
	public void collectFileCode() {
		this.dao.clearHisFetchProjectCode(LotterySsqConfig.expect, "2");
		Set<String[]> fileCode = this.getCodeFromFile("lottery/ssq/excluderedfile.txt");
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		int i = 0;
		String sCode = "";
		if (CollectionUtils.isEmpty(fileCode)) {
			return;
		}
		int tmp = -20;
		for (Iterator<String[]> iterator = fileCode.iterator(); iterator.hasNext();) {
			String[] codes = (String[]) iterator.next();
			String code = StringUtils.join(codes, "+");
			i++;
			if ("".equals(sCode)) {
				sCode = code;
			} else {
				sCode += "@@" + code;
			}
			if (i > 10) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("code", sCode);
				map.put("net", "2");
				map.put("expect", LotterySsqConfig.expect);
				map.put("proid", tmp + "");
				resultList.add(map);
				i = 0;
				sCode = "";
				tmp++;
			}
			if (resultList.size() > 200) {
				this.dao.batchSaveSsqLotteryCollectFetch(resultList);
				resultList.clear();
			}
		}
		if (!"".equals(sCode)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("code", sCode);
			map.put("net", "2");
			map.put("expect", LotterySsqConfig.expect);
			map.put("proid", (tmp++) + "");
			resultList.add(map);
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.batchSaveSsqLotteryCollectFetch(resultList);
			resultList.clear();
		}
		logger.info("========" + "文件抓取完成..............................................");
	}

	/**
	 * 处理ssq_lottery_collect_fetch中的文件类型数据
	 */
	@SuppressWarnings("unchecked")
	public void saveFileProjectRedCode() {
		List<String[]> resultList = new ArrayList<String[]>();
		int last = 0;
		int page = 200;
		List list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "2");
		while (CollectionUtils.isNotEmpty(list)) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map map = (Map) iterator.next();
				String code = ObjectUtils.toString(map.get("code"));
				code = StringUtils.replace(code, "|", "+");
				String[] codes = StringUtils.split(code, "@@");
				for (String ssq : codes) {
					String redCode = StringUtils.split(ssq, "+")[0];
					String[] redCodes = StringUtils.split(redCode, ",");
					if (redCodes.length > 20) {
						lotterySsqThan20Service.select(6, redCodes, false);
						continue;
					}
					if (redCodes.length < 6 || redCodes.length > 20) {
						logger.error("方案解析失败==" + ssq);
						continue;
					}
					LotterySsqUtils.selectArray(6, redCodes, resultList);
				}
				if (resultList.size() > 2000) {
					this.dao.saveSsqLotteryCollectRedCod(resultList);
					resultList.clear();
				}
			}
			last += page;
			list = this.dao.getSsqLotteryCollectFetchLimit(last, page, "2");
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.saveSsqLotteryCollectRedCod(resultList);
			resultList.clear();
		}
	}

	public static void main(String[] args) {
		LotterySsqFileService f = new LotterySsqFileService();
		f.getCodeFromFile();
		f.clearFileContent();
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
	/**
	 * 备份历史，清空文件内容，防止下次误使用.
	 */
	public void clearFileContent() {
		try {
			ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
			Enumeration urls = null;
			try {
				urls = classLoader.getResources(this.DEFAULTFILENAME);
			} catch (IOException e) {
			}
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				String filePath = url.getPath().substring(1);
				File file = new File(filePath);
				if (file.exists()) {
					file.renameTo(new File(filePath + "." + LotterySsqConfig.expect));
					file.createNewFile();
				}
			}
		} catch (FileNotFoundException e) {
			logger.error(this.DEFAULTFILENAME + "没有找到..............");
		} catch (UnsupportedEncodingException e) {
			logger.error(this.DEFAULTFILENAME + "编码有问题..............");
		} catch (IOException e) {
			logger.error(this.DEFAULTFILENAME + "+++++" + e.getMessage() + "..............");
		}
	}

	/**
	 * 处理过期的收集文件
	 */
	public void clearCollectFile() {
		String expect = this.dao.getMaxLotteryFetchJob();
		if (StringUtils.isNotBlank(expect)) {
			this.clearFileContent();
		}
	}

	/**
	 * 删除文件收集中的红球号码，一般用于意外情况的使用。
	 */
	public void deleteFileRedCode() {
		Set<String> rSet = this.getRedCodeFromFile();
		List<String> resultList = new ArrayList<String>();
		for (Iterator<String> iterator = rSet.iterator(); iterator.hasNext();) {
			String redCode = (String) iterator.next();
			String[] redCodes = redCode.split(",");
			if (redCodes.length > 20) {
				this.lotterySsqThan20Service.select(6, redCodes, true);
				continue;
			}
			LotterySsqUtils.select(6, redCodes, resultList);
			if (resultList.size() > 5000) {
				this.dao.deleteSsqLotteryFilterResult(resultList);
				resultList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(resultList)) {
			this.dao.deleteSsqLotteryFilterResult(resultList);
			resultList.clear();
		}
	}
}
