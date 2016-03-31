package composite.ox.ui;

import javax.swing.*;
import java.awt.*;

class OxPage extends JPanel {
    protected final OxGameForm form;

    OxPage(OxGameForm form) {
        this.form = form;

        setOpaque(false);
        setBackground(new Color(255, 255, 255));
    }

    @Override
    protected void paintComponent(Graphics graph)
    {
        Graphics2D g = (Graphics2D)graph.create();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        g.setColor(getBackground());
        g.fillRect(0, 0, w - 1, h - 1);
        g.setColor(Color.BLUE);
        g.drawRect(0, 0, w - 1, h - 1);

        g.dispose();
        super.paintComponent(graph);
    }
}
