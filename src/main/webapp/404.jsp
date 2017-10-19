<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
<spring:url value="/resources/css/aris-style.css" var="arisStyleCss"/>
<spring:url value="/resources/css/sample-style.css" var="sampleStyleCss"/>
<link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
<link rel="stylesheet" type="text/css" href="${arisStyleCss}" />
<link rel="stylesheet" type="text/css" href="${sampleStyleCss}" />
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" />
<title>ARIS Sample Application</title>
</head>
<body>
  <div class="container">
    <header id="header">
      <h3>ARIS Sample Application</h3>
    </header>
    <c:if test="${not empty menues}">

      <div class="navbar navbar-inverse">
        <div class="navbar-inner">
          <div class="container">
            <nav>
              <ul class="nav">
                <c:forEach var="item" items="${menues}">
                  <li class="dropdown">
                    <spring:url var="itemUrl" value="${item.url}" />
                    <a href="${itemUrl}" class="dropdown-toggle">${item.content}</a>
                  </li>
                </c:forEach>
              </ul>
              <ul class="nav pull-right">
                <li id="fat-menu" class="dropdown">
                  <spring:url var="logoutUrl" value="/logout" />
                  <a href="${logoutUrl}" class="dropdown-toggle">LOGOUT</a>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </c:if>
    
    <h4>404 NOT FOUND</h4>
    <p>the url is not found.</p>

    <footer class="span12 offset2">
      <p>Copyright 2013 Accenture. All Rights Reserved. Accenture Confidential. For Internal Use Only.</p>
    </footer>

  </div>
</body>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
<script type="text/javascript" charset="utf-8" src="${bootstrapJs}"/></script>
</html>