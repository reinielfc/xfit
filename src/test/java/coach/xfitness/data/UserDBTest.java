package coach.xfitness.data;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coach.xfitness.business.User;

public class UserDBTest {
   
    private final ByteArrayOutputStream out= new ByteArrayOutputStream();
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();


    @Before
	// runs before each test starts - redirects streams to local variables
	public void setUpStreams() {
        System.setOut(new PrintStream(out));
	    System.setErr(new PrintStream(err));
	}

    @After
	// runs after each test ends - cleans up streams to defaults
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	    System.setIn(System.in); //reset System.in to its original
	}

    @Test

    public void testSelectByEmailSuccess() {

        String actualEmail = "marioisaman@gmail.com";
        //create our user objects
        User expected = new User();
        //set both emails equal to the same email

        expected.setEmail(actualEmail);
        expected.setPassword("password");
        expected.setName("name");
        //insert the actual value into the database

        UserDB.insert(expected);
        User actual = UserDB.selectByEmail(actualEmail);

        assertNotNull(actual);
        //confirm that the user "actual" that we inserted is equal to
        assertEquals(actualEmail, actual.getEmail());
        //Clean up after test {delete user from table}
        UserDB.deleteByEmail(actualEmail);

    }

    @Test

    public void testSelectByEmailFailure() {
        
        String actualEmail= "";
        String actualPassword = "";
        String actualName = "";
        User expected = new User();

        expected.setEmail(actualEmail);
        expected.setPassword(actualPassword);
        expected.setName(actualName);

        UserDB.insert(expected);

        try {
            UserDB.selectByEmail(actualEmail);
        } catch (Exception e) {
            //TODO: handle exception
            fail("unable to find the email");
        }
        UserDB.deleteByEmail(actualEmail);
    }

    @Test
    public void testInsertSuccess() {
        String actualEmail = "thisdoesexist@gmail.com";
        User expected = new User();
        boolean check = true;

        expected.setEmail(actualEmail);
        expected.setPassword("password");
        expected.setName("name");

        UserDB.insert(expected);

        assertEquals(UserDB.hasUserWithEmail(actualEmail), check);

        UserDB.deleteByEmail(actualEmail);

    }

    @Test
    public void testInsertFailure() {
        
        String actualEmail= "";
        String actualPassword = "";
        String actualName = "";
        User expected = new User();

        expected.setEmail(actualEmail);
        expected.setPassword(actualPassword);
        expected.setName(actualName);

        try {
                UserDB.insert(expected);
        } catch (Exception e) {
            //TODO: handle exception
            fail("Unable to insert User");
        }


    }

    @Test
    public void testHasUserWithEmailSuccess() {

        User expected = new User();

        String actualEmail = "marioisaman@gmail.com";
        expected.setEmail(actualEmail);
        expected.setPassword("password");
        expected.setName("name");

        UserDB.insert(expected);

        User actual  = UserDB.selectByEmail(actualEmail);

        assertNotNull(actual);

        UserDB.deleteByEmail(actualEmail);

    }

    @Test
    public void testHasUserWithEmailFailure() {

        User expected = new User();

        String actualEmail = "";
        expected.setEmail(actualEmail);
        expected.setPassword("password");
        expected.setName("name");

        UserDB.insert(expected);
        try {
            UserDB.selectByEmail(actualEmail);
        } catch (Exception e) {
            //TODO: handle exception
            fail("unable to find user with email");
        }

        UserDB.deleteByEmail(actualEmail);

    }

    @Test
    public void testDeleteByEmailSuccess() {
        String actualEmail = "marioisaman@gmail.com";

        User expected = new User();

        expected.setEmail(actualEmail);
        expected.setPassword("password");
        expected.setName("name");     

        UserDB.insert(expected);

        UserDB.deleteByEmail(actualEmail);

        try {
            UserDB.selectByEmail(actualEmail);
        } catch (Exception e) {
            //TODO: handle exception
            fail("Unable to find User");
        }
    }
    @Test
    public void testUpdateSuccess() {
        String emptyEmail = "";
        String actualEmail = "fillinganemail@gmail.com";
        User missing = new User();

        missing.setEmail(emptyEmail);
        missing.setPassword("");
        missing.setName("");
        UserDB.insert(missing);
        
        User fill = new User();

        fill.setEmail(actualEmail);
        fill.setPassword("fillpassword");
        fill.setName("fillaname");
        UserDB.insert(fill);

        UserDB.update(missing);
    
        try {
            UserDB.selectByEmail(emptyEmail);
        } catch (Exception e) {
            //TODO: handle exception
            fail("email does not match the records");
        }

        UserDB.deleteByEmail(missing.getEmail());
        UserDB.deleteByEmail(fill.getEmail());
    }

}
