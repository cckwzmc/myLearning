<?php
require(dirname(__FILE__)."/config.php");
CheckPurview('member_Edit');
$ENV_GOBACK_URL = isset($_COOKIE['ENV_GOBACK_URL']) ? "member_main.php" : '';
$id = ereg_replace("[^0-9]","",$id);
$row = $dsql->GetOne("select  * from #@__member where mid='$id'");

//�������û��ǹ���Ա�ʺţ��������㹻Ȩ�޵��û����ܲ���
if($row['matt']==10)
{
	CheckPurview('sys_User');
}
include DedeInclude('templets/member_view.htm');

?>