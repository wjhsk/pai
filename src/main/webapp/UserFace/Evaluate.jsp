<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>查看单况</title>
<link rel="stylesheet" href="/mypaidan/css/bootstrap.min.css"/>
<style>
        body,ul,li{
            padding:0;
            margin: 0;
        }
        li{
            list-style: none;
        }
        .rating{
            width: 600px;
            height: 26px;
            margin: 30px auto;
        }
        .rating-item{
            float: left;
            width:50px;
            height: 50px;
            background: url("../image/6.jpg") no-repeat ;
            -webkit-background-size:50px 100px;
            background-size: 50px 100px;
            background-position: 0 -58px;
            overflow: hidden;
            margin-left: 10px;
        }
		.rating-item-group{
			float: left;
            width:100px;
            height: 50px;
            -webkit-background-size:50px 100px;
            background-size: 50px 100px;
            background-position: 0 -58px;
            overflow: hidden;
            margin-left: 10px;
		}
    </style>
</head>
<body>
<form action="/mypaidan/UserServlet?action=5" method="post" name=form1>
<div class="container" style=" text-align: center; margin-top:20px;" align="center">
<div class="list-group">
    <ul class="list-group">
    <li class="list-group-item active">
       <span style="font-size:20px;">订&nbsp;单&nbsp;评&nbsp;价</span> 
    </li>
    </ul>
  </div>   
  <input type="hidden" name="n1" value="1" id="n1">
  <input type="hidden" name="n2" value="1" id="n2">
  <input type="hidden" name="n3" value="1" id="n3">
  <%String oddnumber=request.getParameter("oddnumber"); %>
  <input type="hidden" name="oddnumber" value=<%=oddnumber %> id="oddnumber">
  <div class="rating"> 
  <ul class="rating" id="rating1">
		<li class="rating-item-group">
          <span style="font-size:20px;">满意度&nbsp;&nbsp;</span>
       </li>
        <li class="rating-item" title="很不好"></li>
        <li class="rating-item" title="不好"></li>
        <li class="rating-item" title="一般"></li>
        <li class="rating-item" title="好"></li>
        <li class="rating-item" title="很好"></li>
    </ul>
  </div>
  <div class="rating"> 
  <ul class="rating" id="rating2">
       <li class="rating-item-group">
          <span style="font-size:20px;">服务态度&nbsp;&nbsp;</span>
       </li>
        <li class="rating-item" title="很不好"></li>
        <li class="rating-item" title="不好"></li>
        <li class="rating-item" title="一般"></li>
        <li class="rating-item" title="好"></li>
        <li class="rating-item" title="很好"></li>
    </ul>
  </div>
  <div class="rating"> 
  <ul class="rating" id="rating3">
       <li class="rating-item-group">
          <span style="font-size:20px;">综合评价&nbsp;&nbsp;</span>
       </li>
        <li class="rating-item" title="很不好"></li>
        <li class="rating-item" title="不好"></li>
        <li class="rating-item" title="一般"></li>
        <li class="rating-item" title="好"></li>
        <li class="rating-item" title="很好"></li>
    </ul>
  </div>
  <br/>
  <br/>
  <br/>
  <span style="font-size:15px;color:red;" class="errorcode">
	  		${errorMsg }
      </span>
  <div class="input-group mt-3">
     <button type="button" class="btn btn-success w-100 mt-3" onclick="submitServlet()">提交</button>
 </div>
 </div>
 </form>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>
        var num1 = 1;//点亮个数
        var num2 = 1;
        var num3 = 1;
        $item1 = $('#rating1').find('.rating-item');//获取的所有li
        $item2 = $('#rating2').find('.rating-item');
        $item3 = $('#rating3').find('.rating-item');
        //点亮星星函数
        var dianLiang = function (num,i) {
        	if(i==1){
        		$item1.each(function (index) { //遍历所有li,即所有星星
                    if (index < num){
                        $(this).css('background-position','0 0') //点亮
                    }else {
                        $(this).css('background-position','0 -58px') //未点亮
                    }
                })
        	}
            if(i==2){
            	$item2.each(function (index) { //遍历所有li,即所有星星
                    if (index < num){
                        $(this).css('background-position','0 0') //点亮
                    }else {
                        $(this).css('background-position','0 -58px') //未点亮
                    }
                })
            }
			if(i==3){
				$item3.each(function (index) { //遍历所有li,即所有星星
                    if (index < num){
                        $(this).css('background-position','0 0') //点亮
                    }else {
                        $(this).css('background-position','0 -58px') //未点亮
                    }
                })
			}
        }
    //    初始化,默认点亮1颗
        dianLiang(num1,1)
        dianLiang(num2,2)
        dianLiang(num3,3)
    //    绑定事件
        $item1.on('mouseover',function () {
            dianLiang($(this).index(),1)
        }).on('click',function () {
            dianLiang($(this).index(),1);
            num1 = $(this).index()
        })
        $('#rating1').on('mouseout',function () {
            dianLiang(num1,1)
        })
        $item2.on('mouseover',function () {
            dianLiang($(this).index(),2)
        }).on('click',function () {
            dianLiang($(this).index(),2);
            num2 = $(this).index()
        })
        $('#rating2').on('mouseout',function () {
            dianLiang(num2,2)
        })
        $item3.on('mouseover',function () {
            dianLiang($(this).index() ,3)
        }).on('click',function () {
            dianLiang($(this).index(),3);
            num3 = $(this).index()
        })
        $('#rating3').on('mouseout',function () {
            dianLiang(num3,3)
        })
 		function submitServlet()
       {
    	   document.getElementById("n1").value=num1;
    	   document.getElementById("n2").value=num2;
    	   document.getElementById("n3").value=num3;
    	   document.form1.submit();
       }
    </script>
</body>
</html>

