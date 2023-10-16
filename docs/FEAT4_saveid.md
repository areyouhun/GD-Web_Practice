# 아이디 저장
<p align="center">
    <img width="80%" src="https://github.com/areyouhun/web_practice/assets/97642395/5e1995c6-bf9a-4e39-b128-3b59f70936fc">
</p>

<br>

### 🎨View
**[헤더]**
https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/webapp/views/common/header.jsp#L12-L20

https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/webapp/views/common/header.jsp#L46-L49

<br>

### 🧬Controller & Model
**[로그인 서블릿]**
https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/java/com/web/member/controller/LogInServlet.java#L21-L36
- `doPost()` 메소드에서 `doGet()` 메소드 호출
- saveId 변수에는 <i>**null (아이디 저장 X)**</i> 또는 <i>**'on' (아이디 저장 O)**</i>이 할당됨
	- 체크박스가 체크됐을 때 value 속성에 지정된 값이 없으면 'on' 반환
- 아이디 저장은 로그인 성패에 상관 없이 진행되기 때문에 로그인 처리 코드보다 먼저 진행
