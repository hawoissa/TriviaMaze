package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * StatsPanel class represents the panel displaying game statistics in the maze game.
 */
public class StatsPanel extends JPanel {

    /**
     * Panel containing various statistics labels.
     */
    private final JPanel myStatsPanel;

    /**
     * The maze associated with the StatsPanel.
     */
    private final Maze myCurrentMaze;

    /**
     * Label displaying the elapsed time in the game.
     */
    private final JLabel myTimerLabel = new JLabel("00:00"); // Timer label initialization

    /**
     * Delay for the timer in milliseconds.
     */
    private final int myDelay = 1000;

    /**
     * Elapsed time in the game.
     */
    private int myElapsedTime;

    /**
     * Minutes component of the elapsed time.
     */
    private int myMinutes;

    /**
     * Seconds component of the elapsed time.
     */
    private int mySeconds;

    /**
     * Timer for updating the elapsed time label.
     */
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

    /**
     * Label displaying the player's name.
     */
    private JLabel playerNameLabel;

    /**
     * Label displaying a custom message.
     */
    private final JLabel customMessageLabel = createStyledLabel("Hope you enjoy the game!");

    /**
     * Label displaying the number of doors unlocked in the current room.
     */
    private JLabel chancesLabel;

    /**
     * Constructs a StatsPanel object with the given maze.
     *
     * @param theMaze The maze associated with the StatsPanel.
     */
    public StatsPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myStatsPanel = new JPanel();
        myStatsPanel.setLayout(new BoxLayout(myStatsPanel, BoxLayout.Y_AXIS)); // Stacked vertically
        myStatsPanel.setPreferredSize(new Dimension(205, 550)); // Adjusted height (240, 100)
        myStatsPanel.setBackground(new Color(64, 79, 107)); // Dark Blue

        myTimerLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        myTimerLabel.setForeground(Color.WHITE);

        playerNameLabel = createStyledLabel("Player: ");

        // Set the maximum width for chancesLabel
        chancesLabel = createStyledLabel("Doors unlocked in current room: " +
                myCurrentMaze.getMyCurrentRoom().getDoors());

        // Add user information labels to the panel
        playerNameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        chancesLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        customMessageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        myStatsPanel.add(myTimerLabel); // Add myTimerLabel to the panel
        myStatsPanel.add(playerNameLabel);
        myStatsPanel.add(chancesLabel);
        myStatsPanel.add(customMessageLabel);

        // Start the timer if the game is on
        if (myCurrentMaze.isGameOn()) {
            myDisTimer.start();
        }
    }

    /**
     * Creates a styled JLabel with HTML formatting.
     *
     * @param text The text content of the label.
     * @return The styled JLabel.
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel("<html><div style='width: 150px;'>" + text + "</div></html>");
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    /**
     * Gets the StatsPanel.
     *
     * @return The StatsPanel.
     */
    public JPanel getMyStatsPanelPanel() {
        return myStatsPanel;
    }

    /**
     * Updates the timer label and chances label.
     */
    private void updateTimerLabel() {
        long totalTimeMillis = myCurrentMaze.getTotalTime() * 60 * 1000;
        long minutes = (totalTimeMillis / 1000) / 60;
        long seconds = (totalTimeMillis / 1000) % 60;

        String formattedTime = String.format("%d:%02d", minutes, seconds);
        myTimerLabel.setText("Total Time: " + formattedTime);
        chancesLabel.setText(("Doors unlocked in current room: " +
                myCurrentMaze.getMyCurrentRoom().getDoors()));
    }

    /**
     * Gets the timer label.
     *
     * @return The timer label.
     */
    public JLabel getTimerLabel() {
        return myTimerLabel;
    }

    /**
     * Gets the display timer.
     *
     * @return The display timer.
     */
    public Timer getDisTimer() {
        return myDisTimer;
    }
}