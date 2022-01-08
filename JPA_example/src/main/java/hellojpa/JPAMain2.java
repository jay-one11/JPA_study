package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.List;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

public class JPAMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        단위 핻동마다 em을 만들어야함
//        DB당 하나만
        EntityManager em = emf.createEntityManager();
//      EM은 고객의 요청때마다 사용하기
//      쓰레드 간에 절대 공유하지 않기 ( 사용하고 바로 버려야 댐  dbconnection 처럼 )
        EntityTransaction tx = em.getTransaction();
//        data의 모든 변경은 tx 내에서 실행되어야함.
//        단건 커리는 tx를 안걸어도 할 수 있지만, 다건이되면 장애 유발 가능.
        tx.begin();

        try{
//            Insert

//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloJPA");
//
//            em.persist(member);

//            Select
//            Member findMember = em.find(Member.class, 1L);
//            em.find(DTO.class, 원하는 Column)
//            System.out.println("findMember.id : " + findMember.getId());

//            Delete
//            em.remove(findMember);

//           Update
//            Collection 다루듯이 자동으로 막 바껴줌
//            Commit하는 시점에 update된 정보를 확인 후에 commit하기 때문에 자동으로 커밋됨
//            findMember.setName("Hello.JPB");

//            JPQL 소개
/*             가장 간단한 조회 방법
//          em.createQuery(queryString, dto.class).getResultList() 를 통해 객체를 통해 조회할 수 있음.
//          자동으로 dto의 column을 나열해서 조회해줌.

*            List<Member> result = em.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member name =  " + member.getName());
            }
*
*
*
*
* */
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            // 위와같이 oracle과 mysql등 문법이 다른 경우에도 하이버네이트의 방언 기능을 통해 자동으로 번역해줌.
//             검색 조건이 포함된 Entity 객체를 대상으로 하는 SQL문을 통해 조회
            for (Member member : result) {
                System.out.println("member name =  " + member.getName());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
