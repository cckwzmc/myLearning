<?php
require_once(dirname(__FILE__)."/../include/common.inc.php");
require_once(DEDEINC."/channelunit.class.php");
if(!isset($action)) $action = '';

if(isset($arcID)) $aid = $arcID;
$arcID = $aid = (isset($aid) && is_numeric($aid) ? $aid : 0);

if(empty($aid)) {
	  ShowMsg("�ĵ�ID����Ϊ��!","-1");
	  exit();
}

//��ȡ�ĵ���Ϣ
if($action=='')
{
  //��ȡ�ĵ���Ϣ
  $arcRow = GetOneArchive($aid);
  if($arcRow['aid']=='') {
	   ShowMsg("�޷���δ֪�ĵ��Ƽ�������!","-1");
	   exit();
  }
  extract($arcRow, EXTR_SKIP);
}
//�����Ƽ���Ϣ
//-----------------------------------
else if($action=='send')
{
	if(!eregi("^[0-9a-z][a-z0-9\.-]{1,}@[a-z0-9-]{1,}[a-z]\.[a-z\.]{1,}[a-z]$",$email))
  {
	  echo "<script>alert('Email��ʽ����ȷ!');history.go(-1);</script>";
	  exit();
  }
  $mailbody = '';
  $msg = htmlspecialchars($msg);
  $mailtitle = "��ĺ��Ѹ����Ƽ���һƪ����";
  $mailbody .= "$msg \r\n\r\n";
  $mailbody .= "Power by http://www.dedecms.com ֯�����ݹ���ϵͳ��";

	$headers = "From: ".$cfg_adminemail."\r\nReply-To: ".$cfg_adminemail;
	
	if($cfg_sendmail_bysmtp == 'Y' && !empty($cfg_smtp_server))
	{		
		$mailtype = 'TXT';
		require_once(DEDEINC.'/mail.class.php');
		$smtp = new smtp($cfg_smtp_server,$cfg_smtp_port,true,$cfg_smtp_usermail,$cfg_smtp_password);
		$smtp->debug = false;
		$smtp->sendmail($email, $cfg_smtp_usermail, $mailtitle, $mailbody, $mailtype);
	}
	else
	{
		@mail($email, $mailtitle, $mailbody, $headers);
	}

  ShowMsg("�ɹ��Ƽ�һƪ����!",$arcurl);
  exit();
}

//��ʾģ��(��PHP�ļ�)
include($cfg_basedir.$cfg_templets_dir.'/plus/recommend.htm');

?>