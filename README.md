# JPA_study

* jpa 의 장점 

     1. SQL을 직접 작성하지 않아도 사용할 수 있다.
     2. SQL자동화



* 목표 1 : 객체와 테이블 설계 매핑
    - 객체와 테이블을 제대로 설계하고 매핑하는 방법
    - 기본 키와 외래 키 매핑
    - 1:N, N:1, 1:1, N:M 매핑


* 목표 2 : JPA 내부 동작 방식 이해
    - JPA의 내부 동작 방식 이해
    - JPA내부 동작 방식을 그림자 코드로 설명
    - JPA가 SQL을 어떻게 만들어 내는지
    - JPA가 언제 SQL을 실행하는지


## 1차시

### JPA와 Modern Java Data Store Tech

[SQL 중심적인 개발의 문제점](/[text]())

1. SQL 중심적인 개발의 문제점
    - 객체를 관계형 DB에 저장하는 시대
    - 객체지향적 언어를 쓰더라도 결국 DB를 사용하기 위해서 SQL만 무한 반복하는 것
    - CRUD 시, table 의 Column 하나만 바꿔도 모든 CRUD 연산을 바꾸는 것
    - 이런 원인 : 패러다임의 불일치
    - 객체와 관계형 데이터베이스의 차이
        1. 상속
            * 개체 상속 vs Table 슈퍼타입
            슈퍼타입으로 생성 시, Insert 두번, Select는 여러 테이블의 Join 을 통해서 조회해야하는 문제
            * 객체 지향으로 했으면 list에 저장후 get으로 꺼내면 되는데 너무 불편함
            * 객체는 자유롭게 객체 그래프를 탐색할 수 있어야 한다.
            >> 계층형 아키텍쳐, 진정한 의미의 계층 분할이 어렵다.
        2. 연관관계
            자바(getinstance) vs db(join)
        3. 데이터 타입
        4. 데이터 식별 타입 
2. JPA소개
    - DB를 java의 collection 처럼 불러오고 받아올 수 있는 방법..
    - JPA : JAVA Persistance API
    - 자바 진영의 ORM 기술 표준

* ORM이란 ? 
    - Object- Relational Mapping (객체 관계 매핑)
    - 객체는 객체대로 설계
    - 관계형 데이터베이스는 관계형 데이터 베이스대로 설계
    - ORM 프레임워크가 중간에서 매핑


* JPA는 애플리케이션과 JDBC사이에서 동작
<img alt="image1" src ="https://github.com/jay-one11/JPA_study/blob/5cdb23ba893bd9d0631e980ea790fc21a683c1f0/image/JPA%EA%B5%AC%EC%A1%B0.PNG">

* JPA 동작
    1. 저장
        <img alt="JPA-저장" src="">
        - JPA는 JDBC API를 활용하여 DB와 통신
    2. 조회
        <img alt="JPA-조회" src="">
        - JPA는 column 의 mapping 정보를 바탕으로 조회, ResultSet을 DB에서 얻어옴.

* JPA 소개
    <img alt="JPA소개" src="">
    - JPA는 인터페이스의 모음
    - 하이버네이트, EclipseLink, DataNucleus
    - JPA 2.2 사용

* JPA를 왜 사용해야 하는가?
    - SQL중심적인 개발에서 객체 중심으로 개발
    - 생산성
    - 유지보수
    - 패러다임의 불일치 해결 ⭐⭐
    - 성능
    - 데이터 접근 추상화와 벤더 독립성
    - 표준

1. 생산성
    - 저장 : jpa.persist(member)
    - 조회 : Memeber member = jpa.find(memberid)
    - 수정 : member.setName("변경할 이름")
        >> 다른 조건 없이 스스로 JPA가 찾아서 바꿔줌 💙 
    - 삭제 : jpa.remove(member)

2. 유지보수
    - <img alt="유지보수" src="">
    - 기존 : 필드 변경 시 모든 SQL 수정
    - <img alt="유지보수2" src="" >
    - JPA : 필드만 추가하면 알아서 JPA가 SQL문까지 수정

3. JPA와 패러다임 불일치 해결
    1. JPA와 상속
    <img alt="패러다임-상속" src="">
    <img alt="패러다임-저장" src="">
        - : JPA가 알아서 Query 여러개로 쪼개준다.
    <img alt="패러다임-조회" src="">
        - : JPA가 알아서 Join까지 QUery로 가져온다.

    2. 연관관계 저장
    <img alt="연관관계, 객체 그래프 탐색" src="">
        - 마치 java collection을 사용하는 것처럼 편하게 사용할 수 있다.

    3. 신뢰할 수 있는 엔티티, 계층
    <img alt="신뢰-엔티티-계층" src="">

    4. JPA와 비교하기
    <img alt="JPA와 비교하기" src="">
        * 🌟동일한 트랜잭션에서 조회한 엔티티는 같은 객체임을 보장

4. JPA의 성능 최적화 기능
    1. 1차 캐시와 동일성 보장
        1. 같은 트랜잭션 안에서는 같은 엔티티를 반환 - 약간의 조회 성능 향상
            - 캐싱 기능을 통해서 기존의 값 다시 제공
        2. DB Isolation Level 이 Read Commit 이어도 Application에서 Repeatable Read 보장
        <img alt="DB-Isolation" src="">
    2. 트랜잭션을 지원하는 쓰기 지연
        - 버퍼링 기능
        1. Insert
            1. 트랜잭션을 Commit할 때까지 Insert Sql을 모음
            2. JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송
            3. Commit 하는 순간 한번에 보낸다.
            <img alt="쓰기지연 ISERT" src="">
        2. Update
            <img alt="쓰기지연 Update" src="">
    3. 지연 로딩
        - 지연 로딩 : 객체가 실제 사용될 때 로딩
        - 즉시 로딩 : JOIN SQL로 한번에 연관된 객체끼리 미리 조회
        <img alt="지연,즉시로딩" src="">

* ORM 은 객체와 RDB 두 기둥위에 있는 기술..
    - 그래도 db 기술은 생명이 길기 때문에 관계형 db 기술도 소홀히하지 맙시다..