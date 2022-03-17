package coach.xfitness.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;

import coach.xfitness.business.User;

public class UserDB {

    public static void insert(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            if(hasUser(user.getEmail())){
                throw new NonUniqueResultException();
            }
            else{
                entityManager.persist(user);
                entityTransaction.commit();
            }
        } catch (Exception e) {
            System.out.println("Failed to add User.");
        } finally {
            entityManager.close();
        }
    }

    public static User selectUser(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        User result = null;
        try{
            String queryString = "SELECT user FROM User user WHERE user.email = :email";
            TypedQuery<User> typedQuery = entityManager.createQuery(queryString, User.class);
            typedQuery.setParameter("email", email);
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Could not find user in database.");
        } finally {
            entityManager.close();
        }

        return result;
    }

    public static boolean hasUser(String email) {
        User user = selectUser(email);
        return user != null;
    }  

    public static void deleteUser(String email){
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try{
            User s = selectUser(email);
            User u = entityManager.find(User.class, s.getUserID());
           entityManager.remove(u);
           entityManager.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Couldn't delete User");
        }
        finally{
            entityManager.close();
        }
    }

}
