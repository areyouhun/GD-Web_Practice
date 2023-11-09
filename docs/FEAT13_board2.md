# 13. 게시판 2 - 댓글

<br>

## Entity | 댓글
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dto/BoardComment.java#L14-L22
- **boardCommentNo**: 댓글 식별용 번호
- **level**: 댓글의 depth (1은 댓글, 2는 답글, 3은 답글의 답글....)
- **boardCommentWriter**: 댓글 작성자 (원칙대로 하자면 Member 타입으로 지정하는 게 맞지만 여기선 편의상 String으로 진행)
- **boardCommentContent**: 댓글 내용
- **boardCommentDate**: 댓글 작성일
- **boardCommentRef**: 참조 댓글 번호 (0이면 댓글, 0이 아니면 답글)
- **boardRef**: 참조 게시글 번호

<br>

## Controller | 게시판 이동 서블릿 - 댓글
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L59

<br>

#### ⛓ 게시판 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L80-L85

<br>

#### ⛓ 게시판 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L136-L156

```sql
-- sql.getProperty("selectBoardCommentByBoardNo")

SELECT * FROM BOARD_COMMENT WHERE BOARD_REF = ?
START WITH BOARD_COMMENT_LEVEL = 1
CONNECT BY PRIOR BOARD_COMMENT_NO = BOARD_COMMENT_REF
```
오라클의 계층형 쿼리를 이용하면 테이블에 저장된 데이터를 계층 구조로 가져올 수 있다. `START WITH`은 시작 조건, `CONNECT BY`에는 연결 조건이 들어간다. 연결 조건은 `PRIOR BOARD_COMMENT_NO = BOARD_COMMENT_REF`로, 만약 방금 전 레코드의 BOARD_COMMENT_NO 컬럼 값에 3이라면 이 3을 BOARD_COMMENT_REF 컬럼의 값으로 갖고 있는 모든 레코드를 찾아서 방금 전 레코드와 연결하라는 의미이다.

<br>

## View | 게시판 - 댓글
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L13-L20

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L128-L142

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L187-L199
- 댓글 등록 폼에 **onsubmit** 이벤트를 부착해 로그인 한 경우에만 댓글을 달 수 있도록 설정
- 댓글 등록 시 히든 태그의 value 속성 값들을 요청 파라미터로 전달
    - 댓글로 등록돼야 하므로 **level** 정보가 담긴 히든 태그는 value 속성 값을 **1**로 지정
    - **boardCommentRef** 정보가 담긴 히든 태그는 value 속성 값을 **0**으로 지정 (참조하는 댓글이 없다는 의미로 서블릿에서 null로 바꾼 뒤 DB에 전달할 예정)

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L152-L171

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L172-L183

<p align="center">
    <img width="50%" src="https://github.com/areyouhun/web_practice/assets/97642395/4ecb8d56-f487-4ca7-a83e-a9156d30da89">
</p>

- 댓글과 답글은 레벨로 구분 (스타일도 레벨별로 다르게 부여)
- 댓글 hover 시 답글, 수정, 삭제 버튼 출력
    - 답글 버튼은 로그인 한 경우에만 출력
    - 수정과 삭제 버튼은 댓글 작성자 또는 admin 계정인 경우에만 출력

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L162

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L201-L215

<p align="center">
    <img width="50%" src="https://github.com/areyouhun/web_practice/assets/97642395/4acaada1-ab8e-4401-a547-1f2a292a80f8">
</p>

답글 버튼을 누르면 답글 등록 폼이 나타난다. 이 등록 폼은 댓글 등록 폼을 clone 해서 만드는데, **답글로 등록돼야 하므로 level과 boardCommentRef 정보가 담긴 히든 태그의 value 속성 값을 변경해준다.**

<br>

## Controller | 댓글 & 답글 등록 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/CommentInsertServlet.java#L24-L30

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/CommentInsertServlet.java#L32-L40

<br>

#### ⛓ 게시판 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L67-L78

<br>

#### ⛓ 게시판 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L115-L134

```sql
-- sql.getProperty("insertBoardComment")

INSERT INTO BOARD_COMMENT VALUES(SEQ_BOARD_COMMENT_NO.NEXTVAL, ?, ?, ?, ?, ?, DEFAULT)
```
댓글 엔티티의 프로퍼티로 SQL문을 만들기 전에 boardCommentRef 필드 값이 0이라면 null로 교체한다. 댓글 테이블에서 BOARD_COMMENT_REF 컬럼 (자식키)은 BOARD_COMMENT_NO 컬럼 (부모키)을 자체 참조하는데, BOARD_COMMENT_NO 컬럼 값 중에 0은 없기 때문이다. (1부터 시작)

<br>

댓글 테이블의 BOARD_COMMENT_REF 컬럼에 댓글 엔티티의 boardCommentRef 필드 값이 들어갈 때 null이 들어갈 수도 있으니 SQL문 세팅 시 `pstmt.setInt()`가 아니라 `pstmt.setString()`을 써줘야 한다. 타입을 문자열로 지정해도 실제로는 숫자를 의미하므로 오라클에서 자동으로 NUMBER 타입으로 변환한다. (NOT NULL 설정을 안 했다면 NULL도 들어감)
