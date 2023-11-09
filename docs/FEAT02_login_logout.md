# 02. 로그인 & 로그아웃
<p align="center">
  <img width="80%" src="https://github.com/areyouhun/web_practice/assets/97642395/c58db874-c302-42b0-bebd-78e25148e15a">        
</p>

<br>

## View | 헤더 - 로그인
https://github.com/areyouhun/web_practice/blob/89d28f4d580798f52b2e691ea5f9f244a0e70be1/src/main/webapp/views/common/header.jsp#L5-L10

https://github.com/areyouhun/web_practice/blob/89d28f4d580798f52b2e691ea5f9f244a0e70be1/src/main/webapp/views/common/header.jsp#L26-L44

https://github.com/areyouhun/web_practice/blob/89d28f4d580798f52b2e691ea5f9f244a0e70be1/src/main/webapp/views/common/header.jsp#L85-L99
로그인 버튼이 submit 타입이라서 버튼을 누르면 입력 정보가 무조건 서버로 전송된다. `<form>`에 **onsubmit** 이벤트를 부착해 유효성 검사를 통과했을 때만 입력 정보가 전송되도록 한다.

<br>

## Controller | 로그인 서블릿
https://github.com/areyouhun/web_practice/blob/965a9e3308b18d2be3027337b126a02b51b275de/src/main/java/com/web/member/controller/LogInServlet.java#L24-L25

https://github.com/areyouhun/web_practice/blob/965a9e3308b18d2be3027337b126a02b51b275de/src/main/java/com/web/member/controller/LogInServlet.java#L38-L47

<br>

#### ⛓ 회원 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/member/service/MemberService.java#L16-L21

<br>

#### ⛓ 회원 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/member/model/dao/MemberDao.java#L24-L45

```sql
-- sql.getProperty("selectByAccount")

SELECT * FROM MEMBER WHERE USERID = ? AND PASSWORD = ?
```

<br>

## View | 메세지 알림
https://github.com/areyouhun/web_practice/blob/965a9e3308b18d2be3027337b126a02b51b275de/src/main/webapp/views/common/msg.jsp#L1-L21
각종 요청에 대한 처리 결과를 안내하는 공용 페이지이다. 로그인 요청에 실패한 경우 이 페이지에서 실패 메세지를 띄운 뒤 메인 페이지로 이동한다.

<br>

## View | 헤더 - 로그아웃
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/webapp/views/common/header.jsp#L51-L63

<br>

## Controller | 로그아웃 서블릿
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/java/com/web/member/controller/LogOutServlet.java#L11-L22
