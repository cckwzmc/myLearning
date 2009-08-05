<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我购买的服务</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
</head>
<body>
<?php include(DEDEMEMBER."/templets/head.htm"); ?>
<div class="wrapper lineT">
  <div class="mainBox">
    <div class="main">
      <?php include(DEDEMEMBER."/templets/menu.php"); ?>
      <div class="dedeMain">
        <div class="location"><span class="fLeft">我购买的服务</span><span class="fRight mR10"><a href="buy.php">购买服务</a></span></div>
        <div class="titleTabBar bgGreen lineTB">
          <ul>
            <li class="select"><a href="mypay.php" id="buy">服务</a></li>
            <li><a href="shops_products.php">商品</a></li>
          </ul>
        </div>
        <div class="mL10 mR10 mTB10">
          <div class="titleBar bgGrass lineB mB10">
            <div class="fLeft"><strong>购买的服务</strong></div>
            <div class="fRight titSecondary white" style="margin-top:5px;"> 
            <em class="mR10"><a href="mypay.php">购买的服务</a></em> 
            <em class="mR10"><a href="operation.php">历史订单</a></em> </div>
          </div>
          <table cellspacing="1" class="list">
            <thead>
              <tr>
                <th align="left" width="1%">ID</th>
                <th >标题</th>
                <th width="5%">消费点数</th>
                <th width="5%">消费时间</th>
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
              <td><?php echo $fields['aid']; ?></td>
              <td>
<?php if(strpos($fields['buyid'], 'ARCHIVE') === 0)
{
	echo '<a href="../plus/view.php?aid='.str_replace('ARCHIVE', '', $fields['buyid']).'" target="_blank">'.$fields['oldinfo'].'</a>'; 
} else {
	echo $fields['oldinfo'];
}
?>
</td>
              <td align="center"><?php echo $fields['money']; ?></td>
              <td align="center"><?php echo GetDateTimeMk($fields['mtime']); ?></td>
            </tr>
            <?php
}
?>
            </tbody>
            
            <tfoot>
              <tr>
                <td colspan="6"><div class="pages fRight"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div></td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<?php include(DEDEMEMBER."/templets/foot.htm"); ?>
</body>
</html>