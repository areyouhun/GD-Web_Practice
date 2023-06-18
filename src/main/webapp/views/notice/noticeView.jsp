<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.notice.model.dto.Notice" %>
<%@ include file="/views/common/head.jsp" %>
<%
	Object noticeUncast = request.getAttribute("notice");
	Notice notice = null;

	if (noticeUncast != null & noticeUncast instanceof Notice) {
		notice = Notice.class.cast(noticeUncast);
	}
%>
<title>공지사항</title>
<style>
section {
	text-align: center;
	height: 600px;
}

.nav-link:visited {
	color: white;
}

section#notice-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}

.download-container {
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	<section id="notice-container">
		<h2>공지사항</h2>
		<table id="tbl-notice" class="mb-3">
			<tr>
				<th>제 목</th>
				<td><%= notice.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= notice.getNoticeWriter() %></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<% if (notice.getFilePath() != null) { %>
						<div class="download-container" onclick="downloadFile('<%= notice.getFilePath() %>');">
							<img src="<%= request.getContextPath() %>/images/file.png"><span><%= notice.getFilePath() %></span>
						</div>
					<% } %>
				</td>
			</tr>
			<tr>
				<th>내 용</th>
				<td><%= notice.getNoticeContent() %></td>
			</tr>
			<% if (
				memberLoggedIn != null 
				&& (memberLoggedIn.getUserId().equals("admin") || memberLoggedIn.getUserId().equals(notice.getNoticeWriter()))
			) { %>
				<tr>
					<th colspan="2"><input type="button" value="수정하기" onclick="">
						<input type="button" value="삭제하기" onclick=""></th>
				</tr>
			<% } %>
		</table>
	</section>
	<script>
		const downloadFile = (fileName) => {
			location.assign("<%= request.getContextPath() %>/fileDownload.do?fileName=" + fileName);
		};
	</script>
<%@ include file="/views/common/footer.jsp" %>