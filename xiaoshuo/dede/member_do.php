<?php
require_once(dirname(__FILE__)."/config.php");
require_once(DEDEINC."/oxwindow.class.php");
if(empty($dopost))
{
	$dopost = '';
}
if(empty($fmdo))
{
	$fmdo = '';
}
$ENV_GOBACK_URL = isset($_COOKIE['ENV_GOBACK_URL']) ? 'member_main.php' : '';

/*----------------
function __DelMember()
ɾ����Ա
----------------*/
if($dopost=="delmember")
{
	CheckPurview('member_Del');
	if($fmdo=='yes')
	{
		$id = ereg_replace('[^0-9]','',$id);
		$safecodeok = substr(md5($cfg_cookie_encode.$randcode),0,24);
		if($safecodeok!=$safecode)
		{
			ShowMsg("����д��ȷ�İ�ȫ��֤����","member_do.php?id={$id}&dopost=delmember");
			exit();
		}
		if(!empty($id))
		{
			//ɾ���û���Ϣ
			$rs = $dsql->ExecuteNoneQuery2("Delete From `#@__member` where mid='$id' And matt<>10 limit 1");
			if($rs > 0)
			{
				$dsql->ExecuteNoneQuery("Delete From `#@__member_tj` where mid='$id' limit 1");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_space` where mid='$id' limit 1");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_company` where mid='$id' limit 1");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_person` where mid='$id' limit 1");

				//ɾ���û��������
				$dsql->ExecuteNoneQuery("Delete From `#@__member_stow` where mid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_flink` where mid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_guestbook` where mid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_operation` where mid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_pms` where toid='$id' Or fromid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_friends` where mid='$id' Or fid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__member_vhistory` where mid='$id' Or vid='$id' ");
				$dsql->ExecuteNoneQuery("Delete From `#@__feedback` where mid='$id' ");
				$dsql->ExecuteNoneQuery("update `#@__archives` set mid='0' where mid='$id'");
			}
			else
			{
				ShowMsg("�޷�ɾ���˻�Ա����������Ա�ǹ���Ա������ID��<br />������ɾ���������Ա����ɾ�����ʺţ�",$ENV_GOBACK_URL,0,3000);
				exit();
			}
		}
		ShowMsg("�ɹ�ɾ��һ����Ա��",$ENV_GOBACK_URL);
		exit();
	}
	$randcode = mt_rand(10000,99999);
	$safecode = substr(md5($cfg_cookie_encode.$randcode),0,24);
	$wintitle = "��Ա����-ɾ����Ա";
	$wecome_info = "<a href='".$ENV_GOBACK_URL."'>��Ա����</a>::ɾ����Ա";
	$win = new OxWindow();
	$win->Init("member_do.php","js/blank.js","POST");
	$win->AddHidden("fmdo","yes");
	$win->AddHidden("dopost",$dopost);
	$win->AddHidden("id",$id);
	$win->AddHidden("randcode",$randcode);
	$win->AddHidden("safecode",$safecode);
	$win->AddTitle("��ȷʵҪɾ��(ID:".$id.")�����Ա?");
	$win->AddMsgItem("��ȫ��֤����<input name='safecode' type='text' id='safecode' size='16' style='width:200px' />&nbsp;(���Ʊ����룺 <font color='red'>$safecode</font> )","30");
	$winform = $win->GetWindow("ok");
	$win->Display();
}
/*----------------
function __UpRank()
��Ա����
----------------*/
else if($dopost=="uprank")
{
	CheckPurview('member_Edit');
	if($fmdo=="yes")
	{
		$id = ereg_replace('[^0-9]','',$id);
		$membertype = ereg_replace('[^0-9]','',$membertype);
		$dsql->ExecuteNoneQuery("update `#@__member` set rank='$membertype',uprank='0' where mid='$id'");
		ShowMsg("�ɹ�����һ����Ա�ȼ���",$ENV_GOBACK_URL);
		exit();
	}
	$MemberTypes = '';
	$dsql->SetQuery("Select rank,membername From `#@__arcrank` where rank>0");
	$dsql->Execute();
	$MemberTypes[0] = "���ƻ�Ա";
	while($row = $dsql->GetObject())
	{
		$MemberTypes[$row->rank] = $row->membername;
	}
	$options = "<select name='membertype' style='width:100px'>\r\n";
	foreach($MemberTypes as $k=>$v)
	{
		if($k!=$uptype)
		{
			$options .= "<option value='$k'>$v</option>\r\n";
		}
		else
		{
			$options .= "<option value='$k' selected>$v</option>\r\n";
		}
	}
	$options .= "</select>\r\n";
	$wintitle = "��Ա����-��Ա����";
	$wecome_info = "<a href='".$ENV_GOBACK_URL."'>��Ա����</a>::��Ա����";
	$win = new OxWindow();
	$win->Init("member_do.php","js/blank.js","POST");
	$win->AddHidden("fmdo","yes");
	$win->AddHidden("dopost",$dopost);
	$win->AddHidden("id",$id);
	$win->AddTitle("��Ա������");
	$win->AddItem("��ԱĿǰ�ĵȼ���",$MemberTypes[$mtype]);
	$win->AddItem("��Ա����ĵȼ���",$MemberTypes[$uptype]);
	$win->AddItem("��ͨ�ȼ���",$options);
	$winform = $win->GetWindow("ok");
	$win->Display();
}
/*----------------
function __Recommend()
�Ƽ���Ա
----------------*/
else if($dopost=="recommend")
{
	CheckPurview('member_Edit');
	$id = ereg_replace("[^0-9]","",$id);
	if($matt==0)
	{
		$dsql->ExecuteNoneQuery("update `#@__member` set matt=1 where mid='$id' And matt<>10 limit 1");
		ShowMsg("�ɹ�����һ����Ա�Ƽ���",$ENV_GOBACK_URL);
		exit();
	}
	else
	{
		$dsql->ExecuteNoneQuery("update `#@__member` set matt=0 where mid='$id' And matt<>10 limit 1");
		ShowMsg("�ɹ�ȡ��һ����Ա�Ƽ���",$ENV_GOBACK_URL);
		exit();
	}
}
/*----------------
function __AddMoney()
��Ա��ֵ
----------------*/
else if($dopost=="addmoney")
{
	CheckPurview('member_Edit');
	if($fmdo=="yes")
	{
		$id = ereg_replace('[^0-9]','',$id);
		$money = ereg_replace('[^0-9]','',$money);
		$dsql->ExecuteNoneQuery("update `#@__member` set money=money+$money,upmoney = 0 where mid='$id'");
		ShowMsg('�ɹ���һ����Ա��ֵ��',$ENV_GOBACK_URL);
		exit();
	}
	if(empty($upmoney))
	{
		$upmoney = 500;
	}
	$wintitle = "��Ա����-��Ա��ֵ";
	$wecome_info = "<a href='".$ENV_GOBACK_URL."'>��Ա����</a>::��Ա��ֵ";
	$win = new OxWindow();
	$win->Init("member_do.php","js/blank.js","POST");
	$win->AddHidden("fmdo","yes");
	$win->AddHidden("dopost",$dopost);
	$win->AddHidden("id",$id);
	$win->AddTitle("��Ա��ֵ��");
	$win->AddMsgItem("�������ֵ������<input type='text' name='money' size='10' value='$upmoney' />",60);
	$winform = $win->GetWindow("ok");
	$win->Display();
}
/*----------------
function __EditUser()
���Ļ�Ա
----------------*/
else if($dopost=="edituser")
{
	CheckPurview('member_Edit');
	if(!isset($_POST['id']))
	{
		exit("Request Error!");
	}
	if($spacesta>0)
	{
		$spacesta = '';
	}
	else
	{
		$spacesta = ",spacesta='$spacesta'";
	}
	if($pwd=='')
	{
		$spacesta = '';
	}
	else
	{
		$pwd = ",pwd='".md5($pwd)."'";
	}
	$query = "update `#@__member` set
			email = '$email',
			uname = '$uname',
			sex = '$sex',
			matt = '$matt',
			money = '$money',
			scores = '$scores',
			rank = '$rank'
			$pwd
			$spacesta
			where mid='$id' And matt<>10 ";
	$dsql->ExecuteNoneQuery($query);
	ShowMsg("�ɹ����Ļ�Ա���ϣ�",$ENV_GOBACK_URL);
	exit();
}
/*--------------
function __LoginCP()
��¼��Ա�Ŀ������
----------*/
else if($dopost=="memberlogin")
{
	CheckPurview('member_Edit');
	PutCookie('DedeUserID',$id,1800);
	PutCookie('DedeLoginTime',time(),1800);
	header("location:../member/index.php");
}
elseif($dopost == "upoperations")
{
	$nid = ereg_replace('[^0-9,]','',ereg_replace('`',',',$nid));
	$nid = explode(',',$nid);
	if(is_array($nid))
	{
		foreach ($nid as $var)
		{
			$query = "update `#@__member_operation` set sta = '1' where aid = '$var'";
			$dsql->ExecuteNoneQuery($query);
			ShowMsg("���óɹ���","member_operations.php");
			exit();
		}
	}
}
elseif($dopost == "okoperations")
{
	$nid = ereg_replace('[^0-9,]','',ereg_replace('`',',',$nid));
	$nid = explode(',',$nid);
	if(is_array($nid))
	{
		foreach ($nid as $var)
		{
			$query = "update `#@__member_operation` set sta = '2' where aid = '$var'";
			$dsql->ExecuteNoneQuery($query);
			ShowMsg("���óɹ���","member_operations.php");
			exit();
		}
	}
}
?>