package coach.xfitness.data;

import coach.xfitness.business.Exercise;
import coach.xfitness.business.User;

import javax.persistence.*;

public class UserDB {
    // TODO: This method is for tesing purposes only, DELETE IT
    public static void main(String[] args) {
        User user = new User();
        user.setName("jsmith");
        user.setEmail("jsmith@example.net");
        user.setPassword("sesame");

        UserDB.insert(user);

        System.out.println(
            UserDB.select(user.getEmail()).getEmail()
        );;

        user.setName("john");
        UserDB.update(user);

        System.out.println(
            UserDB.select(user.getEmail()).getEmail()
        );

        user = UserDB.select(user.getEmail());
        user.setEmail("jsmith@email.com");
        UserDB.update(user);

        System.out.println(
            UserDB.select(user.getEmail()).getEmail()
        );

        user.getFavoriteExercises().add(ExerciseDB.select(22));
        user.getFavoriteExercises().add(ExerciseDB.select(32));
        user.getFavoriteExercises().stream().forEach(fe -> {
            fe.getExerciseMusclesById().forEach(em -> System.out.println(fe.getTitle() + ": " + em.getMuscleByMuscleId().getName()));
        });

        UserDB.delete(user.getEmail());

        System.out.println(
            UserDB.select(user.getEmail()).getEmail()
        );
    }

    public static User select(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<User> typedQuery = entityManager.createNamedQuery("User.selectByEmail", User.class);
        typedQuery.setParameter("email", email);
        User result = null;

        try {
            System.out.println("[DATABASE] SELECT: User with email '" + email + "' from database.");
            result = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("[DATABASE ERROR] SELECT FAIL: User with email '" + email + "' not found:");
            System.err.println(e);
        }

        return result;
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
            System.err.println(e);
        }
    }

    public static boolean has(String email) {
        User user = select(email);
        return user != null;
    }

    public static void update(User user) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("[DATABASE] UPDATE: User with email '" + user.getEmail() + "'.");
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public static void delete(String email) {
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        User user = entityManager.find(User.class, UserDB.select(email).getId());
        System.out.println("[DATABASE] DELETE: User with email '" + user.getEmail() + "'.");
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

}
