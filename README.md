# JPA_study

## 목차
[1차시 : JPA소개](#1차시-JPA소개) ✔ <br/>
[2차시 : JPA 시작하기](#2차시-JPA-시작하기) ✔ <br/>
[3차시 : 영속성 관리 - 내부 동작 방식](#3차시-영속성-관리---내부-동작-방식) ✔ <br/> 
[4차시 : 엔티티 매핑](#4차시-Entity-Mapping) ✔ Now !  <br/> 
    >> [실전 예제 연습 1](/JPA_Practice1) <br/>
[5차시 : 연관관계 매핑 기초](#5차시-연관관계-매핑-기초) <br/>
[6차시 : 다양한 연관관계 매핑](#6차시-다양한-연관관계-매핑) <br/>
[7차시 : 고급 매핑](#7차시-고급-매핑) <br/>
[8차시 : 프록시와 연관관계 관리](#8차시-프록시와-연관관계-관리) <br/>
[9차시 : 값 타입](#9차시-값-타입) <br/>
[10차시 : 객체지향 쿼리 언어1](#10차시-객체지향-쿼리-언어1) <br/>
[11차시 : 객체지향 쿼리 언어2](#11차시-객체지향-쿼리-언어2) <br/>
`



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


## 1차시 JPA소개

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
        <img alt="JPA-저장" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EB%8F%99%EC%9E%91-%EC%A0%80%EC%9E%A5.PNG">
        - JPA는 JDBC API를 활용하여 DB와 통신
    2. 조회
        <img alt="JPA-조회" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EB%8F%99%EC%9E%91-%EC%A1%B0%ED%9A%8C.PNG">
        - JPA는 column 의 mapping 정보를 바탕으로 조회, ResultSet을 DB에서 얻어옴.

* JPA 소개
    <img alt="JPA소개" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EC%86%8C%EA%B0%9C.PNG">
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
    - <img alt="유지보수" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EC%9C%A0%EC%A7%80%EB%B3%B4%EC%88%981.PNG">
    - 기존 : 필드 변경 시 모든 SQL 수정
    - <img alt="유지보수2" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EC%9C%A0%EC%A7%80%EB%B3%B4%EC%88%982.PNG" >
    - JPA : 필드만 추가하면 알아서 JPA가 SQL문까지 수정

3. JPA와 패러다임 불일치 해결
    1. JPA와 상속
    <img alt="패러다임-상속" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%ED%8C%A8%EB%9F%AC%EB%8B%A4%EC%9E%84-%EC%83%81%EC%86%8D1.PNG">
    <img alt="패러다임-저장" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%ED%8C%A8%EB%9F%AC%EB%8B%A4%EC%9E%84-%EC%A0%80%EC%9E%A5.PNG">
        - : JPA가 알아서 Query 여러개로 쪼개준다.
    <img alt="패러다임-조회" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%ED%8C%A8%EB%9F%AC%EB%8B%A4%EC%9E%84-%EC%A1%B0%ED%9A%8C.PNG">
        - : JPA가 알아서 Join까지 QUery로 가져온다.

    2. 연관관계 저장
    <img alt="연관관계, 객체 그래프 탐색" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84-%EA%B0%9D%EC%B2%B4.PNG">
        - 마치 java collection을 사용하는 것처럼 편하게 사용할 수 있다.

    3. 신뢰할 수 있는 엔티티, 계층
    <img alt="신뢰-엔티티-계층" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%8B%A0%EB%A2%B0-%EC%97%94%ED%8B%B0%ED%8B%B0.PNG">

    4. JPA와 비교하기
    <img alt="JPA와 비교하기" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/JPA%EC%99%80%20%EB%B9%84%EA%B5%90%ED%95%98%EA%B8%B0.PNG">
        * 🌟동일한 트랜잭션에서 조회한 엔티티는 같은 객체임을 보장

4. JPA의 성능 최적화 기능
    1. 1차 캐시와 동일성 보장
        1. 같은 트랜잭션 안에서는 같은 엔티티를 반환 - 약간의 조회 성능 향상
            - 캐싱 기능을 통해서 기존의 값 다시 제공
        2. DB Isolation Level 이 Read Commit 이어도 Application에서 Repeatable Read 보장
        <img alt="DB-Isolation" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B0%EC%9E%A5%EC%A0%90.PNG">
    2. 트랜잭션을 지원하는 쓰기 지연
        - 버퍼링 기능
        1. Insert
            1. 트랜잭션을 Commit할 때까지 Insert Sql을 모음
            2. JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송
            3. Commit 하는 순간 한번에 보낸다.
            <img alt="쓰기지연 ISERT" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B0-INSERT.PNG">
        2. Update
            <img alt="쓰기지연 Update" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B0-UPDATE.PNG">
    3. 지연 로딩
        - 지연 로딩 : 객체가 실제 사용될 때 로딩
        - 즉시 로딩 : JOIN SQL로 한번에 연관된 객체끼리 미리 조회
        <img alt="지연,즉시로딩" src="https://github.com/jay-one11/JPA_study/blob/7a73078c117fb0608b70be23f489dd7d3f8febe5/image/%EC%A7%80%EC%97%B0,%EC%A6%89%EC%8B%9C%EB%A1%9C%EB%94%A9.PNG">

* ORM 은 객체와 RDB 두 기둥위에 있는 기술..
    - 그래도 db 기술은 생명이 길기 때문에 관계형 db 기술도 소홀히하지 맙시다..



## 2차시 JPA 시작하기
--- 
### 1. JPA 프로젝트 생성

* 필요 도구
    - h2 DATABASE (실습용 DB)
    - MAVEN or GRADLE (현재는 maven)
    - upper JAVA 8 version


1. 프로젝트 생성
    - new -> project -> maven 으로 추가

2. pom.xml 라이브러리 추가
    ```
    <?xml version="1.0" encoding="UTF-8"?> 
    <project xmlns="http://maven.apache.org/POM/4.0.0" 
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"> 
    <modelVersion>4.0.0</modelVersion> 
    <groupId>jpa-basic</groupId> 
    <artifactId>ex1-hello-jpa</artifactId> 
    <version>1.0.0</version> 
    <dependencies> 
    <!-- JPA 하이버네이트 --> 
    <dependency> 
    <groupId>org.hibernate</groupId> 
    <artifactId>hibernate-entitymanager</artifactId> 
    <version>5.3.10.Final</version> 
    </dependency> 
    <!-- H2 데이터베이스 --> 
    <dependency> 
    <groupId>com.h2database</groupId> 
    <artifactId>h2</artifactId> 
    <version>1.4.199</version> 
    </dependency> 
    </dependencies> 
    </project> 

    ```
    - 프로젝트 구성요소 ( maven, JPA hibernate, H2 db)등의 버전 설정
    - Hibernate는 Spring 사이트의 Hibernate 지원 버전을 꼭 확인하고 해당 버전 아래버전으로 설치하기!
    - 설치한 h2와 일치하게 적어주기

2. JPA설정하기 (persistance.xml)
    - src/main/resouces/META-INF 내에 persistance-unit.xml 생성
    - hibernate / javax 에 따라서 조금 씩 다름 ( 여기서는 hibernate )
    ```
    <persistence version="2.2" 
    xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"> 
    <persistence-unit name="hello"> 
    <properties> 
    <!-- 필수 속성 --> 
    <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/> 
    <property name="javax.persistence.jdbc.user" value="sa"/> 
    <property name="javax.persistence.jdbc.password" value=""/> 
    <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/test"/> 
    <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/> 
    
    <!-- 옵션 --> 
    <property name="hibernate.show_sql" value="true"/> 
    <property name="hibernate.format_sql" value="true"/> 
    <property name="hibernate.use_sql_comments" value="true"/> 
    <!--<property name="hibernate.hbm2ddl.auto" value="create" />--> 
    </properties> 
    </persistence-unit> 
    </persistence>
    ```

※ 데이터 베이스 방언
 <img alt="데이터베이스 방언" src="https://github.com/jay-one11/JPA_study/blob/a65cebbf1379aa1948e98678bbe2e02787cf19ed/image/JPA%EB%B0%A9%EC%96%B8.PNG">
 - hibernate를 통해 데이터베이스 방언 통역
 - `hibernate.dialect` 속성에 지정
    - H2 : org.hibernate.dialect.H2Dialect
    - oracle 10g : org.hibernate.dialect.Oracle10gDialect
    - Mysql : org.hibernate.dialect.MySQL5InnoDBDialect
 - Hibernate 는 40가지 이상의 데이터베이스 방언 지원 ( 사실상 모든 db 에서 사용 가능 )

---- 

### 2. JPA 구동 방식

<img alt="JPA구동 방식" src = "https://github.com/jay-one11/JPA_study/blob/a65cebbf1379aa1948e98678bbe2e02787cf19ed/image/JPA%EA%B5%AC%EB%8F%99%20%EB%B0%A9%EC%8B%9D.PNG">


1. DB 만들기
    - H2 console을 통해 H2 DB에 접속한다.
        - 접속 url : jdbc:h2:tcp://localhost/~/test
        - 처음 접속 시 : 
        jdbc:h2:~/test 로 url설정하면 db파일을 만들어준다.
        - 접속 후 Create문을 통하여 table을 생성한다.
        ```
        package hellojpa; 
        import javax.persistence.Entity; 
        import javax.persistence.Id; 
        @Entity 
        public class Member { 
        @Id 
        private Long id; 
        private String name; 
        //Getter, Setter … 
        }
        ```

2. Model 생성
    ```
    package hellojpa; 
    import javax.persistence.Entity; 
    import javax.persistence.Id; 
    @Entity 
    public class Member { 
    @Id 
    private Long id; 
    private String name; 
    //Getter, Setter … 
    }
    ```
    - `@Entity` JPA가 관리할 객체
    - `@iD` 데이터베이스의 Primary key 와 매핑
    - `@Table(name="Table명")` Dto와 DB의 table명이 다른 경우, 명시적으로 wiring 해주는 annotation
    - `@column(name="column명")` Dto의 멤버변수와 DB table의 column명이 다른 경우, 명시적으로 wiring 해주는 annotation
    - InteliJ의 경우, Alt + Insert를 통해서 getter, setter 자동생성 가능
    -     "         , Alt + enter를 통해서 빠른 완성기능 사용


3. CRUD연산

    0. 🌟 EntityManger 구현
        - Query를 수행하기 위한 EntityManager ( JDBC에서는 Dbconnection 과 같은 기능 )을 생성해야한다.
        - EntityManager는 EntityManagerFactory를 통해서 생성할 수 있다.
        - 🌟 EntityManageFactory는 DB당 하나만 생성해야한다. ( 2개 이상 시  혼선으로 인해 data 중복 또는 장애 가능)
        - 🌟 EntityManager는 단위 행동마다 하나씩 생성해야하며, 쓰레드 간의 공유를 절대 금지한다. (사용하고 바로 close 해야함 )
        - 🌟 Data의 모든 변경은 EntityTransaction 내에서 실행되어야 한다.
        - 단건 쿼리는 DB내부에서 자동으로 트랜잭션 처리를 하지만, 다건 쿼리에서는 장애를 유발할 수 있기 때문에 EntityTransaction 을 항상 사용하는 버릇을 가집시다.
        - 사용 예시
            ```
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // Hello 는 persistence.xml 내부의 persistence-unit의 name
            EntityManager em = emf.createEntityManager(); // EM 만들기
            EntityTransaction tx = em.getTransaction(); // tx 만들기
            tx.begin(); // 트랜잭션 시작

            try{
                CRUD연산
            }catch(Exception e ){
                tx.rollback();
            }
            finally{
                em.close // EntityManager close하기
            }
            
            emf.close(); // 모든 연산이 끝난 후 EntityManager close하기
            ```

    1. Insert
        - Insert는 `em.persist(DTO);` 의 형태로 쉽게 사용할 수 있다.
        - DTO를 선언 후, 변수를 set해준다음 persist를 통해 삽입한다.
        ```
            try{
                Member member = new Member();
                member.setId(1L);
                member.setName("Hello JPA");

                em.persist(member); 
            }

        ```
    
    2. Select
        - Select는 `em.find(DTO.class, 조건); ` 을 통해서 조회할 수 있다.
        ```
            Member findMember = em.find(Member.class, 1L);
        ```
    3. Delete
        - Delete는 `em.remove(DTO);`를 통해 삭제한다.
        ```
            em.remove(findMemeber);
        ```

    4. Update
        - 개인적으로 Update할 때 JPA의 장점이 가장 잘 드러나지 않았나 생각한다.
        - JPA는 Commit하는 시점에 table의 Update된 정보를 확인 후에 commit하도록 설정되어 있는데, 이 때문에 db에 직접 변경하지 않고, select한 정보를 setter를 통해 변경하여도 자동으로 DB에 반영되는 강력한 기능을 가지고 있다!
        ```
        Member findmember = em.find(Member.class, 1L);
        findmember.setName("Hello JPB?");
        ```
        - 🌟위와 같이 조회한 Member를 setter를 통해 변경했을 뿐인데 DB로 알아서 Update해준다🌟


### 3. JPQL
- 가장 단순한 조회 방법
- `em.createQuery(QueryString, dto.class).getResultList()` 를 사용하여 객체를 통한 조회를 할 수 있음.
    - 자동으로 dto의 column을 가지고 조회해준다. 
```
    ex 1) 
    List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
    for(Member member : result){
        System.out.println("member name : " + memeber.getName());
    }

    ex 2)
    List<Member> result = em.createQuery("select m from Member as m", Member.class)
    .setFirstResult(5)
    .setMaxResults(8)
    .getResultList();
```
- ex 2)와 같이 oracle mysql 과 같이 문법이 다른 경우에도 hibernate의 방언 통역 기능을 통해 자동으로 해당 언어에 맞게 번역해준다.
- 검색 조건이 포함된 Entity 객체를 대상으로 하는 SQL문을 통해 조회한다.
- createQuery의 QueryString에 여러 조건을 추가하여 세부 검색도 가능하다.


-----
## 3차시 영속성 관리 - 내부 동작 방식

### 1. 영속성 컨텍스트
- JPA에서 가장 중요한 2가지
    - 객체와 관계형 데이터베이스 매핑하기 (ORM)
    - 영속성 컨텍스트
        - JPA가 실제로 내부에서 어떻게 동작하는지 ?

- EntityManagerFactory, EntityManager
<img alt="Emf&EM" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/ENF&EM.PNG">
    - EMF 를 통해서 고객의 요청 시마다 EM을 생성
    - EM은 내부적으로 DBconnection을 통해서 DB 사용


- 영속성 컨텍스트
    - JPA를 이해하는데 가장 중요한 용어
    - "Entity를 영구 저장하는 환경"
    - `EnetityManager.persist(entity);` : Insert와 같음
        - 사실 DB에 저장한다는 것이 아니라, Entity를 영속성 컨텍스트에 저장한다는 뜻.. (깊은 내용)


- 영속성 매니저? 영속성 컨텍스트 ?
    - 영속성 컨텍스트는 논리적인 개념이다.
    - 눈에 보이지 않느다.
    - 엔티티 매니저를 통해서 영속성 컨텍스트에 접근한다.
        -  J2SE환경
            <img alt="J2SE" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/JS2E.PNG">
            - EM 생성하면 1:1로 영속성 컨텍스트가 생성된다.

- 엔티티의 생명 주기
    <img alt="Entity-lifecycle" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/Entity-lifeCycle.PNG">
    - 비영속 ( new / transient )
        : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
    - 양석 ( managed )
        : 영속성 컨텍스트에 관리되는 상태
    - 준영속 ( detached )
        : 영속성 컨텍스트에 저장되었다가 분리된 상태
    - 삭제 ( removed )
        : 삭제된 상태

    
- 비영속
    <img alt="new" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/new.PNG">
    - 멤버 객체를 생성하고, EM에 아무런 연결을 하지 않은 상태
    ```
    Member member = new Member();
    member.setId(1L);
    member.setName("Hello JPA");
    ```

- 영속
    <img alt="managed" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/managed.PNG">
    - 멤버 객체를 생성하고, EM를 생성해서, persist를 통해 영속성을 부여한 상태
    - 영속성 컨텍스트를 통해서 관리가 되는 시점
    ```
    Member member = new Member();
    member.setId(1L);
    member.setName("Hello JPA");

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    System.out.println("------before ------");
    em.persist(member); // 객체를 저장한 상태 ( 영속 )
    System.out.println(("----------after---------"));

    ```
    - 실행결과 : <img alt="before-after1" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/before-after2.PNG">
    - ⭐ DB에 저장되지는 않은 상태.
    - tx.commit 하는 순간 쿼리가 날아갑니다

- 준영속
    - 회원 엔티티를 컨텍스트에서 분리, 준영속 상태
    - ` em.detach(member);`

- 삭제
    - db에서 영구적으로 지우고 싶은 상황
    - `em.remove(member);`


- 영속성 컨텍스트의 장점
    - 쉽게 생각하면, 영속성 컨텍스트는 App과 DB사이의 중간계층 역할을 수행한다
    - 1차 캐싱 역할
    - 동일성 (identity )  보장
    - 트랜잭션을 지원하는 쓰기지연기능
    - 변경 감지
    - 지연 로딩


- 엔티티 조회, 1차 캐싱
    <img alt="엔티티조회" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/Entity-search.PNG">
    - `em.persist(member);`를 통해서 entity를 영속화 시키면, 영속 컨텍스트의 1차 캐시 내부에 entity가 담기게 된다.
    - 1차 캐시 내에 entity 는 PK - Entity 형태로 저장된다.
        - JPA는 `em.find()`를 수행할 때, 1차캐시를 먼저 탐색한다. 이 이때 PK와 일치하는 값이 있다면 바로 조회해온다 ( 성능 향상 )
        <img alt="1차캐시조회" src="https://github.com/jay-one11/JPA_study/blob/bfddf1673086baa34194c5df34e42fc19296f0ef/image/1%EC%B0%A8%20%EC%BA%90%EC%8B%9C%EC%97%90%EC%84%9C%20%EC%A1%B0%ED%9A%8C.PNG">
        - 만약 1차 캐시에 원하는 값이 없다면 DB에서 조회해서 1차캐시에 저장하고, 사용자에게 반환한다.
        <img alt="1차캐시조회2" src="https://github.com/jay-one11/JPA_study/blob/d130ce03c15b8f6d2e42a009663b4d147f155d9d/image/1%EC%B0%A8%20%EC%BA%90%EC%8B%9C%EC%97%90%EC%84%9C%20%EC%A1%B0%ED%9A%8C2.PNG">
    - 사실, 트랜잭션이 끝나면 EntityManager 또한 종료되기 때문에 매번 초기화 하는 특성 상 많은 성능 향상이 되진 않는다. (복잡한 비즈니스 로직에서 유용함)
    - 성능의 이점은 2차캐시에서 성능 향상!
    - Example 
    ```
    //  영속
    em.persist(member);
    Member findMember1 = em.find(Member.class, 111L);
    Member findMember2 = em.find(Member.class, 111L);
    System.out.println("findMember1 name  : " + findMember1.getName());
    System.out.println("findMember2 name : " + findMember2.getName());

    tx.commit();
    ```

    - 위와 같은 경우, 처음 find를 통해서 불러온 findmember1은 1차 캐시에 저장되기 때문에 findmember2를 불러올 때는  1차 캐시 내에 있는 값을 그대로 가져오기 때문에 query가 생성되지 않는다.
    <img alt="1차캐싱결과" src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/1%EC%B0%A8%EC%BA%90%EC%8B%B1%EC%A1%B0%ED%9A%8C-%EA%B2%B0%EA%B3%BC1.PNG">

    - 사실 성능적인 장점보다는 객체지향적인 컨셉적 이점이 크다.

- 영속 엔티티의 동일성 보장
    <img alt="영속엔티티의동일성보장" src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%EC%98%81%EC%86%8D%EC%84%B1%EC%97%94%ED%8B%B0%ED%8B%B0%EC%9D%98%EB%8F%99%EC%9D%BC%EC%84%B1%EB%B3%B4%EC%9E%A5.PNG">
    - JPA는 영속 엔티티의 동일성을 보장한다.
    - 위의 findMember1과 Findmember2는 서로 같은 객체라는 것을 보장한다.
    - 단, 같은 트랜잭션 내에서 실행 시 !

- 엔티티 등록 시 트랜잭션을 지원하는 쓰기 지연
    <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%EC%9D%84%EC%A7%80%EC%9B%90%ED%95%98%EB%8A%94%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B0.PNG" alt="트랜잭션을지원하는쓰기지연">
    - Commit하기 전까지 JPA에 Insert query를 저장했다가 , Commit을 하는 순간 DB에 해당 query를 보낸다.
     <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%EC%9D%84%EC%A7%80%EC%9B%90%ED%95%98%EB%8A%94%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B02.PNG" alt="트랜잭션을지원하는쓰기지연2">
     - 영속 컨텍스트 내의 "쓰기지연 SQL저장소"에 Insert SQL을 생성하고 1차 캐시에 저장한다.
     <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%ED%8A%B8%EB%9E%9C%EC%9E%AD%EC%85%98%EC%9D%84%EC%A7%80%EC%9B%90%ED%95%98%EB%8A%94%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B03.PNG" alt="트랜잭션을지원하는쓰기지연3">
     - Commit을 하는 순간 한번에 SQL문이 실행 ( Flush ) 되어 SQL query가 수행된다.
     - Example
     ```        
        Member member1 = new Member(150L, "A");
        Member member2 = new Member(160L, "B");


        em.persist(member1);
        em.persist(member2);
        System.out.println("=======================");
        tx.commit();
     ```
     - 결과 
     <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%EC%93%B0%EA%B8%B0%EC%A7%80%EC%97%B0%EA%B2%B0%EA%B3%BC.PNG" alt="쓰기지연결과">
     - 쓰기 지연의 장점
        - 옵션 하나로 성능을 향상시킬 수 있음.
        - buffer를 통해 한다는 점
     - `persistence.xml` 의 hibernate.jdbc.batch_size를 통해서 한번에 쓰기지연 할 수 있는 최대 개수를 지정할 수 있음.


- Entity 수정 / 변경 감지
    <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/%EC%97%94%ED%8B%B0%ED%8B%B0%EC%88%98%EC%A0%95-%EB%B3%80%EA%B2%BD%EA%B0%90%EC%A7%80.PNG" alt="엔티티수정변경감지">
    ```
    Member findMember1 = em.find(Member.class, 150L);
    findMember1.setName("zzzzz");

    System.out.println("=======================");
    tx.commit();
    ```
    - 결과
        - 수정 전
        <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/update%EC%A0%84.PNG" alt="엔티티수정전">
        - 수정 후
         <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/update%ED%9B%84.PNG" alt="엔티티수정후">
    
    - JPA는 변경 감지 기능으로 Entity Update 기능을 제공한다.
    - 마치 Collection을 사용하듯이 쉽게 변경할 수 있는 기능
    <img src="https://github.com/jay-one11/JPA_study/blob/b8212b112570411cb9c6d9f862ac3260a8012b18/image/DirtyChecking.PNG" alt="dirtychecking">
        - 1. 과정에서 Entity를 변경 후 commit을 하게되면
        - 2. 과정과 같이 Entity와 스냅샷(값을 읽어온 최초 시점의 Entity정보)을 비교
        - 3. Entity와 스냅샷의 값이 다르다면 쓰기지연 SQL저장소에 Update Query를 생성한다.
        - 4. flush를 통해 DB에 반영후 commit한다.

- Entity 삭제
    ```
        Member memberA = em.find(Member.class, "memberA");
        em.remove(memberA);
    ```

### 2. 플러시
- 영속성 컨텍스트 변경내용을 데이터베이스에 반영하는 작업
- 보통 Commit() 될때 영속성 컨텍스트 => DB로 Flush
- 플러시가 발생할 때 일어나는 일
    - 변경 감지 (dirty checking)
    - 수정된 엔티티 쓰기지연 SQL저장소에 등록
    - 쓰기지연 SQL저장소의 쿼리를 DB에 전송 (등록, 수정, 삭제)
- persistence Context를 Flush 하는 방법
    1. `em.flush()` : 직접 수동 호출 ( 거의 사용할 일 없음 / test용 )
        - ※ 원할 때 Query를 보낼 수 있음. ( 비추 , 테스트용 )
        - 1차 캐시는 유지되고 쓰기 지연 SQL저장소, 변경감지 된 엔티티들이 DB에 반영되는 과정
    2. `tx.commit()` : ⭐ flush 자동 호출 
    3. JPQL query 수행 : flush 자동 호출
        <img src="" alt="Flush-JPQL">
        - Member A,B,C 는 JPQL Query를 수행하는 시점에서 Commit이 되어있지 않기 때문에 DB에 반영되지 않았다.
        - 따라서 ` List<member> members` 는 빈 List가 되어진다.
        - ⭐ 이러한 오류를 방지하기 위해 JPA는 JPQL을 사용할 때 자동으로 commit을 수행하기 때문에 `List<member> members`에는 member A,B,C의 Entity가 정상적으로 저장되어질 수 있다 ⭐

- Flush Mode Option
    `em.setFlushMode(FlushModeType.COMMIT)`
    - `FlushModeType.AUTO` : Commit이나 QUery를 실행할 때 Flush(default)
    - `FlushModeType.COMMIT` : Commit할 시에만 Flush
        - 지금 가진 Query를 flush하지 않고 table을 조회하고 싶은 경우
        - 조회하고자 하는 Query에서 Flush할 Query가 의미 없는 경우

- ※ 플러시는 !
    - persistence Context를 비우지 않음
    - persistence Context의 변경 내용을 데이터베이스에 동기화
    - ⭐ Transaction이라는 작업단위가 중요 -> 커밋 직전에만 동기화 하면 됨!!


### 3. 준영속 상태 (Detached Status)

- 준영속 상태(Detached Status)란?
    - 영속 -> 준영속 된 상태
        - 비영속 -> 영속 상황 ( `persist` 또는 `find`를 통한 DB조회) 
    - 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(Detached)된 상태
    - 영속성 컨텍스트가 제공하는 기능을 사용 못함

- 준영속 상태로 만드는 방법
    1. em.detach(entity)
        - 특정 엔티티만 준영속 상태로 전환 ( 1차 캐시에서 제거 )
        - 개발 환경과 DB를 분리하고 싶을 때 사용
        ```
        Member findMember1 = em.find(Member.class, 150L);
        findMember1.setName("AAAAA"); // 이 시점에서 영속성컨텍스트에 올라감.

        em.detach(findMember1); // 영속성 컨텍스트에서 제외 ( JPA에서 관리 X )

        System.out.println("=======================");
        tx.commit(); // 저장된 DB에서는 AAAA로 변환 X, Update query X
        ```
        결과 
        ```
        Hibernate: 
        select
            member0_.id as id1_0_0_,
            member0_.name as name2_0_0_ 
        from
            Member member0_ 
        where
            member0_.id=?
        =======================
        ```
    2. em.clear()
        - 영속성 컨텍스트를 완전히 초기화 ( 1차 캐시를 clear )
        - testcase를 작성할 때 유용하다고 함
    3. em.close()
        - 영속성 컨텍스트를 종료
        - 1차 캐시 자체를 사용할 수 없음

------------

## 4차시 Entity Mapping

### 1. 객체와 테이블 매핑

1. 엔티티 매핑 소개
    - 객체와 테이블 매핑 : `@Entity` , `@Table`
    - 필드와 컬럼 매핑 : `@Column`
    - 기본 키 매핑 : `@Id`
    - 연관 관계 매핑 : `@ManyToOne`(1:N Mapping), `@JoinColumn`

<img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/Entity&Table%20example.PNG" alt="Entity&Table example1">

- `@Entity`
    - `@Entity`가 붙은 클래스는 JPA가 관리하며, '엔티티'라고 한다.
    - JPA를 사용해서 테이블과 매핑할 클래스는 `@Entity` 가 필수
    - 🚩 주의할 점 🚩
        - `@Entity`를 붙일 클래스는 반드시 기본 생성자가 있어야 한다.
        - final, enum, interface, inner 클래스 사용 ❌❌
        - 저장할 필드에 Final 사용 ❌
    - 속성 : Name
        - JPA에서 사용할 엔티티 이름을 지정한다.
        - 기본 값 : 클래스 이름을 그대로 사용 ( 예 : Member )
        - 같은 클래스 이름이 없으면 가급적 기본 값을 사용 ( 혼동 방지 )


- `@Table`
    - `@Table`은 엔티티와 매필할 테이블 지정
    <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/@table.PNG" alt="@Table">
    - 속성을 통해 name, catalog, schema 등을 매칭 가능 ( 추후에 다룸 )


### 2. Database Schema Auto Create

- 데이터베이스 스키마 자동 생성
    - DDL을 애플리케이션 실행 시점에 자동 생성
        - 객체를 매핑하면 스스로 테이블을 만들어 줌
    - 테이블 중심 -> 객체 중심
    - 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 생성
        - DDL이 생성 될 때 방언에 맞게 적절한 쿼리 생성
    - 이렇게 생성된 DDL은 개발 장비에서만 사용 ( 운영에서 사용 ❌)
    - 생성된 DDL은 운영 서버에서는 사용하지 않거나, 적절히 다듬은 후 사용

- 데이터 베이스 스키마 자동 생성 - 속성
    - `hibernate.hbm2ddl.auto`
        - persistence.xml 파일의 property중 하나,
        - `<property name="hibernate.hbm2ddl.auto" value="create" />`
        - value를 `create`로 설정 시
        ```
        Hibernate: 
            
            drop table Member if exists
        Hibernate: 
            
            create table Member (
            id bigint not null,
                age integer not null,
                name varchar(255),
                primary key (id)
            )
        ```
        - 위와 같이 drop table 후 create table을 수행한다. ( 테이블 초기화 후 수행 )
        - 빠르게 Table을 수정할 때 편리함
        <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4%20%EC%8A%A4%ED%82%A4%EB%A7%88%20%EC%9E%90%EB%8F%99%20%EC%83%9D%EC%84%B1%20-%20%EC%86%8D%EC%84%B1.PNG" alt="데이터베이스 스키마 자동 생성 - 속성">
        - update : Alter ( column 추가만 됨, 지우기 ❌ )
        - validate : 컬럼에 없을 시 `missing column` error 발생

- 데이터 베이스 스키마 자동 생성 - 실습
    - 스키마 자동 생성하기 설정
    - 스키마 자동 생성하기 실행, 옵션별 확인
    - 데이터베이스 방언 별로 달라지는 것 확인
        - `persistence.xml` 파일에서 ` <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>` 을 언어에 따라 변경하였을 때 저장되는 타입이 달라짐.

- 데이터베이스 스키마 자동 생성 - 주의
    - 🚩 운영 장비에는 절대 create, create-drop, update 사용 금지  🚩
    - 개발 초기에는 create 또는 update
    - 테스트 서버에는 update 또는 validate
    - 스테이징과 운영 서버에는 validate 또는 none
    - 🏴‍☠️ 운영 서버에서 DB의 Column을 직접 조작한다는 것은 엄청난 장애 초래 가능 가급적이면 직접으로 스크립트를 만든 후에 테스트 서버 확인 후 조작할 것!!!  🏴‍☠️


- DDL 생성 기능
    - 제약 조건 추가 : 회원 이름은 필수, 10자 초과 X
        - `@Column(nullable = false, length = 10)`
    - 유니크 제약 조건 추가 
        - `@Table(UniqueConstraints ={@UniqueConstraint(name = "Name_AGE_UNIQUE", columnNames={"Name","AGE"})})`
    - DDL의 생성 기능은 DDL을 자동 생성할 때만 사용 되고 JPA의 실행 로직에는 영향을 주지 않는다. (= Alter 가 한번 수행되는 것일 뿐이다. )


### 3. Field & Column Mapping 

- test case 요구사항 추가
    1. 회원은 일반 회원과 관리자로 구분해야 한다.
    2. 회원 가입일과 수정일이 있어야 한다.
    3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.



- Mapping Annotation 
    - `hibernate.hbm2ddl.auto`
    <img src="" alt="mapping Annotation">
    - `@Transient`를 사용하면, Java memory에서만 사용할 수 있고 DB에 반영하지 않음.
    - 예시 
    <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/Field&Column_code.PNG" alt="field&column code">
    <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/Field&Column_code_result.PNG" alt="field&column code_result">

- `@Colummn`

    <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/@Column.PNG" alt="@Column">
    - `insertable`, `updatable` : 삽입, 수정이 가능한지?
        - `@column(updateable=false)`하게 되면, 처음 삽입된 data에서 JPA를 통해서는 절대 변경되지 않음 (SQL을 통한 직접 변경은 가능)
    - `nullable` : `@Column(nullable = false)`하게 되면 NOT NULL 제약조건 추가
    - `Unique` : unique 명이 랜덤으로 생성되고, 여러 속성을 동시에 줄 수 없단 점에서 자주 사용하지 않음 ( Table 에서 제약조건 추가함 )
    - `ColumnDefinition` : 데이터베이스 컬럼 정보를 직접 줄 수 있다.
        - ex) `"varchar(100) default 'EMPTY'"` 
        - 필드의 자바 타입과 방언 정보를 사용해서 자동으로 반영 가능

- `@Enumerated`
    - 주의 ! ORDINAL 사용 ❌ String을 사용!!!⭐
        - ORDINAL 은 Enum의 idx를 DB에 저장하기 때문.. ( 개수가 늘어나면 오류 발생 가능성 ⬆ )
        - STRING 타입을 사용해서 명시적으로 저장하기
    - JAVA Enum 타입을 Mapping할 때 사용
    - ⭐ `@Enumerated(EnumType.STRING)` 을 사용합시다 ⭐
    <img src="https://github.com/jay-one11/JPA_study/blob/4697430837f1abdefd3195623435465f353c20c2/image/@Enumerated.PNG" alt="@enumerated">

- `@Temporal`
    - 날짜 타입을 매핑할 때 사용 ( ✔ 최근은 별로 안씀 ,, )
    - LocalDate, LocalDateTime 을 사용할 때는 아래와 같이 타입으로 생성 가능
        - ` private LocalDate TestTime; ` 
        - ` private LocalDateTime testDateTime; `

- `@Lob`
    - DB의 BLOB, CLOB Type과 Mapping
    - `@LOB`에는 지정할 수 있는 속성은 없음
    - Mapping 하는 Field Type이 문자면 CLOB, 나머지는 BLOB Mapping
        - CLOB : String, Char[], java.sql.CLOB
        - BLOB : byte[], java.sql.BLOB

- `@Transient`
    - Field - DB mapping ❌
    - DB에 저장, 조회 ❌❌
    - 메모리 상에서만 임시로 어떤 값을 보관하고 싶을 때만 사용
    ```
    @Transient
    private Integer tmp;
    ```

### 4. 기본 키 매핑

- 기본 키 매핑 어노테이션
    - `@Id`
        - 직접 PK를 할당할 때
    - `@GeneratedValue`
        - 특정한 조건을 통해 ID를 세팅하고 싶을 때 (자동 할당)
    ```
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    ```
    <br />
- 기본 키 매핑 방법
    - 직접 할당 : `@Id`만 사용
    - 자동 생성(`@GeneratedValue`)
        - IDENTITY : 데이터베이스에 위임, MYSQL
        - SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            - `@SequenceGenerator`필요
        - TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용
            - `@TableGenerator` 필요
        - AUTO : 방언에 따라 자동 지정 ( 기본 값 )

- IDENTITY 전략 - 특징 (`@generatedValue(Strategy = GenerationType.IDENTITY)`)
    - ⭐ 기본 키 생성을 데이터베이스에 위임
    - 주로 MYSQL, PorstgreSQL, SQL server, DB2에서 사용
        - ex) Mysql의 Auto_Increment
    - JPA는 보통 트랜잭션 커밋 시점에 INSERT SQL 실행
    - AUTO_INCREMENT는 데이터베이스에 INSERT SQL을 실행 한 후에 ID 값을 알 수 있음.
    - IDENTITY전략은 em.persist() 시점에 INSERT SQL 실행하고 DB에서 식별자를 조회
    - example
    ```
    <pesistence.xml>
    <property name="hibernate.hbm2ddl.auto" value="create" />

    <member.java>
    @Entity
    public class Member {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "name") // 객체는 username, DB는 name
        private String username;

        //Getter, Setter…
    }

    <JPA_Main.java>
     Member member = new Member();
            member.setUsername("jaewon");
            em.persist(member);
            tx.commit();
    ``` 
    - result
    ```
    Hibernate: 
    
    drop table Member if exists
    Hibernate: 
        
        create table Member (
        id bigint generated by default as identity,
            name varchar(255),
            primary key (id)
        )

    Hibernate: 
    /* insert hellojpa.Member
        */ insert 
        into
            Member
            (id, name) 
        values
            (null, ?)
    ```
    <img src="" alt="IDENTITY전략">
    - 위와 같이 IDENTITY를 Strategy로 사용하였을 때 Auto_Increment를 사용하는 것을 확인할 수 있다.
    - IDENTITY 전략은 INSERT를 하였을 때야 값을 확인할 수 있는 단점이 있다..
        - 이러한 단점은.. 영속성 컨텍스트에 PK - Entity로 저장된다는 특징
            - em.persist()가 호출한 시점에 바로 db Insert query를 보냄 (⭐ 쓰기 지연 X )
            - 바로 Insert query 후 영속성 컨텍스트에 해당 Entity 저장
- SQUENCE 전략 - 특징
    - Database Sequence는 유일한 값을 순서대로 생성하는 특별한 Database Object ( Ex) Oracle Sequence )
    - Oracle, PostgreSQL, DB2, H2 에서 사용
    - example
    ```
    <pesistence.xml>
    <property name="hibernate.hbm2ddl.auto" value="create" />

    <member.java>
    @Entity
    public class Member {
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;

        @Column(name = "name") // 객체는 username, DB는 name
        private String username;

        //Getter, Setter…
    }

    <JPA_Main.java>
     Member member = new Member();
            member.setUsername("jaewon");
            em.persist(member);
            tx.commit();
    ```
    - result
    ```
    Hibernate: 
    
    drop table Member if exists
    Hibernate: 
        
    drop sequence if exists hibernate_sequence
    Hibernate: create sequence hibernate_sequence start with 1 increment by 1
    Hibernate: 
        
        create table Member (
        id bigint not null,
            name varchar(255),
            primary key (id)
        )

    Hibernate: 
    call next value for hibernate_sequence
    Hibernate: 
        /* insert hellojpa.Member
            */ insert 
            into
                Member
                (name, id) 
            values
                (?, ?)
    ```
    - sequence에서 값을 가져온 다음 다음 값을 지정하는 방식
    - `em.persist()` 가 수행되면 Insert하기 전에 Sequence의 next_val값을 가져온 뒤 INSERT 를 수행한다.
<br />

- `@SequenceGenerator`
    - Sequence_generator를 사용하여 Sequence를 생성할 수도 있다.
    <img src="" alt="Sequence_generator">
    -  SequenceGenerator 속성
        - name : 식별자 생성기 이름 ( 필수 )
        - sequenceName : 데이터베이스에 등록되어 있는 Sequence 이름 (성능 최적화 )
            - 기본값 : hibernate_sequence
        - initialValue : DDL 생성시에만 사용됨, 시퀀스 DDL을 생성할 때 처음 1 시작하는 수를 지정한다.
            - 기본값 : 1
        - allocationSize : 시퀀스 한번 호출 때마다 증가하는 수 (🚩 1 추천)
            - ⭐ 기본값 : 50
            - 🚩 만약 allocationSize를 50을 한뒤 여러 entity를 저장한다면?
                ```
                em.persist(member1);
                em.persist(member2);
                em.persist(member3);
                ```
                - allocation size 가 50이지만, 처음 호출 할때 id =1, Sequence = 1, 두번 째 호출할 때 id=2, Sequence = 51, 세번째 호출할 때 id=3, Sequence = 3, ... 과 같은 방식으로 처음 호출 하였을 때 Sequence 값을 memory에 저장하여 내부적으로 id 값과 Sequence값을 비교하여 같아질 때까지 추가 호출을 하지 않는다. 
                - 이러한 기능은 Allocation size = 1일 때 한개의 persist마다 DB Sequence를 참조하여 값을 가져오는 방식 보다 효율적이다.
                - 하지만 Allocatoins size를 너무 크게 설정하면 서버의 예상치 못한 종료 시 Memory 누수가 생길 수 있으므로 50~100정도로 맞춰주는 것이 좋다.
        - catalog, schema : catalog, schema 이름

- TABLE 전략
    - 키 생성 전용 테이블을 하나 만들어서 Sequence를 흉내내는 전략
    - 장점 : 모든 DB에서 사용 가능
    - 단점 : 성능 저하
    - example
    ```
    <member.java>
    @Entity 
    @TableGenerator( 
    name = "MEMBER_SEQ_GENERATOR", 
    table = "MY_SEQUENCES", 
    pkColumnValue = “MEMBER_SEQ", allocationSize = 1) 
    public class Member { 
    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, 
    generator = "MEMBER_SEQ_GENERATOR") 
    private Long id;

    @Column(name = "name") // 객체는 username, DB는 name
        private String username;

        //Getter, Setter…
    }

    <JPA_Main.java>
    Member member = new Member();
    member.setUsername("jaewon");
    em.persist(member);
    tx.commit();

    ```
    - result
    ```
    Hibernate: 
        
        drop table hibernate_sequences if exists
    Hibernate: 
        
        drop table Member if exists
    Hibernate: 
        
        create table hibernate_sequences (
        sequence_name varchar(255) not null,
            next_val bigint,
            primary key (sequence_name)
        )
    Hibernate: 
        
        insert into hibernate_sequences(sequence_name, next_val) values ('default',0)
    Hibernate: 
        
        create table Member (
        id bigint not null,
            name varchar(255),
            primary key (id)
        )

    Hibernate: 
    select
        tbl.next_val 
    from
        hibernate_sequences tbl 
    where
        tbl.sequence_name=? for update
            
    Hibernate: 
        update
            hibernate_sequences 
        set
            next_val=?  
        where
            next_val=? 
            and sequence_name=?
    Hibernate: 
        /* insert hellojpa.Member
            */ insert 
            into
                Member
                (name, id) 
            values
                (?, ?)

    ```
    <img src="/image/TABLESEQUENCE_DB.png" alt="TABLESEQUENCE_DB">
    - 위처럼 Sequence들을 저장하는 table을 생성한다.
    - 운영에서 사용하기는 약간 부담스러운 부분 있음

- `@TableGenerator` 속성
    <img src="" alt="tableGenerator속성">

- 🚩 권장하는 식별자 전략
    - <b>기본 키 제약 조건</b> : null 아님, 유일, 변하면 안된다.
    - 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키 (대체 키)를 사용하기 ( 기본 키가 변하지 않는다 보장 어려움 )
        - ※ 대체키 : 기본키와 상관없는 어떤 값
    - 주민등록번호도 기본 키로 적절하지 않다..
    - ⭐⭐ <b>권장 : Long형 + 대체 키 + 키 생성전략 </b> 사용하기