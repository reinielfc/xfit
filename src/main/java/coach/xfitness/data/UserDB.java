package coach.xfitness.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import coach.xfitness.business.User;

public class UserDB {

    public static void insert(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            entityManager.persist(user);
            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
    }

    public static User selectUser(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        String queryString = "SELECT user FROM User user WHERE user.email = :email";
        TypedQuery<User> typedQuery = entityManager.createQuery(queryString, User.class);
        typedQuery.setParameter("email", email);
        User result = null;
        try {
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }

        return result;
    }

    public static boolean hasUser(String email) {
        User user = selectUser(email);
        return user != null;
    }

}
