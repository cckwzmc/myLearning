<?php
if(!defined('DEDEINC'))
{
	exit("Request Error!");
}

if(isset($_FILES['GLOBALS']))
{
	exit('Request not allow!');
}

//Ϊ�˷�ֹ�û�ͨ��ע��Ŀ����ԸĶ������ݿ�
//����ǿ���޶���ĳЩ�ļ����ͽ�ֹ�ϴ�
$cfg_not_allowall = "php|pl|cgi|asp|aspx|jsp|php3|shtm|shtml";
$keyarr = array('name','type','tmp_name','size');

foreach($_FILES as $_key=>$_value)
{
	foreach($keyarr as $k)
	{
		if(!isset($_FILES[$_key][$k]))
		{
			exit('Request Error!');
		}
	}
	if( eregi('^(cfg_|GLOBALS)',$_key) )
	{
		exit('Request var not allow for uploadsafe!');
	}
	$$_key = $_FILES[$_key]['tmp_name'] = str_replace("\\\\","\\",$_FILES[$_key]['tmp_name']);
	${$_key.'_name'} = $_FILES[$_key]['name'];
	${$_key.'_type'} = $_FILES[$_key]['type'] = eregi_replace('[^0-9a-z\./]','',$_FILES[$_key]['type']);
	${$_key.'_size'} = $_FILES[$_key]['size'] = ereg_replace('[^0-9]','',$_FILES[$_key]['size']);
	if(!empty(${$_key.'_name'}) && (eregi("\.(".$cfg_not_allowall.")$",${$_key.'_name'}) || !ereg("\.",${$_key.'_name'})) )
	{
		if(!defined('DEDEADMIN'))
		{
			exit('Upload filetype not allow !');
		}
	}
	if(empty(${$_key.'_size'}))
	{
		${$_key.'_size'} = @filesize($$_key);
	}
}

//ǰ̨��Աͨ���ϴ�����
//$upname ���ļ��ϴ���ı����������Ǳ��ı���
//$handname �����û��ֹ�ָ����ַ����µ���ַ
function MemberUploads($upname,$handname,$userid=0,$utype='image',$exname='',$maxwidth=-1,$maxheight=-1,$water=false)
{
	global $cfg_imgtype,$cfg_mb_addontype,$cfg_mediatype,$cfg_user_dir,$cfg_basedir,$cfg_dir_purview;
	if(is_uploaded_file($GLOBALS[$upname]))
	{
		$nowtme = time();
		$GLOBALS[$upname.'_name'] = trim(ereg_replace("[ \r\n\t\*\%\\/\?><\|\":]{1,}",'',$GLOBALS[$upname.'_name']));
		if($utype=='image')
		{
			if(!eregi("\.(".$cfg_imgtype.")$",$GLOBALS[$upname.'_name']))
			{
				ShowMsg("�����ϴ���ͼƬ���Ͳ�������б����ϴ�{$cfg_imgtype}���ͣ�",'-1');
				exit();
			}
			$sparr = Array("image/pjpeg","image/jpeg","image/gif","image/png","image/xpng","image/wbmp");
			$imgfile_type = strtolower(trim($GLOBALS[$upname.'_type']));
			if(!in_array($imgfile_type,$sparr))
			{
				ShowMsg("�ϴ���ͼƬ��ʽ������ʹ��JPEG��GIF��PNG��WBMP��ʽ������һ�֣�",'-1');
				exit();
			}
		}
		else if($utype=='flash' && !eregi("\.swf$",$GLOBALS[$upname.'_name']))
		{
			ShowMsg("�ϴ����ļ�����Ϊflash�ļ���",'-1');
			exit();
		}
		else if($utype=='media' && !eregi("\.(".$cfg_mediatype.")$",$GLOBALS[$upname.'_name']))
		{
			ShowMsg("�����ϴ����ļ����ͱ���Ϊ��".$cfg_mediatype,'-1');
			exit();
		}
		else if(!eregi("\.(".$cfg_mb_addontype.")$",$GLOBALS[$upname.'_name']))
		{
			ShowMsg("�����ϴ����ļ����Ͳ�������",'-1');
			exit();
		}

		//��Ϊ�ο�Ͷ�������£����idӦ��Ϊ 0
		if($userid == '')
		{
			ShowMsg("ϵͳ�޷�����û�ID����ֹ�ϴ��ļ���",'-1');
			exit();
		}
		if(!is_dir($cfg_basedir.$cfg_user_dir."/$userid"))
		{
			MkdirAll($cfg_basedir.$cfg_user_dir."/$userid",$cfg_dir_purview);
			CloseFtp();
		}
		$fs = explode('.',$GLOBALS[$upname.'_name']);
		$sname = $fs[count($fs)-1];
		$alltype = $cfg_mb_addontype.'|'.$cfg_imgtype.'|'.$cfg_mediatype;
		$alltypes = explode('|',$alltype);

		//ϵͳ������������
		if(!in_array(strtolower($sname),$alltypes))
		{
			ShowMsg("ϵͳ�޷�ʶ�����ϴ����ļ���Ϊ��ָ�����ͣ�",'-1');
			exit();
		}

		//ǿ�ƽ�ֹ���ļ�����
		if(eregi("asp|php|pl|cgi|jsp|shtm",$sname))
		{
			ShowMsg("���ϴ����ļ�Ϊϵͳ��ֹ�����ͣ�",'-1');
			exit();
		}
		if($exname=='')
		{
			$filename = $cfg_user_dir."/$userid/".dd2char($nowtme.'-'.mt_rand(1000,9999)).'.'.$sname;
		}
		else
		{
			$filename = $cfg_user_dir."/{$userid}/{$exname}.".$sname;
		}
		move_uploaded_file($GLOBALS[$upname],$cfg_basedir.$filename) or die("�ϴ��ļ��� {$filename} ʧ�ܣ�");
		@unlink($GLOBALS[$upname]);

		//��СͼƬ���ˮӡ
		if($utype=='image' && ( ($maxwidth > 0 && $maxheight > 0) || $water) )
		{
			include_once(DEDEINC.'/image.func.php');
			if($maxwidth > 0 && $maxheight > 0)
			{
				//ImageResize($cfg_basedir.$filename,$maxwidth,$maxheight);
			}
			if($water)
			{
				WaterImg($cfg_basedir.$filename);
			}
		}

		return $filename;
	}
	else
	{
		//if(ereg(':',$handname) && !eregi('http:')) return '';
		//else
		return $handname;
	}
}
?>