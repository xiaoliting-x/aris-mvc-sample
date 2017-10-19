<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
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
              <a href="${logoutUrl}" class="dropdown-toggle">logout</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>
</c:if>