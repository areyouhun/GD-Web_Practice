<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.dto.Member" %>
<%
	Object result = request.getAttribute("result");
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
		<% if (result != null) { %>
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
			window.opener.document.getElementById('idToUse').setAttribute('duplicate', 'false');
			window.opener.document.getElementById('idToUse').value = "<%= request.getParameter("userId") %>";
			window.close();
		}
	</script>
</body>
</html>