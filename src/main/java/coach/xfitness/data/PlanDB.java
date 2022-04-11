package coach.xfitness.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coach.xfitness.business.Plan;

public class PlanDB {
    final static int BATCH_SIZE = 8;

    public static Plan selectBy(Byte dayOfWeek, Integer position) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Plan> typedQuery = entityManager.createNamedQuery("Exercise.selectByPositionInDay", Plan.class);
        typedQuery.setParameter("dayOfWeek", dayOfWeek);
        typedQuery.setParameter("position", position);

        Plan result = null;
        try {
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Plan> selectDay(Byte dayOfWeek) {
        EntityManager entityManager = null;
        List<Plan> resultsList = null;

        try {
            entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
            Query query = entityManager.createNamedQuery("Exercise.selectDay");
            query.setParameter("dayOfWeek", dayOfWeek);
            resultsList = DBUtil.castList(Plan.class, query.getResultList());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return resultsList;
    }

    private static void doActionOnList(List<Plan> planList, String action) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.setProperty("hibernate.jdbc.batch_size", String.valueOf(BATCH_SIZE));

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try {
            for (int i = 0; i < planList.size(); i++) {
                if (i > 0 && i % BATCH_SIZE == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }

                Plan plan = planList.get(i);

                switch (action) {
                    case "insert":
                        entityManager.persist(plan);
                        break;
                    case "update":
                        entityManager.merge(plan);
                        break;
                    case "delete":
                        if (!entityManager.contains(plan)) {
                            plan = entityManager.merge(plan);
                        }

                        entityManager.remove(plan);
                        break;
                }
            }
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
    }

    public static void insertList(List<Plan> planList) {
        doActionOnList(planList, "insert");
    }

    public static void updateList(List<Plan> planList) {
        doActionOnList(planList, "update");
    }

    public static void deleteList(List<Plan> planList) {
        doActionOnList(planList, "delete");
    }

}
