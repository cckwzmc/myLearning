<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=<?php echo $GLOBALS['cfg_soft_lang']; ?>" />
<title>�û����ۣ�<?php echo $GLOBALS['title']; ?></title>
<link href="<?php echo $GLOBALS['cfg_templeturl']; ?>/style/dedecms.css" rel="stylesheet" media="screen" type="text/css" />
<link href="../style/dedecms.css" rel="stylesheet" media="screen" type="text/css" />
<script language="javascript" type="text/javascript" src="../include/dedeajax2.js"></script>
<script src="<?php echo $GLOBALS['cfg_cmspath']; ?>/images/js/cn_conver_py.js"></script> 
<script language="javascript">
function postBadGood(ftype,fid)
{
	var taget_obj = document.getElementById(ftype+fid);
	var saveid = GetCookie('badgoodid');
	if(saveid != null)
	{
		 var saveids = saveid.split(',');
		 var hasid = false;
		 saveid = '';
		 j = 1;
		 for(i=saveids.length-1;i>=0;i--)
		 {
		 	  if(saveids[i]==fid && hasid) continue;
		 	  else {
		 	  	if(saveids[i]==fid && !hasid) hasid = true;
		 	  	saveid += (saveid=='' ? saveids[i] : ','+saveids[i]);
		 	  	j++;
		 	  	if(j==10 && hasid) break;
		 	  	if(j==9 && !hasid) break;
		 	  }
		 }
     if(hasid) { alert('���ղ��ѱ�������ร�'); return false; }
     else saveid += ','+fid;
		 SetCookie('badgoodid',saveid,1);
	}
	else
	{
		SetCookie('badgoodid',fid,1);
	}
	//document.write("feedback.php?action="+ftype+"&fid="+fid);
	//return;
  myajax = new DedeAjax(taget_obj,false,false,'','','');
  myajax.SendGet2("feedback.php?aid=<?php echo $GLOBALS['aid']; ?>&action="+ftype+"&fid="+fid);
  DedeXHTTP = null;
}
</script>
<body class="commentpage">

<div class="header">
    <div class="top w960 center">
      <div class="titleHead">
        <h1> <a href="<?php echo $GLOBALS['cfg_basehost']; ?>/"><?php echo $GLOBALS['cfg_webname']; ?></a> </h1>
      </div>
      <!-- /title -->
		<div class="welcome">
       <!--  <?php if($cfg_ml->M_ID <= 0) { ?>
            ����,��ӭ��������վ,����û�е�½,����<a href="<?php echo $cfg_memberurl; ?>/index.php">��½</a>����<a href="<?php echo $cfg_memberurl; ?>/index_do.php?fmdo=user&dopost=regnew">ע��</a>.
        <?php }else{  echo $cfg_ml->M_LoginID; ?>,��ӭ���ĵ�½��
                <a href="<?php echo $cfg_memberurl; ?>/index.php">��Ա����</a> |
                <a href="<?php echo $cfg_memberurl; ?>/edit_fullinfo.php">�ҵ�����</a> |
                <a href="<?php echo $cfg_memberurl; ?>/index.php?uid=<?php echo urlencode($cfg_ml->M_LoginID);?>">�ҵĿռ�</a> |
                <a href="<?php echo $cfg_memberurl; ?>/index_do.php?fmdo=login&dopost=exit">�˳���¼</a>
        <?php
        }
        ?> -->
		</div>
      <!-- /banner -->
    </div>
    <!-- /top -->

	</div><!-- /nav -->
</div><!-- /header -->
<div class="w960 clear center mt1 cmt-box">
	<div class="sp-title">
		<h2>����:<a href="javascript:page_url('<?php echo $GLOBALS['typename']; ?>','<?php echo $GLOBALS['aid']; ?>')"><?php echo $GLOBALS['title']; ?></a></h2>
	</div>
	<div>
		<dl class="tbox">
			<dt>
				<strong>�����б����������۽������ѱ�����˿���������������վͬ����۵��֤ʵ��������</strong>
				<span class="label">
<a href='feedback.php?aid=<?php echo $GLOBALS['aid']; ?>&ftype=good' <?php if($ftype=='good') echo "class='thisclass'"; ?>>ֻ������</a>
<a href='feedback.php?aid=<?php echo $GLOBALS['aid']; ?>&ftype=bad' <?php if($ftype=='bad') echo "class='thisclass'"; ?>>ֻ������</a>
<a href='feedback.php?aid=<?php echo $GLOBALS['aid']; ?>&ftype=feedback' <?php if($ftype=='feedback') echo "class='thisclass'"; ?>>ֻ������</a>
<a href='feedback.php?aid=<?php echo $GLOBALS['aid']; ?>' <?php if($ftype=='') echo "class='thisclass'"; ?>>ȫ������</a>
				</span>
			</dt>
			<dd>
				<div class="dede_comment">
<?php
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
	  <div class="decmt-box">
			<div class="decmt-title">
				<span class="moodico"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/<?php if($fields['face']>0) echo "ico-mood-{$fields['face']}.gif"; ?>" />                </span>
				<span class="username"> <?php echo $fields['username']; ?></span>
				<span class="date"> �� <?php echo MyDate('m-d H:i',$fields['dtime']); ?></span>˵��</div><!-- /decmt-title -->
			<div class="decmt-act">
				<span id='goodfb<?php echo $fields['id']; ?>'><a href="#" onclick="postBadGood('goodfb',<?php echo $fields['id']; ?>)">֧��</a>[<?php echo $fields['good']; ?>]</span>
				<span id='badfb<?php echo $fields['id']; ?>'><a href="#" onclick="postBadGood('badfb',<?php echo $fields['id']; ?>)">����</a>[<?php echo $fields['bad']; ?>]</span>
				<span><a href="feedback.php?fid=<?php echo $fields['id']; ?>&action=quote">����</a></span>
			</div><!-- /decmt-act -->
			<div class="decmt-content">
				<?php echo Quote_replace($fields['msg']); ?>
			<img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/<?php echo $fields['bgimg']; ?>" />
			</div><!-- /decmt-content -->
		</div><!-- /decmt-box -->
<?php
}

$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listitem'] = 'info,index,end,pre,next,pageno';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
		</div><!-- /dede_comment -->
			</dd>
		</dl>
	</div>

	<div class="mt1">
		<dl class="tbox">
			<dt>
				<strong>��������</strong>
			</dt>
			<dd>
				<div class="dede_comment_post">
          <form action="feedback.php" method="post" name="feedback">
          <input type="hidden" name="action" value="send" />
          <input type="hidden" name="comtype" value="comments">
          <input type="hidden" name="aid" value="<?php echo $aid; ?>" />
          <input type="hidden" name="isconfirm" value="yes" />
                    <div class="dcmp-title">
						<small>���Ծ����ػ�������ص����߷��棬�Ͻ�����ɫ�顢���������������ۡ�</small>
					</div><!-- /dcmp-title -->
					<div class="dcmp-stand">
						<strong>����:</strong>
							<input type="radio" name="feedbacktype" checked="1" value="feedback" id="dcmp-stand-neu" /><label for="dcmp-stand-neu"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-neu.gif" />����</label>
							<input type="radio" name="feedbacktype" value="good" id="dcmp-stand-good" /><label for="dcmp-stand-good"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-good.gif" />����</label>
							<input type="radio" name="feedbacktype" value="bad" id="dcmp-stand-bad" /><label for="dcmp-stand-bad"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-bad.gif" />����</label>
					</div><!-- /dcmp-stand -->
					<div class="dcmp-content">
						<textarea cols="60" name="msg" rows="5" class="ipt-txt"></textarea>
					</div><!-- /dcmp-content -->

					<div class="dcmp-mood">
						<strong>����:</strong>
						<ul>
								<li><input type="radio" name='face' value='6' checked="1" /><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-6.gif" /></li>
								<li><input type="radio" name='face' value='4'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-4.gif" /></li>
								<li><input type="radio" name='face' value='3'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-3.gif" /></li>
								<li><input type="radio" name='face' value='5'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-5.gif" /></li>
								<li><input type="radio" name='face' value='2'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-2.gif" /></li>
								<li><input type="radio" name='face' value='1'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-1.gif" /></li>
								<li><input type="radio" name='face' value='7'/><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/mood/ico-mood-7.gif" /></li>
						</ul>
					</div><!-- /dcmp-mood -->
					<div class="dcmp-post"><!--δ��½-->
						<div class="dcmp-userinfo">
				<!-- �û����� -->
                    <?php if($cfg_ml->M_ID <= 0) { ?>
                    <input name="username" type="hidden" id="username" size="10" class="nb" />
                    
                   <!--  ���룺 -->
                    <input name="pwd" type="hidden" id="pwd" size="10"class="nb" />
                   <?php
                    }else{  echo $cfg_ml->M_LoginID."&nbsp;"; }
                   ?>
                    <input name="notuser" type="hidden" id="notuser" value="1" />
                   <!--  �������� -->
                <?php if($cfg_feedback_ck=='Y') { ?>
                ��֤�룺<input name="validate" type="text" id="validate" size="10" style="height:18px;width:60px;margin-right:6px;" class="nb" />
                                   <img src='../include/vdimgck.php' width='60' height='24' />

				<?php } ?><span style="padding-left:450px;"/>
                   <button type="submit" class="btn-2">��������</button>
						</div><!-- /dcmp-submit -->
					</div><!-- /dcmp-post -->
                    </form>
				</div><!-- /dede_comment_post -->

			</dd>
		</dl>
	</div>
</div>


<?php include $this->CompilerInclude("../default/footer.htm"); ?>
<!-- /footer -->
</body>
</html>