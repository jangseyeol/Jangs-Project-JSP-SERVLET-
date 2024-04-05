<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty loginUser}">
	<jsp:forward page='loginsub.do' />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>풍족한교회 미니홈페이지 메인화면</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="script/parallax.js"></script>
<link rel="stylesheet" type="text/css" href="css/main.css">
<body>
	<jsp:include page="header.jsp"></jsp:include>

<div class="parallax_window_div1" date-parallax="scroll" date-image-src="">
	<br><br><br>
	
	<div class="div2">
		<h1>풍족한교회 미니홈페이지</h1>
		<p> 풍족한교회는 <b>대한예수교장로회 대신 교단</b>에 속해 있는 건강한 교회입니다.</p>
		<p> 이곳은 풍족한 교회에서 함께 <b>신앙생활</b> 하며 하나가 되어<p> 천국을 향해 나아가는 <b>하나님의 자녀들을 위한 교제의 공간</b> 입니다.</p>	
	</div>
</div>

<div class="outwrap">
<div class="inwrap" style="margin-top: 10px">
	<br>
	'${loginUser.id}'님 환영합니다.
	<!-- onclick="location.href='LoginServlet?command=logout_finish'" -->
		<input type="button" value="회원정보" onclick="location.href='LoginServlet?command=mypage_form'" class="w3-button w3-round w3-padding-small w3-dark-grey">
		<input type="button" value="로그아웃" onclick="location.href='LoginServlet?command=logout_finish'" class="w3-button w3-round w3-padding-small w3-dark-grey"></br>
	<br>
		<div class="contents1">
			<figure class="snip1384">
			<img src="img/bible.jpg" alt="sample83" height="430px" />
			<figcaption>
				<h3>게시판 바로가기</h3>
				<p>오직예수! 오직십자가! 오직부활!</p>
				<p>풍족한교회 미니홈페이지의<br> 게시판입니다.<br>다음과 같은 사항을 게시해주세요.<br>1.중보기도<br>2.간증<br>3.성경질문<br>4.친교,교제신청<br>5.특별찬송신청</p>
			</figcaption>
			<a href="BoardServlet?command=board_list">
			</a>
			</figure>
		</div>
		
		<div class="contents2">
			<figure class="snip1384">
			<img src="img/heavenload.jpg" alt="sample83" height="430px" />
			<figcaption>
				<h3>천로역정 바로가기</h3>			
				<p>17세기 영국 청교도 목회자로서, <br>신앙과 설교사역 때문에 옥고를 치른 존 번연(John Bunyan)이, <br>옥중에서 쓴 천로역정(The Pilgrim's Progress, 1678년 초판발행)은 <br>성경 다음가는 베스트셀러로 널리 <br>알려진 크리스천 서사시극이다.<br>하나님의 자녀로서 어떠한 삶과 <br>인생의 여정을 걸어가야하는지 <br>'천로역정'을 통해 살펴보자. </p>
			</figcaption>
			<a href="BoardServlet?command=heaven_load_list">
			</a>
			</figure>
		</div>

</div>
</div>
<br><br><br>
</body>

<script type="text/javascript">
$('.parallax_window_div1').parallax({imageSrc: 'img/cloud.jpg'});
</script>


</html>