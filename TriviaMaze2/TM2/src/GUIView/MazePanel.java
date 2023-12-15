package GUIView;

import Model.Maze;
import Model.QuestionAnswer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class MazePanel extends JPanel {

    private JPanel myMazePanel;
    private QAPanel myCurrentQAPanel;

    private StatsPanel myStatsPanel;
    private JButton myButton = new JButton("arrow");
    Maze myMaze;
    private static final char[][] letterGrid = {
        {'A', 'B', 'C', 'D'},
        {'E', 'F', 'G', 'H'},
        {'I', 'J', 'K', 'L'},
        {'M', 'N', 'O', 'P'}
    };


    public MazePanel( final Maze theMaze, final QAPanel theQAPanel, final StatsPanel theStatsPanel) throws IOException {
        myMaze = theMaze;
        myStatsPanel=theStatsPanel;
        myCurrentQAPanel = theQAPanel;
        myMazePanel = new JPanel();
        GridLayout myGD = new GridLayout(4, 4);
        myMazePanel.setLayout(myGD);
        setUpMazePanel('A');
        myMazePanel.setPreferredSize(new Dimension(400, 550));  // previously (250, 250)
        //myMazePanel.setBackground(Color.ORANGE);
        myMazePanel.setBackground(Color.RED);
        //highlightCurrentRoom();
        setMyShortCuts();
    }
    private JPanel createLetterPanel ( char letter, int row, int col) throws IOException {
        JPanel letterPanel = new JPanel();
        letterPanel.setLayout(new BorderLayout());
        // Create a sub-panel for the arrows and letter
        JPanel contentPanel = new JPanel(new BorderLayout());
        // Create a label for the letter and center it
        if (myMaze.getMyCurrentRoom().getLetter() == letter) {
            JLabel letterLabel = new JLabel("OO", SwingConstants.CENTER);
            letterLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 32));
        } else {
            JLabel letterLabel = new JLabel(Character.toString(letter), SwingConstants.CENTER);
            letterLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        }
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

    private JButton createLockedButton() {
        JButton lockedButton = new JButton("X");
        lockedButton.setPreferredSize(new Dimension(20, 20));
        lockedButton.setEnabled(false); // Disable the button
        lockedButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        lockedButton.setFont(new Font("Arial", Font.BOLD, 20));
        return lockedButton;
    }
    private JButton createArrowButton (String label){
        JButton arrowButton = new JButton(label);
        arrowButton.setPreferredSize(new Dimension(20, 20));
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myMaze.isGameOn()) {
                    if (label == "↑") {
                        myMaze.moveUp();
                        myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
                        System.out.println(myMaze.getMyCurrentRoom().getLetter());
                    } else if (label == "↓") {
                        myMaze.moveDown();
                        myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());

                        System.out.println(myMaze.getMyCurrentRoom().getLetter());
                    } else if (label == "←") {
                        myMaze.moveLeft();
                        myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());
                        System.out.println(myMaze.getMyCurrentRoom().getLetter());
                    } else if (label == "→") {
                        myMaze.moveRight();
                        myCurrentQAPanel.changeRoomLetter(myMaze.getMyCurrentRoom());

                        System.out.println(myMaze.getMyCurrentRoom().getLetter());
                    }
                    try {
                        updateMazePanel();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        arrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        return arrowButton;
    }
    private void setUpMazePanel ( char start) throws IOException {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                char letter = letterGrid[row][col];
                myMazePanel.add(createLetterPanel(letter, row, col));
            }
        }
    }

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

    public JPanel getMyMazePanel () {
        return myMazePanel;
    }
    public Maze getMyMaze () {
        return myMaze;
    }
    public void setMyMaze(Maze theMaze) {
        this.myMaze = theMaze;
    }
}
