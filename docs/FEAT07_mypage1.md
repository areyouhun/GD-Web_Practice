# 7. 마이페이지 1 - 회원정보 수정
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/54f52b1d-bb2b-48b1-875e-4659e46a3e6a">
</p>

<br>

## 헤더
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/common/header.jsp#L51-L57

<br>

## 마이페이지 이동 서블릿
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/java/com/web/member/controller/MemberViewServlet.java#L15-L27

https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/java/com/web/member/controller/MemberViewServlet.java#L41-L43
이미 세션에 로그인 정보가 담겨 있지만 애플리케이션이 돌아가는 구조를 익히기 위해 일부러 DB에 접속해서 가져오도록 했다.

<br>

## 마이페이지 View - 조회
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L5-L26

https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L78-L86

https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L70-L77
- 취미 (체크박스)의 경우 각 취미의 체크 여부를 기록한 hobbiesChecked 객체 이용
- 성별 (라디오버튼)의 경우 삼항 연산자 이용
  
<br>

## 마이페이지 View - 회원정보 수정
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L42

https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L89

https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/webapp/views/member/memberView.jsp#L94-L96

#### ⛓ 회원정보 수정 서블릿
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/java/com/web/member/controller/UpdateMemberEndServlet.java#L14-L48
- 회원정보 수정 성공 시 세션에 바인딩된 로그인 정보를 업데이트한 후 메인 페이지로 이동한다.
- 회원정보 수정 실패 시 <i>**마이페이지 이동 서블릿을 거친 뒤 마이페이지 View로 이동한다.**</i> 마이페이지 View를 렌더링하는 과정에서 request 객체에 바인딩된 데이터가 있어야 하기 때문에 반드시 서블릿을 먼저 거쳐야 한다.

<br>

## Common - 인증 필터
https://github.com/areyouhun/web_practice/blob/79ce414d4b5a0520858c046840c58910332ee24e/src/main/java/com/web/common/filter/AuthenticationFilter.java#L16-L40
로그인한 회원만 마이페이지에 접근할 수 있도록 인증 필터를 추가한다.
