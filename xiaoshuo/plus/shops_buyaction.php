<?php 
require_once (dirname(__FILE__) . "/../include/common.inc.php");
define('_PLUS_TPL_', DEDEROOT.'/templets/plus');
require_once DEDEINC.'/dedetemplate.class.php';
require_once DEDEINC.'/shopcar.class.php';
require_once DEDEINC.'/memberlogin.class.php';
require_once DEDEROOT.'/data/sys_pay.cache.php';

if($cfg_mb_open=='N')
{
	ShowMsg("ϵͳ�ر��˻�Ա���ܣ�������޷����ʴ�ҳ�棡","javascript:;");
	exit();
}

if(isset($pd_encode) && isset($pd_verify) && md5("payment".$pd_encode.$cfg_cookie_encode) == $pd_verify)
{
	parse_str(mchStrCode($pd_encode,'DECODE'),$mch_Post);
	foreach($mch_Post as $k => $v) $$k = $v;
}

$pr_encode = '';
foreach($_REQUEST as $key => $val)
{
	$pr_encode .= $pr_encode ? "&$key=$val" : "$key=$val";
}
$pr_encode = str_replace('=', '', mchStrCode($pr_encode));
$pr_verify = md5("payment".$pr_encode.$cfg_cookie_encode);

$cfg_ml = new MemberLogin();

if(!$cfg_ml->IsLogin())
{
	ShowMsg("δ��¼���������!","javascript:;");
	exit();
}

$oid = ereg_replace("[^-0-9A-Z]","",$oid);
if(empty($oid))
{
	ShowMsg("��Ч������!","javascript:;");
	exit();
}

$rs = $dsql->GetOne("SELECT `oid`,`price`,`cartcount`,`priceCount` FROM `#@__shops_orders` WHERE `oid`='$oid' AND `state`<1 AND userid='".$cfg_ml->M_ID."' AND paytype=1 ");
if(!is_array($rs))
{
	ShowMsg("�ö����˲�����Ч!","javascript:;");
	exit();	
}

$OrdersId = $row['OrdersId'] = $rs['oid'];
$row['cartcount'] = $rs['cartcount'];
$row['price'] = $rs['price'];
$row['priceCount']= $rs['priceCount'];

$rs = $dsql->GetOne("SELECT `consignee`,`zip`,`address`,`tel`,`email`,`des` FROM `#@__shops_userinfo` WHERE `oid`='$oid' AND userid='".$cfg_ml->M_ID."'");
if(empty($rs['consignee']))
{
	ShowMsg("��Ч����!","javascript:;");
	exit();	
}

$row['address'] = $rs['address'];
$row['zip'] = $rs['zip'];
$row['tel'] = $rs['tel'];
$row['email'] = $rs['email'];
$row['des'] = stripslashes($rs['des']);
$row['postname'] = $rs['consignee'];
//�����û���Ʒͳ��	
$countOrders = $dsql->GetOne("SELECT SUM(cartcount) AS nums FROM #@__shops_orders WHERE userid='".$cfg_ml->M_ID."'");	
$dsql->ExecuteNoneQuery("UPDATE #@__member_tj SET `shop`='".$countOrders['nums']."' WHERE mid='".$cfg_ml->M_ID."'");

$priceCount = sprintf("%01.2f", $row['priceCount']);
if(!isset($online_payment))
{		
	$payment_list = array();
	foreach($payment_select as $k => $val)
	{
		$temp_arr['name'] = $cfg_pay_info['name'][$k];
		$temp_arr['logo'] = $cfg_cmspath.'/member/images/pay/'.$cfg_pay_info['logo'][$k];
		$temp_arr['des']	= $cfg_pay_info['des'][$k];
		$temp_arr['value'] = $val;
		$temp_arr['exp'] = sprintf("%01.2f", $priceCount*$payment_exp[$k]);
		$payment_list[] = $temp_arr;
	}
	
	$dtp = new DedeTemplate();
	$dtp->Assign('carts',$row);	
	$dtp->LoadTemplate(_PLUS_TPL_.'/shops_buyaction.htm');
	$dtp->Display();
	exit();
}
else
{
	if(!in_array($online_payment,$payment_select))
	{
		ShowMsg("֧���ӿ���Ч,��û������", 'javascript:;');
		exit();
	}	
	$cart 	= new MemberShops();
	//��չ��ﳵ
	$cart->clearItem();
	$cart->MakeOrders();
	require_once DEDEROOT.'/plus/paycenter/'.$online_payment.'/config_pay_'.$online_payment.'.php';
	exit();
}
?>