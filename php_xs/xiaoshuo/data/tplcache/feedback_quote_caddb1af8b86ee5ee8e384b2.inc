<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=<?php echo $GLOBALS['cfg_soft_lang']; ?>" />
<title>�û����ۣ�<?php echo $row['arctitle'];?></title>
<link href="<?php echo $GLOBALS['cfg_templeturl']; ?>/style/dedecms.css" rel="stylesheet" media="screen" type="text/css" />
<link href="../style/dedecms.css" rel="stylesheet" media="screen" type="text/css" />
<script src="<?php echo $GLOBALS['cfg_cmspath']; ?>/images/js/cn_conver_py.js"></script> 
</head>
<body class="commentpage">
<div class="header">
    <div class="top w960 center">
      <div class="titleHead">
        <h1> <a href="<?php echo $GLOBALS['cfg_basehost']; ?>"><?php echo $GLOBALS['cfg_webname']; ?></a> </h1>
      </div>
      <!-- /title -->
		<div class="welcome">
       <!--  <?php if($cfg_ml->M_ID <= 0) { ?>
            ����,��ӭ��������վ,����û�е�½,����<a href="<?php echo $cfg_memberurl; ?>/index.php">��½</a>����<a href="<?php echo $cfg_memberurl; ?>/index_do.php?fmdo=user&dopost=regnew">ע��</a>.
        <?php }else{  echo $cfg_ml->M_LoginID; ?>,��ӭ���ĵ�½��
                <a href="<?php echo $cfg_memberurl; ?>/index.php">��Ա����</a> |
                <a href="<?php echo $cfg_memberurl; ?>/edit_fullinfo.php">�ҵ�����</a> |
                <a href="<?php echo $myurl;?>">�ҵĿռ�</a> |
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
		<h2>����:<?php echo $row['arctitle'];?></h2>
	</div>
	<div>
		<dl class="tbox">
			<dt>
				<strong>����˵����</strong>
			</dt>
			<dd>
				<div class="dede_comment">
              <div class="decmt-box">
              ѡ��ͬ���������ͻ�Ӱ�����µĻ��֣����鲻Ӱ����֣�����㲻�볤ƪ���ۣ�ֻ����������/������ͼ�꣬ͬ������Ϊ�������ӻ���ٻ��֡�<br />
           		ѡ���������ۡ��󣬲����Ƿ��½������ʾ��Ŀռ����ӣ����Ծ����ػ�������ص����߷��棬�Ͻ�����ɫ�顢���������������ۡ�</div>
				</div><!-- /dede_comment -->
			</dd>
		</dl>
	</div>

	<div class="mt1">
		<dl class="tbox">
			<dt><strong>��������</strong></dt>
			<dd>
				<div class="dede_comment_post">
          <form action="feedback.php" method="post" name="feedback">
          <input type="hidden" name="action" value="send" />
          <input type="hidden" name="comtype" value="reply" />
          <input type="hidden" name="fid" value="<?php echo $row['id']; ?>" />
          <input type="hidden" name="isconfirm" value="yes" />
          <input type="hidden" name="typeid" value="<?php echo $row['typeid'];?>" />
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
					<div class="dcmp-stand">
						<strong>����:</strong>
							<input type="radio" name="feedbacktype" checked="1" value="feedback" id="dcmp-stand-neu" /><label for="dcmp-stand-neu"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-neu.gif" />����</label>
							<input type="radio" name="feedbacktype" value="good" id="dcmp-stand-good" /><label for="dcmp-stand-good"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-good.gif" />����</label>
							<input type="radio" name="feedbacktype" value="bad" id="dcmp-stand-bad" /><label for="dcmp-stand-bad"><img src="<?php echo $GLOBALS['cfg_templeturl']; ?>/images/cmt-bad.gif" />����</label>
					</div><!-- /dcmp-stand -->
					<div class="dcmp-content">
                    	<input type="hidden" name="quotemsg" value="{quote}<?php echo '{title}'.$row['username'].' ��ԭ����{/title}{content}'.$row['msg'].'{/content}';?>{/quote}" />
						<textarea cols="60" name="msg" rows="5" class="ipt-txt"></textarea>
					</div><!-- /dcmp-content -->
                    <div class="dcmp-title">
						<small>���Ծ����ػ�������ص����߷��棬�Ͻ�����ɫ�顢���������������ۡ�</small>
					</div><!-- /dcmp-title -->
					<div class="dcmp-post"><!--δ��½-->
						<div class="dcmp-userinfo">
<!-- �û����� -->
                    <?php if($cfg_ml->M_ID <= 0) { ?>
                    <input name="username" type="hidden" id="username" size="10" class="nb" />
                   
                  <!--   ���룺 -->
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
                 <button type="button" onClick='javascript:if(document.feedback.msg.value!="") document.feedback.submit(); else alert("�������ݲ���Ϊ�գ�");'  class="btn-2">��������</button>
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