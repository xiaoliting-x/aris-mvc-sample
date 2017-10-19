<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<spring:url value="/resources/css" var="cssUrl"/>
<link rel="stylesheet" type="text/css" href="${cssUrl}/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${cssUrl}/aris-style.css" />
<link rel="stylesheet" type="text/css" href="${cssUrl}/sample-style.css" />
<link rel="stylesheet" type="text/css" href="${cssUrl}/jquery-ui-1.10.3.custom.min.css" />
<title>ARIS Sample Application</title>
</head>
<body>
  <spring:url value="/resources/js" var="jsUrl"/>
  <script type="text/javascript" src="${jsUrl}/jquery-1.10.1.min.js"></script>
  <script type="text/javascript" src="${jsUrl}/jquery-ui-1.10.3.custom.min.js"></script>
  <div class="container">
    <tiles:insertAttribute name="header" />
    <tiles:insertAttribute name="menu" />
    <tiles:insertAttribute name="body" />
    <tiles:insertAttribute name="footer" />
  </div>
</body>
<script type="text/javascript" src="${jsUrl}/bootstrap.min.js"/></script>
</html>