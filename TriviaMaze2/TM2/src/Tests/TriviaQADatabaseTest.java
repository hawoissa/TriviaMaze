package Tests;

import Model.QuestionAnswer1;
import Model.TriviaQADatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import Model.QuestionAnswer1;
import Model.TriviaQADatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The TriviaQADatabaseTest class contains JUnit tests for the TriviaQADatabase class.
 */
public class TriviaQADatabaseTest {

    private TriviaQADatabase myTriviaDatabase;
    private ArrayList<QuestionAnswer1> list;

    /**
     * Initializes the fields before each test.
     */
    @BeforeEach
    public void setUp() {
        myTriviaDatabase = new TriviaQADatabase();
        list = new ArrayList<>();
    }

    /**
     * Tests the getQAFromDataBase() method of the TriviaQADatabase class.
     */
    @Test
    public void testGetQAFromDataBase() {
        myTriviaDatabase.getQAFromDataBase(list);
    }

    /**
     * Tests the getData() method of the TriviaQADatabase class.
     */
    @Test
    public void testGetData() {
        assertNotEquals(null, myTriviaDatabase.getData());
    }
}

