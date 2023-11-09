# 05. 회원가입 2 - 유효성 검사

<br>

## View | 회원가입 폼
https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L25-L47

https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L92-L95

https://github.com/areyouhun/web_practice/blob/7d6770d6d471afd66406461add9f3843580b1e6e/src/main/webapp/views/member/enrollMember.jsp#L98-L123

https://github.com/areyouhun/web_practice/blob/51bd9720584756a207d06e1a12722437f7cc54f7/src/main/webapp/views/member/enrollMember.jsp#L125-L135
submit 타입의 `<input>`에 onclick 이벤트를 적용하면 onsubmit 이벤트와 같은 효과를 줄 수 있다. 총 4가지의 유효성 검사를 통과해야 입력 정보가 서버로 전송되도록 했다. **아이디 중복 검사**의 경우 `<input>`에 직접 정의한 duplicate 속성 값이 "false"일 때만 검사를 통과한 걸로 처리한다.

<br>

## Controller | 아이디 중복 검사 서블릿
https://github.com/areyouhun/web_practice/blob/7d6770d6d471afd66406461add9f3843580b1e6e/src/main/java/com/web/member/controller/IdDuplicateServlet.java#L14-L28

<br>

## View | 아이디 중복 검사 결과
https://github.com/areyouhun/web_practice/blob/7d6770d6d471afd66406461add9f3843580b1e6e/src/main/webapp/views/member/idDuplicate.jsp#L4-L6

https://github.com/areyouhun/web_practice/blob/7d6770d6d471afd66406461add9f3843580b1e6e/src/main/webapp/views/member/idDuplicate.jsp#L29-L49
