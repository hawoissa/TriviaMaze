package GUIView;

import Model.Maze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * The MazePanel class represents the panel displaying the maze in the GUI.
 */
public class MazePanel extends JPanel {

    private JPanel myMazePanel;
    private QAPanel myCurrentQAPanel;

    private StatsPanel myStatsPanel;
    private Maze myMaze;
    private static final char[][] letterGrid = {
            {'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H'},
            {'I', 'J', 'K', 'L'},
            {'M', 'N', 'O', 'P'}
    };

    /**
     * Constructs a MazePanel with the specified maze, question-answer panel, and stats panel.
     *
     * @param theMaze      The maze associated with the panel.
     * @param theQAPanel   The question-answer panel associated with the maze.
     * @param theStatsPanel The stats panel associated with the maze.
     * @throws IOException if an I/O error occurs.
     */
    public MazePanel(final Maze theMaze, final QAPanel theQAPanel, final StatsPanel theStatsPanel) throws IOException {
        myMaze = theMaze;
        myStatsPanel = theStatsPanel;
        myCurrentQAPanel = theQAPanel;
        myMazePanel = new JPanel();
        GridLayout myGD = new GridLayout(4, 4);
        myMazePanel.setLayout(myGD);
        setUpMazePanel('A');
        myMazePanel.setPreferredSize(new Dimension(400, 550));
        myMazePanel.setBackground(Color.RED);
        setMyShortCuts();
    }

    /**
     * Creates a letter panel for a specific letter at the given row and column.
     *
     * @param letter The letter to be displayed in the panel.
     * @param row    The row of the panel.
     * @param col    The column of the panel.
     * @return The created letter panel.
     * @throws IOException if an I/O error occurs.
     */
    private JPanel createLetterPanel(char letter, int row, int col) throws IOException {
        JPanel letterPanel = new JPanel();
        letterPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel letterLabel = new JLabel(Character.toString(letter), SwingConstants.CENTER);
        letterLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));

        if (row > 0) {
            contentPanel.add(createArrowButton("↓"), BorderLayout.NORTH);
        }
        if (row < 3) {
            contentPanel.add(createArrowButton("↑"), BorderLayout.SOUTH);
        }
        if (col > 0) {
            contentPanel.add(createArrowButton("→"), BorderLayout.WEST);
        }
        if (col < 3) {
            contentPanel.add(createArrowButton("←"), BorderLayout.EAST);
        }

        if (myMaze.getMyCurrentRoom().getLetter() == letter) {
            contentPanel.setBackground(Color.BLACK);
            letterLabel.setText("?");
            letterLabel.setForeground(Color.WHITE);
        }

        contentPanel.add(letterLabel, BorderLayout.CENTER);
        letterPanel.add(contentPanel, BorderLayout.CENTER);
        return letterPanel;
    }

    /**
     * Updates the maze panel.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void updateMazePanel() throws IOException {
        myMazePanel.removeAll();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                char letter = letterGrid[row][col];
                myMazePanel.add(createLetterPanel(letter, row, col));
            }
        }
        myCurrentQAPanel.updateContent();
        myStatsPanel.setChancesLabel();
        myMazePanel.revalidate();
        myMazePanel.repaint();
    }

    /**
     * Creates a locked button.
     *
     * @return The created locked button.
     */
    private JButton createLockedButton() {
        JButton lockedButton = new JButton("X");
        lockedButton.setPreferredSize(new Dimension(20, 20));
        lockedButton.setEnabled(false); // Disable the button
        lockedButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        lockedButton.setFont(new Font("Arial", Font.BOLD, 20));
        return lockedButton;
    }

    /**
     * Creates an arrow button with the specified label.
     *
     * @param label The label for the arrow button.
     * @return The created arrow button.
     */
    private JButton createArrowButton(String label) {
        JButton arrowButton = new JButton(label);
        arrowButton.setPreferredSize(new Dimension(20, 20));
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myMaze.isGameOn()) {
                    handleArrowKey(label);
                }
            }
        });
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        arrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        return arrowButton;
    }

    /**
     * Sets up the maze panel with the starting letter.
     *
     * @param start The starting letter.
     * @throws IOException if an I/O error occurs.
     */
    private void setUpMazePanel(char start) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                char letter = letterGrid[row][col];
                myMazePanel.add(createLetterPanel(letter, row, col));
            }
        }
    }

    /**
     * Sets up keyboard shortcuts for arrow keys.
     */
    private void setMyShortCuts() {
        myMazePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
        myMazePanel.getActionMap().put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleArrowKey("↑");
            }
        });

        myMazePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
        myMazePanel.getActionMap().put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleArrowKey("↓");
            }
        });

        myMazePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
        myMazePanel.getActionMap().put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleArrowKey("←");
            }
        });

        myMazePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
        myMazePanel.getActionMap().put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleArrowKey("→");
            }
        });
    }

    /**
     * Handles the arrow key action.
     *
     * @param label The label of the arrow key.
     */
    private void handleArrowKey(String label) {
        if (label.equals("↑")) {
            myMaze.moveUp();
            myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
        } else if (label.equals("↓")) {
            myMaze.moveDown();
            myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
        } else if (label.equals("←")) {
            myMaze.moveLeft();
            myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
        } else if (label.equals("→")) {
            myMaze.moveRight();
            myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
        }
        try {
            updateMazePanel();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Gets the maze panel.
     *
     * @return The maze panel.
     */
    public JPanel getMyMazePanel() {
        return myMazePanel;
    }

    /**
     * Gets the associated maze.
     *
     * @return The associated maze.
     */
    public Maze getMyMaze() {
        return myMaze;
    }

    /**
     * Sets the maze for the panel.
     *
     * @param theMaze The maze to be set.
     */
    public void setMyMaze(Maze theMaze) {
        this.myMaze = theMaze;
    }
}
