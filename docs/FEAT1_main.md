# 메인페이지
> VIEW의 공통 요소 분리하기

<br>

<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/b8943f1c-837b-475a-a40f-bb73bfbecaf4">
</p>

<br>

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ include file="/views/common/head.jsp" %>
<title>main page</title>
<!-- 생략 -->
</head>
<body>
  <%@ include file="/views/common/header.jsp" %>
  <section class="d-flex justify-content-center align-items-center">
    <h2>WEB PRACTICE</h2>
  </section>
<%@ include file="/views/common/footer.jsp" %>
```
<p align="left"><a href="https://github.com/areyouhun/web_practice/blob/main/src/main/webapp/index.jsp"><code>index.jsp</code></a></p>

- 헤더와 푸터는 어떤 페이지에서든 공통으로 들어가기 때문에 별도의 jsp 파일로 분리 후 <i>**include 지시자 태그**</i>로 가져온다.
    - <code><%@ include file="파일경로" %></code> 형식에 맞춰서 작성
