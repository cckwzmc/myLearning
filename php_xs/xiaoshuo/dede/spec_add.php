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
	$channelid = -1;
	$cid = isset($cid) && is_numeric($cid) ? $cid : 0;

	//���Ƶ��ģ����Ϣ
	$cInfos = $dsql->GetOne(" Select * From  `#@__channeltype` where id='$channelid' ");
	include DedeInclude("templets/spec_add.htm");
	exit();
}

/*--------------------------------
function __save(){  }
-------------------------------*/
else if($dopost=='save')
{
	require_once(DEDEINC.'/image.func.php');
	require_once(DEDEINC.'/oxwindow.class.php');
	if(!isset($flags))
	{
		$flag = '';
	}
	else
	{
		$flag = join(',',$flags);
	}
	$channelid= -1;
	$money = 0;
	if(!isset($tags))
	{
		$tags = '';
	}

	//�����Զ����ֶλ��õ���Щ����
	if(!isset($autokey))
	{
		$autokey = 0;
	}
	if(!isset($remote))
	{
		$remote = 0;
	}
	if(!isset($dellink))
	{
		$dellink = 0;
	}
	if(!isset($autolitpic))
	{
		$autolitpic = 0;
	}
	if($typeid==0)
	{
		ShowMsg('��ָ���ĵ�����Ŀ��','-1');
		exit();
	}
	if(!TestPurview('a_New'))
	{
		CheckCatalog($typeid,"�Բ�����û�в�����Ŀ {$typeid} ��Ȩ�ޣ�");
		exit();
	}

	//�Ա�������ݽ��д���
	if(empty($writer))$writer=$cuserLogin->getUserName();
	if(empty($source))$source='δ֪';
	$pubdate = GetMkTime($pubdate);
	$senddate = time();
	$sortrank = AddDay($pubdate,$sortup);
	if($ishtml==0)
	{
		$ismake = -1;
	}
	else
	{
		$ismake = 0;
	}

	$title = cn_substrR($title,$cfg_title_maxlen);
	$shorttitle = cn_substrR($shorttitle,36);
	$color =  cn_substrR($color,7);
	$writer =  cn_substrR($writer,20);
	$source = cn_substrR($source,30);
	$description = cn_substrR($description,250);
	$keywords = cn_substrR($keywords,30);
	$filename = trim(cn_substrR($filename,40));
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
		ShowMsg("�޷�����������޷����к���������","-1");
		exit();
	}

	//���浽����
	$inQuery = "INSERT INTO `#@__archives`(id,typeid,sortrank,flag,ismake,channel,arcrank,click,money,title,shorttitle,
    color,writer,source,litpic,pubdate,senddate,mid,description,keywords,filename)
    VALUES ('$arcID','$typeid','$sortrank','$flag','$ismake','$channelid','$arcrank','0','$money','$title','$shorttitle',
    '$color','$writer','$source','$litpic','$pubdate','$senddate','$adminid','$description','$keywords','$filename');";
	if(!$dsql->ExecuteNoneQuery($inQuery))
	{
		echo $inQuery;
		$gerr = $dsql->GetError();
		$dsql->ExecuteNoneQuery("Delete From `#@__arctiny` where id='$arcID'");
		ShowMsg("�����ݱ��浽���ݿ����� `#@__archives` ʱ��������������Ϣ�ύ��DedeCms�ٷ���".str_replace('"','',$gerr),"javascript:;");
		exit();
	}

	//ר��ڵ��б�
	$arcids = '';
	$notelist = '';
	for($i=1;$i<=$cfg_specnote;$i++)
	{
		if(!empty(${'notename'.$i}))
		{
			$notename = str_replace("'","",trim(${'notename'.$i}));
			$arcid = trim(${'arcid'.$i});
			$col = trim(${'col'.$i});
			$imgwidth = trim(${'imgwidth'.$i});
			$imgheight = trim(${'imgheight'.$i});
			$titlelen = trim(${'titlelen'.$i});
			$infolen = trim(${'infolen'.$i});
			$listtmp = trim(${'listtmp'.$i});
			$noteid = trim(${'noteid'.$i});
			$isauto = trim(${'isauto'.$i});
			$keywords = str_replace("'","",trim(${'keywords'.$i}));
			$typeid = trim(${'typeid'.$i});
			if(!empty(${'rownum'.$i}))
			{
				$rownum = trim(${'rownum'.$i});
			}
			else
			{
				$rownum = 0;
			}
			$arcid = ereg_replace("[^0-9,]","",$arcid);
			$ids = explode(",",$arcid);
			$okids = "";
			if(is_array($ids))
			{
				foreach($ids as $mid)
				{
					$mid = trim($mid);
					if($mid=="")
					{
						continue;
					}
					if(!isset($arcids[$mid]))
					{
						if($okids=="")
						{
							$okids .= $mid;
						}
						else
						{
							$okids .= ",".$mid;
						}
						$arcids[$mid] = 1;
					}
				}
			}
			$notelist .= "{dede:specnote imgheight=\\'$imgheight\\' imgwidth=\\'$imgwidth\\'
				infolen=\\'$infolen\\' titlelen=\\'$titlelen\\' col=\\'$col\\' idlist=\\'$okids\\'
				name=\\'$notename\\' noteid=\\'$noteid\\' isauto=\'$isauto\' rownum=\\'$rownum\\'
				keywords=\\'$keywords\\' typeid=\\'$typeid\\'}
				$listtmp
				{/dede:specnote}\r\n";
		}
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
				}
				else
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
	$useip = GetIP();
	//���븽�ӱ�
	$inQuery = "INSERT INTO `#@__addonspec`(aid,typeid,userip,templet,note{$inadd_f}) VALUES ('$arcID','$typeid','$useip','$templet','$notelist'{$inadd_v});";
	if(!$dsql->ExecuteNoneQuery($inQuery))
	{
		$dsql->ExecuteNoneQuery("Delete From `#@__arctiny` where id='$arcID'");
		$dsql->ExecuteNoneQuery("Delete From `#@__archives` where id='$arcID'");
		ShowMsg("�����ݱ��浽���ݿ⸽�ӱ� addonspec ʱ����������ԭ��","-1");
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
    <a href='spec_add.php?cid=$typeid'><u>������ר��</u></a>
    &nbsp;&nbsp;
    <a href='$artUrl' target='_blank'><u>�鿴ר��</u></a>
    &nbsp;&nbsp;
    <a href='content_s_list.php'><u>�ѷ���ר�����</u></a>
    ";
	$wintitle = "�ɹ�����ר�⣡";
	$wecome_info = "���¹���::����ר��";
	$win = new OxWindow();
	$win->AddTitle("�ɹ�����ר�⣺");
	$win->AddMsgItem($msg);
	$winform = $win->GetWindow("hand","&nbsp;",false);
	$win->Display();
}

?>