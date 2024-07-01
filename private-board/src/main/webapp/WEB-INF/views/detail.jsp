<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

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
	<h1 class="page_title">게시글 상세보기</h1>
	<a class="btn myinfobtn" href="updateBoard?bno=${board.bno}">글 수정하기</a>
	<table class="boardTable" border="1">
		<tbody>
			<tr>
				<td class="title">제목</td>
				<td>${board.title} </td>
			</tr>
			<tr>
				<td class="title">내용</td>
				<td>${board.content} </td>
			</tr>
			<tr>
				<td class="title">작성자</td>
				<td>${board.writer} </td>
			</tr>
			<tr>
				<td class="title">작성날짜</td>
				<td>${board.regTime} </td>
			</tr>
		</tbody>
	</table>
	
	<div class="container_comment">
	<h3 class="subTitle">댓글</h3>
		<c:forEach var="commentList" items="${commentList }">
			<div class="commentBox">
				<p class="writer">${commentList.writer }</p>
				<p class="content">${commentList.content }</p>
				<p class="regTime">${commentList.regTime }</p>
				<c:if test="${sessionScope.id == commentList.writer }">
				<input class="btn upatebtn" type="button" value="수정" onclick="updateComment()">
				<a class="btn deletebtn" href="deleteComment?cno=${commentList.cno }&bno=${board.bno}">삭제</a>
				</c:if>
			</div>
		</c:forEach>
	</div>
	
	<h3 class="subTitle">댓글 등록 창</h3>
		<form class="regComment_form" action="regComment" method="post">
			<input type="hidden" name="bno" value="${board.bno }">
			<div class="writerCommentBox">
				<p class="writer">${sessionScope.id }</p>
				<input type="text" class="comment_content_input" name="comment_content" placeholder="댓글 작성란">
				<input class="btn commentWriteBtn" type="submit" value="댓글 등록">
			</div>
		</form>

</div>

<script>
	function updateComment(){
		var comment_content = document.querySelector("#comment_content")
		comment_content.value = "";
	}
</script>
</body>
</html>