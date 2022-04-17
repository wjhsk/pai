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
  <div style="width:50%; margin-bottom:10px">
   <form action="/mypaidan/ManagerServlet?page=1&action=3" method="post">  
    	<div class="input-group md-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">单号</span>
			  </div>
			  <input  type="text" class="form-control" placeholder="请输入单号" id="OddNumber" name="OddNumber"/>
			  &nbsp;&nbsp;
			  <button type="submit" class="btn btn-info btn-sm">查&nbsp;询</button>			
	    </div>
  </form>
  </div>
    <thead style=" text-align: center;">
      <tr>
        <th>单号</th>
        <th>城镇</th>
        <th>使用单位</th>
        <th>维保单位</th>
        <th>设备类型</th>
        <th>设备数量</th>
        <th>检验类别</th>
        <th>状态</th>
        <th>发布用户</th>
        <th>职工</th>   
        <th>操作</th>    
      </tr>
    </thead>
    <c:set var="oddlist" value="${list}"></c:set>
	<c:forEach items="${oddlist}" var="odd"> 
     <tbody style=" text-align: center;">
      <tr>
	   <td height="26px"><c:out value="${odd.oddNumber }"></c:out></td>
	   <td height="26px">
          <c:if test="${odd.town == 'baiyun'}">
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
	         <p class="bg-success text-white">未分派</p>
	     </c:if>
	     <c:if test="${odd.state == '1' }">
	         <p class="bg-success text-white">进行中</p>
	     </c:if>
	     <c:if test="${odd.state == '2' }">
	         <p class="bg-danger text-white">已完单</p>
	     </c:if>
	     <c:if test="${odd.state == '3' }">
	         <p class="bg-success text-white">已派发</p>
	     </c:if>
	   </td>
	   <td height="26px"><c:out value="${odd.uid }"></c:out></td>
	   <td height="26px"><c:out value="${odd.staff }"></c:out></td>
	   <td height="26px">
	   <form action="/mypaidan/ManagerServlet?page=1&action=3" method="post">
	       <input type="hidden" name="oddnumber" value="${odd.oddNumber }"> 
	       <%-- 通过表单传递参数方法，直接写超链接会更好，不过超链接不能传递中文字符 --%>
	       <button type="submit" class="btn btn-info btn-sm">注&nbsp;销</button>
	   </form>
	   </td>
      </tr>
       
     </tbody>
    </c:forEach>
  </table>
</div>

  <div class="container" style=" text-align: center; margin:0px auto;">
      <span style="font-size:15px;color:red;" class="errorcode">
	  		${errorMsg}
      </span>
      <br>
       <ul class="pagination float-md-right">
          <li class="page-item"> 
             <a class="page-link" href="/mypaidan/ManagerServlet?page=1&action=3">首页</a>
          </li>
          <li class="page-item">
             <%--如果当前页面不是第一页，显示上一页选项 --%>
             <c:if test="${dopage.nowPage>1 }">
               <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.nowPage-1 }&action=3">上一页</a>
             </c:if>
          </li>
          <li class="page-item"> 
             <%--如果当前页面不是最后一页，显示下一页选项 --%>
             <c:if test="${dopage.nowPage<dopage.totalPage}">
                <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.nowPage+1 }&action=3">下一页</a>
             </c:if>
          </li>
          <li class="page-item">
            <%--显示末页 --%>
            
            <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.totalPage }&action=3">末页</a>
          </li>
          <li class="page-item">
            <span class="page-link">共有${dopage.totalPage }页</span>   
          </li>
     </ul>
     <form action="/mypaidan/ManagerServlet?page=1&action=4" method="post">
     <button type="submit" class="btn btn-success w-100 mt-3">派&nbsp;单</button>
     </form>

  </div>
  
</body>
</html>