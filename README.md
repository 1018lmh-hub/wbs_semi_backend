---


# Plug-In

<img width="1915" height="945" alt="image" src="https://github.com/user-attachments/assets/08fad005-72f6-45f3-aa73-4b18ba644603" />

---

## 프로젝트 개요

### 1. 프로젝트 목적

> 전기차 충전소에 후기를 남기는 커뮤니티

기존 공공 충전소 지도 서비스(예: EV맵)는 위치·상태 등 정형 정보 제공에 그쳐, 실제 대기시간·고장 빈도·이용 만족도 같은 비정형 경험 정보는 확인할 수 없었습니다.
전기차 보급 확대로 충전소 이용 수요가 지속 증가하는 상황에서, 기존 지도 서비스 위에 '후기 공유' 기능을 결합해 정보 비대칭을 해소하고자 본 프로젝트를 기획했습니다.

### 2. 기간

`2026.06.16 ~ 2026.07.15`

### 3. 팀 구성

팀명 : 일단 만들조<br />
인원 : 박경환, 이명훈

| 이름   | 역할       | 담당                                                                                                                                                                                                                                                                  |
| ------ | ---------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 공통   | Full-stack | **[BE]** 외부 API(한국전력) 연동, 인증/인가(JWT 발급, 회원가입 유효성 검사·중복확인, 토큰 재발급), 라즈베리파이 실시간 데이터 연동, 후기 CRUD<br>**[FE]** 충전소 지도 렌더링/마커·클러스터링, 로그인·회원가입·마이페이지 화면, 후기 CRUD, 실시간 혼잡도 차트, QA 진행 |
| 박경환 | Full-stack | **[BE/FE]** 마이페이지(정보조회·수정, 비밀번호 변경, 프로필수정, 회원탈퇴), 충전소 즐겨찾기 등록/삭제, 후기 좋아요 등록/삭제 **[BE]** 문의게시판 댓글 CRUD                                                                                                            |
| 이명훈 | Full-stack | **[BE/FE]** 공지게시판, 문의게시판 CRUD, 라즈베리 파이 데이터 시뮬레이션 설계 **[FE]** 문의게시판 댓글 CRUD                                                                                                                                                           |

### 4. 프로젝트 결과

공공기관 서비스가 제공하지 못한 '실제 이용 경험'을 후기·별점 기반의 정성적 지표로 채워, 이용자가 방문 전 충전소 상태를 신뢰도 높게 가늠할 수 있도록 했습니다. 
이를 통해 정형 정보만으로는 알 수 없었던 대기시간·고장 빈도·만족도 정보를 커뮤니티 안에서 투명하게 공유하는 것을 목표로 합니다.

---

## 기술스택

### 1. Front

![React](https://img.shields.io/badge/React-19.2.7-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Node.js](https://img.shields.io/badge/Node.js-22.22.3-339933?style=for-the-badge&logo=nodedotjs&logoColor=white)

### 2. Back

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.16-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)

### 3. DB

![Oracle](https://img.shields.io/badge/Oracle_21_XE-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5.15-DA1F31?style=for-the-badge)
![ERDCloud](https://img.shields.io/badge/ERDCloud-4A90D9?style=for-the-badge)

### 4. ETC

![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![GitHub Projects](https://img.shields.io/badge/GitHub_Projects-181717?style=for-the-badge&logo=github&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)
![draw.io](https://img.shields.io/badge/draw.io-F08705?style=for-the-badge&logo=diagramsdotnet&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white)

---

## 아키텍처

<img width="1672" height="941" alt="ChatGPT Image 2026년 7월 16일 오후 12_05_24" src="https://github.com/user-attachments/assets/b0cfb6b8-62b8-425f-b136-d439970006c6" />

---

## 주요 기능

### 1. 충전소 지도 조회

- **충전소 지도 조회**: Naver Maps JS API와 한국전력 공공 API를 연동해 실제 충전소 위치·상세 정보를 지도 위에 매핑, 클러스터링 구현
  
  <img width="1916" height="945" alt="image" src="https://github.com/user-attachments/assets/6e572574-19bb-4a5d-a082-b7ee9672db7e" />

### 2. JWT기반 회원 인증

- **회원 인증**: JWT 기반 로그인/회원가입, Access·Refresh 토큰 재발급(Rotation) 및 axios 인터셉터를 통한 자동 토큰 갱신
  코드

### 3. 핵심 MVP

- **후기 CRUD**: 텍스트·별점 기반 후기 CRUD, CUD는 로그인 필수
  코드

### 4. 추가 기능

#### 1. 마이페이지

#### 2. 공지게시판

#### 3. 문의게시판

#### 4. 충전소 즐겨찾기 / 후기 좋아요

---

## 실행 방법

```bash

```

### 환경 변수 예시

```

```

---

## 라이선스
