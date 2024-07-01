<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀 게시판😏</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<header>
			<a class="home_btn" href="/">🏠 HOME</a>
			<span id="name"></span>
			<a id="loginBtn" href="loginForm">로그인</a>
			<a id="regBtn" href="regForm">회원가입</a>
			<a id="myPageBtn" href="myPage">마이페이지</a>
			<a id="logoutBtn" href="logout">로그아웃</a>
	</header>
	
	<script>
	
	const loginBtn = document.querySelector("#loginBtn");
	const regBtn = document.querySelector("#regBtn");
	const myPageBtn = document.querySelector("#myPageBtn");
	const logoutBtn = document.querySelector("#logoutBtn");
	const userName = document.querySelector("#name");
	
		if(${sessionScope.loginInfo eq null}){
			myPageBtn.style.display="none";
			logoutBtn.style.display="none";
		}else{
			loginBtn.style.display="none";
			regBtn.style.display="none";
			myPageBtn.style.display="block";
			logoutBtn.style.display="block";
			userName.innerHTML="어서오세요! ${sessionScope.loginInfo.getName()} 님😀";
		}
	</script>
</body>
</html>