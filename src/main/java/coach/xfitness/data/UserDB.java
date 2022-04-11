package coach.xfitness.data;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import coach.xfitness.business.User;
public class UserDB {

    public static User selectByEmail(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> typedQuery = entityManager.createNamedQuery("User.selectByEmail", User.class);
        typedQuery.setParameter("email", email);
        User result = null;

        try {
            System.out.println("[DATABASE] SELECT: User with email '" + email + "' from database.");
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("[DATABASE ERROR] SELECT FAIL: User with email '" + email + "' not found:");
            e.printStackTrace();
        }

        return result;
    }

    public static boolean hasUserWithEmail(String email) {
        User user = selectByEmail(email);
        return user != null;
    }

    public static void insert(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        String email = user.getEmail();

        try {
            System.out.println("[DATABASE] INSERT: User with email '" + email  + "' to database.");
            if (UserDB.has(email)) {
                System.err.println("[DATABASE ERROR] INSERT FAIL: User with email '" + email + "' already exists:");
                throw new NonUniqueResultException();
            } else {
                entityManager.persist(user);
                entityTransaction.commit();
            }
        } catch (Exception e) {
            System.err.println("[DATABASE ERROR] INSERT FAIL: User with email '" + email + "' could not be inserted:");
            e.printStackTrace();
        }
    }

    public static void update(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("[DATABASE] UPDATE: User with email '" + user.getEmail() + "'.");
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public static void deleteByEmail(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, UserDB.selectByEmail(email).getId());
        System.out.println("[DATABASE] DELETE: User with email '" + user.getEmail() + "'.");
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

}
