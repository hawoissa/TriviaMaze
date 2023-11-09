package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class QuestionAnswer {
    private String myQuestionString;
    private String myType;
    private Question myQuestion;
    private String myAnswer;
    private ArrayList<Question> myQuestionList;
    private ArrayList<String> myAnswerList;
    private HashMap<String, String>  myQAPairs;

    public QuestionAnswer() {

        myQuestionList = new ArrayList<>();
        myAnswerList = new ArrayList<>();
        myQAPairs = new HashMap<>();
    }


    public void addQAPair(final Question theQuestion, final String theAnswer) {
        myQAPairs.put(theQuestion.getQuestionString(), theAnswer);
    }

    public void addToQuestionList(final Question theQuestion) {
        myQuestionList.add(theQuestion);
    }

    public void addToAnswerList(final String theAnswer) {
        myAnswerList.add(theAnswer);
    }

    public boolean isAnswerCorrect(final Question theQuestion, final String theAnswer) {
        return Objects.equals(myQAPairs.get(theQuestion.getQuestionString()), theAnswer);
    }

    public Question getMyQuestion() {
        return myQuestion;
    }

    public class Question {


        private String myQuestion;
        private String myType;



        Question(String question, String type) {
            this.myQuestion = question;
            this.myType = type;
        }

        public String getQuestionString() {
            return myQuestion;
        }

        public String questionType() {
            return myType;
        }
    }





}
