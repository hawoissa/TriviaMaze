/*
    This is the Maze class
    Name: Matiullah Jalal
    Date: 10/25/2023
    Quarter: Autumn 2023
 */
package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Model.QuestionAnswer1;

public class Maze implements Serializable {
    private Room[][] myMaze;
    private Room myCurrentRoom;
    private boolean myIsGameStarted;
    private long myStartTime;
    private long myEndTime;
    private int myX;
    private int myY;
    private ArrayList<QuestionAnswer1> questionList;

    public Maze() {
        myX = 0;
        myY = 0;

        myMaze = new Room[4][4];
        questionList = new ArrayList<>();

        // Create an instance of TriviaQADatabase and populate questionList
        TriviaQADatabase triviaDatabase = new TriviaQADatabase();
        triviaDatabase.getQAFromDataBase(questionList);

        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // Assign a question to each room from questionList
                myMaze[i][j] = new Room(letter, i, j, new Door(), getQuestionForRoom());
                letter++;
            }
        }

        myCurrentRoom = myMaze[0][0];
    }

    // Add this method to get a question from questionList for a room
    private QuestionAnswer1 getQuestionForRoom() {
        // You can implement your logic to get a question from questionList
        // For example, you can remove a question from the list and return it
        if (!questionList.isEmpty()) {
            return questionList.remove(0);
        } else {
            return null; // Handle the case when questionList is empty
        }
    }

    // Add methods for updating, deleting, and adding questions to rooms
    public void updateQuestionForRoom(int x, int y, QuestionAnswer1 newQuestion) {
        if (isValidCoordinate(x, y)) {
            myMaze[x][y].setQuestionAnswer(newQuestion);
        }
    }

    public void deleteQuestionForRoom(int x, int y) {
        if (isValidCoordinate(x, y)) {
            myMaze[x][y].setQuestionAnswer(null);
        }
    }

    public void addQuestionToRoom(int x, int y, QuestionAnswer1 newQuestion) {
        if (isValidCoordinate(x, y)) {
            myMaze[x][y].setQuestionAnswer(newQuestion);
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < myMaze.length && y >= 0 && y < myMaze[0].length;
    }

    public Maze(Room[][] maze, int currentX, int currentY) {
        myMaze = maze;
        myX = currentX;
        myY = currentY;
        myCurrentRoom = myMaze[myX][myY];
    }

    public QuestionAnswer1 getCurrentQuestion() {
        return myCurrentRoom.getQuestionAnswer();
    }

    // Add this method to check the player's answer and update the game state
    public boolean answerQuestion(String playerAnswer) {
        QuestionAnswer1 currentQuestion = getCurrentQuestion();

        if (myIsGameStarted && currentQuestion != null && myCurrentRoom.isAnswerCorrect(playerAnswer)) {
            myCurrentRoom.getMyDoor().lock(false);
            return true;
        } else {
            myCurrentRoom.lockDoor();
            resetGame();
            return false;
        }
    }

    public void startGame() {
        myIsGameStarted = true;
        myStartTime = System.currentTimeMillis();
    }

    public void resetGame() {
        myIsGameStarted = false;
        myEndTime = System.currentTimeMillis();

        // Reset player's position
        myX = 0;
        myY = 0;
        myCurrentRoom = myMaze[0][0];
    }

    public void saveGame(String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Maze loadGame(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Maze) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public long getTotalTime() {
        if (myIsGameStarted) {
            long totalElapsedTime = (myEndTime - myStartTime);
            return totalElapsedTime / 60000; // Convert milliseconds to min
        }
        return 0;
    }

    public boolean isGameOn() {
        return myIsGameStarted;
    }

    public Room getMyCurrentRoom() {
        return myCurrentRoom;
    }

    public void moveUp() {
        if (myX > 0) {
            myX--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

    public void moveDown() {
        if (myX < myMaze.length - 1) {
            myX++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

    public void moveRight() {
        if (myY < myMaze[0].length - 1) {
            myY++;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

    public void moveLeft() {
        if (myY > 0) {
            myY--;
            myCurrentRoom = myMaze[myX][myY];
        }
    }

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



    private static void markDeadEnd(char[][] maze, int row, int col) {
        maze[row][col] = 'd';
    }

    private static void markVisited(char[][] maze, int row, int col) {
        maze[row][col] = 'v';
    }

    private static boolean atExit(char[][] maze, int row, int col) {
        return row == maze.length - 1 && col == maze[row].length - 1;
    }

    private static boolean validMove(char[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0
                && col < maze[row].length && maze[row][col] == '.';
    }
}
