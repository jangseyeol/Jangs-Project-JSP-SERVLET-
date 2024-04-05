<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='loginsub.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type = "text/javascript" src="script/member.js"></script>
<title>마이페이지</title>
</head>
<body>
	<div class="outwrap">
	<div class="inwrap">
	<img src="img/churchface.png" width="250px">
	<h2>회원정보</h2>
	<form>
	
	<table cellpadding="7px">
		<tr>
			<td>안녕하세요. ${loginUser.name}(${loginUser.id})님</td>
		</tr>
		<tr>
			<td><a href="main.jsp"> 메인페이지로 </a>&nbsp; &nbsp;  <a href="BoardServlet?command=board_list&page=1"> 게시판으로 </a></td>
		</tr>
		<tr><td></td></tr>
		<tr>
		<td colspan="2" align="center">
		<input type="button" value="로그아웃" onclick="location.href='LoginServlet?command=logout_finish'" class="w3-button w3-round w3-dark-grey">
		<input type="button" value="회원정보변경" onclick="location.href='LoginServlet?command=mypage_update_form'" class="w3-button w3-round w3-dark-grey" >
		<input type="button" value="회원탈퇴" 
			onclick="location.href='LoginServlet?command=login_drop_form&id='+'${loginUser.id}'" class="w3-button w3-round w3-dark-grey" >
		</td>
		</tr>
	</table>
	</form>
	</div>
	</div>
	
</body>
</html>