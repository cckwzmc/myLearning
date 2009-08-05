<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=gb2312'>
<title>网站会员管理</title>
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
          <td>关键字：</td>
          <td><input name="keyword" type="text" id="keyword" size="12" style="width:120px" value="<?php echo $GLOBALS['keyword']; ?>" /></td>
          <td>&nbsp;排序：</td>
          <td>
		        <select name="sortkey" id="sortkey" style="width:120px">
              <?php echo $GLOBALS['sortform']; ?>
				      <option value="mid">mid/注册时间</option>
              <option value="logintime">登录时间</option>
              <option value="spaceshow">空间访问量</option>
              <option value="pageshow">文档总点击量</option>
            </select>
          </td>
          <td>
		         <select name="sex" id="sex" style="width:60px">
               <?php echo $GLOBALS['sexform']; ?>
               <option value="男">男</option>
               <option value="女">女</option>
               <option value="保密">保密</option>
             </select>
          </td>
          <td>
          	<select name="mtype" id="mtype" style="width:80px">
               <?php echo $GLOBALS['mtypeform']; ?>
               <option value="个人">个人</option>
               <option value="企业">企业</option>
             </select>
          </td>
          <td>
		         <select name="att" id="att" style="width:90px">
               <?php echo $GLOBALS['attform']; ?>
			         <option value=''>特殊属性</option>
               <option value="uprank">待升级会员</option>
               <option value="upmoney">待充值会员</option>
               <option value="ad">被推荐会员</option>
			         <option value="mb">非普通会员</option>
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
       <td width="29%">&nbsp;<strong>注册会员列表</strong></td>
       <td width="71%" align="right">
          <a href="member_main.php">所有会员</a> |
          <a href="member_main.php?att=uprank">待升级</a> |
          <a href="member_main.php?att=upmoney">待充值</a> |
          <a href="member_main.php?att=ad">被推荐</a> |
          <a href="member_main.php?att=mb">非普通</a> &nbsp;
       </td>
     </tr>
  </table>
 </td>
</tr>
  <tr bgcolor="#FEFCEF" height="26" align="center"> 
    <td width="17%">登录名</td>
    <td width="16%">email/昵称</td>
    <td width="5%">性别</td>
    <td width="10%">会员等级</td>
    <td width="20%">会员属性</td>
    <td width="14%">最后登录</td>
    <td>操作</td>
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
       echo "<br /><img src='{$fields['face']}' width='60' alt='浏览会员空间' />";
	  }
?>
	</a>
	</td>
	<td>
	  <?php echo $fields['email']; ?>
	  <br />
	  昵称：<?php echo $fields['uname']; ?>
	</td>
  <td>
  	<?php echo $fields['sex']; ?>
  </td>
  <td>
  	<?php echo GetMemberName($fields['rank'],'限制会员'); ?>
  </td>
  <td>
  	类型：<?php echo $fields['mtype']; ?> 推荐：<?php echo GetMAtt($fields['matt']); ?>
  	<br />
  	金币：<?php echo $fields['money']; ?> 积分：<?php echo $fields['scores']; ?>
  </td>
  <td>
  	<?php echo MyDate('y-m-d H:i',$fields['logintime']); ?>
  	<br />
  	【<?php echo $fields['loginip']; ?>】
  </td>
  <td align="center">
    <a href="member_view.php?id=<?php echo $fields['mid']; ?>">修改</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=delmember">删除</a> |
    <a href="content_list.php?mid=<?php echo $fields['mid']; ?>" target="_blank">文档</a>
    <br />
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=uprank&mtype=<?php echo $fields['rank']; ?>&uptype=<?php echo $fields['uprank']; ?>">升级</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=addmoney&upmoney=<?php echo $fields['upmoney']; ?>">充值</a> | 
    <a href="member_do.php?id=<?php echo $fields['mid']; ?>&dopost=recommend&matt=<?php echo $fields['matt']; ?>">推荐</a>
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