<?php
require_once(dirname(__FILE__)."/../include/common.inc.php");
if(isset($arcID))
{
	$aid = $arcID;
}
$arcID = $aid = (isset($aid) && is_numeric($aid)) ? $aid : 0;
if($aid==0)
{
	exit();
}
$mid = (isset($mid) && is_numeric($mid)) ? $mid : 0;

//UpdateStat();
$dsql->ExecuteNoneQuery(" Update `#@__archives` set click=click+1 where id='$aid' ");
if(!empty($mid))
{
	$dsql->ExecuteNoneQuery(" Update `#@__member_tj` set pagecount=pagecount+1 where mid='$mid' ");
}
if(!empty($view))
{
	$row = $dsql->GetOne(" Select click From `#@__archives`  where id='$aid' ");
	if(is_array($row))
	{
		echo "document.write('".$row['click']."');\r\n";
	}
}
exit();
/*-----------
�������ʾ�������,������view����,��������ʣӵ��÷ŵ��ĵ�ģ���ʵ�λ��
<script src="{dede:field name='phpurl'/}/count.php?view=yes&aid={dede:field name='id'/}&mid={dede:field name='mid'/}" language="javascript"></script>
��ͨ������Ϊ
<script src="{dede:field name='phpurl'/}/count.php?aid={dede:field name='id'/}&mid={dede:field name='mid'/}" language="javascript"></script>
------------*/
?>