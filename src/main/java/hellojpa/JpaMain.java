package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setUsername("Member1");
            member1.setHomeAddress(new Address("city", "street", "10000"));

            member1.getFavoriteFoods().add("치킨");
            member1.getFavoriteFoods().add("족발");
            member1.getFavoriteFoods().add("피자");

//            member1.getAddressHistory().add(new Address("old1", "street1", "zipcode1"));
//            member1.getAddressHistory().add(new Address("old2", "street2", "zipcode2"));

            em.persist(member1);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member1.getId());
            System.out.println("findMember = " + findMember);

//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }

            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

//            findMember.getHomeAddress().setCity("newCity");

            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1", "street1", "zipcode1"));
//            findMember.getAddressHistory().add(new Address("newCity1", "street1", "zipcode1"));
//
            findMember.getAddressHistory().remove(new AddressEntity());
            findMember.getAddressHistory().add(new AddressEntity());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // close
            em.close();
        }

        emf.close();
    }
//
//    private static void printMember(Member member) {
//        System.out.println("member.getUsername() = " + member.getUsername());
//    }
//
//    private static void printMemberAndTeam(Member member) {
//        String username = member.getUsername();
//        System.out.println("username = " + username);
//
//        Team team = member.getTeam();
//        System.out.println("team = " + team);
//    }
}
