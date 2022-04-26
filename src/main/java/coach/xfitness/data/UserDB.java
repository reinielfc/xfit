package coach.xfitness.data;

import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import coach.xfitness.business.User;

public class UserDB {

    /**
    * Get the user with the given email address.
    * 
    * @param email The email address of the user we want to select.
    * @return A single user object.
    */
    public static User selectByEmail(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> typedQuery = entityManager.createNamedQuery("User.selectByEmail", User.class);
        typedQuery.setParameter("email", email);
        User result = null;

        try {
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
    * If a user with the given email exists, return true, otherwise return false.
    * 
    * @param email The email address of the user to check for.
    * @return A boolean value.
    */
    public static boolean hasUserWithEmail(String email) {
        User user = selectByEmail(email);
        return user != null;
    }

    /**
    * If the user doesn't exist, add the user to the database
    * 
    * @param user The user object that you want to insert into the database.
    */
    public static void insert(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        String email = user.getEmail();

        try {
            if (UserDB.hasUserWithEmail(email)) {
                throw new NonUniqueResultException();
            } else {
                entityManager.persist(user);
                entityTransaction.commit();
            }
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    /**
    * Updates a user in the database.
    * 
    * @param user The user object that you want to update.
    */
    public static void update(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        entityManager.merge(user);
        entityTransaction.commit();
    }

    public static void deleteByEmail(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            User user = selectByEmail(email);
            user = entityManager.find(User.class, user.getId());
            entityManager.remove(user);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    /**
    * If the password is not null or blank, and if the password contains at least 8
    * characters, at least one uppercase letter, at least one lowercase letter, and
    * at least one digit, then the password is valid
    * 
    * @param password The password to validate.
    * @return A boolean value.
    */
    public static boolean isPasswordValid(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        String[] requirements = new String[] { "\\S{8,}", "[A-Z]", "[a-z]", "[0-9]" };

        for (String requirement : requirements) {
            // if password does not match requirement regex
            if (!Pattern.compile(requirement).matcher(password).find()) {
                return false; // it is invalid
            }
        }

        return true;
    }

}
