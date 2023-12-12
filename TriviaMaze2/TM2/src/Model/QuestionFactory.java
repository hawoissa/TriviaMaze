package Model;

/**
 * A question factory class that creates object of Data base,
 * that all question from database will be accessible from
 * the factory class.
 */
public class QuestionFactory {
    /** Holds database object.*/
    public final TriviaQADatabase myDataBase;
    /** A constructor to initialize te fields.*/
     QuestionFactory(){
        myDataBase = new TriviaQADatabase();
    }
}
