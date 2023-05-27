## 1. 메인페이지
### VIEW의 공통 요소 분리하기
<p align="center">
    <img src="./docs/img/main_page.png" alt="메인페이지" width="50%">
</p>

```ruby
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<footer class="d-flex justify-content-center align-items-center">
  <p class="fw-bolder m-0">FOOTER</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
  integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
  crossorigin="anonymous">
</script>
</body>
</html>
```
<p align="right"><code>footer.jsp</code></p>
<p>앞으로 제작할 웹 페이지들 중 대부분은 동일한 header와 footer를 가진다. 따라서 해당 요소들은 별도의 jsp 파일로 분리하는 편이 유지보수 측면에도 좋다.</p>
<br>

```ruby
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/views/common/head.jsp" %>
<!-- more link tags here -->
<title>main page</title>
<style>
section {
  text-align: center;
  height: 600px;
}
</style>
</head>
<body>
  <%@ include file="/views/common/header.jsp" %>
  <section class="d-flex justify-content-center align-items-center">
    <h2>WEB PRACTICE</h2>
  </section>
<!-- more script tags here -->
<%@ include file="views/common/footer.jsp"%>
```
<p align="right"><code>index.jsp</code></p>
<p>분리한 jsp 파일을 불러올 땐 <code><%@ include file="파일경로" %></code>와 같이 include 지시자 태그를 이용한다. 그럼 불러온 jsp 파일을 현재 작업 중인 jsp 파일의 일부로 쓸 수 있다.</p>