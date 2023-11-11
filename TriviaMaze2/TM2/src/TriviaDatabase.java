import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TriviaDatabase {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        // Establish connection (creates db file if it does not exist)
        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:questions.db");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Opened database successfully");

        // Now create a table with fields: ID, QUESTION, ANSWER, HINT, TYPE
        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "QUESTION TEXT NOT NULL, " +
            "ANSWER TEXT NOT NULL, " +
            "HINT TEXT, " +
            "TYPE TEXT )";
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Created questions table successfully");

        // Next, insert two rows of data using the insertQuestion method
        System.out.println("Attempting to insert rows into questions table");

        insertQuestion(ds, "What is the capital of France?", "Paris", null, "M");
        insertQuestion(ds, "Who wrote 'Romeo and Juliet'?", "William Shakespeare", null, "T");
        insertQuestion(ds, "The Earth is flat.", "False", "Think about scientific evidence", "TF");

        // Now query the database table for all its contents and display the results
        System.out.println("Selecting all rows from questions table");
        query = "SELECT * FROM questions";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            // Walk through each 'row' of results, grab data by column/field name
            // and print it
            while (rs.next()) {
                int id = rs.getInt("ID");
                String question = rs.getString("QUESTION");
                String answer = rs.getString("ANSWER");
                String hint = rs.getString("HINT");
                String type = rs.getString("TYPE");

                System.out.println("Result: ID = " + id +
                    ", Question = " + question +
                    ", Answer = " + answer +
                    ", Hint = " + hint +
                    ", Type = " + type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

    // Helper method to insert a question into the table
    private static void insertQuestion(SQLiteDataSource ds, String question, String answer, String hint, String type) {
        String query = String.format("INSERT INTO questions (QUESTION, ANSWER, HINT, TYPE) VALUES ('%s', '%s', '%s', '%s')",
            question, answer, hint, type);

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query);
            System.out.println("executeUpdate() returned " + rv);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
