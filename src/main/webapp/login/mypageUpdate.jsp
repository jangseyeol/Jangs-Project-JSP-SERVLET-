<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type = "text/javascript" src="script/member.js"></script>
<title>회원정보변경</title>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	<div class="outwrap">
	<div class="inwrap">
	<h3>회원정보변경</h3>
	<p>*회원 정보 변경 시 비밀번호는 반드시 입력해주세요.</p>
	
	<form name = "frm" method = "post" action = "LoginServlet">
		<input type = "hidden" name = "command" value = "mypage_update_success">
		<table>
			
			<tr>
				<td>아이디</td>
				<td><input type ="text" name ="id" size="20" value="${loginUser.id}" readonly><td>
			</tr>
			
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" size="20" value="${loginUser.name}"></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type = "password" name="pass" size="20"></td>
			</tr>
			
			<tr height = "30">
				<td width="80">비밀번호 확인</td>
				<td><input type="password" name="pass_check" size="20"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><select name="gender">
				<option value="0">선택</option>
				<option value="1">남자</option>
				<option value="2">여자</option>
				</select></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name ="phone" size="20"></td>
			</tr>
			<tr>
				<td colspan = "2" align= "center">
				<input type = "submit" value ="확인" onclick="return joinCheck()" class="w3-button w3-round w3-green">
				<input type = "reset" value ="취소"  onclick="history.back(-1);" class="w3-button w3-round w3-dark-grey">
			</tr>
		</table>
	
	</form>
	</div>
	</div>
</body>
</html>