<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:if test="${empty loginUser}">
	<jsp:forward page='loginsub.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board_rw.css">
<title>천로역정 게시판</title>
</head>
<body>
<%
	session.setAttribute("command", "heaven_load_update");
%>
<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
		<h1>게시글 수정</h1>
		<form name="frm" method="post" action="BoardServlet" enctype="multipart/form-data">
			<input type="hidden" name="command" value="heaven_load_update"> 
			<input type="hidden" name="num" value="${heavenLoad.num}">
			<table id="list">
				<tr>
					<th class="w3-pale-green">작성자</th>
					<td><input type="text" size="12" name="name"
						value="${heavenLoad.name}"> * 필수</td>
				</tr>
				<tr>
					<th class="w3-pale-green">비밀번호</th>
					<td><input type="password" size="12" name="pass"> * 필수
						(게시물 수정 삭제시 필요합니다.)</td>
				</tr>
				<tr>
					<th class="w3-pale-green">이메일</th>
					<td><input type="text" size="40" maxlength="50" name="email"
						value="${heavenLoad.email}"></td>
				</tr>
				<tr>
					<th class="w3-pale-green">사진</th>
					<td><input type="file" name="pictureUrl"><br>
					(주의사항 : 이미지를 변경하고자 할때만 선택하세요.)</td>
				</tr>
				<tr>
					<th class="w3-pale-green">제목</th>
					<td><input type="text" size="70" name="title"
						value="${heavenLoad.title}"></td>
				</tr>
				<tr>
					<th class="w3-pale-green">내용</th>
					<td><textarea cols="70" rows="15" name="content">${heavenLoad.content}</textarea></td>
				</tr>
			</table>
			<br> <br> <input type="submit" value="등록" onclick="return boardCheck()"> 
			<input type="reset" value="다시 작성"> 
			<input type="button" value="목록"
				onclick="location.href='BoardServlet?command=heaven_load_list'">
		</form>
	</div>
</body>
</html>