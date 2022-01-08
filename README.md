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


-----
## 영속성 관리

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
    <img alt="1차캐싱결과" src="">

    - 사실 성능적인 장점보다는 객체지향적인 컨셉적 이점이 크다.

- 영속 엔티티의 동일성 보장
    <img alt="영속엔티티의동일성보장" src="">
    - JPA는 영속 엔티티의 동일성을 보장한다.
    - 위의 findMember1과 Findmember2는 서로 같은 객체라는 것을 보장한다.
    - 단, 같은 트랜잭션 내에서 실행 시 !

- 엔티티 등록 시 트랜잭션을 지원하는 쓰기 지연
    <img src="" alt="트랜잭션을지원하는쓰기지연">
    - Commit하기 전까지 JPA에 Insert query를 저장했다가 , Commit을 하는 순간 DB에 해당 query를 보낸다.
     <img src="" alt="트랜잭션을지원하는쓰기지연2">
     - 영속 컨텍스트 내의 "쓰기지연 SQL저장소"에 Insert SQL을 생성하고 1차 캐시에 저장한다.
     <img src="" alt="트랜잭션을지원하는쓰기지연3">
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
     <img src="" alt="쓰기지연결과">
     - 쓰기 지연의 장점
        - 옵션 하나로 성능을 향상시킬 수 있음.
        - buffer를 통해 한다는 점
     - `persistence.xml` 의 hibernate.jdbc.batch_size를 통해서 한번에 쓰기지연 할 수 있는 최대 개수를 지정할 수 있음.


- Entity 수정 / 변경 감지
    <img src="" alt="엔티티수정변경감지">
    ```
    Member findMember1 = em.find(Member.class, 150L);
    findMember1.setName("zzzzz");

    System.out.println("=======================");
    tx.commit();
    ```
    - 결과
        - 수정 전
        <img src="" alt="엔티티수정전">
        - 수정 후
         <img src="" alt="엔티티수정후">
    
    - JPA는 변경 감지 기능으로 Entity Update 기능을 제공한다.
    - 마치 Collection을 사용하듯이 쉽게 변경할 수 있는 기능
    <img src="" alt="dirtychecking">
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
