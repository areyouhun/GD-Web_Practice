# WEB PRACTICE
> JSP & 서블릿 & JDBC의 삼각편대로 초간단 웹 애플리케이션을 만들어보자.

<br>

## 이클립스 & 프로젝트 세팅
### ✅인코딩
- Window > Preferences > General > Workspace 메뉴의 Text file encoding 항목에서 UTF-8 설정
- Window > Preferences > General > Editors > Text Editors > Spelling 메뉴의 Encoding 항목에서 UTF-8 설정
- Window > Preferences > Web > CSS Files, HTML Files, JSP Files 메뉴에서 UTF-8 설정

<br>

### ✅프로젝트 생성
- **Dynamic Web Project**로 생성
- **Source folders on build path**: `.java` 파일이 저장되는 곳 (`src\main\java`)
- **Default output folder**: `.class` 파일이 저장되는 곳 (`src\main\webapp\WEB-INF\classes`)
- **Generate web.xml deployment descriptor** 체크

<br>

### ✅프로젝트 구성
<p align="center">
	<img width="60%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/c66b1d33-92b9-4f4b-8fa5-f7370f95fc0d">
</p>

- jsp 파일은 `webapp/views`에서 관리 (`index.jsp`는 웰컴 페이지)
- 서블릿을 비롯한 java 파일은 `src/main/java` 하위 패키지에서 관리
- DB 접속 및 SQL 실행에 필요한 properties 파일은 `src/main/resources`에서 관리 (`.gitignore`에 resources 폴더 추가)

웹 서비스에서 제공하는 모든 기능은 <i>**서블릿 > jsp**</i> 순으로 수행돼야 한다. 서블릿을 거치지 않고 바로 jsp로 접근하면 안 된다. jsp 파일은 나중에 WEB-INF 폴더에 보관할 예정인데 이 폴더는 외부에서 접근할 수 없다.

<br>

### ✅DB - MEMBER 테이블
```sql
CREATE TABLE MEMBER (
  USERID VARCHAR2(15) PRIMARY KEY, 
  PASSWORD VARCHAR2(15) NOT NULL, 
  USERNAME  VARCHAR2(20) NOT NULL, 
  GENDER CHAR(1) CHECK (GENDER IN ('M','F')), 
  AGE NUMBER,
  EMAIL VARCHAR2(30), 
  PHONE CHAR(11)  NOT NULL, 
  ADDRESS VARCHAR2(100), 
  HOBBY VARCHAR2(50),
  ENROLLDATE DATE DEFAULT SYSDATE
);
```

- 이클립스에서 DBeaver 플러그인 (community 버전)을 설치하면 sqldevloper를 열지 않고도 오라클 DB 접속 가능
- Auto Commit에서 Manual Commit으로 변경하기

<p align="center">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/2b766e79-e2d1-4e23-a7a1-3b059beafd10">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/beca9e5b-8d3b-468b-b930-df9328d8b127">
	<img width="100%" src="https://github.com/areyouhun/web_programming_practice/assets/97642395/099137b0-673d-4100-a208-c60cf5b2c632">
</p>

<br>

### ✅라이브러리
- `ojdbc.jar`는 `src/main/webapp/WEB-INF/lib`에 추가
- lombok도 추가할 경우
    - [Maven Repository](https://mvnrepository.com/)에서 lombok 다운로드
    - 다운 받은 lombok이 있는 폴더에서 shift + 우클릭 후 <i>**PowerShell**</i> 열기
    - `java -jar lombok-1.18.24.jar`처럼 lombok 파일명을 기입해 Installer 실행
    - Can't find IDE가 뜬다면 Specify location 버튼을 클릭 후 이클립스가 설치된 폴더로 가서 `eclipse.exe` 선택
    - 설치가 끝나면 lombok 파일도 `ojdbc.jar`와 같은 경로에 추가

<br>

## 구현 기능
| LIST |
| ------ |
| [1. 메인페이지](./docs/FEAT1_main.md) |
| [2. 로그인](./docs/FEAT2_login.md) |
| [3. 로그아웃 / ID & PW 유효성 검사](./docs/FEAT3_logout.md) |
| [4. 아이디 저장](./docs/FEAT4_saveid.md) |
| [5. 회원가입 1 - 회원 정보 등록](./docs/FEAT5_enroll1.md) |
| [6. 회원가입 2 - 유효성 검사](./docs/FEAT6_enroll2.md) |
| [7. 마이페이지 1](./docs/FEAT7_mypage1.md) |
