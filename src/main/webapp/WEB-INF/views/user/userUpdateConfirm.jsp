<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="row">

  <div class="header">
    <ul class="breadcrumb">
      <li>Top<span class="divider">&gt;</span></li>
      <li>User Management<span class="divider">&gt;</span></li>
      <li>Update Input<span class="divider">&gt;</span></li>
      <li>Update Confirm</li>
    </ul>
  </div>

  <div class="row">

    <div class="span2"></div>
    
    <div class="span8">
      <div class="text-center">
        <h4>please, check update information...</h4>
      </div>
      
      <spring:url var="updateUrl" value="/user/update" />
      <form:form action="${updateUrl}" method="post" cssClass="text-center" modelAttribute="userUpdateForm">
        <table class="table table-condensed update-confirm">
          <tr>
            <td><label for="id" class="text-right">id　:　</label></td>
            <td>${userUpdateForm.id}</td>
          </tr>
          <tr>
            <td><label class="text-right" for="name">name　:　</label></td>
            <td>${userUpdateForm.name}</td>
          </tr>
          <tr>
            <td><label class="text-right" for="roleId">roleId　:　</label>
            <td>${userUpdateForm.labelRoleId}<form:hidden path="roleId"  value="${userUpdateForm.roleId}" /></td>
          </tr>
          <tr>
            <td><label class="text-right" for="email">email　:　</label></td>
            <td>${userUpdateForm.email}</td>
          </tr>
          <tr>
            <td><label for="sex" class="text-right">sex : </label></td>
            <td>${userUpdateForm.labelSex}</td>
          </tr>
          <tr>
            <td><label for="nationality" class="text-right">nationality : </label></td>
             <td>${userUpdateForm.labelNationality}<form:hidden path="nationality"  value="${userUpdateForm.nationality}" /></td>
          </tr>
          <tr>
            <td><label for="text" class="text-right">text : </label></td>
             <td><c:out value="${userUpdateForm.text}" /></td>
          </tr>
          <tr>
            <td><label for="defkey" class="text-right">defkey : </label></td>
             <td>${userUpdateForm.defkey}</td>
          </tr>
          <tr>
            <td><label for="startDate" class="text-right">startdate : </label></td>
             <td>${userUpdateForm.startDate}</td>
          </tr>
          <tr>
            <td><label for="endDate" class="text-right">enddate : </label></td>
             <td>${userUpdateForm.endDate}</td>
          </tr>
        </table>
        <spring:url var="returnUrl" value="/user/updateReInput" />
        <a href="${returnUrl}" class="btn"><i class="icon-arrow-left icon"></i>&nbsp; Return</a>
        <button type="submit" class="btn btn-danger" name="update"><i class="icon-ok-sign icon-white icon"></i>&nbsp; Update</button>
      </form:form>
    
    </div>
    
    <div class="span2"></div>
  </div>  
</div>