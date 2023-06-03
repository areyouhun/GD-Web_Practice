<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ include file="/views/common/head.jsp"%>
<%
	Object memberInfoUncast = request.getAttribute("memberInfo");
	Member memberInfo = null;
	
	if (memberInfoUncast != null) {
		memberInfo = Member.class.cast(memberInfoUncast);
	}
	
	List<String> hobbies = List.of("운동", "등산", "독서", "게임", "여행"); // 따로 저장해놓기
	List<String> hobbiesChecked = new ArrayList<>();
	if (memberInfo != null) {
		List<String> memberHobbies = Arrays.asList(memberInfo.getHobby());
		
		hobbies.forEach(hobby -> {
			if (memberHobbies.contains(hobby)) {
				hobbiesChecked.add("checked");
			} else {
				hobbiesChecked.add("");
			}
		});
	}
%>
<title>마이 페이지</title>
<style>
	th, td {
		padding-bottom: 15px;
	}
	
	th {
		padding-right: 5px;
	}
</style>
</head>
<body>
	<%@ include file="/views/common/header.jsp"%>
	<section id="enroll-container" class="d-flex flex-column align-items-center my-4">
		<h2 class="mb-4">회원 정보</h2>
		<form id="memberFrm" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userId" id="userId_" value="<%=memberInfo.getUserId()%>" readonly></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" id="userName" required value="<%=memberInfo.getUserName()%>"><br></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age" value="<%=memberInfo.getAge()%>"><br></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%=memberInfo.getEmail()%>"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td><input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%=memberInfo.getPhone()%>"><br></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" placeholder="" name="address" id="address" value="<%=memberInfo.getAddress()%>"><br>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td><input type="radio" name="gender" id="gender0" value="M" <%=memberInfo.getGender().equals("M") ? "checked" : ""%>>
						<label for="gender0">남</label> 
						<input type="radio" name="gender" id="gender1" value="F" <%=memberInfo.getGender().equals("F") ? "checked" : ""%>>
						<label for="gender1">여</label>
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						<% for (String hobby : hobbies) { %>
						<input type="checkbox" name="hobby" id="hobby<%= hobbies.indexOf(hobby) %>" value="<%= hobby %>" <%= hobbiesChecked.get(hobbies.indexOf(hobby)) %>>
						<label for="hobby<%= hobbies.indexOf(hobby) %>"><%= hobby %></label>
						<% } %>
					</td>
				</tr>
			</table>
			<input type="button" value="비밀번호 수정" class="btn btn-dark mx-1"/>
			<input type="button" value="정보수정" class="btn btn-dark mx-1"/> 
			<input type="button" value="탈퇴" class="btn btn-dark mx-1"/>
		</form>
	</section>
	<%@ include file="/views/common/footer.jsp"%>