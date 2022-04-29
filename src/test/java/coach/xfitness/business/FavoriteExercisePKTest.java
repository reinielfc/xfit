package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class FavoriteExercisePKTest {
    
    @Test
    //Test to see if FavoriteExercisePK class exists in system
	 public void testFavoriteExercisePKClassExists(){
        try{
            Class.forName("coach.xfitness.business.FavoriteExercisePK");
        } catch (ClassNotFoundException e){
            fail("FavoriteExercisePK class does not exist");
        }
    }

    @Test
    //Test to see if User constructor(s) exists
    public void testUserConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.FavoriteExercisePK.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.FavoriteExercisePK()")) {
                found = true;
            }
        }
		if (!found){
			fail("FavoriteExercisePK class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetUserId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.FavoriteExercisePK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.FavoriteExercisePK.getUserId()")) {
                FavoriteExercisePK test = new FavoriteExercisePK();
                test.setUserId(300);
                if (test.getUserId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("FavoriteExercisePK class should have a public method called getUserId");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetExerciseId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserEquipmentPK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserEquipmentPK.getEquipmentId()")) {
                FavoriteExercisePK test = new FavoriteExercisePK();
                test.setExerciseId(300);
                if (test.getExerciseId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("FavoriteExercisePK class should have a public method called getEquipmentId");
        }
    }
}
