<?php
// examples: $key = array_search('alipay',$payment_select);
require_once(dirname(__FILE__)."/config.php");
CheckPurview('sys_Data');
$config_file = DEDEDATA.'/sys_pay.cache.php';
$message = '<font color="green">��</font>֧���ӿ������ļ�(/data/sys_pay.cache.php)��д.WINDOWS����������������дȨ��ʱ,������ļ���дȨ��!';
$string	= '';
@touch($config_file);
if(!is_writable($config_file) || !file_exists($config_file))
{
	$message = '<font color="red">��</font>����Ŀ¼/data/sys_pay.cache.php���ļ��Ƿ��п�дȨ��(0777)!';
}

include_once DEDEDATA.'/sys_pay.cache.php';

if(isset($dopost) && $dopost == 'save')
{
	if(!isset($payment_select) || empty($payment_select)){
		ShowMsg("ѡ������֧���ӿ�����!","javascript:;");
		exit();
	}
	
	if(!isset($payment_userid) || empty($payment_userid)){
		ShowMsg("����д��ȷ���̻���!","javascript:;");
		exit();
	}
	
	$payment_select = mch_array_string($_POST['payment_select']);	
	
	$payment_userid = mch_array_string($_POST['payment_userid']);
	
	$payment_email = mch_array_string($_POST['payment_email']);
	
	$payment_key = mch_array_string($_POST['payment_key']);
	
	$payment_exp = mch_array_string($_POST['payment_exp']);

	if( $content = file_get_contents($config_file) ){
	
		$content = insert_mch_config($content, '/\$payment_select \= array\(.*?\);/i', '$payment_select = array('.$payment_select.');');
		
		$content = insert_mch_config($content, '/\$payment_userid \= array\(.*?\);/i', '$payment_userid = array('.$payment_userid.');');
		
		$content = insert_mch_config($content, '/\$payment_key \= array\(.*?\);/i', '$payment_key = array('.$payment_key.');');
		
		$content = insert_mch_config($content, '/\$payment_exp \= array\(.*?\);/i', '$payment_exp = array('.$payment_exp.');');
		
		$content = insert_mch_config($content, '/\$payment_email \= array\(.*?\);/i', '$payment_email = array('.$payment_email.');');

		$fp = fopen($config_file,"w+");
		fwrite($fp,$content);
		fclose($fp);
		ShowMsg("�ɹ�����֧���ӿ�����!","javascript:;");
		exit();
	
	}else{
		ShowMsg("��ȡ�����ļ�ʧ��!","javascript:;");
		exit();
	}

}


function insert_mch_config($s, $find, $replace) {
	if(preg_match($find, $s)) {
		$s = preg_replace($find, $replace, $s);
	} else {
		// ���뵽���һ��
		$s .= "\r\n".$replace;
	}
	return $s;	
}

//��ʽ�����鵽�ַ���
function mch_array_string($arr){
	if(empty($arr)) return '';
	$string = array();
	foreach($arr as $k => $val){
		$k = (!is_numeric($k)) ? "'$k'" : $k;
		$string[] = "$k => \"".addslashes($val)."\"";
	}
	return implode(',',$string);
}

include DedeInclude('templets/sys_info_pay.htm');
?>