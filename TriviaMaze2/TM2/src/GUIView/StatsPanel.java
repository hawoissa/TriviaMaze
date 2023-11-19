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

    private JLabel myTimerLabel = new JLabel("00:00");
    ;

    /**
     * Sets the delay.
     */
    private final int myDelay = 1000;
    /**
     * Sets the elapsed time to zero.
     */
    private int myElapsedTime;
    /**
     * Sets the minutes to zero.
     */
    private int myMinutes;
    /**
     * Sets the seconds to zero.
     */
    private int mySeconds;

    //private final Timer myTimer;
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
    private Maze maze;

    public StatsPanel(final Maze theMaze) {


        myStartButton = new JButton();

        myCurrentMaze = theMaze;
        myStatsPanel = new JPanel();
        myStatsPanel.setPreferredSize(new Dimension(350, 350));
        myStatsPanel.setBackground(Color.ORANGE);



        myTimerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        setLayout(new FlowLayout(FlowLayout.CENTER));
        myStatsPanel.add(myTimerLabel);

        // Initialize and start the Swing Timer


        myStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDisTimer.start();
            }
        });

        myStatsPanel.add(myStartButton);

        myRoomLabel = new JLabel();
        myRoomLabel.setSize(350,350);
        myRoomLabel.setText(Character.toString('A'));
        myStatsPanel.add(myRoomLabel);
    }

    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
    }




    public JPanel getMyStatsPanelPanel() {
        return myStatsPanel;
    }

    private void updateTimerLabel() {
        long totalTimeMillis = myCurrentMaze.getTotalTime() * 60 * 1000; // Convert total minutes to milliseconds
        long minutes = (totalTimeMillis / 1000) / 60;
        long seconds = (totalTimeMillis / 1000) % 60;

        String formattedTime = String.format("%d:%02d", minutes, seconds);
        myTimerLabel.setText("Total Time: " + formattedTime);
    }

    public JLabel getTimerLabel() {
        return myTimerLabel;
    }


}
