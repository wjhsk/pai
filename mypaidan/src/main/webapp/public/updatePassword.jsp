<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<script>
function check(){
	var hidpwd=document.getElementById("hidold").value;	//隐藏输入框中的旧密码值
	var oldpwd=document.getElementById("old").value;	//旧密码输入框的值
	var newpwd=document.getElementById("password").value;
	if(hidpwd != oldpwd){
		alert("输入的旧密码不正确");
		return false;
	}else if(newpwd == "" || (newpwd.length==0)){
		alert("请输入密码");
		return false;
	}else if(newpwd==oldpwd){
		alert("新密码不能与旧密码相同");
		return false;
	}else{
		return true;
	}
}
</script>
</head>
<body style="text-align: center; margin:10px auto;">
<div class="container" style=" text-align: center; margin-top:20px;">
    <div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">修改密码</span> 
    </li>
    </ul>
  </div>
  <form action="/mypaidan/updatepwdServlet" method="post"  onSubmit="return check()">
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">账号</span>&nbsp;&nbsp;
	   </div>
	  <%String un=session.getAttribute("uname").toString(); %> 
	  <%=un%>
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">旧密码</span>
	   </div>
	  <input type="hidden" name="hidold" value="${pwd }" id="hidold">
	  <input type="hidden" name="uname" value="${uname }" id="uname">
	  <input type="hidden" name="usertypes" value="${usertypes }" id="usertypes">
	  <input type="password" name="old" id="old" class="form-control" >							
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">新密码</span>
	   </div>
	   <input type="password" name="pword" id="pword" class="form-control" >							
	</div>
    <div class="container" style="margin-top:20px;">
        <input type="submit" class="btn btn-success" value="修改"/>
        &nbsp;&nbsp;&nbsp;
    </div>
</form> 
</div>


</body>
</html>