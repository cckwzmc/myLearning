package org.webservice.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.iris.scholar.server.ws.IScholarFetchServicePortType;

public class HelloWorldClient extends Thread {
	private static final Log log=LogFactory.getLog(HelloWorldClient.class);
	public static void main(String[] args) {
		// JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
		// factory.setAddress("http://localhost:8083/scholar/ws/fetchWebService");
		// factory.setServiceClass(HelloWorld.class);
		// HelloWorld helloworld=(HelloWorld) factory.create();
		// System.out.println(helloworld.sayHello("AAA"));
		System.out.println("webservices test ...............................");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8089/scholarws/service/IScholarFetchService");
		factory.setServiceClass(IScholarFetchServicePortType.class);
		IScholarFetchServicePortType service = (IScholarFetchServicePortType) factory.create();
		String str=null;
		java.io.File files=new File("D:\\hgd\\ISI");
		File[] list=files.listFiles();
		String[] fielStr = new String[] { "d:\\ISIWebOfScience74522009_00000001.xml" ,"d:\\ISIWebOfScience74522009_00000002.xml","d:\\ISIWebOfScience74522009_00000003.xml"};
		FileReader file;
		try {
			Map dupMap=new HashMap();
			StringBuffer outStr=new StringBuffer();
			int dupInt=0;
//			FileWriter outFile=new FileWriter("D:\\dup_"+System.currentTimeMillis()+".xml");
			for (int i = 0; i < list.length; i++) {
				if(list[i].isDirectory()){
					File[] dirFiles=list[i].listFiles();
					String path=list[i].getName();
					String[] paths=StringUtils.split(path, "_");
					for (int j = 0; j < dirFiles.length; j++) {
						StringBuffer filestr=new StringBuffer();
						file = new FileReader(dirFiles[j]);
						BufferedReader reader = new BufferedReader(file);
						str = reader.readLine();
						while (str != null) {
							filestr.append(str);
							str=reader.readLine();
						}
						service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", NumberUtils.toInt(paths[0]), NumberUtils.toInt(paths[1]), NumberUtils.toInt(paths[2]), new String(Base64.encodeBase64(filestr.toString().getBytes())));
					}
				}
				
//				file = new FileReader(list[i]);
//				BufferedReader reader = new BufferedReader(file);
//				str = reader.readLine();
//				while (str != null) {
//					filestr.append(str);
//					str=reader.readLine();
//				}
//				Document doc = DocumentHelper.parseText(filestr.toString());
//				List listNode = doc.selectNodes("//scholarWorks/data");
//				if(listNode == null|| listNode.size()==0)
//				{
//					log.info("~~~~~~~~~~本次上传xml:" + filestr.toString());
//				}
//				for (Iterator iterator = listNode.iterator(); iterator.hasNext();) {
//					Map<String, Object> map = new HashMap<String, Object>();
//					Node e = ((Node) iterator.next());
//					Element ePub = (Element) (e.selectSingleNode("publication"));
//					for (Iterator it = ePub.attributeIterator(); it.hasNext();) {
//						Attribute attribute = (Attribute) it.next();
//						map.put(!"".equals(ObjectUtils.toString(attribute.getName())) ? attribute.getName().toLowerCase() : "", attribute.getValue());
//					}
//					String title = "".equals(ObjectUtils.toString(map.get("ctitle")).trim()) ? ObjectUtils.toString(map.get("etitle")) : ObjectUtils.toString(map.get("ctitle"));
//					String key=title+"|#973|#4|#"+map.get("original");
//					if(MapUtils.isEmpty(dupMap))
//					{
//						dupMap.put(key, "");
//					}else if(dupMap.containsKey(key)){
//						String currentData=e.asXML();
//						outStr.append(currentData);
//						dupInt++;
//					}else{
//						dupMap.put(key, "");
//					}
//				}
			}	
//			System.out.println("==============="+dupInt);
//			outFile.write(outStr.toString());
//			outFile.flush();
//			outFile.close();
//			for (int i = 0; i < list.length; i++) {
//				StringBuffer filestr=new StringBuffer();
//				file = new FileReader(list[i]);
//				BufferedReader reader = new BufferedReader(file);
//				str = reader.readLine();
//				while (str != null) {
//					filestr.append(str);
//					str=reader.readLine();
//				}
//				int retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 1976, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
//				System.out.println("message====================="+i+"==" + retint);
//			}
//			for (int i = 0; i < fielStr.length; i++) {
//				StringBuffer filestr=new StringBuffer();
//				file = new FileReader(fielStr[i]);
//				BufferedReader reader = new BufferedReader(file);
//				str = reader.readLine();
//				while (str != null) {
//					filestr.append(str);
//					str=reader.readLine();
//				}
//				int retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 1976, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
//				System.out.println("message====================="+i+"==" + retint);
//			}
			int number=0;
			while(number<1){
				number=service.getRecordsNum("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=",1976, 2008, 1);
				System.out.println("getrecodes number ===="+number);	
				sleep(3000);
			}
			
			/*
				file = new FileReader(fielStr[0]);
				BufferedReader reader = new BufferedReader(file);
				str = reader.readLine();
				while (str != null) {
					filestr.append(str);
					str=reader.readLine();
				}
				System.out.println(filestr);
				int retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
				System.out.println("message=====================" + retint);
				str=null;filestr=new StringBuffer();
				
				if(retint>0){
					file = new FileReader(fielStr[1]);
					reader = new BufferedReader(file);
					str = reader.readLine();
					while (str != null) {
						filestr.append(str);
						str=reader.readLine();
					}
					retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
					System.out.println("message=====================" + retint);
					str=null;filestr=new StringBuffer();
					if(retint>0){
						file = new FileReader(fielStr[2]);
						reader = new BufferedReader(file);
						str = reader.readLine();
						while (str != null) {
							filestr.append(str);
							str=reader.readLine();
						}
						retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
						System.out.println("message=====================" + retint);
						str=null;filestr=new StringBuffer();
						if(retint>0){
							file = new FileReader(fielStr[3]);
							reader = new BufferedReader(file);
							str = reader.readLine();
							while (str != null) {
								filestr.append(str);
								str=reader.readLine();
							}
							retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
							System.out.println("message=====================" + retint);
							str=null;filestr=new StringBuffer();
							if(retint>0){
								file = new FileReader(fielStr[4]);
								reader = new BufferedReader(file);
								str = reader.readLine();
								while (str != null) {
									filestr.append(str);
									str=reader.readLine();
								}
								retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
								System.out.println("message=====================" + retint);
								str=null;filestr=new StringBuffer();
								if(retint>0){
									file = new FileReader(fielStr[5]);
									reader = new BufferedReader(file);
									str = reader.readLine();
									while (str != null) {
										filestr.append(str);
										str=reader.readLine();
									}
									retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1, EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
									System.out.println("message=====================" + retint);
								}
							}
						}
					}
				}*/
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("webservices test ...............................end");
	}
}
