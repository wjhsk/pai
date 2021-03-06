<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>查看单况</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<style type="text/css">
.pagination{
   width:300px;
}
</style>
</head>
<body>

<div class="container" style=" text-align: center; margin-top:20px;">
  <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">所有单信息</span> 
    </li>
    </ul>
  </div>     
  <table class="table  table-striped">
    <thead style=" text-align: center;">
      <tr>
        <th>单号</th>
        <th>城镇</th>
        <th>使用单位</th>
        <th>维保单位</th>
        <th>设备类型</th>
        <th>设备数量</th>
        <th>检验类别</th> 
        <th>操作</th>
      </tr>
    </thead>
    <c:set var="oddlist" value="${list}"></c:set>
	<c:forEach items="${oddlist}" var="odd"> 
     <tbody style=" text-align: center;">
      <tr>
	   <td height="26px"><c:out value="${odd.oddNumber }"></c:out></td>
	   <td height="26px">
          <c:if test="${odd.town == 'baiyun'}"> <%-- 注意使用类.属性时首字母要小写 --%>
              <span>白云区</span>
          </c:if>
          <c:if test="${odd.town == 'tianhe'}">
              <span>天河区</span>
          </c:if>
          <c:if test="${odd.town == 'yuexiu'}">
              <span>越秀区</span>
          </c:if>
          <c:if test="${odd.town == 'panyu'}">
              <span>番禺区</span>
          </c:if>
          <c:if test="${odd.town == 'haizhu'}">
              <span>海珠区</span>
          </c:if>
          <c:if test="${odd.town == 'huangpu'}">
              <span>黄埔区</span>
          </c:if>
       </td>
	   <td height="26px"><c:out value="${odd.useUnit }"></c:out></td>
	   <td height="26px"><c:out value="${odd.maintenanceUnit }"></c:out></td>
       <td height="26px">
          <c:if test="${odd.equipmentType == 'Elevator'}">
              <span>电梯</span>
          </c:if>
          <c:if test="${odd.equipmentType == 'Crane'}">
              <span>起重机</span>
          </c:if>
          <c:if test="${odd.equipmentType == 'Forklift'}">
              <span>叉车</span>
          </c:if>
       </td>
	   <td height="26px"><c:out value="${odd.quantity }"></c:out></td>
       <td height="26px">
	     <c:if test="${odd.checkoutType == 'Regular' }">
	         <p class="bg-success text-white">定期检验
	     </c:if>
	     <c:if test="${odd.checkoutType == 'Entrust' }">
	         <p class="bg-success text-white">委托检验
	     </c:if>
	     <c:if test="${odd.checkoutType == 'Install' }">
	         <p class="bg-success text-white">安装检验
	     </c:if>	     
	   </td>
	   <td height="26px">
	   <c:if test="${odd.state == '0' }">
	   <%-- 有单选择接不接 --%>
	     <a href="/mypaidan/StaffServlet?action=2&Town=${odd.town }&eType=${odd.equipmentType }" class="btn btn-primary btn-sm" role="button">接&nbsp;受</a>
	     <a href="/mypaidan/StaffServlet?action=3&Town=${odd.town }&eType=${odd.equipmentType }" class="btn btn-danger btn-sm" role="button">拒&nbsp;绝</a>
       </c:if>
       <c:if test="${odd.state == '1' }">
       <%-- 单工作完就选择完成结束单 --%>
	     <a href="/mypaidan/StaffServlet?action=6&OddNumber=${odd.oddNumber }" class="btn btn-primary btn-sm" role="button">完&nbsp;成</a>
	 
       </c:if>
       </td>
      </tr>  
     </tbody>
    </c:forEach>
  </table>
  
  <span style="font-size:20px;color:red;" class="errorcode">
	  		${errorMsg}
  </span>
</div>
</body>
</html>