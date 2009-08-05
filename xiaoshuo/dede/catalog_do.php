<?php
require_once(dirname(__FILE__)."/config.php");
if(empty($dopost))
{
	ShowMsg("�Բ�����ָ����Ŀ������","catalog_main.php");
	exit();
}
$cid = empty($cid) ? 0 : intval($cid);
$channelid = empty($channelid) ? 0 : intval($channelid);

/*--------------------------
//�����ĵ�
function addArchives();
---------------------------*/
if($dopost=="addArchives")
{
	//Ĭ�����µ��÷�����
	if(empty($cid) && empty($channelid))
	{
		header("location:article_add.php");
		exit();
	}
	if(!empty($channelid))
	{
		//����ģ�͵��÷�����
		$row = $dsql->GetOne("Select addcon from #@__channeltype where id='$channelid'");
	}
	else
	{
		//������Ŀ���÷�����
		$row = $dsql->GetOne("Select ch.addcon from `#@__arctype` tp left join `#@__channeltype` ch on ch.id=tp.channeltype where tp.id='$cid' ");
	}
	$gurl = $row["addcon"];
	if($gurl=="")
	{
		ShowMsg("�Բ�����ָ����Ŀ��������","catalog_main.php");
		exit();
	}

	//��ת�����ݲ���
	header("location:{$gurl}?channelid={$channelid}&cid={$cid}");
	exit();
}

/*--------------------------
//�����ĵ�
function listArchives();
---------------------------*/
else if($dopost=="listArchives")
{
	if(!empty($gurl))
	{
		if(empty($arcrank))
		{
			$arcrank = '';
		}
		$gurl = str_replace('..','',$gurl);
		header("location:{$gurl}?arcrank={$arcrank}&cid={$cid}");
		exit();
	}
	if($cid>0)
	{
		$row = $dsql->GetOne("Select #@__arctype.typename,#@__channeltype.typename as channelname,#@__channeltype.id,#@__channeltype.mancon from #@__arctype left join #@__channeltype on #@__channeltype.id=#@__arctype.channeltype where #@__arctype.id='$cid'");
		$gurl = $row["mancon"];
		$channelid = $row["id"];
		$typename = $row["typename"];
		$channelname = $row["channelname"];
		if($gurl=="")
		{
			ShowMsg("�Բ�����ָ����Ŀ��������","catalog_main.php");
			exit();
		}
	}
	else if($channelid>0)
	{

		$row = $dsql->GetOne("Select typename,id,mancon from #@__channeltype where id='$channelid'");
		$gurl = $row["mancon"];
		$channelid = $row["id"];
		$typename = "";
		$channelname = $row["typename"];
	}
	
	if(empty($gurl))
	{
		$gurl = 'content_list.php';
	}
	header("location:{$gurl}?channelid={$channelid}&cid={$cid}");
	exit();
}

/*--------------------------
//���ͨ��ģ��Ŀ¼
function viewTempletDir();
---------------------------*/
else if($dopost=="viewTemplet")
{
	header("location:tpl.php?path=/".$cfg_df_style);
	exit();
}

/*--------------------------
//���Բ�����
function GoGuestBook();
---------------------------*/
else if($dopost=="guestbook")
{
	header("location:{$cfg_phpurl}/guestbook.php?gotopagerank=admin");
	exit();
}

/*------------------------
�������ҳ�����Ŀ
function ViewSgPage()
------------------------*/
else if($dopost=="viewSgPage")
{
	require_once(DEDEINC."/arc.listview.class.php");
	$lv = new ListView($cid);
	$pageurl = $lv->MakeHtml();
	ShowMsg("���»��壬���Ժ�...",$pageurl);
	exit();
}

/*------------------------
������Ŀ����˳��
function upRank()
------------------------*/
else if($dopost=="upRank")
{
	//���Ȩ�����
	CheckPurview('t_Edit,t_AccEdit');

	//�����Ŀ�������
	CheckCatalog($cid,"����Ȩ���ı���Ŀ��");
	$row = $dsql->GetOne("Select reid,sortrank From #@__arctype where id='$cid'");
	$reid = $row['reid'];
	$sortrank = $row['sortrank'];
	$row = $dsql->GetOne("Select sortrank From #@__arctype where sortrank<=$sortrank And reid=$reid order by sortrank desc ");
	if(is_array($row))
	{
		$sortrank = $row['sortrank']-1;
		$dsql->ExecuteNoneQuery("update #@__arctype set sortrank='$sortrank' where id='$cid'");
	}
	ShowMsg("�����ɹ�������Ŀ¼...","catalog_main.php");
	exit();
}
else if($dopost=="upRankAll")
{
	//���Ȩ�����
	CheckPurview('t_Edit');
	$row = $dsql->GetOne("Select id From #@__arctype order by id desc");
	if(is_array($row))
	{
		$maxID = $row['id'];
		for($i=1;$i<=$maxID;$i++)
		{
			if(isset(${'sortrank'.$i}))
			{
				$dsql->ExecuteNoneQuery("Update #@__arctype set sortrank='".(${'sortrank'.$i})."' where id='{$i}';");
			}
		}
	}
	ShowMsg("�����ɹ������ڷ���...","catalog_main.php");
	exit();
}

/*--------------------------
//������Ŀ����
function UpCatlogCache();
---------------------------*/
else if($dopost=="upcatcache")
{
	UpDateCatCache();
	$sql = " TRUNCATE TABLE `#@__arctiny`";
	$dsql->executenonequery($sql);
	
	//������ͨģ��΢����
	$sql = "insert into `#@__arctiny`(id, typeid, typeid2, arcrank, channel, senddate, sortrank, mid)  
	        Select id, typeid, typeid2, arcrank, channel, senddate, sortrank, mid from `#@__archives` ";
	$dsql->executenonequery($sql);
	
	//���뵥��ģ��΢����
	$dsql->SetQuery("Select id,addtable From `#@__channeltype` where id < -1 ");
	$dsql->Execute();
	$doarray = array();
	while($row = $dsql->GetArray())
	{
		$tb = str_replace('#@__', $cfg_dbprefix, $row['addtable']);
		if(empty($tb) || isset($doarray[$tb]) )
		{
			continue;
		}
		else
		{
			$sql = "insert into `#@__arctiny`(id, typeid, typeid2, arcrank, channel, senddate, sortrank, mid)  
			        Select aid, typeid, 0, arcrank, channel, senddate, 0, mid from `$tb` ";
			$rs = $dsql->executenonequery($sql); 
			$doarray[$tb]  = 1;
		}
	}
	
	ShowMsg("�����ɹ������ڷ���...","catalog_main.php");
	exit();
}

/*---------------------
��ȡJS�ļ�
function GetJs
----------------------*/
else if($dopost=="GetJs")
{
	header("location:makehtml_js.php");
	exit();
}

/*-----------
������������
function GetSunListsMenu();
-----------*/
else if($dopost=="GetSunListsMenu")
{
	$userChannel = $cuserLogin->getUserChannel();
	require_once(DEDEINC."/typeunit.class.menu.php");
	AjaxHead();
	PutCookie('lastCidMenu',$cid,3600*24,"/");
	$tu = new TypeUnit($userChannel);
	$tu->LogicListAllSunType($cid,"��");
}

/*-----------
������������
function GetSunLists();
-----------*/
else if($dopost=="GetSunLists")
{
	require_once(DEDEINC."/typeunit.class.admin.php");
	AjaxHead();
	PutCookie('lastCid',$cid,3600*24,"/");
	$tu = new TypeUnit();
	$tu->dsql = $dsql;
	echo "    <table width='100%' border='0' cellspacing='0' cellpadding='0'>\r\n";
	$tu->LogicListAllSunType($cid,"��");
	echo "    </table>\r\n";
	$tu->Close();
}

?>