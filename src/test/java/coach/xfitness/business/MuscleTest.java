package coach.xfitness.business;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;

public class MuscleTest {
    
    @Test
    //Test to see if User class exists in system
	 public void testMuscleClassExists(){
        try{
            Class.forName("coach.xfitness.business.Muscle");
        } catch (ClassNotFoundException e){
            fail("Muscle class does not exist");
        }
    }

    @Test
    //Test to see if User constructor(s) exists
    public void testMuscleConstructorsExist(){
        boolean found = false;  
		Constructor[] list = coach.xfitness.business.Muscle.class.getConstructors();  // get all constructors
        // loop through list of Constructors
        for (Constructor constructor : list) {
            String value = "" + constructor; // convert to string
            if (value.contentEquals("public coach.xfitness.business.Muscle()")) {
                found = true;
            }
        }
		if (!found){
			fail("Muscle class should have 1 constructor with no parameters");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetId() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.Muscle.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.Muscle.getId()")) {
                Muscle test = new Muscle();
                test.setId(300);
                if (test.getId() == 300) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("Muscle class should have a public method called getId");
        }
    }

    @Test
    //test if method exists and returns a string attribute
    public void testGetName() {
        boolean found = false;  
		Method[] list = coach.xfitness.business.Muscle.class.getDeclaredMethods();
        // loop through list of attributes
        for (Method method : list) {
            String value = "" + method; // convert to string
            if (value.contentEquals("public java.lang.String coach.xfitness.business.Muscle.getName()")) {
                Muscle test = new Muscle();
                test.setName("Triceps");
                if (test.getName().equals("Triceps")) {
                    found = true;
                }
            }
        }
		if (!found){
			fail("Muscle class should have a public method called getName");
        }
    }
}
