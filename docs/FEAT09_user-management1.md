# 9. 회원관리 1 - 페이징
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/b85045aa-e097-457c-b136-175c68e024c9">
</p>

<br>

## View | 헤더
https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/webapp/views/common/header.jsp#L76-L80

<br>

## Controller | 회원관리 이동 서블릿
https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/admin/controller/MemberListServlet.java#L16-L24

https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/admin/controller/MemberListServlet.java#L46

https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/admin/controller/MemberListServlet.java#L51-L53

<br>

## View | 회원관리
https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/webapp/views/admin/memberManagement.jsp#L5-L15

https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/webapp/views/admin/memberManagement.jsp#L141-L176

<br>

## Common | 어드민 필터
https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/common/filter/AdminFilter.java#L18

https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/common/filter/AdminFilter.java#L26-L45
관리자만 회원관리에 접근할 수 있도록 인증 필터를 추가한다.

<br>

## 페이징
- 페이지 바는 5개의 페이지 번호로 구성
- 한 페이지 당 5개의 회원 데이터 출력
    - 1 페이지 > 1번 ~ 5번 회원 데이터
    - 3 페이지 > 11번 ~ 15번 회원 데이터
- 미리 데이터 넘버링을 해야 하므로 SQL의 ROWNUM 기능 이용

<br>

## Common - 페이지 바 생성기
https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L7-L8
- **DEFAULT_CURRENT_PAGE**: 현재 페이지 기본값 (요청 객체에 바인딩된 정보 중 현재 페이지 정보가 없을 때 사용)
- **DEFAULT_NUM_PER_PAGE**: 페이지 당 데이터 수 기본값

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L11-L20
페이지 바에 들어갈 HTML 구문 (페이지 번호 버튼)

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L28-L36
- **uri**: 페이지 버튼 클릭 시 연결될 URI
- **curretPage**: 현재 페이지 번호
- **numPerPage**: 페이지 당 데이터 수 (DEFAULT_NUM_PER_PAGE 대입)
- **totalData**: 회원 테이블에 저장된 전체 레코드 수 (SQL COUNT 이용)
- **totalPages**: 전체 레코드 수를 기준으로 생성될 페이지 개수로, `(int) Math.ceil((double) totalData / numPerPage)`와 같이 나타낸다. `(totalData / numPerPage) + 1`처럼 1을 더해서 올림 처리를 하면 안 된다. 정수로 딱 나눠 떨어지는 경우도 있기 때문이다.
- **pageBarSize**: 페이지 바에 출력될 페이지 번호의 개수 (사용자 지정)
- **pageStartNo**: 페이지 바의 시작 페이지 번호로, `((currentPage - 1) / pageBarSize) * pageBarSize + 1`와 같이 나타낸다.
    - 현재 페이지 4 > ((4 - 1) / 5) * 5 + 1 = 시작 페이지 1
    - 현재 페이지 7 > ((7 - 1) / 5) * 5 + 1 = 시작 페이지 6
- **pageEndNo**: 페이지 바의 끝 페이지 번호로, `pageStartNo + pageBarSize - 1`와 같이 나타낸다.
- **pageBar**: 페이지 바에 들어갈 HTML 구문 (페이지 번호 버튼)

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L80-L85

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L38-L48

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L117-L124

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L126-L133

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L135-L143

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L146-L162

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L164-L171

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L173-L180
페이지 바 생성 과정이다. 빌더 패턴을 적용했으며 외부에서 uri, currentPage, totalData 등을 파라미터로 받아 생성된다. 조건에 따라 페이지 번호 버튼을 활성화 (`<a>`) 또는 비활성화 (`<span>`) 시키도록 코드를 작성했다.

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L182-L188
요청 URI의 쿼리 파라미터를 변경하는 메소드이다. 페이지 요청 시 검색 필터가 없으면 쿼리 파라미터가 currentPage부터 시작하지만 (`?currentPage=값`), 검색 필터가 있으면 쿼리 파라미터가 필터 정보 (`?검색필터=값&curretPage=값`)부터 시작하도록 코드를 작성했다. 그래서 추가한 메소드이다.

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L22-L26

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/common/PageBarGenerator.java#L50-L68
pageBar의 요소를 가져올 때 사용할 인덱스용 상수 및 메소드이다.

<br>

## Controller | 회원관리 이동 서블릿 - 페이징 적용
https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/controller/MemberListServlet.java#L25-L46

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/controller/MemberListServlet.java#L49-L50

#### ⛓ 어드민 서비스
https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/service/AdminService.java#L20-L25

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/service/AdminService.java#L13-L18

#### ⛓ 어드민 DAO
https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/model/dao/AdminDao.java#L49-L68

```sql
-- sql.getProperty("selectMemberCount")

SELECT COUNT(*) FROM MEMBER
```

<br>

https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/java/com/web/admin/model/dao/AdminDao.java#L26-L47

```sql
-- sql.getProperty("selectMemberAll")

SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM (SELECT * FROM MEMBER) M) WHERE RNUM BETWEEN ? AND ?
```
- 시작 플레이스홀더: 현재 페이지의 시작 데이터 번호로 `(currentPage * numPerPage) - numPerPage + 1`가 들어간다.
- 마지막 플레이스홀더: 현재 페이지의 마지막 데이터 번호로 `currentPage * numPerPage`가 들어간다.
- 만약 현재 페이지가 3이고 한 페이지에 5개의 회원 데이터를 출력한다면, 11번 ~ 15번의 레코드가 반환된다.

서브 쿼리를 줄이기 위해서 `SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM MEMBER M) WHERE RNUM BETWEEN ? AND ?`와 같이 작성하면 검색 필터를 적용할 때 순서가 꼬일 수 있다.
<i>**검색 필터를 적용할 테이블 > ROWNUM이 부여된 테이블 > ROWNUM (RNUM)의 범위가 설정된 테이블**</i> 순으로 감싼다.

<br>

## View | 회원관리 - 페이징 적용
https://github.com/areyouhun/web_practice/blob/479d0a50b65174473a7568daea6a483dfc755872/src/main/webapp/views/admin/memberManagement.jsp#L177-L181
