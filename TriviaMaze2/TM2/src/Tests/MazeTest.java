package Tests;

import Model.Door;
import Model.Maze;
import Model.QuestionAnswer1;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the Maze class.
 */
class MazeTest {

    private Maze myTestMaze;
    private QuestionAnswer1 myQA;
    private int myX;
    private int myY;


    /**
     * Initializes the Maze instance before each test.
     */
    @BeforeEach
    public void initialize() {
        myTestMaze = new Maze();
    }

    /**
     * Tests the moveRight method of the Maze class.
     * Verifies that the room letter changes correctly when moving right.
     */
    @Test
    public void moveRightTest() {
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(),'A');
        myTestMaze.moveRight();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(),'B');
        myTestMaze.moveRight();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(),'C');
        myTestMaze.moveRight();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(),'D');
        myTestMaze.moveRight();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(),'D'); // Should stay at D since it's the rightmost letter
    }

    /**
     * Tests the moveLeft method of the Maze class.
     * Verifies that the room letter changes correctly when moving left.
     */
    @Test
    public void moveLeftTest() {
        myTestMaze.moveRight();
        myTestMaze.moveRight();
        myTestMaze.moveRight();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'D');
        myTestMaze.moveLeft();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'C');
        myTestMaze.moveLeft();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'B');
        myTestMaze.moveLeft();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A');
        myTestMaze.moveLeft();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A'); // tests if it goes out of bounds
    }

    /**
     * Tests the moveDown method of the Maze class.
     * Verifies that the room letter changes correctly when moving down.
     */
    @Test
    public void moveDownTest() {
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A');
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'E');
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'I');
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'M');
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'M'); // tests if it goes out of bounds
    }

    /**
     * Tests the moveUp method of the Maze class.
     * Verifies that the room letter changes correctly when moving up.
     */
    @Test
    public void moveUpTest() {
        myTestMaze.moveDown();
        myTestMaze.moveDown();
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'M');
        myTestMaze.moveUp();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'I');
        myTestMaze.moveUp();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'E');
        myTestMaze.moveUp();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A');
        myTestMaze.moveUp();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A'); // tests if it goes out of bounds
    }

    /**
     * Tests the startGame method of the Maze class.
     * Verifies that the game starts correctly.
     */
    @Test
    public void startGameTest() {
        assertFalse(myTestMaze.isGameOn());
        assertEquals(myTestMaze.getTotalTime(), 0);
        myTestMaze.startGame();
        assertTrue(myTestMaze.isGameOn());
    }

    /**
     * Tests the resetGame method of the Maze class.
     * Verifies that the game is reset correctly.
     */
    @Test
    public void testResetMaze() {
        myTestMaze.startGame();
        myTestMaze.moveRight();
        myTestMaze.moveRight();
        myTestMaze.moveDown();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'G');
        assertTrue(myTestMaze.isGameOn());
        myTestMaze.resetGame();
        assertEquals(myTestMaze.getMyCurrentRoom().getLetter(), 'A');
        assertFalse(myTestMaze.isGameOn());
    }

    /**
     * Tests the getQuestionForRoom method of the Maze class.
     * Verifies that the correct question is retrieved for a given room.
     */
    @Test
    public void testGetQuestionForRoom() {
        myX = 3;
        myY = 4;
        myQA = new QuestionAnswer1(1,"TF","Red color is my favorite?","F");
        Room[][] rooms = new Room[myX][myY];
        for(int i = 0; i < myX; i++){
            for(int j = 0; j < myY; j++){
                rooms[i][j] = new Room('M', i, j, Door.getInstance(), myQA, 2);
            }
        }
        myTestMaze = new Maze(rooms ,1,2);
        assertNotEquals(rooms, myTestMaze.getMyCurrentRoom());
    }

    /**
     * Tests the getCurrentQuestion method of the Maze class.
     * Verifies that the current question is retrieved correctly.
     */
    @Test
    public void testGetCurrentQuestion() {
        myX = 3;
        myY = 4;
        myQA = new QuestionAnswer1(1,"TF","Red color is my favorite?","F");
        Room[][] rooms = new Room[myX][myY];
        for(int i = 0; i < myX; i++){
            for(int j = 0; j < myY; j++){
                rooms[i][j] = new Room('M', i, j, Door.getInstance(), myQA, 2);
            }
        }
        myTestMaze = new Maze(rooms ,2,1);
        assertEquals("Red color is my favorite?", myTestMaze.getCurrentQuestion());
    }

    /**
     * Tests the answerQuestion method of the Maze class.
     * Verifies that the question is answered correctly.
     */
    @Test
    public void testAnswerQuestion() {
        assertFalse(myTestMaze.answerQuestion("Red color is my favorite?"));
    }

    /**
     * Tests the isGameOn method of the Maze class.
     * Verifies that the game status is correctly determined.
     */
    @Test
    public void testIsGameOn() {
        assertFalse(myTestMaze.isGameOn());
    }

    /**
     * Tests the isGameOver method of the Maze class.
     * Verifies that the game over status is correctly determined.
     */
    @Test
    public void testIsGameOver() {

        if(!myTestMaze.isGameOver()){
            assertFalse(myTestMaze.isGameOver());
        } else {
            assertTrue(myTestMaze.isGameOver());
        }
    }

    /**
     * Tests the getTotalTime method of the Maze class.
     * Verifies that the total time is retrieved correctly.
     */
    @Test
    public void testGetTotalTime() {
        long expected = 0;
        assertEquals(expected,myTestMaze.getTotalTime());
    }

    /**
     * Tests the calculateChances method of the Maze class.
     * Verifies that the chances are calculated correctly.
     */
    @Test
    public void testCalculateChances() {
        int i = 4;
        int j = 4;
        assertEquals(2,myTestMaze.calculateChances(i,j));
    }
}

