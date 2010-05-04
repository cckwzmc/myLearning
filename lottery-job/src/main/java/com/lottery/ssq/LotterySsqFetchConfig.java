package com.lottery.ssq;

public class LotterySsqFetchConfig {

	public static String expect;
	// 第一个数字要求大于等于该数字
	public static int firstMinCode = 1;
	// 第一个数字要求小于等于该数字
	public static int firstMaxCode = 33;
	// 最大数字要求大于等于该数字
	public static int lastMinCode = 29;
	// 最大数字要求小于等于该数字
	public static int lastMaxCode = 34;
	public static int secondMinCode = 2;
	public static int secondMaxCode = 20;
	public static int thirdMinCode = 3;
	public static int thirdMaxCode = 29;
	public static int fourthMinCode = 4;
	public static int fourthMaxCode = 33;
	// 上一期号码
	public static String[] preRedCode = new String[] {};
	// 计算出上一期的所有的边号
	public static String[] preSideCode = new String[] {};
	
	public static String media500WanUrl = "";
	public static String mediaSinaUrl = "";
	public static String dyjUrl = "";
	public static String dyjDowload = "";
	public static String www500wanUrl = "";
	public static String www500wanDowload = "";
	public static String caipiaoUrl="";
	public static String caipiaoDowload="";
	
//	
//	preRedCode` varchar(50) NOT NULL COMMENT '上一期号码',                                                                                                                                                                                                                                                                                    
//    `selectedOneCode` varchar(30) NOT NULL COMMENT '#最多只能中一个号码',                                                                                                                                                                                                                                                                  
//    `haveSideCode` int(1) NOT NULL DEFAULT '1' COMMENT '默认可以有一个边号，有几个边号，即上一期有个3这一期有个2或4,0表示没有边号,<0表示有没有边号都可以',                                                                                                                                              
//    `cannotSelectedTogethor` varchar(100) DEFAULT NULL COMMENT '#不能同时出现的号码多组号码用“|”分隔，如：2,3|4,5|',                                                                                                                                                                                                         
//    `selectCode` varchar(100) DEFAULT NULL COMMENT '#在这些号码中选择6个',                                                                                                                                                                                                                                                                 
//    `musthavered` varchar(20) DEFAULT NULL COMMENT '#胆',                                                                                                                                                                                                                                                                                          
//    `haveOnediffer` int(1) NOT NULL DEFAULT '1' COMMENT '#差值为1的个数(大于等于个数)，，如1，3，5 算两个,0表示没有连号',                                                                                                                                                                                                 
//    `haveThreeSeries` int(1) NOT NULL DEFAULT '-1' COMMENT '#有几个三连号  最多只能一个',                                                                                                                                                                                                                                               
//    `haveTwoSeries` int(1) NOT NULL DEFAULT '1' COMMENT '#有几个两连号,0表示没有连号,<0表示有没有连号都可以',                                                                                                                                                                                                                 
//    `lastMaxCode` int(2) NOT NULL DEFAULT '33' COMMENT '#最大数字要求小于等于该数字',                                                                                                                                                                                                                                                  
//    `lastMinCode` int(2) NOT NULL DEFAULT '22' COMMENT '#最大数字要求大于等于该数字',                                                                                                                                                                                                                                                  
//    `fourthMaxCode` int(2) NOT NULL DEFAULT '28' COMMENT '#第四个数数字要求小于等于该数字',                                                                                                                                                                                                                                          
//    `fourthMinCode` int(2) NOT NULL DEFAULT '11' COMMENT '#第四个数字要求大于等于该数字',                                                                                                                                                                                                                                             
//    `thirdMaxCode` int(2) NOT NULL DEFAULT '22',                                                                                                                                                                                                                                                                                                    
//    `thirdMinCode` int(2) NOT NULL DEFAULT '10',                                                                                                                                                                                                                                                                                                    
//    `secondMaxCode` int(2) NOT NULL DEFAULT '16',                                                                                                                                                                                                                                                                                                   
//    `secondMinCode` int(2) NOT NULL DEFAULT '2',                                                                                                                                                                                                                                                                                                    
//    `firstMaxCode` int(2) NOT NULL DEFAULT '11',                                                                                                                                                                                                                                                                                                    
//    `firstMinxCode` int(2) NOT NULL DEFAULT '1',                                                                                                                                                                                                                                                                                                    
//    `excludeRed` varchar(80) DEFAULT NULL COMMENT '#不包含其中的数值',                                                                                                                                                                                                                                                                      
//    `includePreRedNum` int(1) NOT NULL DEFAULT '1' COMMENT '是否包含上一期的一个号码;-1：不限制；0：不包含；>0:至少包含N个',                                                                                                                                                                                             
//    `quOne` int(1) NOT NULL DEFAULT '-1' COMMENT '#quOne,quTwo,quThree 都不为-1时和值最大为6  ',                                                                                                                                                                                                                                           
//    `quTwo` int(1) DEFAULT '-1',                                                                                                                                                                                                                                                                                                                    
//    `quThree` int(1) DEFAULT '-1',                                                                                                                                                                                                                                                                                                                  
//    `media500WanUrl` varchar(400) NOT NULL DEFAULT 'http://www.500wan.com/static/info/ssq/mediadetail/@expect@.xml',                                                                                                                                                                                                                                
//    `mediaSinaUrl` varchar(400) NOT NULL DEFAULT 'http://sports.sina.com.cn/l/ssqleitai/',                                                                                                                                                                                                                                                          
//    `dyjUrl` varchar(400) NOT NULL DEFAULT 'http://trade.cpdyj.com/trade/getproject.dyj?lottype=ss&playtype=&sort=allmoney&pageno=@pageno@&sort=renqi&expect=20@expect@&stype=0&moneytype=undefined&findstr=&sortending=descending&rnd=@random@',                                                                                                   
//    `dyjDowload` varchar(400) NOT NULL DEFAULT 'http://trade.cpdyj.com/Trade/download.asp?id=@id@&lottype=3&playtype=@playtype@',                                                                                                                                                                                                                   
//    `www500wanUrl` varchar(400) NOT NULL DEFAULT 'http://trade.500wan.com/pages/trade/ssq/project_list_sz_in.php?key=allmoney&sort=desc&pages_num=1&page=@page@&type=&ticheng_term=-1&state_term=0&totalcount_term=0~0&permoney_term=0~0&plan_term=0~0&baodi_term=0&currentsort=desc&currentkey=renqi&lotid=3&playid=1&expect=@expect@&rnd=91050',  
//    `www500wanDowload` varchar(400) NOT NULL DEFAULT 'http://trade.500wan.com/pages/trade/ssq/project_ssqshow.php?pid=@pid@&@nowdate@',                                                                                                                                                                                                             
//    `caipiaoUrl` varchar(400) NOT NULL DEFAULT 'http://www.2caipiao.com/showorder/userOrderShow.jhtml?action=edit&lotteryType=50&page=@page@',                                                                                                                                                                                                      
//    `caipiaoDowload
}
