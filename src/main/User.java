package main;

public class User {
    private String myName;
    private String myEmailAddress;
    public User(String name, String email){
        myName = name;
        myEmailAddress = email;
    }

    public String toString(){
        return myName + " - " + myEmailAddress;
    }
}
