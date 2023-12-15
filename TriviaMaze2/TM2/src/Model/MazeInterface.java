/*
    Name: Matiullah Jalal, Zakiraye Luqman, Hawo Issa
    Date: 10/06/2023
    Quarter: Autumn 2023
 */
package Model;

/**
 * The maze interface allows player to move up, down, right and left.
 * Also, provides information about the players current room.
 */
public interface MazeInterface {
    /**
     * Gets the current room of a player.
     * @return
     */
    public Room getMyCurrentRoom();
    /**
     * A getter to get current question.
     * @return returns a string of question.
     */
    public String getCurrentQuestion();
    /**
     * A method to check the player's answer and update the game state.
     * @return returns ture if answer is correct otherwise false.
     */
    public boolean answerQuestion(String thePlayerAnswer);
    /**
     * Sets the game to start.
     */
    public void startGame();
    /**
     * Sets the game to reset.
     */
    public void resetGame();
    /**
     * Sets the game to load.
     * @param theFilePath is the other file.
     * @return returns the maze object to deserialize.
     */
    public Maze loadGame(String theFilePath);
    /**
     * Sets the game to save.
     * @param theFilePath is the other file.
     */
    public void saveGame(String theFilePath);
    /**
     * A getter to get total time of game played.
     * @return returns total time.
     */
    public long getTotalTime();
    /**
     * A getter if game is on or not.
     * @return returns ture if on otherwise false.
     */
    public boolean isGameOn();
    /**
     * Sets the conditions for game to end.
     */
    public boolean isGameOver();
    /**
     * Moves the player up in maze.
     */
    public void moveUp();
    /**
     * Moves the player down in maze.
     */
    public void moveDown();
    /**
     * Moves the player right in maze.
     */
    public void moveRight();
    /**
     * Moves the player left in maze.
     */
    public void moveLeft();

}
