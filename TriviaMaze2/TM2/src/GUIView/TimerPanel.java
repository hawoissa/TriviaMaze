package GUIView;

import Model.Maze;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel {
    private JLabel myTimerLabel;
    private Timer timer;
    private Maze maze;

    public TimerPanel(Maze maze) {
        this.maze = maze;
        myTimerLabel = new JLabel("Total Time: 0:00");
        myTimerLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(myTimerLabel);

        // Initialize and start the Swing Timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimerLabel();
            }
        });
        timer.start();
    }

    private void updateTimerLabel() {
        long totalTimeMillis = maze.getTotalTime() * 60 * 1000; // Convert total minutes to milliseconds
        long minutes = (totalTimeMillis / 1000) / 60;
        long seconds = (totalTimeMillis / 1000) % 60;

        String formattedTime = String.format("%d:%02d", minutes, seconds);
        myTimerLabel.setText("Total Time: " + formattedTime);
    }

    public JLabel getTimerLabel() {
        return myTimerLabel;
    }
}