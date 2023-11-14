package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private JPanel myStatsPanel;
    private Maze myCurrentMaze;

    private JLabel myRoomLabel;

    public StatsPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myStatsPanel = new JPanel();
        myStatsPanel.setPreferredSize(new Dimension(350, 350));
        myStatsPanel.setBackground(Color.ORANGE);

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
}
