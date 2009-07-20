package org.webservice.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class PatternTest {
	public static void main(String[] args) {
		String teststr = "<div class>aaaaa<br/>www.AiShuzhe.comaaaa<br/><script type=\"text/javascript\">";
		// Pattern p = null;
		// PatternCompiler complier = new Perl5Compiler();
		// p = complier.compile(\".*?</a>([^<b>]*)<b>.*?",
		// Perl5Compiler.CASE_INSENSITIVE_MASK);
		// PatternMatcher matcher = new Perl5Matcher();
//		String regex=".*?<td class=\"odd\"><a href=\"([^<]*)\">.*</a></td>.*?";
//		String regex=".*?<td class=\"odd\"> \\[<a href=\".*\" class=\"sideA\" target=\"_blank\">([^<]*)</a>\\]</td>.*?";
//		String regex=".*?<td class=\"odd\"> \\[<a href=\".*\" class=\"sideA\" target=\"_blank\">([^<]*)</a>\\]</td>.*?";
//		String regex=".*?<tr> <td class=\"even\" align=\"center\">([^</]*)</td>.*?";
//		String regex=".*?<img src=\"([^<]*)\" alt.*vspace=\"5\" />.*?";
		System.out.println(StringUtils.replace(teststr,"(?i)aishuzhe.com", "rentimm.net"));
		String regex=".*?<div class>([$<^<script]*)<script type=\"text/javascript\">.*?";
//				.*?<tr> <td class="even" align="center">([^</td>]*)</td>.*?
		Pattern pt = Pattern.compile(regex);
		Matcher matcher = pt.matcher(teststr);
		while(matcher.find()){
			System.out.println(matcher.groupCount()+matcher.group(1));
		}
		String   oldString   =   "abCde123Abc";  
        String   newString   =   oldString.replaceAll("(?i)abc",   "ABC");  
        System.out.println(newString);  
         
        newString   =   oldString.replaceAll("a(?i)bc",   "ABC");  
        System.out.println(newString);  
         
        newString   =   oldString.replaceAll("a((?i)b)c",   "ABC");  
        System.out.println(newString);       
//		boolean b = matcher.matches(teststr, p);

		//		
		// org.apache.oro.text.regex.Pattern pattern = null;
		// Pattern matcher =Pattern;// new
		// PatternMatcher(".*?(</a>.*<b>.*)*");//pattern.matcher(teststr);
		// PatternCompiler complier=new PatternCompiler();
		// try {
		// pattern=complier.compile(,Perl5Compiler.READ_ONLY_MASK);
		// } catch (MalformedPatternException e) {
		// e.printStackTrace();
		// }
		// boolean b=matcher.matches(teststr, pattern);
//		 boolean b = matcher.matches();
		// 当条件满足时，将返回true，否则返回false
//		System.out.println(b);
//		if (b) {<tr> <td
//			for (int i = 0; i < matcher.getMatch().groups() + 1; i++) {
//				System.out.println(matcher.getMatch().groups() + ":"
//						+ matcher.getMatch().group(i));
//			}
//
//		}
		
		String s="abcddsesee";
		pt = Pattern.compile("[^abc]");
		matcher = pt.matcher(s);
		System.out.println(matcher.matches());
//		while(matcher.find()){
//			System.out.println(matcher.groupCount()+matcher.group(1));
//		}
		// containsPatternTest();
	}

	/**
	 * 部分匹配表达式
	 */
	// public static void containsPatternTest() {
	// PatternCompiler pc = new Perl5Compiler();
	// PatternMatcher matcher = new Perl5Matcher();
	// Pattern p = null;
	//
	// // 编译一下,生成表达式
	// try {
	// p = pc.compile("(<(([a-z]{2})|([0-9]{2}))>)*",
	// Perl5Compiler.CASE_INSENSITIVE_MASK);
	// } catch (MalformedPatternException e) {
	// }
	//
	// if (null != p) {
	// // 查看字符串中是否部分匹配表达式p
	// if (matcher.contains("<11>ads<44>f", p)) {
	// // 得到匹配结果
	// MatchResult mr = matcher.getMatch();
	// // 输出匹配的第一个子表达式值,从1开始的\
	// System.out.println(mr.group(1));
	// } else {
	// }
	// }
	// }
}
