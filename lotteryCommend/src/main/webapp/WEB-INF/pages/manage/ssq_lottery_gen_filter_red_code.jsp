<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生产过滤红球</title>
</head>
<body>
	<s:form name="mainForm" id="mainForm" method="post" action="/${ctx}/manage/gen_red_code.action">
		<s:textarea name="filterRedCode" id="filterRedCode" rows="50" cols="100"></s:textarea>
		<s:submit value="start gen filter red code"></s:submit>
	</s:form>	
</body>
</html>