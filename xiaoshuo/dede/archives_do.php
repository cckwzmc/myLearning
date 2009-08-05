<?php
require_once(dirname(__FILE__).'/config.php');
require_once(DEDEADMIN.'/inc/inc_batchup.php');
require_once(DEDEADMIN.'/inc/inc_archives_functions.php');
require_once(DEDEINC.'/typelink.class.php');
require_once(DEDEINC.'/arc.archives.class.php');
$ENV_GOBACK_URL = (empty($_COOKIE['ENV_GOBACK_URL']) ? 'content_list.php' : $_COOKIE['ENV_GOBACK_URL']);

if(empty($dopost) || empty($aid))
{
	ShowMsg('�Բ�����ûָ�����в�����','-1');
	exit();
}
$aid = ereg_replace('[^0-9]','',$aid);

/*--------------------------
//�༭�ĵ�
function editArchives(){ }
---------------------------*/
if($dopost=='editArchives')
{
	$query = "Select arc.id,arc.typeid,ch.maintable,ch.editcon
           From `#@__arctiny` arc
           left join `#@__arctype` tp on tp.id=arc.typeid
           left join `#@__channeltype` ch on ch.id=arc.channel
          where arc.id='$aid' ";
	$row = $dsql->GetOne($query);
	$gurl = $row['editcon'];
	if($gurl=='')
	{
		$gurl='article_edit.php';
	}
	header("location:{$gurl}?aid=$aid");
	exit();
}

/*--------------------------
//����ĵ�
function viewArchives(){ }
---------------------------*/
else if($dopost=="viewArchives")
{
	$aid = ereg_replace('[^0-9]','',$aid);

	//��ȡ������Ϣ
	$query = "Select arc.*,ch.maintable,ch.addtable,ch.issystem,ch.editcon,
	          tp.typedir,tp.typename,tp.corank,tp.namerule,tp.namerule2,tp.ispart,tp.moresite,tp.sitepath,tp.siteurl
           From `#@__arctiny` arc
           left join `#@__arctype` tp on tp.id=arc.typeid
           left join `#@__channeltype` ch on ch.id=tp.channeltype
           where arc.id='$aid' ";
	$trow = $dsql->GetOne($query);
	$trow['maintable'] = ( trim($trow['maintable'])=='' ? '#@__archives' : trim($trow['maintable']) );
	if($trow['issystem'] != -1)
	{
		$arcQuery = "Select arc.*,tp.typedir,tp.typename,tp.corank,tp.namerule,tp.namerule2,tp.ispart,tp.moresite,tp.sitepath,tp.siteurl
		           from `{$trow['maintable']}` arc left join `#@__arctype` tp on arc.typeid=tp.id
		           left join `#@__channeltype` ch on ch.id=arc.channel where arc.id='$aid' ";
		$arcRow = $dsql->GetOne($arcQuery);
		if($arcRow['ismake']==-1 || $arcRow['corank']!=0 || $arcRow['arcrank']!=0 || $arcRow['typeid']==0 || $arcRow['money']>0)
		{
			echo "<script language='javascript'>location.href='{$cfg_phpurl}/view.php?aid={$aid}';</script>";
			exit();
		}
	}
	else
	{
		$arcRow['id'] = $aid;
		$arcRow['typeid'] = $trow['typeid'];
		$arcRow['senddate'] = $trow['senddate'];
		$arcRow['title'] = '';
		$arcRow['ismake'] = 1;
		$arcRow['arcrank'] = $trow['corank'];
		$arcRow['namerule'] = $trow['namerule'];
		$arcRow['typedir'] = $trow['typedir'];
		$arcRow['money'] = 0;
		$arcRow['filename'] = '';
		$arcRow['moresite'] = $trow['moresite'];
		$arcRow['siteurl'] = $trow['siteurl'];
		$arcRow['sitepath'] = $trow['sitepath'];
	}
	$arcurl  = GetFileUrl($arcRow['id'],$arcRow['typeid'],$arcRow['senddate'],$arcRow['title'],$arcRow['ismake'],$arcRow['arcrank'],
	$arcRow['namerule'],$arcRow['typedir'],$arcRow['money'],$arcRow['filename'],$arcRow['moresite'],$arcRow['siteurl'],$arcRow['sitepath']);
	$arcfile = GetFileUrl($arcRow['id'],$arcRow['typeid'],$arcRow['senddate'],$arcRow['title'],
	$arcRow['ismake'],$arcRow['arcrank'],$arcRow['namerule'],$arcRow['typedir'],$arcRow['money'],$arcRow['filename']);
	if(eregi('^http:',$arcfile))
	{
		$arcfile = eregi_replace("^http://([^/]*)/",'/',$arcfile);
	}
	$truefile = GetTruePath().$arcfile;
	if(!file_exists($truefile))
	{
		MakeArt($aid,true);
	}
	echo "<script language='javascript'>location.href='$arcurl"."?".time()."';</script>";
	exit();
}

/*--------------------------
//�Ƽ��ĵ�
function commendArchives(){ }
---------------------------*/
else if($dopost=="commendArchives")
{
	CheckPurview('a_Commend,sys_ArcBatch');
	if( !empty($aid) && empty($qstr) )
	{
		$qstr = $aid;
	}
	if($qstr=='')
	{
		ShowMsg("������Ч��",$ENV_GOBACK_URL);
		exit();
	}
	$arcids = ereg_replace('[^0-9,]','',ereg_replace('`',',',$qstr));
	$query = "Select arc.id,arc.typeid,ch.issystem,ch.maintable,ch.addtable From `#@__arctiny` arc
           left join `#@__arctype` tp on tp.id=arc.typeid
           left join `#@__channeltype` ch on ch.id=tp.channeltype
          where arc.id in($arcids) ";
	$dsql->SetQuery($query);
	$dsql->Execute();
	while($row = $dsql->GetArray())
	{
		$aid = $row['id'];
		if($row['issystem']!=-1)
		{
			$maintable = ( trim($row['maintable'])=='' ? '#@__archives' : trim($row['maintable']) );
			$arr = $dsql->GetOne("Select flag From `{$maintable}` where id='$aid' ");
			$flag = ($arr['flag']=='' ? 'c' : $arr['flag'].',c');
			$dsql->ExecuteNoneQuery(" Update `{$maintable}` set `flag`='$flag' where id='{$aid}' ");
		}
		else
		{
			$maintable = trim($row['addtable']);
			$arr = $dsql->GetOne("Select flag From `{$maintable}` where aid='$aid' ");
			$flag = ($arr['flag']=='' ? 'c' : $arr['flag'].',c');
			$dsql->ExecuteNoneQuery(" Update `{$maintable}` set `flag`='$flag' where aid='{$aid}' ");
		}
	}
	ShowMsg("�ɹ�����ѡ���ĵ���Ϊ�Ƽ���",$ENV_GOBACK_URL);
	exit();
}

/*--------------------------
//����HTML
function makeArchives();
---------------------------*/
else if($dopost=="makeArchives")
{
	CheckPurview('sys_MakeHtml,sys_ArcBatch');
	if( !empty($aid) && empty($qstr) )
	{
		$qstr = $aid;
	}
	if($qstr=='')
	{
		ShowMsg('������Ч��',$ENV_GOBACK_URL);
		exit();
	}
	require_once(DEDEADMIN.'/inc/inc_archives_functions.php');
	$qstrs = explode('`',$qstr);
	$i = 0;
	foreach($qstrs as $aid)
	{
		$i++;
		$pageurl = MakeArt($aid,false);
	}
	ShowMsg("�ɹ�����ָ�� $i ���ļ�...",$ENV_GOBACK_URL);
	exit();
}

/*--------------------------
//����ĵ�
function checkArchives() {   }
---------------------------*/
else if($dopost=="checkArchives")
{
	CheckPurview('a_Check,a_AccCheck,sys_ArcBatch');
	require_once(DEDEADMIN."/inc/inc_archives_functions.php");
	if( !empty($aid) && empty($qstr) )
	{
		$qstr = $aid;
	}
	if($qstr=='')
	{
		ShowMsg("������Ч��",$ENV_GOBACK_URL);
		exit();
	}
	$arcids = ereg_replace('[^0-9,]','',ereg_replace('`',',',$qstr));
	$query = "Select arc.id,arc.typeid,ch.issystem,ch.maintable,ch.addtable From `#@__arctiny` arc
           	left join `#@__arctype` tp on tp.id=arc.typeid
            left join `#@__channeltype` ch on ch.id=tp.channeltype
            where arc.id in($arcids) ";
	$dsql->SetQuery($query);
	$dsql->Execute('ckall');
	while($row = $dsql->GetArray('ckall'))
	{
		$aid = $row['id'];
		//print_r($row);
		$maintable = ( trim($row['maintable'])=='' ? '#@__archives' : trim($row['maintable']) );
		$dsql->ExecuteNoneQuery("Update `#@__arctiny` set arcrank='0' where id='$aid' ");
		if($row['issystem']==-1)
		{
			$dsql->ExecuteNoneQuery("Update `".trim($row['addtable'])."` set arcrank='0' where aid='$aid' ");
		}
		else
		{
			$dsql->ExecuteNoneQuery("Update `$maintable` set arcrank='0' where id='$aid' ");
		}
		$pageurl = MakeArt($aid,false);
	}
	ShowMsg("�ɹ����ָ�����ĵ���",$ENV_GOBACK_URL);
	exit();
}

/*--------------------------
//ɾ���ĵ�
function delArchives(){ }
---------------------------*/
else if($dopost=="delArchives")
{
	CheckPurview('a_Del,a_AccDel,a_MyDel,sys_ArcBatch');
	require_once(DEDEINC."/oxwindow.class.php");
	if(empty($fmdo))
	{
		$fmdo = '';
	}

	//ȷ���h���������
	if($fmdo=='yes')
	{
		if( !empty($aid) && empty($qstr) )
		{
			$qstr = $aid;
		}
		if($qstr=='')
		{
			ShowMsg("������Ч��",$ENV_GOBACK_URL);
			exit();
		}
		$qstrs = explode("`",$qstr);
		$okaids = Array();

		foreach($qstrs as $aid)
		{
			if(!isset($okaids[$aid]))
			{
				DelArc($aid);
			}
			else
			{
				$okaids[$aid] = 1;
			}
		}
		ShowMsg("�ɹ�ɾ��ָ�����ĵ���",$ENV_GOBACK_URL);
		exit();
	}

	//ɾ��ȷ����ʾ
	else
	{
		$wintitle = "�ĵ�����-ɾ���ĵ�";
		$wecome_info = "<a href='".$ENV_GOBACK_URL."'>�ĵ�����</a>::ɾ���ĵ�";
		$win = new OxWindow();
		$win->Init("archives_do.php","js/blank.js","POST");
		$win->AddHidden("fmdo","yes");
		$win->AddHidden("dopost",$dopost);
		$win->AddHidden("qstr",$qstr);
		$win->AddHidden("aid",$aid);
		$win->AddTitle("��ȷʵҪɾ���� $qstr �� $aid ����Щ�ĵ���");
		$winform = $win->GetWindow("ok");
		$win->Display();
	}
}

/*-----------------------------
function moveArchives(){ }
------------------------------*/
else if($dopost=='moveArchives')
{
	CheckPurview('sys_ArcBatch');
	if(empty($totype))
	{
		require_once(DEDEINC."/oxwindow.class.php");
		require_once(DEDEINC."/typelink.class.php");
		$tl = new TypeLink($aid);
		$typeOptions = $tl->GetOptionArray(0,$cuserLogin->getUserChannel(),0);
		$typeOptions = "<select name='totype' style='width:350px'>
		<option value='0'>��ѡ���ƶ�����λ��...</option>\r\n
		$typeOptions
		</select>";
		$wintitle = "�ĵ�����-�ƶ��ĵ�";
		$wecome_info = "<a href='".$ENV_GOBACK_URL."'>�ĵ�����</a>::�ƶ��ĵ�";
		$win = new OxWindow();
		$win->Init("archives_do.php","js/blank.js","POST");
		$win->AddHidden("fmdo","yes");
		$win->AddHidden("dopost",$dopost);
		$win->AddHidden("qstr",$qstr);
		$win->AddHidden("aid",$aid);
		$win->AddTitle("��Ŀǰ�Ĳ������ƶ��ĵ�����ѡ��Ŀ����Ŀ��");
		$win->AddMsgItem($typeOptions,"30","1");
		$win->AddMsgItem("��ѡ�е��ĵ�ID�ǣ� $qstr <br>�ƶ�����Ŀ�����ѡ�����ĵ�Ƶ������һ�£����������Զ����Բ����ϵ��ĵ���","30","1");
		$winform = $win->GetWindow("ok");
		$win->Display();
	}
	else
	{
		$totype = ereg_replace('[^0-9]','',$totype);
		$typeInfos = $dsql->GetOne("Select tp.channeltype,tp.ispart,tp.channeltype,ch.maintable,ch.addtable From `#@__arctype` tp left join `#@__channeltype` ch on ch.id=tp.channeltype where tp.id='$totype' ");
		if(!is_array($typeInfos))
		{
			ShowMsg('��������','-1');
			exit();
		}
		if($typeInfos['ispart']!=0)
		{
			ShowMsg('�ĵ��������Ŀ����Ϊ�����б���Ŀ��','-1');
			exit();
		}
		if(empty($typeInfos['maintable']))
		{
			$typeInfos['maintable'] = '#@__archives';
		}
		$arcids = ereg_replace('[^0-9,]','',ereg_replace('`',',',$qstr));
		$arc = '';
		$j = 0;
		$okids = array();
		$dsql->SetQuery("Select id,typeid From `{$typeInfos['maintable']}` where id in($arcids) And channel='{$typeInfos['channeltype']}' ");
		$dsql->Execute();
		while($row = $dsql->GetArray())
		{
			if($row['typeid']!=$totype)
			{
				$dsql->ExecuteNoneQuery("Update `#@__arctiny`  Set typeid='$totype' where id='{$row['id']}' ");
				$dsql->ExecuteNoneQuery("Update `{$typeInfos['maintable']}` Set typeid='$totype' where id='{$row['id']}' ");
				$dsql->ExecuteNoneQuery("Update `{$typeInfos['addtable']}` Set typeid='$totype' where aid='{$row['id']}' ");
				$okids[] = $row['id'];
				$j++;
			}
		}
		//����HTML
		foreach($okids as $aid)
		{
			$arc = new Archives($aid);
			$arc->MakeHtml();
		}
		ShowMsg("�ɹ��ƶ� $j ���ĵ���",$ENV_GOBACK_URL);
		exit();
	}
}

//��ԭ�ĵ���
else if($dopost=='return')
{
	CheckPurview('a_Del,a_AccDel,a_MyDel,sys_ArcBatch');
	require_once(DEDEINC."/oxwindow.class.php");

	if( !empty($aid) && empty($qstr) )
	{
		$qstr = $aid;
	}
	if($qstr=='')
	{
		ShowMsg("������Ч��","recycling.php");
		exit();
	}
	$qstrs = explode("`",$qstr);
	foreach($qstrs as $aid)
	{
		$dsql->ExecuteNoneQuery("Update `#@__archives` set arcrank='-1',ismake='0' where id='$aid'");
		$dsql->ExecuteNoneQuery("Update `#@__arctiny` set `arcrank` = '-1' where id = '$aid'; ");
	}
	ShowMsg("�ɹ���ԭָ�����ĵ���","recycling.php");
	exit();
}

//����ĵ���
else if($dopost=='clear')
{
	CheckPurview('a_Del,a_AccDel,a_MyDel,sys_ArcBatch');
	require_once(DEDEINC."/oxwindow.class.php");
	if(empty($fmdo))
	{
		$fmdo = '';
	}

	//ȷ���h���������
	if($fmdo=='yes')
	{
		if( !empty($aid) && empty($qstr) )
		{
			$qstr = $aid;
		}
		if($qstr=='')
		{
			ShowMsg("������Ч��","recycling.php");
			exit();
		}
		$qstrs = explode(",",$qstr);
		$okaids = Array();
		foreach($qstrs as $aid)
		{
			if(!isset($okaids[$aid]))
			{
				DelArc($aid,"OK");
			}
			else
			{
				$okaids[$aid] = 1;
			}
		}
		ShowMsg("�ɹ�ɾ��ָ�����ĵ���","recycling.php");
		exit();
	}
	else
	{
		$dsql->SetQuery("SELECT id FROM `#@__archives` WHERE `arcrank` = '-2'");
		$dsql->Execute();
		$qstr = '';
		while($row = $dsql->GetArray())
		{
			$qstr .= $row['id'].",";
			$aid = $row['id'];
		}
		$num = $dsql->GetTotalRow();
		if(empty($num))
		{
			ShowMsg("�Բ���δ��������ĵ���","recycling.php");
			exit();
		}
		$wintitle = "�ĵ�����-��������ĵ�";
		$wecome_info = "<a href='recycling.php'>�ĵ�����վ</a>::��������ĵ�";
		$win = new OxWindow();
		$win->Init("archives_do.php","js/blank.js","POST");
		$win->AddHidden("fmdo","yes");
		$win->AddHidden("dopost",$dopost);
		$win->AddHidden("qstr",$qstr);
		$win->AddHidden("aid",$aid);
		$win->AddTitle("���β�������ջ���վ<font color='#FF0000'>���й� $num ƪ�ĵ�</font><br>��ȷʵҪ����ɾ���� $qstr ����Щ�ĵ���");
		$winform = $win->GetWindow("ok");
		$win->Display();
	}

}

//����ĵ���
else if($dopost=='del')
{
	CheckPurview('a_Del,a_AccDel,a_MyDel,sys_ArcBatch');
	require_once(DEDEINC."/oxwindow.class.php");
	if(empty($fmdo))
	{
		$fmdo = '';
	}

	//ȷ���h���������
	if($fmdo=='yes')
	{
		if( !empty($aid) && empty($qstr) )
		{
			$qstr = $aid;
		}
		if($qstr=='')
		{
			ShowMsg("������Ч��","recycling.php");
			exit();
		}
		$qstrs = explode("`",$qstr);
		$okaids = Array();

		foreach($qstrs as $aid)
		{
			if(!isset($okaids[$aid]))
			{
				DelArc($aid,"OK");
			}
			else
			{
				$okaids[$aid] = 1;
			}
		}
		ShowMsg("�ɹ�ɾ��ָ�����ĵ���","recycling.php");
		exit();
	}

	//ɾ��ȷ����ʾ
	else
	{
		$wintitle = "�ĵ�����-ɾ���ĵ�";
		$wecome_info = "<a href='recycling.php'>�ĵ�����</a>::ɾ���ĵ�";
		$win = new OxWindow();
		$win->Init("archives_do.php","js/blank.js","POST");
		$win->AddHidden("fmdo","yes");
		$win->AddHidden("dopost",$dopost);
		$win->AddHidden("qstr",$qstr);
		$win->AddHidden("aid",$aid);
		$win->AddTitle("��ȷʵҪ����ɾ���� $qstr �� $aid ����Щ�ĵ���");
		$winform = $win->GetWindow("ok");
		$win->Display();
	}
}
?>