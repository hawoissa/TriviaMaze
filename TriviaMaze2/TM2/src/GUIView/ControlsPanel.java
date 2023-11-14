package GUIView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class ControlsPanel extends JPanel {

    private JPanel myControlPanel;

    public ControlsPanel() {
        myControlPanel = new JPanel(new BorderLayout());
        myControlPanel.setPreferredSize(new Dimension(350, 350));
        myControlPanel.setBackground(Color.ORANGE);



        JLabel controlsLabel = new JLabel();
        controlsLabel.setSize(350,350);

        controlsLabel.setText(
                "Controls: ← - Move Left \n↑ - Move Up \n→ - Move Right \n↓ - Move Down");



        myControlPanel.add(controlsLabel, BorderLayout.CENTER);



    }



    @Override
    protected void paintComponent(Graphics g) {

        g.drawString("Controls:", 10, 20);
        g.drawString("← - Move Left", 10, 40);
        g.drawString("↑ - Move Up", 10, 60);
        g.drawString("→ - Move Right", 10, 80);
        g.drawString("↓ - Move Down", 10, 100);


        // Draw arrow key labels
        myControlPanel.paintComponents(g);
    }

    public JPanel getMyControlPanel() {
        return myControlPanel;
    }
}
