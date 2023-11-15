package GUIView;

import Model.Maze;
import Model.QuestionAnswer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class MazePanel extends JPanel {

    private JPanel myMazePanel;
    private QAPanel myCurrentQAPanel;


    private JButton myButton = new JButton("arrow");
    Maze myMaze;
    private static final char[][] letterGrid = {
        {'A', 'B', 'C', 'D'},
        {'E', 'F', 'G', 'H'},
        {'I', 'J', 'K', 'L'},
        {'M', 'N', 'O', 'P'}
    };


    public MazePanel( final Maze theMaze, final QAPanel theQAPanel) throws IOException {
        myMaze = theMaze;

        myCurrentQAPanel = theQAPanel;
        myMazePanel = new JPanel();
        GridLayout myGD = new GridLayout(4, 4);
        myMazePanel.setLayout(myGD);
        setUpMazePanel('A');
        myMazePanel.setPreferredSize(new Dimension(350, 350));
        myMazePanel.setBackground(Color.ORANGE);
        //highlightCurrentRoom();
    }
    private JPanel createLetterPanel ( char letter, int row, int col){
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
        // Add bidirectional arrow buttons to indicate connections
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
        contentPanel.add(letterLabel, BorderLayout.CENTER);
        letterPanel.add(contentPanel, BorderLayout.CENTER);
        return letterPanel;
    }
    private JButton createArrowButton (String label){
        JButton arrowButton = new JButton(label);
        arrowButton.setPreferredSize(new Dimension(20, 20));
        arrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


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
            }
        });
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        arrowButton.setFont(new Font("Arial", Font.BOLD, 20));
        return arrowButton;
    }
    private void setUpMazePanel ( char start){
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                char letter = letterGrid[row][col];
                myMazePanel.add(createLetterPanel(letter, row, col));
            }
        }
    }
    public void highlightCurrentRoom () throws IOException {
        BufferedImage myPicture = ImageIO.read(new File("/Users/zakariyeluqman/Downloads/Q-mark.jpg"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setSize(22, 22);
        myMazePanel.add(picLabel);
    }
    public JPanel getMyMazePanel () {
        return myMazePanel;
    }
    public Maze getMyMaze () {
        return myMaze;
    }
}
