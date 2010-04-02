<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${sitename }</title>
<!-- Framework CSS -->
<link rel="stylesheet" href="resources/css/blueprint/screen.css" type="text/css" media="screen, projection" />
<!--<link rel="stylesheet" href="resources/css/blueprint/print.css"
	type="text/css" media="print" />
-->
<!--[if lt IE 8]><link rel="stylesheet" href="resources/css/blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
<!-- Import fancy-type plugin for the sample page. -->
</head>
<body>
<div class="container" id="content">
<form action="">
<div class="span-16">
<div>
	<s:radio list="复式,单式" id="select_type" name="select_type"></s:radio>
</div>
<div>
	<s:textarea name="f_redCode" id="f_redCode"></s:textarea>
</div>

</div>
</form>
<div class="span-8">banner</div>
</div>
</body>
</html>