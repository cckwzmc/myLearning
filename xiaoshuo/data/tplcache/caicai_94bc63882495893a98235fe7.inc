<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ȿ͡���ԭ�����»��ܲȵģ�</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	$("div[class*='good'],div[class*='bad']").bind("click", function(){
		var type = $(this).attr('class'),aid = $(this).attr('id');
		var saveId = getCookie('diggid');
		if(saveId != null)
		{
			var saveIds = saveId.split(',');
			var hasId = false;
			j = 1;saveId = '';
			for(i=saveIds.length-1;i>=0;i--)
			{
				if(saveIds[i]==aid && hasId) continue;
				else
				{
					if(saveIds[i]==aid && !hasId) hasId = true;
					saveId += (saveId=='' ? saveIds[i] : ','+saveIds[i]);
					j++;
					if(j==20 && hasId) break;
					if(j==19 && !hasId) break;
				}
			}
			
			if(hasId) { alert("���Ѿ������������벻Ҫ�ظ����� ��"); return false; }
			else saveId += ','+aid;
			setCookie('diggid',saveId,1);
		}
		else
		{
			setCookie('diggid',aid,1);
		}		
		$.ajax({type: 'POST',url: "../plus/feedback.php",
			data: "action="+type+"&aid="+aid,
			dataType: 'text',
			success: function(result){$("#"+type+aid).html(result);}
		});
		
	});
});

//��дcookie����
function getCookie(c_name)
{
	if (document.cookie.length > 0)
	{
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1)
		{
			c_start = c_start + c_name.length + 1;
			c_end   = document.cookie.indexOf(";",c_start);
			if (c_end == -1)
			{
				c_end = document.cookie.length;
			}
			return unescape(document.cookie.substring(c_start,c_end));
		}
	}
	return null
}

function setCookie(c_name,value,expiredays)
{
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + expiredays);
	document.cookie = c_name + "=" +escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString()); //ʹ���õ���Чʱ����ȷ������toGMTString()
}

function postBadGood(type,id){}
</script>
</head>
<body>
<?php include(DEDEMEMBER."/templets/head.htm"); ?>
<div class="wrapper lineT">
  <div class="mainBox">
    <div class="main">
      <div class="dedeMain">
        <div class="location">���Ȳ�</a></div>
        <div class="menuBox">��Ŀѡ��<?php
$atts = array();
$atts['tagname'] = 'block';
$atts['row'] = '12';
$blockValue = $this->refObj->GetCatalog($atts,$this->refObj,$fields); 
if(is_array($blockValue) && count($blockValue) > 0){
foreach( $blockValue as $key=>$fields )
{
?><a href='caicai.php?tid=<?php echo $fields['id']; ?>'><?php echo $fields['typename']; ?></a>|<?php
}
}
?> </div>
        <div class="clear"></div>
        <div class="mTB10">
          <div class="titleBar bgGrass lineB mB10">
            <div class="fLeft"><strong>�ĵ��б�</strong></div>
            <div class="fRight titSecondary white" style="margin-top:5px;"> <em class="mR10">
              <?php if($sort!='lastpost'){ ?>
              <a href='caicai.php?tid=<?php echo $GLOBALS['tid']; ?>'>ʱ��</a>
              <?php } ?>
              </em> <em class="mR10">
              <?php if($sort!='goodpost'){ ?>
              <a href='caicai.php?tid=<?php echo $GLOBALS['tid']; ?>&sort=goodpost'>����</a>
              <?php } ?>
              </em> <em class="mR10">
              <?php if($sort!='badpost'){ ?>
              <a href='caicai.php?tid=<?php echo $GLOBALS['tid']; ?>&sort=badpost'>����</a>
              <?php } ?>
              </em> <em>
              <?php if($sort!='scores'){ ?>
              <a href='caicai.php?tid=<?php echo $GLOBALS['tid']; ?>&sort=scores'>����</a>
              <?php } ?>
              </em> </div>
          </div>
          <div class="allList">
            <ul>
              <?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?>
              <li>
                <div class="img"><a href='<?php echo $fields['spaceurl']; ?>' target='_blank'><img src='<?php echo $fields['face']; ?>' alt='<?php echo $fields['alttitle']; ?>' /></a></div>
                <div class="nametxt">
                  <p class="p1"><strong><a href='<?php echo $fields['arcurl']; ?>' target='_blank'><?php echo $fields['title']; ?></a></strong></p>
                  <p class="p2"><?php echo cn_substr($fields['description'],90); ?>...</p>
                  <p class="p2">������ۣ�<?php echo $fields['lastpost']; ?></p>
                  <p class="p2">�ܻ��֣�<em class="yellow mR10"><?php echo $fields['scores']; ?></em><a href="../plus/feedback.php?aid=<?php echo $fields['id']; ?>">����</a></p>
                </div>
                <div class="caicai">
                  <div class='good' title='����' id="<?php echo $fields['id']; ?>"> <span id='good<?php echo $fields['id']; ?>'><?php echo $fields['goodpost']; ?></span> </div>
                  <div class='bad' title='����' id="<?php echo $fields['id']; ?>"> <span id='bad<?php echo $fields['id']; ?>'><?php echo $fields['badpost']; ?></span> </div>
                </div>
                <div class="clear"></div>
              </li>
              <?php
}
?>
            </ul>
          </div>
          <div class="pages fRight mTB10"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>

            <div class="clear"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
<?php include(DEDEMEMBER."/templets/foot.htm"); ?>
</body>
</html>