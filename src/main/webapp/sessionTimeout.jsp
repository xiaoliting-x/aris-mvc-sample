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
<spring:url value="/resources/css/jquery-ui-1.10.3.custom.min.css" var="jqueryUiCss"/>
<link rel="stylesheet" type="text/css" href="${bootstrapCss}" />
<link rel="stylesheet" type="text/css" href="${arisStyleCss}" />
<link rel="stylesheet" type="text/css" href="${sampleStyleCss}" />
<link rel="stylesheet" type="text/css" href="${jqueryUiCss}" />
<title>ARIS Sample Application</title>
</head>
<body>
  <spring:url value="/resources/js/jquery-1.10.1.min.js" var="jqueryJs"/>
  <spring:url value="/resources/js/jquery-ui-1.10.3.custom.min.js" var="jqueryUiJs"/>
  <script src="${jqueryJs}"></script>
  <script src="${jqueryUiJs}"></script>
  <div class="container">
    <div class="row">
      <div class="span12">
        <header id="header">
          <h3>ARIS Sample Application</h3>
        </header>
        <div class="header">
           <ul class="breadcrumb">
             <li>Session Timeout.</li>
          </ul>
        </div>
      </div>
    </div>
    <article>セッションがタイムアウトしました。次のリンクから再ログインしてください。 <a href="${contextpath}">ログインページへ</a></article>
    <br/>
    <br/>
    <br/>
    <footer class="span12 offset2"><p>Copyright 2013 Accenture. All Rights Reserved. Accenture Confidential. For Internal Use Only.</p></footer>
  </div>
 </body>
</html>