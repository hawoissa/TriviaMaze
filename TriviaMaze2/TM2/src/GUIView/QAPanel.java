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
    }

    public JPanel getQAPanel() {
        return myQAPanel;
    }
}
