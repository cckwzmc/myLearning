<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ҵ��ղؼ�</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
</head>
<body>
<?php include(DEDEMEMBER."/templets/head.htm"); ?>
<div class="wrapper lineT">
  <div class="mainBox">
    <div class="main">
      <?php include(DEDEMEMBER."/templets/menu.php"); ?>
      <div class="dedeMain">
        <div class="location">�ҵ��ղ�</div>
        <div class="titleTabBar bgGreen lineTB">
          <ul>
            <li class="select"><a href="mystow.php">�ҵ��ղ�</a></li>
            <li><a href="mystow.php?type=top20">�ղ�����</a></li>
          </ul>
        </div>
        <div class="mTB10 mL10 mR10">
          <!--
          <div class="titleBar bgGrass lineB mB10">
            <div class="fLeft"></div>
            <div class="fRight titSecondary" style="margin-top:5px;"><em><a id="allDeploy">ȫ��չ��</a></em></div>
          </div>
          -->
          <?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?>
          <div class="item favorite">
            <div class="itemHead" >
              <div class="fRight"> <span class="itemDigg"><a class="text_button" href="../plus/recommend.php?aid=<?php echo $fields['aid']; ?>" target="_blank">[�Ƽ�]</a></span> <span class="itemManage"><a href='archives_do.php?dopost=delStow&aid=<?php echo $fields['aid']; ?>'>[ɾ��]</a> </span> </div>
              <span class="itemHeadTitle"><a href='archives_do.php?dopost=viewArchives&aid=<?php echo $fields["aid"]; ?>' target='_blank'><?php echo $fields["title"]; ?></a> </span>
              <div class="itemTip">�ղ�ʱ��:<?php echo MyDate('m-d H:i', $fields['addtime']); ?></div>
            </div>
            <div class="itemBody invisible" >
              <div class="itemContent"></div>
              <div class="itemBodyFoot"></div>
            </div>
          </div>
          <hr class="dotted" />
          <?php
}
?>
          <div class="pages"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div>
          <div class="clear"></div>
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