package Tests;


import Model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MazeTest {

    private Maze myTestMaze;



    MazeTest() {

    }
    @BeforeEach
    void intialize() {
        myTestMaze = new Maze();
    }

    @Test
    void moveRightTest() {
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
    void moveLeftTest() {
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
    void moveDownTest() {
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
    void moveUpTest() {
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
    void startGameTest() {
        assertFalse(myTestMaze.isGameOn());
        assertEquals(myTestMaze.getTotalTime(), 0);
        myTestMaze.startGame();
        assertTrue(myTestMaze.isGameOn());
    }

    @Test
    void testResetMaze() {
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


}
