package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class UserExerciseKeyTest {
    
    @Test
    //Test to see if UserExerciseKey class exists in system
	 public void testUserExerciseKeyClassExists(){
        try{
            Class.forName("coach.xfitness.business.UserExerciseKey");
        } catch (ClassNotFoundException e){
            fail("UserExerciseKey class does not exist");
        }
    }

    @Test
    //Test to see if UserExerciseKey constructor(s) exists
    public void testUserExerciseKeyConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.UserExerciseKey.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.UserExerciseKey()")) {
                found = true;
            }
        }
		if (!found){
			fail("UserExerciseKey class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetUserId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserExerciseKey.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserExerciseKey.getUserId()")) {
                UserExerciseKey test = new UserExerciseKey();
                test.setUserId(300);
                if (test.getUserId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("UserExerciseKey class should have a public method called getUserId");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetExerciseId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.UserExerciseKey.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.UserExerciseKey.getEquipmentId()")) {
                UserExerciseKey test = new UserExerciseKey();
                test.setExerciseId(300);
                if (test.getExerciseId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("UserExerciseKey class should have a public method called getEquipmentId");
        }
    }

}
