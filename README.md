
# 1. 프로젝트명
   - 새하마노 방방곡곡 (대한민국 소개 사이트)

# 2. 개발기간
  - 2023.12.05 ~ 2024.01.16 // + 2024.01.23 ~ 31 (추가개발)
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

 - ### 메인페이지 (랜덤호텔목록) 
![image](https://github.com/whyj2m/Semi-Portfolio/assets/149341808/9fb53fbd-2a36-404b-92f7-28e764561f9a)

 1. 쿼리를 쓰면서 어려움
   - 랜덤으로 8개의 지역상관X 호텔을 불러오는 쿼리를 짜려고 기획했었다. 그런데 XML에서 프론트로 처리하려고 했으나 어려움이 있었고 선생님께 질문을 했었는데 SQL쿼리문으로 해결이 가능하다고 하셔서
      도움을 받아 쿼리를 작성하였습니다.

     ```java
      <select id="list" resultType="map">
		select anos ano, hno, hotelname, cdesc, price 
        from (
            select min(ano) as anos, hno, hotelname, cdesc, null price
            from hotel
            join area using(ano)
            where ano is not null group by ano order by 1
        ) a
        union
        select * 
        from (
        select ano, hno, hotelname, cdesc, roomprice
            from room 
            join hotel 
            using(hno) 
            join area 
            using(ano) 
            order by rand() 
            limit 8
        ) a
	</select>
-쿼리를 작성하면서 연산자 union, join, limit를 사용하여 서브 쿼리끼리 연결하고 하나의 쿼리로 만들어서 Mapper로 사용했던 부분이 너무 신기하고 쿼리로 이렇게 데이터 처리를 할 수 있구나! 신기했던 부분이었습니다.

   - ### 호텔목록 페이지
     ![image](https://github.com/whyj2m/Semi-Portfolio/assets/149341808/e0a02916-80f9-4d63-a76e-2f2a8af3be7f)

     ### 호텔 상세페이지
     ![image](https://github.com/whyj2m/Semi-Portfolio/assets/149341808/ee78d0cd-ffc3-4200-a843-a0a27aa2479c)

     ![image](https://github.com/whyj2m/Semi-Portfolio/assets/149341808/93e9fc85-5f2c-411c-aecc-881157bebe93)

     ### 댓글
     ![image](https://github.com/whyj2m/Semi-Portfolio/assets/149341808/06d43810-6d5a-4e44-a7aa-6e14492002ac)

-- 호텔목록 및 상세페이지는 특별한 쿼리없이 select 와 from 을 사용하여 테이블 데이터를 불러왔었습니다. 조금 신기했던 부분은 필요한 데이터만 뽑아오려고한쿼리가
```java
<select id="getList" resultType="com.imfreepass.prj.domain.HotelVO">
		select * from (
		    select 
		        (select count(*) from reply where hno = h.hno) replyCnt,
		        (select coalesce(round(AVG(replypoint)), 0) from reply where hno = h.hno) avg,					<!-- coalesce는 avg - null일떄 0으로 변환  -->
		        (select min(roomprice) from room where hno = h.hno) price,
		        h. *
		    from hotel h
		    where ano = #{ano}
		) a
		where price is not null
	</select>
```
- 여기서 호텔 목록에서 호텔 상세페이지 및 댓글 데이터를 불러와서 목록에서 보여줘야 했는데 replyCnt, avg, price 같은 하위 컬럼 데이터들을 저렇게 엮어서 HotelVO에 매핑해서 DB 테이블에 없는 데이터도 저렇게 보여줄 수 있단 게 정말 신기했습니다. 구조를 완벽하지는 않지만
이렇게 하나 둘 알아간다는 게 정말 좋았습니다.


# 세미 프로젝트 총평
-- 개발 언어 측면에서는 프론트 에서 JSP, CSS, JavaScript를 사용하였고, 백엔드에서는 Jsoup 라이브러리와 Java, JavaScript을 활용했습니다. 
	서버로는 Apache Tomcat 9.0를 선택하고, 데이터베이스로는 MariaDB를 사용했습니다. Eclipse를 개발 툴로 활용해  MVC 패턴2를 적용하여 프로젝트를 구성했습니다.
	특히, 데이터 크롤링, 메인페이지의 호텔 목록 및 호텔 객실 페이지, 그리고 댓글 서비스를 주로 맡아 구현했습니다. 
	데이터베이스 처리와 관련하여, 쿼리 작성 및 서브 쿼리를 통한 데이터 가공에 대한 경험은 정말 신기했습니다. 특히, HotelVO 클래스에 쿼리 결과를 매핑하여 DB 테이블에 없는 데이터도 효과적으로 보여줄 수 있었던 부분은 새로운 발견이었습니다
	또한 팀원 분들과 의사소통을 Discord로 주기적으로 하며 진행 현황을 공유하였고 정말 프로젝트 같은 의미 있는 경험이었습니다. 혼자보다 같이 협력하여 진행하니 좋았습니다. 그렇지만 애로사항도 있었습니다. 의견 충돌이나 그런 부분에서는 서로 양보하며
	타협점을 잡아 큰 소란 없이 진행된 게 정말 다행이었습니다. 조화로웠던 팀원분들과 1달 동안 고맙고 감사한 프로젝트였습니다 재밌었습니다!
##  *시연영상(유튜브)

- [제주어때 시연영상](https://youtube.com)
