<?php
require_once(dirname(__FILE__)."/config.php");
require_once(DEDEINC.'/datalistcp.class.php');

$addsql = '';
if(isset($isexp))
{
	$addsql = " where isexp='$isexp' ";
}
$sql = "Select * From #@__moneycard_record $addsql order by aid desc";
$dlist = new DataListCP();
$dlist->pageSize = 25; //�趨ÿҳ��ʾ��¼����Ĭ��25����
if(isset($isexp))
{
	$dlist->SetParameter("isexp",$isexp);
}
$dlist->dsql->SetQuery("Select * From #@__moneycard_type ");
$dlist->dsql->Execute('ts');
while($rw = $dlist->dsql->GetArray('ts'))
{
	$TypeNames[$rw['tid']] = $rw['pname'];
}
$tplfile = DEDEADMIN."/templets/cards_manmage.htm";

//�������˳���ܸ���
$dlist->SetTemplate($tplfile);      //����ģ��
$dlist->SetSource($sql);            //�趨��ѯSQL
$dlist->Display();                  //��ʾ

function GetMemberID($mid)
{
	global $dsql;
	if($mid==0) return '0';
	$row = $dsql->GetOne("Select userid From #@__member where mid='$mid' ");
	if(is_array($row)) return "<a href='member_view.php?mid={$mid}'>".$row['userid']."</a>";
	else return '0';
}
function GetUseDate($time=0)
{
	if(!empty($time))
	{
		return GetDateMk($time);
	}else {
		return 'δʹ��';
	}
}
function GetSta($sta)
{
	if($sta==1) return '���۳�';
	else if($sta==-1) return '��ʹ��';
	else return 'δʹ��';
}

?>