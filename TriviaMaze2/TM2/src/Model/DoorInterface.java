/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */
package Model;

/**
 * The DoorInterface indicates how the doors work.
 * The DoorInterface will initially be implemented by Door.
 */
public interface DoorInterface {
    /**
     * A getter method to get current status of the door.
     */
    public boolean isLocked();

    /**
     * A setter to set either door should be locked or not.
     */
    public void lock(final boolean theLocked);


}