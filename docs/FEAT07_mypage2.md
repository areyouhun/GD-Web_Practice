# 07. 마이페이지 2 - 비밀번호 수정 & 암호화

<br>

## View | 마이페이지
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/memberView.jsp#L88

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/memberView.jsp#L98-L102

<br>

## Controller | 비밀번호 수정 폼 이동 서블릿
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordServlet.java#L10-L20

<br>

## View | 비밀번호 수정 폼
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/updatePassword.jsp#L4-L11

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/updatePassword.jsp#L46-L68

<br>

## Controller | 비밀번호 수정 처리 서블릿
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordEndServlet.java#L23-L29

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordEndServlet.java#L31-L44

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordEndServlet.java#L46-L49
- 비밀번호 수정 실패 시 비밀번호 수정 폼 이동 서블릿을 요청한다.
- 비밀번호 수정 성공 시 **부모창** (**마이페이지**)은 로그아웃 서블릿을 요청하도록 하고, **자식창** (**비밀번호 수정 폼**)은 닫아준다.

<br>

#### ⛓ 회원 서비스
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/member/service/MemberService.java#L56-L67

<br>

#### ⛓ 회원 DAO
https://github.com/areyouhun/web_practice/blob/cc493ae93ec57525c7965fe1e92117ef27ff864b/src/main/java/com/web/member/model/dao/MemberDao.java#L117-L132

```sql
-- sql.getProperty("updatePassword")

UPDATE MEMBER SET PASSWORD = ? WHERE USERID = ?
```

<br>

## 암호화
개인정보보호법에 따라 DB에 저장된 데이터 중 개인정보를 식별할 수 있는 값 (비밀번호, 주민등록번호, 여권번호 등)들은 암호화해야 한다. 여기서는 비밀번호, 이메일, 전화번호를 암호화할 예정이다.

> **단방향 암호화**: 해시 알고리즘을 이용하며 암호화만 가능하다. 비밀번호처럼 원본값 복원이 필요 없는 정보를 다룰 때 사용한다. (암호화한 값이 일치하는지만 확인하면 되기 때문)
> 
> **양방향 암호화**: 암호화와 복호화 모두 가능하다. 원본값이 필요한 정보를 다룰 때 사용한다. 양방향 암호화는 대칭키와 비대칭키로 나뉘는데, **대칭키의 경우 똑같은 키로 암호화와 복호화를 수행한다.** 집 열쇠처럼 복제해서 쓰기 때문에 관리를 잘 해야 한다. **비대칭키의 경우 암호화를 담당하는 공개키와 복호화를 담당하는 개인키 두 종류가 있다.** 공개키는 누구나 쓸 수 있지만 개인키는 관리자가 소장한다. 관리자는 개인키만 잘 간수하면 된다.

<br>

## Common | 단방향 암호화 필터
https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/filter/PasswordEncryptionFilter.java#L15-L35
사용자의 비밀번호가 쓰이는 곳에 단방향 암호화 필터를 적용한다.

<br>

#### ⛓ 단방향 암호화 래퍼
https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/filter/PasswordEncryptionWrapper.java#L10-L23
서블릿에서 `request.getParameter()` 메소드가 호출됐을 때, 파라미터 이름이 "password"이거나 "password-new"일 때만 단방향 암호화를 적용한다.

<br>

https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/filter/PasswordEncryptionWrapper.java#L25-L37
단방향 암호화 과정은 다음과 같다.
- MessageDigest 클래스의 `getInstance()` 메소드를 이용해 SHA-512 알고리즘 객체를 가져온다.
- 원본값 (비밀번호)을 byte 배열로 변환한 뒤 SHA-512 알고리즘 객체로 암호화한다.
- 암호화된 값을 byte 배열로 변환 후 마지막엔 문자열로 변환해 반환한다.

<br>

```sql
ALTER TABLE MEMBER MODIFY PASSWORD VARCHAR2(100);
```
비밀번호에 단방향 암호화가 적용되면 길이가 상당히 길어진다. MEMBER 테이블에서 PASSWORD 컬럼의 길이를 늘려준다.

<br>

## Common | 양방향 대칭키 리스너
이메일과 전화번호엔 양방향 대칭키를 적용한다. DB에 저장할 땐 암호화하고, 화면에 출력할 땐 복호화한다. 양방향 대칭키이므로 키는 하나만 있으면 되며 서버에서 관리한다. 키가 바뀌기라도 하면 이전 키로 암호화한 정보는 복호화할 수 없으니 주의하자!

<br>

https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/AESEncryptor.java#L18-L29
양방향 대칭키의 경우 영속성 유지를 위해 파일로 관리한다. 또한 애플리케이션을 구동할 때마다 해당 파일이 불러와야 하므로 AESEncryptor 클래스는 서블릿 리스너로 등록한다.
- key 멤버 변수: 양방향 대칭키 역할을 하는 SecretKey 타입의 객체 (static으로 지정)
- path 멤버 변수: 양방향 대칭키 객체가 저장된 파일 경로
  - `AESEncryptor.class.getResource("/").getPath()`: classes 경로 (이클립스 Server 옵션에서 **Serve modules without publishing** 체크)
  - `this.path.substring(0, this.path.indexOf("classes"))`: WEB-INF 폴더 경로

<br>

https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/AESEncryptor.java#L31-L42

https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/AESEncryptor.java#L44-L59
양방향 대칭키 파일이 존재하면 해당 파일을 불러와 key 멤버 변수에 할당한다. 존재하지 않으면 SecretKey 객체를 새로 만들어 key 멤버 변수에 할당한다.

<br>

https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/common/AESEncryptor.java#L61-L77
암복호화 메소드이다. 마이페이지 조회, 회원가입, 회원정보 수정 등 쓰일 곳이 많으니 static으로 지정한다.

<br>

## Controller | 회원가입 서블릿 - 양방향 대칭키로 암호화
https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/member/controller/EnrollMemberEndServlet.java#L36-L46

<br>

## Controller | 마이페이지 이동 서블릿 - 양방향 대칭키로 복호화
https://github.com/areyouhun/web_practice/blob/21e75986c70b37c86428d257282230821a41aad0/src/main/java/com/web/member/controller/MemberViewServlet.java#L27-L39
