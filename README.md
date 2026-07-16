# Plug-In

> 전기차 충전소에 후기를 남기는 커뮤니티  
> 위치·상태 정보만 제공하던 기존 지도 서비스에 실제 이용자의 만족도 후기와 충전소 사용 혼잡도 시각화를 더했습니다

<img width="1915" height="945" alt="image" src="https://github.com/user-attachments/assets/08fad005-72f6-45f3-aa73-4b18ba644603" />


 ---
 ## 기술스택

 ![React](https://img.shields.io/badge/React-19.2.7-61DAFB?style=for-the-badge&logo=react&logoColor=black)
 ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.16-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
 ![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
 ![Oracle](https://img.shields.io/badge/Oracle_21_XE-F80000?style=for-the-badge&logo=oracle&logoColor=white)
 ![Node.js](https://img.shields.io/badge/Node.js-22.22.3-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)
 ![MyBatis](https://img.shields.io/badge/MyBatis-3.5.15-DA1F31?style=for-the-badge)
 ![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
 ![GitHub Projects](https://img.shields.io/badge/GitHub_Projects-181717?style=for-the-badge&logo=github&logoColor=white)
 ![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
 ![ERDCloud](https://img.shields.io/badge/ERDCloud-4A90D9?style=for-the-badge)
 ![draw.io](https://img.shields.io/badge/draw.io-F08705?style=for-the-badge&logo=diagramsdotnet&logoColor=white)
 ![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)

---
 
## 목차
- [프로젝트 소개]
- [팀 구성 및 역할]
- [주요 기능]
- [아키텍처]
- [트러블슈팅]
- [실행 방법]
- [회고]
---
 
## 프로젝트 소개
 
### 배경
기존 공공 충전소 지도 서비스(예: EV맵)는 위치·상태 등 정형 정보 제공에 그쳐, 실제 대기시간·고장 빈도·이용 만족도 같은 비정형 경험 정보는 확인할 수 없었습니다. 전기차 보급 확대로 충전소 이용 수요가 지속 증가하는 상황에서, 기존 지도 서비스 위에 '후기 공유' 기능을 결합해 정보 비대칭을 해소하고자 본 프로젝트를 기획했습니다.
 
### 개발 기간
`2026.06.16 ~ 2026.07.15`
 
### 팀 구성
`팀 프로젝트 (2인, 풀스택)` — 팀명 "일단 만들조"

### 프로젝트 결과
공공기관 서비스가 제공하지 못한 '실제 이용 경험'을 후기·별점 기반의 정성적 지표로 채워, 이용자가 방문 전 충전소 상태를 신뢰도 높게 가늠할 수 있도록 했습니다.  
이를 통해 정형 정보만으로는 알 수 없었던 대기시간·고장 빈도·만족도 정보를 커뮤니티 안에서 투명하게 공유하는 것을 목표로 합니다.


---
 
## 팀 구성 및 역할
| 이름 | 역할 | 담당 |
|---|---|---|
| 공통 | Full-stack | **[BE]** 외부 API(한국전력) 연동, 인증/인가(JWT 발급, 회원가입 유효성 검사·중복확인, 토큰 재발급), 라즈베리파이 실시간 데이터 연동, 후기 CRUD<br>**[FE]** 충전소 지도 렌더링/마커·클러스터링, 로그인·회원가입·마이페이지 화면, 후기 CRUD, 실시간 혼잡도 차트, QA 진행 |
| 박경환 | Full-stack | **[BE/FE]** 마이페이지(정보조회·수정, 비밀번호 변경, 프로필수정, 회원탈퇴), 충전소 즐겨찾기 등록/삭제, 후기 좋아요 등록/삭제 **[BE]** 문의게시판 댓글 CRUD |
| 이명훈 | Full-stack | **[BE/FE]** 공지게시판, 문의게시판 CRUD, 라즈베리 파이 데이터 시뮬레이션 설계 **[FE]** 문의게시판 댓글 CRUD |
 
---
 
## 주요 기능
- **충전소 지도 조회**: Naver Maps JS API와 한국전력 공공 API를 연동해 실제 충전소 위치·상세 정보를 지도 위에 매핑, 클러스터링 구현
- **후기 CRUD**: 텍스트·별점 기반 후기 CRUD, CUD는 로그인 필수
- **회원 인증**: JWT 기반 로그인/회원가입, Access·Refresh 토큰 재발급(Rotation) 및 axios 인터셉터를 통한 자동 토큰 갱신

 
---
 
## 아키텍처
<img width="1672" height="941" alt="ChatGPT Image 2026년 7월 16일 오후 12_05_24" src="https://github.com/user-attachments/assets/b0cfb6b8-62b8-425f-b136-d439970006c6" />

 

 
---
 
## 실행 방법
```bash
# Front-end
git clone https://github.com/1018lmh-hub/plugin-frontend.git
npm install
cp .env.example .env
npm run dev
 
# Back-end
git clone https://github.com/1018lmh-hub/plugin-backend.git
./gradlew bootRun
```
 
### 환경 변수 예시
```
DATABASE_URL=
JWT_SECRET=
NAVER_MAPS_CLIENT_ID=
KEPCO_API_KEY=
```
 
---
 
## 회고(개인)
 
### 배운 점
- CUD 예외처리를 AOP(@Around)로 일원화하며 반복 검증 책임을 적합한 계층에 위임하는 감각을 익힘
- Spring Security 예외는 MVC 예외 처리 흐름과 다른 계층(필터 체인)에서 발생한다는 점을 실제로 마주치며, 필터↔서블릿 경계를 넘는 지점에는 HandlerExceptionResolver 같은 연결 장치가 필요함을 확인함
- 프론트-백엔드 간 계약(응답 필드명, 메시지 문자열)이 어긋나면 재발급처럼 여러 계층을 거치는 로직은 한쪽만 고쳐서는 해결되지 않는다는 것을 배움
- 파일 검증·리네임 책임을 파일 객체(AttachedFile) 스스로 갖도록 도메인 모델 패턴을 적용해, 향후 기능 확장 시 재사용 가능한 구조를 확보함
### 아쉬운 점 / 개선하고 싶은 부분
- QA 112건 중 Pass율 66.9%(75건), 미해결(Open) 19건이 남아 있어 테스트 커버리지와 결함 조치 속도를 더 끌어올릴 필요가 있음
- 다중 조인 쿼리 일부가 아직 리팩토링되지 않아, 스칼라 서브쿼리 전환 및 인덱스 전략 추가 적용 같은 성능 개선 과제가 남아있음
- 서비스·컨트롤러 전반에 흩어진 중복 로직(파일 검증, 응답 변환 등)을 공통 유틸/추상 클래스로 통합하는 작업이 아직 부족해, 다시 만든다면 초기 설계 단계부터 공통 모듈화를 우선 고려하고 싶음
---
 
## 라이선스
