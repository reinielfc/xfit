package coach.xfitness.business;

import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class ExerciseMusclePKTest {
    
    @Test
    //Test to see if ExerciseMusclePK class exists in system
	 public void testUserClassExists(){
        try{
            Class.forName("coach.xfitness.business.ExerciseMusclePK");
        } catch (ClassNotFoundException e){
            fail("ExerciseMusclePKclass does not exist");
        }
    }

    @Test
    //Test to see if ExerciseMusclePK constructor(s) exists
    public void testExerciseMusclePKConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.ExerciseMusclePK.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.ExerciseMusclePK()")) {
                found = true;
            }
        }
		if (!found){
			fail("ExerciseMusclePK class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetMuscleId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.ExerciseMusclePK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.ExerciseMusclePK.getUserId()")) {
                ExerciseMusclePK test = new ExerciseMusclePK();
                test.setMuscleId(300);
                if (test.getMuscleId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("ExerciseMusclePK class should have a public method called getMuscleId");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetExerciseId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.ExerciseMusclePK.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.ExerciseMusclePK.getEquipmentId()")) {
                ExerciseMusclePK test = new ExerciseMusclePK();
                test.setExerciseId(300);
                if (test.getExerciseId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("ExerciseMusclePK class should have a public method called getExerciseId");
        }
    }

}
