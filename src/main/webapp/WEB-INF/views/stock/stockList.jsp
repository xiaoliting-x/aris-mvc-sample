<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<html lang="ja">
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="../../../resources/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../../../resources/css/aris-style.css" />
<link rel="stylesheet" type="text/css" href="../../../resources/css/sample-style.css" />
<link rel="stylesheet" type="text/css" href="../../../resources/css/jquery-ui.css" />
<title>DVD SHOP INVENTORY MANAGEMENT</title>
</head>  
<body>
    <script src="../../../resources/js/jquery.min.js"></script>
    <script src="../../../resources/js/jquery-ui.min.js"></script>
  <div class="container">
  begin header
  <header id="header">
    <h3>DVD SHOP INVENTORY MANAGEMENT</h3>
  </header>
  <div class="pull-right"></div>
  end header
  begin menu
  <div class="navbar navbar-inverse">
    <div class="navbar-inner">
      <div class="container">
        <nav>
          <ul class="nav">
              <li class="dropdown">
                  <a href="../stock/index.html" class="dropdown-toggle">STOCK</a>
              </li>
          </ul>
          <ul class="nav pull-right">
            <li id="fat-menu" class="dropdown">
              <a href="../../views/index.html" class="dropdown-toggle">LOGOUT</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div> -->
  <!-- end menu -->

<script type="text/javascript">
  jQuery(function($) {
      $('tr[data-href]').addClass('clickable').click(function(e) {
          if(!$(e.target).is('a')){
              window.location = $(e.target).closest('tr').data('href');
          }
      });
      
      $('#jumpToPage').click(function() {
    	  var url = $(this).attr("href") + '/' + $('#targetPage').val();
          $(this).attr("href", url);
      });
  });  
</script>

<div class="row">

  <div class="span12" style="background-color: white;">

    <!-- breadcrumb -->
    <div class="header">
      <ul class="breadcrumb">
        <li>TOP<span class="divider">&gt;</span></li>
        <li>STOCK<span class="divider">&gt;</span></li>
        <li>SEARCH</li>
      </ul>
    </div>

    <!-- message -->
    <!-- message -->
    <c:if test="${not empty message}">
      <div class="alert alert-error">
        <a class="close" data-dismiss="alert">x</a>
        <span class="inline-help"><c:out value="${message}"></c:out></span>
      </div>
         </c:if>

    <!-- search condition box -->
    <!-- search condition box -->
    <div class="row">
      <div class="span12">
        <spring:url var="searchUrl" value="/stock/search" />
        <form:form action="${searchUrl}" name="search" method="post" 
cssClass="text-center" modelAttribute="stockSearchForm" >
          <table class="table table-condensed">
            <thead>
              <tr>
                <th colspan="2">
                  <div class="text-left">CONDITIONS</div>
	</th>        </tr>
            </thead>
            <tbody>
              <tr>
 
     <!-- error message handle -->
                <c:set var="dvdEanCodeErrors"><form:errors 
     path="dvdEanCode" cssClass="text-error" /></c:set>
                <c:if test="${not empty dvdEanCodeErrors}">
                  <c:set var="dvdEanCodeErrorCheck" value="error" />
                </c:if>
                <td class="${dvdEanCodeErrorCheck}">
                  <label for="dvdEanCode" >DVD EAN CODE : </label>
${dvdEanCodeErrors}                 </td>
<td><form:input class="input-small span3" path="dvdEanCode" /></td>
                <td><label for="dvdTitle" >DVD TITLE : </label></td>
                <td><form:input class="input-small span3" path="dvdTitle" /></td>
              </tr>
              <tr>
                <!-- error message handle -->
                <c:set var="warehouseIdErrors"><form:errors 
path="warehouseId" cssClass="text-error" /></c:set>
                <c:if test="${not empty warehouseIdErrors}">
                  <c:set var="warehouseIdErrorCheck" value="error" />
                </c:if>
                <td class="${warehouseIdErrorCheck}">
                  <label for="warehouseId" >WAREHOUSE ID : </label>
                  ${warehouseIdErrors}
                </td>
                <td><form:input class="input-small span3" path="warehouseId" /></td>
                <td><label for="warehouseName" >WAREHOUSE NAME : </label></td>
                <td><form:input class="input-small span3" path="warehouseName" /></td>
              </tr>
              <tr>
                <td colspan="12">
                  <div class="text-center">
                    <button type="submit" class="btn">
<i class="icon icon-eye-open"></i>&nbsp; SEARCH</button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
         </form:form>
        <hr>
      </div>
    </div>

<!-- search result -->
    <div class="row">
      <div class="span12">
        <c:if test="${not empty stocks}">
          <h5>SEARCH RESULT</h5>
          <table class="table table-hover table-condensed 
table-striped table-bordered list">
            <thead>
              <tr class="parts-title">
                <th>DVD EAN CODE</th>
                <th>DVD TITLE</th>
                <th>WAREHOUSE ID</th>
                <th>WAREHOUSE NAME</th>
                <th>QUANTITY</th>
                <th>
                  <i class="icon-search icon"></i>:VIEW &nbsp;
                  <i class="icon-edit icon"></i>:EDIT                 </th>
              </tr>
            </thead>
<tbody>
              <c:forEach var="stock" items="${stocks}">
                <spring:url var="stockRefUrl" value="/stock/detail/${stock.id}" />
<tr data-href="${stockRefUrl}">                   
 
<td><c:out value="${stock.dvdEanCode}"></c:out></td>
                  <td><c:out value="${stock.dvdTitle}"></c:out></td>
                  <td><c:out value="${stock.warehouseId}"></c:out></td>
                  <td><c:out value="${stock.warehouseName}"></c:out></td>
                  <td><c:out value="${stock.quantity}"></c:out></td>
                  <spring:url var="viewUrl" value="/stock/detail/${stock.id}"/>
                  <spring:url var="editUrl" value="/stock/updateInput/${stock.id}"/>
                  <td>
                    <a href="${viewUrl}" class="btn">
<i class="icon-search icon"></i>
</a>
                    <a href="${editUrl}" class="btn"><i class="icon-edit icon"></i></a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
　　　　　　　 <!--  pagination (在下页说明) -->
<div class="control-group ">
    <c:if test="${not empty pages}">
      <div class="pagination pagination-centered">
        <ul>
          <spring:url var="searchPageUrl" value="/stock/search" />

 <c:forEach var="page" items="${pages}">
            <li class="${page.cssClass}">
<a href="${searchPageUrl}/${page.index}">${page.content}</a>
</li>
          </c:forEach>
          <!--   页号输入框 -->
          <li>
 　  <form:form action="${searchPageUrl}" cssClass="form-inline" method="post">
    Page:
      <input id="targetPage" name="targetPage" class="input-mini"
type="text" value="${curPage}"/> / <c:out value="${pageCnt}"/>
      <a id="jumpToPage" href="${searchPageUrl}" class="btn">
<i class="icon icon-eye-open">         </i>&nbsp; JUMP
      </a>
    </form:form>
  </li>
          
        </ul>
      </div>
    </c:if>
  </div>
        </c:if>
      </div>
    </div>

    <%-- <!-- search row -->
    <div class="row">
      <!-- search result -->
      <div class="span12">
          <h5>SEARCH RESULT</h5>
          <table class="table table-hover table-condensed table-striped table-bordered list">
            <thead>
              <tr class="parts-title">
                <th>DVD EAN CODE</th>
                <th>DVD TITLE</th>
                <th>WAREHOUSE ID</th>
                <th>WAREHOUSE NAME</th>
                <th>QUANTITY</th>
                <th>
                  <i class="icon-search icon"></i>:VIEW &nbsp;
                  <i class="icon-edit icon"></i>:EDIT
                </th>
              </tr>
            </thead>
            <tbody>
                <tr data-href="../stock/detail.html">
                  <td>4988142580726</td>
                  <td>Die Hard 4.0</td>
                  <td>TKY001</td>
                  <td>Shibuya 1st Warehouse</td>
                  <td>1000</td>
                  <td>
                    <a href="../stock/detail.html" class="btn"><i class="icon-search icon"></i></a>
                    <a href="../stock/updateInput.html" class="btn"><i class="icon-edit icon"></i></a>
                  </td>
                </tr>
                <tr data-href="../stock/detail.html">
                  <td>4988135831729</td>
                  <td>Harry Potter and the Half-Blood Prince</td>
                  <td>TKY005</td>
                  <td>Ikebukuro 2nd Warehouse</td>
                  <td>1500</td>
                  <td>
                    <a href="../stock/detail.html" class="btn"><i class="icon-search icon"></i></a>
                    <a href="../stock/updateInput.html" class="btn"><i class="icon-edit icon"></i></a>
                  </td>
                </tr>
                <tr data-href="../stock/detail.html">
                  <td>4988021138543</td>
                  <td>Lupin 3rd Generation VS Detective Konan</td>
                  <td>OSK001</td>
                  <td>Nanpa 1st Warehouse</td>
                  <td>1500</td>
                  <td>
                    <a href="../stock/detail.html" class="btn"><i class="icon-search icon"></i></a>
                    <a href="../stock/updateInput.html" class="btn"><i class="icon-edit icon"></i></a>
                  </td>
                </tr>
                <tr data-href="../stock/detail.html">
                  <td>4547462084750</td>
                  <td>Charlie's Angels: Full Throttle</td>
                  <td>YKH002</td>
                  <td>Minatomirai Warehouse</td>
                  <td>1000</td>
                  <td>
                    <a href="../stock/detail.html" class="btn"><i class="icon-search icon"></i></a>
                    <a href="../stock/updateInput.html" class="btn"><i class="icon-edit icon"></i></a>
                  </td>
                </tr>
            </tbody>
          </table>
          
          <!--  pagination -->
          <div class="control-group ">
            <div class="pagination pagination-centered">
              <ul>
                <li class=""><a href="#">&laquo;</a></li>
                <li class=""><a href="#">&larr;</a></li>
                <li class=""><a href="#">1</a></li>
                <li class="active"><a href="#">2</a></li>
                <li class=""><a href="#">3</a></li>
                <li class=""><a href="#">4</a></li>
                <li class=""><a href="#">5</a></li>
                <li class=""><a href="#">&rarr;</a></li>
                <li class=""><a href="#">&raquo;</a></li>
                <li>
                <form id="command" class="form-inline" action="/aris/stock/search" method="post">
                  Page:
                  <input id="targetPage" name="targetPage" class="input-mini" type="text" value="2"/> / 3
                    <a id="jumpToPage" href="../stock/search.html" class="btn">
                      <i class="icon icon-eye-open">
                      </i>&nbsp; JUMP
                    </a>
                </form>
                </li>
              </ul>
            </div>
          </div>
      </div>
    </div> --%>
  </div>
</div>

<!-- begin footer -->
 <!--  <footer class="span12 offset2">
  <p>Copyright 2014 Accenture. All Rights Reserved. Accenture Confidential. For Internal Use Only.</p>
</footer>  
end footer

  </div>
</body> -->

<script type="text/javascript" charset="utf-8" src="../../../resources/js/bootstrap.min.js"/></script>
</html>