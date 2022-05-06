package main;

public class User {
    private String myName;
    private String myEmail;

    public User(String theName, String theEmail) {
        this.myName = theName;
        this.myEmail = theEmail;
    }
    public String getMyName() {
        return myName;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyName(String theName) {
        this.myName = theName;
    }

    public void setMyEmail(String theEmail) {
        this.myEmail = theEmail;
    }
}
