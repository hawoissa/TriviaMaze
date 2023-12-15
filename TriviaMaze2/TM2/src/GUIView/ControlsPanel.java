package GUIView;

import Model.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * The ControlsPanel class represents the control panel for the maze game.
 * It includes arrow buttons for navigation and displays corresponding controls.
 */
public class ControlsPanel extends JPanel {

    private JPanel myControlPanel;
    private Maze myMaze;
    private MazePanel myMazePanel;

    /**
     * Constructs a ControlsPanel with the specified maze and maze panel.
     *
     * @param maze       The maze associated with the control panel.
     * @param mazePanel  The maze panel associated with the control panel.
     */
    public ControlsPanel(Maze maze, MazePanel mazePanel) {
        myMaze = maze;
        myMazePanel = mazePanel;
        myControlPanel = new JPanel(new GridLayout(3, 3));
        myControlPanel.setPreferredSize(new Dimension(865, 150));
        myControlPanel.setBackground(new Color(240, 220, 30, 255));

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

    /**
     * Gets the control panel.
     *
     * @return The control panel.
     */
    public JPanel getMyControlPanel() {
        return myControlPanel;
    }

    /**
     * Creates an arrow button with the specified label.
     *
     * @param label The label for the arrow button.
     * @return The created arrow button.
     */
    private JButton createArrowButton(String label) {
        JButton arrowButton = new JButton(label);
        arrowButton.setPreferredSize(new Dimension(90, 90));
        arrowButton.addActionListener(e -> handleMove(getDirectionForLabel(label)));
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        arrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        return arrowButton;
    }

    /**
     * Gets the direction associated with the specified label.
     *
     * @param label The label associated with the direction.
     * @return The direction associated with the label.
     * @throws IllegalArgumentException if the label is invalid.
     */
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

    /**
     * Handles the move based on the specified direction.
     *
     * @param direction The direction of the move.
     */
    public void handleMove(Direction direction) {
        if (myMaze.isGameOn()) {
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
    }

    /**
     * Enumeration representing the possible directions for a move.
     */
    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    /**
     * Updates the associated maze panel.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void updateMazePanel() throws IOException {
        myMazePanel.updateMazePanel();
    }
}

