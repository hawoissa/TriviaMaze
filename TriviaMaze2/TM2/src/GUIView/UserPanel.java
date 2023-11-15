package GUIView;

import Model.Maze;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class UserPanel extends JPanel {
    private final JLabel playerNameLabel;
    private final JLabel scoreLabel;
    private final JLabel gameStatusLabel;
    private final JLabel customMessageLabel;
    private JPanel myUserPanel;
    private Maze myCurrentMaze;

    public UserPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;

        myUserPanel = new JPanel();
        myUserPanel.setLayout(new BoxLayout(myUserPanel, BoxLayout.Y_AXIS));
        myUserPanel.setPreferredSize(new Dimension(350, 150));
        myUserPanel.setBackground(Color.CYAN);

        playerNameLabel = createFunStyledLabel("Player: ");
        scoreLabel = createFunStyledLabel("Mistakes: ");
        gameStatusLabel = createFunStyledLabel("Game Status: ");
        customMessageLabel = createFunStyledLabel("Hope you enjoy the game!");

        // Add JLabels to the panel
        myUserPanel.add(playerNameLabel);
        myUserPanel.add(scoreLabel);
        myUserPanel.add(gameStatusLabel);
        myUserPanel.add(customMessageLabel);

        updateUserInfo("Example", 3, "20");
    }

    // Helper method to update and display user information
    public void updateUserInfo(String playerName, int score, String gameStatus) {
        playerNameLabel.setText("Player: " + playerName);
        scoreLabel.setText("Mistakes: " + score);
        gameStatusLabel.setText("Game Status: " + gameStatus);
        customMessageLabel.setText("Hope you enjoy the game!");
        repaint();
    }

    private JLabel createFunStyledLabel(String text) {
        JLabel label = new JLabel(text);
        Color backgroundColor = new Color(135, 206, 250); // Light Sky Blue
        label.setBackground(backgroundColor);

        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        return label;
    }

    public JPanel getUserPanel() {
        return myUserPanel;
    }
}