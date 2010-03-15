<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@include file="/common/taglibs.jsp"%>
<s:form method="post" action="${ctx}/manage/bg_cal.action">
	<s:checkbox value="1" name="is_append" id="is_append">是否是追加运算</s:checkbox>
	<s:textarea name="ft_code" id="ft_code"></s:textarea>
	<s:submit value="start backgroup cal"></s:submit>
</s:form>