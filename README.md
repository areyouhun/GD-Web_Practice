# WEB PRACTICE
> JSP & 서블릿 & JDBC의 삼각편대로 초간단 웹 애플리케이션을 만들어보자.

<br>

## 이클립스 세팅
#### 📌 UTF-8 인코딩
- Window > Preferences > General > Workspace 메뉴의 Text file encoding 항목에서 UTF-8 설정
- Window > Preferences > General > Editors > Text Editors > Spelling 메뉴의 Encoding 항목에서 UTF-8 설정
- Window > Preferences > Web > CSS Files, HTML Files, JSP Files 메뉴에서 UTF-8 설정

<br>

#### 📌 프로젝트 생성
- **Dynamic Web Project**로 생성
- **Source folders on build path**: `.java` 파일이 저장되는 곳 (`src\main\java`)
- **Default output folder**: `.class` 파일이 저장되는 곳 (`src\main\webapp\WEB-INF\classes`)
- **Generate web.xml deployment descriptor** 체크

<br>

#### 📌 패키지 구조
<p align="center">
	<img width="60%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/c66b1d33-92b9-4f4b-8fa5-f7370f95fc0d">
</p>

- JSP 파일은 `webapp/views` 경로 하위에서 관리 (`index.jsp`는 웰컴 페이지)
- 서블릿을 비롯한 JAVA 파일은 `src/main/java` 경로 하위에서 관리
- DB 접속 및 SQL 실행에 필요한 properties 파일은 `src/main/resources` 경로 하위에서 관리 (`.gitignore`에 resources 폴더 추가)
- 라이브러리는 `src/main/webapp/WEB-INF/lib` 경로 하위에서 관리

<br>

#### 📌 라이브러리
- **OJDBC**: 오라클 JDBC 드라이버
- **COS**: 사용자가 업로드한 파일을 서버에서 간편하게 저장할 수 있도록 지원
	- [Servlets.com](servlets.com) > COS File Upload Library > Download 항목에서 JAR 파일 다운로드
- **롬복**: 반복적인 메소드를 애너테이션으로 처리
    - [Maven Repository](https://mvnrepository.com/)에서 롬복 다운로드
    - 다운받은 롬복이 있는 폴더에서 shift + 우클릭 후 **PowerShell** 열기
    - `java -jar 롬복파일명.jar`을 입력해 Installer 실행
    - **Can't find IDE**가 뜬다면 Specify location 버튼을 클릭 후 이클립스가 설치된 폴더로 가서 `eclipse.exe` 선택
    - 설치가 끝나면 롬복 파일도 라이브러리에 추가

<br>

#### 📌 Database
이클립스에서 **DBeaver 플러그인** (community 버전)을 설치하면 sqldevloper를 열지 않고도 오라클 DB에 접속할 수 있다. (Auto Commit에서 Manual Commit으로 변경하기)

<br>

<p align="center">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/2b766e79-e2d1-4e23-a7a1-3b059beafd10">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/beca9e5b-8d3b-468b-b930-df9328d8b127">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/099137b0-673d-4100-a208-c60cf5b2c632">
</p>

<br>

## 구현 기능
| LIST |
| ------ |
| [01. 메인페이지](./docs/FEAT01_main.md) |
| [02. 로그인 & 로그아웃](./docs/FEAT02_login_logout.md) |
| [03. 아이디 저장](./docs/FEAT03_saveid.md) |
| [04. 회원가입 1 - 회원 정보 등록](./docs/FEAT04_enroll1.md) |
| [05. 회원가입 2 - 유효성 검사](./docs/FEAT05_enroll2.md) |
| [06. 마이페이지 1 - 회원정보 수정](./docs/FEAT06_mypage1.md) |
| [07. 마이페이지 2 - 비밀번호 수정 & 암호화](./docs/FEAT07_mypage2.md) |
| [08. 회원관리 1 - 페이징](./docs/FEAT08_user-management1.md) |
| [09. 회원관리 2 - 검색 필터](./docs/FEAT09_user-management2.md) |
| [10. 공지사항 1 - 조회](./docs/FEAT10_notice1.md) |
| [11. 공지사항 2 - 등록](./docs/FEAT11_notice2.md) |
| [12. 게시판 1 - 조회](./docs/FEAT12_board1.md) |
| [13. 게시판 2 - 댓글](./docs/FEAT13_board2.md) |
