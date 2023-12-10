package Model;/*
    Names: Matiullah Jalal, Zakirye Luqman, Hawo Issa
    Course: TCSS 360
    Quarter: Winter 2023
 */

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class creates questions and answers database.
 * @version 27 November 2023.
 */
public class TriviaQADatabase {
    /** Holds sqlite data source . */
    SQLiteDataSource ds = null;
    ArrayList<QuestionAnswer1> data;
    /**
     * Constructor initializes the fields.
     */
    public TriviaQADatabase(){
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:question.db");
            // will create database table if not exits one
            createTable();
            data=new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Opened data base successfully");
    }

    public void initializeDatabase() {
        try {
            // Create the table if it doesn't exist
            createTable();

            // Insert rows into the database
            insertRowsData();

            // Retrieve data from the database and populate the ArrayList
            getQAFromDataBase(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Initialized database successfully");
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
         String query2 = "DROP TABLE IF EXISTS questions";
         String query = "CREATE TABLE IF NOT EXISTS questions (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "QUESTION TEXT NOT NULL, " +
                "ANSWER TEXT NOT NULL, " +
                "HINT TEXT, " +
                "TYPE TEXT )";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv2 = stmt.executeUpdate(query2);
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
                                final String theType) {
        String selectQuery = "SELECT COUNT(*) FROM questions WHERE QUESTION = ?";
        String insertQuery = "INSERT INTO questions (QUESTION, ANSWER, HINT, TYPE) VALUES (?, ?, ?, ?)";

        try (Connection conn = theDs.getConnection();
             PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            // Check if the question already exists
            selectStmt.setString(1, theQuestion);
            ResultSet resultSet = selectStmt.executeQuery();
            resultSet.next();
            int questionCount = resultSet.getInt(1);

            if (questionCount == 0) {
                // If the question does not exist, insert it
                insertStmt.setString(1, theQuestion);
                insertStmt.setString(2, theAnswer);
                insertStmt.setString(3, theHint);
                insertStmt.setString(4, theType);

                int rv = insertStmt.executeUpdate();
                System.out.println("executeUpdate() returned " + rv);
            } else {
                // Question already exists, handle accordingly (e.g., log a message)
                System.out.println("Question already exists: " + theQuestion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Insert rows of data using the insertQuestion method.
     */
    public void insertRowsData() {
        System.out.println("Attempting to insert rows into questions table");
        insertQuestion(ds, "Who wrote (Romeo and Juliet)?",
                "William Shakespeare", "null", "Text");
        insertQuestion(ds, "The Earth is flat.?", "False",
                "Think about scientific evidence", "TF");
        insertQuestion(ds, "Texas is the largest state by area?", "False",
                null, "TF");
        insertQuestion(ds, "Mount Everest is the highest mountain in the world. (True/False)",
                "True", null, "TF");
        insertQuestion(ds, "China is the largest country by population in the world.",
                "True", null, "TF");
        insertQuestion(ds, "C.P.U stands for central processing unit.",
                "True", null, "TF");
        insertQuestion(ds, "Java is a high-level language.",
                "True", null, "TF");
        insertQuestion(ds, "To use SQLite in Java, do we need to import its library?",
                "True", null, "TF");
        insertQuestion(ds, "The Great Wall of China is visible from space. (True/False)",
                "False", null, "TF");
        insertQuestion(ds, "The currency of India is the Dollar. (True/False)",
                "False", null, "TF");
        moreQuestions();
        insertMoreTextQuestions();
    }

    private void moreQuestions() {
        insertQuestion(ds, "What is the nickname of Washington State? A. The Sunshine State, B. The Evergreen State, C. The Peach State",
                "B", null, "M");
        insertQuestion(ds, "What is an algorithm? A. Mathematical equation, B. Step by Step Solution, C. Chemical substance",
                "B", null, "M");
        insertQuestion(ds, "Who invented the electric bulb? A. Nikola Tesla, B. Thomas Edison, C. Marie Curie",
                "B", null, "M");
        insertQuestion(ds, "What is the capital of Japan? A. Beijing, B. Seoul, C. Tokyo",
                "C", null, "M");
        insertQuestion(ds, "Who painted the Mona Lisa? A. Vincent van Gogh, B. Pablo Picasso, C. Leonardo da Vinci",
                "C", null, "M");
        insertQuestion(ds, "Which planet is known as the Red Planet? A. Venus, B. Mars, C. Jupiter",
                "B", null, "M");
        insertQuestion(ds, "Who wrote the play 'Hamlet'? A. Charles Dickens, B. William Shakespeare, C. Jane Austen",
                "B", null, "M");
        insertQuestion(ds, "What is the square root of 144? A. 12, B. 15, C. 18",
                "A", null, "M");
        insertQuestion(ds, "Who developed the theory of relativity? A. Isaac Newton, B. Marie Curie, C. Albert Einstein",
                "C", null, "M");
        insertQuestion(ds, "What is the largest mammal in the world? A. Elephant, B. Blue Whale, C. Giraffe",
                "B", null, "M");
        insertQuestion(ds, "In which year did World War II end? A. 1943, B. 1945, C. 1947",
                "B", null, "M");
        insertQuestion(ds, "The Eiffel Tower is located in which city? A. Paris, B. London, C. Rome",
                "A", null, "M");
        insertQuestion(ds, "Who wrote 'To Kill a Mockingbird'? A. Mark Twain, B. Harper Lee, C. J.K. Rowling",
                "B", null, "M");
        insertQuestion(ds, "The human heart has how many chambers? A. Two, B. Four, C. Six",
                "B", null, "M");
        insertQuestion(ds, "What is the largest ocean on Earth? A. Atlantic, B. Pacific, C. Indian",
                "B", null, "M");
        insertQuestion(ds, "Who is known as the 'Father of Computer Science'? A. Alan Turing, B. Bill Gates, C. Steve Jobs",
                "A", null, "M");
        insertQuestion(ds, "What is the capital of France? A. Paris, B. London, C. Berlin",
                "A", "null", "M");
        insertQuestion(ds, "How many other countries does the U.S. share a land border with? A. One, B. Two, C. Three",
                "B", "null", "M");
        insertQuestion(ds, "What do the stripes of the American flag represent? A. States, B. Colonies, C. Provinces",
                "B", null, "M");
    }

    private void insertMoreTextQuestions() {
        // Insert questions with short answers
        insertQuestion(ds, "What is the capital of Spain?", "Madrid", null, "Text");
        insertQuestion(ds, "Name a primary color.", "Red", null, "Text");
        insertQuestion(ds, "What is the largest planet in our solar system?",
                "Jupiter", null, "Text");
        insertQuestion(ds, "What is the currency of Japan?", "Yen",
                null, "Text");
        insertQuestion(ds, "Who wrote '1984'?", "George Orwell", null, "Text");
        insertQuestion(ds, "What is the main ingredient in guacamole?",
                "Avocado", null, "Text");
        insertQuestion(ds, "Which gas do plants absorb from the atmosphere?",
                "Carbon dioxide", null, "Text");
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

    public ArrayList<QuestionAnswer1> getData() {
        return data;
    }
}

