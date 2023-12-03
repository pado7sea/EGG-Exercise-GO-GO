# ssafy10_1_final


# EGG (Exercise Go Go)

목차

## 프로젝트 소개

**프로젝트 명** : EGG

**팀 명** : 삶은 계란

**팀원 소개 :** 이서윤, 정혜진

### **프로젝트 제작 동기** :

계란에서 병아리가 될 때까지! 

이 프로젝트는 운동을 시작하는 것이 얼마나 어려운 일인지를 알고 있고, 그 어려움을 극복하고자 하는 이들을 위해 탄생했습니다. 계란에서 병아리가 되는 순간처럼, 운동을 시작하는 순간이 가장 어렵고 중요하다는 인식에서 시작되었습니다.

알을 깨고 나오는 것이 가장 어려운 당신에게, 운동 동기부여 홈페이지 EGG 입니다.

### **프로젝트 개요 :**

오늘의 날씨는? EGG 하기 좋은 날씨!

날씨에 구애받지 않고 누구나 쉽게 운동을 시작할 수 있도록 돕기 위한 프로젝트입니다. EGG는 사용자에게 오늘의 날씨를 고려한 맞춤형 운동 추천과 함께, 다양한 기능을 제공하여 운동을 즐겁게 이어나갈 수 있는 플랫폼을 제공합니다. 

시작의 부담을 최소화하고, 사용자가 꾸준한 운동 습관을 형성할 수 있도록 합니다.

## 프로젝트 설계

### 툴

**기술 스택**

스프링 부트, 뷰

**협업 툴**

피그마, 노션, 깃

### **화면 설계서(목업페이지)**

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/b98737f4-f99c-46ee-a196-2a90feba91e9/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/059eddb7-2850-4144-8f1b-3c6d3eddba18/Untitled.png)

- 메인페이지(로그인 전)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/ec330e64-716a-4213-8e2b-0a9da73a5815/Untitled.png)
    
- 메인페이지(로그인 후)
    
    날씨에 따라 실외/실내 운동 장소와 운동 영상을 추천해줍니다.
    
    날씨가 추울 경우, 실내 운동하기 좋은 곳과 실내 운동으로 무엇을 하면 좋을지 영상을 보여줍니다.
    
    처음부터 다양한 선택지를 주기보다 적은 선택지를 보여주므로써, 시작에 대한 부담감을 줄여줍니다.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/59f19db1-f088-40ca-bd49-e313fadc2ede/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/8f99c429-d044-4939-a632-c54d37d0da4f/Untitled.png)
    
- 지도(카카오맵 지도) & 영상(유튜브)
    
    더 많은 선택지를 원하는 사람들을 위해 더 많은 장소와 더 많은 영상을 보여주는 기능도 만들었습니다.
    
    이 기능은 메인 홈에서 들어갈 수도 있고, nav바를 통해 따로 조회할 수도 있습니다.
    
    지도와 영상 탭에서는 검색창만 있는 것이 아니라 추천 키워드를 주변에 배치함으로써, 역시 시작에 대한 부담감을 줄였습니다.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/c83a2d80-ea7e-47cc-8940-049b8515ddb2/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/de1baadd-7b7c-4502-b20d-07083c8ddb39/Untitled.png)
    
- 인증 게시판
    
    운동 인증 게시판에서 다른 사람들의 운동인증 글을 보여줌으로써, 운동 동기부여를 해줍니다.
    
    사진 위주로 구성된 게시판으로, 오늘의 운동 인증 사진을 올릴 수 있습니다. 
    
    운동 인증 게시판에 글을 올리면 알을 하나 드립니다. 
    
    모은 알들이 병아리가 되길 기대하며, 매일매일 알을 모아봅시다.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/fa5ae353-4cee-4944-b19b-6b41e8695b8f/Untitled.png)
    
- 친구 탭
    
    항상 화면 위쪽에서 자신이 모은 알들과 최근 운동시간을 확인할 수 있습니다.
    
    친구 탭에서는 친구가 모은 알과 최근 운동시간을 볼 수 있습니다. 친구의 활동을 같이 보여줌으로써 경쟁심을 자극합니다. 
    
    친구 추가와 삭제가 가능합니다.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0bb77181-ed96-4727-b4ff-c22d5c8aadd3/c2e09d5a-5f13-4d38-91f9-8799fef6ed53/Untitled.png)
    

### **요구사항 명세서**

| 요구사항 구분 | 명칭 | 요구사항 상세 | 담당 | 비고 |
| --- | --- | --- | --- | --- |
| UI | Nav-bar | 1. 로고 클릭하면 홈으로 |  |  |
|  |  | 2. 로그인 |  |  |
|  |  | 2-1. 로그아웃 |  | 로그인 시 로그아웃 버튼으로 전환 |
|  |  | 3. 회원가입 |  | 로그인 시 로그아웃 버튼으로 전환 |
|  |  | 4. 인증게시판 |  |  |
|  |  | 5. 친구탭 |  |  |
|  |  | 6. 유튜브 |  |  |
|  |  | 7. 카카오 맵 |  |  |
|  |  | 8. 화면 상단 알 개수 |  | 글 작성 시마다 증가 |
| 기능 | 회원가입 | 1. 아이디 |  |  |
|  |  | 2. 비밀번호 |  |  |
|  |  | 2-1. 비밀번호 확인 |  |  |
|  |  | 3. 이름 |  |  |
|  |  | 4. 생일 |  |  |
|  |  | 5. 비밀번호 재확인 기능 |  | 두 비밀번호가 일치해야 회원가입 가능 |
|  |  | 6. 실시간 비밀번호 확인 (숨기기/보이기 버튼) |  |  |
|  |  | 7. 유효한 날짜 확인 (날짜 형식, 현재 날짜 이후 날짜 제한) |  |  |
|  |  | 8. 회원가입 버튼 활성화 조건 |  | 아이디, 비밀번호, 이름, 유효한 날짜 모두 입력시 활성화 |
|  |  | 9. 회원가입 성공 시 로그인 페이지로 이동 |  |  |
|  |  | 10. 비밀번호 해싱 저장 |  | 비밀번호는 서버에 저장되지 않고 해싱된 값이 저장됨 |
| 기능 | 로그인 | 1. 아이디 |  |  |
|  |  | 2. 비밀번호 |  |  |
| 기능 | 메인페이지(로그인 전) | 1. 아이디 입력 |  |  |
|  |  | 2. 비밀번호 입력 |  |  |
|  |  | 3. 비밀번호 숨기기/보이기 버튼 |  |  |
| 기능 | 메인페이지(로그인 후) | 1. 날씨 API를 이용한 현재 위치의 기온 정보 가져오기 |  |  |
|  |  | 2. 현재 기온에 따라 실외/실내 운동 장소와 영상 추천 |  |  |
|  |  | 3. 날씨가 추울 경우, 실내 운동 장소 및 영상 추천 |  | 부담감 감소를 위한 적은 선택지 제시 |
|  |  | 4. 날씨가 야외 운동하기에 적절한 경우, 야외 운동 장소 및 영상 추천 |  | 부담감 감소를 위한 적은 선택지 제시 |
| UI |  | 5. 캐로셀 기능을 통한 지도와 영상 전환 |  |  |
|  |  | 6. 항상 화면 상단에 알 개수와 최근 운동시간 표시 |  |  |
| 기능 | 지도 및 영상 | 1. 더 많은 장소 및 영상 보여주기 기능 |  | 메인페이지에 연결된 라우터 |
|  |  | 2. 검색창 외에 추천 키워드 배치 |  | 메인페이지에 연결된 라우터 |
| 기능 | 지도 | 1. 카카오맵 API를 이용한 지도 정보 가져오기 |  | 메인페이지, Nav창에서 모두 접근 가능 |
|  |  | 2. 현재 위치를 병아리 그림 마커로 표시 |  |  |
|  |  | 3. 키워드 검색을 통한 주변 장소 마커 표시 |  |  |
|  |  | 4. 장소 검색 결과 리스트 및 페이지네이션 |  |  |
| 기능 | 영상 | 1. 유튜브 API를 이용한 영상 정보 가져오기 |  | 메인페이지, Nav창에서 모두 접근 가능 |
|  |  | 2. 키워드 검색을 통한 실내/실외 운동 영상 검색 |  |  |
|  |  | 3. 날씨에 따른 미리보기 영상 표시 |  | 실내/야외 |
| 기능 | 인증게시판 | 1. 글 작성 |  | 이미지 파일 업로드 기능 |
|  |  | 1-1. 이미지 파일 업로드 기능 |  |  |
|  |  | 1-2. 자동 닉네임 등록 |  | 로그인한 사용자 아이디가 닉네임 |
|  |  | 1-3 글 작성시 알 개수 증가 |  |  |
|  |  | 1-4. 로그인해야만 글 작성 가능 |  | 라우터가드 |
|  |  | 2. 글 조회 |  | 사진 위주의 운동 인증 게시판 구성 |
|  |  | 3. 글 수정 |  | 글 수정 시 닉네임 수정 불가능 |
|  |  | 4. 글 삭제 |  | 글 삭제는 작성자만 가능 |
|  |  | 5. 글 검색 |  |  |
|  |  | 5-1. 친구 글 검색해서 조회 |  |  |
| 기능 | 친구 탭 | 1. 친구 목록 조회 |  | 이름, 알 개수, 최근 운동 시각 |
|  |  | 2. 친구 추가 |  |  |
|  |  | 3. 친구 삭제 |  |  |
|  |  | 4. 친구 검색 |  | 아이디, 닉네임으로 검색해서 친구 추가 가능 |
|  |  | 5. 로그인해야만 친구탭 조회 가능 |  | 라우터가드 |

유즈케이스 다이어그램
- 테이블 구조도(ERD)
- 클래스 다이어그램

### 개발 일정 :

[제목 없는 데이터베이스](https://www.notion.so/7cd4a1f8f87447bcb85d60e45a15f78d?pvs=21)

## 구현 결과

### 주요 기능과 화면

- **회원 가입**
    - 두 비밀번호가 일치할 때만 회원가입이 가능하도록 비밀번호 재확인 기능이 제공됩니다.
    - 입력한 비밀번호를 숨기기/보이기 버튼으로 실시간으로 확인할 수 있습니다.
    - 유효한 날짜가 아니면 회원가입이 불가능합니다.
    - 아이디, 유효한 비밀번호, 이름, 유효한 날짜를 입력했을 때 회원가입 버튼이 활성화되며, 성공 시 로그인 페이지로 이동합니다.
    - 비밀번호는 해싱된 값으로 서버에 저장되어 보안성이 강화됩니다.
    
- **메인페이지(로그인 전)**
    - 아이디와 비밀번호를 입력받아 로그인을 진행합니다.
    - 입력한 비밀번호를 숨기기/보이기 버튼으로 실시간으로 확인할 수 있습니다.
    - 로그인 시 입력한 비밀번호는 서버에 저장된 해싱된 비밀번호값과 비교하여 일치 여부를 확인합니다.

- **메인페이지(로그인 후)**
    - 날씨 API를 활용하여 현재 위치의 실시간 기온 정보를 가져옵니다.
    - 현재 기온에 따라 실외/실내 운동 장소와 운동 영상을 추천합니다.
    - 추울 경우, 실내 운동하기 좋은 장소를 지도로 표시하고 해당 지역의 영상을 제공합니다.
    - 날씨가 야외 운동에 적합하면 야외 운동 장소와 영상을 추천합니다.
    - 캐로셀 기능을 활용하여 한 화면에서 지도와 영상을 전환이 가능합니다.
    - 처음부터 다양한 선택지를 보여주기보다 적은 선택지를 보여줌으로써, 시작에 대한 부담감을 줄여줍니다.
    - 화면 상단에는 항상 자신이 모은 알과 최근 운동시간을 확인할 수 있습니다.
    
- **지도(카카오맵 지도) & 영상(유튜브)**
    - 이 기능은 메인 홈에서 들어갈 수도 있고, nav바를 통해 따로 들어갈 수도 있습니다.
    - '더 많은 장소' 및 '더 많은 영상'을 제공하여 선택지를 확장합니다.
    - 지도와 영상 탭에서는 검색창뿐만 아니라 주변 추천 키워드를 배치하여 사용자 부담을 최소화합니다.
    
    **지도:**
    
    - 카카오맵 API를 활용하여 현재 위치와 검색한 장소를 지도에 표시합니다.
    - 장소는 리스트로 확인 가능하며, 페이지네이션을 통해 편리하게 넘길 수 있습니다.
    
    **영상:**
    
    - 유튜브 API를 이용하여 영상 정보를 제공합니다.
    - 키워드 검색을 통해 실내/실외 운동을 검색할 수 있습니다.
    - 검색 전에 날씨에 따라 기본적으로 실내/실외 운동 영상이 나타나 있습니다.
    
- **인증 게시판**
    - 사진 위주로 구성된 게시판으로, 오늘의 운동 인증 사진을 올릴 수 있습니다.
    - 운동 인증 게시판에 글을 올리면 알을 하나 받습니다.
    - 운동 인증 게시판에서 다양한 운동 인증 사진을 확인할 수 있어 운동 동기부여를 높입니다.
    - 게시글 등록, 수정, 삭제, 검색이 가능하며, 이미지 파일 업로드가 지원됩니다.
    - 글 등록 시 로그인된 이용자의 이름으로 자동 닉네임 등록됩니다.
    - 글 등록은 로그인을 해야만 가능합니다. 로그인 하지 않고 글 등록 버튼을 누르면 로그인요청 모달창이 뜨며, 로그인 페이지로 이동할 것인지 여부를 묻습니다. 확인 버튼을 누르면 로그인 페이지로 이동하며, 취소 버튼을 누르면 현재 페이지가 유지됩니다.
    - 글 수정 시 닉네임은 변경할 수 없습니다.
    - 글 삭제는 로그인한 이용자가 작성한 글에 대해서만 가능합니다.
    
- **친구 탭**
    - 로그인 후에만 조회 가능하며, 조회 시 로그인 요청 모달창이 나타납니다.
    - 친구의 알 개수와 최근 운동시간을 확인할 수 있습니다.
    - 친구 추가 및 삭제가 가능하며, 검색을 통해 아이디/닉네임으로 추가할 수 있습니다.
    - 친구 추가는 상대방이 회원가입한 이용자일 때만 가능합니다.
    

### 사이트 적용 핵심 알고리즘

**적용 API**

날씨 API (기상청 단기예보 API)

카카오맵 API

유튜브 API

**핵심 알고리즘**

라우터가드 

JWT, 비밀번호 암호화

### 기대효과

운동 동기 부여 홈페이지 EGG의 기대효과는 크게 이용자, 개발자, 그리고 기업 관점으로 나눌 수 있습니다.

**1. 이용자 관점:**

- **운동 시작의 용이성:** 초심자부터 중급자까지 누구나 쉽게 운동을 시작할 수 있어, 꾸준한 운동과 체력 향상을 기대할 수 있습니다.
- **맞춤형 운동 추천:**  운동 영상 및 장소를 추천하고, 친구와의 알 개수 경쟁을 통해 동기 부여를 높입니다.

**2. 개발자 관점:**

- **확장성:**  다른 운동 관련 플랫폼과의 연동을 통해 확장성을 기대할 수 있습니다.
- **개인화된 기능 추가:** 새로운 기능 및 서비스를 지속적으로 추가하여 사용자 경험을 향상시키고 사용자들에게 새로운 가치를 제공할 수 있습니다.

**3. 기업 관점:**

- **관련 사업과의 연계:** 관련 운동 사업과의 협업을 통해 건강기능식품, 운동 기구 등을 판매하는 판매사이트를 추천하거나 헬스장 광고를 통해 수익 모델을 다각화할 수 있습니다.
- **광고 효율성:** 운동을 시작하는 이용자들에게 제품 및 서비스를 소개하는 광고가 효율적으로 전달될 수 있습니다.

