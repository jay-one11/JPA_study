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



## 2차시
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

