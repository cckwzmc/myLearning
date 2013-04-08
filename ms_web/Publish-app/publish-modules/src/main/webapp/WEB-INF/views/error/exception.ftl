<!DOCTYPE html >
<html>
<head>	
<meta http-equiv="Content-type" content="text/html; charset=UTF-8">
 <title>ERROR</title>
</head>
<body> 

<#if data??>
Status: ${data.scode!""} <br/>
Message: ${data.smsg!""} <br/>
</#if> 

<#if exception??>

<hr/>
<#assign stackTrace=exception.stackTrace >
<#list stackTrace as stack>  
${stack} <br/>
</#list>

</#if>

<hr/>

<P> ${data.data!"unknown1"} </P>
</body>
</html>
