<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top页面</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<style type="text/css">
   .col-sm-3{
     line-height:40px;
       }
    body {
        background-color:#73A1BF;
    }
</style>
<script type="text/javascript">
   function logout(){
	   var flag=window.confirm("确定退出当前账号吗");
	   if(flag){//说明点击的是按钮
		   top.location="exit.jsp";
	   }
	   return false;
   }
</script>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col-sm-9" >
    <h3>电梯派单系统 <span class="badge badge-info">V1.0.1</span></h3>
    </div>
    <div class="col-sm-3" >
       <%
         //获取会话中封装的的登陆用户信息
			String uname=session.getAttribute("uname").toString();
        	String pwd=session.getAttribute("pwd").toString();
       	 	String usertypes=session.getAttribute("usertypes").toString();
         //获取用户的id     
         	if(usertypes.equals("User")){
        %>
        	 <span class="glyphicon glyphicon-user">用户</span> <%=uname%>
        <%
         }else if(usertypes.equals("Manager")){    	 
        %>
        <span class="glyphicon glyphicon-user">管理员</span> <%=uname%>
        <% 
         }else if(usertypes.equals("Staff")){    	 
        %>
        <span class="glyphicon glyphicon-user">职工</span> <%=uname%>
        <% 
         }
        %>       
       <button type="button" class="btn btn-outline-dark btn-sm" onclick="logout()">退出</button>
    </div>
  </div>
</div>
</body>
</html>