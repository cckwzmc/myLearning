<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();

$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{

  if($fields['userid']!='') $spaceurl = $cfg_basehost.'/member/index.php?uid='.$fields['userid'];
  else $spaceurl = "#";
  if($fields['username']=='����') $spaceurl = "#";
  $fields['bgimg'] = 'cmt-neu.gif';
  $fields['ftypetitle'] = '���û���ʾ����';
  if($fields['ftype']=='bad') {
  	$fields['bgimg'] = 'cmt-bad.gif';
  	$fields['ftypetitle'] = '���û���ʾ����';
  }
  else if($fields['ftype']=='good') {
  	$fields['bgimg'] = 'cmt-good.gif';
  	$fields['ftypetitle'] = '���û���ʾ����';
  }
?>
document.write("<div class='decmt-box'>");
document.write("<div class='decmt-title'>");
document.write("<span class='moodico'><img src='<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-<?php if($fields['face']>0) echo $fields['face'];?>.gif'/></span>");
document.write("<span class='username'><a href='<?php echo $spaceurl;?>'><?php echo $fields['username']; ?></a></span>");
document.write("<span class='date'><?php echo GetDateMk($fields['dtime']); ?></span>");
document.write("<span>����</span>");
document.write("</div>");
document.write("<div class='decmt-act'>");
document.write("<span id='goodfb<?php echo $fields['id']; ?>'><a href=#goodfb<?php echo $fields['id']; ?> onclick=postBadGood('goodfb',<?php echo $fields['id']; ?>)>֧��</a>[<?php echo $fields['good']; ?>]</span>");
document.write("<span id='badfb<?php echo $fields['id']; ?>'><a href=#badfb<?php echo $fields['id']; ?> onclick=postBadGood('badfb',<?php echo $fields['id']; ?>)>����</a>[<?php echo $fields['bad']; ?>]</span>");
document.write("<span><a href='/plus/feedback.php?fid=<?php echo $fields['id']; ?>&action=quote'>����</a></span>");
document.write("</div>");
document.write("<div class='decmt-content'><?php echo jstrim($fields['msg'],100); ?><img src='<?php echo $GLOBALS['cfg_templeturl']; ?>/images/<?php echo $fields['bgimg']; ?>' alt='<?php echo $fields['ftypetitle']; ?>' />");
document.write("</div>");
document.write("</div>");
<?php
}
?>