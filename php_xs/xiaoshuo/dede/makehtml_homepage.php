<?php
require_once(dirname(__FILE__)."/config.php");
CheckPurview('sys_MakeHtml');
require_once(DEDEINC."/arc.partview.class.php");
if(empty($dopost))
{
	$dopost = '';
}
if($dopost=="view")
{
	$pv = new PartView();
	$templet = str_replace("{style}",$cfg_df_style,$templet);
	$pv->SetTemplet($cfg_basedir.$cfg_templets_dir."/".$templet);
	$pv->Display();
	exit();
}
else if($dopost=="make")
{
	$homeFile = DEDEADMIN."/".$position;
	$homeFile = str_replace("\\","/",$homeFile);
	$homeFile = str_replace("//","/",$homeFile);
	$fp = fopen($homeFile,"w") or die("��ָ�����ļ��������⣬�޷������ļ�");
	fclose($fp);
	if($saveset==1)
	{
		$iquery = "update `#@__homepageset` set templet='$templet',position='$position' ";
		$dsql->ExecuteNoneQuery($iquery);
	}
	$templet = str_replace("{style}",$cfg_df_style,$templet);
	$pv = new PartView();
	$GLOBALS['_arclistEnv'] = 'index';
	$pv->SetTemplet($cfg_basedir.$cfg_templets_dir."/".$templet);
	$pv->SaveToHtml($homeFile);
	echo "�ɹ�������ҳHTML��".$homeFile;
	echo "<br/><br/><a href='$position' target='_blank'>���...</a>";
	exit();
}
$row  = $dsql->GetOne("Select * From #@__homepageset");
include DedeInclude('templets/makehtml_homepage.htm');

?>