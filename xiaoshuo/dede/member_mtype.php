<?php
require_once(dirname(__FILE__)."/config.php");
CheckPurview('member_Type');
if(empty($dopost))
{
	$dopost = "";
}

if($dopost == 'save')
{
	$name = isset($name) ? trim($name) : '';

	//�滻�����ַ�
	$name = preg_replace("/['\"\.\/\*\\\?]/", '', $name);

	$str = 'ENUM(\'����\',\'��ҵ\'';
	if(isset($types) && is_array($types))
	{
		foreach ($types as $type)
		{
			$type = preg_replace("/['\"\.\/\*\\\?]/", '', $type);
			$str .= ',\''.$type.'\'';
		}
	}
	$str .= ')';
	if($name != ''){
		$str = str_replace(')', ',\''.$name.'\')', $str);
	}
	$sql = " ALTER TABLE `#@__member` CHANGE `mtype` `mtype` $str  NOT NULL DEFAULT '����' ";

	if($dsql->ExecNoneQuery($sql))
	{
		ShowMsg('��Ա����ɹ�', 'member_mtype.php');
		exit();
	}else
	{
		ShowMsg('�޸Ļ�Ա���ʧ��', '-1');
		exit;
	}
}
else
{
	$sql = "desc #@__member";
	$dsql->SetQuery($sql);
	$dsql->Execute();
	while ($row = $dsql->GetArray()) {
		if($row['Field'] == 'mtype')
		{
			$types = $row['Type'];
			break;
		}
	}
	$types = str_replace(array('enum', '(', ')', '\''), '', $types);
	$types = explode(',', $types);
	include(DEDEADMIN.'/templets/member_mtype.htm');
}

?>