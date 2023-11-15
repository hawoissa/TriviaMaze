package Model;

import java.io.Serializable;

public class Door implements DoorInterface, Serializable {
    private boolean myIsLocked;


    /**
     * Constructor initializes the fields.
     */
    public Door(){
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
     * A setter to set either door is locked or not.
     */
    public void lock(final boolean theLocked){
        myIsLocked = theLocked;
    }
    /**
     * A to string to print on console.
     */
//    public String toString(){
//
//    }
}
