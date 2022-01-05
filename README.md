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