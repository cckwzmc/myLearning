<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ĵ�����</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script language="javascript" type="text/javascript">
function viewArc(aid){
	window.open("<?php echo $GLOBALS['cfg_phpurl']; ?>/view.php?aid="+aid);
}
function editArc(aid,channelid){
	location = "archives_do.php?dopost=edit&channelid="+channelid+"&aid="+aid;
}
function delArc(aid){
	if(window.confirm('��ȷ��Ҫɾ����ƪ�ĵ���?')) location="archives_do.php?aid="+aid+"&dopost=delArc";
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
        <div class="location"><span class="fLeft">�ѷ���<?php echo $positionname; ?></span><span class="fRight mR10 titPublish icon"><a href='archives_do.php?dopost=addArc&channelid=<?php echo $channelid;  ?>'>�������ĵ�</a></span></div>
        <div class="titleBar bgGreen lineTB">
          <form name='form3' action='content_list.php' method='get'>
            <div class="fLeft" style="margin-top:3px">
              <input type='hidden' name='dopost' value='listArchives' />
              <input type='hidden' name='channelid' value='<?php echo $channelid?>'>
              �ؼ��֣�
              <input class="text" type='text' name='keyword' value='<?php echo $keyword?>' style='width:140px' />
              <button class="button2" type="submit">�ύ</button>
            </div>
            <div class="fRight" style="margin-top:3px">
              <?php
            	echo "<select  name='mtypesid' onchange='location.href=this.value' size='1'>\r\n";
            	echo "<option value='0'>��ѡ�����...</option>\r\n";
                echo "<option value='content_list.php?channelid=$channelid'>��������</option>\r\n";
				echo $classlist;
				echo "</select>";
            ?>
            </div>
          </form>
        </div>
        <form name="form2" class="mL10 mR10 mTB10">
          <table cellspacing="1" class="list">
            <thead>
              <tr>
                <th >���±���</th>
                <th width="10%">��Ŀ </th>
                <th width="10%">״̬ </th>
                <th width="5%">��� </th>
                <th width="10%">����ʱ�� </th>
                <th width="10%">����</th>
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
              <td><span class="titDot icon16 fLeft"></span><a href='<?php echo $cfg_phpurl; ?>/view.php?aid=<?php echo $fields['id']; ?>' target='_blank'><?php echo $fields['title'];  echo IsPicArchives($fields['litpic']); ?></a>
                <!--<?php
if(trim($fields['litpic'])!='')
{
	echo "<img class='' src='{$fields['litpic']}' width='90' />";
}
?> -->
              </td>
              <td align="center"><?php echo $fields['typename']; ?></td>
              <td align="center"><?php
if($fields['arcrank']>=0) echo "�����";
else echo "<font color='red'>δ���</font>";
?></td>
              <td align="center"><?php echo $fields['click']; ?></td>
              <td><?php echo GetDateMk($fields['senddate']); ?></td>
              <td align="center"><?php
if($fields['arcrank']<0 || $arcsta!=-1)
{
?>
                <a href="javascript:editArc(<?php echo $fields['id']?>,<?php echo $fields['channel']?>);">�޸�</a> | <a href="javascript:delArc(<?php echo $fields['id']?>);">ɾ��</a> |
                <?php
}
else
{
	echo "<span class='gray'>�� | ɾ | </span>";
}
?>
                <a href="javascript:viewArc(<?php echo $fields['id']; ?>);">Ԥ��</a></td>
            </tr>
            <?php
}
?>
            </tbody>
            
            <tfoot>
              <tr>
                <td colspan="6"><div class="pages mTB10 fRight"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div>
                  <div class="clear"></div></td>
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