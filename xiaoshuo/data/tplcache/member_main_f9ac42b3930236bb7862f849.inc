<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=gb2312'>
<title>��վ��Ա����</title>
<link href='../img/base.css' rel='stylesheet' type='text/css'>
<link href='img/base.css' rel='stylesheet' type='text/css'>
<style type="text/css">
.rb{ border-right:1px solid #666666 }
.tb{ border-top:1px solid #666666 }
</style>
</head>
<body background='img/allbg.gif' leftmargin='8' topmargin='8'>
<table width="98%" border="0" align="center" cellpadding="3" cellspacing="1" bgcolor="#D1DDAA" style="margin-bottom:6px">
  <tr>
    <td height="30" align="center" bgcolor="#FAFCEF"> 
  <form action="member_main.php" name="form1" method="get">
	  <table border="0" cellspacing="0" cellpadding="0">
      <tr>
          <td>�ؼ��֣�</td>
          <td><input name="keyword" type="text" id="keyword" size="12" style="width:120px" value="<?php echo $GLOBALS['keyword']; ?>" /></td>
          <td>&nbsp;����</td>
          <td>
		        <select name="sortkey" id="sortkey" style="width:120px">
              <?php echo $GLOBALS['sortform']; ?>
				      <option value="mid">mid/ע��ʱ��</option>
              <option value="logintime">��¼ʱ��</option>
              <option value="spaceshow">�ռ������</option>
              <option value="pageshow">�ĵ��ܵ����</option>
            </select>
          </td>
          <td>
		         <select name="sex" id="sex" style="width:60px">
               <?php echo $GLOBALS['sexform']; ?>
               <option value="��">��</option>
               <option value="Ů">Ů</option>
               <option value="����">����</option>
             </select>
          </td>
          <td>
          	<select name="mtype" id="mtype" style="width:80px">
               <?php echo $GLOBALS['mtypeform']; ?>
               <option value="����">����</option>
               <option value="��ҵ">��ҵ</option>
             </select>
          </td>
          <td>
		         <select name="att" id="att" style="width:90px">
               <?php echo $GLOBALS['attform']; ?>
			         <option value=''>��������</option>
               <option value="uprank">��������Ա</option>
               <option value="upmoney">����ֵ��Ա</option>
               <option value="ad">���Ƽ���Ա</option>
			         <option value="mb">����ͨ��Ա</option>
            </select>
		     </td>
         <td>
           <input name="imageField" class="np" type="image" src="img/button_search.gif" width="60" height="22" border="0" />
          </td>
        </tr>
      </table>
	  </form>
    </td>
  </tr>
</table>
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA">
  <tr> 
  <td height="28" colspan="7" background="img/tbg.gif">
   <table width="99%" border="0" cellspacing="0" cellpadding="0">
     <tr>
       <td width="29%">&nbsp;<strong>ע���Ա�б�</strong></td>
       <td width="71%" align="right">
          <a href="member_main.php">���л�Ա</a> |
          <a href="member_main.php?att=uprank">������</a> |
          <a href="member_main.php?att=upmoney">����ֵ</a> |
          <a href="member_main.php?att=ad">���Ƽ�</a> |
          <a href="member_main.php?att=mb">����ͨ</a> &nbsp;
       </td>
     </tr>
  </table>
 </td>
</tr>
  <tr bgcolor="#FEFCEF" height="26" align="center"> 
    <td width="17%">��¼��</td>
    <td width="16%">email/�ǳ�</td>
    <td width="5%">�Ա�</td>
    <td width="10%">��Ա�ȼ�</td>
    <td width="20%">��Ա����</td>
    <td width="14%">����¼</td>
    <td>����</td>
  </tr>
<?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?> 
<tr height="26" align="center" bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#EDF7D0';"
  	 onMouseOut="javascript:this.bgColor='#FFFFFF';"> 
  <td>
  <a href="../member/index.php?uid=<?php echo $fields['userid']; ?>" target='_blank'>
	<?php
echo '<u>'.$fields['userid'].'</u>';
	  if(!empty($fields['face'])) {
       echo "<br /><img src='{$fields['face']}' width='60' alt='�����Ա�ռ�' />";
	  }
?>
	</a>
	</td>
	<td>
	  <?php echo $fields['email']; ?>
	  <br />
	  �ǳƣ�<?php echo $fields['uname']; ?>
	</td>
  <td>
  	<?php echo $fields['sex']; ?>
  </td>
  <td>
  	<?php echo GetMemberName($fields['rank'],'���ƻ�Ա'); ?>
  </td>
  <td>
  	���ͣ�<?php echo $fields['mtype']; ?> �Ƽ���<?php echo GetMAtt($fields['matt']); ?>
  	<br />
  	��ң�<?php echo $fields['money']; ?> ���֣�<?php echo $fields['scores']; ?>
  </td>
  <td>
  	<?php echo MyDate('y-m-d H:i',$fields['logintime']); ?>
  	<br />
  	��<?php echo $fields['loginip']; ?>��
  </td>
  <td align="center">
    <a href="member_view.php?id=<?php echo $fields['mid']; ?>">�޸�</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=delmember">ɾ��</a> |
    <a href="content_list.php?mid=<?php echo $fields['mid']; ?>" target="_blank">�ĵ�</a>
    <br />
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=uprank&mtype=<?php echo $fields['rank']; ?>&uptype=<?php echo $fields['uprank']; ?>">����</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=addmoney&upmoney=<?php echo $fields['upmoney']; ?>">��ֵ</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=recommend&matt=<?php echo $fields['matt']; ?>">�Ƽ�</a>
  </td>
</tr>
<?php
}
?> 
<tr bgcolor="#F1FDE3"> 
 <td height="36" colspan="7" align="center">
	<?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '6';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>

 </td>
</tr>
</table>
</body>
</html>