<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ա���� -<?php echo $cfg_webname; ?></title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script type="text/javascript" src="js/box.js"></script>
</head>
<body>
<?php include(DEDEMEMBER."/templets/head.htm"); ?>
<div class="wrapper lineT">
  <div class="mainBox">
    <div class="main">
      <?php include(DEDEMEMBER."/templets/menu.php"); ?>
      <div class="dedeCenter" id="personal">
        <!--������Ϣ -->
        <div class="message">
          <div class="visualize fLeft">
          	<img class="mB10" src="<?php echo (!empty($cfg_ml->fields['face']) ? $cfg_ml->fields['face'] : "images/nopic.gif"); ?>" width="150" height="150" alt="��������" />
            <ul class="black">
              <li><a class="icon upImage" href="edit_face.php" title="�޸ĸ���ͷ��">�ϴ�ͷ��</a></li>
              <li><a class="icon mySetting" href="edit_baseinfo.php" title="���ø�������">��������</a></li>
            </ul>
            <div class="clear"></div>
          </div>
          <div class="datum fLeft mT10">
            <h3 class="userName"><?php echo $cfg_ml->M_LoginID;?><em class="mL10"><?php echo $cfg_ml->M_MbType; ?>�û�</em></h3>
            <div class="userState mTB10"><span class="fLeft"><?php echo $cfg_ml->GetSta($dsql); ?></span>
              <div class="clear"></div>
              <script type="text/javascript">
function setTab(m,n){
var tli=document.getElementById("menu"+m).getElementsByTagName("li");
var mli=document.getElementById("main"+m).getElementsByTagName("dl");
for(i=0;i<tli.length;i++){
   tli[i].className=i==n?"select":"";
   mli[i].className=i==n?"block":"none";
}
}
</script>
              <div id="Upbox" style="display:none;">
                <div class="lineBorder mTB10">
                  <div class="titleTabBar bgGreen lineB">
                    <ul id="menuUp">
                      <li class="select"><a onclick="setTab('Up',0)">��Ҫ����</a></li>
                      <li><a onclick="setTab('Up',1)">��Ҫ��ֵ</a></li>
                    </ul>
                  </div>
                  <div id="mainUp" class=" mTB10 mL10 mR10">
                    <form name="formrank" action="index_do.php" method="post">
                      <dl class="block">
                        <dt><strong>�ʺ�����</strong></dt>
                        <?php
                        $sRanks = array();
                        $dsql->SetQuery("Select membername,rank From `#@__arcrank` where rank>-1 order by rank asc");
                        $dsql->Execute();
                        $maxrank = 0;
                        while($row = $dsql->GetObject())
                        {
                            if($row->rank > $maxrank) $maxrank = $row->rank;
                            $sRanks[$row->rank] = $row->membername;
                        }
                        $sRanks[0] = "���ƻ�Ա";
                        ?>
                        <dd class="mT10">��Ŀǰ�ļ���<?php echo $sRanks[$cfg_ml->M_Rank]; ?></dd>
                        <?php
                        if($cfg_ml->M_Rank >= $maxrank){
                        ?>
                        <dd class="mTB10">���Ѿ�����߼���Ļ�Ա��������������
                          <button class="mL10 buttonGary2" type="button" onclick="tb_remove()">�ر�</button>
                        </dd>
                        <?php }else{ ?>
                        <input type="hidden" name="fmdo" value="user" />
                        <input type="hidden" name="dopost" value="uprank" />
                        <dd class="mT10">���������ļ���
                          <select name="uptype" id="uptype">
                            <?php foreach($sRanks as $k => $v){  if($k > $cfg_ml->M_Rank){?>
                            <option value="<?php echo $k;?>"><?php echo $v;?></option>
                            <?php } }?>
                          </select>
                        </dd>
                        <dd>
                          <hr />
                          <button class="button2" type="submit">�ύ</button>
                          <button class="mL10 buttonGary2" type="button" onclick="tb_remove()">ȡ��</button>
                        </dd>
                        <?php }?>
                      </dl>
                    </form>
                    <form name="formmoney" action="index_do.php" method="post">
                      <dl class="none">
                        <dt><strong>�˻���ֵ</strong></dt>
                        <dd class="mT10">
                          <input type="hidden" name="fmdo" value="user" />
                          <input type="hidden" name="dopost" value="addmoney" />
                          �������
                          <input class="text" name="money" type="text" id="money" value="500" size="6" />
                        </dd>
                        <dd class="mTB10">��֤�룺
                          <input class="text" name="vdcode" type="text" id="vdcode" size="4" style="width:160px;text-transform:uppercase;" />
                          <img id="vdimgck" src="../include/vdimgck.php" width="50" height="20" alt="�����壿�������" align="absmiddle" style="cursor:pointer" onclick="this.src=this.src+'?'" /></dd>
                        <dd>
                          <hr />
                          <button class="button2" type="submit">�ύ</button>
                          <button class="mL10 buttonGary2" type="button" onclick="tb_remove()">ȡ��</button>
                        </dd>
                      </dl>
                    </form>
                    <div class="clear"></div>
                  </div>
                </div>
              </div>
            </div>
            <ul class="entry">
              <li><a href="pm.php" title="����Ϣ">��Ϣ</a>(<?php echo $pms['nums'];?>)</li>
              <li><a href="caicai.php" title="����������">����</a>(<?php echo $minfos['feedback']; ?>)</li>
              <li><a href="mystow.php" title="�ղؼ�">�ղ�</a>(<?php echo $minfos['stow']; ?>)</li>
              <li><a href="javascript:;">����</a>(<?php echo $minfos['archives']; ?>)</li>
              <li><a href="content_list.php?channelid=1" title="�ѷ���������">����</a>(<?php echo $minfos['article']; ?>)</li>
              <li><a href="content_list.php?channelid=2" title="����ͼ��">ͼ��</a>(<?php echo $minfos['album']; ?>)</li>
              <li><a href="content_list.php?channelid=3" title="�ѷ���������">����</a>(<?php echo $minfos['soft']; ?>)</li>
              <li><a href="shops_products.php" title="�������Ʒ">��Ʒ</a>(<?php echo $minfos['shop']; ?>)</li>              
            </ul>
            <div> <span class="fRight" style='background-color:#F8FEDA;clear:both;padding:3px 5px 3px 5px;margin-top:12px;'> <a href="#" onclick="location='buy.php';return false;" class="thickbox">[�������]</a>&nbsp; <a href="#TB_inline?height=210&width=380&inlineId=Upbox&modal=true" title="�������ֵ" class="thickbox">[��������/��ֵ]</a> </span> </div>
            <div class="clear"></div>
            <script type="text/javascript">
			$("document").ready(function(){
				$(".tipClose").click(function(){
					$(this).parent(".tip").animate({ height: 'toggle', opacity: 'toggle' }, "hide");
					})
			});
			</script>
            <!--��ʾ -->
            <?php
        switch($cfg_ml->M_Spacesta)
        {
        	case '-2':
        		echo "<div class='tip'>
            <div class='tipClose fRight'>������ʾ</div>
            <div class='tipContent fLeft icon titTip'>�ѱ�����</div>
            </div>";
        		break;
        	case '-1':
        		echo "<div class='tip'>
            <div class='tipClose fRight'>������ʾ</div>
            <div class='tipContent fLeft icon titTip'>δͨ����ˣ�<a href='edit_space_info.php'>�޸Ŀռ�����</a></div>
            <div class='clear'></div>
            </div>";
        		break;
        	case '0':
        		echo "<div class='tip'>
            <div class='tipClose fRight'>������ʾ</div>
            <div class='tipContent fLeft icon titTip'>���Ŀռ���Ϣ��������<a href='edit_space_info.php'>[��д�ռ�����]</a></div>
            <div class='clear'></div>
            </div>";
        		break;
        	case '1':
        		echo "<div class='tip'>
            <div class='tipClose fRight'>������ʾ</div>
            <div class='tipContent fLeft icon titTip'>����û���������ĸ������ϣ�<a href='edit_fullinfo.php'>[������ϸ����]</a></div>
            <div class='clear'></div>
            </div>";
        		break;
        	default:
        		echo "";
        		break;
        }
        ?>
          </div>
        </div>
        <div class="titleBar bgGreen lineT">
          <h5 class="fLeft icon titFangle">������</h5>
          <div class="fRight"><a href="search.php">�����˵���</a></div>
          <div class="clear"></div>
        </div>
        <div class="fangle">
          <dl class="artList mL10 mR10">
            <?php
foreach ($archives as $archive)
{
?>
            <dd> <em><a class="yellow" href="index.php?uid=<?php echo $archive['userid'];?>" target="_blank"><?php echo $archive['userid'];?></a> <?php echo MyDate('m-d H:i', $archive['senddate']);?></em> <span><a href="<?php echo $archive['htmlurl'];?>" title="<?php echo $archive['title'];?>" target="_blank"><?php echo $archive['title'];?></a></span> </dd>
            <?php
}
?>
            <dt><a class="titMore icon" href="caicai.php">����</a></dt>
          </dl>
        </div>
        <!--˭�������� -->
        <div class="whoSeeMe">
          <div class="titleBar bgGreen lineT">
            <h5 class="fLeft icon titWhoSeeMe">˭��������</h5>
          </div>
          <ul class="picList textCenter">
            <?php
$atts = array();
$atts['tagname'] = 'newvisitor';
$atts['row'] = '6';
$blockValue = MakePublicTag($atts,$this->refObj,$fields); 
if(is_array($blockValue) && count($blockValue) > 0){
foreach( $blockValue as $key=>$fields )
{
?>
            <li>
              <p><a target="_blank" href="<?php echo $fields['url']; ?>"><img src="<?php echo $fields['face']; ?>" width="45" height="50" alt="<?php echo $fields['uname']; ?>" /></a></p>
              <span><a target="_blank" href="<?php echo $fields['url']; ?>"><?php echo $fields['uname']; ?></a></span></li>
            <?php
}
}
?>
          </ul>
          <div class="clear"></div>
          <div class="titleBar bgGreen lineT">
            <h5 class="fLeft icon titStow">�ҵ��ղ�</h5>
            <div class="fRight"><a href="mystow.php">����</a></div>
            <div class="clear"></div>
          </div>
          <div class="Stow">
            <dl class="artList mL10 mR10">
              <?php
foreach ($favorites as $favorite)
{
?>
              <dd><em><?php echo MyDate('m-d H:i', $favorite['addtime']);?></em><span><a href="archives_do.php?dopost=viewArchives&aid=<?php echo $favorite['aid'];?>" title="<?php echo $favorite['title'];?>" target="_blank"><?php echo $favorite['title'];?></a></span> </dd>
              <?php
}
?>
              <dt>&nbsp;</dt>
            </dl>
          </div>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="dedeRight">
      <!--��Ϣͳ�� -->
      <div class="titleBar bgGaryImg">
        <h5 class="fLeft">��Ϣͳ��</h5>
      </div>
      <dl class="statistics overflow mL10 mR10">
        <dt>�ռ��������</dt>
        <dd><?php echo $minfos['homecount']; ?></dd>
        <dt>�ĵ��ܵ����</dt>
        <dd><?php echo $minfos['pagecount']; ?></dd>
        <dt>����������</dt>
        <dd><?php echo $minfos['friend']; ?></dd>
        <dt>�ռ�汾��</dt>
        <dd>
          <?php
        if($cfg_mb_lit=='Y') echo '�����';
        else echo '��ȫ��';
        ?>
        </dd>
        <dt>�ϴ����ƣ�</dt>
        <dd><?php echo $cfg_mb_upload_size; ?> KB</dd>
        <dt>�ռ��С��</dt>
        <dd><?php echo $minfos['totaluse']."/".$cfg_mb_max; ?> MB</dd>
      </dl>
      <div class="clear"></div>
      <!--�����б� -->
      <script type="text/javascript">
		$("document").ready(function(){
			$(".buddy dt").click(function(){$(this).toggleClass("close");$(this).next("dd").toggle();});
			$(".buddy li").hover(function(){$(this).addClass("hover"); },function(){$(this).removeClass("hover"); });
		});
		</script>
      <div class="titleBar bgGaryImg">
        <h5 class="fLeft">�����б�</h5>
        <span class="fRight"><a href="myfriend.php" title="���ѹ���">����</a></span> </div>
      <dl class="buddy overflow mL10 mR10">
        <dt class="buddySort icon">���к���</dt>
        <dd class="buddyList">
          <ul>
            <?php
foreach ($friends as $friend)
{
?>
            <li class="thickbox"><span class="fLeft icon16 titOnline"></span><a href="index.php?uid=<?php echo $friend['floginid'];?>" target="_blank"><?php echo $friend['floginid'];?></a></li>
            <?php
}
?>
          </ul>
        </dd>
        <dt class="buddySort icon"><a href="myfriend.php?ftype=1">�ر��ע</a></dt>
        <dt class="buddySort icon"><a href="myfriend.php?ftype=-1">������</a></dt>
      </dl>
      <hr />
      <!--�������� -->
      <div class="buddySearch textCenter">
        <form action="search.php">
          <input class="text" type="text" name="keyword" value="�����º���" style="width:150px;" />
          <button class="button1" type="submit">��</button>
        </form>
      </div>
    </div>
    <div class="clear"></div>
  </div>
</div>
<?php include(DEDEMEMBER."/templets/foot.htm"); ?>
</body>
</html>