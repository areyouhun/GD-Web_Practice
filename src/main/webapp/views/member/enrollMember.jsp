<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/head.jsp"%>
<title>회원가입</title>
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
		<h2 class="mb-4">회원 가입</h2>
		<form action="<%=request.getContextPath()%>/member/enrollMemberEnd.do" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" placeholder="4글자이상" name="userId" id="userIdToUse"> 
						<input type="button" value="중복확인" class="btn btn-secondary py-1">
					</td>
				</tr>
				<tr>
					<th>패스워드</th>
					<td><input type="password" name="password" id="passwordToUse"><br></td>
				</tr>
				<tr>
					<th>패스워드 확인</th>
					<td><input type="password" id="passwordToCheck"><br></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" id="userName"><br></td>
				</tr>
				<tr>
					<th>나이</th>
					<td><input type="number" name="age" id="age"><br></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="email" placeholder="abc@xyz.com" name="email" id="email"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰</th>
					<td>
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required><br>
					</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>
						<input type="text" placeholder="" name="address" id="address"><br>
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" id="gender0" value="M"><label for="gender0">남</label> 
						<input type="radio" name="gender" id="gender1" value="F"><label for="gender1">여</label>
					</td>
				</tr>
				<tr>
					<th>취미</th>
					<td>
						<input type="checkbox" name="hobby" id="hobby0" value="운동"><label for="hobby0">운동</label> 
						<input type="checkbox" name="hobby" id="hobby1" value="등산"><label for="hobby1">등산</label> 
						<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label>
						<input type="checkbox" name="hobby" id="hobby3" value="게임"><label for="hobby3">게임</label> 
						<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label>
					</td>
				</tr>
			</table>
			<div class="d-flex justify-content-center">
				<input type="submit" value="가입" class="btn btn-dark mx-1">
				<input type="reset" value="취소" class="btn btn-dark mx-1">
			</div>
		</form>
	</section>
<%@ include file="/views/common/footer.jsp" %>