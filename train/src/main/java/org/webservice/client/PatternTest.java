package org.webservice.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.oro.text.regex.MalformedPatternException;

public class PatternTest {
	public static void main(String[] args) throws MalformedPatternException {
		String teststr = "asfdas\n\td\rf<tr> <td class=\"even\" align=\"center\">武侠</td> <td class=\"odd\"><a href=\"http://www.junzitang.com/files/article/info/41/41997.htm\">风流三界</a></td> <td class=\"even\"><a href=\"http://www.junzitang.com/files/article/html/41/41997/3369913.html\" target=\"_blank\">正文 第一百四十四章 王</a></td> <td class=\"odd\"><a href=\"http://www.junzitang.com/userinfo.php?id=311782\" target=\"_blank\">超级殳彳</a></td> <td class=\"even\">1081174</td> <td class=\"odd\" align=\"center\">09-07-16</td> <td class=\"even\" align=\"center\">连载</td> </tr>asfsafd";
		// Pattern p = null;
		// PatternCompiler complier = new Perl5Compiler();
		// p = complier.compile(\".*?</a>([^<b>]*)<b>.*?",
		// Perl5Compiler.CASE_INSENSITIVE_MASK);
		// PatternMatcher matcher = new Perl5Matcher();
		String regex=".*?<td class=\"odd\"><a href=\"([^<]*)\">.*</a></td>.*?";
//		String regex=".*?<tr> <td class=\"even\" align=\"center\">([^</]*)</td>.*?";
//				.*?<tr> <td class="even" align="center">([^</td>]*)</td>.*?
		Pattern pt = Pattern.compile(regex);
		Matcher matcher = pt.matcher(teststr);
		while(matcher.find()){
			System.out.println(matcher.groupCount()+matcher.group(1));
		}
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
