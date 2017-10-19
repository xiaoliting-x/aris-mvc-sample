<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">
  <div class="span12">

    <!-- breadcrumb -->
    <div class="header">
      <ul class="breadcrumb">
        <li>Top<span class="divider">&gt;</span></li>
        <li>User Management<span class="divider">&gt;</span></li>
        <li>Register Input<span class="divider">&gt;</span></li>
        <li>Register confirm</li>
      </ul>
    </div>

    <!-- message -->
    <c:if test="${not empty message}">
      <div class="alert alert-error">
        <a class="close" data-dismiss="alert">x</a>
        <span class="inline-help"><c:out value="${message}"></c:out></span>
      </div>
    </c:if>

    <!-- contents -->
    <div class="row">

      <!-- left space -->
      <div class="span2"></div>
    
      <!-- center space -->
      <div class="span8">
        <div class="text-center">
          <h4>please, check register information...</h4>
        </div>
        <spring:url value="/user/register" var="registerUrl"/>
        <form:form name="userRegisterForm" action="${registerUrl}" method="post" cssClass="text-center" modelAttribute="userRegisterForm">
          <table class="table table-condensed">
            <tr>
              <td><label for="id" class="text-right">id　:　</label></td>
              <td>${userRegisterForm.id}</td>
            </tr>
            <tr>
              <td><label class="text-right" for="name">name　:　</label></td>
              <td>${userRegisterForm.name}</td>
            </tr>
            <tr>
              <td><label class="text-right" for="password">password　:　</label></td>
              <td>${userRegisterForm.password}</td>
            </tr>
            <tr>
              <td><label class="text-right" for="roleId">roleId　:　</label>
              <td>${userRegisterForm.labelRoleId}<form:hidden path="roleId"  value="${userRegisterForm.roleId}" /></td>
            </tr>
            <tr>
              <td><label class="text-right" for="email">email　:　</label></td>
              <td>${userRegisterForm.email}</td>
            </tr>
            <tr>
              <td><label for="sex" class="text-right">sex : </label></td>
              <td>${userRegisterForm.labelSex}<form:hidden path="sex"  value="${userRegisterForm.sex}" /></td>
            </tr>
            <tr>
              <td><label for="nationality" class="text-right">nationality : </label></td>
              <td>${userRegisterForm.labelNationality}<form:hidden path="nationality"  value="${userRegisterForm.nationality}" /></td>
            </tr>
            <tr>
            <tr>
              <td><label for="text" class="text-right">text : </label></td>
               <td><c:out value="${userRegisterForm.text}" /></td>
            </tr>
            <tr>
              <td><label for="defkey" class="text-right">defkey : </label></td>
               <td>${userRegisterForm.defkey}</td>
            </tr>
            <tr>
              <td><label for="startdate" class="text-right">startdate : </label></td>
               <td>${userRegisterForm.startDate}</td>
            </tr>
            <tr>
              <td><label for="enddate" class="text-right">enddate : </label></td>
               <td>${userRegisterForm.endDate}</td>
            </tr>
          </table>
          <spring:url value="/user/registerReInput" var="returnUrl"/>
          <a href="${returnUrl}" class="btn"><i class="icon-arrow-left icon"></i>&nbsp; Return</a>
          <button type="submit" class="btn btn-danger" name="register"><i class="icon-ok-sign icon-white icon"></i>&nbsp; Register</button>
        </form:form>
      </div>
      
      <!-- right space -->
      <div class="span2"></div>
    
    </div>
  </div>
</div>