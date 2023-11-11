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
     * Gets a question which is associated with the room.
     * @return returns the question.
     */
    public QuestionAnswer.Question getQuestion();

}
