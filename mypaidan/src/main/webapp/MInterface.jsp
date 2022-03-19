<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>用户端</title>
</head>
	<frameset rows="40,*" frameborder="0" border="0" framespacing="0" >
	    <frame src="/mypaidan/public/top.jsp" name="top" noresize="noresize" frameborder="0" scrolling="no" marginwidth="0" marginheight="0">
		<frameset cols="200,*">
			<frame src="/mypaidan/ManagerFace/ManagerLeft.jsp" name="left" noresize="noresize" frameborder="0" scrolling="no" marginwidth="0" marginheight="0">
			<frame src="/mypaidan/public/right.jsp" name="main" noresize="noresize" frameborder="0"  marginwidth="0" marginheight="0">
		</frameset>
	</frameset>

</html>