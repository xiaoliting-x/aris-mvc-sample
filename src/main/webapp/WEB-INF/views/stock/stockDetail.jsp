<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
 
    <div class="row">
      <div class="span12" style="background-color: white;">
    
        <!-- breadcrumb -->
        <div class="header">
          <ul class="breadcrumb">
            <li>TOP<span class="divider">&gt;</span></li>
            <li>STOCK<span class="divider">&gt;</span></li>
            <li>DETAIL</li>
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
            <div class="text-center">
              <a href="http://localhost:8080/aris/stock/search" class="btn"><i class="icon icon-eye-open"></i>&nbsp; Go Search</a>
            </div>
            <br>
            <br>
          </div>
        
          <!-- center space -->
          <div class="span8"> 
            <div class="row text-center">
            <c:set var = "stock" value = "${stock}"/>
           <spring:url value="${details.dvdPackageImgPath}" var="dvdPackageImgURI"/>
            <img src="${dvdPackageImgURI}" class="img-rounded">
           </div>
            
            <div class="text-center">
              <h4>STOCK DETAIL</h4>
            </div>
            <table class="table table-condensed detail">
              <thead>
              </thead>
              <tbody>
               <c:set var = "stock" value = "${stock}"/>
                <tr>
                  <td><label for="dvdEanCode" class="pull-right">DVD EAN CODE  :  </label></td>
                  <td><c:out value="${details.dvdEanCode}"></c:out></td>
                </tr>
                <tr>
                  <td><label class="pull-right" for="dvdTitle">DVD TITLE  :  </label></td>
                  <td><c:out value="${details.dvdTitle}"></c:out></td>
                </tr>
                <tr>
                  <td><label for="warehouseId" class="pull-right">WAREHOUSE ID  :  </label></td>
                  <td><c:out value="${details.warehouseId}"></c:out></td>
                </tr>
                <tr>
                  <td><label class="pull-right" for="warehouseName">WAREHOUSE NAME  :  </label></td>
                  <td><c:out value="${details.warehouseName}"></c:out></td>
                </tr>
                <tr>
                  <td><label for="warehouseAddress" class="pull-right">WAREHOUSE ADDRESS  :  </label></td>
                  <td><c:out value="${details.warehouseAddress}"></c:out></td>
                </tr>
                <tr>
                  <td><label for="warehouseTel" class="pull-right">WAREHOUSE TEL  :  </label></td>
                  <td><c:out value="${details.warehouseTel}"></c:out></td>
                </tr>
                <tr>
                  <td><label for="quantity" class="pull-right">QUANTITY  :  </label></td>
                  <td><c:out value="${details.quantity}"></c:out></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div class="text-center">
                      <a href="../stock/updateInput.html" class="btn"><i class="icon-edit" style="margin-top: 1px;"></i>&nbsp; Update</a>
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
 

<script type="text/javascript" charset="utf-8" src="../../../resources/js/bootstrap.min.js"/></script>
 