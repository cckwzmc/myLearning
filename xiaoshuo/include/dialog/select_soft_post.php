<?php
if(!isset($cfg_basedir))
{
	include_once(dirname(__FILE__)."/config.php");
}
if(empty($uploadfile))
{
	$uploadfile = '';
}
if(empty($uploadmbtype))
{
	$uploadmbtype = "�������";
}
if(empty($bkurl))
{
	$bkurl = 'select_soft.php';
}
if(!is_uploaded_file($uploadfile))
{
	ShowMsg("��û��ѡ���ϴ����ļ���ѡ����ļ���С��������!","-1");
	exit();
}
$uploadfile_name = trim(ereg_replace("[ \r\n\t\*\%\\/\?><\|\":]{1,}",'',$uploadfile_name));
if(!eregi("\.(".$cfg_softtype.")",$uploadfile_name))
{
	ShowMsg("�����ϴ���{$uploadmbtype}��������б������ϵͳ����չ���޶������ã�","-1");
	exit();
}
$nowtme = time();
if($activepath==$cfg_soft_dir)
{
	$newdir = MyDate('Ym',$nowtme);
	$activepath = $activepath.'/'.$newdir;
	if(!is_dir($cfg_basedir.$activepath))
	{
		MkdirAll($cfg_basedir.$activepath,$cfg_dir_purview);
		CloseFtp();
	}
}
$filename = $cuserLogin->getUserID().'_'.MyDate('dHis',$nowtme);
$fs = explode('.',$uploadfile_name);
if(eregi($cfg_not_allowall,$fs[count($fs)-1]))
{
	ShowMsg("���ϴ���ĳЩ���ܴ��ڲ���ȫ���ص��ļ���ϵͳ�ܾ�������",'javascript:;');
	exit();
}
$filename = $filename.'.'.$fs[count($fs)-1];
$fullfilename = $cfg_basedir.$activepath.'/'.$filename;
$fullfileurl = $activepath.'/'.$filename;
move_uploaded_file($uploadfile,$fullfilename) or die("�ϴ��ļ��� $fullfilename ʧ�ܣ�");
@unlink($uploadfile);
if($uploadfile_type == 'application/x-shockwave-flash')
{
	$mediatype=2;
}
else if(eregi('audio|media|video',$uploadfile_type))
{
	$mediatype=3;
}
else
{
	$mediatype=4;
}
$inquery = "INSERT INTO #@__uploads(arcid,title,url,mediatype,width,height,playtime,filesize,uptime,mid)
   VALUES ('0','$filename','$fullfileurl','$mediatype','0','0','0','{$uploadfile_size}','{$nowtme}','".$cuserLogin->getUserID()."'); ";

$dsql->ExecuteNoneQuery($inquery);
ShowMsg("�ɹ��ϴ��ļ���",$bkurl."?comeback=".urlencode($filename)."&f=$f&activepath=".urlencode($activepath)."&d=".time());
exit();
?>