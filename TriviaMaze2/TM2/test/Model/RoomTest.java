package Model;

import Model.QuestionAnswer;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoomTest {


        private Door myDoor;
        private int myX;
        private int myY;
        private char myRoomLetter;
        private QuestionAnswer myQA;
        private Room myRoom;

        /**
         * Initializes the fields.
         */
        @BeforeEach
        void setUp() {
            myX = 2;
            myY = 3;
            myRoomLetter = 'A';
            myQA = new QuestionAnswer();
            myDoor = new Door();
            myQA = new QuestionAnswer();
            myRoom = new Room(myRoomLetter,myRoomLetter,myY,myDoor);
        }

        /**
         * Tests getter method of the Room class.
         */
        @Test
        void testGetMyX() {
            assertEquals(myX, myRoom.getMyX());
        }
        /**
         * Tests getter method of the Room class.
         */
        @Test
        void testGetMyY() {
            assertEquals(myY, myRoom.getMyY());
        }
        @Test
        void testGetMyDoor(){

            assertEquals(myDoor, myRoom.getMyDoor());
        }
        @Test
        void testGetLetter(){
            assertEquals(myRoomLetter, myRoom.getLetter());
        }
        // Need to  fix this tester
        @Test
        void testGetQuestion(){
        }

}
