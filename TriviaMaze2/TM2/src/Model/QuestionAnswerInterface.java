package Model;

import java.util.ArrayList;

public interface QuestionAnswerInterface {
    /**
     * Getter gets the ID.
     * @return returns the ID.
     */
    public int getMyID();
    /**
     * Getter gets the question type.
     * @return returns the type.
     */
    public String getMyType();
    /**
     * Getter gets the question.
     * @return returns the question.
     */
    public String getMyQuestion();
    /**
     * Getter gets answer of a question.
     * @return returns the answer.
     */
    public String getMyAnswer();
    /**
     * Setter to add questions to arraylist.
     */
    public void addToQuestionList(final QuestionAnswer1 theQuestion);
    /**
     * Getter gets the arraylist of question.
     * @return returns the arraylist.
     */
    public ArrayList<QuestionAnswer1> getMyQuestionArraylist();

}
