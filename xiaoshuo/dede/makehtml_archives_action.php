<?php
require_once(dirname(__FILE__)."/config.php");
CheckPurview('sys_MakeHtml');
require_once(DEDEINC."/arc.archives.class.php");

$est1 = ExecTime();
$startid  = (empty($startid)  ? -1  : $startid);
$endid    = (empty($endid)    ? 0  : $endid);
$startdd  = (empty($startdd)  ? 0  : $startdd);
$pagesize = (empty($pagesize) ? 20 : $pagesize);
$totalnum = (empty($totalnum) ? 0  : $totalnum);
$typeid   = (empty($typeid)   ? 0  : $typeid);
$seltime  = (empty($seltime)  ? 0  : $seltime);
$stime    = (empty($stime)    ? '' : $stime );
$etime    = (empty($etime)    ? '' : $etime);
$sstime   = (empty($sstime)   ? 0  : $sstime); 
$mkvalue  = (empty($mkvalue)  ? 0  : $mkvalue);

//一键更新传递的参数
if(!empty($uptype))
{
	if($uptype!='time')
	{
		$startid = $mkvalue;
	} else {
		$t1 = $mkvalue;
	}
}
else
{
	$uptype = '';
}

//获取条件
$idsql = '';
$gwhere = ($startid==-1 ? " where a.arcrank=0 " : " where a.id>=$startid And a.arcrank=0 ");


$gwhere.=" And ar.flag like '%x%' and ar.isbookpage is not null ";	

	
if($endid > $startid && $startid > 0) {
	$gwhere .= " And a.id <= $endid ";
}

if($typeid!=0) {
	$ids = GetSonIds($typeid);
	$gwhere .= " And a.typeid in($ids) ";
}

if($idsql=='') {
	$idsql = $gwhere;
}

if($seltime==1)
{
	$t1 = GetMkTime($stime);
	$t2 = GetMkTime($etime);
	$idsql .= " And (a.senddate >= $t1 And a.senddate <= $t2) ";
}
else if(isset($t1) && is_numeric($t1))
{
	$idsql .= " And a.senddate >= $t1 ";
}

//统计记录总数
if($totalnum==0)
{
	$row = $dsql->GetOne("Select count(*) as dd From `#@__arctiny`  a left join  `#@__archives` ar on a.id=ar.id  $idsql");
	$totalnum = $row['dd'];
	//清空缓存
	$dsql->ExecuteNoneQuery("Delete From `#@__arccache` ");
}

//获取记录，并生成HTML
if($totalnum > $startdd+$pagesize) {
	$limitSql = " limit $startdd,$pagesize";
}
else {
	$limitSql = " limit $startdd,".($totalnum - $startdd);
}

$tjnum = $startdd;
if(empty($sstime)) {
	$sstime = time();
}

//如果生成数量大于500，并且没选栏目，按栏目排序生成
if($totalnum > 500 && empty($typeid)) {
	$dsql->Execute('out',"Select a.id From `#@__arctiny`   a left join  `#@__archives` ar on a.id=ar.id  $idsql  $limitSql");
}
else {
	$dsql->Execute('out',"Select a.id From `#@__arctiny`   a left join  `#@__archives` ar on a.id=ar.id  $idsql $limitSql");
}

while($row=$dsql->GetObject('out'))
{
	$tjnum++;
	$id = $row->id;
	$aa_id=Array($id,"detail");
	$ac = new Archives($aa_id);
	$rurl = $ac->MakeHtml();
}

$t2 = ExecTime();
$t2 = ($t2 - $est1);
$ttime = time() - $sstime;
$ttime = number_format(($ttime / 60),2);

//返回提示信息
$tjlen = $totalnum>0 ? ceil( ($tjnum/$totalnum) * 100 ) : 100;
$dvlen = $tjlen * 2;
$tjsta = "<div style='width:200;height:15;border:1px solid #898989;text-align:left'><div style='width:$dvlen;height:15;background-color:#829D83'></div></div>";
$tjsta .= "<br/>本次用时：".number_format($t2,2)."，总用时：$ttime 分钟，到达位置：".($startdd+$pagesize)."<br/>完成创建文件总数的：$tjlen %，继续执行任务...";


//速度测试
/*-------------
if($startdd > 1000)
{
	ShowMsg("生成文件：1000 总用时：{$ttime} 分钟", "javascript:;");
	exit();
}
--------------*/

if($tjnum < $totalnum)
{
	$nurl  = "makehtml_archives_action.php?endid=$endid&startid=$startid&typeid=$typeid";
	$nurl .= "&totalnum=$totalnum&startdd=".($startdd+$pagesize)."&pagesize=$pagesize";
	$nurl .= "&seltime=$seltime&sstime=$sstime&stime=".urlencode($stime)."&etime=".urlencode($etime)."&uptype=$uptype&mkvalue=$mkvalue";
	ShowMsg($tjsta,$nurl,0,100);
	exit();
}
else
{
	if($typeid!='')
	{
		  ShowMsg("生成文件：$totalnum 总用时：{$ttime} 分钟，现转向当前栏目更新&gt;&gt;","makehtml_list_action.php?typeid=$typeid&uptype=all&maxpagesize=50&upnext=1");
	}
	else
	{
		if($uptype=='') {
			ShowMsg("完成所有创建任务！，生成文件：$totalnum 总用时：{$ttime} 分钟。","javascript:;");
		}
		else {
			ShowMsg("完成文档HTML更新任务，现在开始进行主页更新...","makehtml_all.php?action=make&step=3&uptype=$uptype&mkvalue=$mkvalue");
		}
	}
}

?>