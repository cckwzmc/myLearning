<?php
require_once('config.php');
CheckPurview('t_Move');
require_once(DEDEINC.'/oxwindow.class.php');
require_once(DEDEINC.'/typelink.class.php');
$typeid = isset($typeid) ? intval($typeid) : 0;
if(empty($dopost))
{
	$dopost = 'movelist';
}
$row  = $dsql->GetOne(" Select reid,typename,channeltype From `#@__arctype` where id='$typeid' ");
$typename = $row['typename'];
$reid = $row['reid'];
$channelid = $row['channeltype'];

//�ƶ���Ŀ
if($dopost=='moveok')
{
	if($typeid==$movetype)
	{
		ShowMsg('�ƶԶ����Ŀ��λ����ͬ��','catalog_main.php');
		exit();
	}
	if(IsParent($movetype,$typeid,$dsql))
	{
		ShowMsg('���ܴӸ����ƶ������࣡','catalog_main.php');
		exit();
	}
	$dsql->ExecuteNoneQuery("Update `#@__arctype` set reid='$movetype' where id='$typeid'");
	ShowMsg('�ɹ��ƶ�Ŀ¼��','catalog_main.php');
	exit();
}

function IsParent($myid,$topid,$dsql)
{
	$row = $dsql->GetOne("select id,reid,topid from #@__arctype where topid='$myid' or id='$myid'");
	if($row['reid']==$topid)
	{
		return true;
	}
	else if($row['reid']==0)
	{
		return false;
	}
	else
	{
		return IsParent($row['reid'],$topid,$dsql);
	}
}

$tl = new TypeLink($typeid);
$typeOptions = $tl->GetOptionArray(0,0,$channelid);
$wintitle = "�ƶ���Ŀ";
$wecome_info = "<a href='catalog_main.php'>��Ŀ����</a> &gt;&gt; �ƶ���Ŀ";
$win = new OxWindow();
$win->Init('catalog_move.php','js/blank.js','POST');
$win->AddHidden('typeid',$typeid);
$win->AddHidden('dopost','moveok');
$win->AddTitle("�ƶ�Ŀ¼ʱ����ɾ��ԭ���Ѵ������б��ƶ��������¶���Ŀ����HTML��");
$win->AddItem('��ѡ�����Ŀ�ǣ�',"$typename($typeid)");
$win->AddItem('��ϣ���ƶ����Ǹ���Ŀ��',"<select name='movetype'>\r\n<option value='0'>�ƶ�Ϊ������Ŀ</option>\r\n$typeOptions\r\n</select>");
$win->AddItem('ע�����','������Ӹ����ƶ����Ӽ�Ŀ¼��ֻ�����Ӽ������߼���ͬ����ͬ�����������');
$winform = $win->GetWindow('ok');
$win->Display();

?>