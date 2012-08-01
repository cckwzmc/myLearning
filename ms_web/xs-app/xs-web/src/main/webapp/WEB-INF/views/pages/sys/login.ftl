<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Toney Administrator's login</title>
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
	<form id="loginForm" action="login" method="post">
		
	</form>
<#include "/common/sys_footer.ftl"/>
</body>
</html>
