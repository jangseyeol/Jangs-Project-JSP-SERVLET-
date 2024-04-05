function boardCheck() {
	if (document.frm.name.value.length == 0) {
		alert("작성자를 입력하세요.");
		frm.name.focus();
		return false;
	}
	
	if(document.frm.pass.value.length == 0){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pass.focus();
		return false;
	}
	return true;
}

function del_check(board_num){
	var result = confirm("글을 삭제하시겠습니까?");
    if(result){
    	location.href="BoardServlet?command=board_delete&num="+board_num;
    }
	
}

function like(){
	  $.ajax({
		    url: "BoardServlet",
		    type: "POST",
		    cache: false,
		    dataType: "json",
		    data: $('#like_form').serialize(),   //아이디가 like_form인 곳의 모든 정보를 가져와  파라미터 전송 형태(표준 쿼리형태)로 만들어줌
		    success: 
		    function(data){      					//ajax통신 성공시 넘어오는 데이터 통째 이름 =data
		    	alert("'좋아요'가 반영되었습니다!") ;  // data중 put한 것의 이름 like
                $("#like_result").html(data.like);  //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
		    },   
		    
		    error: 
		    function (request, status, error){  
		      alert("ajax실패")                  
		    }
		  });
}

function open_win(url, name){
	window.open(url, name, "width=500, height=230");
}