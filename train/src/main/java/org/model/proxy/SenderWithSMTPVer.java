package org.model.proxy;

import java.util.Calendar;

import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

public class SenderWithSMTPVer {
	String host = "";
	String user = "";
	String password = "";

	public void setHost(String host) {
		this.host = host;
	}

	public void setAccount(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public void send(String from, String to, String subject, String content) {

		Properties props = new Properties();
		props.put("mail.smtp.host", host);// 指定SMTP服务器
		props.put("mail.smtp.auth", "true");// 指定是否需要SMTP验证
		try {
			Session mailSession = Session.getDefaultInstance(props);

			mailSession.setDebug(true);// 是否在控制台显示debug信息

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));// 发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// 收件人

			message.setSubject(subject);// 邮件主题
			message.setText(content);// 邮件内容
			message.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String args[]) {
		try{
		SenderWithSMTPVer sm = new SenderWithSMTPVer();

		sm.setHost("mail.irissz.com");// 指定要使用的邮件服务器
		sm.setAccount("yili@irissz.com", "654321");// 指定帐号和密码

		/*
		 * @param String 发件人的地址
		 * 
		 * @param String 收件人地址
		 * 
		 * @param String 邮件标题
		 * 
		 * @param String 邮件正文
		 */
		sm.send("lyxmq@126.com", "lyxmq@qq.com", "标题", "内容");
	}catch(Exception e){e.printStackTrace();}
	}

}
