<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<p class="alert alert-error">An Error Occured! Please contact webmaster.</p>
<spring:url var="loginUrl" value="" />
<a href="${loginUrl}" class="btn btn-warning"><i class="icon-play icon-white icon"></i>&nbsp; Login Screen</a>

<hr>
<c:if test="${not empty exceptionName}">
  <h3>Request information.</h3>
  <p>
    <dl class="dl-horizontal">
      <dt>USER</dt><dd><c:out value="${user}"/></dd>
      <dt>ROLE</dt><dd><c:out value="${role}"/></dd>
      <dt>URL</dt><dd><c:out value="${url}"/></dd>
      <dt>content-type</dt><dd><c:out value="${contentType}"/></dd>
      <dt>content-length</dt><dd><c:out value="${contentLength}"/></dd>
      <dt>Session Id</dt><dd><c:out value="${sessionId}"/></dd>
      <dt>Request Parameters</dt><dd><c:out value="${parameters}"/></dd>
      <dt>HTTP Header</dt><dd><c:out value="${headers}"/></dd>
      <dt>Cookies</dt><dd><c:out value="${cookies}"/></dd>
    </dl>
  </p>

  <h3>Exception information. class: <c:out value="${exceptionName}"/></h3>
  <p>
    <c:forEach items="${exceptions}" var="item">
      <c:out value="${item}"/><br>
    </c:forEach>
  </p>
</c:if>
