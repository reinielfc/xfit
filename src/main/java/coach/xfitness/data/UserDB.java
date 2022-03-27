package coach.xfitness.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

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
            User u = entityManager.find(User.class, s.getId());
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

    public static boolean updateUser( User user) {
        return true;
    }

}
