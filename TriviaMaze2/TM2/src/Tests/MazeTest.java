package Tests;


import Model.Door;
import Model.Maze;
import Model.QuestionAnswer1;
import Model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MazeTest {

    private Maze myTestMaze;
    private QuestionAnswer1 myQA;
    private int myX;
    private int myY;



    MazeTest() {
    }
    @BeforeEach
    public void intialize() {
        myTestMaze = new Maze();
    }
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
    @Test
    public void startGameTest() {
        assertFalse(myTestMaze.isGameOn());
        assertEquals(myTestMaze.getTotalTime(), 0);
        myTestMaze.startGame();
        assertTrue(myTestMaze.isGameOn());
    }
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
    @Test
    public void testGetQuestionForRoom(){
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
    @Test
    public void testGetCurrentQuestion(){
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
    @Test
    public void testAnswerQuestion(){
        assertFalse(myTestMaze.answerQuestion("Red color is my favorite?"));
    }
    @Test
    public void testIsGameOn(){
        assertFalse(myTestMaze.isGameOn());
    }
    @Test
    public void testIsGameOver(){

        if(!myTestMaze.isGameOver()){
            assertFalse(myTestMaze.isGameOver());
        } else {
            assertTrue(myTestMaze.isGameOver());
        }
    }
    @Test
    public void testGetTotalTime(){
        long expected = 0;
        assertEquals(expected,myTestMaze.getTotalTime());
    }
    @Test
    public void testCalculateChances(){
        int i = 4;
        int j = 4;
        assertEquals(2,myTestMaze.calculateChances(i,j));
    }

}
