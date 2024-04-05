<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='loginsub.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/board_rw.css">
<script type="text/javascript" src="script/board.js"></script>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<title>천로역정 게시판</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div id="wrap" align="center">
		<table id="list">
			<tr>
				<th class="w3-pale-green">작성자</th>
				<td>${heavenLoadBoard.name}</td>
				<th class="w3-pale-green">이메일</th>
				<td>${heavenLoadBoard.email}</td>
			</tr>
			<tr>
				<th class="w3-pale-green">작성일</th>
				<td><fmt:formatDate value="${heavenLoadBoard.writedate}"
						pattern="yyyy.MM.dd HH:mm:ss" /></td>
				<th class="w3-pale-green">조회수</th>
				<td>${heavenLoadBoard.readcount }</td>
			</tr>
			<tr>
				<th class="w3-pale-green">제목</th>
				<td colspan="3">${heavenLoadBoard.title }</td>
			</tr>
			<tr>
				<th class="w3-pale-green">내용</th>
				<td colspan="3">
				<c:if test="${heavenLoadBoard.pictureUrl !=null}">
						<img src="upload/${heavenLoadBoard.pictureUrl}" style="width: 600px; height: auto;">
					</c:if> 
					<textarea cols="70" rows="15" name="content">${heavenLoadBoard.content }</textarea></td>
			</tr>
		</table>
		<br>



		<%
			String cpage = request.getParameter("page");
		%>
		<table id ="button"> 
		<tr>
		<td>
			<input type="button" value="게시글 리스트"
			onclick="location.href='BoardServlet?command=heaven_load_list&page=<%=cpage %>'">
			<c:set var="aa" value="${heavenLoadBoard.title}"/>
		</td>
		
		<td id="td2">
			<input type="button" value="게시글 등록"
			onclick="location.href='BoardServlet?command=heaven_load_write_form&num=${heavenLoadBoard.num}'">
			
			
			<c:if test="${loginUser.id == heavenLoadBoard.name}">
			<input type="button" value="게시글 수정"
			onclick="open_win('BoardServlet?command=heaven_load_check_pass_form&num=${heavenLoadBoard.num}', 'update')">
			<input type="button" value="게시글 삭제"
			onclick="open_win('BoardServlet?command=heaven_load_check_pass_form&num=${heavenLoadBoard.num}', 'delete')">
			</c:if>		
		</td>
		 
	
	</tr>
	</table>
	</div>



</body>
</html>