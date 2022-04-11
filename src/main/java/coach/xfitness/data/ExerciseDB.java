package coach.xfitness.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coach.xfitness.business.Exercise;
import coach.xfitness.business.User;

public class ExerciseDB {

    public static List<Exercise> selectAll() {
        EntityManager entityManager = null;
        List<Exercise> resultsList = null;

        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectAll");
            resultsList = DBUtil.castList(Exercise.class, query.getResultList());
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return resultsList;
    }

    public static void insert(Exercise exercise) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            entityManager.persist(exercise);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    public static List<Exercise> selectAllAvailableTo(User user) {
        EntityManager entityManager = null;
        List<Exercise> resultsList = null;

        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectAllAvailable");
            query.setParameter("userByUserId", user);
            resultsList = DBUtil.castList(Exercise.class, query.getResultList());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return resultsList;
    }

    public static Exercise selectByName(String name) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Exercise> typedQuery = entityManager.createNamedQuery("Exercise.selectByName", Exercise.class);
        typedQuery.setParameter("name", name);
        Exercise result = null;

        try {
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static boolean hasExerciseByName(String name) {
        Exercise exercise = selectByName(name);
        return exercise != null;
    }

    public static List<String> fetchTypesList() {
        EntityManager entityManager = null;
        List<String> resultsList = null;
        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectDistinctTypes");
            resultsList = DBUtil.castList(String.class, query.getResultList());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return resultsList;
    }



    public static void deleteByName(String name) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            // TODO: there might be some redundancy here
            Exercise exercise = selectByName(name);
            exercise = entityManager.find(Exercise.class, exercise.getId());
            entityManager.remove(exercise);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

}
