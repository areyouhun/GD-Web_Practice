# 03. 아이디 저장
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/cae7060c-d48a-4a42-9640-5b12451a1a46">
</p>

<br>

## View | 헤더
https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/webapp/views/common/header.jsp#L12-L20

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/common/header.jsp#L30-L32

https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/webapp/views/common/header.jsp#L46-L49

<br>

## Controller | 로그인 서블릿
https://github.com/areyouhun/web_practice/blob/9ded478c75a7b2130a61b17676c29f1bd115f439/src/main/java/com/web/member/controller/LogInServlet.java#L24-L36
- saveId 변수에는 **null** (아이디 저장 X) 또는 "**on**" (아이디 저장 O)이 대입된다. (체크박스가 체크됐을 때 value 속성에 지정된 값이 없으면 "**on**" 반환)
- saveId 변수가 null이 아닌 경우 쿠키를 생성해 userId 변수의 값을 바인딩한다. 그렇지 않다면 쿠키를 제거한다.
- 아이디 저장은 로그인 성패에 상관없이 진행돼야 하므로 로그인 처리 코드 이전에 수행한다.
