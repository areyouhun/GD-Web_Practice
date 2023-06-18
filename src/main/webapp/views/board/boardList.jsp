<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.web.board.model.dto.Board" %>
<%@ include file="/views/common/head.jsp" %>
<%
	Object pageBar = request.getAttribute("pageBar");
	Object boardsUncast = request.getAttribute("boards");
	List<Board> boards = new ArrayList<>();


	if (boardsUncast != null && boardsUncast instanceof ArrayList<?>) {
		for (Object boardUncast : (List<?>) boardsUncast) {
			boards.add(Board.class.cast(boardUncast));
	}
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
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-board th, table#tbl-board td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}
/*글쓰기버튼*/
input#btn-add {
	float: right;
	margin: 0 0 15px;
}
/*페이지바*/
div#pageBar {
	margin-top: 10px;
	text-align: center;
}

#pageBar a {
	color: black;
	text-decoration: none;
}
</style>

</head>
<body>
<%@ include file="/views/common/header.jsp" %>
<section id="board-container">
	<h2>게시판 </h2>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<th>조회수</th>
		</tr>
		<% if (boards.isEmpty()) { %>
				<tr>
					<td colspan="6">조회된 결과가 없습니다.</td>
				</tr>
			<% } else {
				for (Board board : boards) { %>
					<tr>
						<td><%= board.getBoardNo() %></td>
						<td>
							<a href="<%= request.getContextPath() %>/board/boardView.do?no=<%=board.getBoardNo() %>"><%= board.getBoardTitle() %></a>
						</td>
						<td><%= board.getBoardWriter().getUserId() %></td>
						<td><%= board.getBoardDate() %></td>
						<td>
							<% if(board.getBoardRenamedFilename() != null) { %>
								<img width="20px" src="<%= request.getContextPath() %>/images/file.png">
							<% } %>
						</td>
						<td><%= board.getBoardReadCount() %></td>
					</tr>
				<% }
			} %>
    </table>
    <div id="pageBar">
    	<%= pageBar != null ? pageBar : "" %>
    </div>
</section>
<%@ include file="/views/common/footer.jsp" %>