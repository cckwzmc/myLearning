package com.lottery.football.service;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.lottery.football.dao.LotteryFootballDao;
import com.lottery.football.util.FootballLotteryUtils;

public class LotteryFootballFileService {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(LotteryFootballFileService.class);

	private LotteryFootballDao footballLotteryDao = null;

	public void setFootballLotteryDao(LotteryFootballDao footballLotteryDao) {
		this.footballLotteryDao = footballLotteryDao;
	}

	/**
	 * 从文件中读取个人收集的号码 有可能是单/复式
	 * 
	 * @param fileName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<String[][]> footballCodeFromFile(String fileName) {
		List<String[][]> list = new ArrayList<String[][]>();
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
			String[] fbCodes = StringUtils.split(StringUtils.remove(filestr, '\r'), '\n');
			for (int i = 0; i < fbCodes.length; i++) {
				String value = fbCodes[i];
				if (StringUtils.indexOf(value, "|") != -1) {
					String[] tmp = StringUtils.split(value, "|");
					list.add(this.convertArray(tmp[1], "("));
				}else if(value.indexOf("(")!=-1){
					list.add(this.convertArray(value, "("));
				} else if (value.indexOf("-") != -1) {
					list.add(this.convertArray(value, "-"));
				} else if (value.length() == 14) {
					list.add(convertArray(value, "14"));
				}
			}
		}
		return list;
	}

	private String[][] convertArray(String value, String split) {
		String[] tmp = new String[14];
		if ("(".equals(split)) {
			List<String> code = new ArrayList<String>();
			while (StringUtils.isNotBlank(value)) {
				int leftIndex = value.indexOf("(");
				String subTmp = "";
				if (leftIndex != -1) {
					subTmp = value.substring(0, leftIndex);
					value = value.substring(leftIndex + 1);
					if (subTmp.length() > 0) {
						for (int i = 0; i < subTmp.length(); i++) {
							code.add(subTmp.substring(i, i + 1));
						}
					}
				}
				int rightIndex = value.indexOf(")");
				try {
					if (leftIndex != -1) {
						code.add(value.substring(0, rightIndex));
						value = value.substring(rightIndex + 1);
					}
				} catch (StringIndexOutOfBoundsException e) {
					value = "";
				}
				if (value.indexOf("(") == -1 && value.indexOf(")") == -1) {
					for (int i = 0; i < value.length(); i++) {
						code.add(value.substring(i, i + 1));
					}
					value = "";
				}
			}
			for (int i = 0; i < code.size(); i++) {
				String ft = code.get(i);
				tmp[i] = ft;
			}
		}
		if (value.indexOf("-") != -1) {
			tmp = value.split("-");
		}
		if (value.indexOf(",") != -1) {
			tmp = value.split(",");
		}
		if ("14".equals(split)) {
			List<String> code = new ArrayList<String>();
			for (int i = 0; i < value.length(); i++) {
				code.add(value.substring(i, i + 1));
			}
			for (int i = 0; i < code.size(); i++) {
				String ft = code.get(i);
				tmp[i] = ft;
			}
		}
		String[][] ftCode = new String[14][];
		for (int i = 0; i < tmp.length; i++) {
			String tt = "";
			for (int j = 0; j < tmp[i].length(); j++) {
				if ("".equals(tt)) {
					tt = tmp[i].substring(j, j + 1);
				} else {
					tt += "," + tmp[i].substring(j, j + 1);
				}
			}
			ftCode[i] = tt.split(",");
		}
		return ftCode;
	}

	public void saveFootballFileCode() {
		List<String[][]> list = this.footballCodeFromFile("lottery/football/excludered_ft_file.txt");
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		List<String> fbList = new ArrayList<String>();
		for (String[][] ftCodes : list) {
			List<String> tmpList = FootballLotteryUtils.doCallAllCode(ftCodes);
			if(CollectionUtils.isNotEmpty(tmpList))
			{
				fbList.addAll(tmpList);
			}else{
				logger.info(ftCodes.toString());
			}
			if (fbList.size() > 2000) {
				this.footballLotteryDao.batchSaveFootballCollectionResult(fbList);
				fbList.clear();
			}
		}
		if (CollectionUtils.isNotEmpty(fbList)) {
			this.footballLotteryDao.batchSaveFootballCollectionResult(fbList);
			fbList.clear();
		}
	}
}
