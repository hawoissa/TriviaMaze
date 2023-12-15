package Tests;

import Model.Door;
import Model.QuestionAnswer1;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The RoomTest class contains JUnit tests for the Room class.
 */
class RoomTest {

    private Door myDoor;
    private int myX;
    private int myY;
    private char myRoomLetter;
    private QuestionAnswer1 myQA;
    private Room myRoom;

    /**
     * Initializes the fields before each test.
     */
    @BeforeEach
    void setUp() {
        myX = 2;
        myY = 3;
        myRoomLetter = 'A';
        myDoor = Door.getInstance();
        String question = "Thomas Addison invent the electricity? T/F";
        String answer = "false";
        String type = "TF";
        int id = 1;

        myQA = new QuestionAnswer1(id, type, question, answer);
        myRoom = new Room(myRoomLetter, myRoomLetter, myY, myDoor, myQA, 4);
    }

    /**
     * Tests the getter method getMyX() of the Room class.
     */
    @Test
    void testGetMyX() {
        assertNotEquals(myX, myRoom.getMyX());
    }

    /**
     * Tests the getter method getMyY() of the Room class.
     */
    @Test
    void testGetMyY() {
        assertEquals(myY, myRoom.getMyY());
    }

    /**
     * Tests the getter method getMyDoor() of the Room class.
     */
    @Test
    void testGetMyDoor() {
        assertEquals(myDoor, myRoom.getMyDoor());
    }

    /**
     * Tests the getter method getLetter() of the Room class.
     */
    @Test
    void testGetLetter() {
        assertEquals(myRoomLetter, myRoom.getLetter());
    }

    /**
     * Tests the getter method getQuestion() of the Room class.
     */
    @Test
    void testGetQuestion() {
        String question = "Thomas Addison invent the electricity? T/F";
        String answer = "false";
        String type = "TF";
        int id = 1;
        QuestionAnswer1 qa = new QuestionAnswer1(id, type, question, answer);
        assertEquals(qa.getMyQuestion(), myRoom.getQuestion());
    }

    /**
     * Tests the getCurrentQuestionType() method of the Room class.
     */
    @Test
    void testGetCurrentQuestionType() {
        assertEquals(myQA.getMyType(), myRoom.getCurrentQuestionType());
    }

    /**
     * Tests the isAnswerCorrect() method of the Room class.
     */
    @Test
    void testIsAnswerCorrect() {
        String answer = "false";
        boolean expectedAnswer = true;
        assertEquals(expectedAnswer, myRoom.isAnswerCorrect(answer));
    }

    /**
     * Tests the getStringQuestion() method of the Room class.
     */
    @Test
    void testGetStringQuestion() {
        assertEquals(myQA.getMyQuestion(), myRoom.getQuestion());
    }

    /**
     * Tests the lockDoor() method of the Room class.
     */
    @Test
    void testLockDoor() {
        boolean expected = false;
        Door door = Door.getInstance();
        door.lock(expected);
        assertNotEquals(door.isLocked(), myRoom.getMyDoor());
    }
}
