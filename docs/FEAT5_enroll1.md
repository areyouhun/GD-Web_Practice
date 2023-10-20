# 회원가입 1 - 회원 정보 등록
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/3f20eb3c-c5d8-4451-a7db-579ecdefb222">
</p>

<br>

## 헤더
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/common/header.jsp#L41-L43

<br>

## 회원가입 폼 이동 서블릿
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/member/controller/EnrollMemberServlet.java#L10-L20

<br>

## 회원가입 폼
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L22-L25

https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L74-L90

https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L92-L95
- 서블릿을 거친 뒤 JSP를 실행하도록 한다. (회원가입 폼 이동 서블릿 > 회원가입 폼)
-  `<form>`의 method 속성을 POST로 지정했기 때문에 입력 정보들은 HTTP 메세지 body에 담겨 서버로 전달된다.
- 라디오 버튼이나 체크박스의 경우 value 속성을 미리 지정한다. 안 그러면 서블릿에서 `request.getParameter()` 메소드로 입력 정보를 받을 때 <i>**'on'**</i>이 반환된다.

<br>

## 회원 등록 서블릿
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/member/controller/EnrollMemberEndServlet.java#L15-L34

https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/member/controller/EnrollMemberEndServlet.java#L48-L66

#### ⛓ 회원 서비스
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/member/service/MemberService.java#L23-L34

#### ⛓ 회원 DAO
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/member/model/dao/MemberDao.java#L47-L70

<br>

## 인코딩 필터
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/java/com/web/common/filter/EncodingFilter.java#L13-L31
문자셋을 통일시키지 않으면 한글 데이터가 깨져서 전달될 수 있다. 동일한 문자셋의 데이터를 주고 받기 위해 인코딩 필터를 추가한다. `@WebFilter`의 맵핑 URL을 `/*`로 설정해 모든 서블릿에 적용해준다.
