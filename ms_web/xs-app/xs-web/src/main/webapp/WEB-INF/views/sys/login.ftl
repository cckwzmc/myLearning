<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Toney Administrator's login</title>
<link href="${staticUrl}css/login.css" rel="stylesheet" type="text/css"/>
<#include "common/sys_head.ftl">
<script type="text/javascript">
	$(document).ready(){
		if(top!=this) {
			top.location=this.location;
		}
		$(function() {
			$("#username").focus();
		});
	}
</script>
</head>
<body>
	<div id="login-box">
	   <div class="login-top"><a href="${domainUrl}" target="_blank" title="返回网站主页">返回网站主页</a></div>
	   <div class="login-main">
	    <form name="loginForm" method="post" action="sys/login">
	      <dl>
		   <dt>用户名：</dt>
		   <dd><input type="text" name="username" id="username"/></dd>
		   <dt>密&nbsp;&nbsp;码：</dt>
		   <dd><input type="password" class="alltxt" name="password"/></dd>
			<dt>&nbsp;</dt>
			<dd><button type="submit" name="sm1" class="login-btn" onclick="this.form.submit();">登录</button></dd>
		 </dl>
		</form>
	   </div>
	</div>
<#include "/common/sys_footer.ftl"/>
</body>
</html>
