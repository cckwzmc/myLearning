<?php
if(!isset($GLOBALS['_vars'])) $GLOBALS['_vars'] = array(); 
$fields = array();
?><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>�ĵ�����</title>
<link rel="stylesheet" type="text/css" href="img/base.css">
<script language="javascript" src="js/ieemu.js"></script>
<script language="javascript" src="js/context_menu.js"></script>
<script language="javascript" src="../include/dedeajax2.js"></script>
<script language="javascript">
if(moz) {
	extendEventObject();
	extendElementModel();
	emulateAttachEvent();
}
function viewArc(aid){
	if(aid==0) aid = getOneItem();
	window.open("archives_do.php?aid="+aid+"&dopost=viewArchives");
}
function editArc(aid){
	if(aid==0) aid = getOneItem();
	location="archives_do.php?aid="+aid+"&dopost=editArchives";
}
function updateArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives_do.php?aid="+aid+"&dopost=makeArchives&qstr="+qstr;
}
function checkArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives_do.php?aid="+aid+"&dopost=checkArchives&qstr="+qstr;
}
function moveArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives_do.php?aid="+aid+"&dopost=moveArchives&qstr="+qstr;
}
function adArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives_do.php?aid="+aid+"&dopost=commendArchives&qstr="+qstr;
}
function delArc(aid){
	var qstr=getCheckboxItem();
	if(aid==0) aid = getOneItem();
	location="archives_do.php?qstr="+qstr+"&aid="+aid+"&dopost=delArchives";
}
//�����Ĳ˵�
function ShowMenu(evt,obj,aid,atitle)
{
  var popupoptions
  popupoptions = [
    new ContextItem("����ĵ�",function(){ viewArc(aid); }),
    new ContextItem("�༭�ĵ�",function(){ editArc(aid); }),
    new ContextSeperator(),
    new ContextItem("����HTML",function(){ updateArc(aid); }),
    new ContextItem("����ĵ�",function(){ checkArc(aid); }),
    new ContextItem("�Ƽ��ĵ�",function(){ adArc(aid); }),
    new ContextSeperator(),
    new ContextItem("ɾ���ĵ�",function(){ delArc(aid); }),
    new ContextSeperator(),
    new ContextItem("ȫ��ѡ��",function(){ selAll(); }),
    new ContextItem("ȡ��ѡ��",function(){ noSelAll(); }),
    new ContextSeperator(),
    new ContextItem("�رղ˵�",function(){})
  ]
  ContextMenu.display(evt,popupoptions);
  //location="catalog_main.php";
}

//���ѡ���ļ����ļ���
function getCheckboxItem()
{
	var allSel="";
	if(document.form2.arcID.value) return document.form2.arcID.value;
	for(i=0;i<document.form2.arcID.length;i++)
	{
		if(document.form2.arcID[i].checked)
		{
			if(allSel=="")
				allSel=document.form2.arcID[i].value;
			else
				allSel=allSel+"`"+document.form2.arcID[i].value;
		}
	}
	return allSel;
}

//���ѡ������һ����id
function getOneItem()
{
	var allSel="";
	if(document.form2.arcID.value) return document.form2.arcID.value;
	for(i=0;i<document.form2.arcID.length;i++)
	{
		if(document.form2.arcID[i].checked)
		{
				allSel = document.form2.arcID[i].value;
				break;
		}
	}
	return allSel;
}

function selAll()
{
	for(i=0;i<document.form2.arcID.length;i++)
	{
		if(!document.form2.arcID[i].checked)
		{
			document.form2.arcID[i].checked=true;
		}
	}
}
function noSelAll()
{
	for(i=0;i<document.form2.arcID.length;i++)
	{
		if(document.form2.arcID[i].checked)
		{
			document.form2.arcID[i].checked=false;
		}
	}
}
function editTitle(aid)
{
   var taget_obj = document.getElementById('arc'+aid);
   var myajax = new DedeAjax(taget_obj,false,false,"","","");
   myajax.SendGet2("ajax.php?action=show&aid="+aid);
   DedeXHTTP = null;
}
function postTitle(aid){
   obj = document.getElementById('arc'+aid);
   v = document.getElementById('v_'+aid).value;
   var myajax = new DedeAjax(obj,false,false,"","","");
   <?php
   if(strtolower(substr($cfg_soft_lang, 0, 3)) == 'utf') {
   ?>
   myajax.AddKeyUtf8('action','post');
   myajax.AddKeyUtf8('aid',aid);
   myajax.AddKeyUtf8('title',v);
   myajax.SendPost2('ajax.php');
   <?php
   }
   elseif (strtolower(substr($cfg_soft_lang, 0, 2)) == 'gb') {
   	 echo 'myajax.SendGet2("ajax.php?action=show&aid="+aid+"&action=post&title="+v);';
   }
   ?>
   DedeXHTTP = null;
}
</script>
</head>
<body leftmargin="8" topmargin="8" background='img/allbg.gif' onLoad="ContextMenu.intializeContextMenu()">

<!--  ����ת��λ�ð�ť  -->
<table width="98%" border="0" cellpadding="0" cellspacing="1" bgcolor="#D1DDAA" align="center">
<tr>
 <td height="26" background="img/newlinebg3.gif">
  <table width="98%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  <td align="center">
  <input type='button' class="coolbg np" onClick="location='catalog_do.php?channelid=<?php echo $GLOBALS['channelid']; ?>&cid=<?php echo $GLOBALS['cid']; ?>&dopost=addArchives';" value='�����ĵ�' />
  <?php if($adminid==$mid){ ?>
	<input type='button' class="coolbg np" onClick="location='content_list.php?cid=<?php echo $GLOBALS['cid']; ?>&mid=0';" value='ȫ���ĵ�' />
	<?php
}
else{
?>
	<input type='button' class="coolbg np" onClick="location='content_list.php?cid=<?php echo $GLOBALS['cid']; ?>&mid=<?php echo $cuserLogin->getUserID(); ?>';" value='�ҵ��ĵ�' />
	<?php }  echo $GLOBALS['CheckUserSend']; ?>
 <input type='button' class="coolbg np" onClick="location='catalog_main.php';" value='��Ŀ����' />
 <input type='button' class="coolbg np" name='bb1' onClick="location='makehtml_list.php?cid=<?php echo $GLOBALS['cid']; ?>';" value='�����б�' />
 <input type='button' class="coolbg np" name='bb2' onClick="location='makehtml_archives.php?cid=<?php echo $GLOBALS['cid']; ?>';" value='�����ĵ�' />
 <input type='button' class="coolbg np" name='bb1' onClick="location='recycling.php?cid=<?php echo $GLOBALS['cid']; ?>';" value='���»���վ' />
 </td>
 </tr>
</table>
</td>
</tr>
</table>
  
<!--  �����б�   -->
<form name="form2">
<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
<tr bgcolor="#E7E7E7">
	<td height="24" colspan="10" background="img/tbg.gif">
		�� <?php echo $GLOBALS['positionname']; ?>�ĵ��б� &nbsp;(ʹ������Ҽ����г��ò���)
	</td>
</tr>
<tr align="center" bgcolor="#FAFAF1" height="22">
	<td width="6%">ID</td>
	<td width="4%">ѡ��</td>
	<td width="28%">���±���</td>
	<td width="10%">¼��ʱ��</td>
	<td width="10%">��Ŀ</td>
	<td width="8%">���</td>
	<td width="6%">HTML</td>
	<td width="8%">Ȩ��</td>
	<td width="8%">������</td>
	<td width="10%">����</td>
</tr>
<?php
$atts = array();
$atts['tagname'] = 'datalist';
$blockValue = $this->refObj->GetArcList($atts,$this->refObj,$fields); 
foreach( $blockValue as $key=>$fields )
{
?>
<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='#FCFDEE';" onMouseOut="javascript:this.bgColor='#FFFFFF';"
	height="22" oncontextmenu="ShowMenu(event,this,<?php echo $fields['id']; ?>,'<?php echo urlencode($fields['title']); ?>')">
	<td><?php echo $fields['id']; ?></td>
	<td><input name="arcID" type="checkbox" id="arcID" value="<?php echo $fields['id']; ?>" class="np"></td>
	<td align='left' onDblClick="editTitle(<?php echo $fields['id']; ?>);"><span id="arc<?php echo $fields['id']; ?>">
<a href='archives_do.php?aid=<?php echo $fields['id']; ?>&dopost=editArchives'
	oncontextmenu="ShowMenu(event,this,<?php echo $fields['id']; ?>,'<?php echo urlencode($fields['title']); ?>')">
	<u><?php echo $fields['title']; ?></u>
</a></span><?php echo IsCommendArchives($fields['flag']); ?>
	</td>
	<td><?php echo GetDateMk($fields['senddate']); ?></td>
	<td><?php echo $fields['typename']; ?></td>
	<td><?php echo $fields['click']; ?></td>
	<td><?php echo IsHtmlArchives($fields['ismake']); ?></td>
	<td><?php echo GetRankName($fields['arcrank']); ?></td>
	<td><?php echo $fields['adminname']; ?></td>
	<td>
		<a href="javascript:editArc(<?php echo $fields['id']; ?>)">�༭</a> |
		<a href="javascript:viewArc(<?php echo $fields['id']; ?>)">Ԥ��</a>
	</td>
</tr>
<?php
}
?>
<tr bgcolor="#FAFAF1">
<td height="28" colspan="10">
	&nbsp;
	<a href="javascript:selAll()" class="coolbg">ȫѡ</a>
	<a href="javascript:noSelAll()" class="coolbg">ȡ��</a>
	<a href="javascript:updateArc(0)" class="coolbg">&nbsp;����&nbsp;</a>
	<a href="javascript:checkArc(0)" class="coolbg">&nbsp;���&nbsp;</a>
	<a href="javascript:adArc(0)" class="coolbg">&nbsp;�Ƽ�&nbsp;</a>
	<a href="javascript:moveArc(0)" class="coolbg">&nbsp;�ƶ�&nbsp;</a>
	<a href="javascript:delArc(0)" class="coolbg">&nbsp;ɾ��&nbsp;</a>
</td>
</tr>
</form>
<tr align="right" bgcolor="#EEF4EA">
	<td height="36" colspan="10" align="center">
		<?php
$atts = array();
$atts['tagname'] = 'pagelist';
$atts['listsize'] = '3';
 echo $this->refObj->GetPageList($atts,$this->refObj,$fields); 
?>

	</td>
</tr>
</table>

<!--  ��������  -->
<form name='form3' action='content_list.php' method='get'>
<input type='hidden' name='dopost' value='listArchives' />

<table width='98%'  border='0' cellpadding='1' cellspacing='1' bgcolor='#CBD8AC' align="center" style="margin-top:8px">
  <tr bgcolor='#EEF4EA'>
    <td background='img/wbg.gif' align='center'>
      <table border='0' cellpadding='0' cellspacing='0'>
        <tr>
          <td width='90' align='center'>��ѡ����Ŀ��</td>
          <td width='160'>
          <select name='cid' style='width:150'>
          <option value='0'>ѡ�����...</option>
          	<?php echo $GLOBALS['optionarr']; ?>
          </select>
        </td>
        <td width='70'>
          �ؼ��֣�
        </td>
        <td width='160'>
          	<input type='text' name='keyword' value='<?php echo $GLOBALS['keyword']; ?>' style='width:150px' />
        </td>
        <td width='110'>
    		<select name='orderby' style='width:80px'>
            <option value='id'>����...</option>
            <option value='pubdate'>����ʱ��</option>
            <option value='sortrank'>�ö�Ȩֵ</option>
            <option value='click'>�����</option>
            <option value='postnum'>���ۻ���</option>
            <option value='lastpost'>�������</option>
      	</select>
      </td>
        <td>
          <input name="imageField" type="image" src="img/button_search.gif" width="60" height="22" border="0" class="np" />
        </td>
       </tr>
      </table>
    </td>
  </tr>
</table>
</form>

</body>
</html>