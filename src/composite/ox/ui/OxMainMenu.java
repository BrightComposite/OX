package composite.ox.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

class OxMainMenu extends JPanel {
    private JButton playTwoButton;
    private JButton exitButton;

    OxMainMenu() {
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
        setLayout(new GridBagLayout());

        playTwoButton = new OxButton();
        playTwoButton.setText("Два игрока");

        exitButton = new OxButton();
        exitButton.setText("Выход");

        Box container = Box.createVerticalBox();

        container.add(playTwoButton);
        container.add(Box.createVerticalStrut(5));
        container.add(exitButton);

       // container.setAlignmentX(Component.CENTER_ALIGNMENT);
    //    container.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(container);

        setVisible(true);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1)
                    return;

                Window window = SwingUtilities.getWindowAncestor(OxMainMenu.this);
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

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
