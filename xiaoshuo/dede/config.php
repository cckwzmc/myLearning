<?php
define('DEDEADMIN', ereg_replace("[/\\]{1,}",'/',dirname(__FILE__) ) );
require_once(DEDEADMIN."/../include/common.inc.php");
require_once(DEDEINC."/userlogin.class.php");
header("Cache-Control:private");
$dsql->safeCheck = false;
$dsql->SetLongLink();

//��õ�ǰ�ű����ƣ�������ϵͳ��������$_SERVER�����������и������ѡ��
$dedeNowurl = $s_scriptName = '';
$isUrlOpen = @ini_get("allow_url_fopen");
$dedeNowurl = GetCurUrl();
$dedeNowurls = explode('?',$dedeNowurl);
$s_scriptName = $dedeNowurls[0];

//�����û���¼״̬
$cuserLogin = new userLogin();
if($cuserLogin->getUserID()==-1)
{
	header("location:login.php?gotopage=".urlencode($dedeNowurl));
	exit();
}
if($cfg_dede_log=='Y')
{
	$s_nologfile = "_main|_list";
	$s_needlogfile = "sys_|file_";
	$s_method = isset($_SERVER['REQUEST_METHOD']) ? $_SERVER['REQUEST_METHOD'] : "";
	$s_query = isset($dedeNowurls[1]) ? $dedeNowurls[1] : "";
	$s_scriptNames = explode('/',$s_scriptName);
	$s_scriptNames = $s_scriptNames[count($s_scriptNames)-1];
	$s_userip = GetIP();
	if( $s_method=='POST' || (!eregi($s_nologfile,$s_scriptNames) && $s_query!='') || eregi($s_needlogfile,$s_scriptNames) )
	{
		$inquery = "INSERT INTO `#@__log`(adminid,filename,method,query,cip,dtime)
             VALUES ('".$cuserLogin->getUserID()."','{$s_scriptNames}','{$s_method}','".addslashes($s_query)."','{$s_userip}','".time()."');";
		$dsql->ExecuteNoneQuery($inquery);
	}
}
$cache1 = DEDEDATA."/cache/inc_catalog_base.inc";
if(!file_exists($cache1))
{
	UpDateCatCache();
}

//������Ŀ����
function UpDateCatCache()
{
	global $dsql,$cfg_multi_site;
	$cache1 = DEDEDATA."/cache/inc_catalog_base.inc";
	$dsql->SetQuery("Select id,reid,channeltype,issend From `#@__arctype`");
	$dsql->Execute();
	$fp1 = fopen($cache1,'w');
	$phph = '?';
	$fp1Header = "<{$phph}php\r\nglobal \$_Cs;\r\n\$_Cs=array();\r\n";
	$fp1Header.="\r\nglobal \$_Ts;\r\n\$_Ts=array();\r\n";
	fwrite($fp1,$fp1Header);
	while($row=$dsql->GetObject())
	{
		fwrite($fp1,"\$_Cs[{$row->id}]=array({$row->reid},{$row->channeltype},{$row->issend});\r\n");
	}
	$dsql->SetQuery("Select arc.id,reid,channeltype,issend From (select id,typeid from `#@__archives` t where t.flag like '%t%' and t.isbookpage is null ) 
	arc left join  `#@__arctype` tp on arc.typeid=tp.id ");
	$dsql->Execute();
	while($row=$dsql->GetObject())
	{
		fwrite($fp1,"\$_Ts[{$row->id}]=array({$row->reid},{$row->channeltype},{$row->issend});\r\n");
	}
	fwrite($fp1,"{$phph}>");
	fclose($fp1);
}


function DedeInclude($filename,$isabs=false)
{
	return $isabs ? $filename : DEDEADMIN.'/'.$filename;
}

?>