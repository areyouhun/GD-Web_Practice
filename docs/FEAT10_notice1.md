# 10. 공지사항 1 - 조회
<p align="center">
    <img width="100%" src="https://github.com/areyouhun/web_practice/assets/97642395/9cff2022-7f66-401f-8ce9-ef18157e7e19">
</p>

<br>

## View | 헤더
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/common/header.jsp#L70-L72

<br>

## Controller | 공지사항 이동 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeListServlet.java#L22-L50
회원관리와 동일한 방식으로 페이징 처리된 공지글 데이터를 가져온다. (페이지 바 초기화 코드가 빠진듯)

<br>

#### ⛓ 공지사항 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/service/NoticeService.java#L24-L29

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/service/NoticeService.java#L17-L22

<br>

#### ⛓ 공지사항 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/model/dao/NoticeDao.java#L49-L68

```sql
-- sql.getProperty("selectNoticeCount")

SELECT COUNT(*) FROM NOTICE
```

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/model/dao/NoticeDao.java#L26-L47

```sql
-- sql.getProperty("selectNoticeAll")

SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM (SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC) N) WHERE RNUM BETWEEN ? AND ?
```
공지글은 최신 등록일 순으로 정렬돼야 하므로 공지사항 테이블에 `ORDER BY NOTICE_DATE DESC`를 적용한다.

<br>

## View | 공지사항
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeList.jsp#L4-L15

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeList.jsp#L62-L65

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeList.jsp#L75-L97

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeList.jsp#L98-L100
- admin 계정으로 접속한 경우 목록 우측 상단에 글쓰기 버튼 출력
- 공지글에 첨부파일이 포함된 경우 첨부파일 아이콘 출력
- 공지글 제목 클릭 시 해당 글로 이동 (쿼리 파라미터로 게시글 번호 전달)

<br>

## Controller | 공지글 이동 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeViewServlet.java#L23-L27

<br>

#### ⛓ 공지사항 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/service/NoticeService.java#L44-L49

<br>

#### ⛓ 공지사항 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/model/dao/NoticeDao.java#L90-L110

```sql
-- sql.getProperty("selectNoticeByNo")

SELECT * FROM NOTICE WHERE NOTICE_NO = ?
```

<br>

## View | 공지글
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeView.jsp#L5-L12

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeView.jsp#L87-L95
로그인 계정과 게시글 작성자가 일치하거나 admin 계정인 경우 수정 및 삭제 버튼이 나타난다.

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeView.jsp#L73-L82

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeView.jsp#L98-L102
첨부파일 클릭 시 다운로드 서블릿으로 요청이 전달된다.

<br>

## Controller | 첨부파일 다운로드 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/FileDownloadServlet.java#L26-L34

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/FileDownloadServlet.java#L36-L44

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/FileDownloadServlet.java#L46-L61
- 입력스트림으로 파일을 불러올 때 절대 경로를 사용하는 걸 권장 (`getServletContext().getRealPath("/upload/notice/") + fileName`)
- 파일명이 한글인 경우 깨지지 않도록 인코딩 처리
- 응답 메세지의 Content-Type 헤더는 **application/octet-stream**으로 설정
- 출력스트림을 이용해 클라이언트한테 파일 전달
