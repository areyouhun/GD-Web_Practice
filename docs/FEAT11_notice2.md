# 11. 공지사항 2 - 등록

<br>

## View | 공지사항
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeList.jsp#L62-L65

<br>

## Controller | 공지글 등록 폼 이동 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertServlet.java#L19

<br>

## View | 공지글 등록 폼
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeInsert.jsp#L51-L52

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/webapp/views/notice/noticeInsert.jsp#L67-L72
등록 항목 중에 첨부 파일이 있기 때문에 `<form>`의 enctype 속성을 **multipart/form-data**로 지정한다.

<br>

## Common | 어드민 필터
https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/common/filter/AdminFilter.java#L18

https://github.com/areyouhun/web_practice/blob/570b0fc0a75044e3c77b1afd5ab7210d4003c4aa/src/main/java/com/web/common/filter/AdminFilter.java#L26-L45
관리자만 공지글 등록 폼에 접근할 수 있도록 인증 필터를 추가한다.

<br>

## Controller | 공지글 등록 서블릿
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L28-L33
우선 폼 데이터 요청 형식이 **multipart/form-data**인지 확인한다. if문을 통과하면 업로드 파일을 처리해야 하는데, COS 라이브러리가 제공하는 **MultipartRequest** 클래스를 이용하면 요청 객체에 담겨 있는 업로드 파일을 서버 내의 지정된 위치에 저장할 수 있다. 이 클래스로 인스턴스를 생성하기 위해선 5개의 파라미터가 필요하다.
- HttpServletRequest 객체
- 파일 저장 위치 (path)
- 파일 최대 용량 (maxSize)
- 인코딩 방식 (encode)
- rename 규칙 (dfrp)

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L41

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L44

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L47

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L50
DefaultFileRenamePolicy 클래스 역시 COS 라이브러리가 제공한다. (재정의 가능)

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L53
앞서 제작한 것들을 MultipartRequest의 생성자 파라미터로 전달해 인스턴스를 생성한다. 그럼 **생성과 동시에 지정 위치에 업로드 파일이 저장된다.**

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L62-L69
파일 저장이 끝나면 요청 객체에 담긴 나머지 데이터들을 공지글 엔티티에 바인딩 후 DB에 등록한다. 참고로 MultipartRequest 객체에 요청 객체가 들어간 이상 요청 객체에선 더 이상 데이터를 가져오지 못 한다. MultipartRequest 객체를 통해서 가져와야 한다. 

<br>

https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/controller/NoticeInsertEndServlet.java#L71-L81
공지글 작성에 성공하면 공지사항 화면으로, 실패하면 다시 공지글 등록 폼으로 이동한다.

<br>

#### ⛓ 공지사항 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/service/NoticeService.java#L31-L42

<br>

#### ⛓ 공지사항 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/notice/model/dao/NoticeDao.java#L70-L88

```sql
-- sql.getProperty("insertNotice")

INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL, ?, ?, ?, DEFAULT, ?, DEFAULT)
```
