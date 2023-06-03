<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<%
	Object obj = request.getAttribute("result");
	Member member = null;

	if (obj != null && obj instanceof Member) {
		member = Member.class.cast(obj);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
<style>
	#checkId-container {
		text-align: center;
		padding-top: 50px;
	}
	
	#duplicated {
		color: red;
		font-weight: bolder;
	}
	
	#unique {
		color: green;
		font-weight: bolder;
	}
</style>
</head>
<body>
	<div id="checkId-container">
		<% if (member != null) { %>
			<span id="duplicated"><%= request.getParameter("userId") %></span>는 사용중입니다.<br><br>
			<form action="<%=request.getRequestURI()%>">
				<input type="text" name="userId" id="userId"> 
				<input type="submit" value="중복검사">
			</form>
		<% } else { %>
			<span id="unique"><%=request.getParameter("userId")%></span>(은)는 사용가능합니다. <br><br>
			<button type="button" onclick="confirmId();">닫기</button>
		<% } %>
	</div>
	<script>
		const confirmId = () => {
			// 이 페이지는 jQuery 파일 없음
			window.opener.document.getElementById('idToUse').value = "<%=request.getParameter("userId")%>";
			window.opener.setIdValid();
			window.close();
		}
	</script>
</body>
</html>