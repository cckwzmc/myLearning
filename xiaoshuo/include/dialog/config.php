<?php
//��ҳ�����ڼ���û���¼���������Ҫ�ֹ�����ϵͳ���ã������common.inc.php
require_once(dirname(__FILE__)."/../common.inc.php");
require_once(dirname(__FILE__)."/../userlogin.class.php");

//��õ�ǰ�ű����ƣ�������ϵͳ��������$_SERVER�����������и������ѡ��
$dedeNowurl   =  '';
$s_scriptName = '';
$isUrlOpen = @ini_get('allow_url_fopen');

$dedeNowurl = GetCurUrl();
$dedeNowurls = explode("?",$dedeNowurl);
$s_scriptName = $dedeNowurls[0];




//�����û���¼״̬
$cuserLogin = new userLogin();
if($cuserLogin->getUserID()==-1)
{
	if($cuserLogin->adminDir=='')
	{
		exit('Request Error!');
	}
	$gurl = "../../{$cuserLogin->adminDir}/login.php?gotopage=".urlencode($dedeNowurl);
	echo "<script language='javascript'>location='$gurl';</script>";
	exit();
}

?>