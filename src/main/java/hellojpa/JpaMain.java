package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 영속
//            Member member = new Member(200L, "member200");
            Member member1 = em.find(Member.class, 150L);
//            member1.setName("AAAAA");

            em.clear();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("===========================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // close
            em.close();
        }

        emf.close();
    }
}
