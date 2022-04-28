package coach.xfitness.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Test;

import coach.xfitness.business.Exercise;

//SelectAll
public class ExerciseDBTest {

    @Test
    public void TestSelectAll() {
        int expectedSize = 22;
        int actualSize = MuscleDB.selectAll().size();

        assertEquals(expectedSize, actualSize);
    }

    //SelectByName
    @Test
    public void testDeleteByName() {
        String name = ""; 

        Exercise actual = new Exercise();
        Exercise expected = new Exercise();
        
        expected.setName(""); // what exercise name
        actual.setName(""); // what exercise name
        
        try {
        }  catch (NoResultException e) {
            fail("The exercise object specified was not found");
        }
        
        assertEquals(expected.getName(), actual.getName());
        
        ExerciseDB.deleteByName(actual.getName());
    }

    private void assertTrue(String message, Object true1) {
    }

    @Test
    public void testDeleteByNameFailure() {
        Exercise actual = new Exercise();
        String name = "name";

        actual.setName("name"); //exercisename

        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Exercise> typedQuery = entityManager.createNamedQuery("Exercise.selectByName", Exercise.class);
        typedQuery.setParameter("name", name);
        Exercise result = null;

        try {
            actual = typedQuery.getSingleResult();
        } catch (NoResultException e) {
            assertTrue("The appropriate exception was thrown", true);
        }
    }


    @Test
    public void testdoActionOnlistSuccess() {    
        String name = "gustavo-s-push-ups";

        //setting exericse 1 params-test1
        Exercise exercise = new Exercise();
        exercise.setName(name); //name

        //setting exercise 2 params -test1 
        Exercise exercise2 = new Exercise();
        exercise2.setName(name); //name
        exercise2.setTitle("Different");

        List<Exercise> exerciseList = new ArrayList<Exercise>();
        List<Exercise> exerciseList2 = new ArrayList<Exercise>();

        //add to Exercise List
        exerciseList.add(exercise);
        exerciseList2.add(exercise2);

        
        //Test insert action.
        ExerciseDB.insertList(exerciseList);
        assertNotNull(ExerciseDB.selectByName(name));
        // TODO: test same attribute

        //Test update action.
        ExerciseDB.updateList(exerciseList2);
        assertEquals(exercise2, ExerciseDB.selectByName(name));
        // TODO: test different attribute TITLE

        //Test delete action.
        //exerciseList2.remove(0);// remove the first item from the list
        ExerciseDB.deleteList(exerciseList2);
        assertNull(ExerciseDB.selectByName(name));
    }
}
