
# 1. 프로젝트명
   - 새하마노 방방곡곡 (대한민국 소개 사이트)

# 2. 개발기간
  - 2023.12.05 ~ 2024.01.16 ::  + 2024.01.23 ~ 31 (추가개발)
## 2-1 팀원
 - 윤주민,백승현,백혜윤

> ## 사용 기술 스택
>  - FE(Front End)
>    - React
>    - HTML
>    - CSS
>    - Javascript
>    
>  - BE(Back-End)
>    - Language: Java, JavaScript
>    - Database
>      - MariaDB
>    - Developer tool
>      - Spring 4.4 (STS - Spring Tool Suite)
>
>
>>  ### 사용한 오픈 API
>  - Swipper
>  - BootStrap
>  - OpenWeatherAPI
>  - KakaoMap
>
>> ### 배포 환경
>  - AWS EC2 (Elastic Compute Cloud)를 사용하여 프로젝트를 배포함
<br><br>

##  *프로젝트에서 나의 역할
 - 팀장으로서 팀원분들과 화합하고 의사소통하며 제가 맡은 메인 페이지 및 각종 소개 페이지 채팅 및 검색을 구현하였습니다.

 - ### 메인페이지
![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/24be80a3-3156-4047-a4e7-c7883ac93b83)
![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/0d68b73b-c739-46d6-bc17-d0e7c5f0a7f9)



 1. 메인페이지 지도 SVG 활용 및 클릭이벤트
 ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/d242cf9c-3deb-443b-88e7-cb3112146a51)

-- SVG 이미지를 컴포넌트로 만들어서 클릭 이벤트를 넣고 CSS 효과까지 넣는 부분이 새롭고 신기하였습니다. 초기에 지도를 어떻게 넣을 것이며 사용자에게 보여줄지 고민하다가 이와 같이 구현하였습니다.

   - ### 지역 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/cc9417e2-2b3c-4c57-820e-dddcb42ca9ef)
     
     ### 관광지 페이지 
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/258cc78e-0d82-47ac-94ab-456d12f6dd0f)

     ### 축제 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/d3727eda-02d7-450a-a966-7d9cfa042ac1)

     ### 검색 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/06a8327f-9650-4cbc-8464-147e5937ea84)



2. 채팅
   ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/b98605e1-9bfb-424e-935c-2406350f2c36)
   ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/1c1f0fd3-0ee3-4896-bf8e-d7a59bd7e4fc)
   ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/ac84d03d-f91f-4ba6-bdcf-fe352f4c3501)

-- WebSocket을 사용하여 DB 연동 없이 세션에서 관리되는 채팅 컴포넌트를 구현하였습니다. 초기에 채팅을 어떻게 구현해야 하나 막막했는데 참고할 자료들이 생각보다 많았고 WebSocket과 세션 그리고 실시간으로 채팅이 진행되는 부분을 이해하는 게 재밌었습니다.


# 세미 프로젝트 총평
-- 개발 언어 측면에서는 프론트 에서 JSP, CSS, JavaScript를 사용하였고, 백엔드에서는 Jsoup 라이브러리와 Java, JavaScript을 활용했습니다. 
	서버로는 Apache Tomcat 9.0를 선택하고, 데이터베이스로는 MariaDB를 사용했습니다. Eclipse를 개발 툴로 활용해  MVC 패턴2를 적용하여 프로젝트를 구성했습니다.
	특히, 데이터 크롤링, 메인페이지의 호텔 목록 및 호텔 객실 페이지, 그리고 댓글 서비스를 주로 맡아 구현했습니다. 
	데이터베이스 처리와 관련하여, 쿼리 작성 및 서브 쿼리를 통한 데이터 가공에 대한 경험은 정말 신기했습니다. 특히, HotelVO 클래스에 쿼리 결과를 매핑하여 DB 테이블에 없는 데이터도 효과적으로 보여줄 수 있었던 부분은 새로운 발견이었습니다
	또한 팀원 분들과 의사소통을 Discord로 주기적으로 하며 진행 현황을 공유하였고 정말 프로젝트 같은 의미 있는 경험이었습니다. 혼자보다 같이 협력하여 진행하니 좋았습니다. 그렇지만 애로사항도 있었습니다. 의견 충돌이나 그런 부분에서는 서로 양보하며
	타협점을 잡아 큰 소란 없이 진행된 게 정말 다행이었습니다. 조화로웠던 팀원분들과 1달 동안 고맙고 감사한 프로젝트였습니다 재밌었습니다!
##  *시연영상(유튜브)

- [제주어때 시연영상](https://youtube.com)
