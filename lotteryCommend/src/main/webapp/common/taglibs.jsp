<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<s:set var="sitename">
<s:text name="all.sitename"></s:text>
</s:set>
<s:set var="ctx" value="${pageContext.request.contextPath}"/>
<s:set var="datePattern"><s:text name="date.format"/></s:set>