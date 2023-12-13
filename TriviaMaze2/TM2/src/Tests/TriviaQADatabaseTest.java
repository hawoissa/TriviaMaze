package Tests;

import Model.QuestionAnswer1;
import Model.TriviaQADatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TriviaQADatabaseTest {
    private TriviaQADatabase myTriviaDatabase;
    private ArrayList<QuestionAnswer1> list;

    @BeforeEach
    public void setUp(){
        myTriviaDatabase = new TriviaQADatabase();
        list = new ArrayList<>();
    }
    @Test
    public void testGetQAFromDataBase(){
        myTriviaDatabase.getQAFromDataBase(list);
    }
    @Test
    public void testGetData() {
        assertNotEquals(null, myTriviaDatabase.getData());
    }
}
