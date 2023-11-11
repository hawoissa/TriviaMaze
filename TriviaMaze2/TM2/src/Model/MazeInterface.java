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
