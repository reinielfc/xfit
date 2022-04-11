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
    final static int BATCH_SIZE = 8;

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

    private static void doActionOnList(List<Exercise> exerciseList, String action) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.setProperty("hibernate.jdbc.batch_size", String.valueOf(BATCH_SIZE));

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            for (int i = 0; i < exerciseList.size(); i++) {
                if (i > 0 && i % BATCH_SIZE == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }

                Exercise exercise = exerciseList.get(i);

                System.out.println(exercise.getId() + " : " + exercise.getName());

                switch (action) {
                    case "insert":
                        entityManager.persist(exercise);
                        break;
                    case "update":
                        entityManager.merge(exercise);
                        break;
                    case "delete":
                        if (!entityManager.contains(exercise)) {
                            exercise = entityManager.merge(exercise);
                        }

                        entityManager.remove(exercise);
                        break;
                }
            }
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    public static void insertList(List<Exercise> exerciseList) {
        doActionOnList(exerciseList, "insert");
    }

    public static void updateList(List<Exercise> exerciseList) {
        doActionOnList(exerciseList, "update");
    }

    public static void deleteList(List<Exercise> exerciseList) {
        doActionOnList(exerciseList, "delete");
    }

}
