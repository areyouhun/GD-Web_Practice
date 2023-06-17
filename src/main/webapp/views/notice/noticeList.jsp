<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.web.notice.model.dto.Notice" %>
<%
	Object pageBar = request.getAttribute("pageBar");
	Object noticesUncast = request.getAttribute("notices");
	List<Notice> notices = new ArrayList<>();


	if (noticesUncast != null && noticesUncast instanceof ArrayList<?>) {
		for (Object notice : (List<?>) noticesUncast) {
			notices.add(Notice.class.cast(notice));
	}
}
%>
<%@ include file="/views/common/head.jsp" %>
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
	width: 100%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
}

table#tbl-notice th, table#tbl-notice td {
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

.notice-container_btn {
	display: flex;
	justify-content: end;
}
</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	<section id="notice-container">
        <h2>공지사항</h2>
        <div class="notice-container_btn mb-3">
        	<% if (memberLoggedIn != null && memberLoggedIn.getUserId().equals("admin")) { %>
	        	<button class="btn btn-dark" 
	        			onclick="location.assign('<%= request.getContextPath() %>/notice/insertForm.do')">글쓰기</button>
        	<% } %>
        </div>
        <table id="tbl-notice">
            <tr>
            
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>첨부파일</th>
                <th>작성일</th>
            </tr>
			<% if (notices.isEmpty()) { %>
				<tr>
					<td colspan="5">조회된 결과가 없습니다.</td>
				</tr>
			<% } else {
				for (Notice notice : notices) { %>
					<tr>
						<td><%= notice.getNoticeNo() %></td>
						<td><%= notice.getNoticeTitle() %></td>
						<td><%= notice.getNoticeWriter() %></td>
						<td>
							<% if(notice.getFilePath() != null) { %>
								<img width="20px" src="<%= request.getContextPath() %>/images/file.png">
							<% } %>
						</td>
						<td><%= notice.getNoticeDate() %></td>
					</tr>
				<% }
			} %>
        </table>
        <div id="pageBar">
        	<%= pageBar != null ? pageBar : "" %>
        </div>
    </section>
<%@ include file="/views/common/footer.jsp" %>