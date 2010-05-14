<?php
	
	require_once('./common.inc.php');
		require_once('./common.func.php');
	//引入数据库类
	require_once('./lottery-db-inc.php');

	$sql="select * from dede_admin where id=1";
	$row=$dsql->GetOne($sql);
	echo 'ddddd';
	while($row){
		
		echo $row['userid'];
	}
?>