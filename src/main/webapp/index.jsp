<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@2.0/nanumsquare.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.7.0.min.js"></script>
<title>main page</title>
<style>
section {
	text-align: center;
	height: 600px;
}
</style>
</head>
<body>
	<header class="px-3 py-3">
		<div class="d-flex justify-content-between align-items-center">
			<h1 class="fw-bolder">HEADER</h1>
			<form>
				<div class="account d-flex justify-content-between">
					<div class="account_left d-flex flex-column justify-content-between me-2">
						<div>
							<input id="userId" name="userId" placeholder="ID">
						</div>
						<div>
							<input type="password" id="userPw" name="userPw" placeholder="PW">
						</div>
					</div>
					<div class="account_right d-flex flex-column justify-content-between">
						<div>
							<input type="submit" class="btn btn-light" value="로그인">
						</div>
						<div>
							<input type="button" class="btn btn-secondary" value="회원가입">
						</div>
					</div>
				</div>
				<div class="d-flex align-items-center">
					<input type="checkbox" name="saveId" id="saveId">
					<label for="saveId">아이디저장</label>
				</div>
			</form>
		</div>
		<nav>
		      <ul class="d-flex justify-content-center px-5 mb-0">
		        <li class="nav-item fw-bolder mx-5">
		          <a class="nav-link" href="#">HOME</a>
		        </li>
		        <li class="nav-item fw-bolder mx-5">
		          <a class="nav-link" href="#">NOTICE</a>
		        </li>
		        <li class="nav-item fw-bolder mx-5">
		          <a class="nav-link" href="#">BOARD</a>
		        </li>
		      </ul>
		</nav>
	</header>
	<section class="d-flex justify-content-center align-items-center">
		<h2>WEB PRACTICE</h2>
	</section>
	<footer class="d-flex justify-content-center align-items-center">
		<p class="fw-bolder m-0">FOOTER</p>
	</footer>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous">
	</script>
</body>
</html>