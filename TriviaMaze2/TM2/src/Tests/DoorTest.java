package Tests;

import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The DoorTest class contains JUnit tests for the Door class.
 */
class DoorTest {

    /**
     * Test setup method.
     */
    void setUp() {
        // myDoor = new Door();
    }

    /**
     * Test the lock method of the Door class.
     */
    @Test
    void testLock() {
        Door myDoor = Door.getInstance();
        boolean myValid = true;
        myDoor.lock(myValid);
    }

    /**
     * Test the isLocked method of the Door class.
     */
    @Test
    void testIsLock() {
        Door myDoor = Door.getInstance();
        boolean myValid = true;
        myDoor.lock(myValid);
        assertTrue(myDoor.isLocked());
    }
}