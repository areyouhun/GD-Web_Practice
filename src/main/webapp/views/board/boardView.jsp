<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.board.model.dto.Board" %>
<%@ include file="/views/common/head.jsp" %>
<%
	Object boardUncast = request.getAttribute("board");
	Board board = null;

	if (boardUncast != null & boardUncast instanceof Board) {
		board = Board.class.cast(boardUncast);
	}
%>
<title>main page</title>
<style>
section {
	text-align: center;
	height: 600px;
}

.nav-link:visited {
	color: white;
}

section#board-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#board-container h2 {
	margin: 10px 0;
}

table#tbl-board {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-board td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}

background-color:#3300FF;position:relative;top:-20px;}
    /*댓글테이블*/
table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
table#tbl-comment button.btn-reply{display:none;}
table#tbl-comment button.btn-delete{display:none;}
table#tbl-comment tr:hover {background:lightgray;}
table#tbl-comment tr:hover button.btn-reply{display:inline;}
table#tbl-comment tr:hover button.btn-delete{display:inline;}
table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
/*답글관련*/
table#tbl-comment textarea{margin: 4px 0 0 0;}
table#tbl-comment button.btn-insert2{width:60px; height:23px; color:white; background:#3300ff; position:relative; top:-5px; left:10px;}
</style>
</head>
<body>
<%@ include file="/views/common/header.jsp" %>
	<section id="board-container">
		<h2>게시판</h2>
		<table id="tbl-board">
			<tr>
				<th>글번호</th>
				<td><%= board.getBoardNo() %></td>
			</tr>
			<tr>
				<th>제 목</th>
				<td><%= board.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= board.getBoardWriter().getUserId() %></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%= board.getBoardReadCount() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>있으면 이미지출력 없으면 공란, 클릭하면 다운로드할 수 있게 구현</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%= board.getBoardContent() %></td>
			</tr>
			<tr>
				<% if (
					memberLoggedIn != null 
					&& (memberLoggedIn.getUserId().equals("admin") || memberLoggedIn.getUserId().equals(board.getBoardWriter().getUserId()))
				) { %>
	        		<th colspan="2">
						<button class="btn btn-dark">수정</button>
						<button class="btn btn-dark">삭제</button>
					</th>
        		<% } %>
			</tr>
		</table>
		<div id="comment-container">
			<div class="comment-editor">
				<form action="<%= request.getContextPath() %>/board/insertComment.do" onsubmit="return checkIfLoggedIn()" method="post"
					class="px-5 mt-3 d-flex flex-column align-items-end">
					<input type="hidden" name="boardCommentWriter" value="<%= memberLoggedIn.getUserId() != null ? memberLoggedIn.getUserId() : "" %>">
					<input type="hidden" name="boardRef" value="<%= board.getBoardNo() %>">
					<input type="hidden" name="boardCommentRef" value="0">
					<input type="hidden" name="level" value="1">
					<textarea name="boardCommentContent" cols="55" rows="3" 
							placeholder="댓글 입력"
							style="display: inline-block; width: 100%"></textarea>
					<button type="submit" id="btn-insert" class="btn btn-dark" style="width: 15%">등록</button>
				</form>
			</div>
		</div>
	</section>
<script>
const checkIfLoggedIn = () => {
	<%-- if ("<%= memberLoggedIn == null %>" == "true") { --%>
	if (<%= memberLoggedIn == null %>) {
		alert("로그인 하셈");
		$('#userId').focus();
		return false;
	}
	
	if (confirm("댓글을 등록하시겠습니까?")) {
		return true;
	}
	return false;
}	
</script>
<%@ include file="/views/common/footer.jsp" %>