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
        <li>Detail</li>
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
      <div class="span2">
        <spring:url var="userUrl" value="/user/search" />
        <div class="text-center">
          <a href="${userUrl}" class="btn"><i class="icon icon-eye-open"></i>&nbsp; Go Search</a>
        </div>
        <br>
        <br>
      </div>
      
      <!-- center space -->
      <div class="span8">
        <div class="text-center">
          <h4>User Detail</h4>
        </div>
        <table class="table table-condensed detail">
          <thead>
          </thead>
          <tbody>
            <tr>
              <td><label for="id" class="pull-right">id　:　</label></td>
              <td><c:out value="${userDetailForm.id}" /></td>
            </tr>
            <tr>
              <td><label class="pull-right" for="name">name　:　</label></td>
              <td><c:out value="${userDetailForm.name}" /></td>
            </tr>
            <tr>
              <td><label for="roleId" class="pull-right">role　:　</label></td>
              <td><c:out value="${userDetailForm.roleId}" /></td>
            </tr>
            <tr>
              <td><label class="pull-right" for="email">email　:　</label></td>
              <td><c:out value="${userDetailForm.email}" /></td>
            </tr>
            <tr>
              <td><label for="sex" class="pull-right">sex　:　</label></td>
              <td><c:out value="${userDetailForm.sex}" /></td>
            </tr>
            <tr>
              <td><label for="nationality" class="pull-right">nationality　:　</label></td>
              <td><c:out value="${userDetailForm.nationality}" /></td>
            </tr>
            <tr>
              <td><label for="text" class="pull-right">text　:　</label></td>
              <td><c:out value="${userDetailForm.text}" /></td>
            </tr>
            <tr>
              <spring:url value="/user/updateInput/${userDetailForm.id}" var="updateUrl"/>
              <spring:url value="/user/deleteConfirm/${userDetailForm.id}" var="detailUrl"/>
              <td colspan="2">
                <div class="text-center">
                <a href="${updateUrl}" class="btn"><i class="icon-edit" style="margin-top: 1px;"></i>&nbsp; Update</a>
                <a href="${detailUrl}" class="btn"><i class="icon-trash" style="margin-top: 1px;"></i>&nbsp; Delete</a>
                </div>
              </td>
            </tr>
          </tbody>  
        </table>
        <br>
      </div>

      <!-- right space -->
      <div class="span2">
      </div>

    </div>
  </div>
</div>