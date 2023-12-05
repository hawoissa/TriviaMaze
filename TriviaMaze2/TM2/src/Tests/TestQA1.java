package Tests;/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */

import Model.QuestionAnswer1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A tester class to test QuestionAnswer1.
 * @author Matiullah Jalal.
 * @version 24, November 2023.
 */
public class TestQA1 {
    private final int myID;
    /** Holds Type of the question. */
    private final String myType;
    /** Holds the question. */
    private final String myQuestion;
    /** Holds an answer for the question. */
    private final String myAnswer;
    /** An arraylist question. */
    private final ArrayList<QuestionAnswer1> myQAArraylist;

    QuestionAnswer1 qa;


    public TestQA1(){
        myID = 1;
        myType = "Multiple";
        myQuestion = "How many states does USA has?";
        myAnswer = "50";
        myQAArraylist = new ArrayList<>();
    }

    /**
     * Tests the constructor to set the data.
     */
    @Test
    public void TestConstructor(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        int id = 2;
        String type = "Multiple";
        String question = "This is not a question.";
        String answer = "None";

        QuestionAnswer1 expected = new QuestionAnswer1(id,type,question, answer);
        assertNotEquals(qa,expected);
    }
    /**
     * Test getID.
     */
    @Test
    public void TestGetID(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        int id = 1;
        assertEquals(id, qa.getMyID());
    }
    /**
     * Test getMyType.
     */
    @Test
    public void TestGetType(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        assertNotEquals("Null", qa.getMyType());
    }
    /**
     * Test getMyType.
     */
    @Test
    public void TestGetQuestion(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        assertNotEquals("How many country are their in the world?", qa.getMyQuestion());
    }
    /**
     * Test getMyType.
     */
    @Test
    public void TestGeAnswer(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        assertEquals("50", qa.getMyAnswer());
    }
    /**
     * Tests add QuestionAnswer to arrayList.
     */
    @Test
    public void TestAddArraylist(){
        qa = new QuestionAnswer1(myID,myType,myQuestion,myAnswer);
        qa.addToQuestionList(qa);
    }
    /**
     * Tests add QuestionAnswer to arrayList.
     */
    @Test
    public void testGetArraylist() {
        QuestionAnswer1 qa = new QuestionAnswer1(myID, myType, myQuestion, myAnswer);

        // Add the instance to the ArrayList
        myQAArraylist.add(qa);

        // Get the string representation of the ArrayList
        String result = String.valueOf(qa.getMyQuestionArraylist());

        // Assert that the string representation does not match the expected string
        assertNotEquals("[ID: 1\n" +
                "Type: Multiple\n" +
                "Question: What is the capital of France?\n" +
                "Answer: Paris\n" +
                "]", result);
    }



}
