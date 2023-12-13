package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class QAPanel extends JPanel {

    private JPanel myQAPanel;
    private Maze myCurrentMaze;
    private JLabel myRoomLabel;
    private JLabel myQuestionLabel;
    private JPanel myAnswerBoxPanel;
   private Room currentRoom;
   private JLabel chancesLabel;

    public QAPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myQAPanel = new JPanel();
        myQAPanel.setPreferredSize(new Dimension(250, 550)); // previously 250 by 250
        myQAPanel.setBackground(new Color(240, 220, 30)); // Light Orange

        myRoomLabel = new JLabel();
        myRoomLabel.setText(Character.toString('A'));

        currentRoom= myCurrentMaze.getMyCurrentRoom();

        myQuestionLabel = new JLabel("Question: ");
        updateQuestionLabel(myCurrentMaze.getCurrentQuestion());

        myAnswerBoxPanel = new JPanel();  // Use a JPanel instead of a JTextField
        chancesLabel = new JLabel("Doors unlocked: " + currentRoom.getDoors());
        myAnswerBoxPanel.add(chancesLabel);

        setMyAnswerBox(currentRoom.getCurrentQuestionType());
        setupLayout();
    }


    private void setupLayout() {
        myQAPanel.setLayout(new BorderLayout());

        // Center Panel for Question and Answer
        JPanel centerPanel = new JPanel();
//        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBackground(new Color(255, 221, 153));

        //centerPanel.add(Box.createVerticalStrut(20)); // Add spacing
        centerPanel.add(myQuestionLabel);
        centerPanel.add(Box.createVerticalStrut(10)); // Add spacing
        centerPanel.add(myAnswerBoxPanel);  // Use myAnswerBoxPanel instead of myAnswerBox

        myQAPanel.add(myRoomLabel, BorderLayout.NORTH);
        myQAPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
        myQuestionLabel.setText("Question: " + myCurrentMaze.getCurrentQuestion());
    }

    // Add a method to display a reaction based on the answer correctness
    private void displayAnswerReaction(boolean isAnswerCorrect) {
        if (isAnswerCorrect) {
            JOptionPane.showMessageDialog(this, "Correct! You can move.");
        } else {
            JOptionPane.showMessageDialog(this, "Wrong! The door is locked.");
        }
    }

    // Modify the handleAnswerSelection method to call the answerQuestion method and display a reaction

    private void handleAnswerSelection(String selectedAnswer) {
        if (myCurrentMaze.isGameOn()) {
            boolean isAnswerCorrect = myCurrentMaze.answerQuestion(selectedAnswer);
            displayAnswerReaction(isAnswerCorrect);

            if (!isAnswerCorrect) {

                if (currentRoom.getDoors() == 0) {
                    // Game over condition
                    JOptionPane.showMessageDialog(this, "Game Over! You've locked all doors.");
                    myCurrentMaze.resetGame();
                    // Optionally, you can restart the game or take other actions
                }
                chancesLabel.setText("Doors unlocked: " + currentRoom.getDoors());
            }

            if (isAnswerCorrect) {
                // If the answer is correct, update the maze panel
                updateContent();
            }
        } else {
            // Optionally, show a message to inform the user that the game is not on
            JOptionPane.showMessageDialog(this, "The game is not currently running.");
        }
    }

    // Modify the updateContent method to set the answer box based on the current question type
    public void updateContent() {
        currentRoom = myCurrentMaze.getMyCurrentRoom();
        myRoomLabel.setText(Character.toString(currentRoom.getLetter()));
        String currentQuestionType = currentRoom.getCurrentQuestionType();
        updateQuestionLabel(currentRoom.getQuestion());
        setMyAnswerBox(currentQuestionType);
        updateChancesLabel();
    }

    // Add a method to set the answer box based on the question type
    private void setMyAnswerBox(String questionType) {
        // Clear the components inside myAnswerBoxPanel
        myAnswerBoxPanel.removeAll();

        // Modify the existing code based on the question type
        if (questionType.equalsIgnoreCase("M")) {
            String[] options = {"A", "B", "C"};
            for (String option : options) {
                JButton optionButton = new JButton(option);
                optionButton.addActionListener(e -> handleAnswerSelection(option));
                myAnswerBoxPanel.add(optionButton);
            }
        } else if (questionType.equalsIgnoreCase("TF")) {
            String[] options = {"True", "False"};
            for (String option : options) {
                JButton optionButton = new JButton(option);
                optionButton.addActionListener(e -> handleAnswerSelection(option));
                myAnswerBoxPanel.add(optionButton);
            }
        } else {
            JLabel answerLabel = new JLabel("Answer: ");
            JTextField answerTextField = new JTextField();
            answerTextField.setPreferredSize(new Dimension(80, 40));
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(e -> handleAnswerSelection(answerTextField.getText())); // Replace with your actual method

            myAnswerBoxPanel.add(answerLabel);
            myAnswerBoxPanel.add(answerTextField);
            myAnswerBoxPanel.add(submitButton);
        }
        chancesLabel.setText("Doors unlocked: " + currentRoom.getDoors());
        myAnswerBoxPanel.add(chancesLabel);
        myAnswerBoxPanel.revalidate();
        myAnswerBoxPanel.repaint();
    }

    private void updateQuestionLabel(String currentQuestion) {
        int maxCharactersPerLine = 30; // Adjust this value as needed
        String formattedText = "<html>" + currentQuestion.replaceAll("(.{" +
                maxCharactersPerLine + "})", "$1<br>") + "</html>";
        myQuestionLabel.setText(formattedText);
        //myQuestionLabel.setText("Question: " + currentQuestion);
    }

    private void updateChancesLabel() {
        chancesLabel.setText("Doors unlocked: " + currentRoom.getDoors());
        myAnswerBoxPanel.revalidate();
        myAnswerBoxPanel.repaint();
    }

    public JPanel getQAPanel() {
        return myQAPanel;
    }
}
