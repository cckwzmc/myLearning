package org.webservice.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.security.util.EncryptionUtils;

import com.iris.scholar.server.ws.IScholarFetchServicePortType;

public class HelloWorldClient extends Thread {
	private static final Log log = LogFactory.getLog(HelloWorldClient.class);

	public static void main(String[] args) {
		// JaxWsProxyFactoryBean factory=new JaxWsProxyFactoryBean();
		// factory.setAddress("http://localhost:8083/scholar/ws/fetchWebService");
		// factory.setServiceClass(HelloWorld.class);
		// HelloWorld helloworld=(HelloWorld) factory.create();
		// System.out.println(helloworld.sayHello("AAA"));
		System.out.println("webservices test ...............................");
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress("http://localhost:8089/scholarws/service/IScholarFetchService");
		// factory.setAddress("http://dev.scholarmate.com/scholarws/service/IScholarFetchService");
		factory.setServiceClass(IScholarFetchServicePortType.class);
		IScholarFetchServicePortType service = (IScholarFetchServicePortType) factory.create();
		String str = null;
		
		java.io.File files = new File("d:\\xml1");
		 File[] list=files.listFiles();
		// String[] fielStr = new String[] { "d:\\xml1"};
		// FileReader file;
		// files=new File("d:\\Noname1.xml");
		try {
//			Map dupMap = new HashMap();
//			StringBuffer outStr = new StringBuffer();
//			int dupInt = 0;
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//			// System.out.println(new String(Base64.decodeBase64((service.getSearchInfo("bhiE8nYtLjUVR8zyNeQWv0wLNqdxaVuK79SAwJYSNLs=", 1356).getBytes()))));
//			System.out.println(EncryptionUtils.byteArrayToString(Base64.decodeBase64(service
//					.getSearchInfo("bhiE8nYtLjUVR8zyNeQWv0wLNqdxaVuKY9Zk8tbvp+8=", 1472).getBytes())));
//			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			// FileWriter outFile=new FileWriter("D:\\dup_"+System.currentTimeMillis()+".xml");
			for (int i = 0; i < list.length; i++) {
				StringBuffer filestr = new StringBuffer();
				FileReader file = new FileReader(list[i]);
				BufferedReader reader = new BufferedReader(file);
				str=reader.readLine();
				while (str != null) {
					filestr.append(str);
					str= reader.readLine();
				}
				service.writeData("bhiE8nYtLjUVR8zyNeQWv0wLNqdxaVuKK5bqEeqQyWY=",1505,2009, 32768, new String(Base64.encodeBase64(filestr.toString().getBytes())));
								//                                               ins_id,yearBatch,ref_db.db_bit_code,
			}

			// file = new FileReader(list[i]);
			// StringBuffer filestr=new StringBuffer();
			// file = new FileReader(files);
			// BufferedReader reader = new BufferedReader(file);
			// str = reader.readLine();
			// while (str != null) {
			// filestr.append(str);
			// str=reader.readLine();
			// }
			// System.out.println(new String(filestr.toString().getBytes("UTF-8"),"GB2312"));
			// Document doc = DocumentHelper.parseText(StringUtils.replace(filestr.toString(), "&quot;", "\""));
			// List listNode = doc.selectNodes("//scholarWorks/data");
			// if(listNode == null|| listNode.size()==0)
			// {
			// log.info("~~~~~~~~~~本次上传xml:" + filestr.toString());
			// }
			// for (Iterator iterator = listNode.iterator(); iterator.hasNext();) {
			// Map<String, Object> map = new HashMap<String, Object>();
			// Node e = ((Node) iterator.next());
			// Element ePub = (Element) (e.selectSingleNode("publication"));
			// for (Iterator it = ePub.attributeIterator(); it.hasNext();) {
			// Attribute attribute = (Attribute) it.next();
			// map.put(!"".equals(ObjectUtils.toString(attribute.getName())) ? attribute.getName().toLowerCase() : "", attribute.getValue());
			// }
			// String title = "".equals(ObjectUtils.toString(map.get("ctitle")).trim()) ? ObjectUtils.toString(map.get("etitle")) :
			// ObjectUtils.toString(map.get("ctitle"));
			// String key=title+"|#973|#4|#"+map.get("original");
			// if(MapUtils.isEmpty(dupMap))
			// {
			// dupMap.put(key, "");
			// }else if(dupMap.containsKey(key)){
			// String currentData=e.asXML();
			// outStr.append(currentData);
			// dupInt++;
			// }else{
			// dupMap.put(key, "");
			// }
			// }
			// }
			// System.out.println("==============="+dupInt);
			// outFile.write(outStr.toString());
			// outFile.flush();
			// outFile.close();
			// for (int i = 0; i < list.length; i++) {
			// StringBuffer filestr=new StringBuffer();
			// file = new FileReader(list[i]);
			// BufferedReader reader = new BufferedReader(file);
			// str = reader.readLine();
			// while (str != null) {
			// filestr.append(str);
			// str=reader.readLine();
			// }
			// int retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 1976, 2008, 1,
			// EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
			// System.out.println("message====================="+i+"==" + retint);
			// }
			// for (int i = 0; i < fielStr.length; i++) {
			// StringBuffer filestr=new StringBuffer();
			// file = new FileReader(fielStr[i]);
			// BufferedReader reader = new BufferedReader(file);
			// str = reader.readLine();
			// while (str != null) {
			// filestr.append(str);
			// str=reader.readLine();
			// }
			// int retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 1976, 2008, 1,
			// EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes())));
			// System.out.println("message====================="+i+"==" + retint);
			// }
			// int number=0;
			// int i=0;
			// while(i<100){
			// String xml=service.getSearchInfo("bhiE8nYtLjUVR8zyNeQWv0wLNqdxaVuK79SAwJYSNLs=",1356);
			// System.out.println("getrecodes number :===="+xml);
			// i++;
			// sleep(3000);
			// }
			// System.out.println(EncryptionUtils.byteArrayToString(Base64.decodeBase64(xml.getBytes())));
			// System.out.println(EncryptionUtils.byteArrayToString(Base64.decodeBase64("PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHNlYXJjaGluZm8+PHNlYXJjaHN0cj48c2VhcmNocGFyYW0+PGRiaWQ+NDwvZGJpZD48ZGJkc2NyPkNoaW5hSm91cm5hbDwvZGJkc2NyPjx5ZWFyPjIwMDg8L3llYXI+PHllYXJkYXRhbnVtPjA8L3llYXJkYXRhbnVtPjxzZWFyY2hzdHI+KOWNleS9jSXlk4jlsJTmu6jlt6XkuJrlpKflraYpIGFuZCDlubQ9MjAwODwvc2VhcmNoc3RyPjwvc2VhcmNocGFyYW0+PC9zZWFyY2hzdHI+PHBhcmFtaW5mbz48UFJPQ05VTT41PC9QUk9DTlVNPjxTRUFSQ0hUSU1FPjU8L1NFQVJDSFRJTUU+PFBST0NUSU1FPjg8L1BST0NUSU1FPjxJTlRFUlZBTD4yMDwvSU5URVJWQUw+PC9wYXJhbWluZm8+PC9zZWFyY2hpbmZvPg==".getBytes())));

			/*
			 * file = new FileReader(fielStr[0]); BufferedReader reader = new BufferedReader(file); str = reader.readLine(); while (str != null) {
			 * filestr.append(str); str=reader.readLine(); } System.out.println(filestr); int
			 * retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); str=null;filestr=new StringBuffer();
			 * 
			 * if(retint>0){ file = new FileReader(fielStr[1]); reader = new BufferedReader(file); str = reader.readLine(); while (str != null) {
			 * filestr.append(str); str=reader.readLine(); } retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); str=null;filestr=new StringBuffer(); if(retint>0){ file = new FileReader(fielStr[2]); reader = new BufferedReader(file); str =
			 * reader.readLine(); while (str != null) { filestr.append(str); str=reader.readLine(); }
			 * retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); str=null;filestr=new StringBuffer(); if(retint>0){ file = new FileReader(fielStr[3]); reader = new BufferedReader(file); str =
			 * reader.readLine(); while (str != null) { filestr.append(str); str=reader.readLine(); }
			 * retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); str=null;filestr=new StringBuffer(); if(retint>0){ file = new FileReader(fielStr[4]); reader = new BufferedReader(file); str =
			 * reader.readLine(); while (str != null) { filestr.append(str); str=reader.readLine(); }
			 * retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); str=null;filestr=new StringBuffer(); if(retint>0){ file = new FileReader(fielStr[5]); reader = new BufferedReader(file); str =
			 * reader.readLine(); while (str != null) { filestr.append(str); str=reader.readLine(); }
			 * retint=service.writeData("bhiE8nYtLjU50OccQcYqBvSxYAiLbKrpFbt0LI0k10c=", 5545, 2008, 1,
			 * EncryptionUtils.byteArrayToString(Base64.encodeBase64(filestr.toString().getBytes()))); System.out.println("message=====================" +
			 * retint); } } } } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("webservices test ...............................end");
	}
}
