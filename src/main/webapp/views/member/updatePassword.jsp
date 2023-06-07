<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<%
	Object obj = session.getAttribute("memberLoggedIn");
	Member memberLoggedIn = null;
	
	if (obj != null) {
		memberLoggedIn = Member.class.cast(obj);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<style>
#updatePassword-container {
	margin-top: 50px;
}

#updatePassword-container table {
	margin: auto;
	border-spacing: 20px;
}

#updatePassword-container table tr:last-of-type td {
	text-align: center;
}

th, td {
	padding-bottom: 15px;
}

th {
	padding-right: 5px;
}
</style>
</head>
<body>
	<div id="updatePassword-container">
		<form name="updatePwdFrm" action="<%=request.getContextPath()%>/member/updatePasswordEnd.do" method="post">
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td><input type="password" name="password_new" id="password_new" required></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" id="password_confirm" required><br></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="userId" value="<%= memberLoggedIn.getUserId() %>">
						<input type="submit" value="변경" class="btn btn-dark mx-1"/>&nbsp; 
						<input type="button" value="닫기" class="btn btn-dark mx-1" onclick="window.close();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
	</script>
</body>
</html>