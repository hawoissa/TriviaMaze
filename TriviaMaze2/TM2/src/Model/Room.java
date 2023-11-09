package Model;

import java.io.Serial;
import java.io.Serializable;

public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = -89283998989899829L;
    private Door myDoor;
    private int myX;
    private int myY;
    private char myRoomLetter;

    private QuestionAnswer myQA;

    /**
     * Constructor initializes the fields.
     */
    public Room(final char letter, final int theX, final int theY,
                final Door theDoor) {
        myRoomLetter = letter;
        myX = theX;
        myY = theY;
        myDoor = theDoor;

    }

    /**
     * Get the current status of X.
     */
    public int getMyX() {
        return myX;
    }

    /**
     * Get the current status of Y.
     */
    public int getMyY() {
        return myY;
    }

    /**
     * Getter to return the status of the door.
     */
    public Door getMyDoor() {
        return myDoor;
    }



    public char getLetter() {
        return myRoomLetter;
    }

    public QuestionAnswer.Question getQuestion() {
        return myQA.getMyQuestion();
    }
}


