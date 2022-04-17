<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
   <style type="text/css">

    body {
        background-image:url(/mypaidan/image/3.jpg);
        background-repeat:no-repeat;
        background-size:100% 100%;
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
		   <div class="bg-info text-white p-2 mt-2" align="center">
			   <h4>注册</h4>
		   </div>
		   <form class="form1" action="/mypaidan/RegisterServlet" method="post">
			 <div class="input-group mt-3">
			   <div class="input-group-prepend ">
				  <span class="input-group-text">用户名</span>
			   </div>
			      <input  type="text" class="form-control" placeholder="请输入用户名" name="uname"/>
							
			</div>
			<div class="input-group mt-3">
			  <div class="input-group-prepend ">
				 <span class="input-group-text">密码</span>
			  </div>
				 <input  type="password" class="form-control" placeholder="请输入密码" name="pwd"/>
							
			</div>
			<div class="input-group mt-3">
				<div class="input-group-prepend ">
					<span class="input-group-text">请选择身份</span>
				</div>
		        <select class="form-control" name="usertypes">
					<option value="Staff" >职工</option>
				    <option value="User">用户</option>
				</select>
			</div>
			 <span style="font-size:12px;color:red;" class="errorcode">
               ${regError}
             </span>
				<button type="submit" class="btn btn-success w-100 mt-3">注册</button>
				<a href="/mypaidan/public/login.jsp" class="btn btn-default btn-sm btn-block" role="button">返回登陆</a>
						
					</form>
				</div>
				
	</div>

		<script src="js/jquery-3.4.1.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>