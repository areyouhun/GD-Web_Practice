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

	</section>
<%@ include file="/views/common/footer.jsp" %>