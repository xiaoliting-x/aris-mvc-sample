<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">
  <div class="span12">
    
    <div class="header">
      <ul class="breadcrumb">
        <li>Top<span class="divider">&gt;</span></li>
        <li>User Management<span class="divider">&gt;</span></li>
        <li>Register Input<span class="divider">&gt;</span></li>
      </ul>
    </div>
    
    <c:if test="${not empty message}">
      <div class="alert alert-error">
        <a class="close" data-dismiss="alert">x</a>
        <span class="inline-help"><c:out value="${message}"></c:out></span>
      </div>
    </c:if>
    
    <div class="row">
    
      <div class="span2"></div>
      
      <div class="span8">
        
        <!-- REGIST FORM -->
        <div class="text-center"><h4>New User Register Input</h4></div>
        <spring:url var="confirmUrl" value="/user/registerConfirm" />
        <form:form name="userRegisterForm" action="${confirmUrl}" method="post" cssClass="form-horizontal"  modelAttribute="userRegisterForm">

          <!-- User Id -->
          <!-- error message handle -->
          <c:set var="idErrors"><form:errors path="id" cssClass="text-error help-inline" /></c:set>
          <c:if test="${not empty idErrors}">
            <c:set var="idErrorCheck" value="error" />
          </c:if>
          <div class="control-group ${idErrorCheck}">
            <label for="id" class="control-label">id</label>
            <div class="controls">
              <form:input path="id" cssClass="input-large"/>
              ${idErrors}
            </div>
          </div>

          <!-- Name -->
          <!-- error message handle -->
          <c:set var="nameErrors"><form:errors path="name" cssClass="text-error help-inline" /></c:set>
          <c:if test="${not empty nameErrors}">
            <c:set var="nameErrorCheck" value="error" />
          </c:if>
          <div class="control-group ${nameErrorCheck}">
            <label for="name" class="control-label">name</label>
            <div class="controls">
              <form:input path="name"  cssClass="input-large" />
              ${nameErrors}
            </div>
          </div>

          <!-- password -->
          <!-- error message handle -->
          <c:set var="passwordErrors"><form:errors path="password" cssClass="text-error help-inline" /></c:set>
          <c:if test="${not empty passwordErrors}">
            <c:set var="passwordErrorCheck" value="error" />
          </c:if>
          <div class="control-group ${passwordErrorCheck}">
            <label for="password" class="control-label">password</label>
            <div class="controls">
              <form:input id="password" name="password" type="password" path="password" cssClass="input-large"/>
              ${passwordErrors}
            </div>
          </div>

          <!-- confirmed password -->
          <!-- error message handle -->
          <c:set var="confirmedPasswordErrors"><form:errors path="confirmedPassword" cssClass="text-error help-inline" /></c:set>
          <c:if test="${not empty confirmedPasswordErrors}">
            <c:set var="confirmedPasswordErrorCheck" value="error" />
          </c:if>
          <div class="control-group ${confirmedPasswordErrorCheck}">
            <label for="confirmedPassword" class="control-label">confirmed password</label>
            <div class="controls">
              <form:input id="confirmedPassword" name="confirmedPassword" type="password" path="confirmedPassword" cssClass="input-large"/>
              ${confirmedPasswordErrors}
            </div>
          </div>
          
          <!-- Role Id -->
          <div class="control-group">
            <label for="roleId" class="control-label">role</label>
            <div class="controls">
              <form:select path="roleId" multiple="false" items="${roles}" itemValue="id"  itemLabel = "name"  />
            </div>
          </div>

          <!-- Email -->
          <!-- error message handle -->
          <c:set var="emailErrors"><form:errors path="email" cssClass="text-error help-inline" /></c:set>
          <c:if test="${not empty emailErrors}">
            <c:set var="emailErrorCheck" value="error" />
          </c:if>
          <div class="control-group ${emailErrorCheck}">
            <label for="email" class="control-label">email</label>
            <div class="controls">
              <form:input path="email"  cssClass="input-large" />
              ${emailErrors}
            </div>
          </div>

          <!-- Sex -->
          <div class="control-group">
            <label for="sex" class="control-label">sex</label>
            <div class="controls">
              <form:radiobuttons path="sex" items="${sexs}" required="true" itemValue="code"  itemLabel = "data"/>
            </div>
          </div>

          <!-- Nationality -->
          <div class="control-group">
            <label for="nationality" class="control-label">nationality</label>
            <div class="controls">
              <form:select path="nationality" multiple="false" items="${nationalities}" itemValue="code"  itemLabel = "data"  />
            </div>
          </div>

          <!-- Text -->
          <div class="control-group">
            <label for="text" class="control-label">text</label>
            <div class="controls">
              <!-- 以下の実装では、XSSに対応できない
              <form:textarea path="text"  rows="3" cols="20" />
              -->
              <textarea name="text"  rows="3" cols="20"><c:out value="${userRegisterForm.text}" /></textarea>
            </div>
          </div>
          
          <form:hidden path="defkey" value="ud" />
          
          <!-- submit or return button -->
          <div class="control-group">
            <div class="controls">
              <spring:url var="cancelUrl" value="/user/search"/>
              <a href="${cancelUrl}" class="btn"><i class="icon-remove-sign icon"></i>&nbsp; Cancel</a>
              <button class="btn btn-success" type="submit" name="update">Confirm &nbsp;<i class="icon-play icon-white icon"></i></button>
            </div>
          </div>
          
          <div class="text-center">
          </div>
          
        </form:form>
      </div>
      
      <div class="span2"></div>
      
    </div>
  </div>
</div>