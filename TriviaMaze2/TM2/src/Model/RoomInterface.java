/*
    Name: Matiullah Jalal, Zakiraye Luqman, Hawo Issa
    Date: 10/05/2023
    Quarter: Autumn 2023
 */
package Model;

/**
 * This class defines methods that should be implemented by Room.
 *
 */
public interface RoomInterface {
    /**
     * Get the X coordinate of the room.
     * @return  returns the X coordinate.
     */
    public int getMyX();
    /**
     * Get the Y coordinate of the room.
     * @return  returns the Y coordinate.
     */
    public int getMyY();
    /**
     * Get the door associated with the room.
     * @return  returns the door.
     */
    public Door getMyDoor();
    /**
     * Get the character associated with the room.
     * @return  returns the character that represents the room.
     */
    public char getLetter();

    /**
     * A getter to get a question.
     * @return returns a question associated with room.
     */
    public String getQuestion();
    /**
     * A getter to get question and answer.
     * @return returns question and answer associated with the room.
     */
    public QuestionAnswer1 getQuestionAnswer();
    /**
     * A getter to get the question type.
     * @return returns the question type.
     */
    public String getCurrentQuestionType();
    /**
     * Checks if the player answered the question is correctly.
     * @param playerAnswer is the answer of the player.
     * @return returns ture if correct otherwise false.
     */
    public boolean isAnswerCorrect(String playerAnswer);
    /**
     *  Add this method to lock the door if the answer is incorrect.
     */
    public void lockDoor();

}
