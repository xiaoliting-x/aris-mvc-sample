<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">
  <div class="span12">
    
    <div class="header">
      <ul class="breadcrumb">
        <li>LOGIN</li>
      </ul>
    </div>
    
    <c:if test="${not empty message}">
      <div class="alert alert-error">
        <a class="close" data-dismiss="alert">x</a>
        <span class="inline-help"><c:out value="${message}"></c:out></span>
      </div>
    </c:if>
    
    <!-- login form -->
    <spring:url var="loginUrl" value="/login" />
    <form:form class="form-horizontal" role="form" action="${loginUrl}" name="loginForm" method="post" commandName="loginForm">
    
      <!-- login title -->
      <div class="control-group">
        <div class="controls">
          <h4>Please input user name and password</h4>
        </div>
      </div>
      
      <!-- login name -->
      <c:set var="nameErrors"><form:errors path="name"/></c:set>
      <c:if test="${not empty nameErrors}">
        <c:set var="nameErrorCheck" value="error" />
      </c:if>
      <div class="control-group  ${nameErrorCheck}">
        <label class="control-label" for="loginInputName">user name</label>
        <div class="controls">
          <form:input path="name" class="input-large" id="loginInputName" name="name" type="text" placeholder="name"/>
          <form:errors path="name"  cssClass="text-error help-inline"/>
        </div>
      </div>
      
      <!-- password -->
      <c:set var="passwordErrors"><form:errors path="password"/></c:set>
      <c:if test="${not empty passwordErrors}">
        <c:set var="passwordErrorCheck" value="error" />
      </c:if>
      <div class="control-group ${passwordErrorCheck}">
        <label class="control-label" for="loginInputName">password</label>
        <div class="controls">
          <form:input path="password" class="input-large" id="loginInputPassword" name="password" type="password" placeholder="password"/>
          <form:errors path="password"  cssClass="text-error help-inline"/>
        </div>
      </div>
      
      <!-- submit -->
      <div class="control-group">
        <div class="controls">
          <button type="submit" class="btn btn-inverse">
            <i class="icon-hand-right icon-white icon"></i>&nbsp; LOGIN
          </button>
        </div>
      </div>
    
    </form:form>
    
  </div>
</div>