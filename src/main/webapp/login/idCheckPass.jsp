<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>풍족한교회 미니홈페이지</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<h2>아이디 중복 체크</h2>
	<form name="frm" method="post" action="LoginServlet">
		<input type="hidden" name="command" value="id_check_pass"> 아이디
		<input type="text" name="id" value="${id}"> 
		<input type="submit" value="중복 체크" onclick="idCheck_Check()"> <br>

		<c:if test="${result == 1}">
			<script type="text/javascript">
				opener.document.frm.id.value = "";
			</script>
			<div style="color: red">${id}는 이미 사용 중인 아이디입니다.</div>
		</c:if>

		<c:if test="${result == -1}">
			<div style="color: red">${id}는 사용 가능한 아이디입니다.</div>
			<input type="button" value="사용" class="cancel" onclick="idok('${id}')">
		</c:if>

	</form>
</body>
</html>