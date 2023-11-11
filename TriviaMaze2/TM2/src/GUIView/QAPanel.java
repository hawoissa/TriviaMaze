package GUIView;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;

public class QAPanel extends JPanel {

    private JPanel myQAPanel;
    private Maze myCurrentMaze;

    private JLabel myRoomLabel;

    public QAPanel(final Maze theMaze) {
        myCurrentMaze = theMaze;
        myQAPanel = new JPanel();
        myQAPanel.setPreferredSize(new Dimension(350, 350));
        myQAPanel.setBackground(Color.ORANGE);

        myRoomLabel = new JLabel();
        myRoomLabel.setSize(350,350);
        myRoomLabel.setText(Character.toString('A'));
        myQAPanel.add(myRoomLabel);
    }

    public void changeRoomLetter(final Room theRoom) {
        myRoomLabel.setText(Character.toString(theRoom.getLetter()));
    }




    public JPanel getQAPanel() {
        return myQAPanel;
    }
}
