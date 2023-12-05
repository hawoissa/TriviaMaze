package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatsPanel extends JPanel {

    private JButton myStartButton;
    private JPanel myStatsPanel;
    private Maze myCurrentMaze;
    private JLabel myRoomLabel;
    private JLabel myTimerLabel = new JLabel("00:00"); // Timer label initialization

    private final int myDelay = 1000;
    private int myElapsedTime;
    private int myMinutes;
    private int mySeconds;

    private final Timer myDisTimer = new Timer(myDelay, new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent theE) {
            final int firstMgNum = 1000;
            final int secMgNum = 60000;
            final int thirdMgNum = 60;
            final String numberFormat = "%02d";
            myElapsedTime += firstMgNum;
            myMinutes = (myElapsedTime / secMgNum) % thirdMgNum;
            mySeconds = (myElapsedTime / firstMgNum) % thirdMgNum;
            final String minsstring = String.format(numberFormat, myMinutes);
            final String secondsstring = String.format(numberFormat, mySeconds);
            myTimerLabel.setText(minsstring + ":" + secondsstring);
        }
    });

    private JLabel playerNameLabel = createStyledLabel("Player: ");
    private JLabel scoreLabel = createStyledLabel("Mistakes: ");
    private JLabel gameStatusLabel = createStyledLabel("Game Status: ");
    private JLabel customMessageLabel = createStyledLabel("Hope you enjoy the game!");

    public StatsPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myStatsPanel = new JPanel();
        myStatsPanel.setLayout(new BoxLayout(myStatsPanel, BoxLayout.Y_AXIS)); // Stacked vertically
        myStatsPanel.setPreferredSize(new Dimension(240, 100)); // Adjusted height
        myStatsPanel.setBackground(new Color(64, 79, 107)); // Dark Blue

        myTimerLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        myTimerLabel.setForeground(Color.WHITE);

        // Add user information labels to the panel
        playerNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        gameStatusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        customMessageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        myStatsPanel.add(myTimerLabel); // Add myTimerLabel to the panel
        myStatsPanel.add(playerNameLabel);
        myStatsPanel.add(scoreLabel);
        myStatsPanel.add(gameStatusLabel);
        myStatsPanel.add(customMessageLabel);

        // Start the timer if the game is on
        if (myCurrentMaze.isGameOn()) {
            myDisTimer.start();
        }
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
    }

    public JPanel getMyStatsPanelPanel() {
        return myStatsPanel;
    }

    private void updateTimerLabel() {
        long totalTimeMillis = myCurrentMaze.getTotalTime() * 60 * 1000;
        long minutes = (totalTimeMillis / 1000) / 60;
        long seconds = (totalTimeMillis / 1000) % 60;

        String formattedTime = String.format("%d:%02d", minutes, seconds);
        myTimerLabel.setText("Total Time: " + formattedTime);
    }

    public JLabel getTimerLabel() {
        return myTimerLabel;
    }

    public Timer getDisTimer() {
        return myDisTimer;
    }
}