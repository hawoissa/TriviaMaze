package GUIView;

import Model.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        myControlPanel = new JPanel(new GridLayout(3, 3));
        myControlPanel.setPreferredSize(new Dimension(350, 250));
        myControlPanel.setBackground(Color.ORANGE);

        // Add empty labels for layout purposes
        myControlPanel.add(new JLabel());
        myControlPanel.add(createArrowButton("↑   Up Arrow"));
        myControlPanel.add(new JLabel());

        myControlPanel.add(createArrowButton("←   Left Arrow"));
        myControlPanel.add(new JLabel());
        myControlPanel.add(createArrowButton("→   Right Arrow"));

        myControlPanel.add(new JLabel());
        myControlPanel.add(createArrowButton("↓   Down Arrow"));
        myControlPanel.add(new JLabel());
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

    private JButton createArrowButton(String label) {
        JButton arrowButton = new JButton(label);
        arrowButton.setPreferredSize(new Dimension(90, 90));
        arrowButton.addActionListener(e -> handleMove(getDirectionForLabel(label)));
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        arrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        return arrowButton;
    }

    private Direction getDirectionForLabel(String label) {
        switch (label) {
            case "↑   Up Arrow":
                return Direction.UP;
            case "↓   Down Arrow":
                return Direction.DOWN;
            case "←   Left Arrow":
                return Direction.LEFT;
            case "→   Right Arrow":
                return Direction.RIGHT;
            default:
                throw new IllegalArgumentException("Invalid label");
        }
    }

    // Method to handle move based on user input or trigger
    public void handleMove(Direction direction) {
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
    }

    private void updateMazePanel() throws IOException {
        myMazePanel.updateMazePanel();
    }
}
