# 로그아웃 / ID & PW 유효성 검사

<br>

## 헤더 - 로그아웃
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/webapp/views/common/header.jsp#L51-L63

<br>

## 로그아웃 서블릿
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/java/com/web/member/controller/LogOutServlet.java#L11-L22

<br>

## 헤더 - onsubmit
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/webapp/views/common/header.jsp#L27-L40
로그인 버튼의 타입이 submit이라서 버튼을 클릭하면 데이터가 무조건 서버로 전송된다. `<form>`에 <i>**onsubmit 이벤트**</i>를 부여해 유효성 검사를 통과할 때만 전송되도록 설정한다.

<br>

## 헤더 - 유효성 검사
https://github.com/areyouhun/web_practice/blob/e156e140828b12055cc80120c1fcf49c754a03e3/src/main/webapp/views/common/header.jsp#L84-L101
