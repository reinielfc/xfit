package coach.xfitness.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    //test that user is inputted into the data and exists in exists and deletes afterward
    public void testInsert() {
        User test = new User();
        test.setId(1);
        test.setEmail("JHerdocia112@att.net");
        test.setName("JavierH");
        test.setPassword("pass");
        
        UserDB.insert(test);
        assertTrue(UserDB.hasUserWithEmail("JHerdocia112@att.net"));
        UserDB.deleteByEmail(test.getEmail());
    }

    @Test
    //test that selectUser will return a null value for invalid selection
    public void testSelectInvalidUser() {
       User test = UserDB.selectByEmail("test@gmail.com");
       if(test != null){
           fail("Must return a null value for invalid or non exsistent user");
       }
    }

    @Test
    //test that duplicate insertion of emails will result in an error
    public void testInsertDuplicateUserEmails(){
        String message = "Failed to add User.";
        User test1 = new User();
        test1.setId(10);
        test1.setEmail("JSmith001@gmail.com");
        test1.setName("JohnSmith");
        test1.setPassword("pass");
        User test2 = new User();
        test2.setId(11);
        test2.setEmail("JSmith001@gmail.com");
        test2.setName("JavierHerdocia");
        test2.setPassword("pass");
        //insert
        UserDB.insert(test1);
        UserDB.insert(test2);
        String output = out.toString();
        assertTrue(output.contains(message));
        UserDB.deleteByEmail(test1.getEmail());
    }

    @Test
    //test that hasUser will return false for invalid user selection
    public void testhasUser(){
        assertFalse(UserDB.hasUserWithEmail("test@gmail.com"));
    }

    @Test
    //test that User attributes cannot be null
    public void testUserNullValues(){

    }
}
