package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * QAPanel class represents the panel displaying the current question and answer options in the maze game.
 */
public class QAPanel extends JPanel {

    /**
     * The main panel containing question and answer-related components.
     */
    private JPanel myQAPanel;

    /**
     * The maze associated with the QAPanel.
     */
    private Maze myCurrentMaze;

    /**
     * Label displaying the current room letter.
     */
    private JLabel myRoomLabel;

    /**
     * Label displaying the current question.
     */
    private JLabel myQuestionLabel;

    /**
     * Panel containing components related to answer options.
     */
    private JPanel myAnswerBoxPanel;

    /**
     * The current room in the maze.
     */
    private Room currentRoom;

    /**
     * Constructs a QAPanel object with the given maze.
     *
     * @param theMaze The maze associated with the QAPanel.
     */
    public QAPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myQAPanel = new JPanel();
        myQAPanel.setPreferredSize(new Dimension(250, 550)); // previously 250 by 250
        myQAPanel.setBackground(new Color(240, 220, 30)); // Light Orange

        myRoomLabel = new JLabel();
        myRoomLabel.setText(Character.toString('A'));

        currentRoom = myCurrentMaze.getMyCurrentRoom();

        myQuestionLabel = new JLabel("Question: ");
        updateQuestionLabel(myCurrentMaze.getCurrentQuestion());

        myAnswerBoxPanel = new JPanel();

        setMyAnswerBox(currentRoom.getCurrentQuestionType());
        setupLayout();
    }

    /**
     * Sets up the layout of the QAPanel.
     */
    private void setupLayout() {
        myQAPanel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBackground(new Color(255, 221, 153));

        centerPanel.add(myQuestionLabel);
        centerPanel.add(Box.createVerticalStrut(10)); // Add spacing
        centerPanel.add(myAnswerBoxPanel);

        myQAPanel.add(myRoomLabel, BorderLayout.NORTH);
        myQAPanel.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Changes the room letter and updates the question label.
     *
     * @param theRoom The room to change to.
     */
    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
        myQuestionLabel.setText("Question: " + myCurrentMaze.getCurrentQuestion());
    }

    /**
     * Displays a reaction based on the answer correctness.
     *
     * @param theAnswerCorrect True if the answer is correct, false otherwise.
     */
    private void displayAnswerReaction(final boolean theAnswerCorrect) {
        if (theAnswerCorrect) {
            JOptionPane.showMessageDialog(this, "Correct! You can move.");
        } else {
            JOptionPane.showMessageDialog(this, "Wrong! The door is locked.");
        }
    }

    /**
     * Handles the selection of an answer and displays a reaction.
     *
     * @param theSelectedAnswer The selected answer.
     */
    private void handleAnswerSelection(final String theSelectedAnswer) {
        if (myCurrentMaze.isGameOn()) {
            boolean isAnswerCorrect = myCurrentMaze.answerQuestion(theSelectedAnswer);
            displayAnswerReaction(isAnswerCorrect);

            if (!isAnswerCorrect && currentRoom.getDoors() == 0) {
                JOptionPane.showMessageDialog(this, "Game Over! You've locked all doors.");
                myCurrentMaze.resetGame();
            }

            if (isAnswerCorrect && myCurrentMaze.getMyCurrentRoom().getLetter() == 'P') {
                JOptionPane.showMessageDialog(this, "Game Won!!!");
            }

            updateContent();
        } else {
            JOptionPane.showMessageDialog(this, "The game is not currently running.");
        }
    }

    /**
     * Updates the content of the QAPanel.
     */
    public void updateContent() {
        currentRoom = myCurrentMaze.getMyCurrentRoom();
        myRoomLabel.setText(Character.toString(currentRoom.getLetter()));
        String currentQuestionType = currentRoom.getCurrentQuestionType();
        updateQuestionLabel(currentRoom.getQuestion());
        setMyAnswerBox(currentQuestionType);
    }

    /**
     * Sets the answer box based on the question type.
     *
     * @param theQuestionType The type of the current question.
     */
    private void setMyAnswerBox(final String theQuestionType) {
        myAnswerBoxPanel.removeAll();

        if (theQuestionType.equalsIgnoreCase("M") || theQuestionType.equalsIgnoreCase("TF")) {
            String[] options = theQuestionType.equalsIgnoreCase("M") ? new String[]{"A", "B", "C"} : new String[]{"True", "False"};
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
            submitButton.addActionListener(e -> handleAnswerSelection(answerTextField.getText()));

            myAnswerBoxPanel.add(answerLabel);
            myAnswerBoxPanel.add(answerTextField);
            myAnswerBoxPanel.add(submitButton);
        }

        myAnswerBoxPanel.revalidate();
        myAnswerBoxPanel.repaint();
    }

    /**
     * Updates the question label with formatted text.
     *
     * @param theCurrentQuestion The current question text.
     */
    private void updateQuestionLabel(final String theCurrentQuestion) {
        int maxCharactersPerLine = 30;
        String formattedText = "<html>" + theCurrentQuestion.replaceAll("(.{" +
                maxCharactersPerLine + "})", "$1<br>") + "</html>";
        myQuestionLabel.setText(formattedText);
    }

    /**
     * Gets the QAPanel.
     *
     * @return The QAPanel.
     */
    public JPanel getQAPanel() {
        return myQAPanel;
    }
}
