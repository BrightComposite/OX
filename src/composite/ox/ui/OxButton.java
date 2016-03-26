package composite.ox.ui;

import javax.swing.*;
import java.awt.*;

class OxButton extends JButton {
    OxButton() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setFocusable(false);
        setAlignmentX(CENTER_ALIGNMENT);
    }

    protected void paintComponent(Graphics graph)
    {
        Graphics2D g = (Graphics2D)graph.create();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g.setColor(Color.RED);
        g.drawRect(0, 0, w - 1, h - 1);

        g.dispose();
        super.paintComponent(graph);
    }
}
