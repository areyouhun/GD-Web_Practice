<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.List" %>
<%@ include file="/views/common/head.jsp" %>
<% 
	Object membersUncast = request.getAttribute("members");
	Object pageBar = request.getAttribute("pageBar");
	
	List<Member> members = new ArrayList<>();
	
	if (membersUncast != null && membersUncast instanceof ArrayList<?>) {
		for (Object obj : (List<?>) membersUncast) {
			members.add(Member.class.cast(obj));
		}
	}
	
	String type = request.getParameter("searchType");
	String keyword = request.getParameter("searchKeyword");
	
	int numPerPage = 5;
	if (request.getAttribute("numPerPage") != null) {
		numPerPage = (int) request.getAttribute("numPerPage");
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

div#search-container {
	margin: 0 0 10px 0; 
	padding: 3px;
}

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
				<option value="userId" <%= (type != null && type.equals("userId")) ? "selected" : "" %>>아이디</option>
				<option value="userName" <%= (type != null && type.equals("userName")) ? "selected" : "" %>>회원이름</option>
				<option value="gender" <%= (type != null && type.equals("gender")) ? "selected" : "" %>>성별</option>
			</select>
			<div id="search-userId">
				<form action="<%=request.getContextPath()%>/admin/searchMember">
					<input type="hidden" name="searchType" value="userId"> 
					<input type="text" name="searchKeyword" size="25" 
							value="<%= (type != null && type.equals("userId")) ? keyword : "" %>"
							placeholder="검색할 아이디를 입력하세요">
					<button type="submit" class="btn btn-secondary py-0">검색</button>
					<input type="hidden" name="numPerPage" value="<%= numPerPage %>">
				</form>
			</div>
			<div id="search-userName">
				<form action="<%=request.getContextPath()%>/admin/searchMember">
					<input type="hidden" name="searchType" value="userName"> 
					<input type="text" name="searchKeyword" size="25" 
							value="<%= (type != null && type.equals("userName")) ? keyword : "" %>"
							placeholder="검색할 이름을 입력하세요">
					<button type="submit" class="btn btn-secondary py-0">검색</button>
					<input type="hidden" name="numPerPage" value="<%= numPerPage %>">
				</form>
			</div>
			<div id="search-gender">
				<form action="<%=request.getContextPath()%>/admin/searchMember" onsubmit="return isChecked();">
					<input type="hidden" name="searchType" value="gender"> 
					<label><input type="radio" name="searchKeyword" 
							<%= (type != null && type.equals("gender") 
								&& keyword.equals("M")) ? "checked" : "" %>
								value="M">남</label> 
					<label><input type="radio" name="searchKeyword" 
							<%= (type != null && type.equals("gender") 
								&& keyword.equals("F")) ? "checked" : "" %>
								value="F">여</label>
					<button type="submit" class="btn btn-secondary py-0">검색</button>
					<input type="hidden" name="numPerPage" value="<%= numPerPage %>">
				</form>
			</div>
		</div>
		<div id="numPerpage-container" class="d-flex">
				<p class="me-1">페이지당 회원수</p>
				<form id="numPerFrm" class="me-3">
					<select name="numPerPage" id="numPerPage">
						<option value="10" <%= numPerPage == 10 ? "selected" : "" %>>10</option>
        				<option value="5" <%= numPerPage == 5 ? "selected" : "" %>>5</option>
        				<option value="3" <%= numPerPage == 3 ? "selected" : "" %>>3</option>
					</select>
					<input type="hidden" name="searchType" value="<%= type %>">
					<input type="hidden" name="searchKeyword" value="<%= keyword %>">
				</form>
		</div>
		<table id="tbl-member" class="mb-3">
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
			<% if (pageBar != null) { %>
				<div id="pageBar">
					<%= pageBar %>
				</div>
			<% } %>
	</section>
<script>
$('#searchType').change((event) => {
	$("div[id^='search-']").css('display', 'none');
	$(`#search-\${event.target.value}`).css('display', 'inline-block');
});

$(() => {
	$('#searchType').change();
	
	$("#numPerPage").change(event => {
		let url = "";
		if (!location.href.includes("searchType")) {
			url = "<%= request.getContextPath() %>/admin/memberList.do";
		} else {
			url = "<%= request.getContextPath() %>/admin/searchMember";
		}
		
		$("#numPerFrm").attr("action", url);
		$("#numPerFrm").submit();
	});
});

const isChecked = () => {
	if(!$("input:radio[name=searchKeyword]").is(":checked")) {
		alert('성별을 체크해주세요.');
		return false;
	}
	return true;
}
</script>
<%@ include file="/views/common/footer.jsp" %>