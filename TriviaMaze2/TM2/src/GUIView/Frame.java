/*
 *  Names: Matiullah Jalal
 *  Quareter: Fall 2023
 *  Course: TCSS 360
 */
package GUIView;
import Model.Maze;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * This class shows a frame of x by y pixels and will hold all the relevant stuff.
 */
public class Frame extends JFrame {

    // Constants for frame dimensions
    private static final int MY_WEIGHT = 900;
    private static final int MY_HEIGHT = 850;

    // Frame components
    private JFrame myFrame;
    private final ToolBar myToolBar;
    private MazePanel myMazePanel;
    private StatsPanel myStatsPanel;
    private QAPanel myFrameQAPanel;
    private ControlsPanel myControlPanel;
    private Maze myMaze;

    /**
     * Constructs frame and initializes the fields.
     */
    public Frame() throws IOException {
        myMaze = new Maze();
        myFrameQAPanel = new QAPanel(myMaze); // Instantiate myFrameQAPanel first
        myStatsPanel = new StatsPanel(myMaze);
        myToolBar = new ToolBar(myStatsPanel);
        myMazePanel = new MazePanel(myMaze, myFrameQAPanel);
        myControlPanel = new ControlsPanel(myMaze, myMazePanel);

        buildFrame();
        setLayout(new BorderLayout());
        setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        //setFocusTraversalPolicy(new LayoutFocusTraversalPolicy());
//        this.add(myMazePanel.getMyMazePanel());
//        this.add(myStatsPanel.getMyStatsPanelPanel());
//        this.add(myControlPanel.getMyControlPanel());
//        this.add(myFrameQAPanel.getQAPanel());
        addToolBar();
        add(myFrameQAPanel.getQAPanel(), BorderLayout.WEST);
        add(myMazePanel.getMyMazePanel(), BorderLayout.CENTER);
        add(myStatsPanel.getMyStatsPanelPanel(), BorderLayout.EAST);
        add(myControlPanel.getMyControlPanel(), BorderLayout.SOUTH);


    }

    /**
     * Adds toolbar and its items to the Frame.
     */
    private void addToolBar() {
        this.add(myToolBar.getToolBar(), myToolBar.setToolBarDimension()); // Specify BorderLayout.NORTH
    }

    /**
     * Builds a frame with two colors.
     */
    private void buildFrame() {
        setSize(MY_WEIGHT, MY_HEIGHT);
        setTitle("Trivia Maze Game");
        setResizable(false);
        setLocationRelativeTo(null);
        setColorOfFrame();
    }

    /**
     * Private method to set the color of the frame.
     */
    private void setColorOfFrame() {
        Color color1 = new Color(0, 128, 128, 153);
        Color color2 = new Color(0, 0, 255, 192);
        // This creates a gradient paint color
        GradientPaint gradient = new GradientPaint(0, 0, color1, MY_WEIGHT, MY_HEIGHT, color2);
        // Setting contents of the pane using JPanel
        setContentPane(new JPanel() {
            /**
             * Overrides paintComponent interface.
             * @param g the <code>Graphics</code> object to protect
             */
            @Override
            protected void paintComponent(Graphics g) {
                // super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, MY_WEIGHT, MY_HEIGHT);
            }
        });
    }
}
