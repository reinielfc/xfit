package coach.xfitness.business;

import java.io.Serializable;

/**
 * User
 */
public class User implements Serializable {
    private Long userID;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public User() {
        this.email = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}