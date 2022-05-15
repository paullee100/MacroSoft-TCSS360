/*
 *
 */
package main;

/**
 * User class
 *
 * @author Gabe Bryan
 * @version Spring 2022
 */
public class User {
    /** Name of the user */
    private String myName;

    /** The email address of the user */
    private String myEmailAddress;

    /**
     * Constructor to initialize the name and email address
     * when a new object is made.
     *
     * @param name the name of the user.
     * @param email the email of the user.
     */
    public User(String name, String email){
        myName = name;
        myEmailAddress = email;
    }

    /**
     * Overrides the toString method to print out the correct
     * string of the User object.
     *
     * @return the correct string format.
     */
    public String toString(){
        return myName + " - " + myEmailAddress;
    }
}
