<?php
$z[0]=Array("global","",236,264);
$z[0][4]['name']="cfg_soft_lang";
$z[1]=Array("field","",277,296);
$z[1][4]['name']="title";
$z[2]=Array("global","",297,323);
$z[2][4]['name']="cfg_webname";
$z[3]=Array("field","",364,393);
$z[3][4]['name']="keywords";
$z[4]=Array("field","",433,491);
$z[4][4]['name']="description";
$z[4][4]['function']="html2text(@me)";
$z[5]=Array("global","",510,536);
$z[5][4]['name']="cfg_cmspath";
$z[6]=Array("global","",588,617);
$z[6][4]['name']="cfg_templeturl";
$z[7]=Array("include","",707,742);
$z[7][4]['filename']="head.htm";
$z[8]=Array("field","",888,917);
$z[8][4]['name']="position";
$z[9]=Array("global","",990,1015);
$z[9][4]['name']="cfg_cmsurl";
$z[10]=Array("arclist","\r\n          	<li><a href=\"[field:arcurl/]\">[field:title/]</a>\r\n                        </li>",1332,1496);
$z[10][4]['flag']="c";
$z[10][4]['titlelen']="42";
$z[10][4]['row']="6";
$z[10][4]['bookpage']="YES";
$z[11]=Array("arclist","\r\n						 <li>                           \r\n						 <table width=\"100%\">\r\n							<tr>\r\n								<td  width=\"12%\" align=\"center\"><a href=\"[field:arcurl /]\"><img src=\"[field:litpic/]\" border=0></a></td>\r\n								<td  align=\"left\" valign=\"top\" style=\"padding-left:8px;\"><a style=\"color:#004D00;\r\ntext-decoration:none;font-size:14px;font-weight:bold;\" href=\"[field:arcurl/]\">[field:title/]</a> <br/><font color='#009900'>���ߣ�[field:writer/]</font><br/><p>&nbsp;&nbsp;&nbsp;&nbsp;[field:description function='cn_substr(@me,180)'/]...</p></td>\r\n							</tr>\r\n						 </table>				\r\n						</li>\r\n					  ",1734,2411);
$z[11][4]['titlelen']="20";
$z[11][4]['flag']="h";
$z[11][4]['col']="3";
$z[11][4]['row']="3";
$z[11][4]['bookpage']="YES";
$z[12]=Array("arclist","\r\n          	<li><a href=\"[field:arcurl/]\">[field:title/]</a>\r\n\r\n            </li>",2829,2983);
$z[12][4]['flag']="c";
$z[12][4]['titlelen']="42";
$z[12][4]['row']="6";
$z[12][4]['bookpage']="YES";
$z[13]=Array("arclist","\r\n          	<li><a href=\"[field:arcurl/]\">[field:title/]</a>\r\n       \r\n            </li>",3203,3364);
$z[13][4]['flag']="c";
$z[13][4]['titlelen']="42";
$z[13][4]['row']="6";
$z[13][4]['bookpage']="YES";
$z[14]=Array("list","\r\n					<tr>\r\n						<td width=\"20%\">&nbsp;<a href=\"[field:arcurl/]\" >[field:title/]</a></td> \r\n						<td width=\"40%\">&nbsp;<a href=\"javascript:getLastArcUrl('[field:typename/]','[field:id/]','[field:lasterartid/]');\">[field:lasterarticle/]</td>\r\n						<td width=\"15%\">&nbsp;[field:writer/]</td> \r\n						<td width=\"9%\">&nbsp;[field:click/]</td> \r\n						<td width=\"10%\">&nbsp;[field:pubdate function=\"MyDate('m-d',@me)\"/]</td> \r\n						<td width=\"6%\">&nbsp;����</td>\r\n					</tr>		\r\n						",3804,4355);
$z[14][4]['pagesize']="80";
$z[14][4]['bookpage']="LIST";
$z[14][4]['orderby']="click";
$z[15]=Array("pagelist","",4596,4667);
$z[15][4]['listitem']="info,index,end,pre,next,pageno";
$z[15][4]['listsize']="5";
$z[16]=Array("include","",4776,4813);
$z[16][4]['filename']="footer.htm";

?>