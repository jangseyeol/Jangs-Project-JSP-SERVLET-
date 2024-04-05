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
<title>천로역정 게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div id="wrap" align="center">
	<br>
	'${loginUser.id }'님 환영합니다. &nbsp; 
		<input type="button" value="메인페이지" onclick="location.href='main.jsp'" class="w3-button w3-round w3-padding-small w3-green">
		
		<div id="wrap_right"> <a href="BoardServlet?command=heaven_load_write_form"><img src="img/edit2.png" width="17px">천로역정 게시글 쓰기 </a></div>
		<table id="list" class="w3-table w3-bordered w3-hoverable">
         <tr class="w3-pale-green">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
         </tr>
         
         <c:forEach var="board" items="${HeavenLoadboardList }">
            <tr class="record">
               <td>${board.num }</td>
               <td><a href="BoardServlet?command=heaven_load_view&num=${board.num}&page=${currentPage}">${board.title }</a></td>
               <td>${board.name}</td>
               <td><fmt:formatDate value="${board.writedate}" pattern="yyyy.MM.dd HH:mm:ss"/> </td>
               <td>${board.readcount}</td>
            </tr>
         </c:forEach>
          
    </table>
         
         
         
      
         <!-- 페이징시작 -->
     <table>
     	<tr>
     		<c:if test="${startPage!=1}">
     			<a href="./BoardServlet?command=heaven_load_list&page=${startPage-1}">[이전]</a>
     		</c:if>
     		
     		<c:forEach var="i" begin="${startPage}" end="${endPage}" varStatus="cnt">
     			<a href="./BoardServlet?command=heaven_load_list&page=${i}">[
     				<font color="#000000" />
     					<c:if test="${currentPage == i }">
     					<font color="#bbbbbb" />
     					</c:if>
     					${i}
     					</font>]
     			</a>     		
     		</c:forEach>
     		
     		<c:if test="${endPage != totalPage}">
     			<a href="./BoardServlet?command=heaven_load_list&page=${endPage+1}">다음 ▶</a>
     		</c:if>
     </table>
   
   </div>
</body>
</html>