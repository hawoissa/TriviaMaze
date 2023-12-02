package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;

public class QAPanel extends JPanel {

    private JPanel myQAPanel;
    private Maze myCurrentMaze;

    private JLabel myRoomLabel;
    private JLabel myQuestionLabel;
    private JTextField myAnswerBox;

    public QAPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myQAPanel = new JPanel();
        myQAPanel.setPreferredSize(new Dimension(150, 150));
        myQAPanel.setBackground(new Color(255, 221, 153)); // Light Orange

        myRoomLabel = new JLabel();
        myRoomLabel.setText(Character.toString('A'));

        myQuestionLabel = new JLabel("Question: ");
        myAnswerBox = new JTextField();
        myAnswerBox.setSize(new Dimension(30, 30));

        setupLayout();
    }

    private void setupLayout() {
        myQAPanel.setLayout(new BorderLayout());

        // Center Panel for Question and Answer
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(255, 221, 153));

        centerPanel.add(Box.createVerticalStrut(20)); // Add spacing
        centerPanel.add(myQuestionLabel);
        centerPanel.add(Box.createVerticalStrut(10)); // Add spacing
        centerPanel.add(myAnswerBox);

        myQAPanel.add(myRoomLabel, BorderLayout.NORTH);
        myQAPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
        myQuestionLabel.setText("Question: " + myCurrentMaze.getCurrentQuestion());
    }

    public void setMyAnswerBox() {
        String type = myCurrentMaze.getMyCurrentRoom().getCurrentQuestionType();
        myAnswerBox.removeAll();
        if (type.equalsIgnoreCase("M")) {
            String[] options = {"A", "B", "C"};
            for (String option : options) {
                JButton optionButton = new JButton(option);
                optionButton.addActionListener(e -> handleAnswerSelection(option));
                myAnswerBox.add(optionButton);
            }
        } else if (type.equalsIgnoreCase("TF")) {
            String[] options = {"True", "False"};
            for (String option : options) {
                JButton optionButton = new JButton(option);
                optionButton.addActionListener(e -> handleAnswerSelection(option));
                myAnswerBox.add(optionButton);
            }
        } else {
            JTextField answerTextField = new JTextField();
            myAnswerBox.add(answerTextField);
        }
        myAnswerBox.revalidate();
        myAnswerBox.repaint();
    }

    private void handleAnswerSelection(String selectedAnswer) {
        System.out.println("Selected answer: " + selectedAnswer);
    }

    public void updateContent() {
        // Get the current room in the maze
        Room currentRoom = myCurrentMaze.getMyCurrentRoom();

        // Update the room letter label
        changeRoomLetter(currentRoom);
        String currentQuestion = myCurrentMaze.getMyCurrentRoom().getCurrentQuestionType();
        updateQuestionLabel(currentQuestion);
        setMyAnswerBox();
    }

    private void updateQuestionLabel(String currentQuestion) {
        myQuestionLabel.setText("Question: " + currentQuestion);
    }

    public JPanel getQAPanel() {
        return myQAPanel;
    }
}
