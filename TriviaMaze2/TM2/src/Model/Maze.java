/*
    This is the Maze class
    Name: Matiullah Jalal
    Date: 10/25/2023
    Quarter: Autumn 2023
 */
package Model;

import java.io.*;
import Model.QuestionAnswer1;

public class Maze implements Serializable {
    private Room[][] myMaze;
    private Room myCurrentRoom;
    private boolean myIsGameStarted;
    private long myStartTime;
    private long myEndTime;
    private int myX;
    private int myY;

    public Maze() {
        myX = 0;
        myY = 0;

        myMaze = new Room[4][4];

        char letter = 'A';
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                myMaze[i][j] = new Room(letter, i, j, new Door());
                letter++;
            }
        }

        myCurrentRoom = myMaze[0][0];
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

        if (myIsGameStarted && myCurrentRoom.isAnswerCorrect(playerAnswer)) {
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
}
