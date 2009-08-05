<?php
require_once(dirname(__FILE__)."/../include/common.inc.php");
require_once(DEDEINC."/filter.inc.php");
if(!isset($action))
{
	$action = '';
}
//���ݾɵ�JS����
if($action == 'good' || $action == 'bad')
{
	if(!empty($aid)) $id = $aid;
	require_once(dirname(__FILE__).'/digg_ajax.php');
	exit();
}


$ischeck = $cfg_feedbackcheck=='Y' ? 0 : 1;
$aid = (isset($aid) && is_numeric($aid)) ? $aid : 0;
$fid = (isset($fid) && is_numeric($fid)) ? $fid : 0;
if(empty($aid) && empty($fid))
{
	ShowMsg('�ĵ�id����Ϊ��!','-1');
	exit();
}

include_once(DEDEINC."/memberlogin.class.php");
$cfg_ml = new MemberLogin();


if($action=='goodfb')
{
	AjaxHead();
	$fid = intval($fid);
	$dsql->ExecuteNoneQuery("Update `#@__feedback` set good = good+1 where id='$fid' ");
	$row = $dsql->GetOne("Select good From `#@__feedback` where id='$fid' ");
	echo "<a onclick=\"postBadGood('goodfb',{$aid})\">֧��</a>[{$row['good']}]";
	exit();
}
else if($action=='badfb')
{
	AjaxHead();
	$fid = intval($fid);
	$dsql->ExecuteNoneQuery("Update `#@__feedback` set bad = bad+1 where id='$fid' ");
	$row = $dsql->GetOne("Select bad From `#@__feedback` where id='$fid' ");
	echo "<a onclick=\"postBadGood('badfb',{$aid})\">����</a>[{$row['bad']}]";
	exit();
}
//�鿴����
/*
function __ViewFeedback(){ }
*/
//-----------------------------------
else if($action=='' || $action=='show')
{
	//��ȡ�ĵ���Ϣ
	$arcRow = GetOneArchive($aid);
	if(empty($arcRow['aid']))
	{
		ShowMsg('�޷��鿴δ֪�ĵ�������!','-1');
		exit();
	}
	extract($arcRow, EXTR_SKIP);
	include_once(DEDEINC.'/datalistcp.class.php');
	$dlist = new DataListCP();
	$dlist->pageSize = 20;

	if(empty($ftype) || ($ftype!='good' && $ftype!='bad' && $ftype!='feedback'))
	{
		$ftype = '';
	}
	$wquery = $ftype!='' ? " And ftype like '$ftype' " : '';

	//���������б�
	$querystring = "select fb.*,mb.userid,mb.face as mface,mb.spacesta,mb.scores from `#@__feedback` fb
                 left join `#@__member` mb on mb.mid = fb.mid
                 where fb.aid='$aid' and fb.ischeck='1' $wquery order by fb.id desc";
	$dlist->SetParameter('aid',$aid);
	$dlist->SetParameter('action','show');
	$dlist->SetTemplate($cfg_basedir.$cfg_templets_dir.'/plus/feedback_templet.htm');
	$dlist->SetSource($querystring);
	$dlist->Display();
	exit();
}

//��������
//------------------------------------
/*
function __Quote(){ }
*/
else if($action=='quote')
{
	$row = $dsql->GetOne("Select * from `#@__feedback` where id ='$fid'");
	require_once(DEDEINC.'/dedetemplate.class.php');
	$dtp = new DedeTemplate();
	$dtp->LoadTemplate($cfg_basedir.$cfg_templets_dir.'/plus/feedback_quote.htm');
	$dtp->Display();
	exit();
}
//��������
//------------------------------------
/*
function __SendFeedback(){ }
*/
else if($action=='send')
{
	//��ȡ�ĵ���Ϣ
	$arcRow = GetOneArchive($aid);
	if((empty($arcRow['aid']) || $arcRow['notpost']=='1')&&empty($fid))
	{
		ShowMsg('�޷��Ը��ĵ���������!','-1');
		exit();
	}

	//�Ƿ����֤����ȷ��
	if(empty($isconfirm))
	{
		$isconfirm = '';
	}
	if($isconfirm!='yes' && $cfg_feedback_ck=='Y')
	{
		extract($arcRow, EXTR_SKIP);
		require_once(DEDEINC.'/dedetemplate.class.php');
		$dtp = new DedeTemplate();
		$dtp->LoadTemplate($cfg_basedir.$cfg_templets_dir.'/plus/feedback_confirm.htm');
		$dtp->Display();
		exit();
	}
	//�����֤��
	if($cfg_feedback_ck=='Y')
	{
		$validate = isset($validate) ? strtolower(trim($validate)) : '';
		$svali = strtolower(trim(GetCkVdValue()));
		if($validate != $svali || $svali=='')
		{
			ResetVdValue();
			ShowMsg('��֤�����','-1');
			exit();
		}
	}

	//����û���¼
	if(empty($notuser))
	{
		$notuser=0;
	}

	//������������
	if($notuser==1)
	{
		$username = $cfg_ml->M_ID > 0 ? '����' : '�ο�';
	}

	//�ѵ�¼���û�
	else if($cfg_ml->M_ID > 0)
	{
		$username = $cfg_ml->M_UserName;
	}

	//�û������֤
	else
	{
		if($username!='' && $pwd!='')
		{
			$rs = $cfg_ml->CheckUser($username,$pwd);
			if($rs==1)
			{
				$dsql->ExecuteNoneQuery("Update `#@__member` set logintime='".time()."',loginip='".GetIP()."' where mid='{$cfg_ml->M_ID}'; ");
			}
			else
			{
				$username = '�ο�';
			}
		}
		else
		{
			$username = '�ο�';
		}
	}
	$ip = GetIP();
	$dtime = time();
	
	//������ۼ��ʱ�䣻
	if(!empty($cfg_feedback_time))
	{
		//�����󷢱�����ʱ�䣬���δ��½�жϵ�ǰIP�������ʱ��
		if($cfg_ml->M_ID > 0)
		{
			$where = "WHERE `mid` = '$cfg_ml->M_ID'";
		}
		else
		{
			$where = "WHERE `ip` = '$ip'";
		}
		$row = $dsql->GetOne("SELECT dtime FROM `#@__feedback` $where ORDER BY `id` DESC ");
		if($dtime - $row['dtime'] < $cfg_feedback_time)
		{
			ResetVdValue();
			ShowMsg('����Ա���������ۼ��ʱ�䣬���Ե���Ϣһ�£�','-1');
			exit();
		}
	}

	if(empty($face))
	{
		$face = 0;
	}
	$face = intval($face);
	extract($arcRow, EXTR_SKIP);
	$msg = cn_substrR(TrimMsg($msg),1000);
	$username = cn_substrR(HtmlReplace($username,2),20);
	if($feedbacktype!='good' && $feedbacktype!='bad')
	{
		$feedbacktype = 'feedback';
	}
	//������������
	if($comtype == 'comments')
	{
		$arctitle = addslashes($title);
		if($msg!='')
		{
			$inquery = "INSERT INTO `#@__feedback`(`aid`,`typeid`,`username`,`arctitle`,`ip`,`ischeck`,`dtime`, `mid`,`bad`,`good`,`ftype`,`face`,`msg`)
	               VALUES ('$aid','$typeid','$username','$arctitle','$ip','$ischeck','$dtime', '{$cfg_ml->M_ID}','0','0','$feedbacktype','$face','$msg'); ";
			$rs = $dsql->ExecuteNoneQuery($inquery);
			if(!$rs)
			{
				echo $dsql->GetError();
				exit();
			}
		}
	}
	//���ûظ�
	elseif ($comtype == 'reply')
	{
		$row = $dsql->GetOne("Select * from `#@__feedback` where id ='$fid'");
		$arctitle = $row['arctitle'];
		$aid =$row['aid'];
		$msg = $quotemsg.$msg;
		$msg = HtmlReplace($msg,2);
		$inquery = "INSERT INTO `#@__feedback`(`aid`,`typeid`,`username`,`arctitle`,`ip`,`ischeck`,`dtime`,`mid`,`bad`,`good`,`ftype`,`face`,`msg`)
				VALUES ('$aid','$typeid','$username','$arctitle','$ip','$ischeck','$dtime','{$cfg_ml->M_ID}','0','0','$feedbacktype','$face','$msg')";
		$dsql->ExecuteNoneQuery($inquery);
	}

	if($feedbacktype=='bad')
	{
		$dsql->ExecuteNoneQuery("Update `#@__archives` set scores=scores-{cfg_feedback_sub},badpost=badpost+1,lastpost='$dtime' where id='$aid' ");
	}
	else if($feedbacktype=='good')
	{
		$dsql->ExecuteNoneQuery("Update `#@__archives` set scores=scores+{$cfg_feedback_add},goodpost=goodpost+1,lastpost='$dtime' where id='$aid' ");
	}
	else
	{
		$dsql->ExecuteNoneQuery("Update `#@__archives` set scores=scores+1,lastpost='$dtime' where id='$aid' ");
	}
	if($cfg_ml->M_ID > 0)
	{
		$dsql->ExecuteNoneQuery("Update `#@__member` set scores=scores+{$cfg_sendfb_scores} where mid='{$cfg_ml->M_ID}' ");
	}
	//ͳ���û�����������
	if($cfg_ml->M_ID > 0)
	{
		$row = $dsql->GetOne("SELECT COUNT(*) AS nums FROM `#@__feedback` WHERE `mid`='".$cfg_ml->M_ID."'");
		$dsql->ExecuteNoneQuery("UPDATE `#@__member_tj` SET `feedback`='$row[nums]' WHERE `mid`='".$cfg_ml->M_ID."'");
	}
	$_SESSION['sedtime'] = time();
	if($ischeck==0)
	{
		ShowMsg("�ɹ��������ۣ�������˺�Ż���ʾ�������!","feedback.php?aid=$aid");
	}elseif($ischeck==1)
	{
		ShowMsg("�ɹ��������ۣ�����ת������ҳ��!","feedback.php?aid=$aid");
	}
	exit();
}
?>