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
    <a href="/mypaidan/public/right.jsp" class="list-group-item list-group-item-action" target="main">首页</a>
    <a href="/mypaidan/ManagerServlet?action=1" class="list-group-item list-group-item-action" target="main">用户管理</a>
    <a href="/mypaidan/ManagerServlet?action=2" class="list-group-item list-group-item-action" target="main">职工管理</a>
    <a href="/mypaidan/ManagerServlet?action=3" class="list-group-item list-group-item-action" target="main">单管理</a>
    <a href="/mypaidan/public/updatePassword.jsp" class="list-group-item list-group-item-action" target="main">修改密码</a>
  </div>
</div>
</body>
</html>