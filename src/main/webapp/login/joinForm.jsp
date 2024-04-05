<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>풍족한교회 미니홈페이지 - 회원가입</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
<div class="outwrap">
<div class="inwrap">
<jsp:include page="../header.jsp"></jsp:include>
	<h3>회원 가입</h3>
	'*' 표시 항목은 필수 입력 항목입니다.
	<form name="frm" method="post" action="LoginServlet">
		<input type="hidden" name="command" value="join_save_form">
		<table>
	
		<tr>
				<td>아이디</td>
				<td><input type="text" name="id" size="20">* </td>
				<td><input type="hidden" name="reid" size="20"> 
				<input type="button" id="re_check" value="중복 체크" onclick="idCheck()" class="w3-button w3-round w3-padding-small w3-dark-grey"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass">*</td>
			</tr>
			<tr height="30">
				<td width="80">비밀번호 확인</td>
				<td><input type="password" name="pass_check">*</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name">*</td>
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
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td colspan="2">${message }</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value="등록" onclick="return joinCheck()" class="w3-button w3-round w3-green"> 
				&nbsp;
				<input type="reset" value="취소" onclick="history.back(-1);" class="w3-button w3-round w3-green"> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>