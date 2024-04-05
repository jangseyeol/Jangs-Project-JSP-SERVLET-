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
<title>풍족한교회 미니홈페이지</title>
<link rel="stylesheet" type="text/css" href="css/board_rw.css">
<script type="text/javascript" src="script/board.js"></script>
</head>
<body>
	<%
		session.setAttribute("command", "board_write");
	%>
	<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
		<h3>게시글 등록</h3>
		<form name="frm" method="post" action="BoardServlet" enctype="multipart/form-data">
			<input type="hidden" name="command" value="board_write">
			<table id = "list">
				<tr>
					<th class="w3-pale-green">작성자</th>
					<td><input type="text" name="name" value="${loginUser.id}" readonly></td>
				</tr>
				<tr>
					<th class="w3-pale-green">비밀번호</th>
					<td><input type="password" name="pass"> * 필수 (게시물 수정
						삭제시 필요합니다.)</td>
				</tr>
				<tr>
					<th class="w3-pale-green">이메일</th>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<th class="w3-pale-green">사진</th>
					<td><input type="file" name="pictureUrl"><br></td>				
				<tr>
					<th class="w3-pale-green">제목</th>
					<td><input type="text" size="70" name="title"> * 필수</td>
				</tr>
				<tr>
					<th class="w3-pale-green">내용</th>
					<td><textarea cols="70" rows="15" name="content"></textarea></td>
				</tr>
			</table>
			<br> <br> <input type="submit" value="등록" onclick="return boardCheck()"> 
			<input type="reset" value="다시 작성"> 
			<input type="button" value="목록"
				onclick="location.href='BoardServlet?command=board_list&page=1'">
			<br>
			<br><br>
		</form>
	</div>
</body>
</html>