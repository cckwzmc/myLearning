<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ϣ����</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script type="text/javascript" language="javascript">
function folderSelAll()
{
	for(i=0;i<document.form1.deleteid.length;i++)
	{
		if(!document.form1.deleteid[i].checked)
		{
			document.form1.deleteid[i].checked=true;
		}
	}
}

//���ѡ���ļ����ļ���
function GetCheckfolderItem()
{
	var allSel='';
	if(document.form1.deleteid.value) return document.form1.deleteid.value;
	for(i=0;i<document.form1.deleteid.length;i++)
	{
		if(document.form1.deleteid[i].checked)
		{
			if(allSel=='')
			allSel=document.form1.deleteid[i].value;
			else
			allSel=allSel+","+document.form1.deleteid[i].value;
		}
	}
	return allSel;
}

function DoSubmit(myfolder)
{
	var selid = GetCheckfolderItem();
	if(selid=='') {
		alert("��ûѡ���κ���Ϣ��");
		return false;
	}
	if(window.confirm("��ȷ��Ҫɾ����Щ��Ϣô��"))
	{
		location = "pm.php?dopost=del&ids="+selid+"&folder="+myfolder;
	}
}
</script>
</head>
<body>
<?php include(DEDEMEMBER."/templets/head.htm"); ?>
<div class="wrapper lineT">
  <div class="mainBox">
    <div class="main">
      <?php include(DEDEMEMBER."/templets/menu.php"); ?>
      <div class="dedeMain">
        <div class="location">
          <div class="fLeft"><?php echo $tname; ?></div>
          <div class="fRight mR10"><a href="?" onclick="DoSubmit('<?php echo $folder; ?>')">ɾ��</a></div>
        </div>
        <div class="titleTabBar bgGreen lineTB">
          <ul id="pm">
            <li><a href="pm.php?dopost=send">д����Ϣ</a></li>
            <li <?php if($folder == "inbox" || empty($folder)) echo 'class="select"'; ?>><a href="pm.php?folder=inbox">�ռ���</a></li>
            <li <?php if($folder == "outbox") echo 'class="select"'; ?>><a href="pm.php?folder=outbox">������</a></li>
            <li <?php if($folder == "track") echo 'class="select"'; ?>><a href="pm.php?folder=track">�ѷ�����Ϣ</a></li>
          </ul>
          <div class="clear"></div>
        </div>
        <form class="mTB10 mL10 mR10" action="pm.php" method="post" name="form1">
          <input type='hidden' name='dopost' value='del' />
          <table cellspacing="1" class="list">
            <thead>
              <tr>
                <th align="left" width="1%"><input name="checkedClick" type="checkbox" value="" id="checkedClick"></th>
                <th >����</th>
                <th width="10%"><?php
          	if($folder == "inbox") echo "������";
          	else echo "�ռ���";
          	?></th>
                <th width="10%">״̬ </th>
                <th width="15%">����ʱ�� </th>
              </tr>
            </thead>
            <tbody>
            
            <?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?>
            <tr>
              <td><input type="checkbox" name="deleteid" value="<?php echo $fields['id']; ?>" /></td>
              <td><?php
           if($fields['hasview']==0) echo "<a href='pm.php?dopost=read&id={$fields['id']}'><strong>{$fields['subject']}</strong></a>";
           else echo "<a href='pm.php?dopost=read&id={$fields['id']}'>{$fields['subject']}</a>";
           ?></td>
              <td align="center"><?php  if($folder == "inbox") {  ?>
                <a href="index.php?uid=<?php echo $fields['floginid']; ?>" target="_blank"> <?php echo $fields['floginid']; ?> </a>
                <?php  }else{  ?>
                <a href="index.php?uid=<?php echo $fields['tologinid']; ?>" target="_blank"> <?php echo $fields['tologinid']; ?> </a>
                <?php }  ?></td>
              <td align="center"><?php
          $readmsg = ($fields['hasview']==0 ? "<span style='color:red'>δ�Ķ�</span>" : '���Ķ�');
          echo $readmsg;
          ?>
              </td>
              <td align="center"><?php
          echo MyDate('y-m-d H:i:s',$fields['sendtime']);
          ?></td>
            </tr>
            <?php
}
?>
            </tbody>
            
            <tfoot>
              <tr>
                <td colspan="5"><div class="pages fRight mTB10"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div></td>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
      <div class="clear"></div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<?php include(DEDEMEMBER."/templets/foot.htm"); ?>
</body>
</html>