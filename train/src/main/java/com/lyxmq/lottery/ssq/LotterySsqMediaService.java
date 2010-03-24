package com.lyxmq.lottery.ssq;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * 媒体推荐的处理
 * 
 * @author Administrator
 * 
 */
public class LotterySsqMediaService {
	
	/**
	 * 解析红球
	 * @param document
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getMediaRedCode(Document document) {
		List<String[]> list = new ArrayList<String[]>();
		List media = document.selectNodes("//xml/media");
		for (Iterator iterator = media.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List redNode = node.selectNodes("tjcode/redcode/code");
			String[] redCodeArr = null;
			if (CollectionUtils.isNotEmpty(redNode)) {
				redCodeArr = new String[redNode.size()];
			}
			for (int i = 0; i < redNode.size(); i++) {
				Node red = (Node) redNode.get(i);
				redCodeArr[i] = red.getText();
			}
			list.add(redCodeArr);
		}
		return list;
	}
	/**
	 * 解析蓝球
	 * @param document
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getMediaBlueCode(Document document){
		List media = document.selectNodes("//xml/media");
		List<String[]> list=new ArrayList<String[]>();
		for (Iterator iterator = media.iterator(); iterator.hasNext();) {
			Node node = (Node) iterator.next();
			List blueNode = node.selectNodes("tjcode/bluecode/code");
			String[] blueCodeArr = null;
			if (CollectionUtils.isNotEmpty(blueNode)) {
				blueCodeArr = new String[blueNode.size()];
			}
			for (int i = 0; i < blueNode.size(); i++) {
				Node blue = (Node) blueNode.get(i);
				blueCodeArr[i] = blue.getText();
			}
			list.add(blueCodeArr);
		}
		return list;
	}
	/**
	 * 历史开奖红球
	 * @param document
	 * @return
	 */
	public String[] getHistoryOpenRedcode(Document document){
		Node kjjg = document.selectSingleNode("//xml/head/kjflag");
		String kjflag = ((Element) kjjg).getText();
		String[] openRedCode=new String[6];
		if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {
			List redCode = document.selectNodes("//xml/head/opencode/redcode");
			for (int i=0;i<redCode.size();i++) {
				Node node = (Node) redCode.get(i);
				openRedCode[i]=node.getText();
			}
		}
		return openRedCode;
	}
	
	/**
	 * 历史蓝球开奖
	 * @param document
	 * @return
	 */
	public String getHistoryOpenBlueCode(Document document){
		Node kjjg = document.selectSingleNode("//xml/head/kjflag");
		String kjflag = ((Element) kjjg).getText();
		String openBlueCode="";
		if (StringUtils.isNotBlank(kjflag) && "1".equals(kjflag)) {
			Node blueCode = document.selectSingleNode("//xml/head/opencode/bluecode");
				openBlueCode=blueCode.getText();
		}
		return openBlueCode;
	}
	
	/**
	 * 按单注方式生成红球号码
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(Document document){
		List<String[]> redCode=this.getMediaRedCode(document);
		List<String> resultList=new ArrayList<String>();
		for (Iterator<String[]> iterator = redCode.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}
	/**
	 * 按单注方式生成红球号码
	 * @param document
	 */
	public List<String> parseCurrentMediaRedCode(List<String[]> redList){
		List<String> resultList=new ArrayList<String>();
		for (Iterator<String[]> iterator = redList.iterator(); iterator.hasNext();) {
			String[] red = (String[]) iterator.next();
			LotteryUtils.select(6, red, resultList);
		}
		return resultList;
	}
	/**
	 * @param redMedia
	 * @param qh
	 */
	public void saveCurrentMediaRedCode(List<String> redMedia,String qh) {
		if (CollectionUtils.isNotEmpty(redMedia)) {
			try {
				File file = new File("d:/myproject/ssq_media_red_" + qh + ".xml");
				if (!file.exists()) {
					file.createNewFile();
				} else {
				}
				FileWriter writer = new FileWriter(file);
				StringBuffer filePrint = new StringBuffer();
				int temp = 0;
				for (Iterator<String> iterator = redMedia.iterator(); iterator.hasNext();) {
					temp++;
					String redCode = (String) iterator.next();
					if (temp < redMedia.size()) {
						filePrint.append(redCode+ "\n");
					} else {
						filePrint.append(redCode);
					}
				}
				writer.write(filePrint.toString());
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
