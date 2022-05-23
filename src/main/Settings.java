/*
 *
 */
package main;

/**
 * This class prevents the version number to be hard coded into
 * the GUI and version number can be adjusted easily in this class.
 *
 * @author Paul Lee
 * @version Spring 2022
 */
public class Settings {

    /** The name of our team name */
    private static final String TEAM_NAME = "MacroSoft";
    /**
     * Version number of the application.
     */
    private static final double VERSION_NUMBER = 1.1;

    private static final String[] collaborators = {
            "Gabe Bryan - gabeb544@uw.edu",
            "Anteh Hsu - gan86650@uw.edu",
            "Wei Wei Chien - weiwei88@uw.edu",
            "Alex Larsen - alexlars@uw.edu",
            "Paul Lee - plee83@uw.edu"
    };

    /**
     * Gets the version number.
     *
     * @return the version number.
     */
    public static double getVersionNumber() {
        return VERSION_NUMBER;
    }
    public static String getTeamName() { return TEAM_NAME; }

    public static String printAbout(){
        StringBuilder content = new StringBuilder();
        if(User.activeUser != null) {
            content.append("This app is registered to:\n" + User.activeUser.toString());
        }else{
            content.append("Error: No User");
        }

        content.append("\nThis app is provided by " + getTeamName());

        //Print collaborators
        content.append("\nAuthors: ");
        for(int i = 0; i < collaborators.length; i++){
            content.append("\n");
            content.append(collaborators[i]);
        }


        content.append("\nVersion " + getVersionNumber());
        return content.toString();
    }

    public static void saveSettings()
    {

    }

    public static void loadSettings()
    {

    }
}
