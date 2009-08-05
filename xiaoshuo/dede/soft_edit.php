<?php
require_once(dirname(__FILE__)."/config.php");
CheckPurview('a_Edit,a_AccEdit,a_MyEdit');
require_once(DEDEINC."/customfields.func.php");
require_once(DEDEADMIN."/inc/inc_archives_functions.php");
if(empty($dopost))
{
	$dopost = '';
}
if($dopost!='save')
{
	require_once(DEDEADMIN."/inc/inc_catalog_options.php");
	require_once(DEDEINC."/dedetag.class.php");
	$aid = ereg_replace("[^0-9]",'',$aid);
	$channelid="3";

	//��ȡ�鵵��Ϣ
	$arcQuery = "Select
    #@__channeltype.typename as channelname,
    #@__arcrank.membername as rankname,
    #@__archives.*
    From #@__archives
    left join #@__channeltype on #@__channeltype.id=#@__archives.channel
    left join #@__arcrank on #@__arcrank.rank=#@__archives.arcrank
    where #@__archives.id='$aid'";
	$dsql->SetQuery($arcQuery);
	$arcRow = $dsql->GetOne($arcQuery);
	if(!is_array($arcRow))
	{
		ShowMsg("��ȡ����������Ϣ����!","-1");
		exit();
	}
	$query = "Select * From `#@__channeltype` where id='".$arcRow['channel']."'";
	$cInfos = $dsql->GetOne($query);
	if(!is_array($cInfos))
	{
		ShowMsg("��ȡƵ��������Ϣ����!","javascript:;");
		exit();
	}
	$addtable = $cInfos['addtable'];
	$addQuery = "Select * From `$addtable` where aid='$aid'";
	$addRow = $dsql->GetOne($addQuery);
	$newRowStart = 1;
	$nForm = '';
	if($addRow['softlinks']!='')
	{
		$dtp = new DedeTagParse();
		$dtp->LoadSource($addRow['softlinks']);
		if(is_array($dtp->CTags))
		{
			foreach($dtp->CTags as $ctag)
			{
				if($ctag->GetName()=='link')
				{
					$nForm .= "�����ַ".$newRowStart."��<input type='text' name='softurl".$newRowStart."' style='width:280' value='".trim($ctag->GetInnerText())."' />
            ���������ƣ�<input type='text' name='servermsg".$newRowStart."' value='".$ctag->GetAtt("text")."' style='width:150' />
            <br />";
					$newRowStart++;
				}
			}
		}
		$dtp->Clear();
	}
	$channelid = $arcRow['channel'];
	$tags = GetTags($aid);
	include DedeInclude("templets/soft_edit.htm");
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
  if(empty($typeid2)) $typeid2 = 0;
	if(!isset($autokey)) $autokey = 0;
	if(!isset($remote)) $remote = 0;
	if(!isset($dellink)) $dellink = 0;
	if(!isset($autolitpic)) $autolitpic = 0;

	if($typeid==0)
	{
		ShowMsg("��ָ���ĵ�����Ŀ��","-1");
		exit();
	}
	if(empty($channelid))
	{
		ShowMsg("�ĵ�Ϊ��ָ�������ͣ������㷢�����ݵı��Ƿ�Ϸ���","-1");
		exit();
	}
	if(!CheckChannel($typeid,$channelid))
	{
		ShowMsg("����ѡ�����Ŀ�뵱ǰģ�Ͳ��������ѡ���ɫ��ѡ�","-1");
		exit();
	}
	if(!TestPurview('a_Edit'))
	{
		if(TestPurview('a_AccEdit'))
		{
			CheckCatalog($typeid,"�Բ�����û�в�����Ŀ {$typeid} ���ĵ�Ȩ�ޣ�");
		}
		else
		{
			CheckArcAdmin($id,$cuserLogin->getUserID());
		}
	}

	//�Ա�������ݽ��д���
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

	//���������ӱ�����
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
					${$vs[0]} = GetFieldValueA(${$vs[0]},$vs[1],$id);
				}
				$inadd_f .= ",`{$vs[0]}` = '".${$vs[0]}."'";
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

	//������������
	$inQuery = "Update `#@__archives` set
	    typeid='$typeid',
	    typeid2='$typeid2',
	    sortrank='$sortrank',
	    flag='$flag',
	    ismake='$ismake',
	    arcrank='$arcrank',
	    money='$money',
	    title='$title',
	    color='$color',
	    source='$source',
	    writer='$writer',
	    litpic='$litpic',
	    pubdate='$pubdate',
	    description='$description',
	    keywords='$keywords',
	    shorttitle='$shorttitle',
	    filename='$filename'
	    where id='$id'; ";
	if(!$dsql->ExecuteNoneQuery($inQuery))
	{
		ShowMsg("�������ݿ�archives��ʱ�������飡","-1");
		exit();
	}

	//��������б�
	$urls = '';
	for($i=1;$i<=30;$i++)
	{
		if(!empty(${'softurl'.$i}))
		{
			$servermsg = str_replace("'",'',stripslashes(${'servermsg'.$i}));
			$softurl = stripslashes(${'softurl'.$i});
			if($servermsg=='')
			{
				$servermsg = '���ص�ַ'.$i;
			}
			if($softurl!='' && $softurl!='http://')
			{
				if($i==1) $urls .= "{dede:link islocal='1' text='{$servermsg1}'} $softurl1 {/dede:link}\r\n" ;
				else $urls .= "{dede:link text='$servermsg'} $softurl {/dede:link}\r\n";
			}
		}
	}
	$urls = addslashes($urls);

	//���¸��ӱ�
	$cts = $dsql->GetOne("Select addtable From `#@__channeltype` where id='$channelid' ");
	$addtable = trim($cts['addtable']);
	if($addtable!='')
	{
		$useip = GetIP();
		$inQuery = "update `$addtable`
	      set typeid ='$typeid',
	      filetype ='$filetype',
	      language ='$language',
	      softtype ='$softtype',
	      accredit ='$accredit',
	      os ='$os',
	      softrank ='$softrank',
	      officialUrl ='$officialUrl',
	      officialDemo ='$officialDemo',
	      softsize ='$softsize',
	      softlinks ='$urls',
	      introduce='$body'{$inadd_f},
	      redirecturl='$redirecturl',
	      userip = '$useip'
	      where aid='$id';";
		if(!$dsql->ExecuteNoneQuery($inQuery))
		{
			ShowMsg("�������ݿ⸽�ӱ� addonsoft ʱ��������ԭ��","-1");
			exit();
		}
	}

	//����HTML
	UpIndexKey($id,$arcrank,$typeid,$sortrank,$tags);
	$arcUrl = MakeArt($id,true);
	if($arcUrl=="")
	{
		$arcUrl = $cfg_phpurl."/view.php?aid=$id";
	}

	//���سɹ���Ϣ
	$msg = "
    ������ѡ����ĺ���������
    <a href='soft_add.php?cid=$typeid'><u>���������</u></a>
    &nbsp;&nbsp;
    <a href='archives_do.php?aid=".$id."&dopost=editArchives'><u>�����޸�</u></a>
    &nbsp;&nbsp;
    <a href='$arcUrl' target='_blank'><u>�鿴���</u></a>
    &nbsp;&nbsp;
    <a href='catalog_do.php?cid=$typeid&dopost=listArchives'><u>�ѷ����������</u></a>
    &nbsp;&nbsp;
    <a href='catalog_main.php'><u>��վ��Ŀ����</u></a>
    ";
	$wintitle = "�ɹ��޸�һ�������";
	$wecome_info = "���¹���::�޸����";
	$win = new OxWindow();
	$win->AddTitle("�ɹ��޸������");
	$win->AddMsgItem($msg);
	$winform = $win->GetWindow("hand","&nbsp;",false);
	$win->Display();
}

?>