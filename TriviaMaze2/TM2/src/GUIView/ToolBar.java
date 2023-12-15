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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * The ToolBar class provides a menu for the Trivia Maze game with options like Start, Save, Load,
 * Exit, About, Rules, and Shortcuts.
 */
public class ToolBar {

    /** Holds Menu bars. */
    private final JMenuBar myToolBar;
    /** Holds menu item (start). */
    private JMenuItem myStart;
    /** Holds menu item (save). */
    private JMenuItem mySave;
    /** Holds menu item (load). */
    private JMenuItem myLoad;
    /** Holds menu item (exit). */
    private JMenuItem myExit;
    /** Holds menu item (about). */
    private JMenuItem myAbout;
    /** Holds menu item (rules). */
    private JMenuItem myRules;
    /** Holds menu item (shortcut keys instructions). */
    private JMenuItem myShortCuts;
    /** Holds the Maze menu. */
    private JMenu myMazeMenu;
    /** Holds the Help menu. */
    private JMenu myHelpMenu;
    private StatsPanel myStatsPanel;

    private Maze myMaze;
    private MazePanel mazePanel;
    private Frame myFrame;

    /**
     * Constructs the ToolBar and initializes the fields.
     *
     * @param theFrame       The main frame of the game.
     * @param myStatsPanel   The stats panel associated with the maze.
     * @param theMaze        The maze associated with the game.
     * @param theMazePanel   The maze panel displaying the maze.
     */
    public ToolBar(Frame theFrame, StatsPanel myStatsPanel, Maze theMaze, MazePanel theMazePanel) {
        myToolBar = new JMenuBar();
        myToolBar.setBorder(BorderFactory.createLineBorder(Color.black));
        setMazeHelpMenu();
        addListeners();
        this.myStatsPanel = myStatsPanel;
        myMaze = theMaze;
        this.mazePanel = theMazePanel;
        myFrame = theFrame;
        setMyShortCuts();
    }

    /**
     * Sets keyboard shortcuts for menu items.
     */
    private void setMyShortCuts() {
        myRules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        myExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        myStart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        mySave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        myLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        myAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        myShortCuts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
    }

    /**
     * Sets up the Maze and Help menus.
     */
    private void setMazeHelpMenu() {
        myMazeMenu = new JMenu("Maze Game");
        myStart = new JMenuItem("Start Game\t\tCTRL + S");
        mySave = new JMenuItem("Save Game\t\tCTRL + V");
        myLoad = new JMenuItem("Load Game\t\tCTRL + L");
        myExit = new JMenuItem("Exit Game\t\tCTRL + E");

        myMazeMenu.add(myStart);
        myMazeMenu.add(mySave);
        myMazeMenu.add(myLoad);
        myMazeMenu.add(myExit);

        myHelpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About              CTRL + A");
        myRules = new JMenuItem("Rules               CTRL + R");
        myShortCuts = new JMenuItem("Shortcuts        CTRL + C");
        myHelpMenu.add(myAbout);
        myHelpMenu.add(myRules);
        myHelpMenu.add(myShortCuts);

        myToolBar.add(myMazeMenu);
        myToolBar.add(myHelpMenu);

        mySave.setEnabled(false);
        myExit.setEnabled(false);
    }

    /**
     * Sets the preferred size of the toolbar.
     *
     * @return The toolbar component with the preferred size.
     */
    public Component setToolBarDimension() {
        myToolBar.setPreferredSize(new Dimension(865, 30));
        return myToolBar;
    }

    /**
     * Gets the toolbar.
     *
     * @return The toolbar.
     */
    public JMenuBar getToolBar() {
        return myToolBar;
    }

    /**
     * Adds listeners to menu items.
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
     * Adds a listener to the Load Game menu item.
     *
     * @param theLoad The Load Game menu item.
     */
    private void addLoadGameListener(JMenuItem theLoad) {
        theLoad.addActionListener(theEvent -> {
            String fileName = JOptionPane.showInputDialog("Enter saved game to reload:");
            if (fileName != null) {
                try {
                    Maze newMaze = myMaze.loadGame(fileName);

                    // Pass the new maze to the Frame's resetMaze method
                    myFrame.resetMaze(newMaze);

                    myStart.setEnabled(false);
                    mySave.setEnabled(true);
                    myLoad.setEnabled(false);
                    myExit.setEnabled(true);
                    myStatsPanel.setPlayerName("Enter your player name again: ");
                    SystemSound loadGameSound = new SystemSound(new File("load-game.wav"));
                    loadGameSound.gameSounds();
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
     * Adds a listener to the Save Game menu item.
     *
     * @param theSave The Save Game menu item.
     */
    private void addSaveGameListener(JMenuItem theSave) {
        theSave.addActionListener(theEvent -> {
            String fileName = JOptionPane.showInputDialog("Enter a name for your saved game:");
            if (fileName != null) {
                String filePath = fileName + ".ser";
                try {
                    myMaze.saveGame(filePath);
                    myStart.setEnabled(true);
                    mySave.setEnabled(false);
                    myLoad.setEnabled(true);
                    myExit.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "Error saving the game: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            SystemSound SaveGameSound = new SystemSound(new File("Save-Game.wav"));
            SaveGameSound.gameSounds();
            myStatsPanel.getDisTimer().stop();
        });
    }

    /**
     * Adds a listener to the Start Game menu item.
     *
     * @param theStart The Start Game menu item.
     */
    public void addStartGameListener(JMenuItem theStart) {
        theStart.addActionListener(theEvent -> {
            myMaze.startGame();
            myStatsPanel.getDisTimer().start();
            myStatsPanel.setPlayerName("Enter your name: ");
            SystemSound sound = new SystemSound(new File("Game-Opener.wav"));
            sound.gameSounds();
            theStart.setEnabled(false);
            mySave.setEnabled(true);
            myLoad.setEnabled(false);
            myExit.setEnabled(true);
        });
    }

    /**
     * Adds a listener to the Exit Game menu item.
     *
     * @param theExit The Exit Game menu item.
     */
    private void addExitListener(final JMenuItem theExit) {
        theExit.addActionListener(theEvent -> {
            SystemSound sound = new SystemSound(new File("game-over-471.wav"));
            sound.gameSounds();
            System.exit(0);
            myStatsPanel.getDisTimer().stop();
        });

        theExit.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isMetaDown() && e.getKeyCode() == KeyEvent.VK_E) {
                    SystemSound sound = new SystemSound(new File("game-over-471.wav"));
                    sound.gameSounds();
                    System.exit(0);
                    myStatsPanel.getDisTimer().stop();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // Not needed for this example
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not needed for this example
            }
        });
    }

    /**
     * Adds a listener to the About menu item.
     *
     * @param theAbout The About menu item.
     */
    private void addAboutListener(final JMenuItem theAbout) {
        theAbout.addActionListener(theEvent -> {
            String aTeam = "Team9.jpeg";
            ImageIcon icon = new ImageIcon(aTeam);
            final StringBuilder build = new StringBuilder();
            build.append("Authors: Matiullah Jalal\n"
                    + "               Hawo Issa\n"
                    + "               Zakariye Luqman\n"
                    + "Code Version: 11/29/2023\n"
                    + "Java Version: \"13.0.8\" 2021-07-20\n\n");
            JOptionPane.showMessageDialog(null, build, "Team 9",
                    JOptionPane.INFORMATION_MESSAGE, icon);
        });
    }

    /**
     * Adds a listener to the Rules menu item.
     *
     * @param theRules The Rules menu item.
     */
    private void addRulesListener(final JMenuItem theRules) {
        theRules.addActionListener(e -> displayRules());
    }

    private void displayRules() {
        String gameRules = "GRule.png";
        ImageIcon icon = new ImageIcon(gameRules);
        final StringBuilder build = new StringBuilder();
        build.append("      ****** Trivia Maze Rules ******\n");
        build.append("Trivia Maze is a fun game, that can be" +
            " played by a single player.\n" +
            " A player will select a question type" +
            " and then answer the question.\n" +
            " If the answer to the question is correct, then the player will move on" +
            " and a door will open.\n" +
            " Otherwise, the door will close, and" +
            " after a couple of wrong answers, the" +
            " game ends.");
        JOptionPane.showMessageDialog(null, build.toString(),null,
                JOptionPane.INFORMATION_MESSAGE, icon);
    }

    private void addShortCutListener(final JMenuItem theShortCuts) {
        theShortCuts.addActionListener(theEvent -> {
            displayShortcuts();
        });
    }

    private void displayShortcuts() {
        final StringBuilder build = new StringBuilder();
        final String shortCut = "shortcut.png";
        final ImageIcon icon = new ImageIcon(shortCut);
        build.append("|--Trivia Maze Shortcut Keys --| \n");
        build.append(String.format("Start Game: %-8s CTR + S %s","","\n"));
        build.append(String.format("Save Game: %-8s CTR + V %s","","\n"));
        build.append(String.format("Load Game: %-8s CTR + L%s","","\n"));
        build.append(String.format("Exit Game: %-8s  CTR + E %s","","\n"));
        build.append(String.format("About: %-15s CTR + A %s","","\n"));
        build.append(String.format("Rules: %-16s CTR + R %s","","\n"));
        build.append(String.format("Shortcuts: %-10s CTR + C %s","","\n"));
        JOptionPane.showMessageDialog(null, build.toString(),"Short Cut",
                JOptionPane.INFORMATION_MESSAGE,icon);
    }
}
