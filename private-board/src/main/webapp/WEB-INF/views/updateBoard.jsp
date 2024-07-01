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

	<form class="updateBoardForm" action="updateBoardInfo?bno=${board.bno}" method="post" onsubmit="return validation()">
		<h1 class="page_title">글 수정</h1>
		<input type="text" name="title" value="${board.title}">
		<textarea rows="5" name="content" placeholder="내용">${board.content}</textarea>
		<input id="receiverId" type="text" name="receiver" value="${board.receiver}">
		<span class="info"></span>
		<a id="idCheckBtn" class="btn" >아이디 체크</a>
		<input id="submitBtn" class="btn" type="submit" value="글등록하기">
	</form>
</div>
<script>
// 아이디 체크 관련 메서드만들기
const idCheckBtn = document.querySelector("#idCheckBtn");
let doneIdCheck = 0;

idCheckBtn.addEventListener("click", checkId);

function checkId(){
	const receiverId = document.querySelector("#receiverId").value;
	  const xhttp = new XMLHttpRequest();
	  xhttp.onload = function() {
		  if(this.responseText == "1"){
			  document.querySelector(".info").innerHTML = "선택 가능한 아이디입니다"
			  doneIdCheck = 1;
		  }else{
			  document.querySelector(".info").innerHTML = "존재하지 않는 아이디입니다. 다시 입력해주세요."
			  doneIdCheck = 0;
		  }
	    }
	  xhttp.open("post", "checkReceiverId?Rid="+ receiverId, true);
	  xhttp.send();
}

function validation(){
	
	if(doneIdCheck == 1){
		return true;
	}else{
		alert("글 등록을 위해서는 보낼 사람의 아이디 체크를 진행해주세요!");
	}
	return false;	
}

</script>
</body>
</html>