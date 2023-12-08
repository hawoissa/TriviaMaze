/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */
package Model;

import java.io.Serializable;

/**
 * Door class indicates a door in trivia maze game.
 * Also, it implements DoorInterface and Serializable.
 * Serializable will help to save and load an object.
 * The door will connect different rooms in the maze.
 */
public class Door implements DoorInterface, Serializable {
    /** Holds the lock status.*/
    private boolean myIsLocked;

    private static Door myDoor = new Door();


    /**
     * Constructor initializes the fields.
     */
    private Door(){
        // initially the door is open
        myIsLocked = false;
    }

    /**
     * A getter method to get current status of the door.
     */
    public boolean isLocked(){
        return myIsLocked;
    }

    /**
     * A setter to set either door should be locked or not.
     */
    public void lock(final boolean theLocked){
        myIsLocked = theLocked;
    }

    public static Door getInstance(){
        return myDoor;
    }

}
