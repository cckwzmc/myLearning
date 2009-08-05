<?php
require_once(dirname(__FILE__)."/config.php");
CheckPurview('a_New,a_AccNew');
require_once(DEDEINC."/customfields.func.php");
require_once(DEDEADMIN."/inc/inc_archives_functions.php");
if(empty($dopost))
{
	$dopost = '';
}
if($dopost!='save')
{
	require_once(DEDEINC."/dedetag.class.php");
	require_once(DEDEADMIN."/inc/inc_catalog_options.php");
	$channelid = empty($channelid) ? 0 : intval($channelid);
	$cid = empty($cid) ? 0 : intval($cid);

	if(empty($geturl)) $geturl = '';
	
	$keywords = $writer = $source = $body = $description = $title = '';

	//�ɼ�������ҳ
	if(ereg('^http://',$geturl))
	{
		require_once(DEDEADMIN."/inc/inc_coonepage.php");
		$redatas = CoOnePage($geturl);
		extract($redatas);
	}

	//���Ƶ��ģ��ID
	if($cid>0 && $channelid==0)
	{
		$row = $dsql->GetOne("Select channeltype From `#@__arctype` where id='$cid'; ");
		$channelid = $row['channeltype'];
	}
	else
	{
		if($channelid==0)
		{
			$channelid = 1;
		}
	}

	//���Ƶ��ģ����Ϣ
	$cInfos = $dsql->GetOne(" Select * From  `#@__channeltype` where id='$channelid' ");
	include DedeInclude("templets/article_add.htm");
	exit();
}

/*--------------------------------
function __save(){  }
-------------------------------*/
else if($dopost=='save')
{
	require_once(DEDEINC.'/image.func.php');
	require_once(DEDEINC.'/oxwindow.class.php');
	$flag = isset($flags) ? join(',',$flags) : '';
	$notpost = isset($notpost) && $notpost == 1 ? 1: 0;
	
	if(empty($typeid2)) $typeid2 = 0;
	if(!isset($autokey)) $autokey = 0;
	if(!isset($remote)) $remote = 0;
	if(!isset($dellink)) $dellink = 0;
	if(!isset($autolitpic)) $autolitpic = 0;
	
	if(empty($typeid))
	{
		ShowMsg("��ָ���ĵ�����Ŀ��","-1");
		exit();
	}
	if(empty($xstypeid)&&strpos($flag, "x"))
	{
		ShowMsg("��ָ���ĵ�����Ŀ��","-1");
		exit();
	}
	if(empty($channelid))
	{
		ShowMsg("�ĵ�Ϊ��ָ�������ͣ������㷢�����ݵı����Ƿ�Ϸ���","-1");
		exit();
	}
	if(!CheckChannel($typeid,$channelid))
	{
		ShowMsg("����ѡ�����Ŀ�뵱ǰģ�Ͳ��������ѡ���ɫ��ѡ�","-1");
		exit();
	}
	if(!TestPurview('a_New'))
	{
		CheckCatalog($typeid,"�Բ�����û�в�����Ŀ {$typeid} ��Ȩ�ޣ�");
	}

	//�Ա�������ݽ��д���
	if(empty($writer))$writer=$cuserLogin->getUserName();
	if(empty($source))$source='δ֪';
	$pubdate = GetMkTime($pubdate);
	$senddate = time();
	$sortrank = AddDay($pubdate,$sortup);
	$ismake = $ishtml==0 ? -1 : 0;
	$title = htmlspecialchars(cn_substrR($title,$cfg_title_maxlen));
	$shorttitle = cn_substrR($shorttitle,36);
	$color =  cn_substrR($color,7);
	$writer =  cn_substrR($writer,20);
	$source = cn_substrR($source,30);
	$description = cn_substrR($description,250);
	$keywords = cn_substrR($keywords,30);
	$filename = trim(cn_substrR($filename,40));
	$userip = GetIP();
	

	if(!TestPurview('a_Check,a_AccCheck,a_MyCheck'))
	{
		$arcrank = -1;
	}
	$adminid = $cuserLogin->getUserID();

	//�����ϴ�������ͼ
	if(empty($ddisremote))
	{
		$ddisremote = 0;
	}
	$litpic = GetDDImage('litpic',$picname,$ddisremote);

	//�����ĵ�ID
	$arcID = GetIndexKey($arcrank,$typeid,$sortrank,$channelid,$senddate,$adminid);
	if(empty($arcID))
	{
		ShowMsg("�޷��������������޷����к���������","-1");
		exit();
	}
	if(trim($title) == '')
	{
		ShowMsg('���ⲻ��Ϊ��', '-1');
		exit();
	}

	//����body�ֶ��Զ�ժҪ���Զ���ȡ����ͼ��
	$body = AnalyseHtmlBody($body,$description,$litpic,$keywords,'htmltext');

	//�Զ���ҳ
	if($sptype=='auto')
	{
		$body = SpLongBody($body,$spsize*1024,"#p#��ҳ����#e#");
	}

	//�����������ӱ�����
	$inadd_f = '';
	$inadd_v = '';
	if(!empty($dede_addonfields))
	{
		$addonfields = explode(';',$dede_addonfields);
		$inadd_f = '';
		$inadd_v = '';
		if(is_array($addonfields))
		{
			foreach($addonfields as $v)
			{
				if($v=='')
				{
					continue;
				}
				$vs = explode(',',$v);
				if($vs[1]=='htmltext'||$vs[1]=='textdata') //HTML�ı����⴦��
				{
					${$vs[0]} = AnalyseHtmlBody(${$vs[0]},$description,$litpic,$keywords,$vs[1]);
				}else
				{
					if(!isset(${$vs[0]}))
					{
						${$vs[0]} = '';
					}
					${$vs[0]} = GetFieldValueA(${$vs[0]},$vs[1],$arcID);
				}
				$inadd_f .= ','.$vs[0];
				$inadd_v .= " ,'".${$vs[0]}."' ";
			}
		}
	}

	//����ͼƬ�ĵ����Զ�������
	if($litpic!='' && !ereg('p',$flag))
	{
		$flag = ($flag=='' ? 'p' : $flag.',p');
	}
	if($redirecturl!='' && !ereg('j',$flag))
	{
		$flag = ($flag=='' ? 'j' : $flag.',j');
	}

	//���浽����
	//ע��flag�Ѿ��ĳ�С˵ר��
	if(!empty($xstypeid)){
		$isbookpage='';
		if(!strpos($flag,'t')){
			$isbookpage=$xstypeid;
		}
		if(!empty($flag)){
			$flag.=",x";	
		}else{
			$flag="x";
		}
		$query = "INSERT INTO `#@__archives`(id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,
			    color,writer,source,litpic,pubdate,senddate,mid,notpost,description,keywords,filename,isbookpage,chinesenum)
			    VALUES ('$arcID','$typeid','$typeid2','$sortrank','$flag','$ismake','$channelid','$arcrank','0','$money',
			    '$title','$shorttitle','$color','$writer','$source','$litpic','$pubdate','$senddate',
			    '$adminid','$notpost','$description','$keywords','$filename','$isbookpage','$chinesenum');";
	}else{
		if(!empty($flag)){
			$flag.=",t";	
		}else{
			$flag="t";
		}
		$tempquery="";
		if(empty($xstypeid)){
			$tempquery="isbookpage=NULL";
		}else{
			$tempquery="isbookpage='$xstypeid'";
		}
		$query = "INSERT INTO `#@__archives`(id,typeid,typeid2,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,
	    color,writer,source,litpic,pubdate,senddate,mid,notpost,description,keywords,filename,isbookpage,chinesenum)
	    VALUES ('$arcID','$typeid','$typeid2','$sortrank','$flag','$ismake','$channelid','$arcrank','0','$money',
	    '$title','$shorttitle','$color','$writer','$source','$litpic','$pubdate','$senddate',
	    '$adminid','$notpost','$description','$keywords','$filename',".$tempquery.",'$chinesenum');";
	}	
	if(!$dsql->ExecuteNoneQuery($query))
	{
		$gerr = $dsql->GetError();
		$dsql->ExecuteNoneQuery("Delete From `#@__arctiny` where id='$arcID'");
		ShowMsg("�����ݱ��浽���ݿ����� `#@__archives` ʱ��������������Ϣ�ύ��DedeCms�ٷ���".str_replace('"','',$gerr),"javascript:;");
		exit();
	}

	//���浽���ӱ�
	$cts = $dsql->GetOne("Select addtable From `#@__channeltype` where id='$channelid' ");
	$addtable = trim($cts['addtable']);
	if(empty($addtable))
	{
		$dsql->ExecuteNoneQuery("Delete From `#@__archives` where id='$arcID'");
		$dsql->ExecuteNoneQuery("Delete From `#@__arctiny` where id='$arcID'");
		ShowMsg("û�ҵ���ǰģ��[{$channelid}]��������Ϣ���޷���ɲ�������","javascript:;");
		exit();
	}
	$useip = GetIP();
	$query = "INSERT INTO `{$addtable}`(aid,typeid,redirecturl,userip,body{$inadd_f}) Values('$arcID','$typeid','$redirecturl','$useip','$body'{$inadd_v})";
	if(!$dsql->ExecuteNoneQuery($query))
	{
		$gerr = $dsql->GetError();
		$dsql->ExecuteNoneQuery("Delete From `#@__archives` where id='$arcID'");
		$dsql->ExecuteNoneQuery("Delete From `#@__arctiny` where id='$arcID'");
		ShowMsg("�����ݱ��浽���ݿ⸽�ӱ� `{$addtable}` ʱ��������������Ϣ�ύ��DedeCms�ٷ���".str_replace('"','',$gerr),"javascript:;");
		exit();
	}

	//����HTML
	InsertTags($tags,$arcID);
	$artUrl = MakeArt($arcID,true,true);
	if($artUrl=='')
	{
		$artUrl = $cfg_phpurl."/view.php?aid=$arcID";
	}

	//���سɹ���Ϣ
	$msg = "
    ������ѡ����ĺ���������
    <a href='article_add.php?cid=$typeid'><u>������������</u></a>
    &nbsp;&nbsp;
    <a href='$artUrl' target='_blank'><u>�鿴����</u></a>
    &nbsp;&nbsp;
    <a href='archives_do.php?aid=".$arcID."&dopost=editArchives'><u>��������</u></a>
    &nbsp;&nbsp;
    <a href='catalog_do.php?cid=$typeid&dopost=listArchives'><u>�ѷ������¹���</u></a>
    &nbsp;&nbsp;
    <a href='catalog_main.php'><u>��վ��Ŀ����</u></a>
    ";
	$wintitle = "�ɹ��������£�";
	$wecome_info = "���¹���::��������";
	$win = new OxWindow();
	$win->AddTitle("�ɹ��������£�");
	$win->AddMsgItem($msg);
	$winform = $win->GetWindow("hand","&nbsp;",false);
	$win->Display();
}

?>