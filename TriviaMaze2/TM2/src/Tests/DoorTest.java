package Tests;

import Model.Door;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoorTest {

    void setUp() {
       // myDoor = new Door();
    }

    @Test
    void testLock() {
        Door myDoor = Door.getInstance();
        boolean myValid = true;
       myDoor.lock(myValid);
    }

    @Test
    void testIsLock() {
        Door myDoor = Door.getInstance();
        boolean myValid = true;
        myDoor.lock(myValid);
        assertTrue(myDoor.isLocked());
    }
}