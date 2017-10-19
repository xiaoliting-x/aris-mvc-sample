<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">
  <div class="span12"">

    <!-- breadcrumb -->
    <div class="header">
      <ul class="breadcrumb">
        <li>Top<span class="divider">&gt;</span></li>
        <li>User Management<span class="divider">&gt;</span></li>
        <li>Delete Confirm</li>
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
          <h4>please, check delete information...</h4>
        </div>
        <spring:url value="/user/delete" var="deleteUrl"/>
        <form:form action="${deleteUrl}" method="post" cssClass="text-center" modelAttribute="userDeleteForm">
          <table class="table table-condensed">
            <tr>
              <td><label for="id" class="text-right">id　:　</label></td>
              <td><c:out value="${userDeleteForm.id}" /></td>
            </tr>
            <tr>
              <td><label class="text-right" for="name">name　:　</label></td>
              <td><c:out value="${userDeleteForm.name}" /></td>
            </tr>
            <tr>
              <td><label class="text-right" for="roleId">roleId　:　</label>
              <td><c:out value="${userDeleteForm.roleId}" /></td>
            </tr>
          </table>
          <spring:url value="/user/detail/${userDeleteForm.id}" var="listUrl"/>
          <a href="${listUrl}" class="btn"><i class="icon-arrow-left icon"></i>&nbsp; Cancel</a>
          <button type="submit" class="btn btn-danger" name="delete"><i class="icon-ok-sign icon-white icon"></i>&nbsp; Delete</button>
        </form:form>
      </div>
      
      <!-- right space -->
      <div class="span2"></div>
    
    </div>
  </div>
</div>