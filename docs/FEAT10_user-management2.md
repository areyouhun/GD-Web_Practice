# 10. 회원관리 2 - 검색 필터
<p align="center">
    <img width="80%" src="https://github.com/areyouhun/web_practice/assets/97642395/6aca1862-b6d9-4dce-9d8b-8f3496ec309b">
</p>

<br>

## View | 회원관리 - 검색 필터
https://github.com/areyouhun/web_practice/blob/f16039e7004ed20f7388b975a18457c65353c86f/src/main/webapp/views/admin/memberManagement.jsp#L17-L18

https://github.com/areyouhun/web_practice/blob/f16039e7004ed20f7388b975a18457c65353c86f/src/main/webapp/views/admin/memberManagement.jsp#L87-L92

https://github.com/areyouhun/web_practice/blob/f16039e7004ed20f7388b975a18457c65353c86f/src/main/webapp/views/admin/memberManagement.jsp#L93-L127

https://github.com/areyouhun/web_practice/blob/f16039e7004ed20f7388b975a18457c65353c86f/src/main/webapp/views/admin/memberManagement.jsp#L184-L191
검색 타입을 고를 수 있는 셀렉트 박스에 change 이벤트를 걸고, jQuery의 ready 함수로 해당 이벤트를 강제 발동시킨다. 그럼 이에 대한 콜백함수가 호출되면서 검색 타입과 연결된 검색창이 나타난다. 각 검색창은 `<form>`으로 만들어졌고, 사용자가 입력한 값을 기반으로 <i>**검색 타입, 검색 키워드, 페이지 당 회원 수**</i>가 서버로 전달된다.

<br>

## Controller | 특정 회원 조회 서블릿
https://github.com/areyouhun/web_practice/blob/604350b1898c4dfc413bc8dac156536940ddb58f/src/main/java/com/web/admin/controller/SearchMemberServlet.java#L25-L33

https://github.com/areyouhun/web_practice/blob/604350b1898c4dfc413bc8dac156536940ddb58f/src/main/java/com/web/admin/controller/SearchMemberServlet.java#L35-L48

https://github.com/areyouhun/web_practice/blob/604350b1898c4dfc413bc8dac156536940ddb58f/src/main/java/com/web/admin/controller/SearchMemberServlet.java#L50-L51

https://github.com/areyouhun/web_practice/blob/604350b1898c4dfc413bc8dac156536940ddb58f/src/main/java/com/web/admin/controller/SearchMemberServlet.java#L53-L56
앞서 전달된 요청 파라미터와 현재 페이지 값을 이용해 페이지 바를 생성하고 이곳에 담을 특정 회원들을 조회한다. 참고로 uri 변수를 보면 쿼리 파라미터가 "?searchType="로 시작한다. 이것 때문에 페이지 바를 생성하는 클래스에 [쿼리 파라미터 변경 메소드](https://github.com/areyouhun/web_practice/blob/main/docs/FEAT09_user-management1.md#common---%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%B0%94-%EC%83%9D%EC%84%B1%EA%B8%B0)를 추가한 것이다. 응답 결과는 다시 회원관리 뷰로 전달한다.

<br>

#### ⛓ 어드민 서비스
https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/java/com/web/admin/service/AdminService.java#L36-L41

https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/java/com/web/admin/service/AdminService.java#L27-L34

<br>

#### ⛓ 어드민 DAO
https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/java/com/web/admin/model/dao/AdminDao.java#L96-L117

```sql
-- sql.getProperty("selectMemberByKeywordCount")

SELECT COUNT(*) FROM MEMBER WHERE #COL LIKE ?
```

<br>

https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/java/com/web/admin/model/dao/AdminDao.java#L70-L94

```sql
-- sql.getProperty("selectMemberByKeyword")

SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM (SELECT * FROM MEMBER WHERE #COL LIKE ?) M) WHERE RNUM BETWEEN ? AND ?
```
사용자가 선택한 검색 타입은 회원 테이블의 컬럼명으로 사용된다. 이를 SQL문에 적용하는 과정에서 리터럴로 인식되지 않도록 사전 작업 (`#COL`)을 해놓는다. 아울러 회원 아이디나 이름으로 회원을 찾을 땐 부분 조회도 가능하도록 첫 번째 플레이스홀더에 들어갈 값의 양옆에 `%` 키워드를 추가해준다.

<br>

## View | 회원관리 - 페이지 필터
https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/webapp/views/admin/memberManagement.jsp#L20-L23

https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/webapp/views/admin/memberManagement.jsp#L129-L140

https://github.com/areyouhun/web_practice/blob/f6c7639f7b521d2be083b3456285765972d44795/src/main/webapp/views/admin/memberManagement.jsp#L189-L203
페이지 당 회원 수를 고르는 셀렉트 박스에도 change 이벤트를 부착한다. 해당 이벤트 발동 시 현재 URL (`location.href`)에 검색 필터 관련 쿼리 파라미터가 없으면 `/admin/memberList.do`을 요청하고, 그렇지 않으면 `/admin/searchMember`를 요청한다. 이 영역도 `<form>`으로 만들어졌으며, 사용자가 선택한 값을 기반으로 <i>**검색 타입, 검색 키워드, 페이지 당 회원 수**</i>가 서버로 전달된다.
