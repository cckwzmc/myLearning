<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ҵĺ���</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script type="text/javascript" language="javascript">
//���ѡ���ļ����ļ���
function GetCheckfolderItem()
{
	var allSel='';
	if(document.form1.selid.value) return document.form1.selid.value;
	for(i=0;i<document.form1.selid.length;i++)
	{
		if(document.form1.selid[i].checked)
		{
			if(allSel=='')
			allSel=document.form1.selid[i].value;
			else
			allSel=allSel+","+document.form1.selid[i].value;
		}
	}
	return allSel;
}

function DelSel(ftype)
{
	var selid = GetCheckfolderItem();
	if(selid=='') {
		alert("��ûѡ���κκ��ѣ�");
		return false;
	}
	if(window.confirm("��ȷ��Ҫɾ����Щ����ô��"))
	{
		location = "myfriend.php?dopost=del&ids="+selid+"&ftype="+ftype;
	}
}

function ConvSta(sta,ftype)
{
	var selid = GetCheckfolderItem();
	var stamsg = '';
	if(selid=='') {
		alert("��ûѡ���κκ��ѣ�");
		return false;
	}

	if(sta=='good') stamsg = '�����ע�ĺ���';
	else if(sta=='bad') stamsg = '���������';
	else stamsg = '��Ϊ��ͨ����';

	if(window.confirm("��ȷ��Ҫ����Щ����"+stamsg+"ô��"))
	{
		location = "myfriend.php?dopost=upsta&sta="+sta+"&ids="+selid+"&ftype="+ftype;
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
          <?php if($ftype == '0') echo '���к���'; if($ftype == '1') echo '��ע����'; if($ftype == '-1') echo '������';?>
        </div>
        <div class="titleTabBar bgGreen lineTB">
          <ul>
            <li class="select"><a href="myfriend.php?ftype=0">���к���</a></li>
            <li><a href="search.php">���Һ���</a></li>
            <li><a href="visit-history.php?dopost=viewme">��ע�ҵ���</a></li>
            <li><a href="visit-history.php?dopost=meview">���������</a></li>
          </ul>
        </div>
        <div class="mTB10 mL10 mR10">
          <div class="titleBar bgGrass lineB mB10">
            <div class="fLeft"><strong>
              <?php if($ftype == '0') echo '���к���'; if($ftype == '1') echo '��ע����'; if($ftype == '-1') echo '������';?>
              </strong><em>(<?php echo $dlist->totalResult;?>)</em></div>
            <div class="fRight titSecondary white" style="margin-top:5px;"> <em><a href="myfriend.php?ftype=0">ȫ��</a></em> <em class="mL10 mR10"><a href="myfriend.php?ftype=1">�ر��ע</a></em> <em><a href="myfriend.php?ftype=-1">������</a></em> </div>
          </div>
          <form name='form1' action="myfriend.php" method="post">
            <input type='hidden' name='ftype' value='del' />
            <?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?>
            <div class="item friend">
              <div class="itemHead" >
                <div class="fLeft"> <span class="itemHeadTitle yellow"><img class="lineBorder mR10" align="top" src="<?php echo getUserInfo($fields['fid'],'face');?>" width="16" height="16" /><a href='index.php?uid=<?php echo urlencode($fields['floginid']); ?>' target='_blank'><?php echo $fields['funame']; ?></a></span><span class="itemTip mL10"><?php echo getUserInfo($fields['fid'],'sign');?></span></div>
                <div class="fRight">
                  <input type="checkbox" name="selid" value="<? echo $fields['id']; ?>" />
                </div>
                <div class="clear"></div>
              </div>
              <div class="itemBody invisible" >
                <div class="itemContent"> ����ʱ��:<?php echo MyDate('Y-m-d H:i:s',$fields['addtime']); ?> <a href="pm.php?dopost=send&uid=<?php echo urlencode($fields['floginid']); ?>">[����Ϣ]</a></div>
                <div class="itemBodyFoot"></div>
              </div>
            </div>
            <hr class="dotted" />
            <?php
}
?>
            <div>
              <div class="fLeft">
                <button class="buttonGary2" type="button" id="checkedClick">ȫѡ</button>
                <button class="buttonGary2 mL10 mR10" onclick="DelSel('<?php echo $ftype; ?>');" type="button" >ɾ��</button>
                <?php if(empty($ftype)) { ?>
                <button class="buttonGary2" onclick="ConvSta('good','<?php echo $ftype; ?>');" type="button" >��ע</button>
                <button class="buttonGary5 mL10 mR10" onclick="ConvSta('bad','<?php echo $ftype; ?>');" type="button" >���������</button>
                <?php }else { ?>
                <button class="buttonGary5" onclick="ConvSta('common','<?php echo $ftype; ?>');" type="button"  >��ԭΪ��ͨ</button>
                <?php } ?>
              </div>
              <div class="pages fRight"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div>
              <div class="clear"></div>
            </div>
          </form>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<?php include(DEDEMEMBER."/templets/foot.htm"); ?>
</div>
</body>
</html>