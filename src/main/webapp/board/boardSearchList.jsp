<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty loginUser}">
	<jsp:forward page='loginsub.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>풍족한교회 게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div id="wrap" align="center">
		<input type="button" value="메인페이지" onclick="location.href='main.jsp'" class="w3-button w3-round w3-padding-small w3-green">
		
		<table id="list" class="w3-table w3-bordered w3-hoverable">
         <tr class="w3-pale-green">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
         </tr>
         
         <c:forEach var="board" items="${SearchList }">
            <tr class="record">
               <td>${board.num }</td>
               <td><a href="BoardServlet?command=board_view&num=${board.num}&page=${currentPage}">${board.title }</a>
                &nbsp; <b style="color:red">[${board.comment_count}]</b></td>
               <td>${board.name}</td>
               <td><fmt:formatDate value="${board.writedate}" pattern="yyyy.MM.dd HH:mm:ss"/> </td>
               <td>${board.readcount}</td>
            </tr>
         </c:forEach>
          
    </table>
         
 
   </div>
</body>
</html>