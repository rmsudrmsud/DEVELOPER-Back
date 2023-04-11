# DEVELOPER-Back
Kosta Final Project
-------------

## 🖥️ 프로젝트 소개

엠브레인 트렌드 모니터의 조사에 따르면 2030 조기 퇴사자들의 대부분은 ‘직무에 대한 비전’과 ‘미래 지향성’ 부족’을 퇴사 이유로 뽑습니다.
이처럼 자신의 성장 가능성에 많은 관심을 기울이는 사람들이 늘어나고 있습니다.
2030 대다수의 자기 개발에 대한 관심 증가와 더불어 IT 업계 특성상 업계 종사자들의 지속적인 성장에 대한 니즈를 충족시킬 수 있는 시장은 성장 가능성이 크다고 판단했습니다. 
따라서 IT 관련 지식 서비스를 향유할 수 있는 플랫폼을 구성하게 되었습니다. 관리자의 승인 하에 사용자들은 누구나 튜터가 될 수 있고, 수업을 수강할 수 있습니다. 
* * *
## 🕰️ 개발 기간

### 1차 개발 

* 23.01.06 - 23.01.13 : 기획 및 이벤트 스토밍 진행

* 23.01.14 - 23.01.16 : ERD 작성 및 DB 구현 

* 23.01.17 - 23.01.24 : 기능 개발 

* 23.01.25 : 1차 발표 


### 2차 개발 

* 23.02.20 - 23.02.22 : 프론트 작업 진행

* 23.02.23 - 23.02.27 : 1차 개발, 미구현 기능 작업

* 23.02.28 : 파일 병합

* 23.03.01 - 23.03.07 : 2차 개발,  추가 기능 구현

* 23.03.08 - 23.03.12 : 프론트 및 기능 구현 마무리

* 23.03.13 : 파일 병합 및 발표 준비

* 23.03.14 : 2차 발표 

* * *

## 🧑‍🤝‍🧑 맴버구성

팀장 1, 팀원 4

* * *

## ⚙️ 개발 환경
|&nbsp;|&nbsp;|
|---|---|
|Language|Java SE-11, JavaScript|
|Server|Tomcat 9.0.71|
|Libary|JUnit, Jackson, json-simple, lombok, commons-fileupload, thumbnailator, modelmapper, log4jdbc, spring-boot-starter-mail, JQuery, summerNote|
|OS|Mac OS|
|IDE|Eclipse IDE 2022-12|
|Framework|SpringBoot 2.7.8|
|Database|Oracle 11g Express Edition, Visual Studio code|
|ORM|1차 : Mybatis, 2차 : Spring Data JPA|
|협업 관리 툴|Github, Notion, Google Drive|

* * *

## 📌 맡은 파트 주요 기능

### 로그인
  * 로그인 시 쿠키(Cookie) 및 세션(Session) 생성
* ID찾기
  + 정보를 입력하여 DB검색 이후 ID값 출력
* PW찾기
  + 정보를 입력하여 임시비밀번호를 Email로 발송

### 커뮤니티
  * 게시글 전체목록 페이징 정렬, 검색
  * 글 작성, 읽기, 수정, 삭제(CRUD)
  * 댓글 작성, 읽기, 수정, 삭제(CRUD)

### 회원 마이페이지 일부

### 관리자 페이지 일부

* * *
## ERD
<img src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/FinalERD.png">

* * *
## 결과 페이지

* 로그인
<p align="center">
<img width="600" alt="로그인" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/Login.png">
</p>

* ID 찾기
<p align="center">
<img width="600" alt="ID 찾기" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/FindId.png">
</p>

* Pwd 찾기
<p align="center">
<img width="600" alt="비밀번호 찾기" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/FindPwd.png">
</p>

<p align="center">
<img width="600" alt="비밀번호 발송" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/FindPwd2.png">
</p>

* 커뮤니티

  + 전체 글 목록
  <p align="center">
<img width="600" alt="글 전체목록" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/BoardList.png">
</p>

  + 게시글 검색
  <p align="center">
<img width="600" alt="글 검색" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/Search.png">
</p>

  + 글 작성
  <p align="center">
<img width="600" alt="글 작성" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/AddBoard.png">
</p>

  + 글 상세
  <p align="center">
<img width="600" alt="글 상세" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/DetailBoard.png">
</p>

  + 글 수정
  <p align="center">
<img width="600" alt="글 수정" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/EditBoard1.png">
</p>
<p align="center">
<img width="600" alt="글 수정2" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/EditBoard2.png">
</p>

  + 글 삭제
  <p align="center">
<img width="600" alt="글 삭제" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/DeleteBoard.png">
</p>

  + 댓글 작성
  <p align="center">
<img width="600" alt="댓글 작성" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/AddRep.png">
</p>

  + 댓글 수정
  <p align="center">
<img width="600" alt="댓글 수정"src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/EditRep.png">
</p>

  + 댓글 삭제
  <p align="center">
<img width="600" alt="댓글 삭제" src="https://github.com/rmsudrmsud/DEVELOPER-Back/blob/feature-gh/back/image/DeleteRep.png">
</p>


* 마이페이지

* 관리자 페이지
