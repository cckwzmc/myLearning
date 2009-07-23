package org.webservice.client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class PatternTest {
	public static void main(String[] args) {
		String teststr = "<TR bgColor=#ffffff height=24>                <TD align=left>100</TD>                <TD noWrap align=middle><A class=ahui12                   href=\"/Article/2/134.html\">玄幻</a></TD>                <TD align=left><A class=ahui12                   href=\"/Article/154172.html\"><font color=\"#006699\">奇迹公子在异界</font></a></TD>                <TD align=left><A class=ahuang12                  href=\"/html/book/130/154172/4217346.shtm\">第二卷 魔法学院  第三十二章 魔法新概念</a></TD>                <TD align=middle>5月6日</TD>                <TD align=middle><A class=ahui12                   href=\"/Author/WB/154172.html\">依然</a></TD>                <TD align=middle><FONT color=blue>完结</FONT></TD>                				</TR>	";
		teststr+="<TR bgColor=#ffffff height=24>                <TD align=left>1</TD>                <TD noWrap align=middle><A class=ahui12                   href=\"/Article/2/340.html\">测试1</a></TD>                <TD align=left><A class=ahui12                   href=\"/Article/151136.html\"><font color=\"#006699\">斗罗大陆</font></a></TD>                <TD align=left><A class=ahuang12                  href=\"/html/book/130/151136/4354833.shtm\">第二十五集 单属宗族 第一百八十九章 唐门五堂</a></TD>                <TD align=middle>7月22日</TD>                <TD align=middle><A class=ahui12                   href=\"/Author/WB/151136.html\">唐家三少</a></TD>                <TD align=middle><FONT color=blue>连载</FONT></TD>                				</TR>";
		teststr+="<td height=\"22\" align=\"center\">[<a href=\"/Book/LN48.aspx\">玄幻魔法</a>]</td><td align=\"left\">[<a href=\"/Html/Book/48/3641/List.htm\" target=\"_blank\">目录</a>]&nbsp;<a href=\"/Book/3641.aspx\">斗破苍穹</a></td><td align=\"left\"><a href=\"/Html/Book/48/3641/1140742.htm\" target=\"_blank\">正文&nbsp;第一百九十九章 探测</a></td><td align=\"center\"><a href=\"/WB3641.aspx\">天蚕土豆</a></td><td align=\"center\">7月22日&nbsp;</td><td align=\"center\"><a href=\"/down/down.asp?id=3641\" target=\"_blank\">TXT</a></td><td align=\"center\"><font color=blue>连载</font></td><td align=\"center\">29550</td>";
		teststr="<td width=\"25%\" height=\"24\"><div align=\"left\"><a title=\"更新时间:2009-1-7 14:02:00 更新字数:2312\" href=\"619328.htm\" target=\"_blank\">第二十四章 第一次套现</a></div></td><td width=\"25%\" height=\"24\"><div align=\"left\"><a title=\"更新时间:2009-1-7 14:02:00 更新字数:2150\" href=\"619325.htm\" target=\"_blank\">第二十一章 无价可估</a></div></td><td width=\"25%\" height=\"24\"><div align=\"left\"><a title=\"更新时间:2009-1-7 14:02:00更新字数:2349\" href=\"619326.htm\" target=\"_blank\">第二十二章 猪栏的理想</a></div></td><td width=\"25%\" height=\"24\"><div align=\"left\"><a title=\"更新时间:2009-1-7 14:02:00 更新字数:2437\" href=\"619327.htm\" target=\"_blank\">第二十三章 远客来访</a></div></td>" +
				"";
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
//		regex=".*?<TD align=left><A class=ahui12                   href=\"([^<]*)\"><font color=\"#006699\">.*?";
		regex=".*?<TR bgColor=#ffffff height=24>.*<TD align=left>100</TD>.*<TD noWrap align=middle><A class=ahui12.*>([^<]*)</a></TD>.*<TD align=left><A class=ahui12.*href=\".*\"><font color=\"#006699\">.*</font></a></TD>.*<TD align=left><A class=ahuang12.*>.*</a></TD>.*<TD align=middle><A class=ahui12.*<TD align=middle><FONT color=blue>.*</FONT></TD>                				</TR>.*?";
		regex=".*?href=\"([^<]*)\" target=\"_blank\">([^<]*)</a></div></td>.*? ";
		Pattern pt = Pattern.compile(regex);
		Matcher matcher = pt.matcher(teststr);
		while(matcher.find()){
			//System.out.println(matcher.groupCount());
			//for (int i = 0; i < matcher.groupCount(); i++) {
				System.out.println(matcher.group(1));
			//}
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
