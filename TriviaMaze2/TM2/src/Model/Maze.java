/*
    This is the Maze class
    Name: Zakirye Luqman, Hawo Issa, Matiullah Jalal
    Date: 10/25/2023
    Quarter: Autumn 2023
 */
package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The maze class shows a maze with doors and rooms.
 * Each room in the maze has door, and these
 * doors can be accessed through specific questions.
 */
public class Maze implements Serializable, MazeInterface {
    /**
     * Holds 2D grid of the maze.
     */
    private Room[][] myMaze;
    /**
     * Holds current room.
     */
    private Room myCurrentRoom;
    /**
     * Holds if the game is already started.
     */
    private boolean myIsGameStarted;
    /**
     * Holds time start.
     */
    private long myStartTime;
    /**
     * Holds time end.
     */
    private long myEndTime;
    /**
     * Holds the x-coordinate.
     */
    private int myX;
    /**
     * Holds the y-coordinate.
     */
    private int myY;
    /**
     * Holds the question list .
     */
    private ArrayList<QuestionAnswer1> myQuestionList;

    /**
     * A default constructor to set a 4x4 maze.
     */
    public Maze() {
        myX = 0;
        myY = 0;

        myMaze = new Room[4][4];
        myQuestionList = new ArrayList<>();
        // Create an instance of QuestionFactory and populate questionList
        QuestionFactory questionFactory = new QuestionFactory();
        questionFactory.myDataBase.initializeDatabase();
        questionFactory.myDataBase.getQAFromDataBase(myQuestionList);

        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Assign a question to each room from questionList
                myMaze[i][j] = new Room(letter, i, j, Door.getInstance(), getQuestionForRoom(), calculateChances(i,j));
                letter++;
            }
        }

        myCurrentRoom = myMaze[0][0];
    }

    /**
     * A method that gets a question from questionList for a room.
     * @return returns the question list otherwise null.
     */
    private QuestionAnswer1 getQuestionForRoom() {
        // You can implement your logic to get a question from questionList
        // For example, you can remove a question from the list and return it
        if (!myQuestionList.isEmpty()) {
            Random random = new Random();
            int r = random.nextInt(myQuestionList.size());
            return myQuestionList.remove(r);
        } else {
            return null; // Handle the case when questionList is empty
        }
    }

    /**
     * A method that update question for room.
     * @param theX is the x-coordinate.
     * @param theY is the y-Coordinate.
     * @param theNewQuestion is the other question.
     */
    public void updateQuestionForRoom(int theX, int theY, QuestionAnswer1 theNewQuestion) {
        if (isValidCoordinate(theX, theY)) {
            myMaze[theX][theY].setQuestionAnswer(theNewQuestion);
        }
    }
    /**
     * A method that deletes a question for room.
     * @param theX is the x-coordinate.
     * @param theY is the y-Coordinate.
     */
    public void deleteQuestionForRoom(int theX, int theY) {
        if (isValidCoordinate(theX, theY)) {
            myMaze[theX][theY].setQuestionAnswer(null);
        }
    }
    /**
     * A method that add question to room.
     * @param theX is the x-coordinate.
     * @param theY is the y-Coordinate.
     * @param theNewQuestion is the other question.
     */
    public void addQuestionToRoom(int theX, int theY, QuestionAnswer1 theNewQuestion) {
        if (isValidCoordinate(theX, theY)) {
            myMaze[theX][theY].setQuestionAnswer(theNewQuestion);
        }
    }

    /**
     * A method that checks for validity of coordinates.
     * @param theX
     * @param theY
     * @return returns true if valid otherwise false.
     */
    private boolean isValidCoordinate(int theX, int theY) {
        return theX >= 0 && theX < myMaze.length && theY >= 0 && theY < myMaze[0].length;
    }
    /**
     * A constructor to initialize the fields.
     * @param theMaze is the maze.
     * @param theCurrentX is the x-coordinate.
     * @param theCurrentY is the y-coordinate.
     */
    public Maze(Room[][] theMaze, int theCurrentX, int theCurrentY) {
        myMaze = theMaze;
        myX = theCurrentX;
        myY = theCurrentY;
        myCurrentRoom = myMaze[myX][myY];
    }
    /**
     * A getter to get current question.
     * @return returns a string of question.
     */
    public String getCurrentQuestion() {
        return myCurrentRoom.getQuestionAnswer().getMyQuestion();
    }

    /**
     * A method to check the player's answer and update the game state.
     * @return returns ture if answer is correct otherwise false.
     */
    public boolean answerQuestion (String thePlayerAnswer){
        QuestionAnswer1 currentQuestion = myCurrentRoom.getQuestionAnswer();

        if (myCurrentRoom.isAnswerCorrect(thePlayerAnswer)) {
            //myCurrentRoom.getMyDoor().lock(false);
            return true;
        } else {
            myCurrentRoom.lockDoor();
            return false;
        }
    }

    /**
     * Sets the game to start.
     */
    public void startGame () {
        myIsGameStarted = true;
        myStartTime = System.currentTimeMillis();
    }

    /**
     * Sets the game to reset.
     */
    public void resetGame () {
        myIsGameStarted = false;
        myEndTime = System.currentTimeMillis();

        // Reset player's position
        myX = 0;
        myY = 0;
        myCurrentRoom = myMaze[0][0];
    }

    /**
     * Sets the game to save.
     * @param theFilePath is the other file.
     */
    public void saveGame (String theFilePath){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(theFilePath))) {
            resetGame();
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the game to load.
     * @param theFilePath is the other file.
     * @return returns the maze object to deserialize.
     */
    public Maze loadGame(String theFilePath){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(theFilePath))) {
            startGame();
            return (Maze) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * A getter to get total time of game played.
     * @return returns total time.
     */
    public long getTotalTime () {
        if (myIsGameStarted) {
            long totalElapsedTime = (myEndTime - myStartTime);
            return totalElapsedTime / 60000; // Convert milliseconds to min
        }
        return 0;
    }

    /**
     * A getter if game is on or not.
     * @return returns ture if on otherwise false.
     */
    public boolean isGameOn () {
        return myIsGameStarted;
    }

    /**
     * A getter to get current room.
     * @return returns current room.
     */
    public Room getMyCurrentRoom () {
        return myCurrentRoom;
    }

    /**
     * A setter to set the entity to move up.
     */
    public void moveUp () {
        if (myX > 0) {
            myX--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move down.
     */
    public void moveDown () {
        if (myX < myMaze.length - 1) {
            myX++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move right.
     */
    public void moveRight () {
        if (myY < myMaze[0].length - 1) {
            myY++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move left.
     */
    public void moveLeft () {
        if (myY > 0) {
            myY--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

    public int calculateChances(int theI, int theJ) {
        int chance = 0;
        if (theI - 1 >= 0) chance++;
        if (theI + 1 < 4) chance++;
        if (theJ - 1 >= 0) chance++;
        if (theJ + 1 < 4) chance++;
        return chance; // Example: return a fixed number of chances
    }
    /**
     * Sets the conditions for game to end.
     * In order to do so, it will travers in maze.
     */
    public boolean isGameOver () {


        char[][] solveMatrix = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (myMaze[i][j].getMyDoor().isLocked()) {
                    solveMatrix[i][j] = 'x';
                } else {
                    solveMatrix[i][j] = '.';
                }
            }
        }

        boolean success = move(solveMatrix, 0, 0);

        if (success) {
            System.out.println("Succcess!!!");

        } else {
            System.out.println("WOWOWOW!!!");

        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(solveMatrix[i][j]);
            }
            System.out.println();
        }

        return success;

    }

    /**
     * A private method to support the movement of each cell in the maze.
     * @param theMaze is the maze.
     * @param theRow is the row.
     * @param theCol is the column.
     * @return returns true if movement was successful otherwise false.
     */
    private static boolean move ( char[][] theMaze, int theRow, int theCol){
        boolean success = false;

        if (validMove(theMaze, theRow, theCol)) {
            markVisited(theMaze, theRow, theCol);
            if (atExit(theMaze, theRow, theCol))
                return true;

            success = move(theMaze, theRow + 1, theCol);

            if (!success)
                success = move(theMaze, theRow, theCol + 1);
            if (!success)
                success = move(theMaze, theRow - 1, theCol);
            if (!success)
                success = move(theMaze, theRow, theCol - 1);
            if (!success)
                markDeadEnd(theMaze, theRow, theCol);

        }

        return success;
    }

    /**
     * A method to set the dead end in the maze.
     * @param theMaze is the maze.
     * @param theRow is the row.
     * @param theCol is the column.
     */
    private static void markDeadEnd ( char[][] theMaze, int theRow, int theCol){
        theMaze[theRow][theCol] = 'd';
    }

    /**
     * A method to mark the cell that is already visited.
     * @param theMaze is the maze.
     * @param theRow is the row.
     * @param theCol is the column.
     */
    private static void markVisited ( char[][] theMaze, int theRow, int theCol){
        theMaze[theRow][theCol] = 'v';
    }
    /**
     * A method to mark the exit points of the maze.
     * @param theMaze is the maze.
     * @param theRow is the row.
     * @param theCol is the column.
     * @return returns true if at exit otherwise false.
     */
     private static boolean atExit ( char[][] theMaze, int theRow, int theCol){
        return theRow == theMaze.length - 1 && theCol == theMaze[theRow].length - 1;
     }
        /**
         * A method to check if move is valid or not.
         * @param theMaze is the maze.
         * @param theRow is the row.
         * @param theCol is the column.
         * @return returns true if move is valid otherwise false.
         */
     private static boolean validMove ( char[][] theMaze, int theRow, int theCol){
        return theRow >= 0 && theRow < theMaze.length && theCol >= 0
                && theCol < theMaze[theRow].length && theMaze[theRow][theCol] == '.';
     }
}
