# 8. 마이페이지 2 - 비밀번호 수정 (암호화)

<br>

## 마이페이지 View
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/memberView.jsp#L88

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/memberView.jsp#L98-L102

#### ⛓ 비밀번호 수정 View 이동 서블릿
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordServlet.java#L10-L20

<br>

## 비밀번호 수정 View
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/updatePassword.jsp#L4-L11

https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/webapp/views/member/updatePassword.jsp#L46-L68

#### ⛓ 비밀번호 수정 서블릿
https://github.com/areyouhun/web_practice/blob/396c5b32a147ffea1f5187ade7ed0876997a1d24/src/main/java/com/web/member/controller/UpdatePasswordEndServlet.java#L14-L49
- 비밀번호 수정 실패 시 비밀번호 수정 View 이동 서블릿을 요청한다.
- 비밀번호 수정 성공 시 부모창 (마이페이지 View)은 로그아웃 서블릿을 요청하도록 하고, 자식창 (비밀번호 수정 View은 닫아준다.

<br>

## 암호화
개인정보보호법에 따라 DB에 저장된 데이터들 중 개인정보를 식별할 수 있는 값 (비밀번호, 주민등록번호, 여권번호 등)들은 암호화해야 한다. 여기서는 비밀번호, 이메일, 전화번호를 암호화할 예정이다.

> **단방향 암호화**: 암호화만 가능하다. 해시 알고리즘을 이용하며, 알고리즘을 모르는 상태에서 암호화된 값만 보고 원본값을 추측할 수 없다. 비밀번호처럼 원본값 복원이 필요 없는 정보를 다룰 때 사용한다. (암호화했을 때 값이 일치하는지만 따지면 되기 때문)
> 
> **양방향 암호화**: 암호화와 복호화 모두 가능하다. 원본값이 필요한 정보를 다룰 때 사용한다. 양방향은 대칭키 암호화와 비대칭키 암호화로 나뉘는데, 대칭키 암호화의 경우 똑같은 key로 암호화와 복호화를 수행한다. 집 열쇠처럼 복제해서 쓰기 때문에 관리를 잘 해야 한다. 비대칭키 암호화의 경우 암호화를 담당하는 공개키와 복호화를 담당하는 개인키 두 종류가 있다. 공개키는 누구나 쓸 수 있지만 개인키는 관리자가 소장한다. 관리자는 개인키만 잘 간수하면 된다.

<br>

## Common - 단방향 암호화 필터

