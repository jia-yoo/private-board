<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀 게시판😏</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="container">
	<form class="regForm" action="regist" method="post" onsubmit="return regFormConfirm()">
	<h1 class="page_title">회원가입</h1>
		<input type="text" name="id" id="id" placeholder="아이디"> 
		<div id="demo"></div>
		<a id="idCheck" onclick="idCheck()">중복체크</a>
		<input type="password" name="pw" id="pw" placeholder="비밀번호"> <br>
		<input type="text" name="name" id="name" placeholder="이름"> <br>
		<input class="btn" type="submit" value="회원가입하기"> <br>
	</form>
</div>
<script>
// 중복체크 버튼 관련 메서드만들기
const id = document.querySelector("#id");
function idCheck(){
	console.log("아이디 중복체크");
	const xhttp = new XMLHttpRequest();
	  xhttp.onload = function() {
	    document.getElementById("demo").innerHTML = this.responseText;
	    }
	  xhttp.open("GET", "/regLoginCheck?id="+ id.value, true);
	  xhttp.send();
}

function regFormConfirm(){
	if(document.querySelector("#id").value.length == 0){
		alert("id를 입력하세요");
		return false;
	}else{
		if(document.querySelector("#demo").textContent == ""){
			console.log("내용 없음");
			alert("ID 중복 체크를 진행해주세요.");
			return false;
		}else if(document.querySelector("#demo").textContent == "이미 사용중인 ID입니다."){
			console.log("이미 사용 중");
			alert("ID 중복 체크를 다시 진행해주세요.");
			return false;
		}
	}
	if(document.querySelector("#pw").value.length == 0){
		alert("pw를 입력하세요");
		return false;
	}
	if(document.querySelector("#name").value.length == 0){
		alert("이름을 입력하세요");
		return false;
	}
}
	if(${result ne null}){
		console.log("xx");
		if(${result eq true}){
			alert("성공적으로 회원가입이 완료되었습니다. 로그인 화면으로 이동합니다.");
				location.href="loginForm";
		}
	}


</script>
</body>
</html>