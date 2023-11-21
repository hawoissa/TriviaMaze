package GUIView;

import Model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

public class ControlsPanel extends JPanel {

    private JPanel myControlPanel;
    private Maze myMaze;
    private MazePanel myMazePanel;

    public ControlsPanel(Maze maze, MazePanel mazePanel) {
        myMaze = maze;
        myMazePanel = mazePanel;
        myControlPanel = new JPanel(new BorderLayout());
        myControlPanel.setPreferredSize(new Dimension(350, 250));
        myControlPanel.setBackground(Color.ORANGE);

        JTextArea controlsArea = new JTextArea();
        controlsArea.setEditable(false);
        controlsArea.setLineWrap(true);
        controlsArea.setWrapStyleWord(true);

        controlsArea.setText(
            "Controls:\n\n"
                + "\u2190 - Move Left\n"
                + "\u2191 - Move Up\n"
                + "\u2192 - Move Right\n"
                + "\u2193 - Move Down");

        controlsArea.setFont(new Font("SansSerif", Font.BOLD, 24));
        controlsArea.setForeground(Color.DARK_GRAY);

        myControlPanel.add(controlsArea, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Controls:", 10, 20);
        g.drawString("\u2190 - Move Left", 10, 50);
        g.drawString("\u2191 - Move Up", 10, 80);
        g.drawString("\u2192 - Move Right", 10, 110);
        g.drawString("\u2193 - Move Down", 10, 140);
    }

    public JPanel getMyControlPanel() {
        return myControlPanel;
    }

    // Method to handle move based on user input or trigger
   /* public void handleMove(Direction direction) {
        switch (direction) {
            case UP:
                myMaze.moveUp();
                break;
            case DOWN:
                myMaze.moveDown();
                break;
            case RIGHT:
                myMaze.moveRight();
                break;
            case LEFT:
                myMaze.moveLeft();
                break;
        }
        // Optionally update UI or perform other actions after the move
        try {
            updateMazePanel();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }*/

    private void updateMazePanel() throws IOException {
        //UPDATE MAZE PANEL
    }
}
