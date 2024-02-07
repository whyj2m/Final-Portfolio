
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



 # 1. 지도 SVG 활용 및 클릭이벤트
-- SVG 이미지를 컴포넌트로 만들어서 클릭 이벤트를 넣고 CSS 효과까지 넣는 부분이 새롭고 신기하였습니다.
      초기에 지도를 어떻게 넣을 것이며 사용자에게 보여줄지 고민하다가 이와 같이 구현하였습니다.

   - ### 지역 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/cc9417e2-2b3c-4c57-820e-dddcb42ca9ef)
     
     ### 관광지 페이지 
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/258cc78e-0d82-47ac-94ab-456d12f6dd0f)

     ### 축제 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/d3727eda-02d7-450a-a966-7d9cfa042ac1)

     ### 검색 페이지
     ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/06a8327f-9650-4cbc-8464-147e5937ea84)



# 2. 채팅
   ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/1c1f0fd3-0ee3-4896-bf8e-d7a59bd7e4fc)
   ![image](https://github.com/whyj2m/Final-Portfolio/assets/149341808/ac84d03d-f91f-4ba6-bdcf-fe352f4c3501)

-- WebSocket을 사용하여 DB 연동 없이 세션에서 관리되는 채팅 컴포넌트를 구현하였습니다. 초기에 채팅을 어떻게 구현해야 하나 막막했는데 참고할 자료들이 생각보다 많았고,
   WebSocket과 세션 그리고 실시간으로 채팅이 진행되는 부분을 이해하는 게 재밌었습니다.


# 파이널 프로젝트 총평
-
프로젝트에서는 초기에 프론트엔드를 담당하여 메인페이지 및 컨텐츠 페이지를 구상하였고 React Router를 활용하여 비동기 방식의 페이지를 구현하려고 노력하였습니다. 이후 후반부에 채팅 기능 및 검색 기능,
그리고 좋아요 기능을 추가로 담당하였으며 Spring을 이용하여 백엔드 로직을 처리하였습니다. 또한 배포 시에는 AWS EC2를 사용하여 프로젝트를 배포하였습니다. Spring은 tomcat10 버전을 EC2 Linux에 설치하여 진행하였고,
React 부분은 nginx를 사용하여 배포하였습니다. 처음 경험하는 프로젝트 및 배포였지만 개발에 대한 흥미와 재미를 느낄 수 있었습니다. 이 경험을 통해 많은 것을 배우고 성장할 수 있었던 소중한 시간이었습니다.

파이널 프로젝트에서는 처음으로 팀장이라는 자리를 맡아서 팀원분들과 소통하고 화합하며 진행하였습니다.
팀원이 아닌 팀장으로서 프로젝트를 경험하며 어려움도 있었고 부족함도 많았지만 서로 존중하고 배려하며 진행하니 좋은 결과가 나온 것 같습니다. 
함께 노력한 모든 팀원들에게 정말 감사했습니다.

## 링크
  ( https 도메인으로 배포 후 수정)
