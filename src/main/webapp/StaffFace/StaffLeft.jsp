<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left页面</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<style type="text/css">
  body{
    padding-top:20px;
  } 
</style>
</head>
<body style="text-align: center;">
<div class="container">

  <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">职工导航栏</li>
    </ul>
    <%-- 点击实现功能跳转 --%>
    <a href="/mypaidan/public/right.jsp" class="list-group-item list-group-item-action" target="main">首页</a>
    <a href="/mypaidan/StaffServlet?action=4" class="list-group-item list-group-item-action" target="main">历史情况</a>
    <a href="/mypaidan/StaffServlet?action=1" class="list-group-item list-group-item-action" target="main">接单情况</a>
    <a href="/mypaidan/public/updatePassword.jsp" class="list-group-item list-group-item-action" target="main">修改密码</a>
  </div>
</div>
</body>
</html>