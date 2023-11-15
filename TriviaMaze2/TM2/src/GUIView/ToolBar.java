/*
    Names: Matiullah Jalal
    Date: 10/29/2023
    Course: TCSS 360
    Quarter: Winter 2023
 */
package GUIView;
import Model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This class provides Menu for Trivia Maze game and Help,
 * and relevant menu items like start and save.
 */
public class ToolBar {

    //private Frame myFrame = new Frame();
    /** Holds Menu bars.*/
    private final JMenuBar myToolBar;
    /** Holds menu item (start).*/
    private JMenuItem myStart;
    /** Holds menu item (save).*/
    private JMenuItem mySave;
    /** Holds menu item (load).*/
    private JMenuItem myLoad;
    /** Holds menu item (exit).*/
    private JMenuItem myExit;
    /** Holds menu item (about).*/
    private JMenuItem myAbout;
    /** Holds menu item (rules).*/
    private JMenuItem myRules;
    /** Holds menu item (shortcut keys instructions).*/
    private JMenuItem myShortCuts;
    /** Holds the Maze menu. */
    private JMenu myMazeMenu;
    /** Holds the Help menu. */
    private JMenu myHelpMenu;

    /**
     * Constructs the class and initializes the fields.
     */
    public ToolBar(){
        myToolBar = new JMenuBar();
        myToolBar.setBorder(BorderFactory.createLineBorder(Color.black));
        setMazeHelpMenu();
        setMnemonic();

    }

    /**
     * Creates the Maze and Help menu and added to toolbar.
     */
    private void setMazeHelpMenu() {
        // Maze menu and its item
        myMazeMenu = new JMenu("Maze Game");
        myStart = new JMenuItem("Start Game\t\tCTRL\t   +  S");
        mySave = new JMenuItem("Save Game\t\tCTRL\t   +  V");
        myLoad = new JMenuItem("Load Game\t\tCTRL\t   +  L");
        myExit = new JMenuItem("Exit Game\t\t  CTRL\t   +  E");

        myMazeMenu.add(myStart);
        myMazeMenu.add(mySave);
        myMazeMenu.add(myLoad);
        myMazeMenu.add(myExit);

        // Help menu and its items
        myHelpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About              CTRL + A");
        myRules = new JMenuItem("Rules               CTRL + R");
        myShortCuts = new JMenuItem("Shortcuts        CTRL + C");
        myHelpMenu.add(myAbout);
        myHelpMenu.add(myRules);
        myHelpMenu.add(myShortCuts);

        // add all to toolbar
        myToolBar.add(myMazeMenu);
        myToolBar.add(myHelpMenu);
    }

    /**
     * A getter method for toolbar of the frame.
     * @return returns the toolbar.
     */
    public JMenuBar getToolBar(){
        return myToolBar;
    }

    /**
     * Sets shortcut keys, using setMnemonic and KeyEven.
     */
    private void setMnemonic() {
        // Mnemonic keys for shortcuts
        myStart.setMnemonic(KeyEvent.VK_S);
        mySave.setMnemonic(KeyEvent.VK_V);
        myLoad.setMnemonic(KeyEvent.VK_L);
        myExit.setMnemonic(KeyEvent.VK_E);
        myAbout.setMnemonic(KeyEvent.VK_A);
        myRules.setMnemonic(KeyEvent.VK_R);
        myShortCuts.setMnemonic(KeyEvent.VK_C);
        addListeners();
    }

    /**
     * This method has all the methods for action listeners.
     */
    private void addListeners() {
        addShortCutListener(myShortCuts);
        addRulesListener(myRules);
        addAboutListener(myAbout);
        addExitListener(myExit);
        addStartGameListener(myStart);
        addLoadGameListener(myLoad);
        addSaveGameListener(mySave);
    }

    /**
     * Sets action listener to Load game.
     * @param theSave is the load menu item.
     */

    private void addSaveGameListener(JMenuItem theSave) {
        theSave.addActionListener(theEvent -> {
            String fileName = JOptionPane.showInputDialog("Enter a name for your saved game:");
            if (fileName != null) {
                String filePath = fileName + ".ser";
                try {
                    Maze myMaze = new Maze();
                    myMaze.saveGame(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                        "Error saving the game: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addLoadGameListener(JMenuItem theLoad) {
        theLoad.addActionListener(theEvent -> {
            String fileName = JOptionPane.showInputDialog("Enter saved game to reload:");
            if (fileName != null) {
                try {
                    Maze myMaze = new Maze();
                    myMaze = Maze.loadGame(fileName);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                        "Error loading the game: " + e.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * Sets action listener to start game.
     * @param theStart is the start menu item.
     */
    private void addStartGameListener(JMenuItem theStart) {
        theStart.addActionListener(theEvent -> {
            Maze myMaze = new Maze();
            myMaze.startGame();
        });
    }


    /**
     * Exit from Game.
     * @param theExit exits program.
     */
    private void addExitListener(final JMenuItem theExit) {
        theExit.addActionListener(theEvent -> {
            System.exit(0);
        });
    }

    /**
     * Gives details about version of the program.
     * @param theAbout  is the program version.
     */
    private void addAboutListener(final JMenuItem theAbout) {
        theAbout.addActionListener(theEvent -> {
            final StringBuilder build = new StringBuilder();
            build.append("Authors: Matiullah Jalal\n"
                    + "               Hawo Issa\n"
                    + "               Zakariye Luqman\n"
                    + "Code Version: 10/28/2023\n"
                    + "Java Version: \"13.0.8\" 2021-07-20\n\n");
            JOptionPane.showMessageDialog(null,  build);
        });
    }

    /**
     * This method give details  about the game rules.
     * @param theRules is the  Rules menu item.
     */

    private void addRulesListener(final JMenuItem theRules) {
        theRules.addActionListener(theEvent -> {
            final StringBuilder build = new StringBuilder();
            build.append("      ****** Trivia Maze Rules ******\n");
            build.append("Trivia Maze is a fun game, that can be" +
                    " played by a single player.\n" +
                    " A player will select question type" +
                    " and then answer the question.\n" +
                    " If answer to the question right then the player will move on" +
                    " and a door will open.\n" +
                    " otherwise the door will close and" +
                    " after couple wrong answers the" +
                    " game ends.");
            JOptionPane.showMessageDialog(null, build.toString());
        });
    }

    /**
     * This method gives details of the shortcut keys used in Toolbar.
     * @param theShortCuts is the shortcut keys.
     */
    private void addShortCutListener(final JMenuItem theShortCuts) {
        theShortCuts.addActionListener(theEvent -> {
            final StringBuilder build = new StringBuilder();
            build.append("|--Trivia Maze Shortcut Keys --| \n");
            build.append("\tStart Game:\t\t CTR\t+\tS\n" + "\tSave Game:\t\t CTR\t+V\n");
            build.append("\tLoad Game:\t\t CTR\t+\tL\n" + "\tExit Game:\t\t  CTR\t+\tE\n");
            build.append("\tAbout:\t\t\t\t\t\t\t\t\t\tCTR\t+\tA\n"
                    + "\tRules:\t\t\t\t\t\t\t\t\t\t\tCTR\t+\tR\n");
            build.append("\tShortcuts:\t\t\t\t\t\tCTR\t+\tC\n");
            JOptionPane.showMessageDialog(null, build.toString());
        });
    }
}
