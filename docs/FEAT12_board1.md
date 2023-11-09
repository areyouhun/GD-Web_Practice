# 12. 게시판 1 - 조회
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/53480e37-a09d-4670-ac44-ce1d9f7afab4">
</p>

<br>

## View | 헤더
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/common/header.jsp#L73-L75

<br>

## Controller | 게시판 이동 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardListServlet.java#L25-L50
공지사항과 동일한 방식으로 페이징 처리된 게시글 데이터를 가져온다.

<br>

#### ⛓ 게시판 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L25-L30

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L18-L23

<br>

#### ⛓ 게시판 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L50-L69

```sql
-- sql.getProperty("selectBoardCount")

SELECT COUNT(*) FROM BOARD
```

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L27-L48

```sql
-- sql.getProperty("selectBoard")

SELECT * FROM (SELECT ROWNUM AS RNUM, B.* FROM (SELECT * FROM BOARD ORDER BY BOARD_DATE DESC) B) WHERE RNUM BETWEEN ? AND ?
```
게시글은 최신 등록일 순으로 정렬돼야 하므로 게시판 테이블에 `ORDER BY BOARD_DATE DESC`를 적용한다.

<br>

## View | 게시판
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardList.jsp#L5-L16

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardList.jsp#L82-L103

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardList.jsp#L105-L107

<br>

## Controller | 게시글 이동 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L26-L32

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L34-L45

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L47-L52

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L54
- 게시글 이동 시 유저가 해당 게시글을 이전에 읽었다면 조회수가 유지되지만 그렇지 않다면 조회수를 1 증가시킨다.
- 게시글 읽음 여부 (**isRead 변수**)는 쿠키에 저장한 게시글 번호로 결정한다.
    - 쿠키에는 |1||2||5||11|과 같은 형식으로 게시글 번호들이 저장된다.
    - 쿠키에 해당 게시글 번호 (**currentBoardNo 변수**)가 있다면 이전에 읽은 적이 있는 걸로 판단하고 isRead 변수를 true로 변경한다.
    - 게시글을 읽은 적이 없다면 쿠키에 해당 게시글의 번호를 추가한다. isRead 변수는 false를 유지한다.
- currentBoardNo와 isRead 변수를 BoardService의 `selectBoardByNo()` 메소드 파라미터로 전달한다.

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/controller/BoardViewServlet.java#L56-L66
사용자가 게시글에 접속하는 순간 게시글이 삭제될 수도 있기 때문에 이에 대한 분기 처리를 해준다. (댓글 가져오는 로직은 다음 챕터에서 설명)

<br>

#### ⛓ 게시판 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L32-L43

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/service/BoardService.java#L45-L52
게시글이 존재하고 유저가 해당 게시글을 이전에 읽은 적이 없다면 **조회수를 1 증가시킨뒤** 게시글을 가져온다.

<br>

#### ⛓ 게시판 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L71-L91

```sql
-- sql.getProperty("selectBoardByNo")

SELECT * FROM BOARD WHERE BOARD_NO = ?
```

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/board/model/dao/BoardDao.java#L93-L108

```sql
-- sql.getProperty("updateBoardReadCount")

UPDATE BOARD SET BOARD_READCOUNT = BOARD_READCOUNT + 1 WHERE BOARD_NO = ?
```

<br>

## View | 게시글
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L5-L11

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L92-L115

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/board/boardView.jsp#L116-L126
