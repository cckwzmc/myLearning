<!DOCTYPE html >
<html>
<head>	
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
 <title>Home</title>
</head>
<body> 
<#include "/common/taglibs.ftl">
<P>  The time on the server is ${serverTime}. </P>

<P><a href="${rc.getContextPath()}/myxiuIndex.do">我的走秀</a></P>
<form action="${rc.getContextPath()}/notice" method="GET">
email:<input name="email" value=""/><p/>
phone:<input name="phone" value=""/>
<input type="submit" id="submit"/>
</form>
<input value="${xssValue?html}"/>
</body>
</html>
