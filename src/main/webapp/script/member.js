function loginCheck(){
	
	console.log("loginCheck");
	
	if(document.frm.id.value.length == 0){
		alert("아이디를 써주세요.");
		frm.id.focus();
		return false;
	}

	if(document.frm.pwd.value == ""){
		alert("암호는 반드시 입력해야 합니다.");
		frm.pwd.focus();
		return false;
	}
	
	return true;
}


function idCheck(){
	
	if(document.frm.id.value == ""){
		alert("아이디 입력하여 주십시오.");
		document.frm.id.focus();
		return false;
	}
	
	var url = "LoginServlet?command=id_check_form&id=" + document.frm.id.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	

}

function idCheck_Check(){
	
	if(document.frm.id.value == ""){
		alert("아이디 입력하여 주십시오.");
		document.frm.id.focus();
		return false;
	}

}

function idok(id){
	
	if(document.frm.id.value == ""){
		alert("아이디 입력하여 주십시오.");
		document.frm.id.focus();
		return false;
	}
	
	opener.frm.id.value = document.frm.id.value;
	opener.frm.reid.value = document.frm.id.value;
	self.close();
	
}

function joinCheck(){
	
	console.log("------------------");
	if(document.frm.id.value.length==0){
		alert("아이디를 입력해주십시오.");
		document.frm.id.focus();
		return false;
	}

	if(document.frm.id.value.length < 0){
		alert("아이디는 4글자이상이여야 합니다.");
		document.frm.id.focus();
		return false;
	}
	
	if(document.frm.reid.value.length==0){
		alert("중복 체크를 하지 않았습니다.");
		frm.userid.focus();
		return false;	
	}
	
	if(document.frm.pass.value == ""){
		alert("비밀번호는 반드시 입력하여주십시오.");
		document.frm.pass.focus();
		return false;
	}

	//비밀번호 재확인
	if(document.frm.pass.value != document.frm.pass_check.value){
		alert("암호가 일치하지 않습니다.");
		document.frm.pass.focus();
		return false;
	}
	
	if(document.frm.name.value.length==0){
		alert("이름을 입력해 주십시오.");
		document.frm.name.focus();
		return false;
	}
	
	if(document.frm.gender.value == "0"){
		alert("성별을 선택해주십시오.");
		document.frm.gender.focus();
		return false;
	}

	if(document.frm.phone.value == ""){
		alert("전화번호를 입력해주십시오.");
		document.frm.phone.focus();
		return false;
	}
	
	return true;
}