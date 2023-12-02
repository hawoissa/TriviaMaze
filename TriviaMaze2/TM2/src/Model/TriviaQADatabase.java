package Model;/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class creates questions and answers database.
 * @version 27 November 2023.
 */
public class TriviaQADatabase {
    /** Holds sqlite data source . */
    SQLiteDataSource ds = null;

    /**
     * Constructor initializes the fields.
     */
    public TriviaQADatabase(){
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:question.db");
            // will create database table if not exits one
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Opened data base successfully");
    }

    public void closeDsConnection() throws SQLException {
        if(ds != null){
            ds.getConnection().close();
        }
        ds = null;
    }

    /**
     * Creates database table.
     */
    private void createTable() {
        // Now creates a table with fields: ID, QUESTION, ANSWER, HINT, TYPE
         String query = "CREATE TABLE IF NOT EXISTS questions (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "QUESTION TEXT NOT NULL, " +
                "ANSWER TEXT NOT NULL, " +
                "HINT TEXT, " +
                "TYPE TEXT )";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Created questions table successfully");
    }

    /**
     * Insert data into table.
     * @param theDs is the ID.
     * @param theQuestion is the question.
     * @param theAnswer is the answer.
     * @param theHint is the hint for an answer.
     * @param theType is the type of question.
     */
    private void insertQuestion(final SQLiteDataSource theDs, final String theQuestion,
                                final String theAnswer, final String theHint,
                                final String theType){
        String query = String.format("INSERT INTO questions (QUESTION, ANSWER, HINT, TYPE) VALUES ('%s','%s','%s','%s')",
                theQuestion, theAnswer, theHint, theType);
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * Insert rows of data using the insertQuestion method.
     */
    public void insertRowsData(){
        System.out.println("Attempting to insert rows into questions table");
        insertQuestion(ds, "What is the capital of France?", "Paris",
                "null", "M");
        insertQuestion(ds, "Who wrote (Romeo and Juliet)?",
                "William Shakespeare", "null", "TF");
        insertQuestion(ds, "The Earth is flat.?", "False",
                "Think about a scientific evidence", "TF");
        insertQuestion(ds,"How many other countries does U.S. share a land border with?",
                "Two", "null", "M");
        insertQuestion(ds,"Texas is the largest state by area?",
                "False", "null", "TF");
        insertQuestion(ds,"What do the stripes of the American flag represents?",
                "13 colonies", "null", "M");
        moreQuestions();
    }

    /**
     * Gets question, answer, type and ID from database.
     */
    public void getQAFromDataBase(ArrayList<QuestionAnswer1> qa){
        // Now query the database table for all its contents
        String query = "SELECT * FROM questions;";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("ID");
                String question = rs.getString("QUESTION");
                String answer = rs.getString("ANSWER");
                String hint = rs.getString("HINT");
                String type = rs.getString("TYPE");
                // an object of QuestionAnswer1
                QuestionAnswer1 newQA = new QuestionAnswer1(id,type,question,answer);
                // add qa into the arraylist
                qa.add(newQA);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void moreQuestions() {
        insertQuestion(ds,"What is the nickname of Washington State?",
            "The Evergreen State", "null", "M");
        insertQuestion(ds,"China is the largest country by population in the world.",
            "T", "null", "TF");
        insertQuestion(ds,"C.P.U stands for central processing unit.",
            "T", "null", "TF");
        insertQuestion(ds,"C.P.U stands for central processing unit.",
            "T", "null", "TF");
        insertQuestion(ds,"What is algorithm?",
            "Step by Step Solution", "null", "M");
        insertQuestion(ds,"Who invented electricity bulb?",
            "Thomas Edison", "null", "M");
        insertQuestion(ds,"Java is a high-level language.",
            "T", "null", "TF");
        insertQuestion(ds,"To use sqlite in java we need to import its library.",
            "T", "null", "TF");
        insertQuestion(ds, "What is the capital of Japan?",
            "Tokyo", "null", "M");
        insertQuestion(ds, "Who painted the Mona Lisa?",
            "Leonardo da Vinci", "null", "M");
        insertQuestion(ds, "The Great Wall of China is visible from space. (True/False)",
            "False", "null", "TF");
        insertQuestion(ds, "Which planet is known as the Red Planet?",
            "Mars", "null", "M");
        insertQuestion(ds, "Who wrote the play 'Hamlet'?",
            "William Shakespeare", "null", "M");
        insertQuestion(ds, "The currency of India is the Dollar. (True/False)",
            "False", "null", "TF");
        insertQuestion(ds, "What is the square root of 144?",
            "12", "null", "M");
        insertQuestion(ds, "Who developed the theory of relativity?",
            "Albert Einstein", "null", "M");
        insertQuestion(ds, "What is the largest mammal in the world?",
            "Blue Whale", "null", "M");
        insertQuestion(ds, "In which year did World War II end?",
            "1945", "null", "M");
        insertQuestion(ds, "The Eiffel Tower is located in which city?",
            "Paris", "null", "M");
        insertQuestion(ds, "Who wrote 'To Kill a Mockingbird'?",
            "Harper Lee", "null", "M");
        insertQuestion(ds, "The human heart has how many chambers?",
            "Four", "null", "M");
        insertQuestion(ds, "Mount Everest is the highest mountain in the world. (True/False)",
            "True", "null", "TF");
        insertQuestion(ds, "What is the largest ocean on Earth?",
            "Pacific Ocean", "null", "M");
        insertQuestion(ds, "Who is known as the 'Father of Computer Science'?",
            "Alan Turing", "null", "M");
    }
}

