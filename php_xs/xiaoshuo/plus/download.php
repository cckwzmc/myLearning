<?php
require_once(dirname(__FILE__)."/../include/common.inc.php");
require_once(DEDEINC."/channelunit.class.php");
if(!isset($open)) $open = 0;

//��ȡ�����б�
if($open==0)
{
	$aid = (isset($aid) && is_numeric($aid)) ? $aid : 0;
	if($aid==0) die(' Request Error! ');

	$arcRow = GetOneArchive($aid);
	if($arcRow['aid']=='')
	{
		ShowMsg('�޷���ȡδ֪�ĵ�����Ϣ!','-1');
		exit();
	}
	extract($arcRow, EXTR_SKIP);

	$cu = new ChannelUnit($arcRow['channel'],$aid);
	if(!is_array($cu->ChannelFields))
	{
		ShowMsg('��ȡ�ĵ�������Ϣʧ�ܣ�','-1');
		exit();
	}

	$vname = '';
	foreach($cu->ChannelFields as $k=>$v){
		if($v['type']=='softlinks'){ $vname=$k; break; }
	}
	$row = $dsql->GetOne("Select $vname From `".$cu->ChannelInfos['addtable']."` where aid='$aid'");

	include_once(DEDEINC.'/taglib/channel/softlinks.lib.php');
	$ctag = '';
	$downlinks = ch_softlinks($row[$vname],$ctag,$cu,'',true);

	require_once($cfg_basedir.$cfg_templets_dir."/plus/download_links_templet.htm");
	exit();
}
//�ṩ������û�����
//------------------------
else if($open==1)
{
	$link = base64_decode(urldecode($link));

	//ͳ�����ش���
	$id = isset($id) && is_numeric($id) ? $id : 0;
	$hash = md5($link);
	$query = "select count(*) as dd from #@__downloads where hash='$hash'";
	$row = $dsql->GetOne($query);
	if($row['dd'] > 0)
	{
		$query = "update #@__downloads set downloads=downloads+1 where hash='$hash'";
		$dsql->ExecNoneQuery($query);
	}else {
		$query = "insert into #@__downloads(hash,id,downloads) values('$hash','$id',1)";
		$dsql->ExecNoneQuery($query);
	}

	echo "<script language='javascript'>location=\"$link\";</script>";
	exit();
}
?>