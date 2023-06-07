<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%@ include file="/views/common/head.jsp" %>
<% 
	Object membersUncast = request.getAttribute("members");
	Object pageBarUncast = request.getAttribute("pageBar");
	
	List<Member> members = new ArrayList<>();
	String pageBar = "";
	
	if (membersUncast != null && membersUncast instanceof ArrayList<?>) {
		for (Object obj : (List<?>) membersUncast) {
			members.add(Member.class.cast(obj));
		}
	}
	
	if (pageBarUncast != null && pageBarUncast instanceof String) {
		pageBar = String.valueOf(pageBarUncast);
	}
%>
<title>main page</title>
<style type="text/css">
section#memberList-container {
	text-align: center;
}

section#memberList-container table#tbl-member {
	width: 100%;
	border: 1px solid gray;
	border-collapse: collapse;
}

section#memberList-container table#tbl-member th, table#tbl-member td {
	border: 1px solid gray;
	padding: 10px;
}

#pageBar {
	margin: 20px 0;
}

#pageBar a, #pageBar span {
	text-decoration: none;
	font-size: 1.5rem;
	margin-left: 2%;
	margin-right: 2%;
}

#pageBar a, #pageBar a:visited {
	color: gray;
}

#pageBar a:hover {
	font-weight: bolder;
	color: black;
}

.prev, .next {
	color: gray;
}

div#search-container {margin:0 0 10px 0; padding:3px;}

div#search-userId{display:inline-block;}
    
div#search-userName{display:none;}

div#search-gender{display:none;}

div#numPerpage-container{float:right;}

form#numperPageFrm{display:inline-block;}
</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp" %>
	<section id="memberList-container" class="px-3">
		<h2 class="mt-5">회원관리</h2>
		<div id="search-container" class="d-flex justify-content-center align-items-center"> 
			<p class="mb-0 me-1">검색 타입</p>
			<select id="searchType" class="py-1 me-1">
				<option value="userId">아이디</option>
				<option value="userName">회원이름</option>
				<option value="gender">성별</option>
			</select>
			<div id="search-userId">
				<form action="<%=request.getContextPath()%>/admin/searchMember">
					<input type="hidden" name="searchType" value="userId"> 
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요">
					<button type="submit" class="btn btn-secondary py-0">검색</button>
				</form>
			</div>
			<div id="search-userName">
				<form action="<%=request.getContextPath()%>/admin/searchMember">
					<input type="hidden" name="searchType" value="userName"> 
					<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요">
					<button type="submit" class="btn btn-secondary">검색</button>
				</form>
			</div>
			<div id="search-gender">
				<form action="<%=request.getContextPath()%>/admin/searchMember">
					<input type="hidden" name="searchType" value="gender"> 
					<label><input type="radio" name="searchKeyword" value="M">남</label> 
					<label><input type="radio" name="searchKeyword" value="F">여</label>
					<button type="submit" class="btn btn-secondary">검색</button>
				</form>
			</div>
		</div>
		<div id="numPerpage-container" class="d-flex">
			<p class="me-1">페이지당 회원수</p>
			<form id="numPerFrm" class="me-3">
				<select name="numPerPage" id="numPerPage">
					<option value="10">10</option>
					<option value="5">5</option>
					<option value="3">3</option>
				</select>
			</form>
		</div>
		<table id="tbl-member">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>나이</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>취미</th>
					<th>가입날짜</th>
				</tr>
			</thead>
			<tbody>
			<% if (members.isEmpty()) { %>
				<tr>
					<td colspan="9">조회된 결과가 없습니다.</td>
				</tr>
			<% } else {
				for (Member member : members) { %>
					<tr>
						<td><%= member.getUserId() %></td>
						<td><%= member.getUserName() %></td>
						<td><%= member.getGender() %></td>
						<td><%= member.getAge() %></td>
						<td><%= member.getEmail() %></td>
						<td><%= member.getPhone() %></td>
						<td><%= member.getAddress() %></td>
						<td><%= member.getHobby() == null ? "" : String.join(",", member.getHobby()) %></td>
						<td><%= member.getEnrollDate() %></td>
					</tr>
				<% }
			} %>
			</tbody>
		</table>
		<% if (!pageBar.isEmpty()) { %>
			<div id="pageBar">
				<%= pageBar %>
			</div>
		<% } %>
	</section>
<%@ include file="/views/common/footer.jsp" %>