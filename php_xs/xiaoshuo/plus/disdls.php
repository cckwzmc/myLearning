<?php
require_once(dirname(__FILE__)."/../include/common.inc.php");
$aid = (isset($aid) && is_numeric($aid)) ? $aid : 0;
$row = $dsql->GetOne("Select sum(downloads) as totals From `#@__downloads`  where id='$aid' ");
if(is_array($row))
{
	echo "document.write('".$row['totals']."');\r\n";
}
exit();
/*-----------
�������ʾ���ش���,��������ʣӵ��÷ŵ��ĵ�ģ���ʵ�λ��
<script src="{dede:field name='phpurl'/}/disdls.php?aid={dede:field name='id'/}" language="javascript"></script>
------------*/
?>