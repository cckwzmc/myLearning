<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�������ݹ���</title>
<?php include(DEDEMEMBER."/templets/head_inc.htm"); ?>
<script type="text/javascript" language="javascript">
function ChangeImage(surl){ document.getElementById('picview').src = surl; }
//���ѡ���ļ����ļ���
function getCheckboxItem(){
	var allSel="";
	if(document.form1.aids.value) return document.form1.aids.value;
	for(i=0;i<document.form1.aids.length;i++)
	{
		if(document.form1.aids[i].checked){
			if(allSel=="")
			allSel=document.form1.aids[i].value;
			else
			allSel=allSel+","+document.form1.aids[i].value;
		}
	}
	return allSel;
}

function AllSel(){
	for(i=0;i<document.form1.aids.length;i++){
		document.form1.aids[i].checked = true;
	}
}

function NoneSel(){
	for(i=0;i<document.form1.aids.length;i++){
		document.form1.aids[i].checked = false;
	}
}

function DelSel(){
	var nid = getCheckboxItem();
	if(nid==""){
		alert("��ѡ����Ŀ!\r\n");
		return ;
	}
	location.href = "archives_do.php?dopost=delUploads&ids="+nid;
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
        <div class="location">�������ݹ���</div>
        <div class="titleBar bgGreen lineTB">
          <form class="fRight" name='forms' method='post' action='uploads.php'>
            <input name="keyword" type="text" id="keyword" class="text" value="<?php echo $keyword?>" />
            <select name='mediatype'>
              <option value='0'>�ļ�����</option>
              <option value='1'<?php if($mediatype==1) echo " selected='1' "; ?>>ͼƬ</option>
              <option value='2'<?php if($mediatype==2) echo " selected='1' "; ?>>FLASH</option>
              <option value='3'<?php if($mediatype==3) echo " selected='1' "; ?>>��Ƶ/��Ƶ</option>
              <option value='4'<?php if($mediatype==4) echo " selected='1' "; ?>>��������</option>
            </select>
            <button class="button2 mL10" type="submit">����</button>
          </form>
        </div>
        <form name="form1" class="mL10 mR10 mTB10">
          <table cellspacing="1" class="list">
            <thead>
              <tr>
                <th align="left" width="1%"><input name="checkedClick" type="checkbox" value="" id="checkedClick"></th>
                <th >�ļ�����</th>
                <th width="5%">��С </th>
                <th width="5%">���� </th>
                <th width="10%">�ϴ�ʱ�� </th>
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
              <td><input type='checkbox' name='aids' id='aids<?php echo $fields['aid']?>' value='<?php echo $fields['aid']; ?>' class='np' /></td>
              <td><a href='<?php echo $fields['url']?>' target='_blank'><?php echo GetImageView($fields['url'],$fields['mediatype']);  echo $fields['title']; ?> </a> </td>
              <td align="center"><?php echo GetFileSize($fields['filesize']); ?></td>
              <td align="center"><?php echo MediaType($fields['mediatype'],$fields['url']); ?></td>
              <td align="center"><?php echo MyDate("Y-m-d H:i",$fields['uptime']); ?></td>
              <td align="center"><a href='uploads_edit.php?aid=<?php echo $fields['aid']; ?>'>����</a> | <a href='archives_do.php?dopost=delUploads&amp;aid=<?php echo $fields['aid']; ?>'>ɾ��</a> </td>
            </tr>
            <?php
}
?>
            </tbody>
            
            <tfoot>
              <tr>
                <td colspan="6"><div>
                    <div class="fLeft">
                      <button class="buttonGary2" onclick="DelSel();">ɾ��</button>
                    </div>
                    <div class="pages fRight"><?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '5';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>
</div>
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