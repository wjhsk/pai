<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加新单</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<script>
function check(){
	var Town=document.getElementById("Town").value;
	var UseUnit=document.getElementById("UseUnit").value;
	var MaintenanceUnit=document.getElementById("MaintenanceUnit").value;
	var Quantity=document.getElementById("Quantity").value;
	if(Town == "" || (Town.length==0)){
		alert("请输入城镇");
		return false;
	}else if(UseUnit == "" || (UseUnit.length==0)){
		alert("请输入使用单位");
		return false;
	}else if(MaintenanceUnit == "" || (MaintenanceUnit.length==0)){
		alert("请输入维保单位");
		return false;
	}
	else if(Quantity == "" || (Quantity.length==0)){
		alert("请输入设备数量");
		return false;
	}
	else{
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
       <span style="font-size:20px;">添加新单</span> 
    </li>
    </ul>
  </div>
  <form action="/mypaidan/UserServlet?action=1" method="post" onSubmit="return check()">
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">城区</span>&nbsp;&nbsp;
	   </div>
	   <input type="hidden" name="uname" value="${uname }" id="uname">
	   <select class="form-control" name="Town">
		   <option value="baiyun">白云区</option>
		   <option value="tianhe">天河区</option>
		   <option value="yuexiu">越秀区</option>
		   <option value="panyu">番禺区</option>
		   <option value="haizhu">海珠区</option>
		   <option value="huangpu">黄浦区</option>
	   </select>	
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">使用单位</span>&nbsp;&nbsp;
	   </div>
	   <input type="text" name="UseUnit" id="UseUnit" class="form-control" >	
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">维保单位</span>&nbsp;&nbsp;
	   </div>
	   <input type="text" name="MaintenanceUnit" id="MaintenanceUnit" class="form-control" >	
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">设备类型</span>&nbsp;&nbsp;
	   </div>
	   <select class="form-control" name="EquipmentType">
		   <option value="Elevator" >电梯</option>
		   <option value="Crane">起重机</option>
		   <option value="Forklift" >叉车</option>
	   </select>
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">设备数量</span>&nbsp;&nbsp;
	   </div>
	   <input type="text" name="Quantity" id="Quantity" onkeyup="if(this.value.length==1){this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/D/g,'')}" onafterpaste="if(this.value.length==1){this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/D/g,'')}" class="form-control" >	
	</div>
	<div class="input-group mt-3 w-25 mx-auto">
	   <div class="input-group-prepend ">
		  <span class="input-group-text">检验类别</span>&nbsp;&nbsp;
	   </div>
	   <select class="form-control" name="CheckoutType">
		   <option value="Regular">定期检验</option>
		   <option value="Entrust">委托检验</option>
		   <option value="Install" >安装检验</option>
	   </select>
	</div>	
	<div class="container" style="margin-top:20px;">
        <input type="submit" class="btn btn-success" value="发布"/>
        &nbsp;&nbsp;&nbsp;
    </div>
</form> 
</div>
	
</body>
</html>