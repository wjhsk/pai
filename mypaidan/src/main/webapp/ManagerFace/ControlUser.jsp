<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
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
       <span style="font-size:20px;">用户信息</span> 
    </li>
    </ul>
  </div>   
  
  <table class="table  table-striped">
  <div style="width:50%; margin-bottom:10px">
   <form action="/mypaidan/ManagerServlet?page=1&action=1" method="post">  
    	<div class="input-group md-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">用户名</span>
			  </div>
			  <input  type="text" class="form-control" placeholder="请输入用户名" id="UID" name="UID"/>
			  &nbsp;&nbsp;
			  <button type="submit" class="btn btn-info btn-sm">查&nbsp;询</button>			
	    </div>
  </form>
  </div>
    <thead style=" text-align: center;">
      <tr>
        <th>用户名</th>
        <th>密码</th>
        <th>操作</th>     
      </tr>
    </thead>
    <c:set var="userlist" value="${list}"></c:set>
	<c:forEach items="${userlist}" var="user"> 
     <tbody style=" text-align: center;">
      <tr>
	   <td height="26px"><c:out value="${user.uid }"></c:out></td>
	   <td height="26px"><c:out value="${user.password }"></c:out></td>
	   <td height="26px">
	   <form action="/mypaidan/ManagerServlet?page=1&action=1" method="post">
	       <input type="hidden" name="Uid" value="${user.uid }"> 
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
	  		${errorMsg}  <%-- 显示由Servlet传来的错误信息 --%>
      </span>
      <br>
       <ul class="pagination float-md-right">
          <li class="page-item"> 
             <a class="page-link" href="/mypaidan/ManagerServlet?page=1&action=1">首页</a>
          </li>
          <li class="page-item">
             <%--如果当前页面不是第一页，显示上一页选项 --%>
             <c:if test="${dopage.nowPage>1 }">
               <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.nowPage-1 }&action=1">上一页</a>
             </c:if>
          </li>
          <li class="page-item"> 
             <%--如果当前页面不是最后一页，显示下一页选项 --%>
             <c:if test="${dopage.nowPage<dopage.totalPage}">
                <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.nowPage+1 }&action=1">下一页</a>
             </c:if>
          </li>
          <li class="page-item">
            <%--显示末页 --%>
            
            <a class="page-link" href="/mypaidan/ManagerServlet?page=${dopage.totalPage }&action=1">末页</a>
          </li>
          <li class="page-item">
            <span class="page-link">共有${dopage.totalPage }页</span>   
          </li>
     </ul>

  </div>
  

</body>
</html>