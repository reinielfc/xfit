package coach.xfitness.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import coach.xfitness.business.Muscle;

public class MuscleDBTest {


    @Test
    public void testSelectAllSuccess() {
        Muscle name1 = new Muscle();
        name1.setName("abdominals");

        Muscle name2 = new Muscle();
        name2.setName("back");

        Muscle name3 = new Muscle();
        name3.setName("biceps");

        List<Muscle> expected = Arrays.asList(name1, name2, name3);
         
        
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Muscle.selectAll");
        List<Muscle> resultsList = DBUtil.castList(Muscle.class, query.getResultList());

        List<Muscle> actual = resultsList;

        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(1).getName(), actual.get(1).getName());
        assertEquals(expected.get(2).getName(), actual.get(2).getName());
    }

    @Test
    public void TestSelectAll(){

        int expectedSize = 22;

        int actualSize = MuscleDB.selectAll().size();

        assertEquals(expectedSize, actualSize);

    }

    @Test
    public void testSelectAllFailure() {
        
        int expectedSize = 23;

        int actualSIze = MuscleDB.selectAll().size();

        assertNotEquals(expectedSize, actualSIze);

    }

    @Test

    public void testSelectAllEmpty() {

        Muscle name1 = new Muscle();
        name1.setName("");

        Muscle name2 = new Muscle();
        name2.setName("");

        Muscle name3 = new Muscle();
        name3.setName("");

        List<Muscle> expected = Arrays.asList(name1, name2, name3);
        
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Muscle.selectAll");
        List<Muscle> resultsList = DBUtil.castList(Muscle.class, query.getResultList());

        List<Muscle> actual = resultsList;

        assertNotEquals(expected.get(0), actual.get(0).getName());
        assertNotEquals(expected.get(1), actual.get(1).getName());
        assertNotEquals(expected.get(2), actual.get(2).getName());

    }


    @Test
    public void testSelectAllNull() {
        Muscle name1 = new Muscle();

        name1.setName(null);

        Muscle name2 = new Muscle();

        name2.setName(null);

        Muscle name3 = new Muscle();

        name3.setName(null);

        List<Muscle> expected = Arrays.asList(name1, name2, name3);  
        
        EntityManager entityManager = DBUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery("Muscle.selectAll");
        List<Muscle> resultsList = DBUtil.castList(Muscle.class, query.getResultList());

        List<Muscle> actual = resultsList;

        assertNotEquals(expected.get(0), actual.get(0).getName());
        assertNotEquals(expected.get(1), actual.get(1).getName());
        assertNotEquals(expected.get(2), actual.get(2).getName());

    }

    @Test
    public void testFetchNamesList() {
        List<String> muscleList = MuscleDB.fetchNamesList();

        String expectedFirst = "abdominals";
        String expectedLast = "upper back";
        String actualFirst = muscleList.get(0);
        String actualLast = muscleList.get(muscleList.size()-1);

        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedLast, actualLast);

    }

    @Test
    public void testFetchNamesListFailure() {
        List<String> muscleList = MuscleDB.fetchNamesList();

        String expectedFirst = "";
        String expectedLast = "";
        String actualFirst = muscleList.get(0);
        String actualLast = muscleList.get(muscleList.size()-1);

        assertNotEquals(expectedFirst, actualFirst);
        assertNotEquals(expectedLast, actualLast);
    }
}
