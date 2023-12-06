/*
    This is the Maze class
    Name: Zakirye Luqman, Hawo Issa, Matiullah Jalal
    Date: 10/25/2023
    Quarter: Autumn 2023
 */
package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.QuestionAnswer1;

/**
 * The maze class shows a maze with doors and rooms.
 * Each room in the maze has door, and these
 * doors can be accessed through specific questions.
 */
public class Maze implements Serializable {
    /** Holds 2D grid of the maze. */
    private Room[][] myMaze;
    /** Holds current room. */
    private Room myCurrentRoom;
    /** Holds if the game is already started.*/
    private boolean myIsGameStarted;
    /** Holds time start.*/
    private long myStartTime;
    /** Holds time end.*/
    private long myEndTime;
    /** Holds the x-coordinate.*/
    private int myX;
    /** Holds the y-coordinate.*/
    private int myY;
    /** Holds the question list .*/
    private ArrayList<QuestionAnswer1> questionList;

    /**
     * A default constructor to set a 4x4 maze.
     */
    public Maze() {
        myX = 0;
        myY = 0;

        myMaze = new Room[4][4];

        questionList = new ArrayList<>();
        TriviaQADatabase triviaDatabase = new TriviaQADatabase();
        triviaDatabase.getQAFromDataBase(questionList);

        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                myMaze[i][j] = new Room(letter, i, j, new Door(),
                    questionList.get(0));
                letter++;
            }
        }

        myCurrentRoom = myMaze[0][0];
    }

    /**
     * A constructor to initialize the fields.
     * @param maze is the maze.
     * @param currentX is the x-coordinate.
     * @param currentY is the y-coordinate.
     */
    public Maze(Room[][] maze, int currentX, int currentY) {
        myMaze = maze;
        myX = currentX;
        myY = currentY;
        myCurrentRoom = myMaze[myX][myY];
    }

    /**
     * A getter to get current question.
     * @return returns the question.
     */
    public QuestionAnswer1 getCurrentQuestion() {
        return myCurrentRoom.getQuestionAnswer();
    }
    /**
     * A method to check the player's answer and update the game state.
     * @return returns ture if answer is correct otherwise false.
     */
    public boolean answerQuestion(String playerAnswer) {
        QuestionAnswer1 currentQuestion = getCurrentQuestion();

        if (myIsGameStarted && myCurrentRoom.isAnswerCorrect(playerAnswer)) {
            return true;
        } else {
            myCurrentRoom.lockDoor();
            resetGame();
            return false;
        }
    }

    /**
     * Sets the game to start.
     */
    public void startGame() {
        myIsGameStarted = true;
        myStartTime = System.currentTimeMillis();
    }

    /**
     * Sets the game to reset.
     */
    public void resetGame() {
        myIsGameStarted = false;
        myEndTime = System.currentTimeMillis();

        // Reset player's position
        myX = 0;
        myY = 0;
        myCurrentRoom = myMaze[0][0];
    }

    /**
     * Sets the game to save.
     * @param filePath is the other file.
     */
    public void saveGame(String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the game to load.
     * @param filePath is the other file.
     * @return returns the maze object to deserialize.
     */
    public static Maze loadGame(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
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
    public long getTotalTime() {
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
    public boolean isGameOn() {
        return myIsGameStarted;
    }

    /**
     * A getter to get current room.
     * @return returns current room.
     */
    public Room getMyCurrentRoom() {
        return myCurrentRoom;
    }

    /**
     * A setter to set the entity to move up.
     */
    public void moveUp() {
        if (myX > 0) {
            myX--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move down.
     */
    public void moveDown() {
        if (myX < myMaze.length - 1) {
            myX++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move right.
     */
    public void moveRight() {
        if (myY < myMaze[0].length - 1) {
            myY++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }
    /**
     * A setter to set the entity to move left.
     */
    public void moveLeft() {
        if (myY > 0) {
            myY--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

    /**
     * Sets the conditions for game to end.
     * In order to do so, it will travers in maze.
     */
    public void isGameOver() {
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

        boolean success =  move(solveMatrix, 0, 0);

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


    }

    /**
     * A private method to support the movement of each cell in the maze.
     * @param maze is the maze.
     * @param row is the row.
     * @param col is the column.
     * @return returns true if movement was successful otherwise false.
     */
    private static boolean move(char[][] maze, int row, int col) {
        boolean success = false;

        if (validMove(maze, row, col)) {
            markVisited(maze, row, col);
            if (atExit(maze, row, col))
                return true;

            success = move(maze, row+1, col);

            if (!success)
                success = move(maze, row, col+1);
            if (!success)
                success = move(maze, row-1, col);
            if (!success)
                success = move(maze, row, col-1);
            if (!success)
                markDeadEnd(maze, row, col);

        }

        return success;
    }

    /**
     * A method to set the dead end in the maze.
     * @param maze is the maze.
     * @param row is the row.
     * @param col is the column.
     */
    private static void markDeadEnd(char[][] maze, int row, int col) {
        maze[row][col] = 'd';
    }

    /**
     * A method to mark the cell that is already visited.
     * @param maze is the maze.
     * @param row is the row.
     * @param col is the column.
     */
    private static void markVisited(char[][] maze, int row, int col) {
        maze[row][col] = 'v';
    }
    /**
     * A method to mark the exit points of the maze.
     * @param maze is the maze.
     * @param row is the row.
     * @param col is the column.
     * @return returns true if at exit otherwise false.
     */
    private static boolean atExit(char[][] maze, int row, int col) {
        return row == maze.length - 1 && col == maze[row].length - 1;
    }
    /**
     * A method to check if move is valid or not.
     * @param maze is the maze.
     * @param row is the row.
     * @param col is the column.
     * @return returns true if move is valid otherwise false.
     */
    private static boolean validMove(char[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0
                && col < maze[row].length && maze[row][col] == '.';
    }
}
