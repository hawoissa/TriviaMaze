/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */
package Model;
import java.util.ArrayList;

/**
 * This class creates questions, question types, and relevant answers.
 * @author Matiullah Jalal.
 * @version 24 November 2023.
 */
public class QuestionAnswer1 implements QuestionAnswerInterface{
    /** Holds ID of the question. */
    private final int myID;
    /** Holds Type of the question. */
    private final String myType;
    /** Holds the question. */
    private final String myQuestion;
    /** Holds an answer for the question. */
    private final String myAnswer;
    /** An arraylist question. */
    private final ArrayList<QuestionAnswer1> myQuestionArraylist;



    /**
     * Constructor initializes the fields.
     * @param theID is the other ID.
     * @param theType is the type of the question.
     * @param theQa is the question.
     * @param theAn is the answer.
     */
    public QuestionAnswer1(final int theID, final String theType,
                           final String theQa, final String theAn){
        myID = theID;
        myType = theType;
        myQuestion = theQa;
        myAnswer = theAn;
        myQuestionArraylist = new ArrayList<>();
    }

    /**
     * Getter gets the ID.
     * @return returns the ID.
     */
    @Override
    public int getMyID(){
        return myID;
    }
    /**
     * Getter gets the question type.
     * @return returns the type.
     */
    public String getMyType(){
        return myType;
    }
    /**
     * Getter gets the question.
     * @return returns the question.
     */
    public String getMyQuestion(){
        return myQuestion;
    }

    /**
     * Getter gets answer of a question.
     * @return returns the answer.
     */
    public String getMyAnswer(){
        return myAnswer;
    }

    /**
     * Setter to add questions to arraylist.
     */
    public void addToQuestionList(final QuestionAnswer1 theQuestion) {
        myQuestionArraylist.add(theQuestion);
    }
    /**
     * Getter gets the arraylist of question.
     * @return returns the arraylist.
     */
    public ArrayList<QuestionAnswer1> getMyQuestionArraylist(){
        return myQuestionArraylist;
    }

    /**
     * To string to print data on console.
     * @return returns string.
     */
    public String toString(){
        StringBuilder array = new StringBuilder();
        for(int i = 0; i < myQuestionArraylist.size()-1; i++){
            array.append(myQuestionArraylist.get(i));
        }
        return "ID: " + myID + "\nType: " + myType
                + "\nQuestion: " + myQuestion + "\nAnswer: "
                + myAnswer + "\n ALL Array Contents: \n" + array;
    }



}
