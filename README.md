# Spring Boot 프로젝트 맛있는재활용
<br>

> 음식물 쓰레기 감소를 위한 챗봇 레시피 추천 웹서비스 입니다

![메인 페이지](https://github.com/user-attachments/assets/d48cbb34-572c-4d88-856d-2941d4dbb4c7)

## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
     - [프론트엔드](#3-2-프론트엔드)
  - [실행 화면](#4-실행-화면)   


- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)
  - [API 설계](#3-api-설계)

- [개발 내용](#개발-내용)

- [마치며](#마치며)
  - [프로젝트 보완사항](#1-프로젝트-보완사항)
  - [후기](#2-후기)

## 들어가며
### 1. 프로젝트 소개

국비지원 수업의 마지막 과정으로 프로젝트를 진행하게 되었습니다. <br>
서비스 이용을 진행 할수록 누적되는 적절한 보상과 출석체크 시스템 및 공공데이터를 활용하여 <br>
유저들의 환경보호 참여의지를 고취시키는 챗봇 서비스 입니다

### 2. 프로젝트 기능

프로젝트의 주요 기능은 다음과 같습니다.

- **사용자 -** OAuth 2.0 카카오 로그인, 회원가입시 유효성 검사 및 중복 검사, 닉네임수정, 회원탈퇴
- **메인 -** 공공데이터 조회, 음식물쓰레기 계산기, 헤더 검색창
- **챗봇 -** CRUD 기능, 인공지능 채팅
- **랭킹 -** CRUD 기능
- **포인트샵 -** CRUD 기능, 문자 전송, 구매시 유효성 검사 
- **게시판/레시피 -** CRUD 기능, 추천수, 찜, 추천, 신고
- **댓글 -** CRUD 기능, 페이징 처리, 찜, 추천, 신고
- **마이페이지 -** CRUD 기능, 출석체크

### 3. 사용 기술

#### 3-1 백엔드

##### 주요 프레임워크 / 라이브러리
- Java 17
- SpringBoot 3.2.10
- MyBatis
- Spring Security
- OAuth 2.0

##### Build Tool
- Gradle 7.2

##### DataBase
- Oracle 11.2.0.2.0

#### 3-2 프론트엔드
- Html/Css
- JavaScript
- Jquery
- ThymeLeaf

### 4. 실행 화면
  <details>
    <summary>회원 관련 (Click)</summary>
     
  **1. 회원가입 화면**
  ![회원가입1](https://github.com/user-attachments/assets/495baa80-0da6-49ea-88e9-561f58661155)
  
  ![회원가입2](https://github.com/user-attachments/assets/9106347d-24a5-4c2c-b7cb-4e4f21a688ac)

  ![회원가입3](https://github.com/user-attachments/assets/6f0684bd-f97d-4a83-985a-815097a978bc)

  ![회원가입4](https://github.com/user-attachments/assets/3592e07c-1ea8-4b6b-a86b-eb2445572dea)

  ![회원가입4-2](https://github.com/user-attachments/assets/a4ee50b6-ebd7-4f3e-9b9f-48b98dc4ec2a)<br>
  중복확인 버튼을 통해 중복검사를 실시 한다
  
  ![회원가입5](https://github.com/user-attachments/assets/352f10a6-b3ee-4ced-9fb3-de885099fca4)<br>
  인증요청 버튼을 통해 인증번호를 받아 입력한다

  ![회원가입5-2](https://github.com/user-attachments/assets/210b5b4b-0cef-4e34-9968-84941ce7cb8d)
  
  ![회원가입5-3](https://github.com/user-attachments/assets/9ec7b5bb-724e-45c2-9e90-28064b153ccc)

  ![회원가입6](https://github.com/user-attachments/assets/5cecb39e-bb19-4176-bc51-5d63c3b84168)

  ![회원가입7](https://github.com/user-attachments/assets/9e09b2a5-d8af-4920-9abf-9859ea8884dd)<br>
  회원가입 시 유효성 검사 및 중복확인을 진행하며 완료시 회원 정보를 저장하고 로그인 화면으로 이동한다
  <br><br><br>
       
  **2. 로그인 화면** 
  ![로그인1](https://github.com/user-attachments/assets/aadbd937-44df-4bda-b5ff-aa8bd7a4cd63)

  ![로그인2](https://github.com/user-attachments/assets/818e2b4d-b83b-44d2-b076-7ea11b9b4e05)<br>
  입력 여부를 확인 후 안내창이 나온다

  ![로그인3](https://github.com/user-attachments/assets/661a39cc-e851-4a0d-b2c6-1b133d8f1a96)

  ![로그인4](https://github.com/user-attachments/assets/9d2e6fef-fd91-4311-b695-c9d1699ab772)<br>
  잘못된 로그인 정보 입력시 안내창이 나온다

  ![로그인5](https://github.com/user-attachments/assets/05797b3f-b51c-49ce-a4d7-10004c1ec2dd)

  ![로그인6](https://github.com/user-attachments/assets/416284e5-f8d2-4b1a-8541-bff353873be9)

  ![로그인7](https://github.com/user-attachments/assets/f8745e55-d7eb-48f7-9b8e-0ed62cf55815)<br>
  로그인 성공시 안내창이 나오고 세션처리로 헤더가 변경되며 메인 화면으로 redirect 한다
  <br><br><br>

  **2-1. OAuth 2.0 소셜 로그인 화면**<br>
  ![소셜로그인1](https://github.com/user-attachments/assets/f99456bd-e0b0-4e59-8343-e8f0b832ac01)
  
  ![소셜로그인2](https://github.com/user-attachments/assets/023b3ccc-0965-40b0-be83-e3bf14d8ea2f)

  ![소셜로그인3](https://github.com/user-attachments/assets/2c1a89d5-12a2-49c7-b90c-99fe6460a23a)
  
  ![소셜로그인4](https://github.com/user-attachments/assets/e3d93f58-8076-4f14-a8ef-e333222ee84f)<br>
  메일과 이름은 OAuth2에서 자동으로 받아오고 추가정보(핸드폰인증) 만 간단하게 기입 후 가입한다

  ![소셜로그인5](https://github.com/user-attachments/assets/cf65eeef-4889-44b9-87aa-dceaecfee857)<br>
  가입시 바로 로그인 되며 이후 카카오 로그인으로 간편 로그인이 가능하다<br>
  또한 카카오에 등록된 프로필사진과 이름이 세션에 자동으로 들어가게 설정하였다
  <br><br><br>
           
  </details>
  <br>

  <details>
    <summary>메인 관련 (Click)</summary>     
  
  **1. 메인페이지 공공데이터 섹션 화면**  
  ![메인공공데이터1](https://github.com/user-attachments/assets/80a652d0-5103-4d34-9ea0-3b2167322ded)<br>
  기본값은 현재의 전달 공공데이터가 나오게 된다

  ![메인공공데이터2](https://github.com/user-attachments/assets/ea21360d-6452-45fb-ab21-b79f115ea3c4)<br>
  그래프 아래의 월 선택 버튼으로 다른 시기의 공공데이터를 선택할 수 있다

  ![메인공공데이터3](https://github.com/user-attachments/assets/54afcea9-a4b1-42fb-a361-3c0573c5a776)<br>
  선택한 월에 대한 데이터로 변경되면서 관련 타이틀과 그래프가 변경된다
  <br><br><br>

  **2. 메인페이지 음식물쓰레기 계산기 화면**
  ![메인음쓰계산기1](https://github.com/user-attachments/assets/30d7798e-b499-4027-a269-c6c6ed37b6ab)<br>

  ![메인음쓰계산기2](https://github.com/user-attachments/assets/9535c112-7b6f-4960-9f63-781c070e4731)<br>
  입력창에 무게를 입력하면 소비된 에너지 및 비용등이 실시간 계산된다
  <br><br><br>

  </details>
  <br>

  <details>
    <summary>챗봇 관련 (Click)</summary>

  **1. 챗봇 접속시 화면**
  ![냉장봇1](https://github.com/user-attachments/assets/34cf3dca-73c9-404b-9621-8f3662a6bf70)
  사이드바는 x버튼으로 닫을 수 있고 입력창 좌측 버튼으로 열고 닫을 수 있다<br>
  이전채팅 목록을 10개까지 나타낸다
  <br><br><br>

  **2. 대화 화면**
  ![냉장봇2](https://github.com/user-attachments/assets/a2623a30-e908-4ea6-abe4-799884a991f6)
  채팅 입력 후 챗봇의 응답을 기다린다<br>

  ![냉장봇3](https://github.com/user-attachments/assets/c78afb47-5792-444e-96b7-a4ebe3c27b4d)
  채팅 응답이 온 뒤 사이드 바에 채팅방이 추가된다
  <br><br><br>

  **3. 이전채팅 화면**<br>
  ![냉장봇 이전채팅1](https://github.com/user-attachments/assets/2ef65b70-22a8-4217-a798-008ae6dee09e)
  
  ![냉장봇 이전채팅2](https://github.com/user-attachments/assets/079b0bb2-d3d5-4805-b64b-35fd891332d3)
  이전 채팅이 있다면 이전 채팅을 불러올 수 있다<br>
  이전채팅에 이어서 채팅이 가능하다
  <br><br><br>

  **4. 채팅삭제 화면**<br>
  ![냉장봇 삭제1](https://github.com/user-attachments/assets/3103553e-ad58-4b7c-801d-ea397932136e)<br>
  필요 없는 채팅은 삭제할 수 있다

  ![냉장봇 삭제2](https://github.com/user-attachments/assets/4f8ca33d-a05f-4b0f-bbc2-ff13110424db)<br>

  ![냉장봇 삭제2-2](https://github.com/user-attachments/assets/b893ebbf-2e49-4669-b3e0-430167b1a8bf)<br>
  현재 채팅방이라면 안내메세지가 다르다<br>
  
  ![냉장봇 삭제3](https://github.com/user-attachments/assets/e14121b4-e784-4b9c-aa15-20ee4d1a8c59)<br>

  ![냉장봇 삭제4](https://github.com/user-attachments/assets/343e9883-545d-4f21-9689-d7dc9ded3b72)<br>
  삭제가 완료되면 채팅목록에서 사라진다
  <br><br><br>

  </details>
  <br>

  <details>
    <summary>포인트샵 관련 (Click)</summary>

  **1. 포인트샵 화면**
  ![포인트샵1](https://github.com/user-attachments/assets/83af5648-410f-41e0-89f1-cc8584b74bdb)

  ![포인트샵2](https://github.com/user-attachments/assets/f6c37531-6d7f-4440-bed4-5d8419761cdd)
  좌측과 우측 버튼으로 슬라이드배너 형식의 포인트샵 상품을 볼 수 있다
  <br><br><br>

  **2. 포인트샵 구매 화면**<br>
  ![포인트샵구매1](https://github.com/user-attachments/assets/42eb31ec-f496-49af-979d-f6dd6b974cfc)<br>

  ![포인트샵구매1-2](https://github.com/user-attachments/assets/0146289e-7111-48e6-892b-71d98732b01f)<br>
  0 이하는 구매할 수 없다<br>

  ![포인트샵구매2](https://github.com/user-attachments/assets/3f9aae4e-0b61-447b-aeaa-9a003320accd)<br>

  ![포인트샵구매2-2](https://github.com/user-attachments/assets/287346c2-993c-4e67-8fbc-f3715aa9e285)<br>
  보유한 포인트보다 많은 포인트를 사용할 수 없다<br>

  ![포인트샵구매3](https://github.com/user-attachments/assets/d779b969-58c2-4bef-8c0a-357ba042b35f)<br>

  ![포인트샵구매3-2](https://github.com/user-attachments/assets/17c041b5-6688-4341-b8a4-7591ad433cb7)<br>
  입력값이 없어도 구매할 수 없다<br>

  ![포인트샵구매4](https://github.com/user-attachments/assets/8be1bb58-cdf7-42c7-a575-e036121d6ae1)<br>

  ![포인트샵구매4-2](https://github.com/user-attachments/assets/2c343dfb-a53e-41e8-be10-1ab189176ce2)<br>

  ![포인트샵구매4-3](https://github.com/user-attachments/assets/90d0c666-31bc-49b7-b75f-00dbd6e4b6d1)<br>

  ![포인트샵구매4-4](https://github.com/user-attachments/assets/64537766-47ff-4eeb-8698-43446746947e)<br>
  ![포인트샵구매4-6](https://github.com/user-attachments/assets/5f141156-4401-41e6-a503-4e3018b5ee1e)<br>

  ![포인트샵구매4-5](https://github.com/user-attachments/assets/ff8c75f1-eaed-4cfb-9fb1-ec31ad8ff86d)<br>
  구매 완료시 사용한 포인트와 재고가 차감되며 redirect 된다<br>
  문자로 구매한 상품의 코드를 받는다
  <br><br><br>  
  
  </details>
  <br>

  <details>
    <summary>헤더 검색 관련 (Click)</summary>

  **1. 검색 화면**
  ![검색1](https://github.com/user-attachments/assets/fef1da74-a414-475f-a36a-40291a81c3a0)
  검색 카테고리를 선택할 수 있다

  ![검색2](https://github.com/user-attachments/assets/511f07a3-f379-4600-9a26-187fb98343b7)<br>
  아무값도 입력하지 않으면 안내메세지가 뜬다

  ![검색결과1](https://github.com/user-attachments/assets/f1d0dc3a-81c9-4f2c-b2db-3a87df078a0e)<br>
  ![검색결과2](https://github.com/user-attachments/assets/e11bd922-5b4a-4f97-a67e-4731ec9e943d)
  검색결과가 존재하지 않을경우 안내페이지로 이동<br>

  ![검색결과3](https://github.com/user-attachments/assets/e7382f08-587a-40a6-87a5-01ef62bbf418)<br>
  ![검색결과4](https://github.com/user-attachments/assets/3a611c1f-c61c-4ba7-8a27-52e5a6f14a1a)
  ![검색결과5](https://github.com/user-attachments/assets/057e4b7a-e2f2-4d1e-85cb-a1d4cc6a7078)
  검색결과가 존재할 경우 검색결과 페이지 이동<br>
  게시글 클릭시 상세페이지로 이동
  <br><br><br>
  
  </details>
  <br>
  
## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  
<summary>패키지 구조 보기 (Click)</summary>   
 

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂com.dr
 ┃ ┃ ┃ ┗ 📂api
 ┃ ┃ ┃ ┃ ┗ 📜MyPageApi.java
 ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig.java
 ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardController.java
 ┃ ┃ ┃ ┃ ┣ 📂chatBot.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NangjangbotController.java
 ┃ ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ErrorContorller.java(아직미구현)
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MainController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PublicDataApiController.java
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ManagerController.java
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MyPageController.java
 ┃ ┃ ┃ ┃ ┣ 📂rnak
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RankController.java
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FileApiController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RecipeController.java
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PointShopController.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SmsController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
 ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┣ 📜CustomOAuth2User.java
 ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardReportDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardScoreDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FreeBoardCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FreeBoardDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FreeBoardListDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FreeBoardWriteDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FreeGoodDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HoneyBoardCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HoneyBoardDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HoneyBoardListDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜HoneyGoodDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatRequest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatResponse.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NangjangbotDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ApiDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DashBoardDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerBoardDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerLoginDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerPhotoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerPointDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerProductDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerRecipeDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerRegisterDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerReportDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerSessionDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ManagerUserDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CheckDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PointCheckDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PointDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PointRecordDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SirenListDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜UserCheckDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜UserInfoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜UserPosttDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜UserRecipeDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSteamDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RankDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeGoodDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeListDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeWriteCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotRecipeWriteDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MyRecipeCommentDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ManagerProductDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MyRecipeDetailDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MyRecipeGoodDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MyRecipeListDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MyRecipeWriteDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipePhotoDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeReportDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RecipeSteamDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PointShopDTO.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailFindDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoUsersDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PwFindDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PwResetDTO.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜UserDTO.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSessionDTO.java
 ┃ ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NangjangbotMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ManagerMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MyPageMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RecipeMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PointShopMapper.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📜KakaoUsers.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserMapper.java
 ┃ ┃ ┃ ┣ 📂mybatis
 ┃ ┃ ┃ ┃ ┗ 📜MyBatisConfig.java
 ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BoardService.java
 ┃ ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatBotService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NangjangbotService.java
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┣ 📜PublicDataService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SearchService.java
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ManagerService.java
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┗ 📜MyPageService.java
 ┃ ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RankService.java
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┗ 📜RecipeService.java
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗ 📜PointShopService.java
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CoolSmsService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomOAuth2UserService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜RandomStringGeneratorService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
 ┃ ┃ ┃ ┗ 📜DrApplication.java
 ┃ ┣ 📂resources
 ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┗ 📜config.xml
 ┃ ┃ ┣ 📂mapper
 ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┗📜BoardMapper.xml
 ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┗📜NangjangbotMapper.xml
 ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┗📜SearchMapper.xml
 ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┗📜ManagerMapper.xml
 ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┗📜MyPageMapper.xml
 ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┗📜RankMapper.xml
 ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┗📜RecipeMapper.xml
 ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┗📜PointShopMapper.xml
 ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┣📜KakaoUsersMapper.xml
 ┃ ┃ ┃ ┃ ┗📜UserMapper.xml
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣📜boardReport.css
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardDetail.css
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardList.css
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardModify.css
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardWirte.css
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardDetail.css
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardList.css
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardModify.css
 ┃ ┃ ┃ ┃ ┃ ┗📜honeyBoardWirte.css
 ┃ ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┃ ┗📜nangjangbot.css
 ┃ ┃ ┃ ┃ ┣ 📂fragment
 ┃ ┃ ┃ ┃ ┃ ┣📜header.css
 ┃ ┃ ┃ ┃ ┃ ┗📜footer.css
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┣📜boardSearchList.css
 ┃ ┃ ┃ ┃ ┃ ┣📜main.css
 ┃ ┃ ┃ ┃ ┃ ┣📜privacyPoilcy.css
 ┃ ┃ ┃ ┃ ┃ ┣📜recipeSearchList.css
 ┃ ┃ ┃ ┃ ┃ ┗📜terms.css
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┣📜dashBoard.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageBoard.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageComment.css
 ┃ ┃ ┃ ┃ ┃ ┣📜managePoint.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageProduct.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageRecipe.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageReport.css
 ┃ ┃ ┃ ┃ ┃ ┣📜managerLogin.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageUpdate.css
 ┃ ┃ ┃ ┃ ┃ ┣📜manageUser.css
 ┃ ┃ ┃ ┃ ┃ ┗📜RegisterProduct.css
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageCaution.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageCheck.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageDelete.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageInformation.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyComplaint.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyPoint.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyPost.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyRecipe.css
 ┃ ┃ ┃ ┃ ┃ ┗📜myPageSteamedList.css
 ┃ ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┃ ┗📜userRank.css
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotDetailPage.css
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotDetailPageNone.css
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeList.css
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeModify.css
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeWriter.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myDetailPage.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myDetailPageNone.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeList.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeModify.css
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeWriter.css
 ┃ ┃ ┃ ┃ ┃ ┗📜report.css
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗📜pointShop.css
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣📜apiJoin.css
 ┃ ┃ ┃ ┃ ┃ ┣📜drJoin.css
 ┃ ┃ ┃ ┃ ┃ ┣📜emailFind.css
 ┃ ┃ ┃ ┃ ┃ ┣📜emailFindFinish.css
 ┃ ┃ ┃ ┃ ┃ ┣📜join.css
 ┃ ┃ ┃ ┃ ┃ ┣📜login.css
 ┃ ┃ ┃ ┃ ┃ ┣📜PwFind.css
 ┃ ┃ ┃ ┃ ┃ ┗📜PwReset.css
 ┃ ┃ ┃ ┣ 📂image
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ 📂nangjangbot
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ 📂photo
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ 📂product
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗```이미지들
 ┃ ┃ ┃ ┃ ┣ ```이미지들
 ┃ ┃ ┃ ┣ 📂js
 ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣📜boardReport.js
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardDetail.js
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardList.js
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardModify.js
 ┃ ┃ ┃ ┃ ┃ ┣📜freeBoardWirte.js
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardDetail.js
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardList.js
 ┃ ┃ ┃ ┃ ┃ ┣📜honeyBoardModify.js
 ┃ ┃ ┃ ┃ ┃ ┗📜honeyBoardWirte.js
 ┃ ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┃ ┗📜nangjangbot.js
 ┃ ┃ ┃ ┃ ┣ 📂fragment
 ┃ ┃ ┃ ┃ ┃ ┗📜header.js
 ┃ ┃ ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┃ ┃ ┗📜main.js
 ┃ ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┃ ┣📜dashBoard.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageBoard.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageComment.js
 ┃ ┃ ┃ ┃ ┃ ┣📜managePoint.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageProduct.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageRecipe.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageReport.js
 ┃ ┃ ┃ ┃ ┃ ┣📜managerLogin.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageUpdate.js
 ┃ ┃ ┃ ┃ ┃ ┣📜manageUser.js
 ┃ ┃ ┃ ┃ ┃ ┗📜RegisterProduct.js
 ┃ ┃ ┃ ┃ ┣ 📂module
 ┃ ┃ ┃ ┃ ┃ ┗📜module.js
 ┃ ┃ ┃ ┃ ┣ 📂myPage
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageCaution.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageCheck.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageDelete.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageInformation.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyComplaint.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyPoint.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyPost.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myPageMyRecipe.js
 ┃ ┃ ┃ ┃ ┃ ┗📜myPageSteamedList.js
 ┃ ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┃ ┗📜userRank.js
 ┃ ┃ ┃ ┃ ┣ 📂recipe
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotDetailPage.js
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotDetailPageNone.js
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeList.js
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeModify.js
 ┃ ┃ ┃ ┃ ┃ ┣📜chatBotRecipeWriter.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myDetailPage.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myDetailPageNone.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeList.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeModify.js
 ┃ ┃ ┃ ┃ ┃ ┣📜myRecipeWriter.js
 ┃ ┃ ┃ ┃ ┃ ┗📜report.js
 ┃ ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┃ ┗📜pointShop.js
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣📜apiJoin.js
 ┃ ┃ ┃ ┃ ┃ ┣📜drJoin.js
 ┃ ┃ ┃ ┃ ┃ ┣📜emailFind.js
 ┃ ┃ ┃ ┃ ┃ ┣📜emailFindFinish.js
 ┃ ┃ ┃ ┃ ┃ ┣📜join.js
 ┃ ┃ ┃ ┃ ┃ ┣📜login.js
 ┃ ┃ ┃ ┃ ┃ ┣📜PwFind.js
 ┃ ┃ ┃ ┃ ┃ ┗📜PwReset.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┣📜boardReport.html
 ┃ ┃ ┃ ┃ ┣📜freeBoardDetail.html
 ┃ ┃ ┃ ┃ ┣📜freeBoardList.html
 ┃ ┃ ┃ ┃ ┣📜freeBoardModify.html
 ┃ ┃ ┃ ┃ ┣📜freeBoardWirte.html
 ┃ ┃ ┃ ┃ ┣📜honeyBoardDetail.html
 ┃ ┃ ┃ ┃ ┣📜honeyBoardList.html
 ┃ ┃ ┃ ┃ ┣📜honeyBoardModify.html
 ┃ ┃ ┃ ┃ ┗📜honeyBoardWirte.html
 ┃ ┃ ┃ ┣ 📂chatBot
 ┃ ┃ ┃ ┃ ┗📜nangjangbot.html
 ┃ ┃ ┃ ┣ 📂error
 ┃ ┃ ┃ ┃ ┗📜미구현
 ┃ ┃ ┃ ┣ 📂fragment
 ┃ ┃ ┃ ┃ ┣📜footer.html
 ┃ ┃ ┃ ┃ ┗📜header.html
 ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┣📜boardSearchList.html
 ┃ ┃ ┃ ┃ ┣📜privacyPolicy.html
 ┃ ┃ ┃ ┃ ┣📜recipeSearchList.html
 ┃ ┃ ┃ ┃ ┗📜terms.html
 ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┣📜dashBoard.html
 ┃ ┃ ┃ ┃ ┣📜manageBoard.html
 ┃ ┃ ┃ ┃ ┣📜manageComment.html
 ┃ ┃ ┃ ┃ ┣📜managePoint.html
 ┃ ┃ ┃ ┃ ┣📜manageProduct.html
 ┃ ┃ ┃ ┃ ┣📜manageRecipe.html
 ┃ ┃ ┃ ┃ ┣📜manageReport.html
 ┃ ┃ ┃ ┃ ┣📜managerLogin.html
 ┃ ┃ ┃ ┃ ┣📜manageUpdate.html
 ┃ ┃ ┃ ┃ ┣📜manageUser.html
 ┃ ┃ ┃ ┃ ┗📜registerProduct.html
 ┃ ┃ ┃ ┣ 📂rank
 ┃ ┃ ┃ ┃ ┗📜userRank.html
 ┃ ┃ ┃ ┣ 📂manager
 ┃ ┃ ┃ ┃ ┣📜chatBotDetailPage.html
 ┃ ┃ ┃ ┃ ┣📜chatBotDetailPageNone.html
 ┃ ┃ ┃ ┃ ┣📜chatBotRecipeList.html
 ┃ ┃ ┃ ┃ ┣📜chatBotRecipeModify.html
 ┃ ┃ ┃ ┃ ┣📜chatBotRecipeWirte.html
 ┃ ┃ ┃ ┃ ┣📜myDetailPage.html
 ┃ ┃ ┃ ┃ ┣📜myDetailPageNone.html
 ┃ ┃ ┃ ┃ ┣📜myRecipeList.html
 ┃ ┃ ┃ ┃ ┣📜myRecipeModify.html
 ┃ ┃ ┃ ┃ ┣📜myRecipeWirter.html
 ┃ ┃ ┃ ┃ ┗📜report.html
 ┃ ┃ ┃ ┣ 📂shop
 ┃ ┃ ┃ ┃ ┗📜pointShop.html
 ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┣📜apiJoin.html
 ┃ ┃ ┃ ┃ ┣📜drJoin.html
 ┃ ┃ ┃ ┃ ┣📜emailFind.html
 ┃ ┃ ┃ ┃ ┣📜emailFindFinish.html
 ┃ ┃ ┃ ┃ ┣📜join.html
 ┃ ┃ ┃ ┃ ┣📜login.html
 ┃ ┃ ┃ ┃ ┣📜PwFind.html
 ┃ ┃ ┃ ┃ ┗📜PwReset.html
 ┃ ┃ ┗ 📜application.properties
 ```
  
 </details>   
 <br/>    
   
     
 ### 2. DB 설계
 
![ERD](https://github.com/user-attachments/assets/3f72fc6e-dcac-4a73-83cb-4cb9baa6a438)
※엔티티 관계도 이미지 클릭후 확대 가능합니다

![ERD_USER](https://github.com/user-attachments/assets/aeb7fd4b-5c23-49fe-ad2c-feb8958e95bd)
![ERD_MANAGER](https://github.com/user-attachments/assets/f5056cf2-90a0-46a1-9403-0676ceacc6f6)
![ERD_BOARD](https://github.com/user-attachments/assets/3164ca6d-c73e-4425-baa9-d3b5381eb5a2)
![ERD_RECIPE](https://github.com/user-attachments/assets/39fe62cc-e2fe-467f-8976-7d79509495cd)
![ERD_PRODUCT](https://github.com/user-attachments/assets/73bf54c8-f5f9-41e9-8381-0431c3a1e594)
![ERD_PHOTO](https://github.com/user-attachments/assets/5d7022fb-7f6b-4bf9-80dc-eddc92c03e94)
![ERD_SCORE](https://github.com/user-attachments/assets/c7702d30-61f7-4260-97d1-fcadf9f7277f)
![ERD_POINT](https://github.com/user-attachments/assets/a6c8b216-8d20-45e4-838c-08a7e5c567ec)
![ERD_STEAM](https://github.com/user-attachments/assets/b0bb863a-4627-4b66-a095-e6f6763141b8)
![ERD_GOOD](https://github.com/user-attachments/assets/ef3ebdd8-3d41-4d75-9cee-41758eb67caa)
![ERD_REPLY](https://github.com/user-attachments/assets/245d0748-0d98-4358-bf26-6cee077ba998)
![ERD_SIREN](https://github.com/user-attachments/assets/f30dd0eb-c5c9-47f9-b801-7db8854a91ad)
![ERD_DAILY](https://github.com/user-attachments/assets/03143311-5563-4842-92eb-cc79e245fad9)
![ERD_CHATSESSION](https://github.com/user-attachments/assets/d3648641-3890-4ba3-9ace-c93befcc083c)
![ERD_CHAT](https://github.com/user-attachments/assets/715f2092-95dc-44fa-8881-4715add9245b)

<br/>

### 3. API 설계

![게시글 관련 API 설계](https://user-images.githubusercontent.com/59757689/156749365-5e4cee67-1431-4e3a-9140-7b58b6e1fd53.PNG)
![회원 관련 API 설계 (2)](https://user-images.githubusercontent.com/59757689/148911411-0cfb65ee-5782-4f04-a7c9-7dcc84abfed8.PNG)   
![댓글 관련 API 설계v2](https://github.com/hojunnnnn/board/assets/59757689/fa9032f0-3ce1-4ec4-9dbd-f420fb4e6152)  

## 개발 내용


- <a href="https://dev-coco.tistory.com/111" target="_blank">게시판 프로젝트 명세서 정리</a>
- <a href="https://dev-coco.tistory.com/113" target="_blank">게시판 조회수 기능 추가</a>
- <a href="https://dev-coco.tistory.com/114" target="_blank">게시판 페이징 처리 구현</a>
- <a href="https://dev-coco.tistory.com/115" target="_blank">게시판 검색처리 및 페이징 구현</a>
- <a href="https://dev-coco.tistory.com/117" target="_blank">생성, 수정시간 LocalDateTime format 변경</a>
- <a href="https://dev-coco.tistory.com/120" target="_blank">Security 회원가입 및 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/121" target="_blank">Security Mustache CSRF 적용 및 문제해결</a>
- <a href="https://dev-coco.tistory.com/122" target="_blank">커스텀 어노테이션을 통해 중복코드 개선</a>
- <a href="https://dev-coco.tistory.com/124" target="_blank">회원가입 Validation 유효성 검사</a>
- <a href="https://dev-coco.tistory.com/125" target="_blank">회원가입 Validation 커스터마이징 중복 검사</a>
- <a href="https://dev-coco.tistory.com/126" target="_blank">Security 로그인 실패시 메시지 출력하기</a>
- <a href="https://dev-coco.tistory.com/127" target="_blank">Security 회원정보 수정(ajax)</a>
- <a href="https://dev-coco.tistory.com/128" target="_blank">OAuth 2.0 구글 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/129" target="_blank">OAuth 2.0 네이버 로그인 구현</a>
- <a href="https://dev-coco.tistory.com/130" target="_blank">JPA 연관관계 매핑으로 글 작성자만 수정, 삭제 가능하게 하기</a>
- <a href="https://dev-coco.tistory.com/133" target="_blank">JPA 양방향 순환참조 문제 및 해결</a>
- <a href="https://dev-coco.tistory.com/132" target="_blank">게시판 댓글 작성 및 조회 구현</a>
- <a href="https://dev-coco.tistory.com/134" target="_blank">게시판 댓글 수정 및 삭제 구현</a>
- <a href="https://dev-coco.tistory.com/136" target="_blank">게시판 댓글 작성자만 수정, 삭제 가능하게 하기</a>
- <a href="https://dev-coco.tistory.com/138" target="_blank">[리팩토링]Dto Class를 Inner Class로 한번에 관리하기</a>

## 마치며   
### 1. 프로젝트 보완사항   

초기에 구상한 기능은 기본적인 CRUD 즉, 게시판에 올라오는 게시글을 대상으로 Create, Read, Update, Delete가 가능한 게시판이었습니다.   
템플릿 엔진으로 Mustache를 선택했는데, 그 이유는 Mustache는 단순히 화면에 데이터를 렌더링 하는 엔진이고   
Logic-less 하기 때문에 View의 역할과 서버의 역할이 명확하게 분리되어 OOP의 5원칙 중 하나인 SRP를 지킬 수 있어    
MVC 설계에서 Model, View, Controller의 역할에 대한 구분도 명확하게 할 수 있겠다는 생각이 들었습니다.   
또한, 다른 템플릿에 비해 빠른 로딩 속도를 자랑하며, xss를 기본적으로 이스케이프 할 수 있다는 장점들에 이끌려 Mustache를 사용하게 되었습니다.   
그러나 게시판 CRUD 기능이 완성되어 갈 때 쯤, 아쉬운 부분이 계속해서 생겨 몇몇 기능들을 추가하게 되었습니다.   
mustache는 로직을 넣을 수 없어 그 과정에 데이터를 렌더링 하기 전 서버에서 전처리를 하거나,    
화면에 표시된 후에 자바스크립트로 후처리를 해줬지만 조금 아쉬운 부분이 몇 가지 남아있다고 생각합니다.   
<details>
  <summary>보완사항</summary>
     
  
- 페이징 처리 및 검색 페이징에서 페이지 번호 활성화
- 페이지 번호는 10페이지 단위로 보여주기
- 페이지 처음, 끝으로 이동하는 버튼
- 생성, 수정시간 format 설정 varchar > datetime
- 다른 사용자와 자신의 댓글이 댓글란에 있을때 자신의 댓글만 수정,삭제 버튼 보이기
  
</details>   

추후에 브랜치를 나눠 Mustache에서 Thymeleaf로 조금씩 바꾸며 프로젝트 완성도를 높이고, 고도화 할 계획에 있습니다.   
   
   <details>
  <summary>추가할 기능 </summary>
     
  
- 챗봇 사이드바 페이징 처리
- 쿠키나 세션을 이용해 조회수 중복 카운트 방지
- 파일 업로드 기능 추가
- 좋아요 기능 추가
  
</details>  


### 2. 후기   

혼자 독학하며 처음 만들어본 프로젝트이기 때문에,   
공부한 내용을 사용해보는 설렘만큼이나 부족한 부분에 대한 아쉬움도 많이 남았습니다.   
효율적인 설계를 위해 고민하고 찾아보며 실제로 많이 공부할 수 있었던 부분도 많았습니다.   
책이나 블로그, 강의로 공부한 예제에서 납득했던 부분들은 실제로 코드를 짜면서 다양한 애로 사항을 마주했고   
'이 로직은 이 단계에서 처리하는게 맞는가', '각 레이어간 데이터 전달은 어떤 방식이든 DTO로 하는게 맞는가' 등   
여러 고민에 빠져 헤맨적도 있었지만, 다행히 결과는 대부분 최선을 찾았었던 것 같습니다.   
그리고 내가 만든 코드를 남에게 보여줬을 때, 누군가 코드의 근거를 물어본다면   
과연 자신 있게 나의 생각을 잘 얘기할 수 있을까 라는 생각을 굉장히 많이 하게 되었습니다.   
그래서 하나를 구현할 때 '이렇게 구현 하는 것이 과연 최선인가', '더 나은 Best Practice는 없을까'   
스스로 의심하고 고민하게 되는 습관을 가지게 되었습니다.   

두 번째로 기술적인 부분에서 더 공부하고 싶은 '방향'을 찾을 수 있었습니다.   
이번 프로젝트는 저에게 좋은 경험이 되었고, 저의 부족한 부분을 스스로 알 수 있는 좋은 계기가 되었습니다.   
부족한 부분에 대해 스스로 인지하고 있고, 더 깊게 공부하며 스스로 발전할 수 있는 '방향'을 다시한번 찾을 수 있게 되었습니다.   
이를 통해 더 나은 웹 애플리케이션을 만들 수 있을 것 같다는 자신감도 생겼습니다.   

끝까지 읽어주셔서 감사합니다. 
