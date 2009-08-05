<?php
//error_reporting(E_ALL);
error_reporting(E_ALL || ~E_NOTICE);
define('DEDEINC', ereg_replace("[/\\]{1,}",'/',dirname(__FILE__) ) );
define('DEDEROOT', ereg_replace("[/\\]{1,}",'/',substr(DEDEINC,0,-8) ) );
define('DEDEDATA', DEDEROOT.'/data');
define('DEDEMEMBER', DEDEROOT.'/member');

//����ע���ⲿ�ύ�ı���
foreach($_REQUEST as $_k=>$_v)
{
	if( strlen($_k)>0 && eregi('^(cfg_|GLOBALS)',$_k) )
	{
		exit('Request var not allow!');
	}
}

function _RunMagicQuotes(&$svar)
{
	if(!get_magic_quotes_gpc())
	{
		if( is_array($svar) )
		{
			foreach($svar as $_k => $_v) $svar[$_k] = _RunMagicQuotes($_v);
		}
		else
		{
			$svar = addslashes($svar);
		}
	}
	return $svar;
}

foreach(Array('_GET','_POST','_COOKIE') as $_request)
{
	foreach($$_request as $_k => $_v) ${$_k} = _RunMagicQuotes($_v);
}

//ϵͳ��ر������
if(!isset($needFilter))
{
	$needFilter = false;
}
$registerGlobals = @ini_get("register_globals");
$isUrlOpen = @ini_get("allow_url_fopen");
$isSafeMode = @ini_get("safe_mode");
if( eregi('windows', @getenv('OS')) )
{
	$isSafeMode = false;
}

//Session����·��
$sessSavePath = DEDEDATA."/sessions/";
if(is_writeable($sessSavePath) && is_readable($sessSavePath))
{
	session_save_path($sessSavePath);
}

//ϵͳ���ò���
require_once(DEDEDATA."/config.cache.inc.php");

//ת���ϴ����ļ���صı�������ȫ����������ǰ̨ͨ�õ��ϴ�����
if($_FILES)
{
	require_once(DEDEINC.'/uploadsafe.inc.php');
}

//���ݿ������ļ�
require_once(DEDEDATA.'/common.inc.php');

//php5.1�汾����ʱ������
//�����������������php5.1���°汾�������壬���ʵ���ϵ�ʱ����ã�Ӧ����MyDate��������
if(PHP_VERSION > '5.1')
{
	$time51 = $cfg_cli_time * -1;
	@date_default_timezone_set('Etc/GMT'.$time51);
}
$cfg_isUrlOpen = @ini_get("allow_url_fopen");

//�û����ʵ���վhost
$cfg_clihost = 'http://'.$_SERVER['HTTP_HOST'];

//վ���Ŀ¼
$cfg_basedir = eregi_replace($cfg_cmspath.'/include$','',DEDEINC);
if($cfg_multi_site == 'Y')
{
	$cfg_mainsite = $cfg_basehost;
}
else
{
	$cfg_mainsite = '';
}

//ģ��Ĵ��Ŀ¼
$cfg_templets_dir = $cfg_cmspath.'/templets';
$cfg_templeturl = $cfg_mainsite.$cfg_templets_dir;

//cms��װĿ¼����ַ
$cfg_cmsurl = $cfg_mainsite.$cfg_cmspath;

//���Ŀ¼�����Ŀ¼�����ڴ�ż�������ͶƱ�����۵ȳ���ı�Ҫ��̬����
$cfg_plus_dir = $cfg_cmspath.'/plus';
$cfg_phpurl = $cfg_mainsite.$cfg_plus_dir;

$cfg_data_dir = $cfg_cmspath.'/data';
$cfg_dataurl = $cfg_mainsite.$cfg_data_dir;

//��ԱĿ¼
$cfg_member_dir = $cfg_cmspath.'/member';
$cfg_memberurl = $cfg_mainsite.$cfg_member_dir;

//ר���б�Ĵ��·��
$cfg_special = $cfg_cmspath.'/special';
$cfg_specialurl = $cfg_mainsite.$cfg_special;

//����Ŀ¼
$cfg_medias_dir = $cfg_cmspath.$cfg_medias_dir;
$cfg_mediasurl = $cfg_mainsite.$cfg_medias_dir;

//�ϴ�����ͨͼƬ��·��,���鰴Ĭ��
$cfg_image_dir = $cfg_medias_dir.'/allimg';

//�ϴ�������ͼ
$ddcfg_image_dir = $cfg_medias_dir.'/litimg';

//�û�Ͷ��ͼƬ���Ŀ¼
$cfg_user_dir = $cfg_medias_dir.'/userup';

//�ϴ������Ŀ¼
$cfg_soft_dir = $cfg_medias_dir.'/soft';

//�ϴ��Ķ�ý���ļ�Ŀ¼
$cfg_other_medias = $cfg_medias_dir.'/media';

//���ժҪ��Ϣ��****�벻Ҫɾ������**** ����ϵͳ�޷���ȷ����ϵͳ©����������Ϣ
$cfg_version = 'V53_1_GBK';
$cfg_soft_lang = 'gb2312';
$cfg_soft_public = 'base';

$cfg_softname = '֯�����ݹ���ϵͳ';
$cfg_soft_enname = 'DedeCms';
$cfg_soft_devteam = 'Dedecms�ٷ��Ŷ�';

//�ĵ���Ĭ����������
$art_shortname = $cfg_df_ext = '.html';
$cfg_df_namerule = '{typedir}/{Y}/{M}{D}/{aid}'.$cfg_df_ext;

//�½�Ŀ¼��Ȩ�ޣ������ʹ�ñ�����ԣ����̲���֤������˳����Linux��Unixϵͳ����
$cfg_dir_purview = 0755;

//����ȫ�ֱ���
$_sys_globals['curfile'] = '';
$_sys_globals['typeid'] = 0;
$_sys_globals['typename'] = '';
$_sys_globals['aid'] = 0;

if(!isset($cfg_NotPrintHead)) {
	header("Content-Type: text/html; charset={$cfg_soft_lang}");
}

//�������ݿ���
require_once(DEDEINC.'/dedesql.class.php');

//ȫ�ֳ��ú���
require_once(DEDEINC.'/common.func.php');

?>