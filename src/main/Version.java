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
public class Version {

    /**
     * Version number of the application.
     */
    private static final double VERSION_NUMBER = 1.1;

    /**
     * Gets the version number.
     *
     * @return the version number.
     */
    public double getVersionNumber() {
        return this.VERSION_NUMBER;
    }
}
