<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>电梯派单系统</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<style type="text/css">
    body {
        background-image:url(/mypaidan/image/1.jpg);
        background-repeat:no-repeat;
        background-size:100% 100%;
        background-color:#73A1BF;
    }
    .card{
      margin-top:100px;
      background-color:rgba(0,0,0,0.3);
    }
</style>
</head>
<body>
 <div class="container" align="center">
       
		<div class="card col-6 p-5 ">
		 <h1>电梯派单系统<sup>V1.0.1</sup></h1>
		   <div class="bg-info text-white p-2 mt-2" align="center">
		   
			   <h4>登陆</h4>
		   </div>
		   <form class="form1" action="/mypaidan/LoginServlet" method="post" >
			 <div class="input-group mt-3">
			   <div class="input-group-prepend ">
				  <span class="input-group-text">用户名</span>
			   </div>
			      <input  type="text" class="form-control" placeholder="请输入用户名" name="uname" id="uname" />			
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">密码</span>
			  </div>
				 <input  type="password" class="form-control" placeholder="请输入密码" id="pwd" name="pwd"/>
							
			</div>
			<div class="input-group mt-3">
				<div class="input-group-prepend ">
					<span class="input-group-text">请选择身份</span>
				</div>
		        <select class="form-control" name="usertypes">
					<option value="Manager" >管理员</option>
				    <option value="Staff">职工</option>
				    <option value="User" >用户</option>
				</select>
			</div>
			   <%
                      //获取请求中封装的属性
                      Object obj=request.getAttribute("errorMsg");
                      if(obj !=null){//如果得到了属性值
                   %>
                   <font color="#feeeed" style="font-size:15px">
                     <%=String.valueOf(obj) %>  
                   </font>
                   <%
                      }
                   %>			
             <br>
				<button type="submit" class="btn btn-success w-100 mt-3">登陆</button>
				<br/><br/>
				<a href="/mypaidan/register.jsp" class="btn btn-primary btn-sm" role="button">没有账号？点击注册</a>						
					</form>
				</div>				
	</div>
		<script src="js/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>