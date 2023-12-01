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

    private QuestionAnswer1 myQA;

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

    public QuestionAnswer1 getQuestionAnswer() {
        return myQA;
    }

    // Add this method to check if the player answered the question correctly
    public boolean isAnswerCorrect(String playerAnswer) {
        return myQA.getMyAnswer().equalsIgnoreCase(playerAnswer);
    }

    // Add this method to lock the door if the answer is incorrect
    public void lockDoor() {
        myDoor.lock(true);
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

    public String getQuestion() {
        return myQA.getMyQuestion();
    }
}


