                                                                                                <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>풍족한교회 미니홈페이지-login</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="script/member.js"></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<div class="outwrap">
		<div class="inwrap" style="margin-top: 100px">
		<div class="form">
			<form name="frm" method="post" action="LoginServlet">
			<input type="hidden" name="command" value="login_success">	
				<table class="w3-table">
					<img src="./img/churchface.png" width="250px">
					<tr></tr>
					<tr>
						<td id="label"><label for="id">ID</label></td>
					</tr>
					<tr>
						<td><input type="text" name="id" id="id" class="w3-input w3-border w3-round" value="${id}">
						</td>
					</tr>
					<tr>
						<td id="label"><label for="pass">비밀번호</label>
						</td>
					</tr>
					<tr>
						<td><input type="password" id="pass" name="pass" class="w3-input w3-border w3-round">
						</td>
					</tr>
					<tr>
						<td><input type="submit" value="로그인" onclick="return loginCheck()" class="w3-button w3-round w3-dark-grey"></td>
					</tr>
					
					  <!-- 네이버 로그인 버튼 노출 영역 -->
  <div id="naver_id_login"></div>
  <!-- //네이버 로그인 버튼 노출 영역 -->
  <script type="text/javascript">
  	var naver_id_login = new naver_id_login("YOUR_CLIENT_ID", "YOUR_CALLBACK_URL");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("YOUR_SERVICE_URL");
  	naver_id_login.setState(state);
  	naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
  </script>
					<tr>
						<td><input type="button" value="회원가입" onclick="location.href='LoginServlet?command=join_form'" style="text-decoration: none;"></a>
						</td>
					</tr>
					<tr>
						<td colspan="2">${message }</td>
					</tr>
				</table>
				
			</form>
		</div>
		<div class="img">
			<figure class="snip1445"><img src="./img/light2.jpg" width="440px" height="440px"/>
			<figcaption>
				<div>
					<h3>세상의 빛과 소금이 
					<br>되는 교회</h3>
				</div>
			</figcaption>
			<a href="#"></a>
		</figure>
		</div>
		</div>
	</div>
</body>
</html>