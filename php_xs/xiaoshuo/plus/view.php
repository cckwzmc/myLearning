<?php

/**
 *
 * ��������Ȩ�����õ�˵��
 * ����Ȩ������������ʽ���£�
 * ���ָ���˻�Ա�ȼ�����ô���뵽������ȼ��������
 * ���ָ���˽�ң����ʱ���ָ��ĵ������������¼���û�ҵ���¼��
 * �������ͬʱָ������ô����ͬʱ������������
 *
 */

require_once(dirname(__FILE__)."/../include/common.inc.php");
require_once(DEDEINC.'/arc.archives.class.php');

$t1 = ExecTime();

if(empty($okview))
{
	$okview = '';
}

if(isset($arcID))
{
	$aid = $arcID;
}

$arcID = $aid = (isset($aid) && is_numeric($aid)) ? $aid : 0;
if($aid==0)
{
	die(" Request Error! ");
}

$arc = new Archives($aid);
if($arc->IsError)
{
	ParamError();
}

//����Ķ�Ȩ��
$needMoney = $arc->Fields['money'];
$needRank = $arc->Fields['arcrank'];

//������Ȩ�����Ƶ�����
//arctitle msgtitle moremsg
if($needMoney>0 || $needRank>1)
{
	require_once(DEDEINC.'/memberlogin.class.php');
	$ml = new MemberLogin();
	
	$arctitle = $arc->Fields['title'];
	
	$arclink = GetFileUrl($arc->ArcID,$arc->Fields["typeid"],$arc->Fields["senddate"],
	                         $arc->Fields["title"],$arc->Fields["ismake"],$arc->Fields["arcrank"]);
	
	$description =  $arc->Fields["description"];
	
	$pubdate = GetDateTimeMk($arc->Fields["pubdate"]);
	
	//��Ա������
	if(($needRank>1 && $ml->M_Rank < $needRank && $arc->Fields['mid']!=$ml->M_ID))
	{
		$dsql->Execute('me' , "Select * From `#@__arcrank` ");
		while($row = $dsql->GetObject('me'))
		{
			$memberTypes[$row->rank] = $row->membername;
		}
		$memberTypes[0] = "ע���Ա";
		$msgtitle = "û��Ȩ�ޣ�";
		$moremsg = "��ƪ�ĵ���Ҫ<font color='red'>".$memberTypes[$needRank]."</font>���ܷ��ʣ���Ŀǰ�ǣ�<font color='red'>".$memberTypes[$ml->M_Rank]."</font>";
		include_once($cfg_basedir.$cfg_templets_dir."/plus/view_msg.htm");
		exit();
	}

	//û���㹻�Ľ��
	if(($needMoney > $ml->M_Money  && $arc->Fields['mid']!=$ml->M_ID) || $ml->M_Money=='')
	{
		$msgtitle = "û��Ȩ�ޣ�";
		$moremsg = "��ƪ�ĵ���Ҫ <font color='red'>".$needMoney." ���</font> ���ܷ��ʣ���Ŀǰӵ�н�ң�<font color='red'>".$ml->M_Money." ��</font>";
		include_once($cfg_basedir.$cfg_templets_dir."/plus/view_msg.htm");
		$arc->Close();
		exit();
	}

	//����Ϊ����������Զ��۵���
	if($needMoney > 0  && $arc->Fields['mid']!=$ml->M_ID) //���������Ҫ��ң�����û��Ƿ���������ĵ�
	{
		$sql = "Select aid,money From `#@__member_operation` where buyid='ARCHIVE".$aid."' And mid='".$ml->M_ID."'";
		$row = $dsql->GetOne($sql);
		if(!is_array($row))
		{
		 	 $inquery = "INSERT INTO `#@__member_operation`(mid,oldinfo,money,mtime,buyid,product,pname)
		              VALUES ('".$ml->M_ID."','$arctitle','$needMoney','".time()."', 'ARCHIVE".$aid."', 'archive',''); ";
		 	 if($dsql->ExecuteNoneQuery($inquery))
		 	 {
		  		$inquery = "Update `#@__member` set money=money-$needMoney where mid='".$ml->M_ID."'";
		    		if(!$dsql->ExecuteNoneQuery($inquery))
				{
					showmsg('����ʧ��, �뷵��', -1);
					exit;
				}
			} else {
				showmsg('����ʧ��, �뷵��', -1);
				exit;
			}
		}
	}
}
$arc->Display();

?>