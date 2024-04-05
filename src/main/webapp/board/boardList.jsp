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
	<br>
	'${loginUser.id }'님 환영합니다. &nbsp; 
		<input type="button" value="메인페이지" onclick="location.href='main.jsp'" class="w3-button w3-round w3-padding-small w3-green">
		
		<div id="wrap_right"> <a href="BoardServlet?command=board_write_form"><img src="img/edit2.png" width="17px">게시글 등록 </a></div>
		<table id="list" class="w3-table w3-bordered w3-hoverable">
         <tr class="w3-pale-green">
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
         </tr>
         
         <c:forEach var="board" items="${boardList }">
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
         
         
         
      
         <!-- 페이징시작 -->
     <table>
     	<tr>
     		<c:if test="${startPage!=1}">
     			<a href="./BoardServlet?command=board_list&page=${startPage-1}">[이전]</a>
     		</c:if>
     		
     		<c:forEach var="i" begin="${startPage}" end="${endPage}" varStatus="cnt">
     			<a href="./BoardServlet?command=board_list&page=${i}">[
     				<font color="#000000" />
     					<c:if test="${currentPage == i }">
     					<font color="#bbbbbb" />
     					</c:if>
     					${i}
     					</font>]
     			</a>     		
     		</c:forEach>
     		
     		<c:if test="${endPage != totalPage}">
     			<a href="./BoardServlet?command=board_list&page=${endPage+1}">다음 ▶</a>
     		</c:if>
     </table>
     
     
         
            <!-- 검색시작 -->   
         
                             
        <div id="wrap" align="center">
                   
          <table class="w3-table w3-bordered w3-hoverable" >
          <form name="frm" method="post" action="BoardServlet">
			<input type="hidden" name="command" value="board_search" > 
				<tr class="w3-pale-green">
					<td>
					<div class="hello">
						<select id="searchCondition" name="searchCondition">
							<option value="title">제목</option>
							<option value="name">작성자</option>
						</select>
						<input id="searchKeyword" name="searchKeyword" type="text">
						<input type="submit" value="검색">
					</div>
					</td>
				</tr>		
		 </form>		 
		</table>
		
		</div>
   
   </div>
</body>
</html>